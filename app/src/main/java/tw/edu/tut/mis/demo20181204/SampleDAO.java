package tw.edu.tut.mis.demo20181204;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SampleDAO {
    //建立資料表的SQL
    public static final String CREATE_SQL =
            "create table sample("
            +"    _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
            +"    name TEXT,"
            +"    tel TEXT,"
            +"    address TEXT"
            +")";
    //新增測試資料的SQL
    public static final String INIT_EXAMPLE_DATA =
            "insert into sample(name,tel,address) values"
            +"(\"張三\", \"062532106\", \"台南市永康區中正路529號\"),"
            +"(\"資管系\", \"062532106\", \"台南市永康區中正路529號\"),"
            +"(\"王五\", \"062532106\", \"台南市永康區中正路529號\")";

    private SQLiteDatabase mDB;

    //建構式
    public SampleDAO(Context ctx){
        mDB = DBHelper.getDB(ctx);
    }


    // 取出sample資料表所有資料
    // 類似 select * from sample
    public List<Sample> getAll(){
        //Cursor rr = mDB.query("sample", null, null, null, null, null, null, null);
                                            //後面有7個null
        Cursor rr = mDB.rawQuery("select * from sample", null);
        List<Sample> list = new ArrayList<>();

        while( rr.moveToNext() ){
            Sample r = new Sample();
            r.setID( rr.getLong(0) );          //_id
            r.setName( rr.getString(1) );      //name
            r.setTEL( rr.getString(2) );       //tel
            r.setAddress( rr.getString(3) );   //address
            list.add(r);
        }

        rr.close();
        return list;
    }


}
