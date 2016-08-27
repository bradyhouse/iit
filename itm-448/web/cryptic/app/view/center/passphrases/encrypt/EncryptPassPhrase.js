/**
 * Encryption pass phrase panel. This class is initialized
 * by the pass phrases panel. It serves as the parent
 * container for the encryption pass phrase components.
 */
Ext.define("Cryptic.view.center.passphrases.encrypt.EncryptPassPhrase", {
    extend: 'Ext.form.Panel',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Ext.Button',
        'Cryptic.view.center.passphrases.encrypt.Encrypt',
        'Cryptic.view.center.passphrases.encrypt.PassPhrase'
    ],
    alias: 'widget.encrypt-passphrase-panel',
    title: '',
    id: 'sub1',
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
            text: 'Enter the encryption pass phrase',
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
                xtype: 'encryptpassphrase'
            }, {
                xtype: 'start-encryption'
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