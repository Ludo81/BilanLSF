package com.sdis.bilan.lsf;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayout;
import com.sdis.bilan.lsf.databinding.ClavierInverseBinding;

public class ClavierInverseActivity extends BaseActivity {

    ClavierInverseBinding clavierInverseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clavierInverseBinding = ClavierInverseBinding.inflate(getLayoutInflater());
        setContentView(clavierInverseBinding.getRoot());

        EditText editTextInput = findViewById(R.id.editTextInput);
        FlexboxLayout imageContainer = findViewById(R.id.imageContainer);

        editTextInput.requestFocus();

        editTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                imageContainer.removeAllViews();
                String texte = s.toString();
                for (int i = 0; i < texte.length(); i++) {
                    char letter = texte.charAt(i);
                    addImage(letter, imageContainer);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void addImage(char character, FlexboxLayout container) {
        if (Character.isLetter(character)) {
            String letterName = String.valueOf(character).toLowerCase();
            int imageResId = getResources().getIdentifier(letterName, "drawable", getPackageName());
            addImage(imageResId, container);
        } else if (Character.isSpaceChar(character)) {
            addImage(R.drawable.space_bar, container);
        } else if (Character.isDigit(character)) {
            switch (character) {
                case '0':
                    addImage(R.drawable.zero, container);
                    break;
                case '1':
                    addImage(R.drawable.un, container);
                    break;
                case '2':
                    addImage(R.drawable.deux, container);
                    break;
                case '3':
                    addImage(R.drawable.trois, container);
                    break;
                case '4':
                    addImage(R.drawable.quatre, container);
                    break;
                case '5':
                    addImage(R.drawable.cinq, container);
                    break;
                case '6':
                    addImage(R.drawable.six, container);
                    break;
                case '7':
                    addImage(R.drawable.sept, container);
                    break;
                case '8':
                    addImage(R.drawable.huit, container);
                    break;
                case '9':
                    addImage(R.drawable.neuf, container);
                    break;
            }
        }
    }

    private void addImage(int imageResId, FlexboxLayout container) {
        if (imageResId != 0) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageResId);
            imageView.setScaleType(ImageView.ScaleType.FIT_END);

            FlexboxLayout.LayoutParams params;
            params = new FlexboxLayout.LayoutParams(100, 100);
            params.setMargins(8, 8, 8, 8);

            imageView.setLayoutParams(params);

            container.addView(imageView);
        }
    }

    public void inverser(View view) {
        Intent intent = new Intent(ClavierInverseActivity.this, ClavierActivity.class);
        startActivity(intent);
    }

}