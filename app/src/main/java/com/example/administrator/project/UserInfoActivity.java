package com.example.administrator.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {
    private TextView tv_sex,tv_nc,tv_qm;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        tv_sex = findViewById(R.id.sex);
        tv_nc = findViewById(R.id.niCheng);
        tv_qm = findViewById(R.id.QianMing);
        sp = getSharedPreferences("formation",MODE_PRIVATE);
        String qm = sp.getString("qm","");
        String nc = sp.getString("nc","");
        if(qm.isEmpty()&&qm.equals("")){
            tv_qm.setText("这个人很懒，什么都没有留下");
        }else {
            tv_qm.setText(qm);
        }
        if(nc.isEmpty()&&nc.equals("")){
            tv_nc.setText("Android");
        }else {
            tv_nc.setText(nc);
        }
        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this);
                builder.setTitle("请选择你的性别");
                final String[] items = {"男","女"};
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_sex.setText(items[which]);
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        tv_nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //昵称
                Intent intent = new Intent(UserInfoActivity.this,
                        ChangeUserInfoActivity.class);
                intent.putExtra("from","nc");
                startActivityForResult(intent,1);
            }
        });
        tv_qm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //签名
                Intent intent = new Intent(UserInfoActivity.this,
                        ChangeUserInfoActivity.class);
                intent.putExtra("from","qm");
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        editor = getSharedPreferences("formation",MODE_PRIVATE).edit();
        switch (requestCode){
            case 1:
                String str = data.getStringExtra("nc");
                editor.putString("nc",str);
                editor.commit();
                tv_nc.setText(str);
                break;
            case 2:
                String s = data.getStringExtra("qm");
                tv_qm.setText(s);
                editor.putString("qm",s);
                editor.commit();
                break;
        }
    }
}
