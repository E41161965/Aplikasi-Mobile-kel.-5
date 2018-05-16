package com.example.aqi.mylaundry2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private CardView layanan, order, nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layanan = (CardView) findViewById(R.id.layanan);
        layanan.setOnClickListener(this);
        order = (CardView) findViewById(R.id.order);
        order.setOnClickListener(this);
        nota = (CardView) findViewById(R.id.nota);
        nota.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_beranda) {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();


        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(HomeActivity.this, ProfilActivity.class);
            startActivity(intent);
            finish();


        } else if (id == R.id.nav_riwayat) {
            Intent intent = new Intent(HomeActivity.this, RiwayatActivity.class);
            startActivity(intent);
            finish();


        } else if (id == R.id.nav_bantuan) {
            Intent intent = new Intent(HomeActivity.this, BantuanActivity.class);
            startActivity(intent);
            finish();


        } else if (id == R.id.nav_keluar) {
            new AlertDialog.Builder(this)
                    .setTitle("E-Laundry")
                    .setMessage("Apakah Anda ingin keluar akun ?")
                    .setIcon(R.drawable.elaundrylogin)
                    .setCancelable(false)
                    .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            HomeActivity.this.finish();
                            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Batal", null)
                    .show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.layanan:
                i = new Intent(this, LayananActivity.class);
                startActivity(i);
                break;
            case R.id.order:
                i = new Intent(this, OrderActivity.class);
                startActivity(i);
                break;
            case R.id.nota:
                i = new Intent(this, NotakuActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
