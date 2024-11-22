package com.sdis.bilan.lsf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;

public class Home2Activity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);
    }

    public void police(View v) {

    }

    public void samu(View v) {
    
    }

    public void pompier(View v) {
        Intent intent = new Intent(Home2Activity.this, HomeActivity.class);
        startActivity(intent);
    }

}
