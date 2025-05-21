package com.sdis.secours.lsf.pompier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.VitesseBinding;

public class VitessePompierActivity extends BasePompierActivity {

    VitesseBinding vitesseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vitesseBinding = VitesseBinding.inflate(getLayoutInflater());
        setContentView(vitesseBinding.getRoot());

        Logger.write(this, "Chargement Vitesse");

        SeekBar regle = findViewById(R.id.regle);
        ImageView aiguille = findViewById(R.id.aiguille);
        TextView vitesse = findViewById(R.id.vitesse);
        regle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aiguille.setRotation(-135 + progress);
                if (progress == 0) {
                    vitesse.setText(0 + " km/h");
                } else {
                    vitesse.setText(Math.round(0.000116836 * progress * progress + 0.877994 * progress + 6.12798) + " km/h");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        onClickVideo(null);
    }

    public void onClickVideo(View view) {
        Intent intent = new Intent(VitessePompierActivity.this, VideoPompierActivity.class);
        intent.putExtra("VIDEO_NAME", "secours_routier_montrez_moi_la_vitesse");
        intent.putExtra("SHOW_STOP", true);
        startActivity(intent);
    }
}
