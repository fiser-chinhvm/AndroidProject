package com.example.hoang.bookmovietickets;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginForm extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView register;
    Button logIn;
    int count = 4;
    public static boolean checkLgIn = false;

    public static ArrayList<String> user = new ArrayList<String>();
    public static ArrayList<String> pass = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.login_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        user.add("admin");
        pass.add("admin");

        FindViewID();
        setEditText();
        setClick();

    }

    public void FindViewID(){
        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        register = (TextView) findViewById(R.id.toRegister);
        logIn = (Button) findViewById(R.id.btnLogin);
    }

    public void setClick(){
        final boolean[] check = {false};
       logIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {// nut log inan vao thi the nay
               for (int i = 0; i < user.size(); i++) {
                   if (username.getText().toString().equals(user.get(i)) &&
                           password.getText().toString().equals(pass.get(i))) {
                       check[0] = true;
                       checkLgIn = true;
                       Intent intent = getIntent();
                       intent.putExtra("isLogin", checkLgIn);
                       setResult(RESULT_OK, intent);
                       finish();//log in dc thi tat no di thoi k phai dang ky a
                       Toast.makeText(LoginForm.this, "You 've Logged In", Toast.LENGTH_SHORT).show();
                   }
               }
                   if(check[0] == false) {
                       count--;
                       Toast.makeText(LoginForm.this, "INVALID , Attemps left:" + String.valueOf(count), Toast.LENGTH_SHORT).show();
                       if (count == 0) {
                           logIn.setVisibility(View.INVISIBLE);
                           Toast.makeText(LoginForm.this, "You cant Log in now", Toast.LENGTH_SHORT).show();
                       }
                   }
               }
       });
//               String tempu = username.getText().toString();
//               String tempp = password.getText().toString();
//               if(tempu.equals("admin") && tempp.equals("admin") ){
//                   finish();
////               } else{
//                   count--;
//                   Toast.makeText(LoginForm.this,"INVALID , Attemps left:" + tempp+tempu+String.valueOf(count),Toast.LENGTH_SHORT).show();
//                   if(count == 0) {
//                       logIn.setEnabled(false);
//                       Toast.makeText(LoginForm.this,"You cant Log in now",Toast.LENGTH_SHORT).show();
////                   }
////               }
//
//           }
//       });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForm.this, RegisterForm.class);
                startActivity(intent);// dang ki day, chi can quan tam log in xong, thoat log in ra
            }
        });
    }

    public void setEditText(){
        username.setBackgroundResource(R.drawable.unfocus_edittext);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    username.setBackgroundResource(R.drawable.focus_edittext);
                } else {
                    username.setBackgroundResource(R.drawable.unfocus_edittext);
                }
            }
        });

        password.setBackgroundResource(R.drawable.unfocus_edittext);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    password.setBackgroundResource(R.drawable.focus_edittext);
                } else {
                    password.setBackgroundResource(R.drawable.unfocus_edittext);
                }
            }
        });

    }
}
