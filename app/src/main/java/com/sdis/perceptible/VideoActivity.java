package com.sdis.perceptible;

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

        String name = getIntent().getStringExtra("VIDEO_NAME");

        if("bilan_primaire_montrez_moi_ou_vous_avez_mal".equals(name)){
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
