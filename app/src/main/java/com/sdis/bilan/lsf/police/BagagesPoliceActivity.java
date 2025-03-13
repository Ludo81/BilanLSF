package com.sdis.bilan.lsf.police;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.BagagesBinding;

import java.util.List;

public class BagagesPoliceActivity extends BasePoliceActivity {

    private BagagesBinding bagagesBinding;

    private ImageView bagageView;

    private ImageView colorPicker;
    int currentColor = Color.BLACK;

    private List<Integer> listeBagages = List.of(R.drawable.valise, R.drawable.sac, R.drawable.sac_a_dos);
    int bagageSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bagagesBinding = BagagesBinding.inflate(getLayoutInflater());
        setContentView(bagagesBinding.getRoot());

        bagageView = findViewById(R.id.bagage);

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

                bagageView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
            }
            return true;
        });
    }

    public void bagagePrecedent(View v) {
        if (bagageSelected - 1 != -1) {
            bagageSelected -= 1;
            bagageView.setImageResource(listeBagages.get(bagageSelected));
        }
    }

    public void bagageSuivant(View v) {
        if (bagageSelected + 1 != listeBagages.size()) {
            bagageSelected += 1;
            bagageView.setImageResource(listeBagages.get(bagageSelected));
        }
    }

}
