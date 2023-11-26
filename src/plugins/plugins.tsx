import { registerPlugin } from '@capacitor/core';

export interface MapstedIonicPlugin {
    echo(options: { value: string }): Promise<{ value: string }>;
    getBuildingInfo(options: { value: number }): Promise<{ value: any }>;
}

const MapstedIonicPlugin = registerPlugin<MapstedIonicPlugin>('MapstedIonicPlugin');

export {
    MapstedIonicPlugin
};