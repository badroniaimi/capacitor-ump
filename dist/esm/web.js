import { WebPlugin } from '@capacitor/core';
export class UmpWeb extends WebPlugin {
    async initialise() {
        console.log('Ump | initialise');
        return { value: true };
    }
    async show(options) {
        console.log('Ump | show', options);
        return { value: true };
    }
}
//# sourceMappingURL=web.js.map