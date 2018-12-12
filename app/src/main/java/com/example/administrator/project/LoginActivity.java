package com.example.administrator.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText et_account,et_password;
    private Button btn_login;
    private TextView tv_ergister,tv_retrieve;
    private ImageView img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        initListener();
    }

    private void initView() {
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.login);
        tv_ergister = findViewById(R.id.tv_register);
        img = findViewById(R.id.login_exit);
        tv_retrieve = findViewById(R.id.retrieve);
    }

    private void initListener() {
        tv_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到找回密码界面
                Intent intent = new Intent(LoginActivity.this,
                        FindPswActivity.class);
                intent.putExtra("from","retrieve");
                startActivity(intent);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_ergister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
                String str_account = et_account.getText().toString().trim();
                String str_pwd = et_password.getText().toString().trim();
                String act = sp.getString("account","");
                String pwd = sp.getString("password","");
                if(str_account.isEmpty()&&str_pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,
                            "不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(str_account.equals(act)&&str_pwd.equals(pwd)){
                    SharedPreferences.Editor editor = getSharedPreferences
                            ("data",MODE_PRIVATE).edit();
                    editor.putBoolean("flag",true);
                    editor.commit();
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,
                            "用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
