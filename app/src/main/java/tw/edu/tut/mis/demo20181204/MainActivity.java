package tw.edu.tut.mis.demo20181204;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_ADD = 1001;
    private final int REQUEST_CODE_EDIT = 1002;

    SampleDAO mSampleDAO;
    SampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSampleDAO = new SampleDAO(this);
        List<Sample> list = mSampleDAO.getAll();

        mAdapter = new SampleAdapter(this, R.layout.sample_list_item, list);

        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(mAdapter);

        // 「新增」按鈕
        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bd = new Bundle();
                //bd.putInt(????);

                Intent it = new Intent(MainActivity.this, AddEditActivity.class);
                it.putExtras(bd);

                startActivityForResult(it, REQUEST_CODE_ADD );
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //來自於新增頁面
        if (requestCode==REQUEST_CODE_ADD) {
            if (resultCode==Activity.RESULT_OK) { //離開頁面時，按的是 OK/Save/Confirm
                //處理新增的動作
                //....
                //...
            }
        }
        //來自於編輯的頁面
        if (requestCode==REQUEST_CODE_EDIT) {
            if (resultCode==Activity.RESULT_OK) { //離開頁面時，按的是 OK/Save/Confirm
                //處理修改的動作
                //....
                //...
            }
        }
    }



    class SampleAdapter extends ArrayAdapter<Sample> {
        public SampleAdapter(Context context, int resource, List<Sample> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if( convertView==null ){
                convertView = LayoutInflater.from(getContext())
                        .inflate( R.layout.sample_list_item, parent, false );
            }

            Sample data = getItem(position);

            TextView tv1, tv2, tv3;
            tv1 = convertView.findViewById(R.id.textView);
            tv2 = convertView.findViewById(R.id.textView2);
            tv3 = convertView.findViewById(R.id.textView3);
            tv1.setText( data.getName()    );
            tv2.setText( data.getTEL()     );
            tv3.setText( data.getAddress() );

            return convertView;
        }
    }

}
