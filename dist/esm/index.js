import { registerPlugin } from '@capacitor/core';
const Ump = registerPlugin('Ump', {
    web: () => import('./web').then(m => new m.UmpWeb()),
});
export * from './definitions';
export { Ump };
//# sourceMappingURL=index.js.map