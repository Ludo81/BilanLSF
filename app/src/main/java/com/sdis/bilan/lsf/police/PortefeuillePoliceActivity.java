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
    private Bitmap bitmap;

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

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.color_picker);
        colorPicker.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                if (x >= 0 && x < bitmap.getWidth() && y >= 0 && y < bitmap.getHeight()) {
                    int color = bitmap.getPixel(x, y);
                    portefeuilleView.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                }
            }
            return true;
        });
    }

    public void monter(View v) {
        argent += 0.01;
        argentView.setText(argent + "€");
    }

    public void descendre(View v) {
        if (argent != 0) {
            argent -= 0.01;
            argentView.setText(argent + "€");
        }

    }

    public void onClickCarteIdentiteElec(View view) {
        if (isCarteIdentiteSelected) {
            carteIdentiteView.setImageResource(R.drawable.carte_identite_elec);
            isCarteIdentiteSelected = false;
        } else {
            carteIdentiteView.setImageResource(R.drawable.carte_identite_elec_select);
            isCarteIdentiteSelected = true;
        }
    }

    public void onClickCarteVitale(View view) {
        if (isCarteVitaleSelected) {
            carteVitaleView.setImageResource(R.drawable.carte_vitale);
            isCarteVitaleSelected = false;
        } else {
            carteVitaleView.setImageResource(R.drawable.carte_vitale_select);
            isCarteVitaleSelected = true;
        }
    }

    public void onClickPermis(View view) {
        if (isPermisSelected) {
            permisView.setImageResource(R.drawable.permis);
            isPermisSelected = false;
        } else {
            permisView.setImageResource(R.drawable.permis_select);
            isPermisSelected = true;
        }
    }

    public void onClickCarteBancaire(View view) {
        if (isCarteBancaireSelected) {
            carteBancaireView.setImageResource(R.drawable.carte_bancaire);
            isCarteBancaireSelected = false;
        } else {
            carteBancaireView.setImageResource(R.drawable.carte_bancaire_select);
            isCarteBancaireSelected = true;
        }
    }
}
