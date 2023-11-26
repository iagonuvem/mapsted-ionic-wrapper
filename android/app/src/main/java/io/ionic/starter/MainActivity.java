package io.ionic.starter;

import android.os.Bundle;
import android.util.Log;

import com.mapsted.MapstedBaseApplication;
import com.mapsted.positioning.CoreApi;
import com.mapsted.positioning.CoreApi.LocationServicesCallback;
import com.mapsted.positioning.MapstedCoreApiProvider;
import com.mapsted.positioning.SdkError;
import com.mapsted.positioning.SdkStatusUpdate;
import com.mapsted.positioning.core.map_download.PropertyDownloadManager;
import com.mapsted.positioning.CoreParams;

import java.util.Locale;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity implements MapstedCoreApiProvider{
    private CoreApi coreApi;

    private static final String TAG = MainActivity.class.getSimpleName();
    private long tActivityStart = 0L;
    private long tStartInitMapsted = 0L;
    private long tInitMapstedFinished = 0L;
    private long tStartDownload = 0L;
    private long tDownloadFinished = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registerPlugin(MapstedIonicPlugin.class);
        coreApi = ((MapstedBaseApplication) getApplication()).getCoreApi(this);

        super.onCreate(savedInstanceState);

        tActivityStart = System.currentTimeMillis();

        Log.d(TAG, "onCreate");
        setupMapstedSdk();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private void setupMapstedSdk() {

        tStartInitMapsted = System.currentTimeMillis();

        CoreParams.Builder<? extends CoreParams, ? extends CoreParams.Builder> builder =
                CoreParams.newBuilder();

        CoreParams params = builder
            // Add additional custom content here
            .build();

        coreApi.setup().initialize(
            params,
            new CoreApi.CoreInitCallback() {
                @Override
                public void onSuccess() {

                    tInitMapstedFinished = System.currentTimeMillis();
                    double dtInitSec = (tInitMapstedFinished - tStartInitMapsted) / 1000.0;

                    String msg = String.format(Locale.CANADA, "coreApi: onSuccess. dt: %.1f s", dtInitSec);

                    Log.d(TAG, msg);

                    coreApi.setup().startLocationServices(MainActivity.this,
                        new LocationServicesCallback() {
                            @Override
                            public void onSuccess() {
                                Log.d(TAG, "coreApi: LocServices: onSuccess");
                            }

                            @Override
                            public void onFailure(SdkError sdkError) {
                                Log.d(TAG, "coreApi: LocServices: onFailure " + sdkError.toString());
                            }
                        });
                }

                @Override
                public void onFailure(SdkError sdkError) {
                    Log.d(TAG, "coreApi: onFailure: " + sdkError);
                }

                @Override
                public void onStatusUpdate(SdkStatusUpdate sdkUpdate) {
                    Log.d(TAG, "coreApi: onStatusUpdate: " + sdkUpdate);
                }
            }
        );
    }

    private void startPropertyDownload(int propertyId) {
        Log.d(TAG, "startPropertyDownload: pId: " + propertyId);

        tStartDownload = System.currentTimeMillis();

        coreApi.properties().startDownload(
            propertyId,
            new PropertyDownloadManager.Listener() {
                @Override
                public void onComplete(int propertyId) {

                    tDownloadFinished = System.currentTimeMillis();

                    double dtDownloadSec = (tStartDownload - tDownloadFinished) / 1000.0;

                    String msg = String.format(Locale.CANADA,
                        "startPropertyDownload: onComplete: pId: %d dt: %.1f s",
                        propertyId, dtDownloadSec);

                    Log.d(TAG, msg);
                }

                @Override
                public void onFail(int propertyId, Exception exception) {

                    String msg = "startPropertyDownload: onFail: pId: " + propertyId + " -> " + exception.getMessage();

                    Log.d(TAG, msg);
                }

                @Override
                public void onProgress(int propertyId, int current, int total) {
                    Log.d(TAG, "startPropertyDownload: onProgress: pId: " + propertyId + " -> (" + current + " / " +           total + ")");
                }
            }
        );
    }

    @Override
    public CoreApi provideMapstedCoreApi() {
        return coreApi;
    }
}
