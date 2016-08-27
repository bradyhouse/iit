/**
 * Encryption process google drive file selection
 * controller. This class listens for events raised by
 * the encrypt pick file google drive container.
 */
Ext.define('Cryptic.controller.content.processes.mixin.pick.encrypt.GoogleDrive', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.scope = Cryptic.Application.scope;
        me.apiKey = Cryptic.Application.apiKey;
        me.clientId = Cryptic.Application.clientId;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'processEncryptContinue',
            selector: 'process-encrypt-continue'
        }, {
            ref: 'processEncryptGoogledrive',
            selector: 'process-encrypt-googledrive'
        }, {
            ref: 'processEncryptGoogledriveBrowse',
            selector: 'process-encrypt-googledrive button'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'process-encrypt-googledrive button': {
                    click: me.onEncryptBrowseClick
                }
            }
        });
    },
    onEncryptSelect: function(file) {
        var me = this,
            gd = me.getProcessEncryptGoogledrive(),
            t = gd && gd.items ? gd.query('#googledrive-text')[0] : null,
            btn = me.getProcessEncryptContinue();
        if(file && file.title && file.title.indexOf('.encrypted') < 0) {
            if (t) {
                t.setValue(file.title);
            }
            Cryptic.Application.file = file;
            btn.enable();
        } else {
            btn.disable();
            Ext.Msg.show({
                title: 'Invalid File',
                msg: 'Please select a file not ending with the *.encrypted extension.',
                modal: true,
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.ERROR,
                width: 300
            });
        }
    },
    onEncryptGoogleDriveClick: function (ctrl) {
        var me = this;
        // Load the drive API
        gapi.client.setApiKey(Cryptic.Application.apiKey);
        gapi.client.load('drive', 'v2', this.driveApiLoaded.bind(this));
        google.load('picker', '1', { callback: this.pickerApiLoaded.bind(this) });
        setTimeout(function () {
            me.open();
        }, 300);
    },
    apiKey: 'AIzaSyBx53vTrzLO0h-z1XbErTELfP3E1Smbmis',
    clientId: '204334724477-7vlcbede1t0k1a6fbfgqc2ofnkfs1l84',
    scope: 'https://www.googleapis.com/auth/drive',
    doEncryptOpen: function() {
        var me = this;
        // Check if the user has already authenticated
        var token = gapi.auth.getToken();
        if (token) {
            me.showEncryptPicker();
        } else {
            // The user has not yet authenticated with Google
            // We need to do the authentication before displaying the Drive picker.
            this.doEncryptAuth(false, (function() { me.showEncryptPicker(); }).bind(this));
        }
    },
    showEncryptPicker: function() {
        var me = this,
            clientId = this.clientId;
        me.picker = new google.picker.PickerBuilder().
            setAppId('204334724477').
            addView(google.picker.ViewId.DOCS).
            setOAuthToken(gapi.auth.getToken().access_token).
            setCallback(this.pickerEncryptCallback.bind(this)).
            build().
            setVisible(true);
    },
    driveEncryptApiLoaded: function() {
        console.log('driveApiLoaded');
    },
    pickerEncryptApiLoaded: function() {
        console.log('pickerApiLoaded');
    },
    pickerEncryptCallback: function(data) {
        if (data[google.picker.Response.ACTION] == google.picker.Action.PICKED) {
            var file = data[google.picker.Response.DOCUMENTS][0],
                id = file[google.picker.Document.ID],
                request = gapi.client.drive.files.get({
                    fileId: id
                });
            request.execute(this.fileEncryptGetCallback.bind(this));
        }
    },
    fileEncryptGetCallback: function(file) {
        if (this.onEncryptSelect) {
            this.onEncryptSelect(file);
        }
    },
    doEncryptAuth: function(immediate, callback) {
        gapi.auth.authorize({
            client_id: this.clientId + '.apps.googleusercontent.com',
            scope: this.scope,
            immediate: immediate
        }, callback);
    },
    onEncryptBrowseClick: function (ctrl) {
        var me = this,
            key = this.apiKey;
        // Load the drive API
        gapi.client.setApiKey(key);
        gapi.client.load('drive', 'v2', this.driveEncryptApiLoaded.bind(this));
        google.load('picker', '1', { callback: this.pickerEncryptApiLoaded.bind(this) });
        setTimeout(function () {
            me.doEncryptOpen();
        }, 300);
    }
});
