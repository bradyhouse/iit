/**
 * Encryption pass phrase text field. This class is initialized
 * by the PassPhrases panel.
 */
Ext.define("Cryptic.view.center.passphrases.encrypt.PassPhrase", {
    extend: 'Ext.form.field.Text',
    alias: 'widget.encryptpassphrase',
    requires: [
        'Ext.form.field.Text'
    ],
    inputType: 'password',
    name: 'encrypt-key',
    id: 'encrypt-key',
    height: 70,
    width: 400,
    style: 'margin: 5px'
});