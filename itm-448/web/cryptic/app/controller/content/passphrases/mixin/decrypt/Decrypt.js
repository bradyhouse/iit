/**
 * Start decryption controller. This class listens
 * for the pass phrases panel start decryption click event.
 */
Ext.define('Cryptic.controller.content.passphrases.mixin.decrypt.Decrypt', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'decryptPassphrase',
            selector: 'decryptpassphrase'
        }, {
            ref: 'startDecryption',
            selector: '#start-decryption'
        }, {
            ref: 'postProcess',
            selector: 'postprocess'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'start-decryption': {
                    click: me.onStartDecryptionClick
                }
            }
        });
    },
    // @private
    deleteEncryptedFile: function (fileId) {
        var request = gapi.client.drive.files.delete({
            'fileId': fileId
        });
        request.execute(function(resp) {
            console.log('request.execute > callback');
            console.log('resp');
            console.log(resp);
        });
    },
    // @private
    insertDecryptFile: function (fileName, fileData, callback) {
        console.log('insertDecryptFile');
        const boundary = '-------314159265358979323846';
        const delimiter = "\r\n--" + boundary + "\r\n";
        const close_delim = "\r\n--" + boundary + "--";
        console.log('fileData');
        console.log(fileData);
        var contentType = fileData.type || 'application/octet-stream';
        var metadata = {
            'title': fileName,
            'mimeType': contentType
        };
        var base64Data = btoa(fileData);
        var multipartRequestBody =
            delimiter +
                'Content-Type: application/json\r\n\r\n' +
                JSON.stringify(metadata) +
                delimiter +
                'Content-Type: ' + contentType + '\r\n' +
                'Content-Transfer-Encoding: base64\r\n' +
                '\r\n' +
                base64Data +
                close_delim;
        var request = gapi.client.request({
            'path': '/upload/drive/v2/files',
            'method': 'POST',
            'params': {'uploadType': 'multipart'},
            'headers': {
                'Content-Type': 'multipart/mixed; boundary="' + boundary + '"'
            },
            'body': multipartRequestBody});
        if (!callback) {
            callback = function(file) {
                console.log(file)
            };
        }
        request.execute(function(response) {
            console.log('response');
            console.log(response);
            callback(response);
        });
    },
    // @private
    downloadDecryptFile: function(file, callback) {
        var accessToken,
            xhr = new XMLHttpRequest();
        console.log('downloadFile');
        if (file.downloadUrl) {
            accessToken = gapi.auth.getToken().access_token;
            xhr.open('GET', file.downloadUrl);
            xhr.setRequestHeader('Authorization', 'Bearer ' + accessToken);
            xhr.onload = function() {
                callback(xhr.responseText);
            };
            xhr.onerror = function() {
                callback(null);
            };
            xhr.send();
        } else {
            callback(null);
        }
    },
    onStartDecryptionClick: function (ctrl) {
        var me = this,
            pa = me.getPassphrases(),
            psp = me.getDecryptPassphrase(),
            pp = me.getPostProcess(),
            form = ctrl.up('form').getForm(),
            file = Cryptic.Application.file;
        console.log('onStartDecryptionClick');
        console.log(file);
        switch(Cryptic.Application.target) {
            case 'googledrive':
                me.downloadDecryptFile(file, function(blob) {
                    var password = psp.getValue(),
                        decrypted = null,
                        title = file.title.replace('.pie',''),
                        raw = atob(blob);
                    console.log('downloadDecryptFile');
                    console.log('title');
                    console.log(title);
                    console.log('raw');
                    console.log(raw);
                    console.log('password');
                    console.log(password);
                    if (password) {
                        pa.setLoading('Decrypting ...', true);
                        // Use the CryptoJS library and the AES cypher to encrypt the
                        // contents of the file, held in e.target.result, with the password
                        decrypted = CryptoJS.AES.decrypt(raw, password).toString(CryptoJS.enc.Latin1);
                        decrypted['name'] = title;
                        if(!/^data:/.test(decrypted)){
                            pa.setLoading(false);
                            xt.Msg.show({
                                title: 'Invalid Passphrase',
                                msg: 'Please enter the passphrase that was used to encrypt the file.',
                                modal: true,
                                buttons: Ext.Msg.OK,
                                icon: Ext.Msg.ERROR,
                                width: 300
                            });
                            return false;
                        }
                        Cryptic.Application.file = decrypted;
                        me.deleteEncryptedFile(file.id);
                        me.insertDecryptFile(title, decrypted, function(file) {
                            console.log('insertDecryptFile');
                            Cryptic.Application.file = file;
                            console.log('file uploaded');
                            pa.setLoading(false);
                            pa.hide();
                            pp.show();
                            pp.expand();
                        });
                    } else {
                        Ext.Msg.show({
                            title: 'Input Error',
                            msg: 'Please enter the pass phrase that was used to encrypt the file.',
                            modal: true,
                            buttons: Ext.Msg.OK,
                            icon: Ext.Msg.ERROR,
                            width: 300
                        });
                    }
                });
                break;
            case 'local':
                if(form.isValid()){
                    form.submit({
                        url: 'file-decrypt.php',
                        params: { hiddenFilePath: Cryptic.Application.file.title },
                        success: function(form, data) {
                            form.emptyText = data.result.responseText;
                            Ext.Ajax.request({
                                url: data.result.responseText,
                                cors: true,
                                success: function(response,options) {
                                    Cryptic.Application.target = 'local';
                                    Cryptic.Application.file['webContentLink'] = response.responseText;
                                    pa.setLoading(false);
                                    pa.hide();
                                    pp.show();
                                    pp.expand();
                                },
                                failure: function(response,options) {
                                    Ext.Msg.alert('Error: ' + response.status || 'AJAX ERROR');
                                }
                            });
                        }
                    });
                }
                break;
        }
    }
});
