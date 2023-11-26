package io.ionic.starter;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import android.util.Log;

// Mapsted SDK
import com.mapsted.positioning.CoreApi;
import com.mapsted.positioning.coreObjects.BuildingInfo;

@CapacitorPlugin(name = "MapstedIonicPlugin")
public class MapstedIonicPlugin extends Plugin{
    private static final String TAG = "MapstedIonicPlugin";
    MapstedApplication app = new MapstedApplication();
//    CoreApi coreApi = mainActivity.provideMapstedCoreApi();
    CoreApi coreApi = app.getCoreApi(app.getInstance());

    @PluginMethod()
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.resolve(ret);
    }

    @PluginMethod()
    public void getBuildingInfo(PluginCall call) {
        Integer buildingId = call.getInt("buildingId");
        Log.d(TAG, "Core API: "+coreApi);
        Log.d(TAG, "Building ID: "+buildingId);

        JSObject ret = new JSObject();
        if(buildingId != null){
            BuildingInfo buildingInfo = coreApi.buildings().getBuildingInfo(buildingId);
            Log.d(TAG, "BuildingInfo: "+buildingInfo);
            ret.put("BuildingInfo", buildingInfo);
            call.resolve(ret);
        } else {
            call.reject("Invalid Building ID");
        }
    }
}
