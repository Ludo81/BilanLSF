package com.sdis.secours.lsf.police;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.BijouxBinding;

public class BijouxPoliceActivity extends BasePoliceActivity {

    private BijouxBinding bijouxBinding;

    Button or;
    Button orRose;
    Button argent;
    Button noir;

    private ImageButton bagueView;
    private ImageButton collierView;
    private ImageButton montreView;

    private boolean isBagueSelected = false;
    private boolean isCollierSelected = false;
    private boolean isMontreSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bijouxBinding = BijouxBinding.inflate(getLayoutInflater());
        setContentView(bijouxBinding.getRoot());

        bagueView = findViewById(R.id.bague);
        collierView = findViewById(R.id.collier);
        montreView = findViewById(R.id.montre);

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

    public void selectBague(View v) {
        if (isBagueSelected) {
            bagueView.setBackgroundResource(0);
            isBagueSelected = false;
        } else {
            bagueView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isBagueSelected = true;
        }
    }

    public void selectCollier(View v) {
        if (isCollierSelected) {
            collierView.setBackgroundResource(0);
            isCollierSelected = false;
        } else {
            collierView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isCollierSelected = true;
        }
    }

    public void selectMontre(View v) {
        if (isMontreSelected) {
            montreView.setBackgroundResource(0);
            isMontreSelected = false;
        } else {
            montreView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isMontreSelected = true;
        }
    }
}
