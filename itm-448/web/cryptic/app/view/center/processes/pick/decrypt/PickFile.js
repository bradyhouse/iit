/**
 * Decryption pick file panel. This class is initialized
 * by the processes panel. It serves as the parent
 * container for the decryption file selection components.
 */
Ext.define("Cryptic.view.center.processes.pick.decrypt.PickFile", {
    extend: 'Ext.form.Panel',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Ext.form.field.File',
        'Ext.Button',
        'Cryptic.view.center.processes.pick.decrypt.Local'
    ],
    alias: 'widget.process-decrypt-pickfile',
    title: '',
    id: 'sub4',
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
            text: 'Select the file to decrypt',
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
                xtype: 'process-decrypt-local'
            }, {
                xtype: 'process-decrypt-googledrive'
            }, {
                xtype: 'process-decrypt-continue'
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