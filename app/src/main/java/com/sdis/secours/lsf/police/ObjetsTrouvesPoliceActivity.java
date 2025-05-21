package com.sdis.secours.lsf.police;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.databinding.ObjetsTrouvesBinding;

public class ObjetsTrouvesPoliceActivity extends BasePoliceActivity {

    private ObjetsTrouvesBinding objetsTrouvesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objetsTrouvesBinding = ObjetsTrouvesBinding.inflate(getLayoutInflater());
        setContentView(objetsTrouvesBinding.getRoot());

        Logger.write(this, "Chargement Objets trouves");

        startVideo(null);
    }

    public void startVideo(View v) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, VideoPoliceActivity.class);
        intent.putExtra("VIDEO_NAME", "intervention_quel_objet_avez_vous_perdu_63");
        intent.putExtra("SHOW_STOP", true);
        startActivity(intent);
    }

    public void goToBijoux(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, BijouxPoliceActivity.class);
        startActivity(intent);
    }

    public void goToBagages(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, BagagesPoliceActivity.class);
        startActivity(intent);
    }

    public void goToDocuments(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, DocumentsPoliceActivity.class);
        startActivity(intent);
    }

    public void goToClefs(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, ClefsPoliceActivity.class);
        startActivity(intent);
    }

    public void goToVelo(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, RiderPoliceActivity.class);
        startActivity(intent);
    }

    public void goToTelephone(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, TelephonePoliceActivity.class);
        startActivity(intent);
    }

    public void goToPortefeuille(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, PortefeuillePoliceActivity.class);
        startActivity(intent);
    }

    public void goToVetements(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, VetementsPoliceActivity.class);
        startActivity(intent);
    }
}
