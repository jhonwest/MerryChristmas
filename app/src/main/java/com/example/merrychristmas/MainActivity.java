package com.example.merrychristmas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button input_btn;
    private int[] nubs;
    private String[] datas;
    private EditText count_et;
    private StringBuffer sb;
    private ListView listView;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
                function();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.item, datas);
                listView.setAdapter(adapter);
            }
        });

    }

    //初始化界面
    private void initView() {
        input_btn = (Button) findViewById(R.id.input_btn);
        count_et = (EditText) findViewById(R.id.count);
        listView = (ListView) findViewById(R.id.listView);
    }

    //初始化数据
    private void initData() {
        count = Integer.parseInt(count_et.getText().toString());
        nubs = new int[count];
        datas = new String[count];
        sb = new StringBuffer();
    }


    //核心功能
    private void function() {

        //填充数组为1~count
        for (int i = 0; i < count; i++) {
            nubs[i] = i + 1;
            sb.append(nubs[i]).append(" ");
        }
        Log.e("当前数组为：", sb.toString());
        Random r = new Random();

        //将数组第一位的数字与随机一位数字交换，重复1000次
        for (int i = 0; i < 1000; i++) {
            int index = r.nextInt(nubs.length);
            int temp = nubs[0];
            nubs[0] = nubs[index];
            nubs[index] = temp;
        }
        sb.setLength(0);

        //填充字符串数组，为ListView做数据源
        for (int i = 0; i < count; i++) {
            datas[i] = (i + 1) + "送给" + nubs[i];
            sb.append(datas[i]).append("  ");
        }

        //便利整个数组，如果出现自己送给自己，重新执行一遍核心功能代码
        for (int i = 0; i < count; i++) {
            if (nubs[i] == (i + 1)) {
                function();
            }
        }
        Log.e("改变后的数组为：", sb.toString());
        //output_tv.setText(sb.toString());
    }
}
