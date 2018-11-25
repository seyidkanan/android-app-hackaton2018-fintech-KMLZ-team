package com.kmlz.optcredit.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kmlz.optcredit.R;

public class OfferInfo extends AppCompatActivity {

    TextView text_view_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_info);

        text_view_content = findViewById(R.id.text_view_content);
        if (getIntent().getStringExtra("content") != null){
            text_view_content.setText(getIntent().getStringExtra("content"));
        }
    }
}
