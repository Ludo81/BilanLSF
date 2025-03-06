package com.sdis.bilan.lsf.police;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Switch;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.ParametresBinding;

public class ParametresPoliceActivity extends BasePoliceActivity {

    ParametresBinding parametresBinding;

    SharedPreferences sharedPreferences;

    private Switch clavierSwitch;
    private boolean isClavierAzerty = false;

    private RadioButton pompierButton;
    private RadioButton policeButton;
    private boolean isPompierDefault = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parametresBinding = ParametresBinding.inflate(getLayoutInflater());
        setContentView(parametresBinding.getRoot());

        clavierSwitch = findViewById(R.id.clavierSwitch);

        sharedPreferences = getSharedPreferences("Parametrage", MODE_PRIVATE);

        isClavierAzerty = sharedPreferences.getBoolean("isClavierAzerty", false);

        clavierSwitch.setChecked(isClavierAzerty);

        clavierSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences = getSharedPreferences("Parametrage", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isClavierAzerty", isChecked);
            editor.apply();
            isClavierAzerty = isChecked;
        });

        isPompierDefault = sharedPreferences.getBoolean("isPompierDefault", true);

        pompierButton = findViewById(R.id.pompier_button);
        pompierButton.setChecked(isPompierDefault);

        policeButton = findViewById(R.id.police_button);
        policeButton.setChecked(!isPompierDefault);

        pompierButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences = getSharedPreferences("Parametrage", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isPompierDefault", isChecked);
            editor.apply();
            isPompierDefault = isChecked;
        });
    }
}
