package com.fitness.hisabh;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

import static com.fitness.hisabh.Ex.isExternalStorageAvailable;
import static com.fitness.hisabh.Ex.isExternalStorageReadOnly;

public class Aa extends AppCompatActivity {
    TextView tk;
     static String sti;
    RecyclerView listView2;
    ArrayList<Student> al = new ArrayList<>(  );
    Adapt adapt;
    String id,name,surname,date;
    Dbhelper dbh;
    Button button_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_aa );
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.action );
        listView2 = (RecyclerView) findViewById( R.id.R_item);
        button_1 = (Button)findViewById( R.id.button_1 );
        dbh = new Dbhelper( Aa.this );
        Intent in = getIntent();
        sti = in.getStringExtra( "ki" );
        load();
        adapt = new Adapt( Aa.this,al );
        listView2.setAdapter( adapt);
        listView2.setLayoutManager( new LinearLayoutManager( Aa.this ) );
        adapt.notifyDataSetChanged();
        button_1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExcelFile(Aa.this,"hisab.xls");
            }
        } );

    }
    public static String qk1()
    {
        return sti;
    }
    private void load() {


        Cursor cursor = dbh.getAllData2();
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
    private boolean saveExcelFile(Context context, String fileName) {

        // check if available and not read only
      //  MainActivity men = new MainActivity();
        // ArrayList<String> students = new ArrayList<>(  );
        // ArrayList<Student> myl =(ArrayList<Student>)getIntent().getSerializableExtra("key");
        String id,name,surname,date;
        Dbhelper dbh;
        dbh = new Dbhelper( Aa.this );
        Cursor cursor = dbh.getAllData2();




        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.w("FileUtils", "Storage not available or read only");
            return false;
        }

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;
        Cell c1 = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor( HSSFColor.LIME.index);
        cs.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("hisab");

        Row row1 = sheet1.createRow( 0 );

        // Generate column heading
        // s
        // if(cursor.moveToFirst()) {
        //  for (int i = 0; i < cursor.getCount(); i++) {


        while (cursor.moveToNext()) {
            int k = cursor.getPosition() + 1;
            c = row1.createCell( 0 );
            c.setCellValue( "Description/Purpose" );
            Row row = sheet1.createRow( k );
            surname = cursor.getString( 2 );
            c = row.createCell( 0 );
            c.setCellValue( surname );
            c.setCellStyle( cs );

            c = row1.createCell( 1 );
            c.setCellValue("Amount");
            //Row row2 = sheet1.createRow( k );
            name = cursor.getString( 1 );
            c = row.createCell( 1 );
            c.setCellValue( name );
            c.setCellStyle( cs );

            c = row1.createCell( 2 );
            c.setCellValue("Date");
            //Row row2 = sheet1.createRow( k );
            date = cursor.getString( 3 );
            c = row.createCell( 2 );
            c.setCellValue( date );
            c.setCellStyle( cs );
        }

        //  }

        //  }

          /* while (cursor.moveToNext()) {
               int k = cursor.getPosition() + 1;
               c1 = row1.createCell( 1 );
               c1.setCellValue( "name" );
               Row row = sheet1.createRow( k );
               name = cursor.getString( 1 );
               c1 = row.createCell( 1 );
               c1.setCellValue( name );
               c1.setCellStyle( cs );
               Log.w( "ss", "id" );

           } */




        /*

        c = row.createCell(1);
        c.setCellValue("Quantity");
        c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("Price");
        c.setCellStyle(cs);

        c = row1.createCell(2);
        c.setCellValue("Price");
        c.setCellStyle(cs); */

        sheet1.setColumnWidth(0, (15 * 500));
        sheet1.setColumnWidth(1, (15 * 500));
        sheet1.setColumnWidth(2, (15 * 500));

        // Create a path where we will place our List of objects on external storage

        File file = new File(context.getExternalFilesDir( null ), fileName);
        FileOutputStream os = null;




        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Writing file" + file);
            success = true;



        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }

          //  File pk = new File( context.getExternalFilesDir( null),fileName);
                     String l = file.getPath();
             // String ul ="file:///storage/emulated/0/Android/data/com.fitness.hisabh/file/myExcel.xls";
            //Log.w( "qw","o"+pk.getAbsolutePath() );
            Uri ck = Uri.parse(l);
           // Uri cr = Uri.parse( String.valueOf( os ) );

            Intent sd = new Intent(Intent.ACTION_SEND);
             sd.setType("application/vnd.ms-excel*");
            //sd.setType( "*/*" );
            // sd.setType("application/vnd.ms-excel");


            sd.addFlags( Intent.FLAG_GRANT_READ_URI_PERMISSION );
            sd.addFlags( Intent.FLAG_GRANT_WRITE_URI_PERMISSION );
            sd.putExtra( Intent.EXTRA_STREAM,ck);
            sd.setPackage("com.whatsapp");

            startActivity(sd);

        }







        return success;



    }


    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
