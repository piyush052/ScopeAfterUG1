package com.webandrioz.scopeafterug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.webandrioz.scopeafterug.adapters.BookListViewAdapter;
import com.webandrioz.scopeafterug.adapters.CoachingListAdapter;
import com.webandrioz.scopeafterug.constants.Constants;
import com.webandrioz.scopeafterug.models.Book;
import com.webandrioz.scopeafterug.models.Coachings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoachingsActivity extends AppCompatActivity {
    private  final String TAG = getClass().getName();
    ListView listView;
    Spinner spinner;
    ArrayList<String> city=new ArrayList<>();
    ArrayList<Coachings> coachingses=new ArrayList<>();
    String currentCity;
    CoachingListAdapter coachingListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coachings);
        listView= (ListView) findViewById(R.id.coachingListView);
        final String id=getIntent().getStringExtra("id");
        spinner = (Spinner) findViewById(R.id.citySpinner);
        city.add("All");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getCities(id,Constants.BASE_URL+ Constants.COACHINGS);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {

                if(position==0){
                    getCoachings(id,Constants.BASE_URL+ Constants.COACHINGS);

                }else {
                    currentCity = city.get(position);
                Log.e(TAG, "onItemSelected: "+currentCity );
                    getNewCoachings(id, Constants.BASE_URL + Constants.FILTER, currentCity);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void getCities(final String id, String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                JSONArray jsonArray=jsonObject.getJSONArray("coachings");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    coachingses.add(new Coachings(jsonObject1.getString("name"),jsonObject1.getString("about"),jsonObject1.getString("address"),jsonObject1.getString("city"),
                                            jsonObject1.getString("longitude"),
                                            jsonObject1.getString("latitude"),
                                            jsonObject1.getString("website"),
                                            jsonObject1.getString("contact")));
                                }

//                                coachingListAdapter=new CoachingListAdapter(CoachingsActivity.this,coachingses);
//                                listView.setAdapter(coachingListAdapter);
//                                coachingListAdapter.notifyDataSetChanged();

                                JSONArray jsonArray1=jsonObject.getJSONArray("cities");
                                for(int i=0;i<jsonArray1.length();i++){
//                                    JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                                    city.add(jsonArray1.getString(i));
                                }
                                spinner.setAdapter(new ArrayAdapter<String>(CoachingsActivity.this,android.R.layout.simple_list_item_1,city));

                            }else{
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CoachingsActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
//                /params.put("name",name);
                params.put("id",id);
//                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void getCoachings(final String id, String url){
        coachingses.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                JSONArray jsonArray=jsonObject.getJSONArray("coachings");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    coachingses.add(new Coachings(jsonObject1.getString("name"),jsonObject1.getString("about"),jsonObject1.getString("address"),jsonObject1.getString("city"),
                                            jsonObject1.getString("longitude"),
                                            jsonObject1.getString("latitude"),
                                            jsonObject1.getString("website"),
                                            jsonObject1.getString("contact")));
                                }

                                coachingListAdapter=new CoachingListAdapter(CoachingsActivity.this,coachingses);
                                listView.setAdapter(coachingListAdapter);
//                                coachingListAdapter.notifyDataSetChanged();
//
//                                JSONArray jsonArray1=jsonObject.getJSONArray("cities");
//                                for(int i=0;i<jsonArray1.length();i++){
////                                    JSONObject jsonObject1=jsonArray1.getJSONObject(i);
//                                    city.add(jsonArray1.getString(i));
//                                }
//                                spinner.setAdapter(new ArrayAdapter<String>(CoachingsActivity.this,android.R.layout.simple_list_item_1,city));

                            }else{
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CoachingsActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
//                /params.put("name",name);
                params.put("id",id);
//                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void getNewCoachings(final String id, String url, final String city){
        coachingListAdapter.clear();
        coachingses.clear();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                JSONArray jsonArray=jsonObject.getJSONArray("coachings");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    coachingses.add(new Coachings(jsonObject1.getString("name"),jsonObject1.getString("about"),jsonObject1.getString("address"),jsonObject1.getString("city"),
                                            jsonObject1.getString("longitude"),
                                            jsonObject1.getString("latitude"),
                                            jsonObject1.getString("website"),
                                            jsonObject1.getString("contact")));
                                }

                                coachingListAdapter=new CoachingListAdapter(CoachingsActivity.this,coachingses);
                                listView.setAdapter(coachingListAdapter);
                                coachingListAdapter.notifyDataSetChanged();

//                                JSONArray jsonArray1=jsonObject.getJSONArray("cities");
//                                for(int i=0;i<jsonArray1.length();i++){
////                                    JSONObject jsonObject1=jsonArray1.getJSONObject(i);
////                                    city.add(jsonArray1.getString(i));
////                                    spinner.setAdapter(new ArrayAdapter<String>(CoachingsActivity.this,android.R.layout.simple_list_item_1,city));
//                                }
                            }else{
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CoachingsActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("city",city);
                params.put("id",id);
//                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        coachingListAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}