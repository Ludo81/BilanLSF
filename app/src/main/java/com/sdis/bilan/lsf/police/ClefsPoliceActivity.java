package com.sdis.bilan.lsf.police;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.ClefsBinding;

import java.util.List;

public class ClefsPoliceActivity extends BasePoliceActivity {

    private ClefsBinding clefsBinding;

    private ImageButton maisonView;
    private ImageButton voitureView;

    private boolean isMaisonSelected = false;
    private boolean isVoitureSelected = false;

    private ImageView marqueView;

    private ImageButton precedentView;
    private ImageButton suivantView;

    private List<Integer> listeMarques = List.of(R.drawable.aixam, R.drawable.alfa_romeo, R.drawable.alpine, R.drawable.aston_martin, R.drawable.audi,
            R.drawable.bentley, R.drawable.bmw, R.drawable.bugatti, R.drawable.buick, R.drawable.cadillac, R.drawable.chery, R.drawable.chevrolet
            , R.drawable.chrysler, R.drawable.citroen, R.drawable.corvette, R.drawable.cupra, R.drawable.dacia, R.drawable.daewoo, R.drawable.daihatsu
            , R.drawable.datsun, R.drawable.dodge, R.drawable.ds, R.drawable.ferrari, R.drawable.fiat, R.drawable.ford, R.drawable.general_motors
            , R.drawable.genesis, R.drawable.gmc, R.drawable.honda, R.drawable.hummer, R.drawable.hyundai, R.drawable.infiniti, R.drawable.isuzu
            , R.drawable.jaguar, R.drawable.jeep, R.drawable.kia, R.drawable.lada, R.drawable.lamborghini, R.drawable.lancia, R.drawable.land_rover
            , R.drawable.lexus, R.drawable.ligier, R.drawable.lotus, R.drawable.maserati, R.drawable.mazda, R.drawable.mc_laren, R.drawable.mercedes
            , R.drawable.mg, R.drawable.mini, R.drawable.mitsubishi, R.drawable.nissan, R.drawable.opel, R.drawable.peugeot, R.drawable.porsche
            , R.drawable.renault, R.drawable.rolls_royce, R.drawable.rolls_royce, R.drawable.saab, R.drawable.seat, R.drawable.skoda, R.drawable.smart
            , R.drawable.subaru, R.drawable.suzuki, R.drawable.tesla, R.drawable.toyota, R.drawable.volkswagen, R.drawable.volvo);
    int marqueSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clefsBinding = ClefsBinding.inflate(getLayoutInflater());
        setContentView(clefsBinding.getRoot());

        maisonView = findViewById(R.id.maison);
        voitureView = findViewById(R.id.voiture);

        marqueView = findViewById(R.id.marque);

        precedentView = findViewById(R.id.marque_precedente);
        suivantView = findViewById(R.id.marque_suivante);
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

    public void selectVoiture(View v) {
        if (isVoitureSelected) {
            voitureView.setBackgroundResource(0);
            isVoitureSelected = false;
        } else {
            voitureView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isVoitureSelected = true;
        }
    }

    public void marquePrecedente(View v) {
        if (marqueSelected - 1 != -1) {
            marqueSelected -= 1;
            marqueView.setImageResource(listeMarques.get(marqueSelected));

            suivantView.setImageResource(R.drawable.suivant);

            if (marqueSelected == 0) {
                precedentView.setImageResource(0);
            }
        }
    }

    public void marqueSuivante(View v) {
        if (marqueSelected + 1 != listeMarques.size()) {
            marqueSelected += 1;
            marqueView.setImageResource(listeMarques.get(marqueSelected));

            precedentView.setImageResource(R.drawable.precedent);

            if (marqueSelected == listeMarques.size() - 1) {
                suivantView.setImageResource(0);
            }
        }
    }
}
