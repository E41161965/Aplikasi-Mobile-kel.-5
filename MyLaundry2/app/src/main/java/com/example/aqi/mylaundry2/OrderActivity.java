package com.example.aqi.mylaundry2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Membaca dan menentukan isi TexTView
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText("+Tambahan");
        // Membuat span dengan tampilan berbeda dan dapat diklik
        new PatternEditableBuilder().
                addPattern(Pattern.compile("\\+(\\w+)"), Color.BLUE,
                        new PatternEditableBuilder.SpannableClickedListener() {
                            @Override
                            public void onSpanClicked(String text) {
//                                Toast.makeText(OrderActivity.this, "Clicked username: " + text,
//                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(OrderActivity.this, TambahanActivity.class);
                                startActivity(intent);
                            }
                        }).into(textView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            new AlertDialog.Builder(this)
                    .setTitle("E-Laundry")
                    .setMessage("Apakah Anda yakin ingin membatalkan ?")
                    .setIcon(R.drawable.elaundrylogin)
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            OrderActivity.this.finish();
                            Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("E-Laundry")
                .setMessage("Apakah Anda yakin ingin membatalkan ?")
                .setIcon(R.drawable.elaundrylogin)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        OrderActivity.this.finish();
                        Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
