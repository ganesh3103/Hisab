package com.fitness.hisabh;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class add extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Dbhelper dbh;
    ArrayList<Student> al = new ArrayList<>(  );
    String id,name,surname;
    EditText ed1;
    EditText ed2;
    Button btn;
    String current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add );
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.action );
        dbh = new Dbhelper( add.this );
        ed1 = (EditText)findViewById( R.id.ed1 );
         ed2 = (EditText)findViewById( R.id.ed2 );
         btn = (Button)findViewById( R.id.btn );
        ImageButton b2 = (ImageButton) findViewById( R.id.bkn );
        TextView textView = (TextView)findViewById( R.id.txt );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is = dbh.insertData( ed1.getText().toString(),ed2.getText().toString(),current );
                if(is = true)
                {
                    Toast.makeText( add.this, "inserted", Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    Toast.makeText( add.this, "fail", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment date = new DatePickerFragment();
                date.show(getSupportFragmentManager(),"date picker");
            }
        } );
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment date = new DatePickerFragment();
                date.show(getSupportFragmentManager(),"date picker");

            }
        } );
    }



    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        Calendar c = Calendar.getInstance(  );
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        c.set(Calendar.DAY_OF_MONTH,i2);
         current = DateFormat.getDateInstance(DateFormat.FULL  ).format( c.getTime() );
        TextView textView = (TextView)findViewById( R.id.txt );
        textView.setText( current );
    }


    public String send(){
        String c2 = current;
        return c2;
    }
}
