package com.example.administrator.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeUserInfoActivity extends AppCompatActivity {
    private ImageView exit_nc,exit_qm,del_qm,del_nc;
    private TextView save_nc,save_qm;
    private EditText et_qm,et_nc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        String s = intent.getStringExtra("from");
        if(s.equals("qm")){
            setContentView(R.layout.qm_layout);
            et_qm = findViewById(R.id.et_qm);
            del_qm = findViewById(R.id.del_qm);
            save_qm = findViewById(R.id.save_qm);
            exit_qm = findViewById(R.id.exit_qm);
            save_qm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = et_qm.getText().toString().trim();
                    if(str.isEmpty()){
                        Toast.makeText(ChangeUserInfoActivity.this,
                                "签名不能为空",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("qm",str);
                    setResult(2,intent);
                    finish();
                }
            });
            del_qm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    et_qm.setText("");
                }
            });
            exit_qm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else {
            setContentView(R.layout.nc_layout);
            et_nc = findViewById(R.id.et_nc);
            del_nc = findViewById(R.id.del_nc);
            save_nc = findViewById(R.id.save_nc);
            exit_nc = findViewById(R.id.exit_nc);
            save_nc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = et_nc.getText().toString().trim();
                    if(str.isEmpty()){
                        Toast.makeText(ChangeUserInfoActivity.this,
                                "昵称不能为空",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("nc",str);
                    setResult(1,intent);
                    finish();
                }
            });
            del_nc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    et_nc.setText("");
                }
            });
            exit_nc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }
}
