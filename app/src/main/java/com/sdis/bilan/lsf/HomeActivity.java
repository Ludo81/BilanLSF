package com.sdis.bilan.lsf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;

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

        List<RechercheItem> items = new ArrayList<>();
        for (String video : Arrays.stream(R.raw.class.getFields()).map(Field::getName).collect(Collectors.toList())) {
            RechercheItem item = new RechercheItem();
            item.setNomOrigine(video);
            item.setNomAffiche(video.replace("_", " ").replace("39", "'").replace("63", "?").toUpperCase());
            items.add(item);
        }

        CustomArrayAdapter adapter = new CustomArrayAdapter(this, android.R.layout.simple_list_item_1, items);
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
        Intent intent = new Intent(HomeActivity.this, ClavierActivity.class);
        startActivity(intent);
    }

    private void goToGroupe(String groupe, int couleur) {
        Intent intent = new Intent(HomeActivity.this, GroupeActivity.class);
        intent.putExtra("GROUPE", groupe);
        intent.putExtra("COULEUR", couleur);
        startActivity(intent);
    }

    public void onClickAPropos(View view) {
        Intent intent = new Intent(HomeActivity.this, AProposActivity.class);
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

        @NonNull
        @Override
        public String toString() {
            return nomAffiche;
        }
    }

    public class CustomArrayAdapter extends ArrayAdapter<RechercheItem> {

        public CustomArrayAdapter(Context context, int resource, List<RechercheItem> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundColor(getColorByNomVideo(textView.getText().toString()));
            }

            return view;
        }

        private int getColorByNomVideo(String video) {
            if (video.startsWith("ACCOUCHEMENT")) {
                return Color.parseColor("#FBBC05");
            } else if (video.startsWith("ABECEDAIRE")) {
                return Color.parseColor("#1D90DC");
            } else if (video.startsWith("ABORDAGE VICTIME")) {
                return Color.parseColor("#00B52A");
            } else if (video.startsWith("BILAN CIRCONSTANCIEL")) {
                return Color.parseColor("#153849");
            } else if (video.startsWith("BILAN PRIMAIRE")) {
                return Color.parseColor("#8B0041");
            } else if (video.startsWith("BILAN SECONDAIRE")) {
                return Color.parseColor("#D83D1D");
            } else if (video.startsWith("RENSEIGNEMENTS")) {
                return Color.parseColor("#8153FF");
            } else if (video.startsWith("SECOURS ROUTIER")) {
                return Color.parseColor("#da1a29");
            } else {
                return Color.BLACK;
            }
        }
    }
}