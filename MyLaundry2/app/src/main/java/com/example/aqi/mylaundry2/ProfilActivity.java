package com.example.aqi.mylaundry2;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.aqi.mylaundry2.LoginActivity.TAG_ID;
import static com.example.aqi.mylaundry2.LoginActivity.TAG_PASSWORD;
import static com.example.aqi.mylaundry2.LoginActivity.TAG_USERNAME;


public class ProfilActivity extends AppCompatActivity {
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView imageView;
    private String userChoosenTask;
    private Button edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        TextView usernametxt = (TextView) findViewById(R.id.username);
        TextView namatxt = (TextView) findViewById(R.id.NamaLengkap);
        TextView alamattxt = (TextView) findViewById(R.id.Alamat);
        TextView passwordtxt = (TextView) findViewById(R.id.password);
        TextView nohptxt = (TextView) findViewById(R.id.notelpon);

        SharedPreferences pref = getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
        final String id = pref.getString(TAG_ID, null);
        final String username = pref.getString(TAG_USERNAME, null);
        final String password = pref.getString(TAG_PASSWORD, null);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit = (Button) findViewById(R.id.buttonEdit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilActivity.this, ProfilEditActivity.class);
                startActivity(intent);
                finish();
            }
        });

        passwordtxt.setText(password);
        usernametxt.setText(username);

        imageView = (ImageView) findViewById(R.id.userphoto);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

//    /**
//     * method ini dipanggil saat kita menekan button simpan
//     */
//    private void edit(){
//        String e = email.getText().toString();
//        String p = password.getText().toString();
//        String rtp = retypepassword.getText().toString();
//
//        if(!isValidateEmail(e)){
//            Toast.makeText(this, "Harap Masukkan Email Anda atau Email Anda Salah",Toast.LENGTH_SHORT).show();
//        }else if(!isMatch(p,rtp)){
//            Toast.makeText(this, "Password Tidak Cocok",Toast.LENGTH_SHORT).show();
//        }else{
//
//        }
//    }
//
//    /**
//     *
//     * @param email
//     * Method dibawah ini untuk validasi email kosong atau salah
//     */
//    private boolean isValidateEmail(String email){
//        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }
//
//    /**
//     *
//     * @param password
//     * @param retypePassword
//     * method ini digunakan untuk mencocokan password dengan retype password
//     */
//    private boolean isMatch(String password, String retypePassword){
//        return password.equals(retypePassword);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Ambil Gambar"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Pilih dari Galeri"))
                        galleryIntent();
                    else if (userChoosenTask.equals("Hapus Gambar"))
                        deleteIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Ambil Gambar", "Pilih dari Galeri", "Hapus Gambar",
                "Batal"};

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfilActivity.this);
        builder.setTitle("Tambah Gambar!");
        builder.setIcon(R.drawable.elaundrylogin);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(ProfilActivity.this);

                if (items[item].equals("Ambil Gambar")) {
                    userChoosenTask = "Ambil Gambar";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Pilih dari Galeri")) {
                    userChoosenTask = "Pilih dari Galeri";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Hapus Gambar")) {
                    userChoosenTask = "Hapus Gambar";
                    if (result)
                        deleteIntent();

                } else if (items[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void deleteIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imageView.setImageBitmap(bm);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
