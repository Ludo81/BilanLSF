package com.sdis.secours.lsf.police;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.PdcBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PdcPoliceActivity extends BasePoliceActivity {

    PdcBinding pdcBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pdcBinding = PdcBinding.inflate(getLayoutInflater());
        setContentView(pdcBinding.getRoot());

        Logger.write(this, "Chargement PDC");

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
}
