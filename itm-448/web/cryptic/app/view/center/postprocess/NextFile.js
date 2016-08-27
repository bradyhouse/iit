/**
 * Post process next file button.
 * This class is initialized
 * by the post process panel.
 */
Ext.define("Cryptic.view.center.postprocess.NextFile", {
    extend: 'Ext.Button',
    alias: 'widget.nextfile',
    requires: [
        'Ext.Button'
    ],
    text: 'NEXT FILE',
    height: 70,
    width: 240,
    style: 'margin: 5px'
});