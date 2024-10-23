package com.sdis.bilan.lsf;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.ComponentActivity;

public class AProposActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_propos);

        Button versionTextView = findViewById(R.id.version);
        try {
            String appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            versionTextView.setText(String.format("%s%s", getString(R.string.version), appVersion));
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickSdis(View view) {
        Uri uri = Uri.parse("https://www.sdis81.fr/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickBs(View view) {
        Uri uri = Uri.parse("https://www.bonsauveuralby.fr/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickCgu(View view) {
        Intent intent = new Intent(AProposActivity.this, CguActivity.class);
        startActivity(intent);
    }

    public void onClickPdc(View view) {
        Intent intent = new Intent(AProposActivity.this, PdcActivity.class);
        startActivity(intent);
    }

    public void onClickCredits(View view) {
        Intent intent = new Intent(AProposActivity.this, CreditsActivity.class);
        startActivity(intent);
    }

    public void onClickVersion(View view) {
        Intent intent = new Intent(AProposActivity.this, VersionActivity.class);
        startActivity(intent);
    }

    public void retour(View view) {
        finish();
    }
}
