package com.sdis.bilan.lsf.police;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.PointsPermisBinding;

public class PointsPermisPoliceActivity extends BasePoliceActivity {

    PointsPermisBinding pointsPermisBinding;

    TextView pointsView;

    ImageView permisView;

    int points = -0;

    boolean retraitPermis = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pointsPermisBinding = PointsPermisBinding.inflate(getLayoutInflater());
        setContentView(pointsPermisBinding.getRoot());

        pointsView = findViewById(R.id.points);
        permisView = findViewById(R.id.permis_conduire);
    }

    public void monter(View v) {
        if (points != 0) {
            points += 1;
            if (points == 0) {
                pointsView.setText("-0");
            } else {
                pointsView.setText(String.valueOf(points));
            }
        }
    }

    public void descendre(View v) {
        if (points != -12) {
            points -= 1;
            pointsView.setText(String.valueOf(points));
        }
    }

    public void retraitPermis(View view) {
        if (!retraitPermis) {
            retraitPermis = true;
            permisView.setImageResource(R.drawable.permis_conduire_retrait);
        } else {
            retraitPermis = false;
            permisView.setImageResource(R.drawable.permis_conduire);
        }

    }
}
