package com.sdis.bilan.lsf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.ComponentActivity;

public class CorpsActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corps);

        ImageView imageView = findViewById(R.id.corps);
        imageView.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP) {
                addPastille(event.getX(), event.getY());
            }
            return true;
        });
    }


    private void addPastille(float x, float y) {
        Context context = getApplicationContext();

        FrameLayout frame = findViewById(R.id.frame);

        View pastille = new View(context);
        pastille.setLayoutParams(new FrameLayout.LayoutParams(50, 50));
        pastille.setBackgroundColor(Color.parseColor("#61DC2A"));

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setColor(Color.parseColor("#61DC2A"));
        pastille.setBackground(shape);

        pastille.setX(x - 10);
        pastille.setY(y - 30);

        frame.addView(pastille);
    }

    public void onClickVideo(View view) {
        Intent intent = new Intent(CorpsActivity.this, VideoActivity.class);
        intent.putExtra("VIDEO_NAME", "bilan_primaire_montrez_moi_ou_vous_avez_mal");
        intent.putExtra("CREATE_BUTTON", false);
        startActivity(intent);
    }

    public void retour(View view) {
        finish();
    }

}
