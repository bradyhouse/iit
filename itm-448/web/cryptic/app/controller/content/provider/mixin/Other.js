/**
 * Provider other controller. This class listens for the
 * provider view other click event.
 */
Ext.define('Cryptic.controller.content.provider.mixin.Other', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'other',
            selector: 'other'
        }, {
            ref: 'sidebar',
            selector: 'sidebar'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                other: {
                    click: me.onOtherClick
                }
            }
        });
    },
    onOtherClick: function (ctrl) {
        var me = this,
            s = me.getSidebar();
        s.expand();
    }
});