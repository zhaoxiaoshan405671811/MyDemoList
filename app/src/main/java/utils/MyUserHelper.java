package utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;


public class MyUserHelper extends SQLiteOpenHelper{

    public MyUserHelper(Context context) {
        super(context, "userdb", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table user(id integer primary key autoincrement,name text,phone text,pic text,password text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
