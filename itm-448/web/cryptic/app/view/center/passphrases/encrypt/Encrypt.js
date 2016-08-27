/**
 * Pass phrase encryption button. This class is initialized
 * by the encryption pass phrase panel.
 */
Ext.define("Cryptic.view.center.passphrases.encrypt.Encrypt", {
    extend: 'Ext.Button',
    alias: 'widget.start-encryption',
    requires: [
        'Ext.Button'
    ],
    text: 'ENCRYPT',
    height: 70,
    width: 240,
    style: 'margin: 5px'
});