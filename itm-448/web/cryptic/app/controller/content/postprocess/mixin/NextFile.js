/**
 * Post process next file controller. This class listens
 * for the post process panel next file click event.
 */
Ext.define('Cryptic.controller.content.postprocess.mixin.NextFile', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'nextFile',
            selector: 'nextfile'
        }, {
            ref: 'platform',
            selector: 'platform'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                nextfile: {
                    click: me.onNextFileClick
                }
            }
        });
    },
    onNextFileClick: function (ctrl) {
        document.location = 'index.html';
       /* var me = this,
            pp = me.getPostProcess(),
            pl = me.getPlatform();
        pp.hide();
        pl.show();
        pl.expand(); */
    }
});
