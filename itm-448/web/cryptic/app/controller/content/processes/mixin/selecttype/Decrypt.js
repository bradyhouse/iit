/**
 * Select process type decrypt controller. This class listens
 * for the select process type decrypt click event.
 */
Ext.define('Cryptic.controller.content.processes.mixin.selecttype.Decrypt', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addListeners(controller);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'processes-selecttype-decrypt': {
                    click: me.onDecryptClick
                }
            }
        });
    },
    onDecryptClick: function (ctrl) {
        var me = this,
            sl = me.getProcessesSelecttype(),
            pd = me.getProcessDecryptPickFile(),
            pe = me.getProcessEncryptPickFile();
        Cryptic.Application.processType = 'decrypt';
        sl.hide();
        pe.hide();
        pd.show();
        pd.expand();
    }
});
