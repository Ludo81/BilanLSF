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
import android.widget.TextView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.FuiteBinding;

import java.util.List;

public class FuitePoliceActivity extends BasePoliceActivity {

    private FuiteBinding fuiteBinding;

    private ImageView personneView;
    private int nombrePersonnes = 1;
    private TextView nombrePersonnesView;

    private ImageView vehiculeView;

    private ImageButton precedentView;
    private ImageButton suivantView;

    private ImageView colorPicker;
    int currentColor = Color.WHITE;

    boolean asChangedColor = false;

    private final List<Integer> listeVehicules = List.of(R.drawable.voiture, R.drawable.scooter, R.drawable.trotinette,
            R.drawable.skate, R.drawable.velo, R.drawable.personne_seule);
    int vehiculeSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fuiteBinding = FuiteBinding.inflate(getLayoutInflater());
        setContentView(fuiteBinding.getRoot());

        Logger.write(this, "Chargement Fuite");

        vehiculeView = findViewById(R.id.vehicule);
        vehiculeView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);

        precedentView = findViewById(R.id.precedent);
        suivantView = findViewById(R.id.suivant);

        personneView = findViewById(R.id.personne);
        nombrePersonnesView = findViewById(R.id.nombrePersonnes);

        colorPicker = findViewById(R.id.colorPicker);

        colorPicker.setOnTouchListener((v, event) -> {

            if (vehiculeSelected != listeVehicules.size() - 1 && (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN)) {

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

                if (listeVehicules.get(vehiculeSelected) == R.drawable.velo) {
                    vehiculeView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
                } else {
                    vehiculeView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                }
                asChangedColor = true;
            }
            return true;
        });
    }

    public void augmenterNombrePersonnes(View v) {
        personneView.setImageResource(R.drawable.personnes);
        nombrePersonnes += 1;
        nombrePersonnesView.setText(String.valueOf(nombrePersonnes));
    }

    public void diminuerNombrePersonnes(View v) {
        if (nombrePersonnes - 1 != 0) {
            nombrePersonnes -= 1;
            nombrePersonnesView.setText(String.valueOf(nombrePersonnes));
            if (nombrePersonnes == 1) {
                personneView.setImageResource(R.drawable.personne_seule);
            }
        }
    }

    public void vehiculePrecedent(View view) {
        if (vehiculeSelected - 1 != -1) {
            vehiculeSelected -= 1;
            vehiculeView.setImageResource(listeVehicules.get(vehiculeSelected));

            if (listeVehicules.get(vehiculeSelected) == R.drawable.velo) {
                if (!asChangedColor) {
                    vehiculeView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                } else {
                    vehiculeView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
                }
            } else {
                vehiculeView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
            }

            colorPicker.setBackgroundResource(R.drawable.border2);
            colorPicker.setImageResource(R.drawable.color_picker);

            suivantView.setImageResource(R.drawable.suivant);

            if (vehiculeSelected == 0) {
                precedentView.setImageResource(0);
            }
        }
    }

    public void vehiculeSuivant(View v) {
        if (vehiculeSelected + 1 != listeVehicules.size()) {
            vehiculeSelected += 1;
            vehiculeView.setImageResource(listeVehicules.get(vehiculeSelected));

            if (listeVehicules.get(vehiculeSelected) == R.drawable.velo) {
                if (!asChangedColor) {
                    vehiculeView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                } else {
                    vehiculeView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
                }
            } else {
                vehiculeView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
            }

            precedentView.setImageResource(R.drawable.precedent);

            if (listeVehicules.get(vehiculeSelected) == R.drawable.personne_seule) {
                suivantView.setImageResource(0);
                vehiculeView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                colorPicker.setBackgroundResource(0);
                colorPicker.setImageResource(0);
            }
        }
    }

    public void startVideoCombienPersonnes(View v) {
        Intent intent = new Intent(FuitePoliceActivity.this, VideoPoliceActivity.class);
        intent.putExtra("VIDEO_NAME", "intervention_combien__de_personnes_63");
        startActivity(intent);
    }

    public void startVideoCommentPartis(View v) {
        Intent intent = new Intent(FuitePoliceActivity.this, VideoPoliceActivity.class);
        intent.putExtra("VIDEO_NAME", "intervention_comment_sont_ils_partis_63");
        startActivity(intent);
    }
}
