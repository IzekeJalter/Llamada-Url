package com.isaac012.examenllamada.recyclerview;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.isaac012.examenllamada.MainActivity;
import com.isaac012.examenllamada.R;
import com.isaac012.examenllamada.modelos.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdaptador extends RecyclerView.Adapter<DataViewHolder> {
    List<Data> dataList;
    Context ctx;

    public DataAdaptador(List<Data> dataList, Context ctx) {
        this.dataList = dataList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data, parent, false);

        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.dataName.setText(data.getNombre());
        holder.dataNumero.setText(data.getNumero());
        holder.dataUrl.setText(data.getUrl());
//        Picasso.get().load(data.getImage()).into(holder.dataImage);

        holder.dataUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data data = dataList.get(position);
                String url = data.getUrl();
                Uri link = Uri.parse(url);
                Intent i = new Intent(Intent.ACTION_VIEW, link);
                ctx.startActivity(i);
            }
        });

        MainActivity mainActivity = new MainActivity();
        holder.dataNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(view.getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((MainActivity) view.getContext(),
                            new String[]{Manifest.permission.CALL_PHONE}, 255);
                } else {
                    String inicio = "tel:" + data.getNumero();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(inicio));
                    ctx.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
