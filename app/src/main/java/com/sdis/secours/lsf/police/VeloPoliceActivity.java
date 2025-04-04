package com.sdis.secours.lsf.police;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.VeloBinding;

public class VeloPoliceActivity extends BasePoliceActivity {

    private VeloBinding veloBinding;

    private ImageView veloView;

    private ImageView colorPicker;
    int currentColor = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        veloBinding = VeloBinding.inflate(getLayoutInflater());
        setContentView(veloBinding.getRoot());

        Logger.write(this, "Chargement Velo");

        veloView = findViewById(R.id.velo);

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

                veloView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
            }
            return true;
        });

    }
}
