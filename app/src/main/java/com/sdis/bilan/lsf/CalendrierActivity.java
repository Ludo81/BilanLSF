package com.sdis.bilan.lsf;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendrierActivity extends ComponentActivity {

    Calendar calendrier;

    private View selectionPrecendente;
    private Drawable fondPrecendent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendrier);

        calendrier = Calendar.getInstance();
        calendrier.setTime(new Date());

        TextView anneeView = findViewById(R.id.annee);
        anneeView.setText(String.valueOf(calendrier.get(Calendar.YEAR)));

        this.creerMois(R.id.mois1);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois2);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois3);

        if(calendrier.get(Calendar.MONTH) == Calendar.NOVEMBER || calendrier.get(Calendar.MONTH) == Calendar.DECEMBER){
            calendrier = Calendar.getInstance();
            calendrier.setTime(new Date());
        }
    }

    private void creerMois(int moisView) {
        TableLayout tableMois = findViewById(moisView);
        tableMois.removeAllViews();
        int joursDansLeMois = calendrier.getActualMaximum(Calendar.DAY_OF_MONTH);
        String mois = new SimpleDateFormat("MMMM", Locale.FRENCH).format(calendrier.getTime());
        TextView texte = new TextView(this);
        texte.setTextColor(Color.BLACK);
        texte.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size));
        texte.setText(mois.toUpperCase());
        tableMois.addView(texte);
        for (int jour = 1; jour <= 31; jour++) {
            texte = new TextView(this);
            texte.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size));
            texte.setTextColor(Color.BLACK);
            texte.setPadding(20, 20 ,20 ,20);
            if(jour <= joursDansLeMois){ // pour mettre au même niveau le calendrier
                calendrier.set(Calendar.DAY_OF_MONTH, jour);
                String jourDeLaSemaine = new SimpleDateFormat("EEEE", Locale.FRENCH).format(calendrier.getTime());
                int semaine = calendrier.get(Calendar.WEEK_OF_YEAR);
                if(semaine%2 == 0){
                    texte.setBackgroundResource(R.drawable.bordure_gris);
                } else {
                    texte.setBackgroundResource(R.drawable.bordure_blanc);
                }
                if("dimanche".equalsIgnoreCase(jourDeLaSemaine)){
                    texte.setTextColor(Color.RED);
                }
                if("jeudi".equalsIgnoreCase(jourDeLaSemaine)) {
                    texte.setText(jour + " " + jourDeLaSemaine.toUpperCase() + "      " + "Semaine " + semaine);
                } else {
                    texte.setText(jour + " " + jourDeLaSemaine.toUpperCase());
                }
            }
            texte.setOnClickListener(v -> {
                if(this.selectionPrecendente != null){
                    this.selectionPrecendente.setBackground(this.fondPrecendent);
                }
                this.selectionPrecendente = v;
                this.fondPrecendent = v.getBackground();
                v.setBackgroundResource(R.drawable.bordure_selection);
            });
            tableMois.addView(texte);
        }
    }

    public void anneePrecedente(View view) {
        calendrier.add(Calendar.YEAR, -1);
        TextView anneeView = findViewById(R.id.annee);
        anneeView.setText(String.valueOf(calendrier.get(Calendar.YEAR)));
        calendrier.set(Calendar.MONTH, Calendar.JANUARY);
        calendrier.set(Calendar.DAY_OF_MONTH, 1);

        this.creerMois(R.id.mois1);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois2);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois3);
    }

    public void anneeSuivante(View view) {
        calendrier.add(Calendar.YEAR, 1);
        TextView anneeView = findViewById(R.id.annee);
        anneeView.setText(String.valueOf(calendrier.get(Calendar.YEAR)));
        calendrier.set(Calendar.MONTH, Calendar.JANUARY);
        calendrier.set(Calendar.DAY_OF_MONTH, 1);

        this.creerMois(R.id.mois1);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois2);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois3);
    }

    public void moisPrecedent(View view) {
        calendrier.add(Calendar.MONTH, -5);
        calendrier.set(Calendar.DAY_OF_MONTH, 1);
        TextView anneeView = findViewById(R.id.annee);
        anneeView.setText(String.valueOf(calendrier.get(Calendar.YEAR)));

        this.creerMois(R.id.mois1);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois2);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois3);
    }

    public void moisSuivant(View view) {
        calendrier.add(Calendar.MONTH, 1);
        calendrier.set(Calendar.DAY_OF_MONTH, 1);
        TextView anneeView = findViewById(R.id.annee);
        anneeView.setText(String.valueOf(calendrier.get(Calendar.YEAR)));

        this.creerMois(R.id.mois1);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois2);

        calendrier.add(Calendar.MONTH, 1);
        this.creerMois(R.id.mois3);
    }

    public void retour(View view){
        finish();
    }

}
