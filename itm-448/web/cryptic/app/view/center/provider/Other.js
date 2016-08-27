/**
 * Provider other button component.
 * This class is initialized
 * by the Provider panel.
 */
Ext.define("Cryptic.view.center.provider.Other", {
    extend: 'Ext.Button',
    alias: 'widget.other',
    requires: [
        'Ext.Button'
    ],
    text: 'OTHER',
    height: 70,
    width: 240,
    style: 'margin: 5px'
});