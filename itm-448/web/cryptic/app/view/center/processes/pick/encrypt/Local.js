Ext.define("Cryptic.view.center.processes.pick.encrypt.Local", {
    extend: 'Ext.form.File',
    alias: 'widget.process-encrypt-local',
    name: 'local-file-path',
    requires: [
        'Ext.form.field.File'
    ],
    width: 300,
    style: 'margin: 5px',
    clearOnSubmit: false
});