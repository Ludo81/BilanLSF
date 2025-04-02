package com.sdis.secours.lsf.pompier;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.Voiture10Binding;
import com.sdis.secours.lsf.databinding.Voiture5Binding;
import com.sdis.secours.lsf.databinding.Voiture7Binding;

public class VoiturePompierActivity extends BasePompierActivity {

    FrameLayout container;

    int nombreSelection;

    int nombrePlaces = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Voiture5Binding.inflate(getLayoutInflater()).getRoot());
        container = findViewById(R.id.content);

        Logger.write(this, "Chargement Voiture");
    }

    public void reduirePlaces(View view) {
        container.removeAllViews();
        if (nombrePlaces == 7) {
            nombrePlaces = 5;
            container.addView(Voiture5Binding.inflate(getLayoutInflater()).getRoot());
        } else if (nombrePlaces == 10) {
            nombrePlaces = 7;
            container.addView(Voiture7Binding.inflate(getLayoutInflater()).getRoot());
        }
        nombreSelection = 0;
    }

    public void augmenterPlaces(View view) {
        container.removeAllViews();
        if (nombrePlaces == 5) {
            nombrePlaces = 7;
            container.addView(Voiture7Binding.inflate(getLayoutInflater()).getRoot());
        } else if (nombrePlaces == 7) {
            nombrePlaces = 10;
            container.addView(Voiture10Binding.inflate(getLayoutInflater()).getRoot());
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
