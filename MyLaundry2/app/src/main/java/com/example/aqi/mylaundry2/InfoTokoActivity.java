package com.example.aqi.mylaundry2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.widget.TextView;

public class InfoTokoActivity extends AppCompatActivity {
private TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_toko);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv1.setLinkTextColor(Color.parseColor("#ffffffff"));
        tv2.setLinkTextColor(Color.parseColor("#ffffffff"));
        Linkify.addLinks(tv1,Linkify.ALL);
        Linkify.addLinks(tv2,Linkify.ALL);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            Intent intent = new Intent(InfoTokoActivity.this, BantuanActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InfoTokoActivity.this, BantuanActivity.class);
        startActivity(intent);
        finish();
    }
}
