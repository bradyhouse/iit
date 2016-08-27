/**
 * Encrypt pick local controller. This class listens
 * for the encrypt pick local panel expand and collapse events.
 */
Ext.define('Cryptic.controller.content.processes.mixin.pick.encrypt.PickFile', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'processEncryptPickFile',
            selector: 'process-encrypt-pickfile'
        }, {
            ref: 'processEncryptGoogledrive',
            selector: 'process-encrypt-googledrive'
        }, {
            ref: 'processEncryptLocal',
            selector: 'process-encrypt-local'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'process-encrypt-pickfile': {
                    beforecollapse: me.onProcessEncryptPickFileBeforeCollapse,
                    beforeexpand: me.onProcessEncryptPickFileBeforeExpand,
                    show: me.onProcessEncryptPickFileShow
                }
            }
        });
    },
    onProcessEncryptPickFileShow: function (ctrl) {
        var me = this,
            t = Cryptic.Application.target,
            gd = me.getProcessEncryptGoogledrive(),
            l = me.getProcessEncryptLocal();
        if (t === 'googledrive') {
            l.hide();
            gd.show();
        } else {
            gd.hide();
            l.show();
        }
    },
    onProcessEncryptPickFileBeforeExpand: function (ctrl) {
        ctrl.setTitle('');
    },
    onProcessEncryptPickFileBeforeCollapse: function (ctrl) {
        ctrl.setTitle('Select the file to be encrypted');
    }
});
