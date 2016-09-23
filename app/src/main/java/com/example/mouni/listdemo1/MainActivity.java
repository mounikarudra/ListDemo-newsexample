package com.example.mouni.listdemo1;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.HorizontalScrollView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Creating a List of superheroes
    private ArrayList<ImageAndTitle> listSuperHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);
        //gridlayout
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2); // (Context context, int spanCount)

        recyclerView.setLayoutManager(mGridLayoutManager);


        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();

        //Calling method to get data
        getData();
    }

    //This method will get data from the web api
    private void getData(){
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Loading Data", "Please wait...",false,false);

        //Creating a json array request
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Configr.DATA_URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Dismissing progress dialog
                        loading.dismiss();

                        //calling method to parse json array
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    //This method will parse json data
    private void parseData(JSONObject object){
        //for(int i = 0; i<object.length(); i++) {
            //ImageAndTitle superHero = new ImageAndTitle();

            try {

                JSONArray ja = object.getJSONArray("info");

                for (int j = 0; j < ja.length(); j++) {

                    JSONObject jsonObject = ja.getJSONObject(j);
                    ImageAndTitle superHero = new ImageAndTitle();
                    // int id = Integer.parseInt(jsonObject.optString("id").toString());
                    superHero.setImageUrl(jsonObject.getString(Configr.TAG_IMAGE_URL));
                    superHero.setTitle(jsonObject.getString(Configr.TAG_TITLE));
                    superHero.setContent(jsonObject.getString(Configr.TAG_CONTENT));
                    //String title = jsonObject.getString("title");
                    listSuperHeroes.add(superHero);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }



        //Finally initializing our adapter
        adapter = new CardAdapter(listSuperHeroes, this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}