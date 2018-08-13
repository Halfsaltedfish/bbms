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

public class ChangeLine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_line);

        Button deleteLine = (Button)findViewById(R.id.deleteLine);
        Button changeSite = (Button)findViewById(R.id.changeLine);
        final TextView textView = (TextView) findViewById(R.id.train_bid);
        final EditText editText = (EditText)findViewById(R.id.change_site);
        final SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
        Intent intent = getIntent();
        final String str = intent.getStringExtra("changeLine");
        @SuppressLint("Recycle") Cursor cursor= db.rawQuery("select depot from bus where bid = ?", new String[]{str});
        while (cursor.moveToNext()) {
            textView.setText(str);
            editText.setText(cursor.getString(0));
        }
        deleteLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("update bus set depot='' where bid = ?", new String[]{str});
                Toast.makeText(ChangeLine.this, "删除成功", Toast.LENGTH_SHORT).show();
                ChangeLine.this.finish();
            }
        });
        changeSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("update bus set depot=? where bid = ?", new String[]{editText.getText().toString(),str});
                Toast.makeText(ChangeLine.this, "修改成功", Toast.LENGTH_SHORT).show();
                ChangeLine.this.finish();
            }
        });
    }
}
