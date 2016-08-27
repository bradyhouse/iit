/**
 * Encryption pass phrase controller. This class listens
 * for the pass phrases panel start encryption click event.
 */
Ext.define('Cryptic.controller.content.passphrases.mixin.encrypt.Encrypt', {
    extend: 'Ext.app.Controller',
    requiredScripts: [
        'https://www.google.com/jsapi?key=AIzaSyBx53vTrzLO0h-z1XbErTELfP3E1Smbmis',
        'https://apis.google.com/js/client.js?onload=onGoogleClientLoad',
        'https://www.googleapis.com/auth/drive'
    ],
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'startEncryption',
            selector: '#start-encryption'
        }, {
            ref: 'encryptPassphrase',
            selector: 'encryptpassphrase'
        }, {
            ref: 'postProcess',
            selector: 'postprocess'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'start-encryption': {
                    click: me.onStartEncryptionClick
                }
            }
        });
    },
    // @private
    downloadFile: function(file, callback) {
        var accessToken,
            xhr = new XMLHttpRequest();
        console.log('downloadFile');
        console.log('Cryptic.Application.target');
        console.log(Cryptic.Application.target);
        switch(Cryptic.Application.target) {
            case 'googledrive': {
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
                    console.log('else!');
                    callback(null);
                }
            }
            break;
            case 'local': {
                callback(file.blob);
            }
            break;
        }
    },
    // @private
    onStartEncryptionClick: function (ctrl) {
        var me = this,
            pa = me.getPassphrases(),
            psp = me.getEncryptPassphrase(),
            pp = me.getPostProcess(),
            file = Cryptic.Application.file;
            pa.setLoading('Encrypting ...', true);
        me.downloadFile(file, function(blob) {
            var me = this,
                password = psp.getValue(),
                encrypted = null,
                form = ctrl.up('form').getForm(),
                request = null;
            console.log('blob');
            console.log(blob);
            switch(Cryptic.Application.target) {
            case 'googledrive':
                // Use the CryptoJS library and the AES cypher to encrypt the
                // contents of the file, held in e.target.result, with the password
                encrypted = CryptoJS.AES.encrypt(blob, password);
                encrypted['name'] = file.title + ".pie";
                Cryptic.Application.file = encrypted;
                console.log('file.id');
                console.log(file.id);
                request = gapi.client.drive.files.delete({
                    'fileId': file.id
                });
                request.execute(function(resp) {
                    const boundary = '-------314159265358979323846';
                    const delimiter = "\r\n--" + boundary + "\r\n";
                    const close_delim = "\r\n--" + boundary + "--";
                    var contentType = encrypted.type || 'application/octet-stream',
                        metadata = {
                        'title': encrypted.name,
                        'mimeType': contentType
                        },
                        base64Data = btoa(encrypted),
                        multipartRequestBody =
                        delimiter +
                            'Content-Type: application/json\r\n\r\n' +
                            JSON.stringify(metadata) +
                            delimiter +
                            'Content-Type: ' + contentType + '\r\n' +
                            'Content-Transfer-Encoding: base64\r\n' +
                            '\r\n' +
                            base64Data +
                            close_delim,
                        request = gapi.client.request({
                            'path': '/upload/drive/v2/files',
                            'method': 'POST',
                            'params': {'uploadType': 'multipart'},
                            'headers': {
                                'Content-Type': 'multipart/mixed; boundary="' + boundary + '"'
                            },
                            'body': multipartRequestBody});
                    request.execute(function(response) {
                        Cryptic.Application.file['webContentLink'] = response.webContentLink;
                        pa.setLoading(false);
                        pa.hide();
                        pp.show();
                        pp.expand();
                    });
                });
            break;
            case 'local':
                if(form.isValid()){
                    form.submit({
                        url: 'file-encrypt.php',
                        params: { hiddenFilePath: Cryptic.Application.file.title },
                        success: function(form, data) {
                            form.emptyText = data.result.responseText;
                            Ext.Ajax.request({
                                url: data.result.responseText,
                                cors: true,
                                success: function(response,options) {
                                    var blob = response.responseText;
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
        });
    }
});
