package com.sdis.secours.lsf.police;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.PointsPermisBinding;

public class PointsPermisPoliceActivity extends BasePoliceActivity {

    PointsPermisBinding pointsPermisBinding;

    private TextView pointsView;

    private ImageView permisView;
    private ImageButton monterView;
    private ImageButton descendreView;

    int points = -0;

    boolean retraitPermis = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pointsPermisBinding = PointsPermisBinding.inflate(getLayoutInflater());
        setContentView(pointsPermisBinding.getRoot());

        Logger.write(this, "Chargement Points Permis");

        pointsView = findViewById(R.id.points);
        permisView = findViewById(R.id.permis_conduire);

        monterView = findViewById(R.id.monter);
        descendreView = findViewById(R.id.descendre);
    }

    public void monter(View v) {
        if (points != 0) {
            points += 1;
            descendreView.setImageResource(R.drawable.descendre);
            if (points == 0) {
                pointsView.setText("-0");
                monterView.setImageResource(0);
            } else {
                pointsView.setText(String.valueOf(points));
            }
        }
    }

    public void descendre(View v) {
        if (points != -12) {
            monterView.setImageResource(R.drawable.monter);
            points -= 1;
            pointsView.setText(String.valueOf(points));
            if (points == -12) {
                descendreView.setImageResource(0);
            }
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
