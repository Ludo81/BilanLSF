package com.sdis.secours.lsf.pompier;

import android.content.RestrictionsManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.ParametresBinding;

public class ParametresPompierActivity extends BasePompierActivity {

    ParametresBinding parametresBinding;

    SharedPreferences sharedPreferences;

    private boolean isClavierAzerty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parametresBinding = ParametresBinding.inflate(getLayoutInflater());
        setContentView(parametresBinding.getRoot());

        Logger.write(this, "Chargement Paramètres");

        Switch clavierSwitch = findViewById(R.id.clavierSwitch);

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

        RadioGroup emergencyServiceGroup = findViewById(R.id.emergency_service_group);
        RadioButton pompierButton = findViewById(R.id.pompier_button);
        RadioButton policeButton = findViewById(R.id.police_button);

        RestrictionsManager restrictionsManager = (RestrictionsManager) getSystemService(RESTRICTIONS_SERVICE);
        Bundle restrictions = restrictionsManager.getApplicationRestrictions();
        String mdmChoice = restrictions.getString("defaultEmergencyService", "Disabled");
        Logger.write(this, "Récupération de la configuration MDM <defaultEmergencyService> " + mdmChoice);
        if ("Pompier".equals(mdmChoice)) { // Si pompier selectionné depuis le MDM
            pompierButton.setChecked(true);
            policeButton.setChecked(false);
            pompierButton.setClickable(false);
            policeButton.setClickable(false);
        } else if ("Police".equals(mdmChoice)) { // Si police selectionné depuis le MDM
            pompierButton.setChecked(false);
            policeButton.setChecked(true);
            pompierButton.setClickable(false);
            policeButton.setClickable(false);
        } else { // Sinon on applique les paramètres de l'appareil
            String defaultEmergencyService = sharedPreferences.getString("defaultEmergencyService", "Pompier");
            if ("Pompier".equals(defaultEmergencyService)) {
                pompierButton.setChecked(true);
                policeButton.setChecked(false);
            } else if ("Police".equals(defaultEmergencyService)) {
                pompierButton.setChecked(false);
                policeButton.setChecked(true);
            }

            emergencyServiceGroup.setOnCheckedChangeListener((group, checkedId) -> {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (checkedId == R.id.pompier_button) {
                    editor.putString("defaultEmergencyService", "Pompier");
                } else if (checkedId == R.id.police_button) {
                    editor.putString("defaultEmergencyService", "Police");
                }
                editor.apply();
            });
        }
    }
}
