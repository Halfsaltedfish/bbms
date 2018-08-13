package xin.developer97.xianyu.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Forget extends AppCompatActivity {
    EditText ed_findPassword;
    TextView view_findPassword;
    Button button_findPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ed_findPassword = (EditText)findViewById(R.id.ed_findPassword);
        view_findPassword = (TextView)findViewById(R.id.view_findPassword);
        button_findPassword = (Button)findViewById(R.id.button_findPassword);
        button_findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPassword();
            }
        });
    }
    private void findPassword(){
        SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
        //查询获得游标
        @SuppressLint("Recycle") Cursor cursor = db.query("controller", null, null, null, null, null, null);
        //判断游标是否为空
        if (cursor.moveToFirst()) {//遍历游标
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.move(i);
                //获得用户名
                String username = cursor.getString(0);
                //获得密码
                String password = cursor.getString(1);
                if (ed_findPassword.getText().toString().equals(username)) {
                    view_findPassword.setText("您的密码是："+password);
                    break;
                } else {
                    if (cursor.isLast())
                        Toast.makeText(Forget.this, "不存在该用户", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}
