package com.sdis.bilan.lsf.police;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.VetementsBinding;

import java.util.List;

public class VetementsPoliceActivity extends BasePoliceActivity {

    private VetementsBinding vetementsBinding;

    private ImageView vetementView;

    private ImageButton precedentView;
    private ImageButton suivantView;

    private ImageView colorPicker;
    int currentColor = Color.BLACK;

    private List<Integer> listeVetements = List.of(R.drawable.pull, R.drawable.bonnet, R.drawable.veste, R.drawable.casquette, R.drawable.echarpe,
            R.drawable.canne, R.drawable.lunettes, R.drawable.parapluie, R.drawable.gants, R.drawable.chapeau);
    int vetementSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vetementsBinding = VetementsBinding.inflate(getLayoutInflater());
        setContentView(vetementsBinding.getRoot());

        vetementView = findViewById(R.id.vetement);

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

                vetementView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
            }
            return true;
        });
    }

    public void vetementPrecedent(View v) {
        if (vetementSelected - 1 != -1) {
            vetementSelected -= 1;
            vetementView.setImageResource(listeVetements.get(vetementSelected));

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

            precedentView.setImageResource(R.drawable.precedent);

            if (vetementSelected == listeVetements.size() - 1) {
                suivantView.setImageResource(0);
            }
        }
    }
}
