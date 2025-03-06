package com.sdis.bilan.lsf.police;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.AProposBinding;

public class AProposPoliceActivity extends BasePoliceActivity {

    AProposBinding aProposBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aProposBinding = AProposBinding.inflate(getLayoutInflater());
        setContentView(aProposBinding.getRoot());

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

    public void onClickPolice(View view) {
        Uri uri = Uri.parse("https://albi.fr/securite/police-municipale");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickBs(View view) {
        Uri uri = Uri.parse("https://www.bonsauveuralby.fr/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickBilanLsf(View view) {
        Uri uri = Uri.parse("https://ludo81.github.io/BilanLSF/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickCgu(View view) {
        Intent intent = new Intent(AProposPoliceActivity.this, CguPoliceActivity.class);
        startActivity(intent);
    }

    public void onClickPdc(View view) {
        Intent intent = new Intent(AProposPoliceActivity.this, PdcPoliceActivity.class);
        startActivity(intent);
    }

    public void onClickCredits(View view) {
        Intent intent = new Intent(AProposPoliceActivity.this, CreditsPoliceActivity.class);
        startActivity(intent);
    }

    public void onClickVersion(View view) {
        Intent intent = new Intent(AProposPoliceActivity.this, VersionPoliceActivity.class);
        startActivity(intent);
    }
}
