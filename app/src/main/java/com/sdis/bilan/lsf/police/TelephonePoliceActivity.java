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
import com.sdis.bilan.lsf.databinding.TelephoneBinding;

import java.util.List;

public class TelephonePoliceActivity extends BasePoliceActivity {

    private TelephoneBinding telephoneBinding;

    private ImageView objetView;

    private ImageView colorPicker;
    int currentColor = Color.BLACK;

    private ImageView marqueView;

    private List<Integer> listeObjets = List.of(R.drawable.telephone, R.drawable.ordinateur);
    int objetSelected = 0;

    private List<Integer> listeMarquesTelephone = List.of(R.drawable.apple, R.drawable.asus, R.drawable.cat, R.drawable.crosscall, R.drawable.fairphone,
            R.drawable.google_pixel, R.drawable.hammer, R.drawable.honor, R.drawable.huawei, R.drawable.motorola, R.drawable.nokia, R.drawable.oneplus, R.drawable.oppo
            , R.drawable.realme, R.drawable.samsung, R.drawable.sony, R.drawable.vivo, R.drawable.wiko, R.drawable.xiaomi);
    private List<Integer> listeMarquesOrdinateur = List.of(R.drawable.asus, R.drawable.hp, R.drawable.lenovo, R.drawable.acer, R.drawable.apple, R.drawable.dell, R.drawable.fujitsu, R.drawable.msi, R.drawable.razer);
    int marqueSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        telephoneBinding = TelephoneBinding.inflate(getLayoutInflater());
        setContentView(telephoneBinding.getRoot());

        objetView = findViewById(R.id.objet);

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

                objetView.setColorFilter(currentColor, PorterDuff.Mode.SRC_IN);
            }
            return true;
        });
    }

    public void objetPrecedent(View v) {
        if (objetSelected - 1 != -1) {
            objetSelected -= 1;
            objetView.setImageResource(listeObjets.get(objetSelected));

            marqueSelected = 0;
            marqueView.setImageResource(listeMarquesTelephone.get(marqueSelected));
        }
    }

    public void objetSuivant(View v) {
        if (objetSelected + 1 != listeObjets.size()) {
            objetSelected += 1;
            objetView.setImageResource(listeObjets.get(objetSelected));

            marqueSelected = 0;
            marqueView.setImageResource(listeMarquesOrdinateur.get(marqueSelected));
        }
    }

    public void marquePrecedente(View v) {
        if (marqueSelected - 1 != -1) {
            marqueSelected -= 1;
            if (objetSelected == 0) {
                marqueView.setImageResource(listeMarquesTelephone.get(marqueSelected));
            } else {
                marqueView.setImageResource(listeMarquesOrdinateur.get(marqueSelected));
            }
        }
    }

    public void marqueSuivante(View v) {
        if (objetSelected == 0) {
            if (marqueSelected + 1 != listeMarquesTelephone.size()) {
                marqueSelected += 1;
                marqueView.setImageResource(listeMarquesTelephone.get(marqueSelected));
            }
        } else {
            if (marqueSelected + 1 != listeMarquesOrdinateur.size()) {
                marqueSelected += 1;
                marqueView.setImageResource(listeMarquesOrdinateur.get(marqueSelected));
            }
        }

    }
}
