package com.example.aqi.mylaundry2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

//Class Adapter ini Digunakan Untuk Mengatur Bagaimana Data akan Ditampilkan
public class PemesananViewAdapter extends RecyclerView.Adapter<PemesananViewAdapter.ViewHolder>{

    private ArrayList<String> arrayList; //Digunakan untuk Judul
    private ArrayList<String> deskripList; //Digunakan untuk Deskripsi
    private ArrayList<Integer> gambarList; //Digunakan untuk Image/Gambar

    PemesananViewAdapter(ArrayList<String> arrayList, ArrayList<String> deskripList, ArrayList<Integer> gambarList){
        this.arrayList = arrayList;
        this.deskripList = deskripList;
        this.gambarList = gambarList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv1, tv2;
        private ImageView gambar;
        private RelativeLayout ItemList;
        private Context context;

        ViewHolder(View itemView) {
            super(itemView);

            //Untuk Menghubungkan dan Mendapakan Context dari MainActivity
            context = itemView.getContext();

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            tv1 = itemView.findViewById(R.id.textView1);
            tv2 = itemView.findViewById(R.id.textView2);
            gambar = itemView.findViewById(R.id.imageView);
            ItemList = itemView.findViewById(R.id.item_list);
            ItemList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    switch (getAdapterPosition()){
                        case 0 :
                            intent = new Intent(context, OrderActivity.class);
                            break;
                        case 1 :
                            intent = new Intent(context, KonfirmasiActivity.class);
                            break;
                        case 2 :
                            intent = new Intent(context, ProsesActivity.class);
                            break;
                    }
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pemesanan, parent, false);
        ViewHolder VH = new ViewHolder(V);
        return VH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Memasukan Nilai/Value Pada View-View Yang Telah Dibuat
        final String Nama = arrayList.get(position);//Mengambil data sesuai dengan posisi yang telah ditentukan
        final String Deskripsi = deskripList.get(position);
        holder.tv1.setText(Nama);
        holder.tv2.setText(Deskripsi);
        holder.gambar.setImageResource(gambarList.get(position)); // Mengambil gambar sesuai posisi yang telah ditentukan
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return arrayList.size();
    }

}
