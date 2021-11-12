package com.fitness.hisabh;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    RecyclerView lw;
    ArrayList<Student> al = new ArrayList<>();
    MyAdapter myAdapter;
    String id, name, surname, date;
    Dbhelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.action );
        lw = (RecyclerView) findViewById( R.id.list_item2 );
        dbh = new Dbhelper( Search.this );
        loaddata1();
        myAdapter = new MyAdapter( Search.this,al );
        lw.setAdapter( myAdapter );
        lw.setLayoutManager( new LinearLayoutManager( Search.this ) );
        myAdapter.notifyDataSetChanged();
    }

    private void loaddata1() {


        Cursor cursor = dbh.getAllData1();
        if (cursor.getCount() == 0) {
            Toast.makeText( this, "no data", Toast.LENGTH_SHORT ).show();
        }


        while (cursor.moveToNext()) {
            id = cursor.getString( 0 );
            name = cursor.getString( 1 );
            surname = cursor.getString( 2 );
            date = cursor.getString( 3 );
            Student s1 = new Student( id, name, surname, date );
            al.add( s1 );

        }
    }
}