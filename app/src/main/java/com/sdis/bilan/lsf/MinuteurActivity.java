package com.sdis.bilan.lsf;

import static java.util.concurrent.TimeUnit.SECONDS;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MinuteurActivity extends ComponentActivity {

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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

    public void activer(View view) {

        final Runnable runnable = new Runnable() {
            int countdownStarter = secondes + minutes * 60;

            public void run() {
                countdownStarter--;

                TextView afficheurSecondes = findViewById(R.id.afficheur_secondes);
                afficheurSecondes.setText(countdownStarter % 60 < 10 ? "0" + countdownStarter % 60 : String.valueOf(countdownStarter % 60));

                ImageView aiguille_secondes = findViewById(R.id.aiguille_secondes);
                aiguille_secondes.setRotation(165 + countdownStarter * 6);

                ImageView aiguille_minutes = findViewById(R.id.aiguille_minutes);
                aiguille_minutes.setRotation(-55 + countdownStarter / 60 * 6);

                TextView afficheurMinutes = findViewById(R.id.afficheur_minutes);
                afficheurMinutes.setText(String.valueOf(countdownStarter / 60));

                if (countdownStarter == 0) {
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

    public void retour(View view) {
        finish();
    }
}
