package com.sdis.secours.lsf;

import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;

import com.sdis.secours.lsf.police.AccueilPoliceActivity;
import com.sdis.secours.lsf.pompier.AccueilPompierActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("Parametrage", MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Logger.write(this, "Démarrage de SecoursLSF");

        SimpleDateFormat formatDateSimple = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault());
        File dossier = this.getExternalFilesDir(null);
        File logFile = new File(dossier, "logs" + "_" + formatDateSimple.format(new Date()) + ".txt");
        if (dossier != null && dossier.exists() && dossier.isDirectory()) {
            File[] fichiers = dossier.listFiles();

            if (fichiers != null) {
                for (File fichier : fichiers) {
                    if (!fichier.getName().equals(logFile.getName())) {
                        fichier.delete();
                    }
                }
            }
        }

        RestrictionsManager restrictionsManager = (RestrictionsManager) getSystemService(RESTRICTIONS_SERVICE);
        Bundle restrictions = restrictionsManager.getApplicationRestrictions();
        String mdmChoice = restrictions.getString("defaultEmergencyService", "Disabled");
        Logger.write(this, "Récupération de la configuration MDM " + mdmChoice);
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
