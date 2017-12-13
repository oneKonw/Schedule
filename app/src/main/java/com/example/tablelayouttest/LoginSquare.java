package com.example.tablelayouttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginSquare extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_square);
        final EditText edit_Account = (EditText) findViewById(R.id.edit_Account);
        final EditText edit_Password = (EditText) findViewById(R.id.edit_Password);
        Button btn_Login = (Button) findViewById(R.id.btn_Login);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSquare.this ,MainActivity.class);
                intent.putExtra("account", edit_Account.getText().toString());
                intent.putExtra("password",edit_Password.getText().toString());
                startActivity(intent);
            }
        });
    }
}
