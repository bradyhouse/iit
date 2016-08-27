/**
 * Post process download controller. This class listens
 * for the post process panel download click event.
 */
Ext.define('Cryptic.controller.content.postprocess.mixin.Download', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'download',
            selector: 'download'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                download: {
                    click: me.onDownloadClick
                }
            }
        });
    },
    onDownloadClick: function (ctrl) {
        form = ctrl.up('form').getForm();
        switch(Cryptic.Application.target) {
            case 'googledrive': {
                document.location = Cryptic.Application.file.webContentLink;
            }
            break;
            case 'local': {
                if(form.isValid()){
                    document.location = 'file-download.php?f='+Cryptic.Application.file.title;
                }
            }
            break;
        }
    }
});
