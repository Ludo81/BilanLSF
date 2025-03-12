package com.sdis.bilan.lsf.police;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.TelephoneBinding;

public class TelephonePoliceActivity extends BasePoliceActivity {

    private TelephoneBinding telephoneBinding;

    private ImageView telephoneView;

    private ImageView colorPicker;
    int currentColor = Color.BLACK;

    private TextView marqueView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        telephoneBinding = TelephoneBinding.inflate(getLayoutInflater());
        setContentView(telephoneBinding.getRoot());

        telephoneView = findViewById(R.id.telephone);

        colorPicker = findViewById(R.id.colorPicker);

        marqueView = findViewById(R.id.marque);

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

                telephoneView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
            }
            return true;
        });
    }

    public void precedent(View v) {
        marqueView.setText("précédent");
    }

    public void suivant(View v) {
        marqueView.setText("suivant");
    }
}
