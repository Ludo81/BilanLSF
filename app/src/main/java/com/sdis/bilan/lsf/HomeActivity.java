package com.sdis.bilan.lsf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;

import com.sdis.bilan.lsf.police.AccueilPoliceActivity;
import com.sdis.bilan.lsf.pompier.AccueilPompierActivity;

public class HomeActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("Parametrage", MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);

        if (sharedPreferences.getBoolean("isPompierDefault", true)) {
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
