package com.sdis.secours.lsf.pompier;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.sdis.secours.lsf.Logger;
import com.sdis.secours.lsf.R;
import com.sdis.secours.lsf.databinding.VersionBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class VersionPompierActivity extends BasePompierActivity {

    VersionBinding versionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        versionBinding = VersionBinding.inflate(getLayoutInflater());
        setContentView(versionBinding.getRoot());

        Logger.write(this, "Chargement Version");

        try {
            InputStream is = getResources().getAssets().open("releasenote.html");
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");
            String content = scanner.hasNext() ? scanner.next() : "";
            is.close();

            TextView textView = findViewById(R.id.releasetext);
            textView.setText(Html.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
