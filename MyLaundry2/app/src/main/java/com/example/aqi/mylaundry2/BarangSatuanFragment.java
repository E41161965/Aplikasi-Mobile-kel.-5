package com.example.aqi.mylaundry2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarangSatuanFragment extends Fragment {


    public BarangSatuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_barang_satuan, container, false);
        final ListView listView = (ListView) rootView.findViewById(R.id.listView);
        ArrayList<Map<String, String>> listBarang = listData();
        String[] data = { "barang", "harga" };

        int[] target= { android.R.id.text1, android.R.id.text2 };

        SimpleAdapter adapter = new SimpleAdapter(getContext(), listBarang, android.R.layout.simple_list_item_2, data, target);
        listView.setAdapter(adapter);
        return rootView;
    }

    private ArrayList<Map<String, String>> listData() {
        ArrayList<Map<String, String>> listBarang = new ArrayList<Map<String, String>>();
        listBarang.add(setData("Selimut Besar", "Rp 10.000,-"));
        listBarang.add(setData("Selimut Kecil", "Rp  6.000,-"));
        listBarang.add(setData("Bed Cover Besar", "Rp 12.000,-"));
        listBarang.add(setData("Bed Cover Kecil", "Rp  7.000,-"));
        listBarang.add(setData("Sprei Besar", "Rp  9.000,-"));
        listBarang.add(setData("Sprei Kecil", "Rp  5.000,-"));
        listBarang.add(setData("Boneka Besar", "Rp 13.000,-"));
        listBarang.add(setData("Boneka Tanggung", "Rp 10.000,-"));
        listBarang.add(setData("Boneka Kecil", "Rp  4.000,-"));
        listBarang.add(setData("Sepatu", "Rp 10.000,-"));
        listBarang.add(setData("Tas", "Rp  6.000,-"));
        return listBarang;
    }

    private HashMap<String, String> setData(String barang, String harga) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("barang", barang);
        item.put("harga", harga);
        return item;
    }

        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_barang_satuan, container, false);
//        return view;
}


