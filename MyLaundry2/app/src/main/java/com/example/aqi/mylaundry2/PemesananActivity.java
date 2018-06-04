package com.example.aqi.mylaundry2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;


public class PemesananActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> tv1;
    private ArrayList<String> tv2;
    private ArrayList<Integer> gambar;
    //Daftar Judul
    private String[] Judul = {"Order", "Konfirmasi", "Proses"};
    //Daftar Deskripsi
    private String[] Deskripsi = {"Silahkan order disini", "Silahkan konfirmasi orderan disini", "Silahkan melihat proses orderan disini"};
    //Daftar Gambar
    private int[] Gambar = {R.drawable.order, R.drawable.konfirmasi, R.drawable.proses};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        //Menambahkan tombol back pada appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //set tab selector color
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));

        //set the indicator
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapterPemesanan adapter = new PagerAdapterPemesanan(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

        tv1 = new ArrayList<>();
        tv2 = new ArrayList<>();
        gambar = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        DaftarItem();
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new PemesananViewAdapter(tv1, tv2, gambar);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);
    }
    //Mengambil data dari Varibale Gambar dan Judul, lalu memasangnya pada list yang terhubung dengan Class Adapter
    private void DaftarItem(){
        for (int w=0; w<Judul.length; w++){
            gambar.add(Gambar[w]);
            tv1.add(Judul[w]);
            tv2.add(Deskripsi[w]);
        }
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
                            PemesananActivity.this.finish();
                            Intent intent = new Intent(PemesananActivity.this, HomeActivity.class);
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
                        PemesananActivity.this.finish();

                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
