package com.webandrioz.scopeafterug;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.webandrioz.scopeafterug.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    public final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText name= (EditText) findViewById(R.id.name);
        final EditText email= (EditText) findViewById(R.id.email);
        final EditText password= (EditText) findViewById(R.id.password);
        final Button signUp= (Button) findViewById(R.id.signup);
        
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || password.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this, "All Field Is Mandatory.", Toast.LENGTH_SHORT).show(); 
                }
                else {
                    signUpcall(name.getText().toString(),password.getText().toString(), email.getText().toString());
                }
            }
        });

    }

    public void signUpcall(final String name, final String email, final String password){
        String REGISTER_URL= Constants.BASE_URL+ Constants.CUSTOM_SIGNUP_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                Toast.makeText(SignUpActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                Intent in=new Intent(SignUpActivity.this,DomainActivity.class);
                                startActivity(in);
                                finish();
                            }else{
                                Toast.makeText(SignUpActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("password",password);
                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    }

