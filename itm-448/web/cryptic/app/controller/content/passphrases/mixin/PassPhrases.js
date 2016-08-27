/**
 * Pass phrases controller. This class listens
 * for the pass phrase view expand and collapse events.
 */
Ext.define('Cryptic.controller.content.passphrases.mixin.PassPhrases', {
    extend: 'Ext.app.Controller',
    init: function (controller) {
        var me = this;
        me.addRefs(controller);
        me.addListeners(controller);
    },
    addRefs: function (controller) {
        controller.addRef([{
            ref: 'passphrases',
            selector: 'passphrases'
        }]);
    },
    addListeners: function (controller) {
        var me = this;
        controller.listen({
            component: {
                passphrases: {
                    beforecollapse: me.onPassPhrasesBeforeCollapse,
                    beforeexpand: me.onPassPhrasesBeforeExpand
                }
            }
        });
    },
    onPassPhrasesBeforeExpand: function (ctrl) {
        ctrl.setTitle('');
    },
    onPassPhrasesBeforeCollapse: function (ctrl) {
        ctrl.setTitle('Pass Phrase');
    }
});
