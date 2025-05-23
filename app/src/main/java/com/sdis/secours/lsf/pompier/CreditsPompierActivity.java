package com.sdis.secours.lsf.pompier;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.CreditsBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CreditsPompierActivity extends BasePompierActivity {

    CreditsBinding creditsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        creditsBinding = CreditsBinding.inflate(getLayoutInflater());
        setContentView(creditsBinding.getRoot());

        Logger.write(this, "Chargement Crédits");

        try {
            InputStream is = getResources().getAssets().open("credits.html");
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");
            String content = scanner.hasNext() ? scanner.next() : "";
            is.close();

            TextView textView = findViewById(R.id.creditstext);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(Html.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
