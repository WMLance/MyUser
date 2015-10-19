package com.wm.lance.myuser;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText et_name;
    private EditText et_password;
    private CheckBox cb_all;
    private Button bt_login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb_all = (CheckBox) findViewById(R.id.cb_all);
        bt_login = (Button) findViewById(R.id.bt_login);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);


        File file = new File("/data/data/com.wm.lance.myuser/user.txt");
        try {
           // FileInputStream f= openFileInput("user.txt")

            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String text = br.readLine();
            String s[] = text.split("##");
            et_name.setText(s[0]);
            et_password.setText(s[1]);


        } catch (Exception e) {
            e.printStackTrace();
        }


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_name = (EditText) findViewById(R.id.et_name);
                String name = et_name.getText().toString().trim();
                et_password = (EditText) findViewById(R.id.et_password);
                String password = et_password.getText().toString().trim();
               



                if (cb_all.isChecked()) {
                    //将姓名和密码写进文件里面
                    File file = new File("/data/data/com.wm.lance.myuser/user.txt");
                    try {
                       // FileOutputStream f= openFileOutput("info.txt",MODE_PRIVATE);

                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write((name + "##" + password).getBytes());
                        fos.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                        Toast.makeText(MainActivity.this, "您记住啦用户名和密码", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(MainActivity.this, "您没有记住用户名和密码", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
