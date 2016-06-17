package com.example.ubundu.canteen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ubundu on 23/5/16.
 */
public class Selectmenu extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    Button btn;
String smcopy;
    Button bt;
    String d="order:";
    int h=0;
    int bill=0;
    int mr=25,ms=20,br=10,snk=7,js=10,ic=10;
    String sm;
    HashMap<String,List<String>> listDataChild;
    int a[]=new int[100];
    String b[]=new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectmenu);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sm=getIntent().getExtras().getString("val");

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        btn=(Button)findViewById(R.id.b);
        bt=(Button)findViewById(R.id.b2);
        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader,listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        for(int i=0;i<20;i++)
        {a[i]=0;}
        for(int i=0;i<20;i++)
        {b[i]="null";}


        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {


            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                if(a[childPosition]==0){v.setBackgroundColor(Color.LTGRAY);
                    a[childPosition]=1;

                    b[childPosition]=listDataChild.get(
                            listDataHeader.get(groupPosition)).get(
                            childPosition);

                 }
                else{a[childPosition]=0;
                    b[childPosition]=null;

                    v.setBackgroundColor(Color.WHITE);}
                return false;

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog rankDialog = new Dialog(Selectmenu.this, R.style.FullHeightDialog);
                rankDialog.setContentView(R.layout.rank_dialog);
                rankDialog.setCancelable(true);
                RatingBar ratingBar = (RatingBar)rankDialog.findViewById(R.id.dialog_ratingbar);

                float userRankValue=0;
                ratingBar.setRating(userRankValue);

                EditText text = (EditText) rankDialog.findViewById(R.id.rank_dialog_text1);

                Button updateButton = (Button) rankDialog.findViewById(R.id.rank_dialog_button);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rankDialog.dismiss();
                    }
                });
                //now that the dialog is set up, it's time to show it
                rankDialog.show();
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent j=new Intent(Selectmenu.this,Order.class);
                j.putExtra("array",b);
                startActivity(j);

            }
        });


    }


    /*
     * Preparing the list data
     */
    private void prepareListData() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        int size = sharedpreferences.getInt("array_size", 0);
        String brkfst1[]=new String[10];
        String brkfst2[]=new String[10];
        String lunch1[]=new String[10];
        String lunch2[]=new String[10];
        String lunch3[]=new String[10];
        String snack1[]=new String[10];
        String snack2[]=new String[10];
        for(int i=0;i<size;i++)
        {brkfst1[i] = sharedpreferences.getString("array_" + i, null);
            brkfst2[i] = sharedpreferences.getString("array1_" + i, null);
            lunch1[i] = sharedpreferences.getString("array2_" + i, null);
            lunch2[i] = sharedpreferences.getString("array3_" + i, null);
            lunch3[i] = sharedpreferences.getString("array4_" + i, null);
            snack1[i] = sharedpreferences.getString("array5_" + i, null);
            snack2[i] = sharedpreferences.getString("array6_" + i, null);}
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        String vgMeal[]={"monday rmeal","tuesday rmeal","wed rmeal"," thus rmeal","fri rmeal"};
        String vgspmeal[]={"monday vsrmeal","tuesday vsrmeal","wed vsrmeal"," thus vsrmeal","fri vsrmeal"};
        String nvgmeal[]={"monday nvrmeal","tuesday nvrmeal","wed nvrmeal"," thus nvrmeal","fri nvrmeal"};
        String nvgspmeal[]={"monday nvsrmeal","tuesday nvsrmeal","wed nvsrmeal"," thus nvsrmeal","fri nvsrmeal"};
       // String brkfst[]={"monday rbrk","tuesday rbk","wed rbrk"," thus rbrk","fri rbrk"};
        String Snacks[]={"monday rsnk","tuesday snk","wed rsnk"," thus snk","fri rsnk"};

        // Adding child data
        listDataHeader.add("Food");
        listDataHeader.add("juice");
        listDataHeader.add("icecream");
        Calendar c=Calendar.getInstance();
        int m=c.get(Calendar.DAY_OF_WEEK);List<String> top250 = new ArrayList<String>();

        int p=m-2;
        if(sm.trim().equals("lunch"))
        {h=2;}
        else if(sm.trim().equals("breakfast"))
        {h=1;}
        else {h=3;}
        // Adding child data

        if(h==1) {

           // top250.add(brkfst[p]);
            top250.add(brkfst1[p]);
            top250.add(brkfst2[p]);

        }
        else if(h==2) {

            top250.add(lunch1[p]);

            top250.add(lunch2[p]);
            top250.add(lunch3[p]);

        }
        else {top250.add(snack1[p]);
            top250.add(snack2[p]);
        }
        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("juice 1");
        nowShowing.add("juice 2");
        nowShowing.add("juice 3");
        nowShowing.add("juice 4");
        nowShowing.add("juice 5");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("icream 1");
        comingSoon.add("icecream 2");
        comingSoon.add("icecream 3");
        comingSoon.add("icecream 4");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

    public void onBackPressed() {
        Intent k=new Intent(Selectmenu.this,Menu.class);
        startActivity(k);
        finish();
    }


}

