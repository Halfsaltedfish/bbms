package xin.developer97.xianyu.text;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xianyu on 2018/7/7.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(Context context) {
        super(context, "bus.db", null, 1);
    }
    @Override
    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE controller(username VARCHAR(20) PRIMARY KEY,password VACHAR(12))");
        db.execSQL("CREATE TABLE bus(bid VACHAR(10) PRIMARY KEY ,price VACHAR(3),time VACHAR(12),depot VACHAR(20))");
        db.execSQL("CREATE TABLE station(depot VACHAR(20) PRIMARY KEY ,mark VARCHAR(100))");
        db.execSQL("INSERT INTO controller VALUES('admin','admin')");
        db.execSQL("INSERT INTO station VALUES('1站','')");
        db.execSQL("INSERT INTO station VALUES('2站','')");
        db.execSQL("INSERT INTO station VALUES('太原理工大学','')");
        db.execSQL("INSERT INTO bus VALUES('1路','2元','6:00-19:00','1站，2站，太原理工大学')");
        db.execSQL("INSERT INTO bus VALUES('2路','1元','6:00-19:00','1站，太原理工大学')");

    }
    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }}
