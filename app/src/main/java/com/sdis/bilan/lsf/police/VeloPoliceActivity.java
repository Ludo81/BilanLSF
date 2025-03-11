package com.sdis.bilan.lsf.police;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.VeloBinding;

public class VeloPoliceActivity extends BasePoliceActivity {

    private VeloBinding veloBinding;

    private ImageView veloView;

    private ImageView colorPicker;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        veloBinding = VeloBinding.inflate(getLayoutInflater());
        setContentView(veloBinding.getRoot());

        veloView = findViewById(R.id.velo);

        colorPicker = findViewById(R.id.colorPicker);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.color_picker);
        colorPicker.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                if (x >= 0 && x < bitmap.getWidth() && y >= 0 && y < bitmap.getHeight()) {
                    int color = bitmap.getPixel(x, y);
                    veloView.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                }
            }
            return true;
        });
    }
}
