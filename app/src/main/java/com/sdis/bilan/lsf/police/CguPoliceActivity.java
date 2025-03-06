package com.sdis.bilan.lsf.police;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.sdis.bilan.lsf.R;
import com.sdis.bilan.lsf.databinding.CguBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CguPoliceActivity extends BasePoliceActivity {

    CguBinding cguBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cguBinding = CguBinding.inflate(getLayoutInflater());
        setContentView(cguBinding.getRoot());

        try {
            InputStream is = getResources().getAssets().open("cgu.html");
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");
            String content = scanner.hasNext() ? scanner.next() : "";
            is.close();

            TextView textView = findViewById(R.id.cgutext);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(Html.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
