package com.sdis.bilan.lsf.police;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.PortefeuilleBinding;

public class PortefeuillePoliceActivity extends BasePoliceActivity {

    private PortefeuilleBinding portefeuilleBinding;

    private TextView argentView;

    private double argent = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        portefeuilleBinding = PortefeuilleBinding.inflate(getLayoutInflater());
        setContentView(portefeuilleBinding.getRoot());

        argentView = findViewById(R.id.argent);
    }

    public void monter(View v) {
        argent += 0.01;
        argentView.setText(argent + "€");
    }

    public void descendre(View v) {
        if (argent != 0) {
            argent -= 0.01;
            argentView.setText(argent + "€");
        }

    }
}
