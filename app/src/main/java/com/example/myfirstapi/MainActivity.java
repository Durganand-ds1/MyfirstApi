package com.example.myfirstapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //using volly we will create and read json file from web
        ListView rootView = (ListView) findViewById(R.id.con);
        //Add view to the Parent layout in which you want to add your views
         String URL = "https://jsonplaceholder.typicode.com/todos";
        ArrayList<Values> obje = new ArrayList<>();

        RequestQueue queue ;
        queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TAG", response.toString());
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject obj = response.getJSONObject(i);
                        String title = obj.getString("title");
                        Log.d("dur", "onResponse: data is" +obj.getString("title")+"the id is"+obj.getInt("id"));
                        obje.add(new Values(title));
                    }


                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp", "onErrorResponse: failed ");
            }
        });
        queue.add(jsonArrayRequest);
        Vadapter itemsa= new Vadapter(this,obje);
        rootView.setAdapter(itemsa);




    }
}