/**
 * Decryption process continue controller. This class listens
 * for the encrypt Pick Local panel's continue click event.
 */
Ext.define('Cryptic.controller.content.processes.mixin.pick.decrypt.Continue', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'processDecryptContinue',
            selector: 'process-decrypt-continue'
        }, {
            ref: 'passphrases',
            selector: 'passphrases'
        }, {
            ref: 'decryptPassphrasePanel',
            selector: 'decrypt-passphrase-panel'
        }, {
            ref: 'encryptPassphrasePanel',
            selector: 'encrypt-passphrase-panel'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'process-decrypt-continue': {
                    click: me.onProcessDecryptContinueClick
                }
            }
        });
    },
    onProcessDecryptContinueClick: function (ctrl) {
        var me = this,
            pr = me.getProcesses(),
            pp = me.getPassphrases(),
            ppd = me.getDecryptPassphrasePanel(),
            ppe = me.getEncryptPassphrasePanel();
        pr.hide();
        pp.show();
        pp.expand();
        ppe.hide();
        ppd.show();
        ppd.expand();
    }
});
