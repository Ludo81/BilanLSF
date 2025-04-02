package com.sdis.secours.lsf.police;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.databinding.AccueilPoliceBinding;
import com.sdis.secours.lsf.pompier.AccueilPompierActivity;
import com.sdis.secours.lsf.pompier.ClavierPompierActivity;

public class AccueilPoliceActivity extends BasePoliceActivity {

    AccueilPoliceBinding accueilPoliceBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accueilPoliceBinding = AccueilPoliceBinding.inflate(getLayoutInflater());
        setContentView(accueilPoliceBinding.getRoot());

        Logger.write(this, "Chargement Accueil Police");
    }

    public void goToPompier(View v) {
        Intent intent = new Intent(AccueilPoliceActivity.this, AccueilPompierActivity.class);
        startActivity(intent);
    }

    public void goToPolice(View v) {

    }

    public void onClickAbecedaire(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("abecedaire", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickAbordage(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("abordage", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickDocuments(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("documents", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickSecuriteRoutiere(View view) {
        Button button = (Button) findViewById(view.getId());
        Drawable buttonBackground = button.getBackground();
        this.goToGroupe("securite_routiere", ((ColorDrawable) buttonBackground).getColor());
    }

    public void onClickClavier(View view) {
        Intent intent = new Intent(AccueilPoliceActivity.this, ClavierPompierActivity.class);
        startActivity(intent);
    }

    private void goToGroupe(String groupe, int couleur) {
        Intent intent = new Intent(AccueilPoliceActivity.this, GroupePoliceActivity.class);
        intent.putExtra("GROUPE", groupe);
        intent.putExtra("COULEUR", couleur);
        startActivity(intent);
    }
}