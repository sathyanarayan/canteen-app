package com.example.ubundu.canteen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


import java.net.URLEncoder;


public class MenuUpload extends Activity {

    final String myTag = "DocsUpload";
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    int history=0;
    int ht=0;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuupload);
        Log.i(myTag, "OnCreate()");
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        s1=getIntent().getExtras().getString("name");
        s2=getIntent().getExtras().getString("order");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                postData();

            }
        });
        t.start();


    }



    public void postData() {

        String fullUrl = "https://docs.google.com/forms/d/1btgrj2uHay7HqR0mG6fTGIhq-HZ29nwYRZiU8ZQN2qM/formResponse";
        HttpRequest mReq = new HttpRequest();
        int  s3=getIntent().getExtras().getInt("total");
        String col1 = s1;
        String col2 = s2;
        String col3=String.valueOf(s3);

        String data = "entry.1058172160=" + URLEncoder.encode(col1) + "&" +
                "entry.380066940=" + URLEncoder.encode(col2)+ "&" +
                "entry.1894411289=" + URLEncoder.encode(col3);
        String response = mReq.sendPost(fullUrl, data);
        Log.i(myTag, response);
    }

    @Override
    public void onBackPressed() {
        Intent k=new Intent(MenuUpload.this,Menu.class);
        startActivity(k);
        finish();
    }

}
