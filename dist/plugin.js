var capacitorUmp = (function (exports, core) {
    'use strict';

    const Ump = core.registerPlugin('Ump', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.UmpWeb()),
    });

    class UmpWeb extends core.WebPlugin {
        async initialise() {
            console.log('Ump | initialise');
            return { value: true };
        }
        async show(options) {
            console.log('Ump | show', options);
            return { value: true };
        }
    }

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        UmpWeb: UmpWeb
    });

    exports.Ump = Ump;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

}({}, capacitorExports));
//# sourceMappingURL=plugin.js.map
