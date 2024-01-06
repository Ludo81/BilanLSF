package com.sdis.bilan.lsf;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

public class VitesseActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vitesse);

        SeekBar regle = findViewById(R.id.regle);
        ImageView aiguille = findViewById(R.id.aiguille);
        TextView vitesse = findViewById(R.id.vitesse);
        regle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aiguille.setRotation(-135 + progress);
                vitesse.setText(Math.round(0.000116836*progress*progress + 0.877994*progress + 6.12798) + " km/h");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void retour(View view) {
        finish();
    }
}
