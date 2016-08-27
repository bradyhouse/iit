/**
 * Post process download button.
 * This class is initialized
 * by the post process panel.
 */
Ext.define("Cryptic.view.center.postprocess.Download", {
    extend: 'Ext.Button',
    alias: 'widget.download',
    requires: [
        'Ext.Button'
    ],
    text: 'DOWNLOAD',
    height: 70,
    width: 240,
    style: 'margin: 5px'
});