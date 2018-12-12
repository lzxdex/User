package com.example.administrator.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    private TextView tv_alertPwd,tv_Mibao;
    private LinearLayout exit;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initListener();
    }

    private void initListener() {
        tv_alertPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置密码界面
                Intent intent = new Intent(SettingActivity.this,
                        ModifyPswActivity.class);
                startActivity(intent);
            }
        });
        tv_Mibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置密保界面
                Intent intent = new Intent(SettingActivity.this,
                        FindPswActivity.class);
                intent.putExtra("from","MiBao");
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传递到我的界面
                SharedPreferences.Editor editor = getSharedPreferences
                        ("data",MODE_PRIVATE).edit();
                editor.putString("account","");
                editor.putString("password","");
                editor.putBoolean("flag",false);
                editor.commit();
                Intent intent = new Intent(SettingActivity.this,
                        MyInfoActivity.class);
                setResult(RESULT_OK);
                startActivity(intent);
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
        tv_alertPwd = findViewById(R.id.alterPwd);
        tv_Mibao = findViewById(R.id.setMiBao);
        exit = findViewById(R.id.exit);
        img = findViewById(R.id.Set_exit);
    }
}
