package com.fitness.hisabh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.util.Calendar.DATE;

public class Dbhelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String col_1 = "ID";
    public static final String col_2 = "NAME";
    public static final String col_3 = "SURNAME";
    public static final String col_4 = "DTA";
    public static String c;




    public Dbhelper(Context context) {
        super( context, DATABASE_NAME, null, 1 );

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,DTA TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( sqLiteDatabase );
    }

    public boolean insertData(String name, String surname,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( col_2, name );
        contentValues.put( col_3, surname );
        contentValues.put( col_4, date );
        long result = db.insert( TABLE_NAME, null, contentValues );
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

     public Cursor getAllData() {

        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from "+ TABLE_NAME,null);
        return c;
    }
    public Cursor getAllData1() {
              String ss,s1,s2,s3;
              Display display = new Display();
              String w = display.qk();


        s1 = w;
        s2 = "'";
        s3 = "%';";
                Log.w( "tt",""+s1+s2+s3);
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("SELECT * FROM student_table WHERE DTA like "+s2+s1+s3 ,null);
        return c;


    }
    public Cursor getAllData2() {
        String ss,s1,s2,s3;
        Aa display1 = new Aa();
        String w = display1.qk1();


        s1 = w;
        s2 = "'";
        s3 = "';";
        Log.w( "tt",""+s1+s2+s3);
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("SELECT * FROM student_table WHERE DTA like "+s2+s1+s3 ,null);
        return c;


    }
}
