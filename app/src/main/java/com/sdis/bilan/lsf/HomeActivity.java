package com.sdis.bilan.lsf;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        List<RechercheItem> items = new ArrayList<>();
        for(String video : Arrays.stream(R.raw.class.getFields()).map(Field::getName).collect(Collectors.toList())){
            RechercheItem item = new RechercheItem();
            item.setNomOrigine(video);
            item.setNomAffiche(video.replace("_", " "));
            items.add(item);
        }

        ArrayAdapter<RechercheItem> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, items);
        AutoCompleteTextView actv = findViewById(R.id.recherche);
        actv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(HomeActivity.this, VideoActivity.class);
            intent.putExtra("VIDEO_NAME", ((RechercheItem) parent.getItemAtPosition(position)).getNomOrigine());
            startActivity(intent);
            actv.setText(null);
        });
        actv.setThreshold(1);
        actv.setAdapter(adapter);
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

    public static class RechercheItem {

        private String nomOrigine;

        private String nomAffiche;

        public String getNomOrigine() {
            return nomOrigine;
        }

        public void setNomOrigine(String nomOrigine) {
            this.nomOrigine = nomOrigine;
        }

        public String getNomAffiche() {
            return nomAffiche;
        }

        public void setNomAffiche(String nomAffiche) {
            this.nomAffiche = nomAffiche;
        }

        @Override
        public String toString(){
            return nomAffiche;
        }
    }
}