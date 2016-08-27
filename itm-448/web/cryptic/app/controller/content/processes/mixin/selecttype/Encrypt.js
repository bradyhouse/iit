/**
 * Select process type encrypt controller. This class listens
 * for the select process type encrypt click event.
 */
Ext.define('Cryptic.controller.content.processes.mixin.selecttype.Encrypt', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addListeners(controller);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'processes-selecttype-encrypt': {
                    click: me.onEncryptClick
                }
            }
        });
    },
    onEncryptClick: function (ctrl) {
        var me = this,
            sl = me.getProcessesSelecttype(),
            pd = me.getProcessDecryptPickFile(),
            pe = me.getProcessEncryptPickFile();
        Cryptic.Application.processType = 'encrypt';
        sl.hide();
        pd.hide();
        pe.show();
        pe.expand();
    }
});
