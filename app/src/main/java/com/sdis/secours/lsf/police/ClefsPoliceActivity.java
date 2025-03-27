package com.sdis.secours.lsf.police;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.ClefsBinding;

import java.util.List;

public class ClefsPoliceActivity extends BasePoliceActivity {

    private ClefsBinding clefsBinding;

    private ImageButton maisonView;
    private ImageButton vehiculeView;

    private boolean isMaisonSelected = false;
    private boolean isVehiculeSelected = false;

    private ImageButton vehiculePrecedentView;
    private ImageButton vehiculeSuivantView;

    private ImageView marqueView;
    private TextView marqueTexteView;

    private ImageButton marquePrecedenteView;
    private ImageButton marqueSuivanteView;

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

    private final List<Integer> listeVehicules = List.of(R.drawable.voiture, R.drawable.moto);
    int vehiculeSelected = 0;

    private final List<Integer> listeMarquesVoiture = List.of(R.drawable.aixam, R.drawable.alfa_romeo, R.drawable.alpine, R.drawable.aston_martin, R.drawable.audi,
            R.drawable.bellier, R.drawable.bentley, R.drawable.bmw, R.drawable.bugatti, R.drawable.buick, R.drawable.cadillac, R.drawable.casalini, R.drawable.chatenet
            , R.drawable.chery, R.drawable.chevrolet, R.drawable.chrysler, R.drawable.citroen, R.drawable.corvette, R.drawable.cupra, R.drawable.dacia, R.drawable.daewoo,
            R.drawable.daf, R.drawable.daihatsu, R.drawable.datsun, R.drawable.dodge, R.drawable.ds, R.drawable.ferrari, R.drawable.fiat, R.drawable.ford, R.drawable.general_motors
            , R.drawable.genesis, R.drawable.gmc, R.drawable.honda, R.drawable.hummer, R.drawable.hyundai, R.drawable.infiniti, R.drawable.isuzu, R.drawable.iveco
            , R.drawable.jaguar, R.drawable.jeep, R.drawable.jiayuan, R.drawable.kia, R.drawable.lada, R.drawable.lamborghini, R.drawable.lancia, R.drawable.land_rover
            , R.drawable.lexus, R.drawable.ligier, R.drawable.lotus, R.drawable.man, R.drawable.maserati, R.drawable.mazda, R.drawable.mc_laren, R.drawable.mercedes
            , R.drawable.mg, R.drawable.microcar, R.drawable.mini, R.drawable.mitsubishi, R.drawable.nissan, R.drawable.opel, R.drawable.peugeot, R.drawable.porsche
            , R.drawable.renault, R.drawable.rolls_royce, R.drawable.rolls_royce, R.drawable.saab, R.drawable.scania, R.drawable.seat, R.drawable.skoda, R.drawable.smart
            , R.drawable.ssangyong, R.drawable.subaru, R.drawable.suzuki, R.drawable.tesla, R.drawable.toyota, R.drawable.volkswagen, R.drawable.volvo);
    private final List<Integer> listeMarquesMoto = List.of(R.drawable.aprilia, R.drawable.bmw, R.drawable.ducati, R.drawable.harley_davidson, R.drawable.honda
            , R.drawable.kawasaki, R.drawable.ktm, R.drawable.mash, R.drawable.mbk, R.drawable.moto_guzzi, R.drawable.peugeot, R.drawable.piaggio, R.drawable.suzuki
            , R.drawable.triumph, R.drawable.vespa, R.drawable.yamaha);
    int marqueSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clefsBinding = ClefsBinding.inflate(getLayoutInflater());
        setContentView(clefsBinding.getRoot());

        maisonView = findViewById(R.id.maison);
        vehiculeView = findViewById(R.id.vehicule);

        marqueView = findViewById(R.id.marque);
        marqueTexteView = findViewById(R.id.marqueTexte);

        vehiculePrecedentView = findViewById(R.id.vehicule_precedent);
        vehiculeSuivantView = findViewById(R.id.vehicule_suivant);

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
    }

    public void selectMaison(View v) {
        if (isMaisonSelected) {
            maisonView.setBackgroundResource(0);
            isMaisonSelected = false;
        } else {
            maisonView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isMaisonSelected = true;
        }
    }

    public void selectVehicule(View v) {
        if (isVehiculeSelected) {
            vehiculeView.setBackgroundResource(0);
            isVehiculeSelected = false;
        } else {
            vehiculeView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isVehiculeSelected = true;
        }
    }

    public void vehiculePrecedent(View v) {
        if (vehiculeSelected - 1 != -1) {
            vehiculeSelected -= 1;
            vehiculeView.setImageResource(listeVehicules.get(vehiculeSelected));

            marqueSelected = 0;
            marqueView.setImageResource(listeMarquesVoiture.get(marqueSelected));
            marqueTexteView.setText(getMarqueTexte(listeMarquesVoiture.get(marqueSelected)));
            marquePrecedenteView.setImageResource(0);
            marqueSuivanteView.setImageResource(R.drawable.suivant);

            vehiculeSuivantView.setImageResource(R.drawable.suivant);

            if (vehiculeSelected == 0) {
                vehiculePrecedentView.setImageResource(0);
            }
        }
    }

    public void vehiculeSuivant(View v) {
        if (vehiculeSelected + 1 != listeVehicules.size()) {
            vehiculeSelected += 1;
            vehiculeView.setImageResource(listeVehicules.get(vehiculeSelected));

            marqueSelected = 0;
            marqueView.setImageResource(listeMarquesMoto.get(marqueSelected));
            marqueTexteView.setText(getMarqueTexte(listeMarquesMoto.get(marqueSelected)));
            marquePrecedenteView.setImageResource(0);
            marqueSuivanteView.setImageResource(R.drawable.suivant);

            vehiculePrecedentView.setImageResource(R.drawable.precedent);

            if (vehiculeSelected == listeVehicules.size() - 1) {
                vehiculeSuivantView.setImageResource(0);
            }
        }
    }

    public void marquePrecedente(View v) {
        if (marqueSelected - 1 != -1) {
            marqueSelected -= 1;
            if (vehiculeSelected == 0) {
                marqueView.setImageResource(listeMarquesVoiture.get(marqueSelected));
                marqueTexteView.setText(getMarqueTexte(listeMarquesVoiture.get(marqueSelected)));
            } else {
                marqueView.setImageResource(listeMarquesMoto.get(marqueSelected));
                marqueTexteView.setText(getMarqueTexte(listeMarquesMoto.get(marqueSelected)));
            }

            marqueSuivanteView.setImageResource(R.drawable.suivant);

            if (marqueSelected == 0) {
                marquePrecedenteView.setImageResource(0);
            }
        }
    }

    public void marqueSuivante(View v) {
        if (vehiculeSelected == 0) {
            if (marqueSelected + 1 != listeMarquesVoiture.size()) {
                marqueSelected += 1;
                marqueView.setImageResource(listeMarquesVoiture.get(marqueSelected));
                marqueTexteView.setText(getMarqueTexte(listeMarquesVoiture.get(marqueSelected)));
                marquePrecedenteView.setImageResource(R.drawable.precedent);

                if (marqueSelected == listeMarquesVoiture.size() - 1) {
                    marqueSuivanteView.setImageResource(0);
                }
            }
        } else {
            if (marqueSelected + 1 != listeMarquesMoto.size()) {
                marqueSelected += 1;
                marqueView.setImageResource(listeMarquesMoto.get(marqueSelected));
                marqueTexteView.setText(getMarqueTexte(listeMarquesMoto.get(marqueSelected)));

                marquePrecedenteView.setImageResource(R.drawable.precedent);

                if (marqueSelected == listeMarquesMoto.size() - 1) {
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
