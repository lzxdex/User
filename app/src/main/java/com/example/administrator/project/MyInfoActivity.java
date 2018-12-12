package com.example.administrator.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyInfoActivity extends AppCompatActivity {
    private ImageView img_tx,img;
    private TextView tv_setting;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);
        initView();
        sp = getSharedPreferences("data",MODE_PRIVATE);
        boolean flag = sp.getBoolean("flag",false);
        if(!flag){
            Toast.makeText(MyInfoActivity.this,
                    "你还未登录，请先登录", Toast.LENGTH_SHORT).show();
        }
        initListener();
    }

    private void initListener() {
        img_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = sp.getString("account","");
                if(flag.isEmpty()) {
                    Intent intent = new Intent(MyInfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    //跳转到个人信息界面
                    Intent intent = new Intent(MyInfoActivity.this,UserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
        tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = sp.getString("account","");
                if(flag.isEmpty()) {
                    Toast.makeText(MyInfoActivity.this,
                            "你还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }else {
                    //跳转到设置界面
                    Intent intent = new Intent(MyInfoActivity.this,
                            SettingActivity.class);
                    startActivity(intent);
                }
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        img_tx = findViewById(R.id.touXiang);
        tv_setting = findViewById(R.id.setting);
        img = findViewById(R.id.Myinfo_exit);
    }

}
