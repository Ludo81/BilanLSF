package com.sdis.secours.lsf.police;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.DocumentsBinding;

public class DocumentsPoliceActivity extends BasePoliceActivity {

    private DocumentsBinding documentsBinding;

    private ImageView carteIdentiteView;
    private ImageView carteVitaleView;
    private ImageView passeportView;
    private ImageView permisView;
    private ImageView carteBancaireView;
    private ImageView chequeView;

    private boolean isCarteIdentiteSelected = false;
    private boolean isCarteVitaleSelected = false;
    private boolean isPasseportSelected = false;
    private boolean isPermisSelected = false;
    private boolean isCarteBancaireSelected = false;
    private boolean isChequeSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        documentsBinding = DocumentsBinding.inflate(getLayoutInflater());
        setContentView(documentsBinding.getRoot());

        Logger.write(this, "Chargement Documents");

        carteIdentiteView = findViewById(R.id.carte_identite_elec);
        carteVitaleView = findViewById(R.id.carte_vitale);
        passeportView = findViewById(R.id.passeport);
        permisView = findViewById(R.id.permis);
        carteBancaireView = findViewById(R.id.carte_bancaire);
        chequeView = findViewById(R.id.cheque);
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

    public void onClickPasseport(View view) {
        if (isPasseportSelected) {
            passeportView.setBackgroundResource(0);
            isPasseportSelected = false;
        } else {
            passeportView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isPasseportSelected = true;
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

    public void onClickCheque(View view) {
        if (isChequeSelected) {
            chequeView.setBackgroundResource(0);
            isChequeSelected = false;
        } else {
            chequeView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isChequeSelected = true;
        }
    }

    public void startVideo(View v) {
        Intent intent = new Intent(DocumentsPoliceActivity.this, VideoPoliceActivity.class);
        intent.putExtra("VIDEO_NAME", "intervention_quel_objet_avez_vous_perdu_63");
        startActivity(intent);
    }
}
