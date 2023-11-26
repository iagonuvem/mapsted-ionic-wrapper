package io.ionic.starter;


import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

// Mapsted SDK
import com.mapsted.positioning.CoreApi;
import com.mapsted.positioning.coreObjects.BuildingInfo;

@CapacitorPlugin(name = "MapstedIonicPlugin")
public class MapstedIonicPlugin extends Plugin{
    MainActivity mainActivity = new MainActivity();
    CoreApi coreApi = mainActivity.provideMapstedCoreApi();

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

        JSObject ret = new JSObject();
        if(buildingId != null){
            BuildingInfo buildingInfo = coreApi.buildings().getBuildingInfo(buildingId);
            ret.put("BuildingInfo", buildingInfo);
            call.resolve(ret);
        } else {
            call.reject("Invalid Building ID");
        }
    }
}
