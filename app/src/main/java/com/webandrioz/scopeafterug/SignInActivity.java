package com.webandrioz.scopeafterug;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class SignInActivity extends AppCompatActivity {

    private  final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final EditText email= (EditText) findViewById(R.id.email);
        final EditText password= (EditText) findViewById(R.id.password);
        TextView forgotPassword= (TextView) findViewById(R.id.forgotPassword);
        TextView signUp= (TextView) findViewById(R.id.signupText);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(SignInActivity.this, "Coming soon..", Toast.LENGTH_SHORT).show();
                showFogotPasswodDialouge();
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(in);
            }
        });
        Button login= (Button) findViewById(R.id.signInButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(SignInActivity.this, "All Field Is Mandatory.", Toast.LENGTH_SHORT).show();
                    
                }else{
                signIncall("ASA",email.getText().toString(),password.getText().toString());


                }
            }
        });
        
        
    }
    public void signIncall(final String name, final String email, final String password){
        String REGISTER_URL= Constants.BASE_URL+ Constants.LOGIN_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                Toast.makeText(SignInActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                Intent in=new Intent(SignInActivity.this,DomainActivity.class);
                                startActivity(in);
                                finish();
                            }else{
                                Toast.makeText(SignInActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignInActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("password",password);
                params.put("email", email);
                params.put("type",Constants.CUSTOM_SIGNUP);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void forgotPasswordCall( final String email){
        String REGISTER_URL= Constants.BASE_URL+ Constants.FORGOT_PASSWORD;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                Toast.makeText(SignInActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//                                Intent in=new Intent(SignInActivity.this,DomainActivity.class);
//                                startActivity(in);
//                                finish();
                            }else{
                                Toast.makeText(SignInActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignInActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showFogotPasswodDialouge() {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(SignInActivity.this);
        View promptsView = li.inflate(R.layout.forget_password_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignInActivity.this);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.forgotEmail);
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                              if(userInput.getText().toString().isEmpty()){
                                  Toast.makeText(SignInActivity.this, "Enter Email id", Toast.LENGTH_SHORT).show();
                              }else{
                                  forgotPasswordCall(userInput.getText().toString());
                              }


                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
