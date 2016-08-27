/**
 * Content panel component. This class is initialized
 * by the Viewport component. It serves as the parent
 * container for the center region.
 */
Ext.define("Cryptic.view.center.Content", {
    extend: 'Ext.panel.Panel',
    alias: 'widget.content',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Cryptic.view.center.platform.Platform',
        'Cryptic.view.center.processes.Processes',
        'Cryptic.view.center.passphrases.PassPhrases',
        'Cryptic.view.center.postprocess.PostProcess'
    ],
    layout: {
        type: 'accordion',
        titleCollapse: true,
        animate: true,
        activeOnTop: true
    },
    collapseFirst: false,
    id : 'mp',
    hideCollapseTool: true,
    bodyStyle:{"background-color": "#7EEF3C" },
    items: [{
        xtype: 'platform'
    }, {
        xtype: 'processes'
    }, {
        xtype: 'cloudprovider'
    }, {
        xtype: 'passphrases'
    }, {
        xtype: 'postprocess'
    }]
});