package com.sdis.secours.lsf.police;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.BagagesBinding;

import java.util.List;

public class BagagesPoliceActivity extends BasePoliceActivity {

    private BagagesBinding bagagesBinding;

    private ImageView bagageView;

    private ImageButton precedentView;
    private ImageButton suivantView;

    private ImageView colorPicker;
    int currentColor = Color.WHITE;

    private final List<Integer> listeBagages = List.of(R.drawable.valise, R.drawable.sac, R.drawable.sac_a_dos,
            R.drawable.sac_sport);
    int bagageSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bagagesBinding = BagagesBinding.inflate(getLayoutInflater());
        setContentView(bagagesBinding.getRoot());

        Logger.write(this, "Chargement Bagages");

        bagageView = findViewById(R.id.bagage);
        bagageView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);

        precedentView = findViewById(R.id.precedent);
        suivantView = findViewById(R.id.suivant);

        colorPicker = findViewById(R.id.colorPicker);

        colorPicker.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {

                int viewX = (int) event.getX();
                int viewY = (int) event.getY();

                int viewWidth = colorPicker.getWidth();
                int viewHeight = colorPicker.getHeight();

                Bitmap image = ((BitmapDrawable) colorPicker.getDrawable()).getBitmap();

                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int imageX = (int) ((float) viewX * ((float) imageWidth / (float) viewWidth));
                int imageY = (int) ((float) viewY * ((float) imageHeight / (float) viewHeight));

                try {
                    currentColor = image.getPixel(imageX, imageY);
                } catch (Exception e) {

                }

                bagageView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
            }
            return true;
        });
    }

    public void bagagePrecedent(View v) {
        if (bagageSelected - 1 != -1) {
            bagageSelected -= 1;
            bagageView.setImageResource(listeBagages.get(bagageSelected));
            suivantView.setImageResource(R.drawable.suivant);

            if (bagageSelected == 0) {
                precedentView.setImageResource(0);
            }
        }
    }

    public void bagageSuivant(View v) {
        if (bagageSelected + 1 != listeBagages.size()) {
            bagageSelected += 1;
            bagageView.setImageResource(listeBagages.get(bagageSelected));
            precedentView.setImageResource(R.drawable.precedent);

            if (bagageSelected == listeBagages.size() - 1) {
                suivantView.setImageResource(0);
            }
        }
    }

    public void startVideo(View v) {
        Intent intent = new Intent(BagagesPoliceActivity.this, VideoPoliceActivity.class);
        intent.putExtra("VIDEO_NAME", "intervention_quel_objet_avez_vous_perdu_63");
        startActivity(intent);
    }
}
