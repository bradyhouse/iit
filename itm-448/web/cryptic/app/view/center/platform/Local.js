/**
 * Platform local button component. This class is initialized
 * by the Platform panel component.
 */
Ext.define("Cryptic.view.center.platform.Local", {
    extend: 'Ext.Button',
    alias: 'widget.platform-local',
    requires: [
        'Ext.Button'
    ],
    text: 'LOCAL',
    height: 70,
    width:  240,
    style: 'margin: 5px'
});