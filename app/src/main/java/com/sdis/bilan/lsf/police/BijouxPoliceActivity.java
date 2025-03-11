package com.sdis.bilan.lsf.police;

import android.os.Bundle;
import android.widget.Button;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.BijouxBinding;

public class BijouxPoliceActivity extends BasePoliceActivity {

    private BijouxBinding bijouxBinding;

    Button or;

    Button orRose;

    Button argent;

    Button noir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bijouxBinding = BijouxBinding.inflate(getLayoutInflater());
        setContentView(bijouxBinding.getRoot());

        or = findViewById(R.id.or);
        or.setBackgroundResource(R.drawable.bouton_or);
        or.setOnClickListener(v -> {
            this.resetBoutons();
            or.setBackgroundResource(R.drawable.bouton_or_selection);
        });

        orRose = findViewById(R.id.orRose);
        orRose.setBackgroundResource(R.drawable.bouton_or_rose);
        orRose.setOnClickListener(v -> {
            this.resetBoutons();
            orRose.setBackgroundResource(R.drawable.bouton_or_rose_selection);
        });

        argent = findViewById(R.id.argent);
        argent.setBackgroundResource(R.drawable.bouton_argent);
        argent.setOnClickListener(v -> {
            this.resetBoutons();
            argent.setBackgroundResource(R.drawable.bouton_argent_selection);
        });

        noir = findViewById(R.id.noir);
        noir.setBackgroundResource(R.drawable.bouton_noire_selection);
        noir.setOnClickListener(v -> {
            this.resetBoutons();
            noir.setBackgroundResource(R.drawable.bouton_noire_selection);
        });
    }

    private void resetBoutons() {
        or.setBackgroundResource(R.drawable.bouton_or);
        orRose.setBackgroundResource(R.drawable.bouton_or_rose);
        argent.setBackgroundResource(R.drawable.bouton_argent);
        noir.setBackgroundResource(R.drawable.bouton_noire);
    }
}
