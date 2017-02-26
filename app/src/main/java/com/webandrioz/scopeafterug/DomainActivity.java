package com.webandrioz.scopeafterug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.webandrioz.scopeafterug.adapters.DomainGridAdapter;
import com.webandrioz.scopeafterug.constants.Constants;
import com.webandrioz.scopeafterug.models.Domains;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DomainActivity extends AppCompatActivity {
    private  final String TAG = getClass().getName();
    GridView gridView;
    ArrayList<Domains> domain=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);
        gridView= (GridView) findViewById(R.id.domainGridView);
        getDomain();

    }

    public void getDomain(){
        String REGISTER_URL= Constants.BASE_URL+ Constants.DOMAIN_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                JSONArray jsonArray=jsonObject.getJSONArray("domain");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    domain.add(new Domains(jsonObject1.getString("domain_name"),jsonObject1.getString("domain_id")));
                                }

                                gridView.setAdapter(new DomainGridAdapter(DomainActivity.this,domain));
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
                        Toast.makeText(DomainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
//                /params.put("name",name);
//                params.put("password",password);
//                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
