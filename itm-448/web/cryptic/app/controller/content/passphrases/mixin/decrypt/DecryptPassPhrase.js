/**
 * Decrypt pass phrase controller. This class listens
 * for the decrypt pass phrase panel expand and collapse events.
 */
Ext.define('Cryptic.controller.content.passphrases.mixin.decrypt.DecryptPassPhrase', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'decryptPassphrasePanel',
            selector: '#decrypt-passphrase-panel'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'decrypt-passphrase-panel': {
                    beforecollapse: me.onDecryptPassPhraseBeforeCollapse,
                    beforeexpand: me.onDecryptPassPhraseBeforeExpand
                }
            }
        });
    },
    onDecryptPassPhraseBeforeExpand: function (ctrl) {
        ctrl.setTitle('');
    },
    onDecryptPassPhraseBeforeCollapse: function (ctrl) {
        ctrl.setTitle('Decryption Pass Phrase');
    }
});
