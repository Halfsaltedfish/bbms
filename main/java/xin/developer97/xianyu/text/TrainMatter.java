package xin.developer97.xianyu.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TrainMatter extends AppCompatActivity {

    private List<Map<String, Object>> List = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_matter);
        ListView train_result = (ListView) findViewById(R.id.train_result);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getDate(), R.layout.sitemanagement_list,
                new String[]{"site_name", "site_mark"}, new int[]{
                R.id.site_name, R.id.site_mark});
        train_result.setAdapter(simpleAdapter);
    }

    private List<Map<String, Object>> getDate() {
        SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
        Intent intent = getIntent();
        String string = intent.getStringExtra("train");
        @SuppressLint("Recycle") Cursor cursor= db.rawQuery("select price,time,depot from bus where bid = ?", new String[]{string});
        while (cursor.moveToNext()) {
            TextView this_price = (TextView)findViewById(R.id.this_price);
            this_price.setText(cursor.getString(0));
            TextView this_time = (TextView)findViewById(R.id.this_time);
            this_time.setText(cursor.getString(1));
            String res = cursor.getString(2);
            String[] strs = res.split("，");
            for(int i=0,len=strs.length;i<len;){
                String remark = strs[i];
                i++;
                String name = "第"+i+"站";
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("site_name",name);
                map.put("site_mark",remark);
                List.add(map);
            }
        }
        if (cursor.getCount() == 0){
            Toast.makeText(TrainMatter.this, "未查询到该车次到信息", Toast.LENGTH_SHORT).show();
            TrainMatter.this.finish();
        }

        return List;
    }
}
