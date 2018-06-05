package com.example.aqi.mylaundry2;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ProsesActivity extends AppCompatActivity {
    LinearLayout linearDetail;
    TextView textDetail;
    ListView listDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textDetail     = (TextView) findViewById(R.id.textDetail);
        linearDetail   = (LinearLayout) findViewById(R.id.linearDetail);
        listDetail     = (ListView) findViewById(R.id.listDetail);

        textDetail.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG); // garis bawah
        textDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linearDetail.getVisibility() == View.GONE){
                    linearDetail.setVisibility(View.VISIBLE);
                    textDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_up_float, 0);
                } else {
                    linearDetail.setVisibility(View.GONE);
                    textDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_down_float, 0);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(ProsesActivity.this, PemesananActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProsesActivity.this, PemesananActivity.class);
        startActivity(intent);
        finish();
    }
}
