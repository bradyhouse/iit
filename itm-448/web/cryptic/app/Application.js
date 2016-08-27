/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application().
 *
 * jslint browser: true, vars: true, plusplus: true, devel: true, nomen: true, indent: 4,
 * maxerr: 50, todo: true, sloppy: true
 * global: Ext, window, House
 */
Ext.define('Cryptic.Application', {
    extend: 'Ext.app.Application',
    name: 'Cryptic',
    requires: [
        'Cryptic.view.Viewport'
    ],
    controllers: [
        'Root',
        'content.passphrases.PassPhrasesController',
        'content.platform.PlatformController',
        'content.processes.ProcessesController',
        'content.provider.ProviderController',
        'content.postprocess.PostProcessController',
        'footer.FooterController',
        'header.BreadCrumbsController',
        'sidebar.SidebarController'
    ],
    appProperty: 'Current',
    defaultToken : '#mp:p1',
    apiKey: 'AIzaSyBx53vTrzLO0h-z1XbErTELfP3E1Smbmis',
    clientId: '204334724477-7vlcbede1t0k1a6fbfgqc2ofnkfs1l84',
    scope: 'https://www.googleapis.com/auth/drive.file',
    file: {},
    target: null,
    initMixins: function (controller) {
        Ext.Object.each(controller.mixins, function (key, mixin) {
            if (mixin.init) {
                mixin.init(controller);
            }
        });
    },
    launch: function () {
    }
});
