/**
 * Provider panel component. This class is initialized
 * by the Content panel. It serves as the parent
 * container for the provider related content and
 * input controls.
 */
Ext.define("Cryptic.view.center.provider.Provider", {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Ext.Button',
        'Cryptic.view.center.provider.Provider',
        'Cryptic.view.center.provider.GoogleDrive',
        'Cryptic.view.center.provider.Other'
    ],
    alias: 'widget.cloudprovider',
    title: 'Which cloud?',
    id: 'p2',
    isp: true,
    hasSubPanels: false,
    hideCollapseTool: true,
    collapsed: true,
    layout:  'fit',
    items: [{
        xtype: 'panel',
        layout:  {
            type: 'vbox',
            align: 'center'
        },
        items: [{
            xtype: 'label',
            text: 'Which cloud?',
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
                xtype: 'googledrive'
            }, {
                xtype: 'other'
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