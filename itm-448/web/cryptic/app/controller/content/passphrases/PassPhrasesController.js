/**
 * Master pass phrase controller. This class is initialized
 * by the Application class. It used to marshal all controllers
 * attached to the pass phrases panel.
 */
Ext.define('Cryptic.controller.content.passphrases.PassPhrasesController', {
    extend: 'Ext.app.Controller',
    mixins: {
        'passphrases': 'Cryptic.controller.content.passphrases.mixin.PassPhrases',
        'encrypt-passphrase-panel': 'Cryptic.controller.content.passphrases.mixin.encrypt.EncryptPassPhrase',
        'decrypt-passphrase-panel': 'Cryptic.controller.content.passphrases.mixin.decrypt.DecryptPassPhrase',
        'start-encryption': 'Cryptic.controller.content.passphrases.mixin.encrypt.Encrypt',
        'start-decryption': 'Cryptic.controller.content.passphrases.mixin.decrypt.Decrypt'
    },
    init: function (application) {
        application.initMixins(this);
    }
});
