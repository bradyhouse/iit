/**
 * Platform local controller class. This class listens for the
 * platform view local click event.
 */
Ext.define('Cryptic.controller.content.platform.mixin.Local', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'platformLocal',
            selector: '#platform-local'
        }, {
            ref: 'breadcrumbs',
            selector: 'breadcrumbs'
        }, {
            ref: 'processes',
            selector: 'processes'
        }, {
            ref: 'processesSelecttype',
            selector: 'processes-selecttype'
        }, {
            ref: 'processEncryptPickFile',
            selector: 'process-encrypt-pickfile'
        }, {
            ref: 'processDecryptPickFile',
            selector: 'process-decrypt-pickfile'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                'platform-local': {
                    click: me.onLocalClick
                }
            }
        });
    },
    onLocalClick: function (ctrl) {
        var me = this,
            plat = me.getPlatform(),
            proc = me.getProcesses(),
            sl = me.getProcessesSelecttype(),
            pd = me.getProcessDecryptPickFile(),
            pe = me.getProcessEncryptPickFile(),
            br = me.getBreadcrumbs();
        console.log('onLocalClick');
        br.body.update('<div>Cryptic &#64; Local</div>');
        // Update the application target attribute
        // this attribute controls whether the processes
        // pick file panel will target the user's local
        // hard drive, or their google drive cloud for
        // the source file
        Cryptic.Application.target = 'local';
        pd.hide();
        pe.hide();
        plat.hide();
        proc.show();
        proc.expand();
        sl.show();
        sl.expand();
    }
});
