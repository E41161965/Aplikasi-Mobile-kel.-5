package com.example.aqi.mylaundry2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
//import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    EditText editText1 ,editText2; //Membuat object dari class EdiText
    Switch switch1; //Membuat object dari class Switch
    String text1 ,text2; //Membuat object string
//    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        editText2 = (EditText) findViewById(R.id.password); //Memanggil ID EditText
        switch1 = (Switch) findViewById(R.id.switch1); //Memanggil ID Switch

        /*Membuat Aksi Ketika Checkbox Di Klik*/
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    //Jika checkbox di ceklis maka tampilkan password
                    editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //Jika checkbox tidak di ceklis maka sembunyikan password
                    editText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

//        // Deklarasi komponen view
//        editText1 = (EditText) findViewById(R.id.username);
//        editText2 = (EditText) findViewById(R.id.password);
//        button = (Button) findViewById(R.id.login);
//        // Setting button ketika di klik
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Panggil void sendLogin()
//                sendLogin();
//            }
//        });
//    }
//    private void sendLogin() {
//        // Setting POST request ke server
//        StringRequest loginRequest = new StringRequest(Request.Method.POST, "http://192.168.56.1/server/login.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Handle response dari server ketika sukses dengan mengkonvert menjadi JSON
//                        try {
//                            JSONObject json = new JSONObject(response);
//                            // Mengambil variable status pada response
//                            String status = json.getString("status");
//                            if(status.equals("success")){
//                                // Jika Login Sukses Maka pindah ke activity lain.
//                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }else{
//                                // Jika Login Gagal Maka mengeluarkan Toast dengan message.
//                                Toast.makeText(getApplicationContext(), "Username & Password Salah", Toast.LENGTH_LONG).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle response dari server ketika gagal
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<>();
//                params.put("username", editText1.getText().toString());
//                params.put("password", editText2.getText().toString());
//                return params;
//            }
//        };
//        // Buat antrian request pada cache android
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        // Tambahkan Request pada antrian request
//        requestQueue.add(loginRequest);
//    }

    /*Membuat Method onclick pada button*/
    public void login(View view) {
        editText1 = (EditText) findViewById(R.id.username); //Memanggil ID EditText
        editText2 = (EditText) findViewById(R.id.password); //Memanggil ID EditText
        text1 = editText1.getText().toString(); //Mengambil value (nilai) pada username
        text2 = editText2.getText().toString(); //Mengambil value (nilai) pada password

        /*Membuat Kondisi Ketika Button Di Tekan*/
        if ((text1.contains("aqi")) && ((text2.contains("aqi"))))
        //Jika text1(username) Dan text2(password) sama dengan kondisi maka tampilkan toast dan pindah activity
        {
            Toast.makeText(this, "Anda Berhasil Login", Toast.LENGTH_SHORT).show(); //Menampilkan toast
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class); //Berpindah activity
            startActivity(intent); //Menjalankan Activity
            finish();
        } else if ((text1.matches("") || text2.matches("")))
        //Atau jika input text 1 dan text 2 kosong maka tampilkan toast
        {
            Toast.makeText(this, "Isikan Username dan Password", Toast.LENGTH_SHORT).show(); //menampilkan toast

        } else
        //jika kedua kondisi diatas tidak memenuhi maka tampilkan toast
        {
            Toast.makeText(this, "Maaf Username/Password Salah", Toast.LENGTH_SHORT).show(); //menampilkan toast
        }

    }

    public void button(View view){
        Intent intent = new Intent(LoginActivity.this, DaftarActivity.class); //Berpindah activity
        startActivity(intent); //Menjalankan Activity
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}