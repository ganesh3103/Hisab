package com.fitness.hisabh;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Display extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    RecyclerView listView,listView1;
    ArrayList<Student> al = new ArrayList<>(  );
    MyAdapter myAdapter;
    String id,name,surname,date;
    Dbhelper dbh;
    EditText ed1;
    Button bk,b;
    static String q1;
    static String current;
    Button qws;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_display );
        getSupportActionBar().hide();
        qws = (Button)findViewById( R.id.ed);
        ed1 = (EditText)findViewById( R.id.kk );
        bk = (Button)findViewById( R.id.kk1 );
        b = (Button)findViewById( R.id.ki );
        listView = (RecyclerView) findViewById( R.id.list_item );
        dbh = new Dbhelper( Display.this );
        loaddata();
        myAdapter = new MyAdapter( Display.this,al );
        listView.setAdapter( myAdapter );
        listView.setLayoutManager( new LinearLayoutManager( Display.this ) );
         myAdapter.notifyDataSetChanged();
         qws.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent dk = new Intent( Display.this,MainActivity.class );
                 startActivity( dk );
             }
         } );
       bk.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               q1 = ed1.getText().toString();

               Intent jk = new Intent( Display.this,Search.class );
               startActivity( jk );

              /* loaddata1();
               myAdapter = new MyAdapter( Display.this,al );
               listView1.setAdapter( myAdapter );
               listView1.setLayoutManager( new LinearLayoutManager( Display.this ) );
               myAdapter.notifyDataSetChanged();*/

           }
       } );
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment date = new DatePickerFragment();
                date.show(getSupportFragmentManager(),"date picker");
                ed1.setText( current );
            }
        } );


      /* ed1.addTextChangedListener( new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       } ); */
    }




    public static String qk()
    {
        return q1;
    }

    private void loaddata() {


        Cursor cursor = dbh.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText( this, "no data", Toast.LENGTH_SHORT ).show();
        }


        while (cursor.moveToNext())
        {
            id = cursor.getString( 0 );
            name = cursor.getString( 1 );
            surname = cursor.getString( 2 );
            date = cursor.getString( 3 );
            Student s1 = new Student( id, name, surname,date );
            al.add( s1 );


        }




    }
    private void loaddata1() {


        Cursor cursor = dbh.getAllData1();
        if (cursor.getCount() == 0) {
            Toast.makeText( this, "no data", Toast.LENGTH_SHORT ).show();
        }


        while (cursor.moveToNext())
        {
            id = cursor.getString( 0 );
            name = cursor.getString( 1 );
            surname = cursor.getString( 2 );
            date = cursor.getString( 3 );
            Student s1 = new Student( id, name, surname,date );
            al.add( s1 );







        }




    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        Calendar c = Calendar.getInstance(  );
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        c.set(Calendar.DAY_OF_MONTH,i2);
        current = DateFormat.getDateInstance(DateFormat.FULL  ).format( c.getTime() );


    }


}


