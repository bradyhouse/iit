/**
 * Decryption GoogleDrive pick file container. This class is initialized
 * by the pick decryption file when the Application.target = "Google Drive".
 * It serves as the parent container for the decryption google drive
 * file selection components.
 */
Ext.define("Cryptic.view.center.processes.pick.decrypt.GoogleDrive", {
    extend: 'Ext.panel.Panel',
    alias: 'widget.process-decrypt-googledrive',
    requires: [
        'Ext.form.field.Text',
        'Ext.Button'
    ],
    items: [{
        xtype: 'panel',
        layout:  {
            type: 'hbox',
            align: 'center'
        },
        items: [{
            xtype: "textfield",
            itemId: 'googledrive-text',
            inputType: "text",
            width: 226,
            style: 'margin: 2px'
        }, {
            xtype: 'button',
            itemId: 'googledrive-browse',
            text: 'Browse...'
        }],
        border: false,
        style: 'margin: 5px'
    }],
    border: false
});