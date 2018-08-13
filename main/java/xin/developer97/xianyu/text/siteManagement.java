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

public class siteManagement extends AppCompatActivity {

    private List<Map<String, Object>> List = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_management);
        ListView siteManagement = (ListView) findViewById(R.id.siteManagement_list);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getDate(), R.layout.sitemanagement_list,
                new String[]{"site_name", "site_mark"}, new int[]{
                R.id.site_name, R.id.site_mark});
        siteManagement.setAdapter(simpleAdapter);
        siteManagement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(siteManagement.this, ChangeSite.class);
                intent.putExtra("changeSite",List.get(position).get("site_name").toString());
                startActivity(intent);
            }
        });
    }

    private List<Map<String, Object>> getDate() {
        SQLiteDatabase db = openOrCreateDatabase("bus.db", Context.MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from station",null);
        while (cursor.moveToNext()){
            //获得站名
            String name = cursor.getString(0);
            //获得备注
            String remark = cursor.getString(1);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("site_name",name);
            map.put("site_mark",remark);
            List.add(map);
            }
            return List;
    }
}

