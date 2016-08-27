/**
 * Encryption GoogleDrive pick file container. This class is initialized
 * by the pick encryption file when the Application.target = "Google Drive".
 * It serves as the parent container for the encryption google drive
 * file selection components.
 */
Ext.define("Cryptic.view.center.processes.pick.encrypt.GoogleDrive", {
    extend: 'Ext.panel.Panel',
    alias: 'widget.process-encrypt-googledrive',
    requires: [
        'Ext.form.field.Text',
        'Ext.Button'
    ],
    items: [{
        xtype: 'panel',
        name: 'googledrive-panel',
        itemid: 'googledrive-panel',
        layout:  {
            type: 'hbox',
            align: 'center'
        },
        items: [{
            xtype: "textfield",
            name: 'googledrive-text',
            itemId: 'googledrive-text',
            inputType: "text",
            width: 226,
            style: 'margin: 2px'
        }, {
            xtype: 'button',
            name: 'googledrive-browse',
            itemId: 'googledrive-browse',
            text: 'Browse...'
        }],
        border: false,
        style: 'margin: 5px'
    }],
    border: false
});