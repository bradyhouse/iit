/**
 * Content platform panel component. This class is initialized
 * by the Content component. It serves as the parent
 * container for platform components.
 */
Ext.define("Cryptic.view.center.platform.Platform", {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Ext.Button',
        'Cryptic.view.center.platform.Platform',
        'Cryptic.view.center.platform.Local',
        'Cryptic.view.center.platform.Cloud'
    ],
    alias: 'widget.platform',
    title: '',
    id: 'p1',
    isp: true,
    hasSubPanels: false,
    hideCollapseTool: true,
    layout:  'fit',
    items: [{
        xtype: 'panel',
        layout:  {
            type: 'vbox',
            align: 'center'
        },
        items: [{
            xtype: 'label',
            text: 'Where does the file reside?',
            style: {
                fontWeight: 'normal',
                fontSize: '48px',
                textAlign: 'center',
                vAlign: 'center',
                color: '#fff',
                paddingTop: '40px',
                marginBottom: '60px'
            }
        }, {
            xtype: 'panel',
            layout: {
                type: 'hbox',
                align: 'center'
            },
            items: [{
                xtype: 'platform-local'
            }, {
                xtype: 'platform-cloud'
            }],
            border: false,
            collapsible: false,
            split: false
        }],
        border: false,
        collapsible: false,
        split: false
    }],
    border: false,
    split: false,
    initComponent: function () {
        this.callParent();
    }
});