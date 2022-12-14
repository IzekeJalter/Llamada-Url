package com.isaac012.examenllamada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.isaac012.examenllamada.modelos.Data;
import com.isaac012.examenllamada.modelos.DataListResponse;
import com.isaac012.examenllamada.recyclerview.DataAdaptador;
import com.isaac012.examenllamada.singleton.SingletonRequest;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info();
    }

    private void info() {
        String URL = "https://ramiro.uttics.com/api/contactos";
        final RecyclerView rvData = findViewById(R.id.rvData);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    DataListResponse datalista = gson.fromJson(response.toString(), DataListResponse.class);
                    List<Data> mdata = datalista.getData();

                    DataAdaptador dataAdaptador = new DataAdaptador(mdata, MainActivity.this);

                    rvData.setAdapter(dataAdaptador);
                    rvData.setHasFixedSize(true);

                    RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
                    rvData.setLayoutManager(manager);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        SingletonRequest.getInstance(this).addToRequestQue(jsonObjectRequest);
    }
}