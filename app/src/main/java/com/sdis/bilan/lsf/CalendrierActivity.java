package com.sdis.bilan.lsf;

import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;

public class CalendrierActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendrier);
    }

    public void retour(View view){
        finish();
    }
}
