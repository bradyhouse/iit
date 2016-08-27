/**
 * Platform cloud controller class. This class listens
 * for the platform view cloud click event.
 */
Ext.define('Cryptic.controller.content.platform.mixin.Cloud', {
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
        }, {
            ref: 'platformCloud',
            selector: '#platform-cloud'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'platform-cloud': {
                    click: me.onCloudClick
                }
            }
        });
    },
    onCloudClick: function (ctrl) {
        var me = this,
            prov = me.getCloudProvider(),
            plat = me.getPlatform();
        plat.hide();
        prov.show();
        prov.expand();
    }
});
