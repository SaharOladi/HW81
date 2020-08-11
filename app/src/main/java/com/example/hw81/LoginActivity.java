package com.example.hw81;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME = "UserName";
    public static final String EXTRA_PASSWORD_VALUE = "PasswordValue";
    private TextView mTextUserName;
    private TextView mTextPassword;
    private Button mButtonLogin;
    private Button mButtonSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();


        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTextUserName.getText().toString().equals("") && mTextPassword.length() != 0) {
                    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                    String userName = mTextUserName.getText().toString();
                    int userPassword = Integer.parseInt(mTextPassword.getText().toString());
                    intent.putExtra(EXTRA_USER_NAME, userName);
                    intent.putExtra(EXTRA_PASSWORD_VALUE,userPassword);
                    startActivity(intent);
                }
            }
        });
    }


    private void findViews() {
        mTextUserName = (EditText) findViewById(R.id.txt_username);
        mTextPassword = (EditText) findViewById(R.id.txt_password);
        mButtonLogin = findViewById(R.id.btn_login);
        mButtonSignUp = findViewById(R.id.btn_sign_up);
    }
}