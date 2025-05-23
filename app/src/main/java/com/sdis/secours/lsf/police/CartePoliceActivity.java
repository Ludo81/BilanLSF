package com.sdis.secours.lsf.police;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.CarteBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;

public class CartePoliceActivity extends BasePoliceActivity {

    private CarteBinding carteBinding;

    private MapView mapView;

    private FusedLocationProviderClient fusedLocationClient;

    private ArrayList<Marker> markersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carteBinding = CarteBinding.inflate(getLayoutInflater());
        setContentView(carteBinding.getRoot());

        Logger.write(this, "Chargement Carte");

        String userAgent = "Secours LSF";
        Configuration.getInstance().setUserAgentValue(userAgent);

        mapView = findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE); // Utilise le fond de carte OpenStreetMap

        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(3.00);

        mapView.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint point) {
                mapView.getOverlays().removeAll(markersList);
                markersList.clear();

                Marker marker = new Marker(mapView);
                marker.setPosition(point);
                marker.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.point_rouge));
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
        setLocalisation(null);

        startVideo(null);
    }

    public void setLocalisation(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            mapView.getOverlays().removeAll(markersList);
                            markersList.clear();

                            GeoPoint point = new GeoPoint(location.getLatitude(), location.getLongitude());
                            mapView.getController().setCenter(point);
                            mapView.getController().setZoom(17.5);
                            mapView.getController().animateTo(point);

                            Marker marker = new Marker(mapView);
                            marker.setPosition(point);
                            mapView.getOverlays().add(marker);
                        }
                    });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    public void startVideo(View v) {
        Intent intent = new Intent(CartePoliceActivity.this, VideoPoliceActivity.class);
        intent.putExtra("VIDEO_NAME", "intervention_par_ou_sont_ils_partis_63");
        intent.putExtra("SHOW_STOP", true);
        startActivity(intent);
    }
}
