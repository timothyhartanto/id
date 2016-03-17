package com.example.proto.launcher.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.proto.launcher.JSONParser;
import com.example.proto.launcher.Login;
import com.example.proto.launcher.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MultiFragment extends AppCompatActivity {
    public static String PREF_NAME = "loginFile";
    private static final String LOGIN_URL = "http://192.168.131.1:8080/webservice/login.php";
    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //BUG TO FIX

        // Restore preferences
        SharedPreferences.Editor edit = getSharedPreferences("loginFile", MODE_PRIVATE).edit();
        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        getPref(PREF_NAME, this);
        boolean already_logged_in = sp.getBoolean("loggedIn", false);

        if(!already_logged_in){
            Intent i = new Intent(MultiFragment.this, Login.class);
            startActivity(i);
        }
        if(already_logged_in){
            String username = sp.getString("userName", null);
            String password = sp.getString("password", null);
//            Login.AttemptLogin login = new Login().new AttemptLogin();
//            login.execute(username, password);

            // JSON parser class
            JSONParser jsonParser = new JSONParser();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));

            try {
            // getting product details by making HTTP request
            JSONObject json = jsonParser.makeHttpRequest(
                    LOGIN_URL, "POST", params);

            // check your log for json response
            Log.d("Login attempt", json.toString());


                // json success tag
                int success = json.getInt(TAG_SUCCESS);
                if (success != 1) {
                    edit.clear();
                    edit.commit();
                    Intent i = new Intent(MultiFragment.this, Login.class);
                    startActivity(i);
                }
            }
            catch(Exception e){
                edit.clear();
                edit.commit();
                Intent i = new Intent(MultiFragment.this, Login.class);
                startActivity(i);
            }
        }

        setContentView(R.layout.multi_fragment);

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.menuContainer, new MenuFragment()).commit();
            }

    }

    public static SharedPreferences getPref(String file, Context context){
        return context.getSharedPreferences(file, Context.MODE_PRIVATE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Restore preferences
        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean already_logged_in = sp.getBoolean("loggedIn", false);

        if(already_logged_in){

        }
    }
}
