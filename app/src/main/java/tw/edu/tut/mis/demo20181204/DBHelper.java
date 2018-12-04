package tw.edu.tut.mis.demo20181204;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "database.db";
    private static final int DB_VERSION = 2;  //若資料庫結構有改變，要更動版本號
                                              //記得加上對應的SQL在下面的onUpgrade, 通常是 alter 指令
    private static SQLiteDatabase mDB;

    public static SQLiteDatabase getDB(Context ctx){
        if( mDB==null || !mDB.isOpen() ){
            mDB = new DBHelper(ctx, DB_NAME, null, DB_VERSION  ).getWritableDatabase();
        }
        return mDB;
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //當資料庫檔案第一次被產生時，執行這個...
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( SampleDAO.CREATE_SQL );
        db.execSQL( SampleDAO.INIT_EXAMPLE_DATA );
    }

    //當前面指定的版本有更改時，執行這個...
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
