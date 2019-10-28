package com.example.mapdemo1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseMap extends SQLiteOpenHelper {

    public DatabaseMap(Context context) {
        super(context, "SQLiteVer1.db", null, 1);
    }

    public final static String T_GMAP = "gmapTable";

    public final static String C_NAMELOCATION = "namelocation";
    public final static String C_LAT = "lat";
    public final static String C_LNG = "lng";

    public final static String CREATE_GMAP = "CREATE TABLE gmapTable (namelocation NVARCHAR PRIMARY KEY, lat DOUBLE, lng DOUBLE)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GMAP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
