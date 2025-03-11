package com.sdis.bilan.lsf.police;

import android.os.Bundle;

import com.sdis.bilan.lsf.databinding.DocumentsBinding;

public class DocumentsPoliceActivity extends BasePoliceActivity {

    private DocumentsBinding documentsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        documentsBinding = DocumentsBinding.inflate(getLayoutInflater());
        setContentView(documentsBinding.getRoot());
    }

}
