package com.example.aqi.mylaundry2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.aqi.mylaundry2.LoginActivity.TAG_ALAMAT;
import static com.example.aqi.mylaundry2.LoginActivity.TAG_ID;
import static com.example.aqi.mylaundry2.LoginActivity.TAG_NAMA;
import static com.example.aqi.mylaundry2.LoginActivity.TAG_NOHP;
import static com.example.aqi.mylaundry2.LoginActivity.TAG_PASSWORD;
import static com.example.aqi.mylaundry2.LoginActivity.TAG_USERNAME;

public class ProfilEditActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    private EditText nama, alamat, telpon, username, password, retypepassword ;
    private Button simpan;
    private String url = Server.ubahprofil;
    private static final String TAG = ProfilEditActivity.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    private ConnectivityManager conMgr;
    private String id;
    private String username1;
    private String password1;
    private String nama1;
    private String alamat1;
    private String nohp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_edit);
        sharedPreferences = getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
        id = sharedPreferences.getString(TAG_ID, null);
        username1 = sharedPreferences.getString(TAG_USERNAME, null);
        password1 = sharedPreferences.getString(TAG_PASSWORD, null);
        nama1 = sharedPreferences.getString(TAG_NAMA, null);
        alamat1 = sharedPreferences.getString(TAG_ALAMAT, null);
        nohp1 = sharedPreferences.getString(TAG_NOHP, null);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama = (EditText) findViewById(R.id.editNamaLengkap);
        alamat = (EditText) findViewById(R.id.editAlamat);
        telpon = (EditText) findViewById(R.id.editNoTelpon);
        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        retypepassword = (EditText) findViewById(R.id.editText_passwordAgain);
        simpan = (Button) findViewById(R.id.buttonSimpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String NAMA = nama.getText().toString();
               String HP = telpon.getText().toString();
               String ALAMAT = alamat.getText().toString();
               String USERNAME = username.getText().toString();
               String PASSWORD = password.getText().toString();
               String konfirmasi_password = retypepassword.getText().toString();


                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    konek(id,NAMA,HP,ALAMAT,USERNAME,PASSWORD,konfirmasi_password);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(TAG_NAMA, NAMA);
                    editor.putString(TAG_NOHP, HP);
                    editor.putString(TAG_ALAMAT, ALAMAT);
                    editor.putString(TAG_USERNAME, USERNAME);
                    editor.putString(TAG_PASSWORD, PASSWORD);
                    editor.commit();

                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        password.setText(password1);
        username.setText(username1);
        nama.setText(nama1);
        alamat.setText(alamat1);
        telpon.setText(nohp1);

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void konek (final String id,final String nama,final String telpon,final String alamat,final String username,final String password,final String konfirmasi_password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Sedang menyimpan ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    int success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Simpan berhasil", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        finish();


                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {

                    // JSON error
                    Toast.makeText(getApplicationContext(),"JSON ERROR "+e,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Simpan Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage()+"Tidak ada respon", Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("nama", nama);
                params.put("nohp", telpon);
                params.put("alamat", alamat);
                params.put("username", username);
                params.put("password", password);
                params.put("konfirmasipassword", konfirmasi_password);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

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
