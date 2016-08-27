/**
 * Pass phrase decryption button. This class is initialized
 * by the pass phrase panel.
 */
Ext.define("Cryptic.view.center.passphrases.decrypt.Decrypt", {
    extend: 'Ext.Button',
    alias: 'widget.start-decryption',
    requires: [
        'Ext.Button'
    ],
    text: 'DECRYPT',
    height: 70,
    width: 240,
    style: 'margin: 5px'
});