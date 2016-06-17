package com.example.ubundu.canteen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ubundu on 8/6/16.
 */
public class Menudisplay extends AppCompatActivity {

    private static final String DEBUG_TAG = "HttpExample";
    public static final String MyPREFERENCES = "MyPrefs" ;
    ArrayList<Team> teams = new ArrayList<Team>();
    ListView listview;
    Button btnDownload;
    Button orderfood;
    int l;

    int change;
    String val;
    String breakfast1[]=new String[20];
    String breakfast2[]=new String[20];
    String lunch1[]=new String[20];
    String lunch2[]=new String[20];
    String lunch3[]=new String[20];
    String snack1[]=new String[20];
    String snack2[]=new String[20];
    int foodcount=0;
    String plcs[]=new String[30];
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menudisplay);
        listview = (ListView) findViewById(R.id.listview);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        orderfood= (Button) findViewById(R.id.button6);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        l=getIntent().getExtras().getInt("intval");
        change=getIntent().getExtras().getInt("change");
        val=getIntent().getExtras().getString("val");
        if(change==1){call();}
        plcs[1]="https://spreadsheets.google.com/tq?key=1SdDFzJFRabNq5ZuZD_gvzyrCKz12VdsPCFgBpV6zbiQ";
        plcs[0]="https://spreadsheets.google.com/tq?key=1SdDFzJFRabNq5ZuZD_gvzyrCKz12VdsPCFgBpV6zbiQ";
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            btnDownload.setEnabled(true);
        } else {
            btnDownload.setEnabled(false);
        }
        orderfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=getIntent().getExtras().getString("val",null);
                Intent k=new Intent(Menudisplay.this,Selectmenu.class);
                k.putExtra("val",value);
                startActivity(k);
            }
        });
    }

    public void buttonClickHandler(View view) {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
                final SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("array_size", foodcount);
                for(int i=0;i<foodcount; i++)
                {editor.putString("array_" + i, breakfast1[i]);
                    editor.putString("array1_"+i,breakfast2[i]);
                    editor.putString("array2_"+i,lunch1[i]);
                    editor.putString("array3_"+i,lunch2[i]);
                    editor.putString("array4_"+i,lunch3[i]);
                    editor.putString("array5_"+i,snack1[i]);
                    editor.putString("array6_"+i,snack2[i]);

                    editor.commit();}
            }
        }).execute(plcs[l]);

    }

    public void call(){
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
                final SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("array_size", foodcount);
                for(int i=0;i<foodcount; i++)
                {editor.putString("array_" + i, breakfast1[i]);
                    editor.putString("array1_"+i,breakfast2[i]);
                    editor.putString("array3_"+i,lunch1[i]);
                    editor.putString("array4_"+i,lunch2[i]);
                    editor.putString("array5_"+i,lunch3[i]);
                    editor.putString("array6_"+i,snack1[i]);
                    editor.putString("array7_"+i,snack2[i]);

                    editor.commit();}
            }
        }).execute(plcs[l]);
    }

    private void processJson(JSONObject object) {

        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                int position = columns.getJSONObject(0).getInt("v");
                String name = columns.getJSONObject(1).getString("v");
                breakfast1[foodcount]=columns.getJSONObject(1).getString("v");

                String wins = columns.getJSONObject(2).getString("v");
                breakfast2[foodcount]=columns.getJSONObject(2).getString("v");
                String draws = columns.getJSONObject(3).getString("v");
                lunch1[foodcount]=columns.getJSONObject(3).getString("v");
                String losses = columns.getJSONObject(4).getString("v");
                lunch2[foodcount]=columns.getJSONObject(4).getString("v");
                String points = columns.getJSONObject(5).getString("v");
                lunch3[foodcount]=columns.getJSONObject(5).getString("v");
                String snk1=columns.getJSONObject(6).getString("v");
                snack1[foodcount]=columns.getJSONObject(6).getString("v");
                String snk2=columns.getJSONObject(7).getString("v");
                snack2[foodcount]=columns.getJSONObject(7).getString("v");
                foodcount=foodcount+1;
                Team team = new Team(position, name, wins, draws, losses, points,snk1,snk2);
                teams.add(team);
            }
            if(change==1){ final SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("array_size", foodcount);
                for(int i=0;i<foodcount; i++)
                {editor.putString("array_" + i, breakfast1[i]);
                    editor.putString("array1_"+i,breakfast2[i]);
                    editor.putString("array3_"+i,lunch1[i]);
                    editor.putString("array4_"+i,lunch2[i]);
                    editor.putString("array5_"+i,lunch3[i]);
                    editor.putString("array6_"+i,snack1[i]);
                    editor.putString("array7_"+i,snack2[i]);

                    editor.commit();}
                Intent k=new Intent(Menudisplay.this,Selectmenu.class);
                   k.putExtra("val",val);
                  startActivity(k);}

            final TeamsAdapter adapter = new TeamsAdapter(this, R.layout.team, teams);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
