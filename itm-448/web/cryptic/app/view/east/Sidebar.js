/**
 * Side bar panel component. This class is initialized
 * by the view port component. It defines the static
 * html visible in the view port's east region.
 */
Ext.define("Cryptic.view.east.Sidebar", {
    extend: 'Ext.panel.Panel',
    alias: 'widget.sidebar',
    id: 'p3',
    style: {
        fontWeight: 'normal',
        fontSize: '12px',
        textAlign: 'center',
        vAlign: 'center',
        color: '#fff',
        padding: '10px'
    },
    html: '<div>Sorry, but Cryptic only ' +
        'supports Google Drive.  ' +
        'If you would like to use the app with a different ' +
        'cloud, please drop us as an <a href="mailto:bradyhouse@gmail.com">email</a>&nbsp;' +
        'and we will see what we can do.' +
        '</div>'
});