package com.sdis.bilan.lsf;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.core.content.ContextCompat;

public class VoitureActivity extends ComponentActivity {

    int nombreSelection;

    int nombrePlaces = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voiture5);
    }

    public void reduirePlaces(View view) {
        if (nombrePlaces == 7) {
            nombrePlaces = 5;
            setContentView(R.layout.voiture5);
        } else if (nombrePlaces == 10) {
            nombrePlaces = 7;
            setContentView(R.layout.voiture7);
        }
        nombreSelection = 0;
    }

    public void augmenterPlaces(View view) {
        if (nombrePlaces == 5) {
            nombrePlaces = 7;
            setContentView(R.layout.voiture7);
        } else if (nombrePlaces == 7) {
            nombrePlaces = 10;
            setContentView(R.layout.voiture10);
        }
        nombreSelection = 0;
    }

    public void selection(View view) {
        ImageView siegeView = (ImageView) view;
        if (siegeView.getDrawable().getConstantState() == ContextCompat.getDrawable(this, R.drawable.siege_avant).getConstantState()) {
            siegeView.setImageResource(R.drawable.siege_avant_selection);
            nombreSelection += 1;
        } else if (siegeView.getDrawable().getConstantState() == ContextCompat.getDrawable(this, R.drawable.siege_avant_selection).getConstantState()) {
            siegeView.setImageResource(R.drawable.siege_avant);
            nombreSelection -= 1;
        } else if (siegeView.getDrawable().getConstantState() == ContextCompat.getDrawable(this, R.drawable.petit_siege).getConstantState()) {
            siegeView.setImageResource(R.drawable.petit_siege_selection);
            nombreSelection += 1;
        } else if (siegeView.getDrawable().getConstantState() == ContextCompat.getDrawable(this, R.drawable.petit_siege_selection).getConstantState()) {
            siegeView.setImageResource(R.drawable.petit_siege);
            nombreSelection -= 1;
        } else if (siegeView.getDrawable().getConstantState() == ContextCompat.getDrawable(this, R.drawable.grand_siege).getConstantState()) {
            siegeView.setImageResource(R.drawable.grand_siege_selection);
            nombreSelection += 1;
        } else if (siegeView.getDrawable().getConstantState() == ContextCompat.getDrawable(this, R.drawable.grand_siege_selection).getConstantState()) {
            siegeView.setImageResource(R.drawable.grand_siege);
            nombreSelection -= 1;
        }
        TextView selectionView = findViewById(R.id.selection);
        if (nombreSelection == 0) {
            selectionView.setVisibility(View.INVISIBLE);
            selectionView.setText("");
        } else if (nombreSelection == 1) {
            selectionView.setVisibility(View.VISIBLE);
            selectionView.setText(nombreSelection + " PERSONNE SELECTIONNEE");
        } else {
            selectionView.setVisibility(View.VISIBLE);
            selectionView.setText(nombreSelection + " PERSONNES SELECTIONNEES");
        }
    }

    public void retour(View view) {
        finish();
    }
}
