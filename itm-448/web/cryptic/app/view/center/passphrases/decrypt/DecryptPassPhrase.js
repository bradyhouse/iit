/**
 * Decryption pass phrase panel. This class is initialized
 * by the pass phrases panel. It serves as the parent
 * container for the decryption pass phrase components.
 */
Ext.define("Cryptic.view.center.passphrases.decrypt.DecryptPassPhrase", {
    extend: 'Ext.form.Panel',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Ext.Button',
        'Cryptic.view.center.passphrases.decrypt.Decrypt',
        'Cryptic.view.center.passphrases.decrypt.PassPhrase'
    ],
    alias: 'widget.decrypt-passphrase-panel',
    title: 'Decryption pass phrase',
    id: 'sub2',
    isSubPanel: true,
    layout:  'fit',
    items: [{
        xtype: 'panel',
        layout:  {
            type: 'vbox',
            align: 'center'
        },
        items: [{
            xtype: 'label',
            text: 'Enter the decryption pass phrase',
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
                type: 'hbox',
                align: 'center'
            },
            items: [{
                xtype: 'decryptpassphrase'
            }, {
                xtype: 'start-decryption'
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