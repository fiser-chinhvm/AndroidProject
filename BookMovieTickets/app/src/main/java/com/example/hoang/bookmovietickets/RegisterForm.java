package com.example.hoang.bookmovietickets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterForm extends AppCompatActivity {
    TextView lgIn;
    EditText u;
    EditText p1;
    EditText p2;
    Button btncreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FindAllView();
        setClickBtn();


    }

    public void FindAllView(){
        u = (EditText) findViewById(R.id.user);
        p1 = (EditText) findViewById(R.id.pass);
        p2 = (EditText) findViewById(R.id.rePass);
        btncreate = (Button) findViewById(R.id.createAcc);
        lgIn = (TextView) findViewById(R.id.backToLgIn);
    }

    public boolean checkPass(){
        if(p1.getText().toString().length() < 4) return  false;
        if(p1.getText().toString().equals(p2.getText().toString())) return true;
        return false;
    }

    public boolean checkUser(){
        if(u.getText().toString().length() < 4 || p2.getText().toString().length() < 4) return  false;
        for(int i = 0 ; i < LoginForm.user.size() ; i++){
            if(u.equals(LoginForm.user.get(i))) return false;
        }
        return true;
    }

    public void setClickBtn(){
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPass()) {
                    Toast.makeText(getApplicationContext(), "Password Not Match or Too Short", Toast.LENGTH_SHORT).show();
                } else {
                    if (!checkUser()) {
                        Toast.makeText(getApplicationContext(), "Excisting or Invalid Username", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "SuccessFully Create Account", Toast.LENGTH_SHORT).show();
                        LoginForm.user.add(u.getText().toString());
                        LoginForm.pass.add(p1.getText().toString());
                    }
                }

            }
        });

        lgIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
