package com.sdis.secours.lsf.pompier;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.databinding.AccueilPompierBinding;
import com.sdis.secours.lsf.police.AccueilPoliceActivity;

public class AccueilPompierActivity extends BasePompierActivity {

    AccueilPompierBinding accueilPompierBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accueilPompierBinding = AccueilPompierBinding.inflate(getLayoutInflater());
        setContentView(accueilPompierBinding.getRoot());

        Logger.write(this, "Chargement Accueil Pompier");
    }

    public void goToPompier(View v) {

    }

    public void goToPolice(View v) {
        Intent intent = new Intent(AccueilPompierActivity.this, AccueilPoliceActivity.class);
        startActivity(intent);
    }

    public void onClickAccouchement(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("accouchement", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickAbecedaire(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("abecedaire", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickAbordageVictime(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("abordage_victime", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickBilanCirconstanciel(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("bilan_circonstanciel", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickBilanPrimaire(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("bilan_primaire", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickBilanSecondaire(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("bilan_secondaire", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickRenseignements(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("renseignements", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickSecoursRoutier(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("secours_routier", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickClavier(View view) {
        Intent intent = new Intent(AccueilPompierActivity.this, ClavierPompierActivity.class);
        startActivity(intent);
    }

    private void goToGroupe(String groupe, int couleur) {
        Intent intent = new Intent(AccueilPompierActivity.this, GroupePompierActivity.class);
        intent.putExtra("GROUPE", groupe);
        intent.putExtra("COULEUR", couleur);
        startActivity(intent);
    }
}