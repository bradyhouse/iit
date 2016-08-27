/**
 * Encryption process continue controller. This class listens
 * for the decrypt Pick Local panel's continue click event.
 */
Ext.define('Cryptic.controller.content.processes.mixin.pick.encrypt.Continue', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'processEncryptContinue',
            selector: 'process-encrypt-continue'
        }, {
            ref: 'passphrases',
            selector: 'passphrases'
        }, {
            ref: 'encryptPassphrasePanel',
            selector: 'encrypt-passphrase-panel'
        }, {
            ref: 'decryptPassphrasePanel',
            selector: 'decrypt-passphrase-panel'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'process-encrypt-continue': {
                    click: me.onProcessEncryptContinueClick
                }
            }
        });
    },
    onProcessEncryptContinueClick: function (ctrl) {
        var me = this,
            pr = me.getProcesses(),
            pp = me.getPassphrases(),
            ppe = me.getEncryptPassphrasePanel(),
            ppd = me.getDecryptPassphrasePanel();
        pr.hide();
        pp.show();
        pp.expand();
        ppd.hide();
        ppe.show();
        ppe.expand();
    }
});
