package com.example.aqi.mylaundry2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class OrderActivity extends AppCompatActivity {
    Dialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDialog = new Dialog(this);

        // Membaca dan menentukan isi TexTView
        TextView textView1 = (TextView) findViewById(R.id.textView3);
        textView1.setText("+Tambahan");
        // Membuat span dengan tampilan berbeda dan dapat diklik
        new PatternEditableBuilder().
                addPattern(Pattern.compile("\\+(\\w+)"), Color.BLUE,
                        new PatternEditableBuilder.SpannableClickedListener() {
                            @Override
                            public void onSpanClicked(String text) {
//                                Toast.makeText(getActivity(), "Clicked username: " + text,
//                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(OrderActivity.this, TambahanActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).into(textView1);

        // Membaca dan menentukan isi TexTView
        TextView textView2 = (TextView) findViewById(R.id.textView5);
        textView2.setText("+DetailTransaksi");
        // Membuat span dengan tampilan berbeda dan dapat diklik
        new PatternEditableBuilder().
                addPattern(Pattern.compile("\\+(\\w+)"), Color.BLUE,
                        new PatternEditableBuilder.SpannableClickedListener() {
                            @Override
                            public void onSpanClicked(String text) {
//                                Toast.makeText(getActivity(), "Clicked username: " + text,
//                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(OrderActivity.this, DetailTransaksiActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).into(textView2);

        Button button = (Button) findViewById(R.id.buttonOrder);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                mDialog.setContentView(R.layout.popup_order);
                txtclose =(TextView) mDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                    }
                });
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(OrderActivity.this, PemesananActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderActivity.this, PemesananActivity.class);
        startActivity(intent);
        finish();
    }
}
