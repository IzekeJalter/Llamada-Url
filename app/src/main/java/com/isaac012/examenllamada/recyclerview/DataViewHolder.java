package com.isaac012.examenllamada.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isaac012.examenllamada.R;

public class DataViewHolder extends RecyclerView.ViewHolder {
    TextView dataName, dataNumero, dataUrl;
//    ImageView dataImage;
    LinearLayout dataListContainer;


    public DataViewHolder(@NonNull View v) {
        super(v);
        dataName = v.findViewById(R.id.dataName);
        dataNumero = v.findViewById(R.id.dataNumero);
        dataUrl = v.findViewById(R.id.dataUrl);
//        dataImage = v.findViewById(R.id.dataImage);
        dataListContainer = v.findViewById(R.id.dataListContainer);
    }
}
