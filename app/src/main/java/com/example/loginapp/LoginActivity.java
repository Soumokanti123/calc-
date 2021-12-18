package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText edusername;
    private EditText edpassword;
    private Button btnlogin;
    private Button btnsignUp;

    private final String CREDENTIAL_SHARED_PREP = "our_shared_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edusername = findViewById(R.id.ed_username);
        edpassword = findViewById(R.id.ed_password);
        btnlogin = findViewById(R.id.btn_login);
        btnsignUp = findViewById(R.id.btn_signUp);

        btnsignUp.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                                             startActivity(intent);
                                         }
                                     }
        );

        btnlogin.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View view) {
                                            SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREP, Context.MODE_PRIVATE);
                                            String strUsername = credentials.getString("Username", null);
                                            String strPassword = credentials.getString("Password", null);

                                            String username_from_ed = edusername.getText().toString();
                                            String password_from_ed = edpassword.getText().toString();

                                            if (strUsername != null && strUsername.equalsIgnoreCase(username_from_ed)) {
                                                if (strPassword != null && strPassword.equalsIgnoreCase(password_from_ed)) {
                                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
        );
    }
}
