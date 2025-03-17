package com.sdis.bilan.lsf.police;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.DescriptionPhyisiqueCorpsABinding;
import com.sdis.bilan.lsf.databinding.DescriptionPhyisiqueCorpsBBinding;
import com.sdis.bilan.lsf.databinding.DescriptionPhyisiqueCorpsCBinding;
import com.sdis.bilan.lsf.databinding.DescriptionPhyisiqueCorpsDBinding;

public class DescriptionPhysiquePoliceActivity extends BasePoliceActivity {

    FrameLayout container;

    private boolean isFemme = false;

    private ImageView corpsView;

    private ImageView corpsHommeView;
    private ImageView corpsFemmeView;

    private ImageView corpsMinceView;
    private ImageView corpsNormalView;
    private ImageView corpsCostaudView;

    private int couleurPeau = 0;

    private SeekBar tailleView;
    private int taille = 50;

    private Corpulence corpulence = Corpulence.NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(DescriptionPhyisiqueCorpsABinding.inflate(getLayoutInflater()).getRoot());
        container = findViewById(R.id.content);

        corpsHommeView = findViewById(R.id.corps_homme);
        corpsFemmeView = findViewById(R.id.corps_femme);
    }

    public void goToSexe(View v) {
        container.removeAllViews();
        container.addView(DescriptionPhyisiqueCorpsABinding.inflate(getLayoutInflater()).getRoot());

        corpsHommeView = findViewById(R.id.corps_homme);
        corpsFemmeView = findViewById(R.id.corps_femme);

        if (corpulence == Corpulence.MINCE) {
            corpsHommeView.setImageResource(R.drawable.homme_mince);
            corpsFemmeView.setImageResource(R.drawable.femme_mince);
        } else if (corpulence == Corpulence.NORMAL) {
            corpsHommeView.setImageResource(R.drawable.homme_normal);
            corpsFemmeView.setImageResource(R.drawable.femme_normale);
        } else if (corpulence == Corpulence.COSTAUD) {
            corpsHommeView.setImageResource(R.drawable.homme_costaud);
            corpsFemmeView.setImageResource(R.drawable.femme_ronde);
        }
        if (couleurPeau != 0) {
            corpsHommeView.setColorFilter(couleurPeau, PorterDuff.Mode.MULTIPLY);
            corpsFemmeView.setColorFilter(couleurPeau, PorterDuff.Mode.MULTIPLY);
        }

        if (isFemme) {
            corpsHommeView.setBackgroundResource(0);
            corpsFemmeView.setBackgroundResource(R.drawable.rounded_light_gray_background);
        } else {
            corpsHommeView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            corpsFemmeView.setBackgroundResource(0);
        }
    }

    public void goToCouleurPeau(View v) {
        container.removeAllViews();
        container.addView(DescriptionPhyisiqueCorpsBBinding.inflate(getLayoutInflater()).getRoot());

        corpsView = findViewById(R.id.corps);
        if (isFemme && corpulence == Corpulence.MINCE) {
            corpsView.setImageResource(R.drawable.femme_mince);
        } else if (isFemme && corpulence == Corpulence.NORMAL) {
            corpsView.setImageResource(R.drawable.femme_normale);
        } else if (isFemme && corpulence == Corpulence.COSTAUD) {
            corpsView.setImageResource(R.drawable.femme_ronde);
        } else if (!isFemme && corpulence == Corpulence.MINCE) {
            corpsView.setImageResource(R.drawable.homme_mince);
        } else if (!isFemme && corpulence == Corpulence.NORMAL) {
            corpsView.setImageResource(R.drawable.homme_normal);
        } else if (!isFemme && corpulence == Corpulence.COSTAUD) {
            corpsView.setImageResource(R.drawable.homme_costaud);
        }
        if (couleurPeau != 0) {
            corpsView.setColorFilter(couleurPeau, PorterDuff.Mode.MULTIPLY);
        }
    }

    public void goToTaille(View v) {
        container.removeAllViews();
        container.addView(DescriptionPhyisiqueCorpsCBinding.inflate(getLayoutInflater()).getRoot());

        corpsView = findViewById(R.id.corps);
        if (isFemme && corpulence == Corpulence.MINCE) {
            corpsView.setImageResource(R.drawable.femme_mince);
        } else if (isFemme && corpulence == Corpulence.NORMAL) {
            corpsView.setImageResource(R.drawable.femme_normale);
        } else if (isFemme && corpulence == Corpulence.COSTAUD) {
            corpsView.setImageResource(R.drawable.femme_ronde);
        } else if (!isFemme && corpulence == Corpulence.MINCE) {
            corpsView.setImageResource(R.drawable.homme_mince);
        } else if (!isFemme && corpulence == Corpulence.NORMAL) {
            corpsView.setImageResource(R.drawable.homme_normal);
        } else if (!isFemme && corpulence == Corpulence.COSTAUD) {
            corpsView.setImageResource(R.drawable.homme_costaud);
        }
        if (couleurPeau != 0) {
            corpsView.setColorFilter(couleurPeau, PorterDuff.Mode.MULTIPLY);
        }

        tailleView = findViewById(R.id.taille);
        tailleView.setProgress(taille);
        tailleView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                taille = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void goToCorpulence(View v) {
        container.removeAllViews();
        container.addView(DescriptionPhyisiqueCorpsDBinding.inflate(getLayoutInflater()).getRoot());

        corpsMinceView = findViewById(R.id.corps_mince);
        corpsNormalView = findViewById(R.id.corps_normal);
        corpsCostaudView = findViewById(R.id.corps_costaud);

        if (isFemme) {
            corpsMinceView.setImageResource(R.drawable.femme_mince);
            corpsNormalView.setImageResource(R.drawable.femme_normale);
            corpsCostaudView.setImageResource(R.drawable.femme_ronde);
        }
        if (couleurPeau != 0) {
            corpsMinceView.setColorFilter(couleurPeau, PorterDuff.Mode.MULTIPLY);
            corpsNormalView.setColorFilter(couleurPeau, PorterDuff.Mode.MULTIPLY);
            corpsCostaudView.setColorFilter(couleurPeau, PorterDuff.Mode.MULTIPLY);
        }
        if (corpulence == Corpulence.MINCE) {
            corpsMinceView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            corpsNormalView.setBackgroundResource(0);
            corpsCostaudView.setBackgroundResource(0);
        } else if (corpulence == Corpulence.NORMAL) {
            corpsMinceView.setBackgroundResource(0);
            corpsNormalView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            corpsCostaudView.setBackgroundResource(0);
        } else {
            corpsMinceView.setBackgroundResource(0);
            corpsNormalView.setBackgroundResource(0);
            corpsCostaudView.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
    }

    public void selectHomme(View v) {
        isFemme = false;
        corpsHommeView.setBackgroundResource(R.drawable.rounded_light_gray_background);
        corpsFemmeView.setBackgroundResource(0);
    }

    public void selectFemme(View v) {
        isFemme = true;
        corpsHommeView.setBackgroundResource(0);
        corpsFemmeView.setBackgroundResource(R.drawable.rounded_light_gray_background);
    }

    public void selectMince(View v) {
        corpulence = Corpulence.MINCE;
        corpsMinceView.setBackgroundResource(R.drawable.rounded_light_gray_background);
        corpsNormalView.setBackgroundResource(0);
        corpsCostaudView.setBackgroundResource(0);
    }

    public void selectNormal(View v) {
        corpulence = Corpulence.NORMAL;
        corpsMinceView.setBackgroundResource(0);
        corpsNormalView.setBackgroundResource(R.drawable.rounded_light_gray_background);
        corpsCostaudView.setBackgroundResource(0);
    }

    public void selectCostaud(View v) {
        corpulence = Corpulence.COSTAUD;
        corpsMinceView.setBackgroundResource(0);
        corpsNormalView.setBackgroundResource(0);
        corpsCostaudView.setBackgroundResource(R.drawable.rounded_light_gray_background);
    }

    public void changerCouleurPeau(View view) {
        Button button = (Button) view;
        int couleur = button.getBackgroundTintList() != null
                ? button.getBackgroundTintList().getDefaultColor()
                : Color.TRANSPARENT;
        couleurPeau = couleur;
        corpsView.setColorFilter(couleurPeau, PorterDuff.Mode.MULTIPLY);
    }
}

enum Corpulence {
    MINCE,
    NORMAL,
    COSTAUD
}
