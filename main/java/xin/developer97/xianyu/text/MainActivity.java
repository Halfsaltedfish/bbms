package xin.developer97.xianyu.text;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton1 = (ImageButton)findViewById(R.id.imageButton);
        imageButton2 = (ImageButton)findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton)findViewById(R.id.imageButton3);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        final RadioButton radioButton1 = (RadioButton)findViewById(R.id.train_select);
        final RadioButton radioButton2 = (RadioButton)findViewById(R.id.station_select);
        final EditText matter = (EditText)findViewById(R.id.matter);
        Button toQuery = (Button)findViewById(R.id.toQuery);
        radioButton1.setChecked(true);
        //管理员入口
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity(intent);
            }
        });
        //服务公告
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Sign.class);
                startActivity(intent2);
            }
        });
        //公交介绍
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Introduce.class);
                startActivity(intent3);
            }
        });
        MyDBOpenHelper myDBOpenHelper = new MyDBOpenHelper(MainActivity.this);
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = myDBOpenHelper.getWritableDatabase();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButton1.isChecked()){
                    matter.setHint("输入车次,如“1路”");
                }
                if (radioButton2.isChecked()){
                    matter.setHint("输入站点,如“太原理工大学”");
                }
            }
        });
        toQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButton1.isChecked()){
                    Intent intent4 = new Intent(MainActivity.this, TrainMatter.class);
                    intent4.putExtra("train",matter.getText().toString());
                    startActivity(intent4);
                }
                if (radioButton2.isChecked()){
                    Intent intent5 = new Intent(MainActivity.this, StationMatter.class);
                    intent5.putExtra("station_matter",matter.getText().toString());
                    startActivity(intent5);
                }
            }
        });
    }
}
