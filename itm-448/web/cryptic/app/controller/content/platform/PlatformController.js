/**
 * Master platform controller class. This class is initialized
 * by the Application class. It used to marshal all controllers
 * classes attached to the platform view.
 */
Ext.define('Cryptic.controller.content.platform.PlatformController', {
    extend: 'Ext.app.Controller',
    mixins: {
        platform: 'Cryptic.controller.content.platform.mixin.Platform',
        cloud: 'Cryptic.controller.content.platform.mixin.Cloud',
        local: 'Cryptic.controller.content.platform.mixin.Local'
    },
    init: function (application) {
         application.initMixins(this);
    }
});
