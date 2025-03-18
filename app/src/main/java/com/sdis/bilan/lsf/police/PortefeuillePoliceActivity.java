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
import com.sdis.bilan.lsf.databinding.PortefeuilleBinding;

public class PortefeuillePoliceActivity extends BasePoliceActivity {

    private PortefeuilleBinding portefeuilleBinding;

    private TextView argentView;

    private double argent = 10;

    private ImageView carteIdentiteView;
    private ImageView carteVitaleView;
    private ImageView permisView;
    private ImageView carteBancaireView;

    private boolean isCarteIdentiteSelected = false;
    private boolean isCarteVitaleSelected = false;
    private boolean isPermisSelected = false;
    private boolean isCarteBancaireSelected = false;

    private ImageView portefeuilleView;
    private ImageView colorPicker;
    int currentColor = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        portefeuilleBinding = PortefeuilleBinding.inflate(getLayoutInflater());
        setContentView(portefeuilleBinding.getRoot());

        argentView = findViewById(R.id.argent);

        carteIdentiteView = findViewById(R.id.carte_identite_elec);
        carteVitaleView = findViewById(R.id.carte_vitale);
        permisView = findViewById(R.id.permis);
        carteBancaireView = findViewById(R.id.carte_bancaire);

        portefeuilleView = findViewById(R.id.portefeuille);

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

                portefeuilleView.setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
            }
            return true;
        });
    }

    public void monter(View v) {
        argent += 0.01;
        argentView.setText(String.format("%.2f€", argent));
    }

    public void descendre(View v) {
        if (argent != 0) {
            argent -= 0.01;
            argentView.setText(String.format("%.2f€", argent));
        }

    }

    public void onClickCarteIdentiteElec(View view) {
        if (isCarteIdentiteSelected) {
            carteIdentiteView.setBackgroundResource(0);
            isCarteIdentiteSelected = false;
        } else {
            carteIdentiteView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isCarteIdentiteSelected = true;
        }
    }

    public void onClickCarteVitale(View view) {
        if (isCarteVitaleSelected) {
            carteVitaleView.setBackgroundResource(0);
            isCarteVitaleSelected = false;
        } else {
            carteVitaleView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isCarteVitaleSelected = true;
        }
    }

    public void onClickPermis(View view) {
        if (isPermisSelected) {
            permisView.setBackgroundResource(0);
            isPermisSelected = false;
        } else {
            permisView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isPermisSelected = true;
        }
    }

    public void onClickCarteBancaire(View view) {
        if (isCarteBancaireSelected) {
            carteBancaireView.setBackgroundResource(0);
            isCarteBancaireSelected = false;
        } else {
            carteBancaireView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isCarteBancaireSelected = true;
        }
    }
}
