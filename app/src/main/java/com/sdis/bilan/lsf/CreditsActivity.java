package com.sdis.bilan.lsf;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.sdis.bilan.lsf.databinding.CreditsBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CreditsActivity extends BaseActivity {

    CreditsBinding creditsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        creditsBinding = CreditsBinding.inflate(getLayoutInflater());
        setContentView(creditsBinding.getRoot());

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
