package xin.developer97.xianyu.text;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StationMatter extends AppCompatActivity {

    private java.util.List<Map<String, Object>> List = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_matter);


        ListView station_result = (ListView) findViewById(R.id.station_result);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getDate(), R.layout.sitemanagement_list,
                new String[]{"site_name", "site_mark"}, new int[]{
                R.id.site_name, R.id.site_mark});
        station_result.setAdapter(simpleAdapter);
    }

    private List<Map<String, Object>> getDate() {
        int n = 1;
        Intent intent5 = getIntent();
        String string = intent5.getStringExtra("station_matter");
        SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select bid from bus where depot like ?", new String[]{"%"+string+"%"});
        while (cursor.moveToNext()){
            //获得站名
            String name = String.valueOf(n);
            //获得备注
            String remark = cursor.getString(0);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("site_name",name);
            map.put("site_mark",remark);
            List.add(map);
            n++;
        }
        if (cursor.getCount() == 0){
            Toast.makeText(StationMatter.this, "未查询到该站点到信息", Toast.LENGTH_SHORT).show();
            StationMatter.this.finish();
        }
        return List;
    }
}
