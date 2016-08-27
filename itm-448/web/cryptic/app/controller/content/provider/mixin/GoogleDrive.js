/**
 * Provider google drive controller. This class listens for the
 * provider view google drive click event.
 */
Ext.define('Cryptic.controller.content.provider.mixin.GoogleDrive', {
    extend: 'Ext.app.Controller',
    requiredScripts: [
        'https://www.google.com/jsapi?key=AIzaSyBx53vTrzLO0h-z1XbErTELfP3E1Smbmis',
        'https://apis.google.com/js/client.js?onload=onGoogleClientLoad'
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
            ref: 'googleDrive',
            selector: 'googledrive'
        }, {
            ref: 'breadcrumbs',
            selector: 'breadcrumbs'
        }, {
            ref: 'processes',
            selector: 'processes'
        }, {
            ref: 'processesSelecttype',
            selector: 'processes-selecttype'
        }, {
            ref: 'processEncryptPickFile',
            selector: 'process-encrypt-pickfile'
        }, {
            ref: 'processDecryptPickFile',
            selector: 'process-decrypt-pickfile'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                googledrive: {
                    click: me.onGoogleDriveClick
                }
            }
        });
    },
    apiKey: 'AIzaSyBx53vTrzLO0h-z1XbErTELfP3E1Smbmis',
    clientId: '204334724477-7vlcbede1t0k1a6fbfgqc2ofnkfs1l84',
    scope: 'https://www.googleapis.com/auth/drive',
    onSelect: function(file) {
        console.log(file);
        alert('Selected ' + file.title);
    },
    onGoogleDriveClick: function (ctrl) {
        var me = this;
        // Load the drive API
        gapi.client.setApiKey(this.apiKey);
        gapi.client.load('drive', 'v2', this.driveApiLoaded.bind(this));
        google.load('picker', '1', { callback: this.pickerApiLoaded.bind(this) });
        setTimeout(function () {
            me.open();
        }, 300);
    },
    open: function() {
        // Check if the user has already authenticated
        var token = gapi.auth.getToken();
        if (token) {
            this.googleDriveContinue();
        } else {
            // The user has not yet authenticated with Google
            // We need to do the authentication before displaying the Drive picker.
            this.doAuth(false, (function() { this.googleDriveContinue(); }).bind(this));
        }
    },
    googleDriveContinue: function() {
        var me = this,
            prov = me.getCloudProvider(),
            proc = me.getProcesses(),
            sl = me.getProcessesSelecttype(),
            pd = me.getProcessDecryptPickFile(),
            pe = me.getProcessEncryptPickFile(),
            br = me.getBreadcrumbs();
        br.body.update('<div>Cryptic &#64; Google Drive</div>');
        // Update the application target attribute
        // this attribute controls whether the processes
        // pick file panel will target the user's local
        // hard drive, or their google drive cloud for
        // the source file
        Cryptic.Application.target = 'googledrive';
        prov.hide();
        proc.show();
        proc.expand();
        pd.hide();
        pe.hide();
        sl.show();
        sl.expand();
    },
    driveApiLoaded: function() {
        console.log('driveApiLoaded');

    },
    pickerApiLoaded: function() {
        console.log('pickerApiLoaded');
    },
    pickerCallback: function(data) {
        if (data[google.picker.Response.ACTION] == google.picker.Action.PICKED) {
            var file = data[google.picker.Response.DOCUMENTS][0],
                id = file[google.picker.Document.ID],
                args = {
                    'fileId': fileId
                },
                request = gapi.client.drive.files.get(args);
            request.execute(this.fileGetCallback.bind(this));
        }
    },
    fileGetCallback: function(file) {
        if (this.onSelect) {
            this.onSelect(file);
        }
    },
    doAuth: function(immediate, callback) {
        gapi.auth.authorize({
            client_id: this.clientId + '.apps.googleusercontent.com',
            scope: this.scope,
            immediate: immediate
        }, callback);
    }
});
