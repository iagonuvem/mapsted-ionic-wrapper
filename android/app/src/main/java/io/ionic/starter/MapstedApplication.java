package io.ionic.starter;

import com.mapsted.MapstedBaseApplication;

public class MapstedApplication extends MapstedBaseApplication{
    private static MapstedApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MapstedApplication getInstance() {
        return instance;
    }
}