/**
 * Decryption pass phrase text field. This class is initialized
 * by the PassPhrases panel.
 */
Ext.define("Cryptic.view.center.passphrases.decrypt.PassPhrase", {
    extend: 'Ext.form.field.Text',
    alias: 'widget.decryptpassphrase',
    requires: [
        'Ext.form.field.Text'
    ],
    inputType: 'password',
    name: 'decrypt-key',
    id: 'decrypt-key',
    height: 70,
    width: 400,
    style: 'margin: 5px'
});