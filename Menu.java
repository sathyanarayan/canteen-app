package com.example.ubundu.canteen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ubundu on 17/5/16.
 */
public class Menu extends AppCompatActivity {
       String s;
    public static final String MyPREFERENCES = "MyPrefs" ;
    int dsp,dsp2;
    Button img,history;
    Calendar c;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
Spinner dropdown =(Spinner)findViewById(R.id.spinner);
        final Spinner dropdown2 =(Spinner)findViewById(R.id.spinner2);
        img=(Button)findViewById(R.id.imageButton);

        history=(Button)findViewById(R.id.button5);
        String[]places=new String[]{"select canteen location","carnac bunder","dharavi","trombay","jojobera"} ;
        final String[] time=new String[]{"select time","breakfast","lunch","snacks"};
        final List<String> placelist=new ArrayList<>(Arrays.asList(places));
        final List<String> timelist=new ArrayList<>(Arrays.asList(time));
        Button b=(Button)findViewById(R.id.button2);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        final ArrayAdapter<String> t=new ArrayAdapter<String>(this,R.layout.spinner_item,placelist){
            @Override
            public boolean isEnabled(int position){
                if(position==0)
                {return false;}
                else
                {return true;}
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                View view=super.getDropDownView(position,convertView,parent);
                TextView tv=(TextView) view;
                if(position==0)
                {tv.setTextColor(Color.GRAY);
                tv.setTextSize(30);}
                else {tv.setTextColor(Color.BLACK);tv.setTextSize(30);}
                return view;
            }};
        t.setDropDownViewResource(R.layout.spinner_item);
        dropdown.setAdapter(t);
        final ArrayAdapter<String> k=new ArrayAdapter<String>(this,R.layout.spinner_item,timelist){
            @Override
            public boolean isEnabled(int position){
                if(position==0)
                {return false;}
                else
                {return true;}
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                View view=super.getDropDownView(position,convertView,parent);
                TextView tv=(TextView) view;

                if(position==0)
                {tv.setTextColor(Color.GRAY);tv.setTextSize(30);}
                else {tv.setTextColor(Color.BLACK);tv.setTextSize(30);}
                return view;
            }};
        k.setDropDownViewResource(R.layout.spinner_item);
        dropdown2.setAdapter(k);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                dsp=pos;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                dsp2=pos;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n=null;
                editor.putString("emailkey",n);
                editor.commit();
                Intent r=new Intent(Menu.this,MainActivity.class);
                startActivity(r);

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=time[dsp];
                editor.putString("val",s);
                editor.commit();
                Intent i=new Intent(Menu.this,Menudisplay.class);
                i.putExtra("val",s);
                i.putExtra("intval",dsp2);
                startActivity(i);
            }
        });
        c=Calendar.getInstance();
        int month=c.get(Calendar.MONTH);
        editor.putInt("manthvar1",month);
        editor.commit();
        int month2=sharedpreferences.getInt("month2",0);
        if(month2<month)
        {editor.putInt("history",0);
            editor.putInt("month2",month);
         editor.commit();
        }
        else{}


history.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        String his=String.valueOf(sharedpreferences.getInt("history",0));
        Toast.makeText(getApplicationContext(),"Toatal Amount history:"+his,Toast.LENGTH_LONG).show();
    }
});

    }
    @Override
    public void onBackPressed() {
        Intent k=new Intent(Menu.this,MainActivity.class);
        startActivity(k);
        finish();
    }

}
