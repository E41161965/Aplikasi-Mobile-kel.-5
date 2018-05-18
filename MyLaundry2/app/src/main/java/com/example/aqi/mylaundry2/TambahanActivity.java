package com.example.aqi.mylaundry2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class TambahanActivity extends AppCompatActivity {
    private TextView mShowCount,mShowCount2,mShowCount3,mShowCount4,mShowCount5,mShowCount6,
            mShowCount7,mShowCount8,mShowCount9,mShowCount10,mShowCount11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahan);


        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mShowCount = (TextView) findViewById(R.id.jumlahselimutbesar);
        mShowCount2 = (TextView) findViewById(R.id.jumlahselimutkecil);
        mShowCount3 = (TextView) findViewById(R.id.jumlahbedcoverbesar);
        mShowCount4 = (TextView) findViewById(R.id.jumlahbedcoverkecil);
        mShowCount5 = (TextView) findViewById(R.id.jumlahspreibesar);
        mShowCount6 = (TextView) findViewById(R.id.jumlahspreikecil);
        mShowCount7 = (TextView) findViewById(R.id.jumlahbonekabesar);
        mShowCount8 = (TextView) findViewById(R.id.jumlahbonekatanggung);
        mShowCount9 = (TextView) findViewById(R.id.jumlahbonekakecil);
        mShowCount10 = (TextView) findViewById(R.id.jumlahsepatu);
        mShowCount11 = (TextView) findViewById(R.id.jumlahtas);


    }
    public void up1(View v){
        countUp(mShowCount);

    } public void min1(View v){
        countdown(mShowCount);

    }public void up2(View v){
        countUp(mShowCount2);

    }
    public void min2(View v){
        countdown(mShowCount2);

    }
    public void up3(View v){
        countUp(mShowCount3);

    } public void min3(View v){
        countdown(mShowCount3);

    }
    public void up4(View v){
        countUp(mShowCount4);

    } public void min4(View v){
        countdown(mShowCount4);

    }
    public void up5(View v){
        countUp(mShowCount5);

    } public void min5(View v){
        countdown(mShowCount5);

    }
    public void up6(View v){
        countUp(mShowCount6);

    } public void min6(View v){
        countdown(mShowCount6);

    }
    public void up7(View v){
        countUp(mShowCount7);

    } public void min7(View v){
        countdown(mShowCount7);

    }
    public void up8(View v){
        countUp(mShowCount8);

    } public void min8(View v){
        countdown(mShowCount8);

    }
    public void up9(View v){
        countUp(mShowCount9);

    } public void min9(View v){
        countdown(mShowCount9);

    }
    public void up10(View v){
        countUp(mShowCount10);

    } public void min10(View v){
        countdown(mShowCount10);

    }
    public void up11(View v){
        countUp(mShowCount11);

    } public void min11(View v){
        countdown(mShowCount11);

    }

    public void countUp(TextView view) {
        int angka = Integer.parseInt(view.getText().toString());
        if(angka==10){
            Toast toast = Toast.makeText(this,"maksimum",Toast.LENGTH_SHORT);
            toast.show();
        }else{
            angka++;
            view.setText(Integer.toString(angka));
        }

    }



    public void countdown(TextView view) {
        int angka = Integer.parseInt(view.getText().toString());
        if(angka==0){
            Toast toast = Toast.makeText(this,"minimum",Toast.LENGTH_SHORT);
            toast.show();
        }else{
            angka--;
            view.setText(Integer.toString(angka));
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            Intent intent = new Intent(TambahanActivity.this, OrderActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TambahanActivity.this, OrderActivity.class);
        startActivity(intent);
        finish();
    }
}
