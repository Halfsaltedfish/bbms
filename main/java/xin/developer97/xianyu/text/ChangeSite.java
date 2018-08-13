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
import android.widget.Toast;

public class ChangeSite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_site);
        Button deleteSite = (Button)findViewById(R.id.deleteSite);
        Button changeSite = (Button)findViewById(R.id.changeSite);
        final EditText editText = (EditText)findViewById(R.id.change_depot);
        final EditText editText2 = (EditText)findViewById(R.id.change_mark);
        final SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
        Intent intent = getIntent();
        final String str = intent.getStringExtra("changeSite");
        @SuppressLint("Recycle") Cursor cursor= db.rawQuery("select mark from station where depot = ?", new String[]{str});
        while (cursor.moveToNext()) {
            editText.setText(str);
            editText2.setText(cursor.getString(0));
        }
        deleteSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("delete from station where depot = ?", new String[]{str});
                Toast.makeText(ChangeSite.this, "删除成功", Toast.LENGTH_SHORT).show();
                ChangeSite.this.finish();
            }
        });
        changeSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("update station set depot=?,mark=? where depot = ?", new String[]{editText.getText().toString(),editText2.getText().toString(),str});
                Toast.makeText(ChangeSite.this, "修改成功", Toast.LENGTH_SHORT).show();
                ChangeSite.this.finish();
            }
        });
    }
}
