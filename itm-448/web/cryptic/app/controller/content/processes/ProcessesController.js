/**
 * Master Processes controller. This class is initialized
 * by the Application class. It used to marshal all controllers
 * classes attached to the processes view.
 */
Ext.define('Cryptic.controller.content.processes.ProcessesController', {
    extend: 'Ext.app.Controller',
    mixins: {
        'processes': 'Cryptic.controller.content.processes.mixin.Processes',
        'processes-selecttype': 'Cryptic.controller.content.processes.mixin.selecttype.SelectType',
        'processes-selecttype-decrypt': 'Cryptic.controller.content.processes.mixin.selecttype.Decrypt',
        'processes-selecttype-encrypt': 'Cryptic.controller.content.processes.mixin.selecttype.Encrypt',
        'process-encrypt-pickfile': 'Cryptic.controller.content.processes.mixin.pick.encrypt.PickFile',
        'process-encrypt-continue': 'Cryptic.controller.content.processes.mixin.pick.encrypt.Continue',
        'process-encrypt-local': 'Cryptic.controller.content.processes.mixin.pick.encrypt.Local',
        'process-decrypt-pickfile': 'Cryptic.controller.content.processes.mixin.pick.decrypt.PickFile',
        'process-decrypt-continue': 'Cryptic.controller.content.processes.mixin.pick.decrypt.Continue',
        'process-decrypt-local': 'Cryptic.controller.content.processes.mixin.pick.decrypt.Local',
        'process-decrypt-googledrive': 'Cryptic.controller.content.processes.mixin.pick.decrypt.GoogleDrive',
        'process-encrypt-googledrive': 'Cryptic.controller.content.processes.mixin.pick.encrypt.GoogleDrive'
    },
    init: function (application) {
        application.initMixins(this);
    }
});
