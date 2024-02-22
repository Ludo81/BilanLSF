package com.sdis.bilan.lsf;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.ComponentActivity;

public class DessinActivity extends ComponentActivity {

    Button noir;

    Button rouge;

    Button vert;

    Button bleu;

    Button jaune;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dessin);

        DessinView dessinView = findViewById(R.id.dessinView);

        ImageButton effacerBouton = findViewById(R.id.effacer);
        effacerBouton.setOnClickListener(v -> dessinView.clearDrawing());

        noir = findViewById(R.id.noir);
        noir.setBackgroundResource(R.drawable.bouton_noire_selection);
        noir.setOnClickListener(v -> {
            this.resetBoutons();
            dessinView.changeColor(Color.BLACK);
            noir.setBackgroundResource(R.drawable.bouton_noire_selection);
        });


        rouge = findViewById(R.id.rouge);
        rouge.setBackgroundResource(R.drawable.bouton_rouge);
        rouge.setOnClickListener(v -> {
            this.resetBoutons();
            dessinView.changeColor(Color.RED);
            rouge.setBackgroundResource(R.drawable.bouton_rouge_selection);
        });


        vert = findViewById(R.id.vert);
        vert.setBackgroundResource(R.drawable.bouton_vert);
        vert.setOnClickListener(v -> {
            this.resetBoutons();
            dessinView.changeColor(Color.GREEN);
            vert.setBackgroundResource(R.drawable.bouton_vert_selection);
        });


        bleu = findViewById(R.id.bleu);
        bleu.setBackgroundResource(R.drawable.bouton_bleu);
        bleu.setOnClickListener(v -> {
            this.resetBoutons();
            dessinView.changeColor(Color.BLUE);
            bleu.setBackgroundResource(R.drawable.bouton_bleu_selection);
        });


        jaune = findViewById(R.id.jaune);
        jaune.setBackgroundResource(R.drawable.bouton_jaune);
        jaune.setOnClickListener(v -> {
            this.resetBoutons();
            dessinView.changeColor(Color.YELLOW);
            jaune.setBackgroundResource(R.drawable.bouton_jaune_selection);
        });

    }

    private void resetBoutons() {
        noir.setBackgroundResource(R.drawable.bouton_noire);

        rouge.setBackgroundResource(R.drawable.bouton_rouge);

        vert.setBackgroundResource(R.drawable.bouton_vert);

        bleu.setBackgroundResource(R.drawable.bouton_bleu);

        jaune.setBackgroundResource(R.drawable.bouton_jaune);
    }

    public void retour(View view) {
        finish();
    }
}
