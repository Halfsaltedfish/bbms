package xin.developer97.xianyu.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewTrain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_train);

        final EditText newBid = (EditText)findViewById(R.id.newBid);
        final EditText newPrice = (EditText)findViewById(R.id.newPrice);
        final EditText newTime = (EditText)findViewById(R.id.newTime);
        final EditText newdepot = (EditText)findViewById(R.id.newdepot);
        Button newTrain = (Button)findViewById(R.id.newTrain);

        newTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
                @SuppressLint("Recycle") Cursor cursor= db.rawQuery("select bid from bus where bid = ?", new String[]{newBid.getText().toString()});
                if (cursor.getCount() != 0){
                    Toast.makeText(NewTrain.this, "已存在该线路", Toast.LENGTH_SHORT).show();
                }else {
                    db.execSQL("INSERT INTO bus VALUES (?,?,?,?)", new String[]{newBid.getText().toString(), newPrice.getText().toString(),newTime.getText().toString(),newdepot.getText().toString()});
                    Toast.makeText(NewTrain.this, "添加成功", Toast.LENGTH_SHORT).show();
                    NewTrain.this.finish();
                }
            }
        });
    }
}
