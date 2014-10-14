package com.example.fix.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.apigee.sdk.ApigeeClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyActivity extends Activity {

    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String ORG = "mtrebell";
        String APP = "sandbox";

//        GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
//        GoogleMap map = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map))
//                .getMap();

        ApigeeClient apigeeClient = new ApigeeClient(ORG,APP,this.getBaseContext());

        // hold onto the ApigeeClient instance in our application object.
        MyApplication yourApp = (MyApplication) getApplication();
        yourApp.setApigeeClient(apigeeClient);

        setContentView(R.layout.activity_my);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        if (map!=null){
            Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
                    .title("Hamburg"));
            Marker kiel = map.addMarker(new MarkerOptions()
                    .position(KIEL)
                    .title("Kiel")
                    .snippet("Kiel is cool")
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_launcher)));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // add items to the action bar
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_newPin:
                openAddPin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void openSearch(){
        Intent searchIntent = new Intent(this,SearchActivity.class);
        //Start Product Activity
        startActivity(searchIntent);
    }

    public void openFilter(){

    }

        public void openAddPin(){
            Intent addIntent = new Intent(this,NewPinActivity.class);
            //Start Product Activity
            startActivity(addIntent);

    }

    public void openPinInfo(){

    }

    public void openMenu(){

    }

    public void openNotifications(){

    }
}
