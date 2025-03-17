package com.sdis.bilan.lsf.police;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.CarteBinding;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;

public class CartePoliceActivity extends BasePoliceActivity {

    private CarteBinding carteBinding;

    private FusedLocationProviderClient fusedLocationClient;

    private ArrayList<Marker> markersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carteBinding = CarteBinding.inflate(getLayoutInflater());
        setContentView(carteBinding.getRoot());

        String userAgent = "Bilan LSF";
        Configuration.getInstance().setUserAgentValue(userAgent);

        MapView mapView = findViewById(R.id.mapview);
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK); // Utilise le fond de carte OpenStreetMap

        mapView.setMultiTouchControls(true);

        mapView.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint point) {
                mapView.getOverlays().removeAll(markersList);
                markersList.clear();

                Marker marker = new Marker(mapView);
                marker.setPosition(point);
                markersList.add(marker);
                mapView.getOverlays().add(marker);
                mapView.getController().animateTo(point);
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        }));

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            IMapController mapController = mapView.getController();
                            GeoPoint point = new GeoPoint(location.getLatitude(), location.getLongitude());
                            mapController.setCenter(point);
                            mapController.setZoom(17.5);
                            mapController.animateTo(point);

                            // Ajouter un marqueur
                            Marker marker = new Marker(mapView);
                            marker.setPosition(point);
                            mapView.getOverlays().add(marker);
                        }
                    });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }
}
