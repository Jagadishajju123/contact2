package com.example.pavillon.contacts_delta;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class contactsdelta extends ActionBarActivity {

    String[] names = {"Jagadish", "Peter","Ranbir","Caffrey","Mozzie","Durke","Clinton","Jones","Keller"};
    ArrayList<String> names_descend= new ArrayList<String>();
    String temp= null;
    int i,j,flag=0;
    ListView list;
    Button b1,b2;
    EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactsdelta);
        list=(ListView) findViewById(R.id.listView);
        //Log.d("hey","list initialized");
        ArrayAdapter adapt = new ArrayAdapter(this, R.layout.row, R.id.text,names);
        //Log.d("hey","adapter initialized");
        list.setAdapter(adapt);
        //Log.d("hey","adapter set");

        b1=(Button) findViewById(R.id.sort);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(flag==0) {
                    b1.setText("Sort Descending");
                    for (i = 0; i < names.length; i++) {
                        for (j = 1; j < names.length - i; j++) {
                            if (names[j].compareTo(names[j - 1]) < 0) {
                                temp = names[j];
                                names[j] = names[j - 1];
                                names[j - 1] = temp;
                            }
                        }
                    }
                    for(i=names.length-1;i>=0;i--)
                        names_descend.add(names[i]);
                    list.setAdapter( new ArrayAdapter(getApplicationContext(), R.layout.row, R.id.text,names));

                    flag=1;
                }
                else if (flag==1)
                {
                    flag=2;
                    b1.setText("Sort Ascending");
                    list.setAdapter( new ArrayAdapter(getApplicationContext(), R.layout.row, R.id.text,names_descend));
                }
                else{
                    flag=1;
                    b1.setText("Sort Descending");
                    list.setAdapter( new ArrayAdapter(getApplicationContext(), R.layout.row, R.id.text,names));
                }
            }
        });
        b2=(Button) findViewById(R.id.SearchButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et=(EditText) findViewById(R.id.SearchText);
                temp=et.getText().toString();
                j=0;

                for (i=0;i<names.length;i++)
                {

                    if(names[i].equals(temp))
                    {

                        Toast t=Toast.makeText(getApplicationContext(),"Contact found: "+temp,Toast.LENGTH_SHORT);
                        t.setGravity(Gravity.CENTER,0,0);
                        t.show();
                        j=1;
                    }
                }
                if(j==0)
                {
                    Toast t=Toast.makeText(getApplicationContext(),"Missing", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER,0,0);
                    t.show();
                }
            }
        });
    }

}


