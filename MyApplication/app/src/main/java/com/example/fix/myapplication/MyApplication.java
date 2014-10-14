package com.example.fix.myapplication;

import android.app.Application;
import com.apigee.sdk.ApigeeClient;
/**
 * Created by fix on 19/09/2014.
 */
public class MyApplication extends Application {
    private ApigeeClient apigeeClient;

    public MyApplication()
    {
        this.apigeeClient = null;
    }

    public ApigeeClient getApigeeClient()
    {
        return this.apigeeClient;
    }

    public void setApigeeClient(ApigeeClient apigeeClient)
    {
        this.apigeeClient = apigeeClient;
    }
}
