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
import com.sdis.bilan.lsf.databinding.DescriptionPhysiqueVisageBinding;
import com.sdis.bilan.lsf.databinding.DetailsVisageBinding;

import java.util.List;

public class DescriptionPhysiquePoliceActivity extends BasePoliceActivity {

    FrameLayout container;

    private boolean isFemme = false;

    private ImageView corpsView;

    private ImageView corpsHommeView;
    private ImageView corpsFemmeView;

    private ImageView corpsMinceView;
    private ImageView corpsNormalView;
    private ImageView corpsCostaudView;

    private ImageView cheveuxView;
    private ImageView visageView;
    private ImageView yeuxView;
    private ImageView nezView;
    private ImageView moustacheView;
    private ImageView boucheView;
    private ImageView barbeView;

    private int cheveuxSelected = 0;
    private int visageSelected = 0;
    private int yeuxSelected = 0;
    private int nezSelected = 0;
    private int moustacheSelected = 0;
    private int boucheSelected = 0;
    private int barbeSelected = 0;

    private int couleurPeau = 0;

    private SeekBar tailleView;
    private int taille = 50;

    private Corpulence corpulence = Corpulence.NORMAL;

    private List<Integer> listeCheveuxHomme = List.of(R.drawable.cheveux_homme_1, R.drawable.cheveux_homme_2, R.drawable.cheveux_homme_3, R.drawable.cheveux_homme_4,
            R.drawable.cheveux_homme_5, R.drawable.cheveux_homme_6, R.drawable.cheveux_homme_7, R.drawable.cheveux_homme_8, R.drawable.cheveux_homme_9, R.drawable.cheveux_homme_10
            , R.drawable.cheveux_homme_11, R.drawable.cheveux_homme_12, R.drawable.cheveux_homme_3, R.drawable.cheveux_homme_14, R.drawable.cheveux_homme_15, R.drawable.cheveux_homme_16);

    private List<Integer> listeCheveuxFemme = List.of(R.drawable.cheveux_femme_1, R.drawable.cheveux_femme_2, R.drawable.cheveux_femme_3, R.drawable.cheveux_femme_4
            , R.drawable.cheveux_femme_5, R.drawable.cheveux_femme_6, R.drawable.cheveux_femme_7, R.drawable.cheveux_femme_8, R.drawable.cheveux_femme_9, R.drawable.cheveux_femme_10
            , R.drawable.cheveux_femme_11, R.drawable.cheveux_femme_12, R.drawable.cheveux_femme_13, R.drawable.cheveux_femme_14, R.drawable.cheveux_femme_15, R.drawable.cheveux_femme_16
            , R.drawable.cheveux_femme_17);

    private List<Integer> listeVisages = List.of(R.drawable.forme_1, R.drawable.forme_2, R.drawable.forme_3, R.drawable.forme_4);

    private List<Integer> listeYeuxHomme = List.of(R.drawable.yeux_homme_1, R.drawable.yeux_homme_2, R.drawable.yeux_homme_3, R.drawable.yeux_homme_4,
            R.drawable.yeux_homme_5, R.drawable.yeux_homme_6);

    private List<Integer> listeYeuxFemme = List.of(R.drawable.yeux_femme_1, R.drawable.yeux_femme_2, R.drawable.yeux_femme_3, R.drawable.yeux_femme_4,
            R.drawable.yeux_femme_5, R.drawable.yeux_femme_6);

    private List<Integer> listeNez = List.of(R.drawable.nez_1, R.drawable.nez_2, R.drawable.nez_3, R.drawable.nez_4, R.drawable.nez_5, R.drawable.nez_6
            , R.drawable.nez_7, R.drawable.nez_8, R.drawable.nez_9, R.drawable.nez_10, R.drawable.nez_11, R.drawable.nez_12, R.drawable.nez_13
            , R.drawable.nez_14);

    private List<Integer> listeMoustaches = List.of(0, R.drawable.moustache_1, R.drawable.moustache_2, R.drawable.moustache_3, R.drawable.moustache_4,
            R.drawable.moustache_5);

    private List<Integer> listeBouches = List.of(R.drawable.bouche_1, R.drawable.bouche_2, R.drawable.bouche_3, R.drawable.bouche_4, R.drawable.bouche_5
            , R.drawable.bouche_6, R.drawable.bouche_7, R.drawable.bouche_8, R.drawable.bouche_9, R.drawable.bouche_10, R.drawable.bouche_11, R.drawable.bouche_12
            , R.drawable.bouche_13, R.drawable.bouche_14, R.drawable.bouche_15, R.drawable.bouche_16, R.drawable.bouche_17, R.drawable.bouche_18, R.drawable.bouche_19);

    private List<Integer> listeBarbes = List.of(0, R.drawable.barbe_1, R.drawable.barbe_2, R.drawable.barbe_3, R.drawable.barbe_4, R.drawable.barbe_5, R.drawable.barbe_6
            , R.drawable.barbe_7, R.drawable.barbe_8, R.drawable.barbe_9);

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

    public void goToVisage(View v) {
        container.removeAllViews();
        container.addView(DescriptionPhysiqueVisageBinding.inflate(getLayoutInflater()).getRoot());

        cheveuxView = findViewById(R.id.cheveux);
        visageView = findViewById(R.id.visage);
        yeuxView = findViewById(R.id.yeux);
        nezView = findViewById(R.id.nez);
        moustacheView = findViewById(R.id.moustache);
        boucheView = findViewById(R.id.bouche);
        barbeView = findViewById(R.id.barbe);

        cheveuxSelected = 0;
        visageSelected = 0;
        yeuxSelected = 0;
        nezSelected = 0;
        moustacheSelected = 0;
        boucheSelected = 0;
        barbeSelected = 0;

        if (isFemme) {
            cheveuxView.setImageResource(R.drawable.cheveux_femme_1);
            yeuxView.setImageResource(R.drawable.yeux_femme_1);
        }
    }

    public void goToDetailsVisage(View v) {
        container.removeAllViews();
        container.addView(DetailsVisageBinding.inflate(getLayoutInflater()).getRoot());
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

    public void cheveuxPrecedent(View view) {
        if (isFemme) {
            if (cheveuxSelected == 0) {
                cheveuxSelected = listeCheveuxFemme.size() - 1;
            } else {
                cheveuxSelected -= 1;
            }
            cheveuxView.setImageResource(listeCheveuxFemme.get(cheveuxSelected));
        } else {
            if (cheveuxSelected == 0) {
                cheveuxSelected = listeCheveuxHomme.size() - 1;
            } else {
                cheveuxSelected -= 1;
            }
            cheveuxView.setImageResource(listeCheveuxHomme.get(cheveuxSelected));
        }
    }

    public void cheveuxSuivant(View view) {
        if (isFemme) {
            if (cheveuxSelected == listeCheveuxFemme.size() - 1) {
                cheveuxSelected = 0;
            } else {
                cheveuxSelected += 1;
            }
            cheveuxView.setImageResource(listeCheveuxFemme.get(cheveuxSelected));
        } else {
            if (cheveuxSelected == listeCheveuxHomme.size() - 1) {
                cheveuxSelected = 0;
            } else {
                cheveuxSelected += 1;
            }
            cheveuxView.setImageResource(listeCheveuxHomme.get(cheveuxSelected));
        }
    }

    public void visagePrecedent(View view) {
        if (visageSelected == 0) {
            visageSelected = listeVisages.size() - 1;
        } else {
            visageSelected -= 1;
        }
        visageView.setImageResource(listeVisages.get(visageSelected));
    }

    public void visageSuivant(View view) {
        if (visageSelected == listeVisages.size() - 1) {
            visageSelected = 0;
        } else {
            visageSelected += 1;
        }
        visageView.setImageResource(listeVisages.get(visageSelected));
    }

    public void yeuxPrecedent(View view) {
        if (isFemme) {
            if (yeuxSelected == 0) {
                yeuxSelected = listeYeuxFemme.size() - 1;
            } else {
                yeuxSelected -= 1;
            }
            yeuxView.setImageResource(listeYeuxFemme.get(yeuxSelected));
        } else {
            if (yeuxSelected == 0) {
                yeuxSelected = listeYeuxHomme.size() - 1;
            } else {
                yeuxSelected -= 1;
            }
            yeuxView.setImageResource(listeYeuxHomme.get(yeuxSelected));
        }
    }

    public void yeuxSuivant(View view) {
        if (isFemme) {
            if (yeuxSelected == listeYeuxFemme.size() - 1) {
                yeuxSelected = 0;
            } else {
                yeuxSelected += 1;
            }
            yeuxView.setImageResource(listeYeuxFemme.get(yeuxSelected));
        } else {
            if (yeuxSelected == listeYeuxHomme.size() - 1) {
                yeuxSelected = 0;
            } else {
                yeuxSelected += 1;
            }
            yeuxView.setImageResource(listeYeuxHomme.get(yeuxSelected));
        }
    }

    public void nezPrecedent(View view) {
        if (nezSelected == 0) {
            nezSelected = listeNez.size() - 1;
        } else {
            nezSelected -= 1;
        }
        nezView.setImageResource(listeNez.get(nezSelected));
    }

    public void nezSuivant(View view) {
        if (nezSelected == listeNez.size() - 1) {
            nezSelected = 0;
        } else {
            nezSelected += 1;
        }
        nezView.setImageResource(listeNez.get(nezSelected));
    }

    public void moustachePrecedente(View view) {
        if (!isFemme) {
            if (moustacheSelected == 0) {
                moustacheSelected = listeMoustaches.size() - 1;
            } else {
                moustacheSelected -= 1;
            }
            moustacheView.setImageResource(listeMoustaches.get(moustacheSelected));
        }
    }

    public void moustacheSuivante(View view) {
        if (!isFemme) {
            if (moustacheSelected == listeMoustaches.size() - 1) {
                moustacheSelected = 0;
            } else {
                moustacheSelected += 1;
            }
            moustacheView.setImageResource(listeMoustaches.get(moustacheSelected));
        }
    }

    public void bouchePredecente(View view) {
        if (boucheSelected == 0) {
            boucheSelected = listeBouches.size() - 1;
        } else {
            boucheSelected -= 1;
        }
        boucheView.setImageResource(listeBouches.get(boucheSelected));
    }

    public void boucheSuivante(View view) {
        if (boucheSelected == listeBouches.size() - 1) {
            boucheSelected = 0;
        } else {
            boucheSelected += 1;
        }
        boucheView.setImageResource(listeBouches.get(boucheSelected));
    }

    public void barbePrecedente(View view) {
        if (!isFemme) {
            if (barbeSelected == 0) {
                barbeSelected = listeBarbes.size() - 1;
            } else {
                barbeSelected -= 1;
            }
            barbeView.setImageResource(listeBarbes.get(barbeSelected));
        }
    }

    public void barbeSuivante(View view) {
        if (!isFemme) {
            if (barbeSelected == listeBarbes.size() - 1) {
                barbeSelected = 0;
            } else {
                barbeSelected += 1;
            }
            barbeView.setImageResource(listeBarbes.get(barbeSelected));
        }
    }
}

enum Corpulence {
    MINCE,
    NORMAL,
    COSTAUD
}
