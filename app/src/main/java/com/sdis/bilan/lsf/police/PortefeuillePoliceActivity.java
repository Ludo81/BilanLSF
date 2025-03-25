package com.sdis.bilan.lsf.police;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.PortefeuilleBinding;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PortefeuillePoliceActivity extends BasePoliceActivity {

    private PortefeuilleBinding portefeuilleBinding;

    private TextView argentView;

    DecimalFormat df = new DecimalFormat("#0.00");
    private BigDecimal argent = new BigDecimal("10.00");
    private BigDecimal increment = new BigDecimal("0.01");
    private int compteur = 0;

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

    private ImageButton monterView;
    private ImageButton descendreView;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable incrementRunnableUp = new Runnable() {
        @Override
        public void run() {
            monter(monterView);
            compteur += 1;
            if (compteur == 100) {
                increment = new BigDecimal("0.10");
            } else if (compteur == 200) {
                increment = new BigDecimal("1.00");
            } else if (compteur == 300) {
                increment = new BigDecimal("5.00");
            }
            handler.postDelayed(this, 1);
        }
    };

    private final Runnable incrementRunnableDown = new Runnable() {
        @Override
        public void run() {
            descendre(descendreView);
            compteur += 1;
            if (compteur == 100) {
                increment = new BigDecimal("0.10");
            } else if (compteur == 200) {
                increment = new BigDecimal("1.00");
            } else if (compteur == 300) {
                increment = new BigDecimal("5.00");
            }
            handler.postDelayed(this, 1);
        }
    };

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

        monterView = findViewById(R.id.monter);
        descendreView = findViewById(R.id.descendre);

        monterView.setOnLongClickListener(v -> {
            handler.post(incrementRunnableUp);
            return true;
        });

        monterView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                handler.removeCallbacks(incrementRunnableUp);
                increment = new BigDecimal("0.01");
                compteur = 0;
            }
            return false;
        });

        descendreView.setOnLongClickListener(v -> {
            handler.post(incrementRunnableDown);
            return true;
        });

        descendreView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                handler.removeCallbacks(incrementRunnableDown);
                increment = new BigDecimal("0.01");
                compteur = 0;
            }
            return false;
        });

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
        descendreView.setImageResource(R.drawable.descendre);
        argent = argent.abs().add(increment);
        argentView.setText(df.format(argent.abs()) + " €");
    }

    public void descendre(View v) {
        if (!df.format(argent.abs()).equals("0,00") && argent.abs().subtract(increment).signum() != -1) {
            argent = argent.abs().subtract(increment);
            argentView.setText(df.format(argent.abs()) + " €");
            if (df.format(argent.abs()).equals("0,00")) {
                descendreView.setImageResource(0);
            }
        } else {
            argentView.setText("0,00 €");
            argent = new BigDecimal("0.00");
            descendreView.setImageResource(0);
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
