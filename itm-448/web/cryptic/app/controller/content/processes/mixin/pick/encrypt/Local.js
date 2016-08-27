/**
 * Encryption process Local file selection controller. This class listens
 * for the PickFile file change event.
 */
Ext.define('Cryptic.controller.content.processes.mixin.pick.encrypt.Local', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'processEncryptLocal',
            selector: 'process-encrypt-local'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'process-encrypt-local': {
                    change: me.onProcessEncryptLocalChange
                }
            }
        });
    },
    onProcessEncryptLocalChange: function (ctrl, value) {
        var me = this,
            form = ctrl.up('form').getForm();
        if(form.isValid()){
            form.submit({
                url: 'file-upload.php',
                success: function(form, data) {
                    form.emptyText = data.result.responseText;
                    Ext.Ajax.request({
                        url: data.result.responseText,
                        cors: true,
                        success: function(response,options) {
                            var blob = response.responseText;
                            Cryptic.Application.target = 'local';
                            Cryptic.Application.file = { title: data.result.responseText,
                                                         blob: blob };
                        },
                        failure: function(response,options) {
                            Ext.Msg.alert('Error: ' + response.status || 'AJAX ERROR');
                        }
                    });
                }
            });
        }
    }
});
