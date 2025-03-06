package com.sdis.bilan.lsf.pompier;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.GroupeBinding;

import java.io.IOException;

public class GroupePompierActivity extends BasePompierActivity {

    GroupeBinding groupeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupeBinding = GroupeBinding.inflate(getLayoutInflater());
        setContentView(groupeBinding.getRoot());

        String groupe = getIntent().getStringExtra("GROUPE");
        int couleur = getIntent().getIntExtra("COULEUR", 0);

        TableLayout tableContainer = findViewById(R.id.table);

        TextView nomGroupeTextView = findViewById(R.id.nom_groupe);
        nomGroupeTextView.setText(groupe.replace("_", " ").toUpperCase());

        try {
            // Récupérer l'AssetManager et lister les vidéos dans "assets/videos/pompier/"
            AssetManager assetManager = getAssets();
            String[] videoFiles = assetManager.list("videos/pompier");

            TableRow ligne = null;

            for (String name : videoFiles) {
                if (groupe != null && name.startsWith(groupe)) {
                    if (ligne == null) {
                        ligne = new TableRow(this);
                        tableContainer.addView(ligne);
                    }

                    // Création du bouton avec le nom de la vidéo
                    Button button = new Button(this);
                    button.setText(name.replace(groupe, "").replace("_", " ").replace("39", "'").replace("63", "?").replace(".mp4", ""));
                    button.setBackgroundColor(couleur);
                    button.setTextColor(Color.WHITE);

                    // Adapter la taille du bouton en fonction de la résolution de l'écran
                    int hauteurBouton = 350;
                    int margin = 20;
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    if (getResources().getDisplayMetrics().density == 1.5 && displayMetrics.heightPixels < 500 && displayMetrics.widthPixels < 1000) {
                        hauteurBouton = 150;
                        margin = 10;
                    }

                    // Paramètres du bouton
                    TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(0, hauteurBouton);
                    buttonParams.weight = 1;
                    buttonParams.setMargins(margin, margin, margin, margin);
                    button.setLayoutParams(buttonParams);

                    // Ajouter un onClickListener pour ouvrir VideoActivity avec la vidéo sélectionnée
                    button.setOnClickListener(view -> {
                        Intent intent = new Intent(GroupePompierActivity.this, VideoPompierActivity.class);
                        intent.putExtra("VIDEO_NAME", name);
                        startActivity(intent);
                    });

                    // Ajouter le bouton à la ligne
                    ligne.addView(button);

                    // Passer à la ligne suivante après 2 boutons
                    if (ligne.getChildCount() >= 2) {
                        ligne = null;
                    }
                }
            }

            if (ligne != null && ligne.getChildCount() == 1) {
                Button button = new Button(this);
                button.setVisibility(View.INVISIBLE);
                int hauteurBouton = 350;
                int margin = 20;
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                if (getResources().getDisplayMetrics().density == 1.5 && displayMetrics.heightPixels < 500 && displayMetrics.widthPixels < 1000) {
                    hauteurBouton = 150;
                    margin = 10;
                }
                TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(0, hauteurBouton);
                buttonParams.weight = 1;
                buttonParams.setMargins(margin, margin, margin, margin);
                button.setLayoutParams(buttonParams);
                ligne.addView(button);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
