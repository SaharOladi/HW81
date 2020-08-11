package com.example.hw81.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hw81.R;
import com.example.hw81.controller.LoginActivity;

public class SignupActivity extends AppCompatActivity {
    public static final String EXTRA_USER_NAME_SIGNUP = "com.example.hw81.username";
    public static final String EXTRA_PASSWORD_SIGNUP = "com.example.hw81.password";
    private TextView mTextUserName;
    private TextView mTextPassword;
    private Button mButtonSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViews();
        setListener();


        Bundle userPass = getIntent().getExtras();
        if (userPass != null) {
            String userName = userPass.getString(LoginActivity.EXTRA_USER_NAME_LOGIN);
            int userPassword = userPass.getInt(LoginActivity.EXTRA_PASSWORD_LOGIN);
            mTextUserName.setText(userName);
            mTextPassword.setText("" + userPassword);
        }


    }

    private void setListener() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextUserName.getText().toString().equals(""))
                    Toast.makeText(SignupActivity.this, "User Name is empty, please enter your user name",
                            Toast.LENGTH_LONG).show();
                if (mTextPassword.length() == 0)
                    Toast.makeText(SignupActivity.this, "User Password is empty, please enter your user password",
                            Toast.LENGTH_LONG).show();
                if (mTextUserName.getText().toString().equals("") && mTextPassword.length() == 0)
                    Toast.makeText(SignupActivity.this, "User Name and User Password are empty, please fill them",
                            Toast.LENGTH_SHORT).show();
                if (!mTextUserName.getText().toString().equals("") && mTextPassword.length() != 0) {
                    setEditText();
                    finish();
                }
            }
        });
    }

    private void setEditText() {
        Intent intent = new Intent();
        String userName = mTextUserName.getText().toString();
        int userPassword = Integer.parseInt(mTextPassword.getText().toString());
        intent.putExtra(EXTRA_USER_NAME_SIGNUP, userName);
        intent.putExtra(EXTRA_PASSWORD_SIGNUP, userPassword);

        setResult(RESULT_OK, intent);
    }

    private void findViews() {
        mTextUserName = (EditText) findViewById(R.id.txt_username_second);
        mTextPassword = (EditText) findViewById(R.id.txt_password_second);
        mButtonSignUp = findViewById(R.id.btn_sign_up_second);

    }
}