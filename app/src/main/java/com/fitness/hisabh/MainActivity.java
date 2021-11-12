package com.fitness.hisabh;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int god;
    Button bt, bt1, bt2, k;
    RecyclerView listView;
    ArrayList<Student> al = new ArrayList<>();
    ArrayList<String> la = new ArrayList<>();
    MyAdapter myAdapter;
    ArrayList<String> eid;
    String id, name, surname, date;
    String id1, name1, surname1, date1;
    Dbhelper dbh;
    String oki;
    private static final String[] STORAGE_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final String[] STORAGE_PERMISSIONS1 = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.action );
        verify();
        dbh = new Dbhelper( MainActivity.this );
        bt1 = findViewById( R.id.b1 );
        bt = findViewById( R.id.add );

        bt1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Display.class );
                startActivity( intent );


            }
        } );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, add.class );
                startActivity( intent );
            }
        } );


    }
    private void verify() {
        int permission_memory = ActivityCompat.checkSelfPermission( MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE );
        int permission_net = ActivityCompat.checkSelfPermission( MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE );

        if(permission_memory != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions( MainActivity.this,STORAGE_PERMISSIONS,1 );
        }

    }

}





