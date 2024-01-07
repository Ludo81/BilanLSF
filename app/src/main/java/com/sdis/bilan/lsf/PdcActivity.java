package com.sdis.bilan.lsf;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.core.text.HtmlCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PdcActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdc);

        try {
            InputStream is = getResources().getAssets().open("pdc.html");
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");
            String content = scanner.hasNext() ? scanner.next() : "";
            is.close();

            TextView textView = findViewById(R.id.pdctext);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(Html.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void retour(View view) {
        finish();
    }
}