export interface UmpPlugin {
    initialise(): Promise<{
        value: boolean;
    }>;
    show(options: {
        force: boolean;
    }): Promise<{
        value: boolean;
    }>;
}
