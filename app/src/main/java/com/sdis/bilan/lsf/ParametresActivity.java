package com.sdis.bilan.lsf;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import com.sdis.bilan.lsf.databinding.ParametresBinding;

public class ParametresActivity extends BaseActivity {

    ParametresBinding parametresBinding;

    SharedPreferences sharedPreferences;

    private Switch clavierSwitch;
    private boolean isClavierAzerty = false;

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
    }
}
