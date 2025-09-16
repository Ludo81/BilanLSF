package com.sdis.secours.lsf.police;

import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.ObjetsTrouvesBinding;

public class ObjetsTrouvesPoliceActivity extends BasePoliceActivity {

    private ObjetsTrouvesBinding objetsTrouvesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objetsTrouvesBinding = ObjetsTrouvesBinding.inflate(getLayoutInflater());
        setContentView(objetsTrouvesBinding.getRoot());

        Logger.write(this, "Chargement Objets trouves");

        RestrictionsManager restrictionsManager = (RestrictionsManager) getSystemService(RESTRICTIONS_SERVICE);
        Bundle restrictions = restrictionsManager.getApplicationRestrictions();
        String mdmLicence = restrictions.getString("licenceUnlockModules");
        Logger.write(this, "Récupération de la configuration MDM <licenceUnlockModules> " + mdmLicence);

        SharedPreferences sharedPreferences = getSharedPreferences("Parametrage", MODE_PRIVATE);

        ImageButton telephoneButton = findViewById(R.id.telephone);
        GridLayout gridLayoutTelephone = (GridLayout) telephoneButton.getParent();

        if (!"AB7F-92KD-ZX4L-MQ8P".equals(mdmLicence) && !sharedPreferences.getBoolean("isLicenceValide", false)) {
            gridLayoutTelephone.removeView(telephoneButton);
        }

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
