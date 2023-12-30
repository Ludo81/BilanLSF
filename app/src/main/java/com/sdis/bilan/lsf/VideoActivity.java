package com.sdis.bilan.lsf;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.VideoView;

import androidx.activity.ComponentActivity;

public class VideoActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        this.lireVideo();
    }

@Override
    protected void onResume() {
        super.onResume();
        this.lireVideo();
    }

    private void lireVideo(){
        String name = getIntent().getStringExtra("VIDEO_NAME");
        boolean createButton = getIntent().getBooleanExtra("CREATE_BUTTON", true);

        if ("bilan_primaire_montrez_moi_ou_vous_avez_mal".equals(name) && createButton) {
            Button boutonCorps = new Button(this);
            boutonCorps.setText(R.string.corps);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

            boutonCorps.setOnClickListener(v -> {
                Intent intent = new Intent(VideoActivity.this, CorpsActivity.class);
                startActivity(intent);
            });

            FrameLayout frame = findViewById(R.id.frame);
            frame.addView(boutonCorps, params);
        }

        if ("bilan_secondaire_depuis_quelle_heure_avez_vous_mal".equals(name) || "accouchement_a_quelle_heure_ont_debute_les_contractions".equals(name) ||
        "accouchement_est_ce_que_vous_avez_perdu_les_eaux_si_oui".equals(name)) {
            Button boutonHorloge = new Button(this);
            boutonHorloge.setText(R.string.horloge);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

            boutonHorloge.setOnClickListener(v -> {
                Intent intent = new Intent(VideoActivity.this, HorlogeActivity.class);
                startActivity(intent);
            });

            FrameLayout frame = findViewById(R.id.frame);
            frame.addView(boutonHorloge, params);
        }

        if ("accouchement_quelle_est_la_date_des_dernieres_regles".equals(name) || "accouchement_quelle_est_la_date_prevue_du_terme".equals(name)) {
            Button boutonCalendrier = new Button(this);
            boutonCalendrier.setText(R.string.calendrier);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

            boutonCalendrier.setOnClickListener(v -> {
                Intent intent = new Intent(VideoActivity.this, CalendrierActivity.class);
                startActivity(intent);
            });

            FrameLayout frame = findViewById(R.id.frame);
            frame.addView(boutonCalendrier, params);
        }

        int videoResourceId = getResources().getIdentifier(name, "raw", getPackageName());
        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + videoResourceId;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.setOnCompletionListener(mp -> finish());
        videoView.start();
    }

    public void onClickClavier(View view) {
        Intent intent = new Intent(VideoActivity.this, ClavierActivity.class);
        startActivity(intent);
    }

    public void retour(View view) {
        finish();
    }
}
