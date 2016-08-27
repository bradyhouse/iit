Ext.Loader.setConfig({
    enabled : true,
    paths: {
        'Ext.ux' : 'ux'
    }
});
Ext.application({
    name: 'Cryptic',
    extend: 'Cryptic.Application',
    autoCreateViewport: true
});
