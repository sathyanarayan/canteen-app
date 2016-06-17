package com.example.ubundu.canteen;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Order extends Activity {


    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public int count=0;
    String arrText[]=new String[20];
    private String[] arrTemp;
    Button b;
    int l=0;
    int history=0;
    String Name="User id:";
    String order=" ";
    String hv="you have ordered:";
    String comma=",";
    String dash="-";
    final Context context = this;
    int total=0;
    int ch=1;
    int t1[]=new int[10];
    int t2[]=new int[10];
    int ct=0,ct1=0;
    String s="notgotit";
    String brkfst1[]=new String[10];
    String brkfst2[]=new String[10];
    String lunch1[]=new String[10];
    String lunch2[]=new String[10];
    String lunch3[]=new String[10];
    String snack1[]=new String[10];
    String snack2[]=new String[10];
String placevalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        Name=Name.concat(sharedpreferences.getString("emailkey",null));
        placevalue=sharedpreferences.getString("val",null);
        String array[]=getIntent().getStringArrayExtra("array");
        int size = sharedpreferences.getInt("array_size", 0);

        for(int i=0;i<size;i++)
        {brkfst1[i] = sharedpreferences.getString("array_" + i, null);
            brkfst2[i] = sharedpreferences.getString("array1_" + i, null);
            lunch1[i] = sharedpreferences.getString("array2_" + i, null);
            lunch2[i] = sharedpreferences.getString("array3_" + i, null);
            lunch3[i] = sharedpreferences.getString("array4_" + i, null);
            snack1[i] = sharedpreferences.getString("array5_" + i, null);
            snack2[i] = sharedpreferences.getString("array6_" + i, null);}
        for(int i=0;i<array.length;i++)
        {if(array[i]==null)
        { }
        else if(array[i].contentEquals("null")){}
            else{
            arrText[count]=array[i];
            count=count+1;
            }

        }

        arrTemp = new String[arrText.length];
        b=(Button)findViewById(R.id.button);
        MyListAdapter myListAdapter = new MyListAdapter();
        ListView listView = (ListView) findViewById(R.id.listViewMain);
        listView.setAdapter(myListAdapter);
         for(int p=0;p<count;p++)
         {arrTemp[p]="1";
          t1[p]=1;t2[p]=1;}
        for(int p=0;p<count;p++)
        {for(int i=0;i<brkfst1.length;i++)
        {if(arrText[p].equals(brkfst1[i]))
        {t2[p]=7;
        }
        else if(arrText[p].equals(brkfst2[i]))
        {t2[p]=7;}
        else if(arrText[p].equals(lunch1[i]))
        {t2[p]=20; }
        else if(arrText[p].equals(lunch2[i]))
        {t2[ct]=20;
            }
        else if(arrText[p].equals(lunch3[i]))
        {t2[p]=25;}
        else if(arrText[p].equals(snack1[i]))
        {t2[p]=6;}
        else if(arrText[p].equals(snack2[i]))
        {t2[p]=6;}

        }
        }
        for(int p=0;p<count;p++){
            t1[p]=Integer.valueOf(arrTemp[p]);}
        for(int k=0;k<count;k++)
        {total=total+(t2[k]*Integer.valueOf(arrTemp[k]));}

        final String t=String.valueOf(total);
        history=sharedpreferences.getInt("history",0);
        history=history+total;
        editor.putInt("history",history);
        editor.commit();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=0;
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("YOUR TOTAL"+" "+"$"+total+" "+history);
                order=order.concat(Name);
                order=order.concat("  ");
                order=order.concat(hv);

                for(i=0;i<count;i++)
                {
                    order=order.concat(arrText[i]);
                    order=order.concat(dash);
                    order=order.concat(arrTemp[i]);
                    order=order.concat(comma);
                   }

                // set title


                // set dialog message
                alertDialogBuilder
                        .setMessage(order)
                        .setCancelable(false)
                        .setPositiveButton("confirm",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                editor.putInt("total",total);
                                editor.commit();
                                Intent l=new Intent(Order.this,MenuUpload.class);
                                l.putExtra("order",order);
                                l.putExtra("name",Name);
                                startActivity(l);
                                finish();
                            }
                        })
                        .setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                if(ch==1)
                                {Intent l=new Intent(Order.this,Selectmenu.class);
                                    l.putExtra("val",placevalue);
                                startActivity(l);
                                finish();}
                                else {dialog.cancel();}

                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }


    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if(arrText != null && arrText.length != 0){
                return count;
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return arrText[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //ViewHolder holder = null;
            final ViewHolder holder;
            if (convertView == null) {

                holder = new ViewHolder();
                LayoutInflater inflater = Order.this.getLayoutInflater();
                convertView = inflater.inflate(R.layout.lyt_listview_list, null);
                holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
                holder.editText1 = (EditText) convertView.findViewById(R.id.editText1);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.ref = position;

            holder.textView1.setText(arrText[position]);
            holder.editText1.setText(arrTemp[position]);
            holder.editText1.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    arrTemp[l]=holder.editText1.getText().toString();
                    t2[l]=Integer.valueOf(holder.editText1.getText().toString());
                    l=l+1;
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    arrTemp[holder.ref] = arg0.toString();


                }
            });

            return convertView;
        }

        private class ViewHolder {
            TextView textView1;
            EditText editText1;
            int ref;
        }


    }

}
