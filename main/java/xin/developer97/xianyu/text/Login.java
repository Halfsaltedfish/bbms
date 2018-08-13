package xin.developer97.xianyu.text;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    TextView siteManagement;
    TextView lineMaintenance;
    TextView trainMaintenance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        siteManagement = (TextView)findViewById(R.id.SiteManagement);
        lineMaintenance = (TextView)findViewById(R.id.LineMaintenance);
        trainMaintenance = (TextView)findViewById(R.id.TrainMaintenance);
        TextView NewTrain = (TextView)findViewById(R.id.NewTrain);
        TextView NewStation = (TextView)findViewById(R.id.NewStation);
        siteManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, siteManagement.class);
                startActivity(intent);
            }
        });
        lineMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, lineMaintenance.class);
                startActivity(intent);
            }
        });
        trainMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, trainMaintenance.class);
                startActivity(intent);
            }
        });
        NewStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NewStation.class);
                startActivity(intent);
            }
        });
        NewTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NewTrain.class);
                startActivity(intent);
            }
        });
    }
}
