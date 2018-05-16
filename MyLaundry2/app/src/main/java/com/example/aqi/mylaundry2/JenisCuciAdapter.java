package com.example.aqi.mylaundry2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class JenisCuciAdapter extends ArrayAdapter<JenisCuci> {
    private static final String TAG = "JenisCuciAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;


    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView jenis;
        TextView cucikeringsetrika;
        TextView cucikering;
        TextView setrikasaja;
    }

    /**
     * @param context
     * @param resource
     * @param objects
     */
    public JenisCuciAdapter(Context context, int resource, ArrayList<JenisCuci> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String jenis = getItem(position).getJenis();
        String cucikeringsetrika = getItem(position).getCucikeringsetrika();
        String cucikering = getItem(position).getCucikering();
        String setrikasaja = getItem(position).getSetrikasaja();

        JenisCuci jenisCuci = new JenisCuci(jenis,cucikeringsetrika,cucikering,setrikasaja);

        final View result;

        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.jenis = (TextView) convertView.findViewById(R.id.textView1);
            holder.cucikeringsetrika = (TextView) convertView.findViewById(R.id.textView2);
            holder.cucikering = (TextView) convertView.findViewById(R.id.textView3);
            holder.setrikasaja = (TextView) convertView.findViewById(R.id.textView4);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.jenis.setText(jenisCuci.getJenis());
        holder.cucikeringsetrika.setText(jenisCuci.getCucikeringsetrika());
        holder.cucikering.setText(jenisCuci.getCucikering());
        holder.setrikasaja.setText(jenisCuci.getSetrikasaja());


        return convertView;
    }
}

