package com.example.aqi.mylaundry2;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    private static final String TAG = "OrderFragment";

    Dialog mDialog;


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Started.");
        mDialog = new Dialog(getActivity());
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);

        // Membaca dan menentukan isi TexTView
        TextView textView = (TextView) rootView.findViewById(R.id.textView3);
        textView.setText("+Tambahan");
        // Membuat span dengan tampilan berbeda dan dapat diklik
        new PatternEditableBuilder().
                addPattern(Pattern.compile("\\+(\\w+)"), Color.BLUE,
                        new PatternEditableBuilder.SpannableClickedListener() {
                            @Override
                            public void onSpanClicked(String text) {
//                                Toast.makeText(getActivity(), "Clicked username: " + text,
//                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), TambahanActivity.class);
                                getActivity().startActivity(intent);
                            }
                        }).into(textView);

        Button button = (Button) rootView.findViewById(R.id.buttonOrder);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                mDialog.setContentView(R.layout.popup_order);
                txtclose =(TextView) mDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                        Intent intent = new Intent(getActivity(), DetailTransaksiActivity.class);
                        startActivity(intent);
                    }
                });
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
