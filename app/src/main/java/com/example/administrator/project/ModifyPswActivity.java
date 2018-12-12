package com.example.administrator.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ModifyPswActivity extends AppCompatActivity {
    private EditText et_x_pwd,et_y_pwd,et_x_okpwd;
    private Button btn_save;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        initView();
        initListener();
    }

    private void initListener() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_yuan = et_y_pwd.getText().toString().trim();
                String srt_new = et_x_pwd.getText().toString().trim();
                String str_oknew = et_x_okpwd.getText().toString().trim();
                SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
                if(str_yuan.isEmpty()&&str_oknew.isEmpty()&&srt_new.isEmpty()){
                    Toast.makeText(ModifyPswActivity.this,
                            "内容不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else if(!(srt_new.equals(str_oknew))){
                    Toast.makeText(ModifyPswActivity.this,
                            "密码不一致",Toast.LENGTH_SHORT).show();
                }else if(!sp.getString("password","").equals(str_yuan)){
                    Toast.makeText(ModifyPswActivity.this,
                            "原密码输入错误",Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor editor = getSharedPreferences
                            ("data",MODE_PRIVATE).edit();
                    editor.putString("password",srt_new);
                    editor.commit();
                    Toast.makeText(ModifyPswActivity.this,
                            "密码修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ModifyPswActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
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
        et_x_pwd = findViewById(R.id.et_x_pwd);
        et_y_pwd = findViewById(R.id.et_y_pwd);
        et_x_okpwd = findViewById(R.id.et_ok_pwd);
        btn_save = findViewById(R.id.btn_save);
        img = findViewById(R.id.Modify_exit);
    }
}
