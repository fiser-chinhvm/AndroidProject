package com.example.hoang.bookmovietickets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang on 11/14/2015.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String TAG = "MOVIE";

    //movie table
    public static final String TABLE_NAME = "movie";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IS_BOOKED = "booked";
    public static final String COLUMN_ID = "id";

    public static final String DATABASE_NAME = "bookticket.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_MOVIE = "create table " + TABLE_NAME + "( "
                                                    + COLUMN_ID + " integer primary key autoincrement, "
                                                    + COLUMN_TITLE + " text, "
                                                    + COLUMN_DESCRIPTION + " text, "
                                                    + COLUMN_IMAGE + " text,"
                                                    + COLUMN_IS_BOOKED + " boolean" + ")";
    private static final String SQL_DELETE_MOVIE = "drop table if exists " + TABLE_NAME;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MOVIE);
        onCreate(db);
    }

    private MovieModel getModel(Cursor c){
        MovieModel model = new MovieModel();
        model.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        model.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
        model.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
        model.setImage(c.getString(c.getColumnIndex(COLUMN_IMAGE)));
        model.setBooked(c.getInt(c.getColumnIndex(COLUMN_IS_BOOKED)) == 0 ? false : true);
        return model;
    }

    private ContentValues getContent(MovieModel model){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, model.getTitle());
        values.put(COLUMN_DESCRIPTION, model.getDescription());
        values.put(COLUMN_IMAGE, model.getImage());
        values.put(COLUMN_IS_BOOKED, model.isBooked());

        return values;
    }

    public void createMovie(MovieModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getContent(model);
        long n = database.insert(TABLE_NAME, null, values);
        if(n < 0){
            Log.d(TAG, "Create Movie failed");
        }else {
            Log.d(TAG, "Create Movie successful");
        }
    }

    public void updateMovie(MovieModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getContent(model);
        long n = database.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(model.getId())});
        if(n < 0){
            Log.d(TAG, "Updated Movie failed");
        }else {
            Log.d(TAG, "Updated Movie successful");
        }
    }

    public MovieModel getMovie(int id){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + COLUMN_ID + " = " + id;
        Cursor c = database.rawQuery(query, null);
        if (c.moveToNext()){
            return getModel(c);
        }
        return null;
    }

    public List<MovieModel> getListMovie(){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor c = database.rawQuery(query, null);
        List<MovieModel> list = new ArrayList<MovieModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            list.add(getModel(c));
            c.moveToNext();
        }
        database.close();
        return list;
    }

    public List<MovieModel> getListBookedMovie(int isBooked){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + COLUMN_IS_BOOKED + " = " + isBooked;
        Cursor c = database.rawQuery(query, null);
        List<MovieModel> list = new ArrayList<MovieModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            list.add(getModel(c));
            c.moveToNext();
        }
        database.close();
        return list;
    }

}
