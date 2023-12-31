package com.sdis.bilan.lsf;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendrierActivity extends ComponentActivity {

    Calendar calendrier;

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

        calendrier = Calendar.getInstance();
        calendrier.setTime(new Date());
    }

    private void creerMois(int moisView) {
        TableLayout tableMois = findViewById(moisView);
        tableMois.removeAllViews();
        int joursDansLeMois = calendrier.getActualMaximum(Calendar.DAY_OF_MONTH);
        String mois = new SimpleDateFormat("MMMM", Locale.FRENCH).format(calendrier.getTime());
        TextView texte = new TextView(this);
        texte.setTextColor(Color.BLACK);
        texte.setTextSize(30);
        texte.setText(mois.toUpperCase());
        tableMois.addView(texte);
        for (int jour = 1; jour <= 31; jour++) {
            texte = new TextView(this);
            texte.setTextSize(20);
            texte.setTextColor(Color.BLACK);
            texte.setPadding(20, 20 ,20 ,20);
            if(jour <= joursDansLeMois){ // pour mettre au même niveau le calendrier
                calendrier.set(Calendar.DAY_OF_MONTH, jour);
                String jourDeLaSemaine = new SimpleDateFormat("EEEE", Locale.FRENCH).format(calendrier.getTime());

                if("dimanche".equalsIgnoreCase(jourDeLaSemaine)){
                    texte.setTextColor(Color.RED);
                }
                if("jeudi".equalsIgnoreCase(jourDeLaSemaine)) {
                    texte.setText(jour + " " + jourDeLaSemaine.toUpperCase() + "      " + "Semaine " + calendrier.get(Calendar.WEEK_OF_YEAR));
                } else {
                    texte.setText(jour + " " + jourDeLaSemaine.toUpperCase());
                }

            }
            texte.setBackgroundResource(R.drawable.bordure);
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
