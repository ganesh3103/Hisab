package com.fitness.hisabh;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.record.formula.functions.Column;
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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ex extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    Button tw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);

        tw = (Button)findViewById( R.id.tk );
        View writeExcelButton = findViewById(R.id.b6);
        writeExcelButton.setOnClickListener(this);




    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.b6:
                saveExcelFile(this,"myExcel.xls");
                break;
            case R.id.tk:
                readExcelFile(this,"myExcel.xls");
                break;

        }
    }


    private boolean saveExcelFile(Context context, String fileName) {

        // check if available and not read only
        MainActivity men = new MainActivity();
       // ArrayList<String> students = new ArrayList<>(  );
       // ArrayList<Student> myl =(ArrayList<Student>)getIntent().getSerializableExtra("key");
        String id,name,surname,date;
        Dbhelper dbh;
        dbh = new Dbhelper( Ex.this );
        Cursor cursor = dbh.getAllData();




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
        cs.setFillForegroundColor(HSSFColor.LIME.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("my");

        Row row1 = sheet1.createRow( 0 );

        // Generate column heading
        // s
       // if(cursor.moveToFirst()) {
          //  for (int i = 0; i < cursor.getCount(); i++) {


           while (cursor.moveToNext()) {
               int k = cursor.getPosition() + 1;
               c = row1.createCell( 0 );
               c.setCellValue( "id" );
               Row row = sheet1.createRow( k );
               id = cursor.getString( 0 );
               c = row.createCell( 0 );
               c.setCellValue( id );
               c.setCellStyle( cs );

                    c = row1.createCell( 1 );
                    c.setCellValue("name");
                    //Row row2 = sheet1.createRow( k );
                    name = cursor.getString( 1 );
                    c = row.createCell( 1 );
                    c.setCellValue( name );
                    c.setCellStyle( cs );

               c = row1.createCell( 2 );
               c.setCellValue("DATE");
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
        File file = new File(context.getExternalFilesDir(null), fileName);
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
        }

        return success;
    }

    private void readExcelFile(Context context, String filename) {

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
        {
            Log.w("FileUtils", "Storage not available or read only");
            return;
        }

        try{
            // Creating Input Stream
            File file = new File(context.getExternalFilesDir(null), filename);
            FileInputStream myInput = new FileInputStream(file);

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);


            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Uri path = FileProvider.getUriForFile(context, "com.fitness.m1.fileprovider", file);
           // Uri t =  Uri.parse( mySheet );
            Intent sd = new Intent( Intent.ACTION_SEND );
             sd.setType( "xls*/*" );
           // sd.setDataAndType(path, "xls/vnd.ms-excel");
            sd.putExtra( Intent.EXTRA_SUBJECT,"Data" );
            sd.addFlags( Intent.FLAG_GRANT_READ_URI_PERMISSION );
            sd.putExtra( Intent.EXTRA_FROM_STORAGE,path );
            startActivity(Intent.createChooser( sd,"send" ));


            /** We now need something to iterate through the cells.**/
            Iterator<Row> rowIter = mySheet.rowIterator();

            while(rowIter.hasNext()){
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                while(cellIter.hasNext()){
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    Log.w("FileUtils", "Cell Value: " +  myCell.toString());
                    Toast.makeText(context, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){e.printStackTrace(); }


        return;

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
