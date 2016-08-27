/**
 * Encrypt pass phrase controller. This class listens
 * for the encrypt pass phrase panel expand and collapse events.
 */
Ext.define('Cryptic.controller.content.passphrases.mixin.encrypt.EncryptPassPhrase', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'encryptPassphrasePanel',
            selector: '#encrypt-passphrase-panel'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'encrypt-passphrase-panel': {
                    beforecollapse: me.onEncryptPassPhraseBeforeCollapse,
                    beforeexpand: me.onEncryptPassPhraseBeforeExpand
                }
            }
        });
    },
    onEncryptPassPhraseBeforeExpand: function (ctrl) {
        ctrl.setTitle('');
    },
    onEncryptPassPhraseBeforeCollapse: function (ctrl) {
        ctrl.setTitle('Encryption Pass Phrase');
    }
});
