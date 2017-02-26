package com.webandrioz.scopeafterug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
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
    ArrayList<Coachings> coachingses=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coachings);
        listView= (ListView) findViewById(R.id.coachingListView);
        String id=getIntent().getStringExtra("id");
        getCoachings(id);

    }

    public void getCoachings(final String id){
        String REGISTER_URL= Constants.BASE_URL+ Constants.COACHINGS;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
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

                                listView.setAdapter(new CoachingListAdapter(CoachingsActivity.this,coachingses));
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
}