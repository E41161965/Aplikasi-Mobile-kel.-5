package com.example.aqi.mylaundry2;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



/**
 * A simple {@link Fragment} subclass.
 */
public class PagerAdapterFragment extends FragmentStatePagerAdapter {
    private int number_tabs;


    public PagerAdapterFragment(FragmentManager fm, int number_tabs) {
        // Required empty public constructor
        super(fm);
        this.number_tabs = number_tabs;
    }


    //Mengembalikan Fragment yang terkait dengan posisi tertentu
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new JenisCuciFragment();
            case 1:
                return new BarangSatuanFragment();
            case 2:
                return new ParfumFragment();
            default:
                return null;
        }
    }

    //Mengembalikan jumlah tampilan yang tersedia.
    @Override
    public int getCount() {
        return number_tabs;
    }

}
