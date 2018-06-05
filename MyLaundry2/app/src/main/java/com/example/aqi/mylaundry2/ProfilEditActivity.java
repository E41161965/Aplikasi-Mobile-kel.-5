package com.example.aqi.mylaundry2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfilEditActivity extends AppCompatActivity {
    private EditText nama, alamat, telpon, email, password, retypepassword ;
    private Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_edit);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama = (EditText) findViewById(R.id.editNamaLengkap);
        alamat = (EditText) findViewById(R.id.editAlamat);
        telpon = (EditText) findViewById(R.id.editNoTelpon);
        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_password);
        retypepassword = (EditText) findViewById(R.id.editText_passwordAgain);
        simpan = (Button) findViewById(R.id.buttonSimpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mengecek Form Nama
                if(nama.getText().toString().length()==0){
                    //Jika Form Nama Kosong
                    nama.setError("Harap Masukkan Nama Anda");
                }
                //Mengecek Form Alamat
                else if(alamat.getText().toString().length()==0){
                    //Jika Form Alamat Kosong
                    alamat.setError("Harap Masukkan Alamat Anda");
                }
                //Mengecek Form No Telepon
                else if(telpon.getText().toString().length()==0){
                    //Jika Form No Telepon Kosong
                    telpon.setError("Harap Masukkan Nomor Telepon Anda");
                }
                //Mengecek Form Email
                else if(email.getText().toString().length()==0){
                    //Jika Form Email Kosong
                    email.setError("Harap Masukkan Email Anda");
                }
                //Mengecek Form Password
                else if (password.getText().toString().length()==0){
                    //Jika Form Password Kosong
                    password.setError("Harap Masukkan Password Anda");
                }
                //Mengecek Form ReTypePassword
                else if(retypepassword.getText().toString().length()==0){
                    //Jika Form ReTypePassword Kosong
                    retypepassword.setError("Harap Masukkan Re-type Password Anda");
                }else{
                    //Jika semua sudah terisi maka tampilkan toast
                    Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan",Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(ProfilEditActivity.this, ProfilActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(ProfilEditActivity.this, ProfilActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfilEditActivity.this, ProfilActivity.class);
        startActivity(intent);
        finish();
    }
}
