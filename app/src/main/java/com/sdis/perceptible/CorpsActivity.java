package com.sdis.perceptible;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.util.Arrays;
import java.util.List;

public class CorpsActivity extends ComponentActivity {

    //private final List<Integer> buttonsId = Arrays.asList(R.id.plaie, R.id.traumatisme, R.id.deformation, R.id.brulure, R.id.hemorragie, R.id.fracture_ouverte, R.id.douleur, R.id.section);

    private Integer couleurSelectionnee = null;
    private String typeSelectionnee = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corps);

        ImageView imageView = findViewById(R.id.corps);
        imageView.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP /*&& couleurSelectionnee != null*/) {
                addPastille(event.getX(), event.getY());
            }
            return true;
        });
    }


    private void addPastille(float x, float y) {
        Context context = getApplicationContext();

        FrameLayout frame = findViewById(R.id.frame);

        View pastille = new View(context);
        pastille.setLayoutParams(new FrameLayout.LayoutParams(60, 60));
        //pastille.setBackgroundColor(couleurSelectionnee);
        pastille.setBackgroundColor(Color.parseColor("#61DC2A"));

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        //shape.setColor(couleurSelectionnee);
        shape.setColor(Color.parseColor("#61DC2A"));
        pastille.setBackground(shape);

        pastille.setX(x - 30);
        pastille.setY(y - 30);

        /*
        TextView textView = new TextView(context);
        textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(typeSelectionnee);
        textView.setTextColor(Color.WHITE);
        textView.setX(x - 10);
        textView.setY(y - 13);
        */

        frame.addView(pastille);
        //frame.addView(textView);
    }

    public void onClickVideo(View view) {
        Intent intent = new Intent(CorpsActivity.this, VideoActivity.class);
        intent.putExtra("VIDEO_NAME", "bilan_primaire_montrez_moi_ou_vous_avez_mal");
        startActivity(intent);
    }

    public void retour(View view) {
        finish();
    }

    /*
    public void onClickPlaie(View view) {
        toutDeselectionner();
        Button button = findViewById(R.id.plaie);
        button.setBackgroundColor(Color.parseColor("#F7B82D"));
        button.setTextColor(Color.parseColor("#FFFFFF"));
        couleurSelectionnee = Color.parseColor("#F7B82D");
        typeSelectionnee = "P";
        updateTexte("UNE PLAIE");
    }

    public void onClickTraumatisme(View view) {
        toutDeselectionner();
        Button button = findViewById(R.id.traumatisme);
        button.setBackgroundColor(Color.parseColor("#8153FF"));
        button.setTextColor(Color.parseColor("#FFFFFF"));
        couleurSelectionnee = Color.parseColor("#8153FF");
        typeSelectionnee = "T";
        updateTexte("UN TRAUMATISME");
    }

    public void onClickDeformation(View view) {
        toutDeselectionner();
        Button button = findViewById(R.id.deformation);
        button.setBackgroundColor(Color.parseColor("#AF6100"));
        button.setTextColor(Color.parseColor("#FFFFFF"));
        couleurSelectionnee = Color.parseColor("#AF6100");
        typeSelectionnee = "D";
        updateTexte("UNE DEFORMATION");
    }

    public void onClickBrulure(View view) {
        toutDeselectionner();
        Button button = findViewById(R.id.brulure);
        button.setBackgroundColor(Color.parseColor("#000000"));
        button.setTextColor(Color.parseColor("#FFFFFF"));
        couleurSelectionnee = Color.parseColor("#000000");
        typeSelectionnee = "B";
        updateTexte("UNE BRULURE");
    }

    public void onClickHemorragie(View view) {
        toutDeselectionner();
        Button button = findViewById(R.id.hemorragie);
        button.setBackgroundColor(Color.parseColor("#D83D1D"));
        button.setTextColor(Color.parseColor("#FFFFFF"));
        couleurSelectionnee = Color.parseColor("#D83D1D");
        typeSelectionnee = "H";
        updateTexte("UNE HEMORRAGIE");
    }

    public void onClickFractureOuverte(View view) {
        toutDeselectionner();
        Button button = findViewById(R.id.fracture_ouverte);
        button.setBackgroundColor(Color.parseColor("#C600E8"));
        button.setTextColor(Color.parseColor("#FFFFFF"));
        couleurSelectionnee = Color.parseColor("#C600E8");
        typeSelectionnee = "FO";
        updateTexte("UNE FRACTURE OUVERTE");
    }

    public void onClickDouleur(View view) {
        toutDeselectionner();
        Button button = findViewById(R.id.douleur);
        button.setBackgroundColor(Color.parseColor("#61DC2A"));
        button.setTextColor(Color.parseColor("#FFFFFF"));
        couleurSelectionnee = Color.parseColor("#61DC2A");
        typeSelectionnee = "DL";
        updateTexte("UNE DOULEUR");
    }

    public void onClickSection(View view) {
        toutDeselectionner();
        Button button = findViewById(R.id.section);
        button.setBackgroundColor(Color.parseColor("#8B0041"));
        button.setTextColor(Color.parseColor("#FFFFFF"));
        couleurSelectionnee = Color.parseColor("#8B0041");
        typeSelectionnee = "S";
        updateTexte("UNE SECTION");
    }

    private void toutDeselectionner(){
        for(Integer buttonId : this.buttonsId){
            Button button = findViewById(buttonId);
            button.setBackgroundColor(Color.parseColor("#FFD5D6D6"));
            button.setTextColor(Color.parseColor("#000000"));
        }
    }

    private void updateTexte(String suite){
        TextView texte = findViewById(R.id.texte_saisir);
        texte.setText(String.format("SAISIR %s", suite));
    }

     */
}
