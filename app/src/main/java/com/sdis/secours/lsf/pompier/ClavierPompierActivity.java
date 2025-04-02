package com.sdis.secours.lsf.pompier;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.ClavierAbcdefBinding;
import com.sdis.secours.lsf.databinding.ClavierAzertyBinding;

public class ClavierPompierActivity extends BasePompierActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("Parametrage", MODE_PRIVATE);

        super.onCreate(savedInstanceState);

        if (sharedPreferences.getBoolean("isClavierAzerty", false)) {
            setContentView(ClavierAzertyBinding.inflate(getLayoutInflater()).getRoot());
        } else {
            setContentView(ClavierAbcdefBinding.inflate(getLayoutInflater()).getRoot());
        }

        Logger.write(this, "Chargement Clavier");
    }

    public void inverser(View view) {
        Intent intent = new Intent(ClavierPompierActivity.this, ClavierInversePompierActivity.class);
        startActivity(intent);
    }

    public void effacer(View view) {
        TextView textView = findViewById(R.id.texte);
        if (textView.getText().length() > 0)
            textView.setText(textView.getText().subSequence(0, textView.getText().length() - 1));
    }

    public void tout_effacer(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText("");
    }

    public void espace(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s ", textView.getText()));
    }

    public void oui(View view) {
        final Dialog dialog = new Dialog(ClavierPompierActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_oui);

        final Handler handler = new Handler();
        handler.postDelayed(dialog::dismiss, 1500);

        dialog.show();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void non(View view) {
        final Dialog dialog = new Dialog(ClavierPompierActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_non);

        final Handler handler = new Handler();
        handler.postDelayed(dialog::dismiss, 1500);

        dialog.show();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void zero(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s0", textView.getText()));
    }

    public void un(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s1", textView.getText()));
    }

    public void deux(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s2", textView.getText()));
    }

    public void trois(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s3", textView.getText()));
    }

    public void quatre(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s4", textView.getText()));
    }

    public void cinq(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s5", textView.getText()));
    }

    public void six(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s6", textView.getText()));
    }

    public void sept(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s7", textView.getText()));
    }

    public void huit(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s8", textView.getText()));
    }

    public void neuf(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s9", textView.getText()));
    }

    public void dix(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%s10", textView.getText()));
    }

    public void a(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sa", textView.getText()));
    }

    public void b(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sb", textView.getText()));
    }

    public void c(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sc", textView.getText()));
    }

    public void d(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sd", textView.getText()));
    }

    public void e(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%se", textView.getText()));
    }

    public void f(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sf", textView.getText()));
    }

    public void g(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sg", textView.getText()));
    }

    public void h(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sh", textView.getText()));
    }

    public void i(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%si", textView.getText()));
    }

    public void j(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sj", textView.getText()));
    }

    public void k(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sk", textView.getText()));
    }

    public void l(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sl", textView.getText()));
    }

    public void m(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sm", textView.getText()));
    }

    public void n(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sn", textView.getText()));
    }

    public void o(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%so", textView.getText()));
    }

    public void p(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sp", textView.getText()));
    }

    public void q(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sq", textView.getText()));
    }

    public void r(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sr", textView.getText()));
    }

    public void s(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%ss", textView.getText()));
    }

    public void t(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%st", textView.getText()));
    }

    public void u(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%su", textView.getText()));
    }

    public void v(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sv", textView.getText()));
    }

    public void w(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sw", textView.getText()));
    }

    public void x(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sx", textView.getText()));
    }

    public void y(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sy", textView.getText()));
    }

    public void z(View view) {
        TextView textView = findViewById(R.id.texte);
        textView.setText(String.format("%sz", textView.getText()));
    }
}
