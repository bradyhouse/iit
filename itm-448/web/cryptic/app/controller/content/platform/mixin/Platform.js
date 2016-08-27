/**
 * Platform controller platform mixin. This class listens
 * for the platform view expand and collapse events.
 */
Ext.define('Cryptic.controller.content.platform.mixin.Platform', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'platform',
            selector: 'platform'
        }, {
            ref: 'breadcrumbs',
            selector: 'breadcrumbs'
        }, {
            ref: 'processes',
            selector: 'processes'
        }, {
            ref: 'cloudProvider',
            selector: 'cloudprovider'
        }, {
            ref: 'postProcess',
            selector: 'postprocess'
        }, {
            ref: 'passPhrases',
            selector: 'passphrases'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                platform: {
                    beforecollapse: me.onPlatformBeforeCollapse,
                    beforeexpand: me.onPlatformBeforeExpand,
                    show: me.onPlatformShow
                }
            }
        });
    },
    onPlatformShow: function (ctrl) {
        var me = this,
            p = me.getProcesses(),
            pr = me.getCloudProvider(),
            pp = me.getPostProcess(),
            pa = me.getPassPhrases(),
            br = me.getBreadcrumbs();
        if (!Cryptic.Application.target) {
            br.body.update('<div>Cryptic &#64; &#63;</div>');
        }
        p.hide();
        pr.hide();
        pp.hide();
        pa.hide();
    },
    onPlatformBeforeExpand: function (ctrl) {
        ctrl.setTitle('');
    },
    onPlatformBeforeCollapse: function (ctrl) {
        ctrl.setTitle('Where does the file reside?');
    }
});
