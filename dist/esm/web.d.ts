import { WebPlugin } from '@capacitor/core';
import type { UmpPlugin } from './definitions';
export declare class UmpWeb extends WebPlugin implements UmpPlugin {
    initialise(): Promise<{
        value: boolean;
    }>;
    show(options: {
        force: boolean;
    }): Promise<{
        value: boolean;
    }>;
}
