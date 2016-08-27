/**
 * Post process panel. This class is initialized
 * by the Content panel. It serves as the parent
 * container for the post process related content and
 * input controls.
 */
Ext.define("Cryptic.view.center.postprocess.PostProcess", {
    extend: 'Ext.form.Panel',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Ext.Button',
        'Cryptic.view.center.postprocess.Download',
        'Cryptic.view.center.postprocess.NextFile',
        'Cryptic.view.center.postprocess.Subtitle'
    ],
    alias: 'widget.postprocess',
    title: 'Download',
    id: 'p12',
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
            text: 'Your file is done',
            style: {
                fontWeight: 'normal',
                fontSize: '48px',
                textAlign: 'center',
                vAlign: 'center',
                color: '#fff',
                paddingTop: '40px',
                marginBottom: '30px'
            }
        }, {
            xtype: 'postprocess-subtitle'
        }, {
            xtype: 'panel',
            layout: {
                type: 'hbox',
                align: 'center'
            },
            items: [{
                xtype: 'download'
            }, {
                xtype: 'nextfile'
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