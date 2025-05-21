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
import com.sdis.secours.lsf.databinding.VetementsBinding;

import java.util.List;

public class VetementsPoliceActivity extends BasePoliceActivity {

    private VetementsBinding vetementsBinding;

    private ImageView vetementView;

    private ImageButton precedentView;
    private ImageButton suivantView;

    private ImageView colorPicker;
    int currentColor = Color.WHITE;

    boolean asChangedColor = false;

    private final List<Integer> listeVetements = List.of(R.drawable.pull, R.drawable.bonnet, R.drawable.veste, R.drawable.casquette, R.drawable.echarpe,
            R.drawable.canne, R.drawable.parapluie, R.drawable.gants, R.drawable.chapeau, R.drawable.chaussures_homme, R.drawable.chaussures_femme,
            R.drawable.serviette, R.drawable.lunettes);
    int vetementSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vetementsBinding = VetementsBinding.inflate(getLayoutInflater());
        setContentView(vetementsBinding.getRoot());

        Logger.write(this, "Chargement Vetements");

        vetementView = findViewById(R.id.vetement);
        vetementView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);

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
                if (listeVetements.get(vetementSelected) == R.drawable.lunettes) {
                    vetementView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
                } else {
                    vetementView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                }
                asChangedColor = true;
            }
            return true;
        });
    }

    public void vetementPrecedent(View v) {
        if (vetementSelected - 1 != -1) {
            vetementSelected -= 1;
            vetementView.setImageResource(listeVetements.get(vetementSelected));
            if (listeVetements.get(vetementSelected) == R.drawable.lunettes) {
                if (!asChangedColor) {
                    vetementView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                } else {
                    vetementView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
                }
            } else {
                vetementView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
            }
            suivantView.setImageResource(R.drawable.suivant);

            if (vetementSelected == 0) {
                precedentView.setImageResource(0);
            }
        }
    }

    public void vetementSuivant(View v) {
        if (vetementSelected + 1 != listeVetements.size()) {
            vetementSelected += 1;
            vetementView.setImageResource(listeVetements.get(vetementSelected));
            if (listeVetements.get(vetementSelected) == R.drawable.lunettes) {
                if (!asChangedColor) {
                    vetementView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                } else {
                    vetementView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
                }
            } else {
                vetementView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
            }
            precedentView.setImageResource(R.drawable.precedent);

            if (vetementSelected == listeVetements.size() - 1) {
                suivantView.setImageResource(0);
            }
        }
    }

    public void startVideo(View v) {
        Intent intent = new Intent(VetementsPoliceActivity.this, VideoPoliceActivity.class);
        intent.putExtra("VIDEO_NAME", "intervention_quel_objet_avez_vous_perdu_63");
        startActivity(intent);
    }
}
