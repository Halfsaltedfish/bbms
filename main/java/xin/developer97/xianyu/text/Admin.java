package xin.developer97.xianyu.text;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Admin extends Activity {
    TextView toEnrol;
    TextView toForget;
    Button toLogin;
    EditText name;
    EditText word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        toEnrol = (TextView)findViewById(R.id.toEnrol);
        toForget = (TextView)findViewById(R.id.toForget);
        toLogin = (Button)findViewById(R.id.toLogin);
        name = (EditText)findViewById(R.id.name);
        word = (EditText)findViewById(R.id.word);
        toEnrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, EnrolActivity.class);
                startActivity(intent);
            }
        });
        toForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Forget.class);
                startActivity(intent);
            }
        });
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){
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
                if (name.getText().toString().equals(username) && word.getText().toString().equals(password)) {
                    Intent intentToLogin = new Intent(Admin.this, Login.class);
                    startActivity(intentToLogin);
                    break;
                } else {
                    if (cursor.isLast())
                        Toast.makeText(Admin.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}


