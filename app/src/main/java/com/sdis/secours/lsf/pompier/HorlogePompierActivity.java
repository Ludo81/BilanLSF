package com.sdis.secours.lsf.pompier;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.HorlogeBinding;

public class HorlogePompierActivity extends BasePompierActivity {

    HorlogeBinding horlogeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        horlogeBinding = HorlogeBinding.inflate(getLayoutInflater());
        setContentView(horlogeBinding.getRoot());

        Logger.write(this, "Chargement Horloge");

        SeekBar regle = findViewById(R.id.regle_horloge);
        ImageView aiguille_minutes = findViewById(R.id.aiguille_minutes);
        ImageView aiguille_heures = findViewById(R.id.aiguille_heures);
        ImageView fond = findViewById(R.id.fond);
        TextView afficheur = findViewById(R.id.afficheur);
        regle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 720) {
                    fond.setImageResource(R.drawable.horloge_pm);
                } else {
                    fond.setImageResource(R.drawable.horloge_am);
                }
                int heures = progress / 60;
                int minutes = (progress % 60);
                aiguille_minutes.setRotation(-55 + 360 * minutes / 60);
                aiguille_heures.setRotation(-55 + heures * 30);

                String minutes_string = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
                afficheur.setText(heures + ":" + minutes_string);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
