/**
 * Provider controller. This class listens
 * for the provider view expand and collapse events.
 */
Ext.define('Cryptic.controller.content.provider.mixin.Provider', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'cloudProvider',
            selector: 'cloudprovider'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                cloudprovider: {
                    beforecollapse: me.onProviderBeforeCollapse,
                    beforeexpand: me.onProviderBeforeExpand
                }
            }
        });
    },
    onProviderBeforeExpand: function (ctrl) {
        ctrl.setTitle('');
    },
    onProviderBeforeCollapse: function (ctrl) {
        ctrl.setTitle('Which Cloud?');
    }
});
