/**
 * Processes controller. This class listens
 * for the processes view expand and collapse events.
 */
Ext.define('Cryptic.controller.content.processes.mixin.Processes', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'processes',
            selector: 'processes'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                processes: {
                    render: me.onProcessesRender,
                    beforecollapse: me.onProcessesBeforeCollapse,
                    beforeexpand: me.onProcessesBeforeExpand
                }
            }
        });
    },
    onProcessesRender: function (ctrl) {
        var me = this,
            sl = me.getProcessesSelecttype(),
            pd = me.getProcessDecryptPickFile(),
            pe = me.getProcessEncryptPickFile();
        pd.hide();
        pe.hide();
        sl.show();
        sl.expand();
    },
    onProcessesBeforeExpand: function (ctrl) {
        ctrl.setTitle('');
    },
    onProcessesBeforeCollapse: function (ctrl) {
        ctrl.setTitle('Processes');
    }
});
