package xin.developer97.xianyu.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewStation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_station);
        final EditText newDepot = (EditText)findViewById(R.id.newDepot);
        final EditText newMark = (EditText)findViewById(R.id.newMark);
        Button newSite = (Button)findViewById(R.id.newSite);
        newSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
                @SuppressLint("Recycle") Cursor cursor= db.rawQuery("select depot from station where depot = ?", new String[]{newDepot.getText().toString()});
                if (cursor.getCount() != 0){
                    Toast.makeText(NewStation.this, "已存在该站点", Toast.LENGTH_SHORT).show();
                }else {
                    db.execSQL("INSERT INTO station VALUES (?,?)", new String[]{newDepot.getText().toString(), newMark.getText().toString()});
                    Toast.makeText(NewStation.this, "添加成功", Toast.LENGTH_SHORT).show();
                    NewStation.this.finish();
                }
            }
        });
    }
}
