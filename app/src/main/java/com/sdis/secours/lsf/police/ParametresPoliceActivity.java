package com.sdis.secours.lsf.police;

import android.content.RestrictionsManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.ParametresBinding;

public class ParametresPoliceActivity extends BasePoliceActivity {

    ParametresBinding parametresBinding;

    SharedPreferences sharedPreferences;

    private boolean isClavierAzerty = false;

    EditText licenceInput;

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

        licenceInput = findViewById(R.id.licenceInput);
        licenceInput.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isFormatting) return;

                isFormatting = true;

                String input = s.toString().toUpperCase().replaceAll("[^A-Z0-9]", ""); // seulement lettres/nombres
                StringBuilder formatted = new StringBuilder();

                for (int i = 0; i < input.length(); i++) {
                    if (i > 0 && i % 4 == 0 && formatted.length() < 19) {
                        formatted.append("-");
                    }
                    formatted.append(input.charAt(i));
                }

                licenceInput.removeTextChangedListener(this);
                licenceInput.setText(formatted.toString());
                licenceInput.setSelection(formatted.length());
                licenceInput.addTextChangedListener(this);

                isFormatting = false;
            }
        });
    }

    public void enregistrer(View v) {
        String licence = licenceInput.getText().toString().trim();

        sharedPreferences = getSharedPreferences("Parametrage", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ("AB7F-92KD-ZX4L-MQ8P".equals(licence)) {
            Toast.makeText(this, "Licence valide !", Toast.LENGTH_LONG).show();

            editor.putBoolean("isLicenceValide", true);
        } else {
            Toast.makeText(this, "Licence invalide !", Toast.LENGTH_LONG).show();

            editor.putBoolean("isLicenceValide", false);
        }
        editor.apply();
    }
}
