package com.sdis.secours.lsf.pompier;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.CorpsBinding;

import java.util.Stack;

public class CorpsActivity extends BasePompierActivity {

    CorpsBinding corpsBinding;

    private Stack<View> undoStack = new Stack<>();
    private Stack<View> redoStack = new Stack<>();

    ImageView undoButton;
    ImageView redoButton;

    ImageView echelle_haut_plus;
    ImageView echelle_haut;
    ImageView echelle_moyen_haut;
    ImageView echelle_moyen_bas;
    ImageView echelle_bas;
    ImageView echelle_bas_plus;

    String couleur = "#0dc27f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        corpsBinding = CorpsBinding.inflate(getLayoutInflater());
        setContentView(corpsBinding.getRoot());

        Logger.write(this, "Chargement Corps");

        undoButton = findViewById(R.id.undo);
        redoButton = findViewById(R.id.redo);

        ImageView imageView = findViewById(R.id.corps);
        imageView.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP) {
                addPastille(event.getX(), event.getY());
            }
            return true;
        });

        echelle_haut_plus = findViewById(R.id.echelle_haut_plus);
        echelle_haut = findViewById(R.id.echelle_haut);
        echelle_moyen_haut = findViewById(R.id.echelle_moyen_haut);
        echelle_moyen_bas = findViewById(R.id.echelle_moyen_bas);
        echelle_bas = findViewById(R.id.echelle_bas);
        echelle_bas_plus = findViewById(R.id.echelle_bas_plus);

        SeekBar regle = findViewById(R.id.regle_douleur);
        regle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (0 <= progress && progress <= 16) {
                    resetEchelle();
                    couleur = "#0dc27f";
                    changeColorPoint(couleur);
                    echelle_bas_plus.setImageResource(R.drawable.echelle_bas_plus_selection);
                } else if (17 <= progress && progress <= 33) {
                    resetEchelle();
                    couleur = "#8ddd64";
                    changeColorPoint(couleur);
                    echelle_bas.setImageResource(R.drawable.echelle_bas_selection);
                } else if (34 <= progress && progress <= 50) {
                    resetEchelle();
                    couleur = "#ffdd56";
                    changeColorPoint(couleur);
                    echelle_moyen_bas.setImageResource(R.drawable.echelle_moyen_bas_selection);
                } else if (51 <= progress && progress <= 67) {
                    resetEchelle();
                    couleur = "#ffb758";
                    changeColorPoint(couleur);
                    echelle_moyen_haut.setImageResource(R.drawable.echelle_moyen_haut_selection);
                } else if (68 <= progress && progress <= 84) {
                    resetEchelle();
                    couleur = "#ff8e4b";
                    changeColorPoint(couleur);
                    echelle_haut.setImageResource(R.drawable.echelle_haut_selection);
                } else {
                    resetEchelle();
                    couleur = "#e05858";
                    changeColorPoint(couleur);
                    echelle_haut_plus.setImageResource(R.drawable.echelle_haut_plus_selection);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void resetEchelle() {
        echelle_haut_plus.setImageResource(R.drawable.echelle_haut_plus);
        echelle_haut.setImageResource(R.drawable.echelle_haut);
        echelle_moyen_haut.setImageResource(R.drawable.echelle_moyen_haut);
        echelle_moyen_bas.setImageResource(R.drawable.echelle_moyen_bas);
        echelle_bas.setImageResource(R.drawable.echelle_bas);
        echelle_bas_plus.setImageResource(R.drawable.echelle_bas_plus);
    }

    private void changeColorPoint(String color) {
        if (!undoStack.isEmpty()) {
            View lastPastille = undoStack.peek();
            GradientDrawable shape = (GradientDrawable) lastPastille.getBackground();
            shape.setColor(Color.parseColor(color));
        }
    }

    private void addPastille(float x, float y) {
        Context context = getApplicationContext();
        FrameLayout frame = findViewById(R.id.frame);

        View pastille = new View(context);
        pastille.setLayoutParams(new FrameLayout.LayoutParams(50, 50));
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setColor(Color.parseColor(couleur));
        pastille.setBackground(shape);

        pastille.setX(x - 10);
        pastille.setY(y - 30);

        frame.addView(pastille);
        undoStack.push(pastille);
        redoStack.clear();

        updateUndoRedo();
    }

    public void onClickUndo(View view) {
        if (!undoStack.isEmpty()) {
            View pastille = undoStack.pop();
            FrameLayout frame = findViewById(R.id.frame);
            frame.removeView(pastille);
            redoStack.push(pastille);

            updateUndoRedo();
        }
    }

    public void onClickRedo(View view) {
        if (!redoStack.isEmpty()) {
            View pastille = redoStack.pop();
            FrameLayout frame = findViewById(R.id.frame);
            frame.addView(pastille);
            undoStack.push(pastille);

            updateUndoRedo();
        }
    }

    public void onClickVideo(View view) {
        Intent intent = new Intent(CorpsActivity.this, VideoPompierActivity.class);
        intent.putExtra("VIDEO_NAME", "bilan_primaire_montrez_moi_ou_vous_avez_mal");
        startActivity(intent);
    }

    private void updateUndoRedo() {
        if (undoStack.isEmpty()) {
            undoButton.setImageResource(R.drawable.undo_disabled);
        } else {
            undoButton.setImageResource(R.drawable.undo);
        }
        if (redoStack.isEmpty()) {
            redoButton.setImageResource(R.drawable.redo_disabled);
        } else {
            redoButton.setImageResource(R.drawable.redo);
        }
    }

}
