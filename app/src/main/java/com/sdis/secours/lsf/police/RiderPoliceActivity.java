package com.sdis.secours.lsf.police;

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
import com.sdis.secours.lsf.databinding.RiderBinding;

import java.util.List;

public class RiderPoliceActivity extends BasePoliceActivity {

    private RiderBinding riderBinding;

    private ImageView riderView;

    private ImageButton precedentView;
    private ImageButton suivantView;

    private ImageView colorPicker;
    int currentColor = Color.GRAY;

    private final List<Integer> listeRiders = List.of(R.drawable.velo, R.drawable.trotinette, R.drawable.skate);
    int riderSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        riderBinding = RiderBinding.inflate(getLayoutInflater());
        setContentView(riderBinding.getRoot());

        Logger.write(this, "Chargement Rider");

        riderView = findViewById(R.id.rider);
        riderView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);

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

                if (listeRiders.get(riderSelected) == R.drawable.velo) {
                    riderView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
                } else {
                    riderView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                }
            }
            return true;
        });
    }

    public void riderPrecedent(View v) {
        if (riderSelected - 1 != -1) {
            riderSelected -= 1;
            riderView.setImageResource(listeRiders.get(riderSelected));
            suivantView.setImageResource(R.drawable.suivant);

            if (listeRiders.get(riderSelected) == R.drawable.velo) {
                riderView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
            } else {
                riderView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
            }

            if (riderSelected == 0) {
                precedentView.setImageResource(0);
            }
        }
    }

    public void riderSuivant(View v) {
        if (riderSelected + 1 != listeRiders.size()) {
            riderSelected += 1;
            riderView.setImageResource(listeRiders.get(riderSelected));
            precedentView.setImageResource(R.drawable.precedent);

            if (listeRiders.get(riderSelected) == R.drawable.velo) {
                riderView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
            } else {
                riderView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
            }

            if (riderSelected == listeRiders.size() - 1) {
                suivantView.setImageResource(0);
            }
        }
    }
}
