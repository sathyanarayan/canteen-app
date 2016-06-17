package com.example.ubundu.canteen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;

    public static final String Password = "passkey";
    EditText em;
    EditText pass;
    String eml;
    String ps;
    String checkmail=null;


    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b=(Button)findViewById(R.id.button);
         em=(EditText)findViewById(R.id.editText2);
         pass=(EditText)findViewById(R.id.editText);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        checkmail=sharedpreferences.getString("emailkey",null);
        editor.putInt("month2",6);
        editor.putInt("history",0);
        editor.commit();
        if(sharedpreferences.getString("emailkey",null)!=null)
        { Toast.makeText(getApplicationContext(),checkmail,Toast.LENGTH_LONG).show();

            Intent p=new Intent(MainActivity.this,Menu.class);
            startActivity(p);
        }

       b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 eml=em.getText().toString();
                 ps=pass.getText().toString();
                editor.putString("emailkey",eml);
                editor.putInt("history",0);
                editor.putString(Password,ps);
                editor.commit();
                Toast.makeText(getApplicationContext(),"prvious"+checkmail+" " +"current:"+sharedpreferences.getString("emailkey",null),Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,Menu.class);
                startActivity(i);

            }
        });}
    @Override
    public void onBackPressed() {
        Intent k=new Intent(MainActivity.this,MainActivity.class);
        startActivity(k);
        finish();
    }
    }

