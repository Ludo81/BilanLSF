package com.sdis.secours.lsf.police;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.DescriptionPhyisiqueCorpsABinding;
import com.sdis.secours.lsf.databinding.DescriptionPhyisiqueCorpsBBinding;
import com.sdis.secours.lsf.databinding.DescriptionPhyisiqueCorpsCBinding;
import com.sdis.secours.lsf.databinding.DescriptionPhyisiqueCorpsDBinding;
import com.sdis.secours.lsf.databinding.DescriptionPhysiqueDetailsVisageBinding;
import com.sdis.secours.lsf.databinding.DescriptionPhysiqueVetementsBinding;
import com.sdis.secours.lsf.databinding.DescriptionPhysiqueVisageBinding;

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

    private ImageButton detail1View;
    private ImageButton detail2View;
    private ImageButton detail3View;
    private ImageButton detail4View;
    private ImageButton detail5View;
    private ImageButton detail6View;
    private ImageButton detail7View;
    private ImageButton detail8View;
    private ImageButton lunettesView;
    private ImageButton cagouleView;

    private boolean isDetail1Selected = false;
    private boolean isDetail2Selected = false;
    private boolean isDetail3Selected = false;
    private boolean isDetail4Selected = false;
    private boolean isDetail5Selected = false;
    private boolean isDetail6Selected = false;
    private boolean isDetail7Selected = false;
    private boolean isDetail8Selected = false;
    private boolean isLunettesSelected = false;
    private boolean isCagouleSelected = false;

    private ImageView hautView;
    private ImageView basView;
    private ImageView chaussuresView;

    private ImageView couleurHautView;
    private ImageView couleurBasView;
    private ImageView couleurChaussuresView;

    private int cheveuxSelected = 0;
    private int visageSelected = 0;
    private int yeuxSelected = 0;
    private int nezSelected = 0;
    private int moustacheSelected = 0;
    private int boucheSelected = 0;
    private int barbeSelected = 0;

    private int couleurCheveux = Color.WHITE;
    private int couleurYeux = Color.WHITE;
    private int couleurMoustache = Color.WHITE;
    private int couleurBarbe = Color.WHITE;

    private int couleurPeau = 0;

    private SeekBar tailleView;
    private TextView tailleText;
    private int taille = 110;

    private Corpulence corpulence = Corpulence.NORMAL;

    private final List<Integer> listeCheveuxHomme = List.of(R.drawable.cheveux_homme_1, R.drawable.cheveux_homme_2, R.drawable.cheveux_homme_3, R.drawable.cheveux_homme_4,
            R.drawable.cheveux_homme_5, R.drawable.cheveux_homme_6, R.drawable.cheveux_homme_7, R.drawable.cheveux_homme_8, R.drawable.cheveux_homme_9, R.drawable.cheveux_homme_10
            , R.drawable.cheveux_homme_11, R.drawable.cheveux_homme_12, R.drawable.cheveux_homme_13, R.drawable.cheveux_homme_14, R.drawable.cheveux_homme_15, R.drawable.cheveux_homme_16);

    private final List<Integer> listeCheveuxFemme = List.of(R.drawable.cheveux_femme_1, R.drawable.cheveux_femme_2, R.drawable.cheveux_femme_3, R.drawable.cheveux_femme_4
            , R.drawable.cheveux_femme_5, R.drawable.cheveux_femme_6, R.drawable.cheveux_femme_7, R.drawable.cheveux_femme_8, R.drawable.cheveux_femme_9, R.drawable.cheveux_femme_10
            , R.drawable.cheveux_femme_11, R.drawable.cheveux_femme_12, R.drawable.cheveux_femme_13, R.drawable.cheveux_femme_14, R.drawable.cheveux_femme_15, R.drawable.cheveux_femme_16
            , R.drawable.cheveux_femme_17);

    private final List<Integer> listeVisages = List.of(R.drawable.forme_1, R.drawable.forme_2, R.drawable.forme_3, R.drawable.forme_4);

    private final List<Integer> listeYeuxHomme = List.of(R.drawable.yeux_homme_1, R.drawable.yeux_homme_2, R.drawable.yeux_homme_3, R.drawable.yeux_homme_4,
            R.drawable.yeux_homme_5, R.drawable.yeux_homme_6);

    private final List<Integer> listeYeuxFemme = List.of(R.drawable.yeux_femme_1, R.drawable.yeux_femme_2, R.drawable.yeux_femme_3, R.drawable.yeux_femme_4,
            R.drawable.yeux_femme_5, R.drawable.yeux_femme_6);

    private final List<Integer> listeNez = List.of(R.drawable.nez_1, R.drawable.nez_2, R.drawable.nez_3, R.drawable.nez_4, R.drawable.nez_5, R.drawable.nez_6
            , R.drawable.nez_7, R.drawable.nez_8, R.drawable.nez_9, R.drawable.nez_10, R.drawable.nez_11, R.drawable.nez_12, R.drawable.nez_13
            , R.drawable.nez_14);

    private final List<Integer> listeMoustaches = List.of(0, R.drawable.moustache_1, R.drawable.moustache_2, R.drawable.moustache_3, R.drawable.moustache_4,
            R.drawable.moustache_5);

    private final List<Integer> listeBouches = List.of(R.drawable.bouche_1, R.drawable.bouche_2, R.drawable.bouche_3, R.drawable.bouche_4, R.drawable.bouche_5
            , R.drawable.bouche_6, R.drawable.bouche_7, R.drawable.bouche_8, R.drawable.bouche_9, R.drawable.bouche_10, R.drawable.bouche_11, R.drawable.bouche_12
            , R.drawable.bouche_13, R.drawable.bouche_14, R.drawable.bouche_15, R.drawable.bouche_16, R.drawable.bouche_17, R.drawable.bouche_18, R.drawable.bouche_19);

    private final List<Integer> listeBarbes = List.of(0, R.drawable.barbe_1, R.drawable.barbe_2, R.drawable.barbe_3, R.drawable.barbe_4, R.drawable.barbe_5, R.drawable.barbe_6
            , R.drawable.barbe_7, R.drawable.barbe_8, R.drawable.barbe_9);

    private int hautSelected = 0;
    private int basSelected = 0;
    private int chaussuresSelected = 0;

    private final List<Integer> listeHaut = List.of(R.drawable.haut_1, R.drawable.haut_2, R.drawable.haut_3, R.drawable.haut_4, R.drawable.haut_5, R.drawable.haut_6,
            R.drawable.haut_7, R.drawable.haut_8, R.drawable.haut_9, R.drawable.haut_10);

    private final List<Integer> listeBas = List.of(R.drawable.bas_1, R.drawable.bas_2, R.drawable.bas_3, R.drawable.bas_4);

    private final List<Integer> listeChaussures = List.of(R.drawable.chaussures_1, R.drawable.chaussures_2, R.drawable.chaussures_3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(DescriptionPhyisiqueCorpsABinding.inflate(getLayoutInflater()).getRoot());
        container = findViewById(R.id.content);

        Logger.write(this, "Chargement Description Physique");

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

        tailleText = findViewById(R.id.tailleText);

        tailleView = findViewById(R.id.taille);
        tailleView.setProgress(taille);
        tailleText.setText(taille + 60 + " cm");
        tailleView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                taille = progress;
                tailleText.setText(taille + 60 + " cm");
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

        if (isFemme) {
            cheveuxView.setImageResource(listeCheveuxFemme.get(cheveuxSelected));
            yeuxView.setImageResource(listeYeuxFemme.get(yeuxSelected));
            moustacheView.setImageResource(0);
            barbeView.setImageResource(0);
        } else {
            cheveuxView.setImageResource(listeCheveuxHomme.get(cheveuxSelected));
            yeuxView.setImageResource(listeYeuxHomme.get(yeuxSelected));
            moustacheView.setImageResource(listeMoustaches.get(moustacheSelected));
            barbeView.setImageResource(listeBarbes.get(barbeSelected));
        }
        visageView.setImageResource(listeVisages.get(visageSelected));
        nezView.setImageResource(listeNez.get(nezSelected));
        boucheView.setImageResource(listeBouches.get(boucheSelected));
    }

    public void goToDetailsVisage(View v) {
        container.removeAllViews();
        container.addView(DescriptionPhysiqueDetailsVisageBinding.inflate(getLayoutInflater()).getRoot());

        detail1View = findViewById(R.id.detail1);
        detail2View = findViewById(R.id.detail2);
        detail3View = findViewById(R.id.detail3);
        detail4View = findViewById(R.id.detail4);
        detail5View = findViewById(R.id.detail5);
        detail6View = findViewById(R.id.detail6);
        detail7View = findViewById(R.id.detail7);
        detail8View = findViewById(R.id.detail8);
        lunettesView = findViewById(R.id.lunettes);
        cagouleView = findViewById(R.id.cagoule);

        if (isDetail1Selected) {
            detail1View.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
        if (isDetail2Selected) {
            detail2View.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
        if (isDetail3Selected) {
            detail3View.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
        if (isDetail4Selected) {
            detail4View.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
        if (isDetail5Selected) {
            detail5View.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
        if (isDetail6Selected) {
            detail6View.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
        if (isDetail7Selected) {
            detail7View.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
        if (isDetail8Selected) {
            detail8View.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
        if (isLunettesSelected) {
            lunettesView.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
        if (isCagouleSelected) {
            cagouleView.setBackgroundResource(R.drawable.rounded_light_gray_background);
        }
    }

    public void goToVetements(View v) {
        container.removeAllViews();
        container.addView(DescriptionPhysiqueVetementsBinding.inflate(getLayoutInflater()).getRoot());

        hautView = findViewById(R.id.haut);
        basView = findViewById(R.id.bas);
        chaussuresView = findViewById(R.id.chaussures);

        couleurHautView = findViewById(R.id.couleur_haut);
        couleurBasView = findViewById(R.id.couleur_bas);
        couleurChaussuresView = findViewById(R.id.couleur_chaussures);

        couleurHautView.setOnTouchListener((vHaut, event) -> {

            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {

                int viewX = (int) event.getX();
                int viewY = (int) event.getY();

                int viewWidth = couleurHautView.getWidth();
                int viewHeight = couleurHautView.getHeight();

                Bitmap image = ((BitmapDrawable) couleurHautView.getDrawable()).getBitmap();

                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int imageX = (int) ((float) viewX * ((float) imageWidth / (float) viewWidth));
                int imageY = (int) ((float) viewY * ((float) imageHeight / (float) viewHeight));

                int color = Color.WHITE;
                try {
                    color = image.getPixel(imageX, imageY);
                } catch (Exception e) {

                }

                hautView.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            }
            return true;
        });

        couleurBasView.setOnTouchListener((vBas, event) -> {

            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {

                int viewX = (int) event.getX();
                int viewY = (int) event.getY();

                int viewWidth = couleurBasView.getWidth();
                int viewHeight = couleurBasView.getHeight();

                Bitmap image = ((BitmapDrawable) couleurBasView.getDrawable()).getBitmap();

                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int imageX = (int) ((float) viewX * ((float) imageWidth / (float) viewWidth));
                int imageY = (int) ((float) viewY * ((float) imageHeight / (float) viewHeight));

                int color = Color.WHITE;
                try {
                    color = image.getPixel(imageX, imageY);
                } catch (Exception e) {

                }

                basView.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            }
            return true;
        });

        couleurChaussuresView.setOnTouchListener((vChaussures, event) -> {

            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {

                int viewX = (int) event.getX();
                int viewY = (int) event.getY();

                int viewWidth = couleurChaussuresView.getWidth();
                int viewHeight = couleurChaussuresView.getHeight();

                Bitmap image = ((BitmapDrawable) couleurChaussuresView.getDrawable()).getBitmap();

                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int imageX = (int) ((float) viewX * ((float) imageWidth / (float) viewWidth));
                int imageY = (int) ((float) viewY * ((float) imageHeight / (float) viewHeight));

                int color = Color.WHITE;
                try {
                    color = image.getPixel(imageX, imageY);
                } catch (Exception e) {

                }

                chaussuresView.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            }
            return true;
        });
    }

    public void selectHomme(View v) {
        isFemme = false;
        corpsHommeView.setBackgroundResource(R.drawable.rounded_light_gray_background);
        corpsFemmeView.setBackgroundResource(0);

        cheveuxSelected = 0;
        visageSelected = 0;
        yeuxSelected = 0;
        nezSelected = 0;
        moustacheSelected = 0;
        boucheSelected = 0;
        barbeSelected = 0;
    }

    public void selectFemme(View v) {
        isFemme = true;
        corpsHommeView.setBackgroundResource(0);
        corpsFemmeView.setBackgroundResource(R.drawable.rounded_light_gray_background);

        cheveuxSelected = 0;
        visageSelected = 0;
        yeuxSelected = 0;
        nezSelected = 0;
        moustacheSelected = 0;
        boucheSelected = 0;
        barbeSelected = 0;
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

    public void openColorCheveux(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.color_picker_layout, null);

        ImageView couleurSelectionneeView = dialogView.findViewById(R.id.couleurSelectionnee);
        couleurSelectionneeView.setBackgroundColor(couleurCheveux);

        ImageView colorPicker = dialogView.findViewById(R.id.colorPicker);
        colorPicker.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {

                int viewX = (int) event.getX();
                int viewY = (int) event.getY();

                int viewWidth = colorPicker.getWidth();
                int viewHeight = colorPicker.getHeight();

                Bitmap image = ((BitmapDrawable) colorPicker.getDrawable()).getBitmap();

                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int imageX = (int) ((float) viewX * ((float) imageWidth / (float) viewWidth));
                int imageY = (int) ((float) viewY * ((float) imageHeight / (float) viewHeight));

                try {
                    couleurCheveux = image.getPixel(imageX, imageY);
                } catch (Exception e) {

                }

                couleurSelectionneeView.setBackgroundColor(couleurCheveux);
                cheveuxView.setColorFilter(couleurCheveux, PorterDuff.Mode.MULTIPLY);
            }
            return true;
        });

        builder.setView(dialogView)
                .setPositiveButton("Fermer", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void openColorYeux(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.color_picker_layout, null);

        ImageView couleurSelectionneeView = dialogView.findViewById(R.id.couleurSelectionnee);
        couleurSelectionneeView.setBackgroundColor(couleurYeux);

        ImageView colorPicker = dialogView.findViewById(R.id.colorPicker);
        colorPicker.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {

                int viewX = (int) event.getX();
                int viewY = (int) event.getY();

                int viewWidth = colorPicker.getWidth();
                int viewHeight = colorPicker.getHeight();

                Bitmap image = ((BitmapDrawable) colorPicker.getDrawable()).getBitmap();

                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int imageX = (int) ((float) viewX * ((float) imageWidth / (float) viewWidth));
                int imageY = (int) ((float) viewY * ((float) imageHeight / (float) viewHeight));

                try {
                    couleurYeux = image.getPixel(imageX, imageY);
                } catch (Exception e) {

                }

                couleurSelectionneeView.setBackgroundColor(couleurYeux);
                yeuxView.setColorFilter(couleurYeux, PorterDuff.Mode.MULTIPLY);
            }
            return true;
        });

        builder.setView(dialogView)
                .setPositiveButton("Fermer", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void openColorMoustache(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.color_picker_layout, null);

        ImageView couleurSelectionneeView = dialogView.findViewById(R.id.couleurSelectionnee);
        couleurSelectionneeView.setBackgroundColor(couleurMoustache);

        ImageView colorPicker = dialogView.findViewById(R.id.colorPicker);
        colorPicker.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {

                int viewX = (int) event.getX();
                int viewY = (int) event.getY();

                int viewWidth = colorPicker.getWidth();
                int viewHeight = colorPicker.getHeight();

                Bitmap image = ((BitmapDrawable) colorPicker.getDrawable()).getBitmap();

                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int imageX = (int) ((float) viewX * ((float) imageWidth / (float) viewWidth));
                int imageY = (int) ((float) viewY * ((float) imageHeight / (float) viewHeight));

                try {
                    couleurMoustache = image.getPixel(imageX, imageY);
                } catch (Exception e) {

                }

                couleurSelectionneeView.setBackgroundColor(couleurMoustache);
                moustacheView.setColorFilter(couleurMoustache, PorterDuff.Mode.MULTIPLY);
            }
            return true;
        });

        builder.setView(dialogView)
                .setPositiveButton("Fermer", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void openColorBarbe(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.color_picker_layout, null);

        ImageView couleurSelectionneeView = dialogView.findViewById(R.id.couleurSelectionnee);
        couleurSelectionneeView.setBackgroundColor(couleurBarbe);

        ImageView colorPicker = dialogView.findViewById(R.id.colorPicker);
        colorPicker.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {

                int viewX = (int) event.getX();
                int viewY = (int) event.getY();

                int viewWidth = colorPicker.getWidth();
                int viewHeight = colorPicker.getHeight();

                Bitmap image = ((BitmapDrawable) colorPicker.getDrawable()).getBitmap();

                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int imageX = (int) ((float) viewX * ((float) imageWidth / (float) viewWidth));
                int imageY = (int) ((float) viewY * ((float) imageHeight / (float) viewHeight));

                try {
                    couleurBarbe = image.getPixel(imageX, imageY);
                } catch (Exception e) {

                }

                couleurSelectionneeView.setBackgroundColor(couleurBarbe);
                barbeView.setColorFilter(couleurBarbe, PorterDuff.Mode.MULTIPLY);
            }
            return true;
        });

        builder.setView(dialogView)
                .setPositiveButton("Fermer", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void selectDetail1(View v) {
        if (isDetail1Selected) {
            detail1View.setBackgroundResource(0);
            isDetail1Selected = false;
        } else {
            detail1View.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isDetail1Selected = true;
        }
    }

    public void selectDetail2(View v) {
        if (isDetail2Selected) {
            detail2View.setBackgroundResource(0);
            isDetail2Selected = false;
        } else {
            detail2View.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isDetail2Selected = true;
        }
    }

    public void selectDetail3(View v) {
        if (isDetail3Selected) {
            detail3View.setBackgroundResource(0);
            isDetail3Selected = false;
        } else {
            detail3View.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isDetail3Selected = true;
        }
    }

    public void selectDetail4(View v) {
        if (isDetail4Selected) {
            detail4View.setBackgroundResource(0);
            isDetail4Selected = false;
        } else {
            detail4View.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isDetail4Selected = true;
        }
    }

    public void selectDetail5(View v) {
        if (isDetail5Selected) {
            detail5View.setBackgroundResource(0);
            isDetail5Selected = false;
        } else {
            detail5View.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isDetail5Selected = true;
        }
    }

    public void selectDetail6(View v) {
        if (isDetail6Selected) {
            detail6View.setBackgroundResource(0);
            isDetail6Selected = false;
        } else {
            detail6View.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isDetail6Selected = true;
        }
    }

    public void selectDetail7(View v) {
        if (isDetail7Selected) {
            detail7View.setBackgroundResource(0);
            isDetail7Selected = false;
        } else {
            detail7View.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isDetail7Selected = true;
        }
    }

    public void selectDetail8(View v) {
        if (isDetail8Selected) {
            detail8View.setBackgroundResource(0);
            isDetail8Selected = false;
        } else {
            detail8View.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isDetail8Selected = true;
        }
    }

    public void selectLunettes(View v) {
        if (isLunettesSelected) {
            lunettesView.setBackgroundResource(0);
            isLunettesSelected = false;
        } else {
            lunettesView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isLunettesSelected = true;
        }
    }

    public void selectCagoule(View v) {
        if (isCagouleSelected) {
            cagouleView.setBackgroundResource(0);
            isCagouleSelected = false;
        } else {
            cagouleView.setBackgroundResource(R.drawable.rounded_light_gray_background);
            isCagouleSelected = true;
        }
    }

    public void hautPrecedent(View view) {
        if (hautSelected == 0) {
            hautSelected = listeHaut.size() - 1;
        } else {
            hautSelected -= 1;
        }
        hautView.setImageResource(listeHaut.get(hautSelected));
    }

    public void hautSuivant(View view) {
        if (hautSelected == listeHaut.size() - 1) {
            hautSelected = 0;
        } else {
            hautSelected += 1;
        }
        hautView.setImageResource(listeHaut.get(hautSelected));
    }

    public void basPrecedent(View view) {
        if (basSelected == 0) {
            basSelected = listeBas.size() - 1;
        } else {
            basSelected -= 1;
        }
        basView.setImageResource(listeBas.get(basSelected));
    }

    public void basSuivant(View view) {
        if (basSelected == listeBas.size() - 1) {
            basSelected = 0;
        } else {
            basSelected += 1;
        }
        basView.setImageResource(listeBas.get(basSelected));
    }

    public void chaussuresPrecedentes(View view) {
        if (chaussuresSelected == 0) {
            chaussuresSelected = listeChaussures.size() - 1;
        } else {
            chaussuresSelected -= 1;
        }
        chaussuresView.setImageResource(listeChaussures.get(chaussuresSelected));
    }

    public void chaussuresSuivantes(View view) {
        if (chaussuresSelected == listeChaussures.size() - 1) {
            chaussuresSelected = 0;
        } else {
            chaussuresSelected += 1;
        }
        chaussuresView.setImageResource(listeChaussures.get(chaussuresSelected));
    }
}

enum Corpulence {
    MINCE,
    NORMAL,
    COSTAUD
}
