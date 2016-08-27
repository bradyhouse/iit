Ext.define("Cryptic.view.center.processes.selecttype.SelectType", {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.Accordion',
        'Ext.panel.Panel',
        'Ext.Button',
        'Cryptic.view.center.processes.selecttype.Decrypt',
        'Cryptic.view.center.processes.selecttype.Encrypt'
    ],
    alias: 'widget.processes-selecttype',
    title: '',
    id: 'sub6',
    isSubPanel: true,
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
            text: 'What do you want to do?',
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
                xtype: 'processes-selecttype-decrypt'
            }, {
                xtype: 'processes-selecttype-encrypt'
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