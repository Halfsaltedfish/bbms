package xin.developer97.xianyu.text;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnrolActivity extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    EditText ed3;
    Button buttonToEnrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrol);
        ed1 = (EditText)findViewById(R.id.ed1);
        ed2 = (EditText)findViewById(R.id.ed2);
        ed3 = (EditText)findViewById(R.id.ed3);
        buttonToEnrol = (Button)findViewById(R.id.buttonToEnrol);
        buttonToEnrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed2.getText().toString().equals(ed3.getText().toString())){
                    Usear p = new Usear();
                    p.userName  = ed1.getText().toString();
                    p.password = ed2.getText().toString();
                    SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
                    db.execSQL("INSERT INTO controller VALUES (?,?)", new String[]{p.userName, p.password});
                    Toast.makeText(EnrolActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(EnrolActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
