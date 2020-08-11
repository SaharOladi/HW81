package com.example.hw81.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hw81.R;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME_LOGIN = "com.example.hw81.UserName";
    public static final String EXTRA_PASSWORD_LOGIN = "com.example.hw81.PasswordValue";
    public static final int SIGNUP_CODE = 0;
    private TextView mTextUserName;
    private TextView mTextPassword;
    private Button mButtonLogin;
    private Button mButtonSignUp;

    private String mSignupString = "";
    private int mSignupInt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();


        setListener();
    }

    private void setListener() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                if (!mTextUserName.getText().toString().equals("")){
                    String userName = mTextUserName.getText().toString();
                    intent.putExtra(EXTRA_USER_NAME_LOGIN, userName);
                }
                if (mTextPassword.length() != 0) {
                    int userPassword = Integer.parseInt(mTextPassword.getText().toString());
                    intent.putExtra(EXTRA_PASSWORD_LOGIN, userPassword);
                }

                startActivityForResult(intent, SIGNUP_CODE);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mSignupString.equals(mTextUserName.getText().toString()) || 
                        mSignupInt != Integer.parseInt(mTextPassword.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Sign up fields and Log in fields are not match",
                            Toast.LENGTH_LONG).show();
                }
                // to do, add snackbar for show message
            }
        });
    }


    private void findViews() {
        mTextUserName = (EditText) findViewById(R.id.txt_username);
        mTextPassword = (EditText) findViewById(R.id.txt_password);
        mButtonLogin = findViewById(R.id.btn_login);
        mButtonSignUp = findViewById(R.id.btn_sign_up);
    }
    private void setResultFromSignUp(String strInp, int intInp){
        mTextUserName.setText(strInp);
        mTextPassword.setText(""+intInp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != SignupActivity.RESULT_OK || data == null) {
            return;
        }
        if(requestCode == SIGNUP_CODE){
            mSignupString = data.getStringExtra(SignupActivity.EXTRA_USER_NAME_SIGNUP);
            mSignupInt = data.getIntExtra(SignupActivity.EXTRA_PASSWORD_SIGNUP,0);
        }
        setResultFromSignUp(mSignupString,mSignupInt);
    }
}