/**
 * Master post process controller. This class is initialized
 * by the Application at startup. It used to marshal all controllers
 * classes attached to the post process panel.
 */
Ext.define('Cryptic.controller.content.postprocess.PostProcessController', {
    extend: 'Ext.app.Controller',
    mixins: {
        postprocess: 'Cryptic.controller.content.postprocess.mixin.PostProcess',
        download: 'Cryptic.controller.content.postprocess.mixin.NextFile',
        nextfile: 'Cryptic.controller.content.postprocess.mixin.Download'
    },
    init: function (application) {
        application.initMixins(this);
    }
});
