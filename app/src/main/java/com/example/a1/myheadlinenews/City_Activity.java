package com.example.a1.myheadlinenews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zaaach.citypicker.CityPickerActivity;

public class City_Activity extends AppCompatActivity {
    private static final int REQUEST_CODE_PICK_CITY = 233;
    private TextView resultTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_);
        resultTV = (TextView) findViewById(R.id.tv_result);
        findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(City_Activity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                resultTV.setText("当前选择：" + city);
            }
        }
    }
}
