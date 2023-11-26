import { registerPlugin } from '@capacitor/core';

export interface MapstedIonicPluginI {
    echo(options: { value: string }): Promise<{ value: string }>;
    getBuildingInfo(options: { buildingId: number }): Promise<{ BuildingInfo: any }>;
}

const MapstedIonicPlugin = registerPlugin<MapstedIonicPluginI>('MapstedIonicPlugin');

export {
    MapstedIonicPlugin
};