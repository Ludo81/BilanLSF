package com.sdis.secours.lsf;

import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;

import com.sdis.secours.lsf.police.AccueilPoliceActivity;
import com.sdis.secours.lsf.pompier.AccueilPompierActivity;

public class HomeActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("Parametrage", MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        RestrictionsManager restrictionsManager = (RestrictionsManager) getSystemService(RESTRICTIONS_SERVICE);
        Bundle restrictions = restrictionsManager.getApplicationRestrictions();
        String mdmChoice = restrictions.getString("defaultEmergencyService", "Disabled");
        if ("Pompier".equals(mdmChoice)) { // Si le choix mdm est pompier
            pompier();
        } else if ("Police".equals(mdmChoice)) { // Si le choix mdm est police
            police();
        } else { // Sinon on prend le choix paramétré de l'appareil
            String defaultEmergencyService = sharedPreferences.getString("defaultEmergencyService", "Pompier");
            if ("Pompier".equals(defaultEmergencyService)) {
                pompier();
            } else if ("Police".equals(defaultEmergencyService)) {
                police();
            } else {
                pompier();
            }
        }
    }

    public void police() {
        Intent intent = new Intent(HomeActivity.this, AccueilPoliceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void samu(View v) {

    }

    public void pompier() {
        Intent intent = new Intent(HomeActivity.this, AccueilPompierActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
