package tw.edu.tut.mis.demo20181204;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddEditActivity extends AppCompatActivity {

    int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        Bundle bd = getIntent().getExtras();
        mMode = bd.getInt("Mode", 1);

        if (mMode==1) { //新增模式
            ((TextView)findViewById(R.id.textView4)).setText("新增");
        } else { //編輯模式
            ((TextView)findViewById(R.id.textView4)).setText("編輯");
            //????
            //編輯的原來資料
        }

        //點選 OK 的按鈕
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bd = new Bundle();
                bd.putString("name",    ((EditText)findViewById(R.id.editText1)).getText().toString());
                bd.putString("tel",     ((EditText)findViewById(R.id.editText2)).getText().toString());
                bd.putString("address", ((EditText)findViewById(R.id.editText3)).getText().toString());
                if (mMode!=1) { //新增沒有其他要傳回的，編輯可能有id
                    //????
                }
                Intent it = new Intent();
                it.putExtras(bd);
                setResult(Activity.RESULT_OK, it); //暫時
                AddEditActivity.this.finish();
            }
        });

        //點選 CANCEL 的按鈕
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                AddEditActivity.this.finish();
            }
        });

    }
}
