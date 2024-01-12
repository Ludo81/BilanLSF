package com.sdis.bilan.lsf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.lang.reflect.Field;

public class GroupeActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupe);

        String groupe = getIntent().getStringExtra("GROUPE");
        int couleur = getIntent().getIntExtra("COULEUR", 0);

        TableLayout tableContainer = findViewById(R.id.table);

        TextView nomGroupeTextView = findViewById(R.id.nom_groupe);
        nomGroupeTextView.setText(groupe.replace("_", " ").toUpperCase());

        Field[] fields = R.raw.class.getFields();
        TableRow ligne = null;
        for (Field field : fields) {
            String name = field.getName();
            if (groupe != null && name.startsWith(groupe)) {
                if (ligne == null) {
                    ligne = new TableRow(this);
                    tableContainer.addView(ligne);
                }

                Button button = new Button(this);
                button.setText(name.replace(groupe, "").replace("_", " ").replace("39", "'").replace("63", "?"));
                button.setBackgroundColor(couleur);
                button.setTextColor(Color.WHITE);
                TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(0, 350);
                buttonParams.weight = 1;
                buttonParams.setMargins(20, 20, 20, 20);
                button.setLayoutParams(buttonParams);
                button.setOnClickListener(view -> {
                    Intent intent = new Intent(GroupeActivity.this, VideoActivity.class);
                    intent.putExtra("VIDEO_NAME", name);
                    startActivity(intent);
                });
                ligne.addView(button);

                if (ligne.getChildCount() >= 2) {
                    ligne = null;
                }
            }
        }

        if(ligne != null && ligne.getChildCount() == 1){
            Button button = new Button(this);
            button.setVisibility(View.INVISIBLE);
            TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(0, 350);
            buttonParams.weight = 1;
            buttonParams.setMargins(20, 20, 20, 20);
            button.setLayoutParams(buttonParams);
            ligne.addView(button);
        }
    }

    public void onClickClavier(View view) {
        Intent intent = new Intent(GroupeActivity.this, ClavierActivity.class);
        startActivity(intent);
    }

    public void retour(View view){
        finish();
    }
}
