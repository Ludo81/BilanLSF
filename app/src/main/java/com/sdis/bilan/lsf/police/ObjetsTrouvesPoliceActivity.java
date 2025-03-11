package com.sdis.bilan.lsf.police;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sdis.bilan.lsf.databinding.ObjetsTrouvesBinding;

public class ObjetsTrouvesPoliceActivity extends BasePoliceActivity {

    private ObjetsTrouvesBinding objetsTrouvesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objetsTrouvesBinding = ObjetsTrouvesBinding.inflate(getLayoutInflater());
        setContentView(objetsTrouvesBinding.getRoot());
    }

    public void goToBijoux(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, BijouxPoliceActivity.class);
        startActivity(intent);
    }

    public void goToDocuments(View view) {
        Intent intent = new Intent(ObjetsTrouvesPoliceActivity.this, DocumentsPoliceActivity.class);
        startActivity(intent);
    }
}
