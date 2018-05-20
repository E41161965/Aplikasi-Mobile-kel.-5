package com.example.aqi.mylaundry2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DaftarActivity extends AppCompatActivity {

    private EditText mEditNamaLengkap;
    private EditText mEditAlamat;
    private EditText mEditNoTelpon;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextReTypePassword;
    private Button mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditNamaLengkap = (EditText) findViewById(R.id.editNamaLengkap);
        mEditAlamat = (EditText) findViewById(R.id.editAlamat);
        mEditNoTelpon = (EditText) findViewById(R.id.editNoTelpon);
        mEditTextEmail = (EditText) findViewById(R.id.editText_email);
        mEditTextPassword = (EditText) findViewById(R.id.editText_password);
        mEditTextReTypePassword = (EditText) findViewById(R.id.editText_passwordAgain);
        mButtonRegister = (Button) findViewById(R.id.button_register);

        mButtonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if(id==R.id.button_register){
                    register();

                }else if (id==R.id.button_register){
                    Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
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
                            DaftarActivity.this.finish();
                            Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
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
                        DaftarActivity.this.finish();
                        Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

        /**
         * method ini dipanggil saat kita menekan button register
         */
        private void register(){
            String nama = mEditNamaLengkap.getText().toString();
            String alamat = mEditAlamat.getText().toString();
            String telpon = mEditNoTelpon.getText().toString();
            String email = mEditTextEmail.getText().toString();
            String password = mEditTextPassword.getText().toString();
            String retypePassword = mEditTextReTypePassword.getText().toString();

            if (!isEmptyField(nama)){
                Toast.makeText(this, "Harap Masukkan Nama Anda",Toast.LENGTH_SHORT).show();
            }else if (!isEmptyField(alamat)){
                Toast.makeText(this, "Harap Masukkan Alamat Anda",Toast.LENGTH_SHORT).show();
            }else if (!isEmptyField(telpon)){
                Toast.makeText(this, "Harap Masukkan Nomor Telepon Anda",Toast.LENGTH_SHORT).show();
            }else if(!isValidateEmail(email)){
                Toast.makeText(this, "Harap Masukkan Email Anda atau Email Anda Salah",Toast.LENGTH_SHORT).show();
            }else if(!isEmptyField(password)){
                Toast.makeText(this, "Harap Masukkan Password Anda",Toast.LENGTH_SHORT).show();
            }else if(!isEmptyField(retypePassword)){
                Toast.makeText(this, "Harap Masukkan Retype Password Anda",Toast.LENGTH_SHORT).show();
            }else if(!isMatch(password,retypePassword)){
                Toast.makeText(this, "Password Tidak Cocok",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Register Berhasil",Toast.LENGTH_SHORT).show();
            }
        }

        /**
         *
         * @param email
         * Method dibawah ini untuk validasi email kosong atau salah
         */
        private boolean isValidateEmail(String email){
            return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

        /**
         *
         * @param yourField
         * Method ini digunakan untuk validasi field kosong atau tidak
         */
        private boolean isEmptyField(String yourField){
            return !TextUtils.isEmpty(yourField);
        }

        /**
         *
         * @param password
         * @param retypePassword
         * method ini digunakan untuk mencocokan password dengan retype password
         */
        private boolean isMatch(String password, String retypePassword){
            return password.equals(retypePassword);
        }
    }
