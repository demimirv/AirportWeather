package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mschroeder.airportweather.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AirportMapFragment extends Fragment {

    private GoogleMap mGoogleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.airport_map, null);

        createMap();

        return view;
    }

    private void createMap(){
        try {
            if(null == mGoogleMap){
                mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();

                // Show an error if the map is null
                if(null == mGoogleMap) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Creating map failed", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("createMap", exception.toString());
        }
    }

    private void addMapMarker(float lat, float lng, String name){
        if(null != mGoogleMap){
            mGoogleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lat, lng))
                            .title(name)
                            .draggable(false)
            );
        }
    }
}
