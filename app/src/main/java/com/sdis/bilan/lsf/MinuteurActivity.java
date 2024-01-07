package com.sdis.bilan.lsf;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MinuteurActivity extends ComponentActivity {

    private int minutes;

    private int secondes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minuteur);

        SeekBar regleMinutes = findViewById(R.id.regle_minutes);
        SeekBar regleSecondes = findViewById(R.id.regle_secondes);
        ImageView aiguille_minutes = findViewById(R.id.aiguille_minutes);
        ImageView aiguille_secondes = findViewById(R.id.aiguille_secondes);
        TextView reglageMinutes = findViewById(R.id.reglage_minutes);
        TextView reglageSecondes = findViewById(R.id.reglage_secondes);
        TextView afficheurMinutes = findViewById(R.id.afficheur_minutes);
        TextView afficheurSecondes = findViewById(R.id.afficheur_secondes);

        regleMinutes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aiguille_minutes.setRotation(-55 + progress * 6);

                reglageMinutes.setText("Minutes : " + progress + "'");

                minutes = progress;
                afficheurMinutes.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        regleSecondes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aiguille_secondes.setRotation(165 + progress * 6);

                reglageSecondes.setText("Secondes : " + progress + "''");

                secondes = progress;
                afficheurSecondes.setText(progress < 10 ? "0" + progress : String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private int decompte;

    private Timer minuterie;

    public void start(View view) {

        decompte = secondes + minutes * 60;

        minuterie = new Timer();

        findViewById(R.id.stop).setVisibility(View.VISIBLE);
        findViewById(R.id.start).setVisibility(View.INVISIBLE);

        minuterie.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(() -> {

                    if (decompte >= 0) {

                        TextView afficheurSecondes = findViewById(R.id.afficheur_secondes);
                        afficheurSecondes.setText(decompte % 60 < 10 ? "0" + decompte % 60 : String.valueOf(decompte % 60));

                        ImageView aiguille_secondes = findViewById(R.id.aiguille_secondes);
                        aiguille_secondes.setRotation(165 + decompte * 6);

                        ImageView aiguille_minutes = findViewById(R.id.aiguille_minutes);
                        aiguille_minutes.setRotation(-55 + decompte / 60 * 6);

                        TextView afficheurMinutes = findViewById(R.id.afficheur_minutes);
                        afficheurMinutes.setText(String.valueOf(decompte / 60));

                        decompte -= 1;
                    } else {
                        minuterie.cancel();

                        findViewById(R.id.stop).setVisibility(View.INVISIBLE);
                        findViewById(R.id.start).setVisibility(View.VISIBLE);
                    }
                });
            }
        }, 0, 1000);

    }

    public void stop(View view) {
        minuterie.cancel();

        findViewById(R.id.stop).setVisibility(View.INVISIBLE);
        findViewById(R.id.start).setVisibility(View.VISIBLE);
    }

    public void retour(View view) {
        finish();
    }
}
