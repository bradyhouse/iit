/**
 * Application view port container. An instance of this class is created by the
 * Cryptic.Application class. It defines (by region) the location and
 * default configuration of all other panels.
 */
Ext.define("Cryptic.view.Viewport", {
    extend: 'Ext.container.Viewport',
    requires:[
        'Ext.layout.container.Border',
        'Cryptic.view.north.BreadCrumbs',
        'Cryptic.view.center.Content',
        'Cryptic.view.south.Footer',
        'Cryptic.view.east.Sidebar'
    ],
    layout: {
        type: 'border'
    },
    padding: '0 0 0 0',
    items: [{
        region: 'north',
        frame: false,
        xtype: 'breadcrumbs',
        bodyStyle: {
            background: '#D58705',
            color: '#FFF',
            padding: '2px'
        },
        border: false,
        collapsible: false,
        split: false
    }, {
        region: 'center',
        xtype: 'content',
        border: false,
        split: true,
        collapsible: false
    }, {
        region: 'east',
        xtype: 'sidebar',
        split: true,
        border: true,
        collapsible: true,
        collapsed: true,
        width: 250
    }, {
        region: 'south',
        xtype: 'footer',
        split: false,
        border: true,
        collapsible: false
    }]
});