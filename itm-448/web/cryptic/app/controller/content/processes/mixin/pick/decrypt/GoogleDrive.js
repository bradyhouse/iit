/**
 * Decryption process google drive file selection
 * controller. This class listens for events raised by
 * the decrypt pick file google drive container.
 */
Ext.define('Cryptic.controller.content.processes.mixin.pick.decrypt.GoogleDrive', {
    extend: 'Ext.app.Controller',
    requiredScripts: [
        'https://www.google.com/jsapi?key=AIzaSyBx53vTrzLO0h-z1XbErTELfP3E1Smbmis',
        'https://apis.google.com/js/client.js?onload=onGoogleClientLoad',
        'https://www.googleapis.com/auth/drive'
    ],
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
            ref: 'processDecryptContinue',
            selector: 'process-decrypt-continue'
        }, {
            ref: 'processDecryptGoogledrive',
            selector: 'process-decrypt-googledrive'
        }, {
            ref: 'processDecryptGoogledriveBrowse',
            selector: 'process-decrypt-googledrive button'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'process-decrypt-googledrive button': {
                    click: me.decryptOnBrowseClick
                }
            }
        });
    },
    apiKey: 'AIzaSyBx53vTrzLO0h-z1XbErTELfP3E1Smbmis',
    clientId: '204334724477-7vlcbede1t0k1a6fbfgqc2ofnkfs1l84',
    scope: 'https://www.googleapis.com/auth/drive',
    onSelect: function(file) {
        var me = this,
            gd = me.getProcessDecryptGoogledrive(),
            btn = me.getProcessDecryptContinue(),
            t = gd && gd.items ? gd.query('#googledrive-text')[0] : null;
        if(file && file.title && file.title.indexOf('.pie') > -1) {
            if (t) {
                t.setValue(file.title);
            }
            Cryptic.Application.file = file;
            btn.enable();
        } else {
            btn.disable();
            Ext.Msg.show({
                title: 'Invalid File',
                msg: 'Please select a file ending with the *.pie extension.',
                modal: true,
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.ERROR,
                width: 300
            });
        }
    },
    onGoogleDriveClick: function (ctrl) {
        var me = this;
        // Load the drive API
        gapi.client.setApiKey(me.apiKey);
        gapi.client.load('drive', 'v2', this.decryptDriveApiLoaded.bind(this));
        google.load('picker', '1', { callback: this.decryptPickerApiLoaded.bind(this) });
        setTimeout(function () {
            me.decryptOpen();
        }, 300);
    },
    decryptOpen: function() {
        var me = this;
        // Check if the user has already authenticated
        var token = gapi.auth.getToken();
        if (token) {
            me.decryptShowPicker();
        } else {
            // The user has not yet authenticated with Google
            // We need to do the authentication before displaying the Drive picker.
            this.decryptDoAuth(false, (function() { me.decryptShowPicker(); }).bind(this));
        }
    },
    decryptShowPicker: function() {
        var me = this,
            clientId = this.clientId;
        var accessToken = gapi.auth.getToken().access_token;
        this.picker = new google.picker.PickerBuilder().
            addView(google.picker.ViewId.DOCS).
            setAppId(clientId).
            setOAuthToken(accessToken).
            setCallback(this.decryptPickerCallback.bind(this)).
            build().
            setVisible(true);
    },
    decryptDriveApiLoaded: function() {
        console.log('decryptDriveApiLoaded');
    },
    decryptPickerApiLoaded: function() {
        console.log('decryptPickerApiLoaded');
    },
    decryptPickerCallback: function(data) {
        if (data[google.picker.Response.ACTION] == google.picker.Action.PICKED) {
            var file = data[google.picker.Response.DOCUMENTS][0],
                id = file[google.picker.Document.ID],
                args = {
                    'fileId': id
                },
                request = gapi.client.drive.files.get(args);
            request.execute(this.decryptFileGetCallback.bind(this));
        }
    },
    decryptFileGetCallback: function(file) {
        if (this.onSelect) {
            this.onSelect(file);
        }
    },
    decryptDoAuth: function(immediate, callback) {
        gapi.auth.authorize({
            client_id: this.clientId + '.apps.googleusercontent.com',
            scope: this.scope,
            immediate: immediate
        }, callback);
    },
    decryptOnBrowseClick: function (ctrl) {
        var me = this,
            key = this.apiKey;
        // Load the drive API
        gapi.client.setApiKey(key);
        gapi.client.load('drive', 'v2', this.decryptDriveApiLoaded.bind(this));
        google.load('picker', '1', { callback: this.decryptPickerApiLoaded.bind(this) });
        setTimeout(function () {
            me.decryptOpen();
        }, 300);
    }
});
