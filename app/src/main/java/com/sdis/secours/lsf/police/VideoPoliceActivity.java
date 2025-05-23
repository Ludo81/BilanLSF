package com.sdis.secours.lsf.police;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.VideoBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class VideoPoliceActivity extends BasePoliceActivity {

    VideoBinding videoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoBinding = VideoBinding.inflate(getLayoutInflater());
        setContentView(videoBinding.getRoot());

        boolean showStop = getIntent().getBooleanExtra("SHOW_STOP", false);
        ImageButton stopButton = findViewById(R.id.stop);
        stopButton.setVisibility(showStop ? View.VISIBLE : View.GONE);

        Logger.write(this, "Chargement Video");
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.lireVideo();
    }

    private void lireVideo() {
        String name = getIntent().getStringExtra("VIDEO_NAME").replace(".mp4", "");

        if ("securite_routiere_montrez_moi_la_vitesse".equals(name)) {
            Button boutonVitesse = new Button(this);
            boutonVitesse.setText(R.string.vitesse);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);

            boutonVitesse.setOnClickListener(v -> {
                Intent intent = new Intent(VideoPoliceActivity.this, VitessePoliceActivity.class);
                startActivity(intent);
            });

            RelativeLayout frame = findViewById(R.id.frame);
            frame.addView(boutonVitesse, params);
        }


        VideoView videoView = findViewById(R.id.videoView);

        try {
            AssetFileDescriptor afd = getAssets().openFd("videos/police/" + name + ".mp4");

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
        Intent intent = new Intent(VideoPoliceActivity.this, ClavierPoliceActivity.class);
        startActivity(intent);
    }

    public void stopVideo(View v) {
        finish();
    }
}
