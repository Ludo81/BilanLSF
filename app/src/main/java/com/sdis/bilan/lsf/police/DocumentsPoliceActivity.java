package com.sdis.bilan.lsf.police;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.DocumentsBinding;

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

        carteIdentiteView = findViewById(R.id.carte_identite_elec);
        carteVitaleView = findViewById(R.id.carte_vitale);
        passeportView = findViewById(R.id.passeport);
        permisView = findViewById(R.id.permis);
        carteBancaireView = findViewById(R.id.carte_bancaire);
        chequeView = findViewById(R.id.cheque);
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

    public void onClickPasseport(View view) {
        if (isPasseportSelected) {
            passeportView.setImageResource(R.drawable.passeport);
            isPasseportSelected = false;
        } else {
            passeportView.setImageResource(R.drawable.passeport_select);
            isPasseportSelected = true;
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

    public void onClickCheque(View view) {
        if (isChequeSelected) {
            chequeView.setImageResource(R.drawable.cheque);
            isChequeSelected = false;
        } else {
            chequeView.setImageResource(R.drawable.cheque_select);
            isChequeSelected = true;
        }
    }
}
