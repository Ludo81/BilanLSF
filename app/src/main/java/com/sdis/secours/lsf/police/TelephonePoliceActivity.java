package com.sdis.secours.lsf.police;

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

import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.TelephoneBinding;

import java.util.List;

public class TelephonePoliceActivity extends BasePoliceActivity {

    private TelephoneBinding telephoneBinding;

    private ImageView objetView;

    private ImageView colorPicker;
    int currentColor = Color.BLACK;

    private ImageView marqueView;
    private TextView marqueTexteView;

    private ImageButton objetPrecedentView;
    private ImageButton objetSuivantView;

    private ImageButton marquePrecedenteView;
    private ImageButton marqueSuivanteView;

    private List<Integer> listeObjets = List.of(R.drawable.telephone, R.drawable.ordinateur);
    int objetSelected = 0;

    private List<Integer> listeMarquesTelephone = List.of(R.drawable.apple, R.drawable.asus, R.drawable.cat, R.drawable.crosscall, R.drawable.fairphone,
            R.drawable.google_pixel, R.drawable.hammer, R.drawable.honor, R.drawable.huawei, R.drawable.motorola, R.drawable.nokia, R.drawable.oneplus, R.drawable.oppo
            , R.drawable.realme, R.drawable.samsung, R.drawable.sony, R.drawable.vivo, R.drawable.wiko, R.drawable.xiaomi);
    private List<Integer> listeMarquesOrdinateur = List.of(R.drawable.asus, R.drawable.hp, R.drawable.lenovo, R.drawable.acer, R.drawable.apple, R.drawable.dell, R.drawable.fujitsu, R.drawable.msi, R.drawable.razer);
    int marqueSelected = 0;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable incrementRunnableUp = new Runnable() {
        @Override
        public void run() {
            marqueSuivante(marqueSuivanteView);
            handler.postDelayed(this, 50);
        }
    };

    private final Runnable incrementRunnableDown = new Runnable() {
        @Override
        public void run() {
            marquePrecedente(marquePrecedenteView);
            handler.postDelayed(this, 50);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        telephoneBinding = TelephoneBinding.inflate(getLayoutInflater());
        setContentView(telephoneBinding.getRoot());

        objetView = findViewById(R.id.objet);

        colorPicker = findViewById(R.id.colorPicker);

        marqueView = findViewById(R.id.marque);
        marqueTexteView = findViewById(R.id.marqueTexte);

        objetPrecedentView = findViewById(R.id.objet_precedent);
        objetSuivantView = findViewById(R.id.objet_suivant);

        marquePrecedenteView = findViewById(R.id.marque_precedente);
        marqueSuivanteView = findViewById(R.id.marque_suivante);

        marqueSuivanteView.setOnLongClickListener(v -> {
            handler.post(incrementRunnableUp);
            return true;
        });

        marqueSuivanteView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                handler.removeCallbacks(incrementRunnableUp);
            }
            return false;
        });

        marquePrecedenteView.setOnLongClickListener(v -> {
            handler.post(incrementRunnableDown);
            return true;
        });

        marquePrecedenteView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                handler.removeCallbacks(incrementRunnableDown);
            }
            return false;
        });

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
            marqueTexteView.setText(getMarqueTexte(listeMarquesTelephone.get(marqueSelected)));
            marquePrecedenteView.setImageResource(0);
            marqueSuivanteView.setImageResource(R.drawable.suivant);

            objetSuivantView.setImageResource(R.drawable.suivant);

            if (objetSelected == 0) {
                objetPrecedentView.setImageResource(0);
            }
        }
    }

    public void objetSuivant(View v) {
        if (objetSelected + 1 != listeObjets.size()) {
            objetSelected += 1;
            objetView.setImageResource(listeObjets.get(objetSelected));

            marqueSelected = 0;
            marqueView.setImageResource(listeMarquesOrdinateur.get(marqueSelected));
            marqueTexteView.setText(getMarqueTexte(listeMarquesOrdinateur.get(marqueSelected)));
            marquePrecedenteView.setImageResource(0);
            marqueSuivanteView.setImageResource(R.drawable.suivant);

            objetPrecedentView.setImageResource(R.drawable.precedent);

            if (objetSelected == listeObjets.size() - 1) {
                objetSuivantView.setImageResource(0);
            }
        }
    }

    public void marquePrecedente(View v) {
        if (marqueSelected - 1 != -1) {
            marqueSelected -= 1;
            if (objetSelected == 0) {
                marqueView.setImageResource(listeMarquesTelephone.get(marqueSelected));
                marqueTexteView.setText(getMarqueTexte(listeMarquesTelephone.get(marqueSelected)));
            } else {
                marqueView.setImageResource(listeMarquesOrdinateur.get(marqueSelected));
                marqueTexteView.setText(getMarqueTexte(listeMarquesOrdinateur.get(marqueSelected)));
            }

            marqueSuivanteView.setImageResource(R.drawable.suivant);

            if (marqueSelected == 0) {
                marquePrecedenteView.setImageResource(0);
            }
        }
    }

    public void marqueSuivante(View v) {
        if (objetSelected == 0) {
            if (marqueSelected + 1 != listeMarquesTelephone.size()) {
                marqueSelected += 1;
                marqueView.setImageResource(listeMarquesTelephone.get(marqueSelected));
                marqueTexteView.setText(getMarqueTexte(listeMarquesTelephone.get(marqueSelected)));
                marquePrecedenteView.setImageResource(R.drawable.precedent);

                if (marqueSelected == listeMarquesTelephone.size() - 1) {
                    marqueSuivanteView.setImageResource(0);
                }
            }
        } else {
            if (marqueSelected + 1 != listeMarquesOrdinateur.size()) {
                marqueSelected += 1;
                marqueView.setImageResource(listeMarquesOrdinateur.get(marqueSelected));
                marqueTexteView.setText(getMarqueTexte(listeMarquesOrdinateur.get(marqueSelected)));

                marquePrecedenteView.setImageResource(R.drawable.precedent);

                if (marqueSelected == listeMarquesOrdinateur.size() - 1) {
                    marqueSuivanteView.setImageResource(0);
                }
            }
        }

    }

    private String getMarqueTexte(int marque) {
        String resourceName = getResources().getResourceEntryName(marque);
        String s1 = resourceName.substring(0, 1).toUpperCase();
        String nameCapitalized = s1 + resourceName.substring(1);
        nameCapitalized = nameCapitalized.replace("_", " ");
        return nameCapitalized;
    }
}
