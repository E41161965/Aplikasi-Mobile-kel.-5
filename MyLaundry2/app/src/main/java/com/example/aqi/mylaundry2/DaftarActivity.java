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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DaftarActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editText_email)
    EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextReTypePassword;
    @BindView(R.id.button_register)
    Button mButtonRegister;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidEmail(mEditTextEmail.getText().toString())){
                    Toast.makeText(DaftarActivity.this,
                            "Format email sudah benar", Toast.LENGTH_SHORT).show();
                } else {
                    mEditTextEmail.setError("Format email salah");
                    Toast.makeText(DaftarActivity.this,
                            "Format email salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        mEditTextEmail = (EditText) findViewById(R.id.editText_email);
        mEditTextPassword = (EditText) findViewById(R.id.editText_password);
        mEditTextReTypePassword = (EditText) findViewById(R.id.editText_passwordAgain);
//        mButtonRegister = (Button) findViewById(R.id.button_register);

//        mButtonRegister.setOnClickListener(this);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static boolean isValidEmail(String email) {
        boolean validate;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern)) {
            validate = true;
        } else if (email.matches(emailPattern2)) {
            validate = true;
        } else {
            validate = false;
        }

        return validate;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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

        /**
         * method ini dipanggil saat kita menekan button register
         */
        private void register(){
            String email = mEditTextEmail.getText().toString();
            String password = mEditTextPassword.getText().toString();
            String retypePassword = mEditTextReTypePassword.getText().toString();

            if(!isValidateEmail(email)){
                Toast.makeText(this, "Email kosong atau salah",Toast.LENGTH_LONG).show();
            }else if(!isEmptyField(password)){
                Toast.makeText(this, "Password harus diisi",Toast.LENGTH_LONG).show();
            }else if(!isEmptyField(retypePassword)){
                Toast.makeText(this, "Retype password harus diisi",Toast.LENGTH_LONG).show();
            }else if(!isMatch(password,retypePassword)){
                Toast.makeText(this, "Password tidak cocok",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Register Berhasil",Toast.LENGTH_LONG).show();
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
