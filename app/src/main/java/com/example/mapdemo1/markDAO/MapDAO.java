package com.example.mapdemo1.markDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mapdemo1.database.DatabaseMap;
import com.example.mapdemo1.model.Map;

import java.util.ArrayList;
import java.util.List;

import static com.example.mapdemo1.database.DatabaseMap.C_LAT;
import static com.example.mapdemo1.database.DatabaseMap.C_LNG;
import static com.example.mapdemo1.database.DatabaseMap.C_NAMELOCATION;
import static com.example.mapdemo1.database.DatabaseMap.T_GMAP;

public class MapDAO {

    private DatabaseMap databaseAssigment;

    public MapDAO(Context context) {
        databaseAssigment = new DatabaseMap(context);
    }

    //------------------------------------------------ THÊM MARK -------------------------------------------------------


    public long insertMark(Map map) {
        SQLiteDatabase sqLiteDatabase = databaseAssigment.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(C_NAMELOCATION, map.name);
        contentValues.put(C_LAT, map.lat);
        contentValues.put(C_LNG, map.lng);

        long result = sqLiteDatabase.insert(T_GMAP, null, contentValues);

        sqLiteDatabase.close();

        return result;
    }


    //------------------------------------------------ LẤY MARK -------------------------------------------------------
    public List<Map> getAllMark() {

        List<Map> mapList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseAssigment.getReadableDatabase();

        String SELECT = "SELECT * FROM " + T_GMAP;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                Map map = new Map();
                map.name = cursor.getString(0);
                map.lat = Double.parseDouble(cursor.getString(1));
                map.lng = Double.parseDouble(cursor.getString(2));
                mapList.add(map);

            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return mapList;
    }

    //------------------------------------------------ LẤY MARK -------------------------------------------------------
    public List<Map> getNameMark(String name) {

        List<Map> mapList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseAssigment.getReadableDatabase();

        String SELECT = "SELECT * FROM " + T_GMAP + " WHERE " + C_NAMELOCATION + " = " + name;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                Map map = new Map();
                map.name = cursor.getString(0);
                map.lat = Double.parseDouble(cursor.getString(1));
                map.lng = Double.parseDouble(cursor.getString(2));
                mapList.add(map);

            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return mapList;
    }


    //------------------------------------------------ LẤY MARK -------------------------------------------------------
    public long updateMark(Map map) {
        SQLiteDatabase sqLiteDatabase = databaseAssigment.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(C_NAMELOCATION, map.name);
        contentValues.put(C_LAT, map.lat);
        contentValues.put(C_LNG, map.lng);

        long result = sqLiteDatabase.update(T_GMAP, contentValues, C_NAMELOCATION + "=?", new String[]{String.valueOf(map.name)});

        sqLiteDatabase.close();

        return result;

    }


}
