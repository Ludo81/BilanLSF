package com.sdis.perceptible;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

public class HomeActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        TextView versionTextView = findViewById(R.id.versionTextView);
        try {
            String appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            versionTextView.setText(String.format("%s%s", getString(R.string.version), appVersion));
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        Intent intent = new Intent(HomeActivity.this, ClavierActivity.class);
        startActivity(intent);
    }

    private void goToGroupe(String groupe, int couleur) {
        Intent intent = new Intent(HomeActivity.this, GroupeActivity.class);
        intent.putExtra("GROUPE", groupe);
        intent.putExtra("COULEUR", couleur);
        startActivity(intent);
    }
}