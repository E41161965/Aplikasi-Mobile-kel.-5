package com.example.aqi.mylaundry2;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

public class JenisCuciFragment extends Fragment {


    private static final String TAG = "JenisCuciFragment";


    public JenisCuciFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: Started.");
        View rootView = inflater.inflate(R.layout.fragment_jenis_cuci, container, false);
        ListView mListView = (ListView) rootView.findViewById(R.id.listView);

        JenisCuci cuci4jam = new JenisCuci("Cuci 4 Jam","Cuci Kering Setrika  Rp  8.500,- / Kg","Cuci Kering    Rp  6.500,- / Kg", "Setrika Saja    Rp  4.500,- / Kg");
        JenisCuci cuci24jam = new JenisCuci("Cuci 24 Jam","Cuci Kering Setrika  Rp  4.500,- / Kg","Cuci Kering    Rp  3.500,- / Kg", "Setrika Saja    Rp  2.500,- / Kg");
        JenisCuci cuci2hari = new JenisCuci("Cuci 2 Hari","Cuci Kering Setrika  Rp  3.000,- / Kg","Cuci Kering    Rp  2.000,- / Kg", "Setrika Saja    Rp  1.500,- / Kg");

        ArrayList<JenisCuci> jenisCuciList = new ArrayList<>();
        jenisCuciList.add(cuci4jam);
        jenisCuciList.add(cuci24jam);
        jenisCuciList.add(cuci2hari);

        JenisCuciAdapter adapter = new JenisCuciAdapter (getContext(), R.layout.adapter_view_layout, jenisCuciList);
        mListView.setAdapter(adapter);
        return rootView;


//        // Inflate the layout for this fragment (original)
//        View view = inflater.inflate(R.layout.fragment_jenis_cuci, container, false);
//        return view;
    }
}
