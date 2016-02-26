package com.example.kiran.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity {
    String url = "http://api.androidhive.info/contacts/";
    Context context;
    TextView tv_id, tv_name, tv_email, tv_gender, tv_address, tv_phone;
    ListView LV_disp;
    ArrayList<ListData> ld = new ArrayList<ListData>();
    String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RetriveFeedtask().execute();
    }


    class RetriveFeedtask extends AsyncTask<String, Integer, String> {
        String[] id, name, email, address, gender, phone;
        JSONObject jsonObjectinner;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("on pre execute ", "started");
        }

        @Override
        protected String doInBackground(String... urls) {
            HttpClient hc = new DefaultHttpClient();
            try {
//                response = EntityUtils.toString((hc.execute(new HttpGet(url)).getEntity()));
                response=EntityUtils.toString(hc.execute(new HttpGet(url)).getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
           /* try {
                HttpClient hc = new DefaultHttpClient();
//                HttpGet hget=new HttpGet(url);
//                HttpResponse hresponse=hc.execute(hget);
//                HttpEntity entity=hresponse.getEntity();
                response = EntityUtils.toString((hc.execute(new HttpGet(url)).getEntity()));
                JSONObject jsonObject = new JSONObject(response);
                Log.d("jsonObjectdata", "el" + jsonObject.length());
                JSONArray jsonArray = jsonObject.getJSONArray("contacts");
                Log.d("jsonObjectdata", "el" + jsonArray);
             *//*   array construction
                id = new String[jsonArray.length()];
                name = new String[jsonArray.length()];
                email = new String[jsonArray.length()];
                address = new String[jsonArray.length()];
                gender = new String[jsonArray.length()];*//*
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectinner = jsonArray.getJSONObject(i);
//                    initilization of array
                    ListData listDataobject = new ListData();
                    listDataobject.setId(jsonObjectinner.getString("id"));
                    listDataobject.setName(jsonObjectinner.getString("name"));
                    listDataobject.setEmail(jsonObjectinner.getString("email"));
                    listDataobject.setGender(jsonObjectinner.getString("gender"));
                    listDataobject.setAddress(jsonObjectinner.getString("address"));
                    ld.add(listDataobject);
                   *//* id[i] = jsonObjectinner.getString("id");
                    name[i] = jsonObjectinner.getString("name");
                    email[i] = jsonObjectinner.getString("email");
                    address[i] = jsonObjectinner.getString("address");
                    gender[i] = jsonObjectinner.getString("gender");*//*

                }*//*json for loop*//*


            } catch (Exception e) {
                e.printStackTrace();
            }*/

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.d("jsonObjectdata", "el" + jsonObject.length());
                JSONArray jsonArray = jsonObject.getJSONArray("contacts");
                Log.d("jsonarray",""+jsonArray);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectinner = jsonArray.getJSONObject(i);
//                    initilization of array
                    ListData listDataobject = new ListData();
                    listDataobject.setId(jsonObjectinner.getString("id"));
                    listDataobject.setName(jsonObjectinner.getString("name"));
                    listDataobject.setEmail(jsonObjectinner.getString("email"));
                    listDataobject.setGender(jsonObjectinner.getString("gender"));
                    listDataobject.setAddress(jsonObjectinner.getString("address"));
                    ld.add(listDataobject);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            LV_disp = (ListView) findViewById(R.id.LV_display);
            customArrayAdapter caa = new customArrayAdapter(getApplicationContext(), ld);
            Log.d("the caa value is", "" + caa.isEmpty());
            LV_disp.setAdapter(caa);
        }
    }

}

/*initilization purpose check*/
/*  Log.d("the name isname[0]",name[3].toString());
            Log.d("the email isemiail[0]",email[3].toString());
            Log.d("the email is address[0]",address[3].toString());
            Log.d("the email isgender[0]", gender[3].toString());*/