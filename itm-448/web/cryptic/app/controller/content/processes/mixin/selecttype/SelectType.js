/**
 * Processes select type controller. This class listens
 * for the select type panel expand and collapse events.
 */
Ext.define('Cryptic.controller.content.processes.mixin.selecttype.SelectType', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'processesSelecttype',
            selector: 'processes-selecttype'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'processes-selecttype': {
                    beforecollapse: me.onProcessesSelecttypeBeforeCollapse,
                    beforeexpand: me.onProcessesSelecttypeBeforeExpand
                }
            }
        });
    },
    onProcessesSelecttypeBeforeExpand: function (ctrl) {
        ctrl.setTitle('');
    },
    onProcessesSelecttypeBeforeCollapse: function (ctrl) {
        ctrl.setTitle('What do you want to do?');
    }
});
