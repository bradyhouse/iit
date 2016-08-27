/**
 * Bread crumbs panel component. This class is initialized
 * by the view port component. It defines the static
 * html visible in the view port's north region.
 */
Ext.define("Cryptic.view.north.BreadCrumbs", {
    extend: 'Ext.panel.Panel',
    alias: 'widget.breadcrumbs',
    html: '<div>Cryptic</div>',
    bodyStyle:{"background-color": "#FF7600" }
});