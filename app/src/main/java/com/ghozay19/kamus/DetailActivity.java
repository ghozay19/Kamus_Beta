package com.ghozay19.kamus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_WORD        = "extra_word";
    public static String EXTRA_DETAIL     = "extra_detail";

    private TextView tvWorddtl, tvDetaildtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvWorddtl   = (TextView)findViewById(R.id.tvWord_detail);
        tvDetaildtl = (TextView)findViewById(R.id.tvdetail_detail);

        String word     = getIntent().getStringExtra(EXTRA_WORD);
        String detail   = getIntent().getStringExtra(EXTRA_DETAIL);

        tvWorddtl.setText(word);
        tvDetaildtl.setText(detail);

    }
}
