/**
 * Encryption file selection form panel. This class is initialized
 * by the processes panel. It serves as the parent
 * container for the decryption file selection components.
 */
Ext.define("Cryptic.view.center.processes.pick.encrypt.PickFile", {
    extend: 'Ext.form.Panel',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Ext.form.field.File',
        'Ext.Button',
        'Cryptic.view.center.processes.pick.encrypt.Local'
    ],
    alias: 'widget.process-encrypt-pickfile',
    title: '',
    id: 'sub3',
    isSubPanel: true,
    hideCollapseTool: true,
    layout:  'fit',
    items: [{
        xtype: 'panel',
        layout:  {
            type: 'vbox',
            align: 'center'
        },
        items: [{
            xtype: 'label',
            text: 'Select the file to encrypt',
            style: {
                fontWeight: 'normal',
                fontSize: '48px',
                textAlign: 'center',
                vAlign: 'center',
                color: '#fff',
                paddingTop: '40px',
                marginBottom: '60px'
            }
        }, {
            xtype: 'panel',
            layout: {
                type: 'vbox',
                align: 'center'
            },
            items: [{
                xtype: 'process-encrypt-local'
            }, {
                xtype: 'process-encrypt-googledrive'
            }, {
                xtype: 'process-encrypt-continue'
            }],
            border: false,
            collapsible: false,
            split: false
        }],
        border: false,
        collapsible: false,
        split: false
    }],
    border: false,
    collapsible: true,
    split: false,
    initComponent: function () {
        this.callParent();
    }
});