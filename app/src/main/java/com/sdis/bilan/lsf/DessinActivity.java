package com.sdis.bilan.lsf;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.ComponentActivity;

public class DessinActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dessin);

        DessinView dessinView = findViewById(R.id.dessinView);

        ImageButton effacerBouton = findViewById(R.id.effacer);
        effacerBouton.setOnClickListener(v -> dessinView.clearDrawing());

        Button noir = findViewById(R.id.noir);
        noir.setOnClickListener(v -> dessinView.changeColor(Color.BLACK));

        Button rouge = findViewById(R.id.rouge);
        rouge.setOnClickListener(v -> dessinView.changeColor(Color.RED));

        Button vert = findViewById(R.id.vert);
        vert.setOnClickListener(v -> dessinView.changeColor(Color.GREEN));

        Button bleu = findViewById(R.id.bleu);
        bleu.setOnClickListener(v -> dessinView.changeColor(Color.BLUE));

        Button jaune = findViewById(R.id.jaune);
        jaune.setOnClickListener(v -> dessinView.changeColor(Color.YELLOW));
    }

    public void retour(View view) {
        finish();
    }
}
