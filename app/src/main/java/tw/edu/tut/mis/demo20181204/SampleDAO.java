package tw.edu.tut.mis.demo20181204;

import android.content.ContentValues;
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


    //加入一筆資料到資料表的動作
    //insert into sample (欄位) value (值)
    public void add(Sample data){
        ContentValues cv = new ContentValues();
        cv.put( "name",    data.getName()    );
        cv.put( "tel",     data.getTEL()     );
        cv.put( "address", data.getAddress() );

        long id = mDB.insert("sample", null, cv);

        data.setID(id); //更新資料物件中的 ID
    }


    //更動資料表中某個 _id 的內容
    //update sample set 欄位=值, 欄位=值, 欄位=值.... where 條件
    public boolean update(Sample data){
        ContentValues cv = new ContentValues();
        cv.put( "name",    data.getName()    );
        cv.put( "tel",     data.getTEL()     );
        cv.put( "address", data.getAddress() );
        String where = "_id="+data.getID();

        int row_affect = mDB.update("sample", cv, where, null );
        return  row_affect > 0;
    }


    //刪除資料表中某個 _id 的內容
    //delete from sample where 條件
    public boolean delete(long id){
        String where = "_id="+id;
        int row_affect = mDB.delete("sample", where, null );
        return  row_affect > 0;
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
