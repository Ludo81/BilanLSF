package com.sdis.secours.lsf.pompier;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.VideoBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class VideoPompierActivity extends BasePompierActivity {

    VideoBinding videoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoBinding = VideoBinding.inflate(getLayoutInflater());
        setContentView(videoBinding.getRoot());

        Logger.write(this, "Chargement Video");
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.lireVideo();
    }

    private void lireVideo() {
        String name = getIntent().getStringExtra("VIDEO_NAME").replace(".mp4", "");

        if ("bilan_primaire_montrez_moi_ou_vous_avez_mal".equals(name)) {
            Button boutonCorps = new Button(this);
            boutonCorps.setText(R.string.corps);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

            boutonCorps.setOnClickListener(v -> {
                Intent intent = new Intent(VideoPompierActivity.this, CorpsActivity.class);
                startActivity(intent);
            });

            FrameLayout frame = findViewById(R.id.frame);
            frame.addView(boutonCorps, params);
        }

        if ("bilan_secondaire_depuis_quelle_heure_avez_vous_mal_63".equals(name) || "accouchement_a_quelle_heure_ont_debute_les_contractions_63".equals(name) ||
                "accouchement_est_ce_que_vous_avez_perdu_les_eaux_si_oui_a_quelle_heure_63".equals(name)) {
            Button boutonHorloge = new Button(this);
            boutonHorloge.setText(R.string.horloge);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

            boutonHorloge.setOnClickListener(v -> {
                Intent intent = new Intent(VideoPompierActivity.this, HorlogeActivity.class);
                startActivity(intent);
            });

            FrameLayout frame = findViewById(R.id.frame);
            frame.addView(boutonHorloge, params);
        }

        if ("accouchement_quelle_est_l39intervalle_entre_les_2_contractions_63".equals(name) || "bilan_secondaire_respirez_normalement_sur_1_minute".equals(name)
                || "bilan_secondaire_respirez_normalement_sur_1_minute_2".equals(name) || "bilan_secondaire_je_controle_votre_pouls_sur_1_minute".equals(name)) {
            Button boutonMinuteur = new Button(this);
            boutonMinuteur.setText(R.string.minuteur);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

            boutonMinuteur.setOnClickListener(v -> {
                Intent intent = new Intent(VideoPompierActivity.this, MinuteurActivity.class);
                startActivity(intent);
            });

            FrameLayout frame = findViewById(R.id.frame);
            frame.addView(boutonMinuteur, params);
        }

        if ("accouchement_quelle_est_la_date_des_dernieres_regles_63".equals(name) || "accouchement_quelle_est_la_date_prevue_du_terme_63".equals(name)) {
            Button boutonCalendrier = new Button(this);
            boutonCalendrier.setText(R.string.calendrier);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

            boutonCalendrier.setOnClickListener(v -> {
                Intent intent = new Intent(VideoPompierActivity.this, CalendrierActivity.class);
                startActivity(intent);
            });

            FrameLayout frame = findViewById(R.id.frame);
            frame.addView(boutonCalendrier, params);
        }

        if ("secours_routier_montrez_moi_la_vitesse".equals(name)) {
            Button boutonVitesse = new Button(this);
            boutonVitesse.setText(R.string.vitesse);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

            boutonVitesse.setOnClickListener(v -> {
                Intent intent = new Intent(VideoPompierActivity.this, VitessePompierActivity.class);
                startActivity(intent);
            });

            FrameLayout frame = findViewById(R.id.frame);
            frame.addView(boutonVitesse, params);
        }


        VideoView videoView = findViewById(R.id.videoView);

        try {
            AssetFileDescriptor afd = getAssets().openFd("videos/pompier/" + name + ".mp4");

            // Lire les données et les copier dans un fichier temporaire
            File tempFile = File.createTempFile("video", ".mp4", getCacheDir());
            FileOutputStream fos = new FileOutputStream(tempFile);
            FileInputStream fis = afd.createInputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

            fis.close();
            fos.close();
            afd.close();

            Logger.write(this, "Lecture vidéo " + name);

            // Lire la vidéo depuis le fichier temporaire
            videoView.setVideoPath(tempFile.getAbsolutePath());
            videoView.start();

            videoView.setOnCompletionListener(mp -> finish());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickClavier(View view) {
        Intent intent = new Intent(VideoPompierActivity.this, ClavierPompierActivity.class);
        startActivity(intent);
    }
}
