package com.example.administrator.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_account,et_pwd,et_okpwd;
    private Button btn_register;
    private ImageView img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
        initListener();
    }

    private void initListener() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_account = et_account.getText().toString().trim();
                String str_pwd = et_pwd.getText().toString().trim();
                String str_okpwd = et_okpwd.getText().toString().trim();
                Log.d("flag",!str_pwd.equals(str_okpwd)+str_pwd+":"+str_okpwd);
                if(str_account.isEmpty()&&str_pwd.isEmpty()&&str_okpwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this,
                            "输入的内容能为空", Toast.LENGTH_SHORT).show();
                }else if(!str_pwd.equals(str_okpwd)) {
                    Toast.makeText(RegisterActivity.this,
                            "密码不一致",Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor editor = getSharedPreferences
                            ("data", MODE_PRIVATE).edit();
                    editor.putString("account", str_account);
                    editor.putString("password", str_pwd);
                    editor.commit();
                    Toast.makeText(RegisterActivity.this,
                            "注册成功",Toast.LENGTH_SHORT).show();
                    finish();
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
        et_account = findViewById(R.id.et_account);
        et_pwd = findViewById(R.id.et_repassword);
        et_okpwd = findViewById(R.id.et_okpwd);
        btn_register = findViewById(R.id.btn_register);
        img = findViewById(R.id.register_exit);
    }
}
