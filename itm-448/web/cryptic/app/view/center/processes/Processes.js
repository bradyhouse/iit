Ext.define("Cryptic.view.center.processes.Processes", {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Cryptic.view.center.processes.selecttype.SelectType',
        'Cryptic.view.center.processes.pick.encrypt.PickFile',
        'Cryptic.view.center.processes.pick.decrypt.PickFile'
    ],
    alias: 'widget.processes',
    title: '',
    id: 'p11',
    isp: true,
    hasSubPanels: true,
    hideCollapseTool: true,
    collapseFirst: false,
    bodyStyle:{"background-color": "#7EEF3C"},
    layout: {
        type: 'accordion',
        titleCollapse: true,
        animate: true,
        activeOnTop: true
    },
    items: [{
        xtype: 'processes-selecttype'
    }, {
        xtype: 'process-encrypt-pickfile'
    }, {
        xtype: 'process-decrypt-pickfile'
    }]
});