(function() {
    var FilePicker = window.FilePicker = function(options) {
        console.log('FilePicker');
        // Config
        this.apiKey = options.apiKey;
        this.clientId = options.clientId;

        // Elements
        this.buttonEl = options.buttonEl;

        // Events
        this.onSelect = options.onSelect;
        this.buttonEl.addEventListener('click', this.open.bind(this));

        // Disable the button until the API loads, as it won't work properly until then.
        this.buttonEl.disabled = true;

        // Load the drive API
        gapi.client.setApiKey(this.apiKey);
        gapi.client.load('drive', 'v2', this._driveApiLoaded.bind(this));
        google.load('picker', '1', { callback: this._pickerApiLoaded.bind(this) });
    }
    FilePicker.prototype = {
        open: function() {
            console.log('open');
            // Check if the user has already authenticated
            var token = gapi.auth.getToken();
            if (token) {
                this._showPicker();
            } else {
                // The user has not yet authenticated with Google
                // We need to do the authentication before displaying the Drive picker.
                this._doAuth(false, function() { this._showPicker(); }.bind(this));
            }
        },
        _showPicker: function() {
            console.log('_showPicker');
            var accessToken = gapi.auth.getToken().access_token;
            this.picker = new google.picker.PickerBuilder().
                addView(google.picker.ViewId.DOCUMENTS).
                setAppId(this.clientId).
                setOAuthToken(accessToken).
                setCallback(this._pickerCallback.bind(this)).
                build().
                setVisible(true);
        },
        _pickerCallback: function(data) {
            console.log('pickerCallback');
            if (data[google.picker.Response.ACTION] == google.picker.Action.PICKED) {
                var file = data[google.picker.Response.DOCUMENTS][0],
                    id = file[google.picker.Document.ID],
                    request = gapi.client.drive.files.get({
                        fileId: id
                    });

                request.execute(this._fileGetCallback.bind(this));
            }
        },
        _fileGetCallback: function(file) {
            console.log('fileGetCallback');
            if (this.onSelect) {
                this.onSelect(file);
            }
        },
        _pickerApiLoaded: function() {
            console.log('pickerApiLoaded');
            this.buttonEl.disabled = false;
        },
        _driveApiLoaded: function() {
            console.log('driveApiLoaded');
            this._doAuth(true, function() {
                    this.open.bind(this);
            });
        },
        _doAuth: function(immediate, callback) {
            console.log('doAuth');
            console.log('this.clientId = ' + this.clientId);
            console.log('gapi');
            console.log(gapi);
            gapi.auth.authorize({
                client_id: this.clientId + '.apps.googleusercontent.com',
                scope: 'https://www.googleapis.com/auth/drive.readonly',
                immediate: immediate
            }, callback);
        }
    };
}());