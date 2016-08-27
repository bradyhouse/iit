/**
 * Decrypt pick local controller. This class listens
 * for the decrypt pick local panel expand and collapse events.
 */
Ext.define('Cryptic.controller.content.processes.mixin.pick.decrypt.PickFile', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'processDecryptPickFile',
            selector: 'process-decrypt-pickfile'
        }, {
            ref: 'processDecryptGoogledrive',
            selector: 'process-decrypt-googledrive'
        }, {
            ref: 'processDecryptLocal',
            selector: 'process-decrypt-local'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'process-decrypt-pickfile': {
                    beforecollapse: me.onProcessDecryptPickFileBeforeCollapse,
                    beforeexpand: me.onProcessDecryptPickFileBeforeExpand,
                    show: me.onProcessDecryptPickFileShow
                }
            }
        });
    },
    onProcessDecryptPickFileShow: function (ctrl) {
        var me = this,
            t = Cryptic.Application.target,
            gd = me.getProcessDecryptGoogledrive(),
            l = me.getProcessDecryptLocal();
        if (t === 'googledrive') {
            l.hide();
            gd.show();
        } else {
            gd.hide();
            l.show();
        }
    },
    onProcessDecryptPickFileBeforeExpand: function (ctrl) {
        ctrl.setTitle('');
    },
    onProcessDecryptPickFileBeforeCollapse: function (ctrl) {
        ctrl.setTitle('Select the file to be decrypted');
    }
});
