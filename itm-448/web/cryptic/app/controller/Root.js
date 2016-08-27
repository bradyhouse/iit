/**
 * Master Root controller. This class is initialized
 * by the Application class. It used to listen for and
 * respond to routing events.
 */
Ext.define('Cryptic.controller.Root', {
    extend: 'Ext.app.Controller',
    config : {
        refs    : {
            content : '#mp',
            sidebar : '#p3'
        },
        routes  : {
            'mp:id' : {
                action     : 'onRouteExpandPanel',
                before     : 'onRouteBeforeExpandPanel',
                conditions : {
                    //take control of the :id & :subid parameters, make them optional but delimited by colon
                    ':id'    : '(?:(?::){1}([%a-zA-Z0-9\-\_\s,]+))?',
                    ':subid' : '(?:(?::){1}([%a-zA-Z0-9\-\_\s,]+))?'
                }
            }
        }
    },
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'content',
            selector: 'content'
        }, {
            ref: 'platform',
            selector: 'platform'
        }, {
            ref: 'processes',
            selector: 'processes'
        }, {
            ref: 'cloudProvider',
            selector: 'cloudprovider'
        }, {
            ref: 'postProcess',
            selector: 'postprocess'
        }, {
            ref: 'passphrases',
            selector: 'passphrases'
        }, {
            ref: 'sidebar',
            selector: 'sidebar'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                platform: {
                    show: me.onPanelShow
                },
                processes: {
                    show: me.onPanelShow
                },
                cloudprovider: {
                    show: me.onPanelShow
                },
                passphrases: {
                    show: me.onPanelShow
                },
                postprocess: {
                    show: me.onPanelShow
                },
                sidebar: {
                    expand: me.onSidebarExpand
                }
            }
        });
    },
    onRouteBeforeExpandPanel : function(id, action) {
        var mP = this.getContent(),
            sB = this.getSidebar(),
            child, bool;
        if (!id) {
            //no id was specified, use 0 index to resolve child
            id = 0;
        }
        child = mP ? mP.getComponent(id) : null;
        if (!child && id !== 'p3') {
            bool = false;
            Ext.Msg.alert('Tab Not Found', 'Tab with id or index "<b>' + id + '</b>" was not found!');
        }
        //continue action execution if child was found, stop if not
        action.resume(bool);
    },
    onRouteExpandPanel : function(id) {
        var mP = this.getContent(),
            child = id && mP && id !== 'p3' ? mP.getComponent(id) : null,
            sB = this.getSidebar(),
            i = 0,
            item;
        if (id === 'p3') {
            sB.expand();
        } else {
            sB.collapse();
        }
        if (child && mP.items && mP.items.length > 0) {
            mP.items.each(function (item) {
                if (item.isp === true) {
                    item.hide();
                }
            });
            child.show();
            child.expand();
        }
    },
    onPanelShow : function(p) {
        var id    = p.getId(),
            hash  = 'mp:' + id;
        this.redirectTo(hash);
    },
    onSidebarExpand : function(p) {
        var id    = p.getId(),
            hash  = 'mp:' + id;
        this.redirectTo(hash);
    }
});