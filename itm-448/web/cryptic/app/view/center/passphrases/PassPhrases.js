/**
 * Pass phrases panel. This class is initialized
 * by the Content panel. It serves as the
 * container for the encryption and decryption pass
 * phrase panels.
 */
Ext.define("Cryptic.view.center.passphrases.PassPhrases", {
    extend: 'Ext.form.Panel',
    alias: 'widget.passphrases',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Cryptic.view.center.passphrases.PassPhrases',
        'Cryptic.view.center.passphrases.encrypt.EncryptPassPhrase',
        'Cryptic.view.center.passphrases.decrypt.DecryptPassPhrase'
    ],
    title: 'PassPhrase',
    id: 'p8',
    isp: true,
    hasSubPanels: true,
    hideCollapseTool: true,
    bodyStyle:{"background-color": "#7EEF3C"},
    collapseFirst: false,
    layout: {
        type: 'accordion',
        titleCollapse: true,
        animate: true,
        activeOnTop: true
    },
    items: [{
        xtype: 'encrypt-passphrase-panel'
    }, {
        xtype: 'decrypt-passphrase-panel'
    }]
});