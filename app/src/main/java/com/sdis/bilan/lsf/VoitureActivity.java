package com.sdis.bilan.lsf;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.sdis.bilan.lsf.databinding.Voiture10Binding;
import com.sdis.bilan.lsf.databinding.Voiture5Binding;
import com.sdis.bilan.lsf.databinding.Voiture7Binding;

public class VoitureActivity extends BaseActivity {

    FrameLayout container;

    Voiture5Binding voiture5Binding;
    Voiture7Binding voiture7Binding;
    Voiture10Binding voiture10Binding;

    int nombreSelection;

    int nombrePlaces = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        voiture5Binding = Voiture5Binding.inflate(getLayoutInflater());
        voiture7Binding = Voiture7Binding.inflate(getLayoutInflater());
        voiture10Binding = Voiture10Binding.inflate(getLayoutInflater());

        setContentView(voiture5Binding.getRoot());
        container = findViewById(R.id.content);
    }

    public void reduirePlaces(View view) {
        container.removeAllViews();
        if (nombrePlaces == 7) {
            nombrePlaces = 5;
            container.addView(voiture5Binding.getRoot());
        } else if (nombrePlaces == 10) {
            nombrePlaces = 7;
            container.addView(voiture7Binding.getRoot());
        }
        nombreSelection = 0;
    }

    public void augmenterPlaces(View view) {
        container.removeAllViews();
        if (nombrePlaces == 5) {
            nombrePlaces = 7;
            container.addView(voiture7Binding.getRoot());
        } else if (nombrePlaces == 7) {
            nombrePlaces = 10;
            container.addView(voiture10Binding.getRoot());
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
}
