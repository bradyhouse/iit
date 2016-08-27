/**
 * Process decrypt file button.
 * This class is initialized
 * by the process decrypt PickFile panel.
 */
Ext.define("Cryptic.view.center.processes.pick.decrypt.Local", {
    extend: 'Ext.form.field.File',
    alias: 'widget.process-decrypt-local',
    requires: [
        'Ext.form.field.File'
    ],
    width: 300,
    name: 'local-file-path',
    style: 'margin: 5px',
    clearOnSubmit: false
});