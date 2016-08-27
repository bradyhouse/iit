/**
 * Master provider controller. This class is initialized
 * by the Application class. It used to marshal all controllers
 * classes attached to the provider view.
 */
Ext.define('Cryptic.controller.content.provider.ProviderController', {
    extend: 'Ext.app.Controller',
    mixins: {
        cloudprovider: 'Cryptic.controller.content.provider.mixin.Provider',
        googledrive: 'Cryptic.controller.content.provider.mixin.GoogleDrive',
        other: 'Cryptic.controller.content.provider.mixin.Other'
    },
    init: function (application) {
        application.initMixins(this);
    }
});
