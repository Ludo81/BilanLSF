package com.sdis.bilan.lsf.police;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
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
    private Bitmap bitmap;

    private TextView marqueView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        telephoneBinding = TelephoneBinding.inflate(getLayoutInflater());
        setContentView(telephoneBinding.getRoot());

        telephoneView = findViewById(R.id.telephone);

        colorPicker = findViewById(R.id.colorPicker);

        marqueView = findViewById(R.id.marque);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.color_picker);
        colorPicker.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                if (x >= 0 && x < bitmap.getWidth() && y >= 0 && y < bitmap.getHeight()) {
                    int color = bitmap.getPixel(x, y);
                    telephoneView.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                }
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
