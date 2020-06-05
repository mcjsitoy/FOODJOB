package com.example.order;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "myfooddb.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase mDataBase;
    private Context mContext = null;
    private String table_name = "Food";
    private String colid = "id";
    private String colname = "name";
    private String colprice = "price";
    private String colcat = "category";





    Cursor curs;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

   /* public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }*/

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("FAIL");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        String outputfname = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outputfname);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {


        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    private boolean checkDataBase() {
        SQLiteDatabase tempdbs = null;
        try {
            String path = DB_PATH + DB_NAME;
        } catch (Exception e) {
            throw new Error("THERE IS NO DBASE");
        }
        if (tempdbs != null)
            tempdbs.close();
        return tempdbs != null ? true : false;
        /*File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();*/
    }

    public void createDatabase() {
        boolean doesdbexist = checkDataBase();
        if (doesdbexist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (Exception e) {

            }
        }
    }

    public List<model> getburg() {
        ArrayList<model> temp = new ArrayList<model>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            curs = mDataBase.rawQuery("SELECT * FROM " + table_name, null);
            if (curs != null) {
                curs.moveToFirst();

                do {
                    model modelb = new model
                            (curs.getInt(curs.getColumnIndex("id")),
                                    curs.getString(curs.getColumnIndex("name")),
                                    curs.getInt(curs.getColumnIndex("price")),
                                    curs.getString(curs.getColumnIndex("category")));
                    temp.add(modelb);


                } while (curs.moveToNext());
                curs.close();
            }

        }
        catch (Exception e){

    }
        db.close();
        return temp;


}

public Cursor fetchburger(){
        SQLiteDatabase datab = this.getWritableDatabase();

        String rq = "SELECT * FROM " + table_name + " WHERE " + " category = " + "'Burger'";



    Cursor data = datab.rawQuery(rq,null);
        return data;
}

    public Cursor fetchbeverage(){
        SQLiteDatabase datab = this.getWritableDatabase();

        String rq = "SELECT * FROM " + table_name + " WHERE " + " category = " + "'Beverages'";



        Cursor data = datab.rawQuery(rq,null);
        return data;
    }
    public Cursor fetchcombo(){
        SQLiteDatabase datab = this.getWritableDatabase();

        String rq = "SELECT * FROM " + table_name + " WHERE " + " category = " + "'Combo Meal'";



        Cursor data = datab.rawQuery(rq,null);
        return data;
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* if (newVersion > oldVersion)
            mNeedUpdate = true;*/
    }
    }



