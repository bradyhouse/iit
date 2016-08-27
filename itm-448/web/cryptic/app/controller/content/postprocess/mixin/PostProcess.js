/**
 * Post process controller. This class listens
 * for the post process panel expand and collapse events.
 */
Ext.define('Cryptic.controller.content.postprocess.mixin.PostProcess', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'postProcess',
            selector: 'postprocess'
        }, {
            ref: 'postProcessSubtitle',
            selector: 'postprocess-subtitle'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                postprocess: {
                    beforecollapse: me.onPostProcessBeforeCollapse,
                    beforeexpand: me.onPostProcessBeforeExpand
                }
            }
        });
    },
    onPostProcessBeforeExpand: function (ctrl) {
        var me = this;
            subTitle = me.getPostProcessSubtitle();
        console.log('subTitle');
        console.log(subTitle);
        ctrl.setTitle('');
        switch(Cryptic.Application.target) {
        case 'googledrive': {
            subTitle.setText('An updated version of the file has been uploaded to your google drive.');
        }
        break;
        case 'local': {
            switch(Cryptic.Application.processType) {
            case 'encrypt':
                subTitle.setText('It has been encrypted.');
                break;
            case 'decrypt':
                subTitle.setText('It has been decrypted.');
                break;
            }
        }
        break;
        }
    },
    onPostProcessBeforeCollapse: function (ctrl) {
        ctrl.setTitle('Download');
    }
});
