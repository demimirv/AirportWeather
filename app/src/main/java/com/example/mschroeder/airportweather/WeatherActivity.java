package com.example.mschroeder.airportweather;

import android.app.FragmentTransaction;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import fragments.AirportListFragment;
import fragments.AirportMapFragment;


public class WeatherActivity extends ActionBarActivity {

    private static String TAG = "WEATHER_ACTIVITY";
    private static int APP_LIST = 0;
    private static int APP_MAP = 1;

    private Menu mMenu;
    private int appState = APP_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airport_layout);

        String version = "";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        }
        catch(PackageManager.NameNotFoundException ex){
            Log.e(TAG, ex.toString());
        }
        setTitle(getString(R.string.app_name) + " " + version);
        gotoListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        getMenuInflater().inflate(R.menu.airport_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.airport_toggle) {
            if (appState == APP_LIST){
                // Switch view to map
                gotoMapFragment();
            }
            else {
                // Switch view to list
                gotoListFragment();
            }
            updateActionIcon();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateActionIcon(){
        MenuItem item = mMenu.findItem(R.id.airport_toggle);

        if (appState == APP_LIST){
            item.setIcon(android.R.drawable.ic_dialog_map);
        }
        else{
            item.setIcon(android.R.drawable.ic_dialog_dialer);
        }
    }

    private void gotoListFragment(){
        appState = APP_LIST;
        // Set up the list view pane
        AirportListFragment frag = new AirportListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentLayout, frag, "ListFragment");
        ft.commit();
    }

    private void gotoMapFragment(){
        appState = APP_MAP;
        // Set up the map view pane
        AirportMapFragment frag = new AirportMapFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentLayout, frag, "MapFragment");
        ft.commit();
    }
}
