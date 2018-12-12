package com.example.administrator.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindPswActivity extends AppCompatActivity {
    private Button btn_ok_MiBao,btn_ok_Pwd;
    private EditText et_name,et_account,et_ok_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String s = intent.getStringExtra("from");
        if(s.equals("MiBao")){
            setContentView(R.layout.set_modify);
            btn_ok_MiBao = findViewById(R.id.ok_name);
            et_name = findViewById(R.id.et_name);
            btn_ok_MiBao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = getSharedPreferences
                            ("MiBao",MODE_PRIVATE).edit();
                    String str = et_name.getText().toString().trim();
                    if(str.isEmpty()){
                        Toast.makeText(FindPswActivity.this,"输入的内容不能为空",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        editor.putString("mibao",str);
                        editor.commit();
                        Toast.makeText(FindPswActivity.this,"设置成功",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            });
        }else {
            setContentView(R.layout.retrieve_pwd);
            btn_ok_Pwd = findViewById(R.id.ok_pwd);
            et_account = findViewById(R.id.et_account);
            et_ok_name = findViewById(R.id.et_ok_name);
            btn_ok_Pwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
                    SharedPreferences sp2 = getSharedPreferences("MiBao",MODE_PRIVATE);
                    String str_account = sp.getString("account","");
                    String str_name = sp2.getString("mibao","");
                    String str_act = et_account.getText().toString().trim();
                    String str_ok_name = et_ok_name.getText().toString().trim();
                    if(str_act.isEmpty()||str_ok_name.isEmpty()){
                        Toast.makeText(FindPswActivity.this,"输入的内容不能为空",
                                Toast.LENGTH_SHORT).show();
                    }else if(!str_account.equals(str_act)){
                        Toast.makeText(FindPswActivity.this,"用户名不一致",
                                Toast.LENGTH_SHORT).show();
                    }else if(str_name.isEmpty()){
                        Toast.makeText(FindPswActivity.this,"你还没有设置密保",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        SharedPreferences.Editor editor = getSharedPreferences
                                ("data",MODE_PRIVATE).edit();
                        editor.putString("password","123456");
                        editor.commit();
                        Toast.makeText(FindPswActivity.this,"找回成功，初始密码为123456",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }




}
