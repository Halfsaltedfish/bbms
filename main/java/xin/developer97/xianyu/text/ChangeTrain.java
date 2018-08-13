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

public class ChangeTrain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_train);

        Button deleteSite = (Button)findViewById(R.id.deleteTrain);
        Button changeSite = (Button)findViewById(R.id.changeTrain);
        final EditText editText = (EditText)findViewById(R.id.change_bid);
        final EditText editText2 = (EditText)findViewById(R.id.change_price);
        final EditText editText3 = (EditText)findViewById(R.id.change_time);
        final SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
        Intent intent = getIntent();
        final String str = intent.getStringExtra("changeTrain");
        @SuppressLint("Recycle") Cursor cursor= db.rawQuery("select price,time from bus where bid = ?", new String[]{str});
        while (cursor.moveToNext()) {
            editText.setText(str);
            editText2.setText(cursor.getString(0));
            editText3.setText(cursor.getString(1));
        }
        deleteSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("delete from bus where bid = ?", new String[]{str});
                Toast.makeText(ChangeTrain.this, "删除成功", Toast.LENGTH_SHORT).show();
                ChangeTrain.this.finish();
            }
        });
        changeSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("update bus set bid=?,price=?,time=? where bid = ?", new String[]{editText.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),str});
                Toast.makeText(ChangeTrain.this, "修改成功", Toast.LENGTH_SHORT).show();
                ChangeTrain.this.finish();
            }
        });
    }
}
