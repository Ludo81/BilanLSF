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

        boolean isPompierDefault = false;
        if (restrictionsManager != null) {
            Bundle restrictions = restrictionsManager.getApplicationRestrictions();
            isPompierDefault = restrictions.getBoolean("isPompierDefault", false);
        }

        if (isPompierDefault || sharedPreferences.getBoolean("isPompierDefault", true)) {
            pompier();
        } else {
            police();
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
