package xin.developer97.xianyu.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class trainMaintenance extends AppCompatActivity {

    private List<Map<String, Object>> List = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_maintenance);
        ListView trainManagement = (ListView) findViewById(R.id.trainManagement_list);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getDate(), R.layout.trainmanagement_list,
                new String[]{"train_id", "train_price", "train_time"}, new int[]{
                R.id.train_id, R.id.train_price, R.id.train_time});
        trainManagement.setAdapter(simpleAdapter);
        trainManagement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(trainMaintenance.this, ChangeTrain.class);
                intent.putExtra("changeTrain",List.get(position).get("train_id").toString());
                startActivity(intent);
            }
        });
    }

    private List<Map<String, Object>> getDate() {
        SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select bid,price,time from bus",null);
        while (cursor.moveToNext()){
            //获得车次
            String id = cursor.getString(0);
            //获得价格
            String price = cursor.getString(1);
            //获得营业时间
            String time = cursor.getString(2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("train_id",id);
            map.put("train_price",price);
            map.put("train_time",time);
            List.add(map);
        }
        return List;
    }
}

