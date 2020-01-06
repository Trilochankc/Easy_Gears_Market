package com.trilochan.easy_gears_market;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnResgiter;
    Button btnLogin;
    EditText etemail;
    EditText etpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // full screen code
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        btnResgiter = findViewById(R.id.btnReg);
        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        btnLogin = findViewById(R.id.btnlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (etemail.getText().toString().equals("Admin@gmail.com") && (etpassword.getText().toString().equals("Admin")))
                {
                    Login();
                    Intent intent =new Intent(LoginActivity.this,Splash_Screen.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();

                }
                Check();

            }
        });
        btnResgiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }


        });

    }
    private void Login(){
        SharedPreferences sharedPreferences =getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();

        editor.putString("username",etemail.getText().toString().trim());
        editor.putString("password",etpassword.getText().toString().trim());
        editor.commit();
    }
    private void Check(){
        SharedPreferences sharedPreferences =getSharedPreferences("User",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");

        if (username.equals(etemail.getText().toString())&&
                password.equals(etpassword.getText().toString())){
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}


