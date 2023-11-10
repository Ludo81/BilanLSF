package com.sdis.bilan.lsf;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.core.text.HtmlCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CguActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cgu);

        try {
            InputStream is = getResources().getAssets().open("cgu.html");
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");
            String content = scanner.hasNext() ? scanner.next() : "";
            is.close();

            TextView textView = findViewById(R.id.cgutext);
            textView.setText(Html.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void retour(View view) {
        finish();
    }
}
