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
    public static final String EXTRA_USER_NAME_PASSWORD_SIGN_UP = "com.example.hw81.username_password";
    public static final String EXTRA_CURRENT_INDEX = "com.example.hw81.current_index";
    private TextView mTextUserName;
    private TextView mTextPassword;
    private Button mButtonSignUp;

    private UsersInfo[] mUsersInfos = new UsersInfo[10];
    private static int mCurrentIndex;
    private UsersInfo user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViews();
        setListener();


        Bundle userPass = getIntent().getExtras();
        if (userPass != null) {
            user = (UsersInfo) userPass.getSerializable(LoginActivity.EXTRA_USER_NAME_LOGIN);

            if (user.getUserName() != null && user.getUserPassword() != 0) {
                mTextUserName.setText(user.getUserName());
                mTextPassword.setText(String.valueOf(user.getUserPassword()));
            } else if (user.getUserName() != null && user.getUserPassword() == 0) {
                mTextUserName.setText(user.getUserName());
            } else if (user.getUserName() == null && user.getUserPassword() != 0) {
                mTextPassword.setText(String.valueOf(user.getUserPassword()));

            }


        }
    }

    private void setListener() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextUserName.getText().toString().equals(""))
                    Toast.makeText(SignupActivity.this, "User Name is empty, please enter your user name",
                            Toast.LENGTH_LONG).show();
                if (mTextPassword.getText().toString().trim().isEmpty())
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
        mUsersInfos[mCurrentIndex++] = new UsersInfo(userName, userPassword);
        intent.putExtra(EXTRA_USER_NAME_PASSWORD_SIGN_UP, mUsersInfos);
        intent.putExtra(EXTRA_CURRENT_INDEX, mCurrentIndex);
        setResult(RESULT_OK, intent);
    }

    private void findViews() {
        mTextUserName = (EditText) findViewById(R.id.txt_username_second);
        mTextPassword = (EditText) findViewById(R.id.txt_password_second);
        mButtonSignUp = findViewById(R.id.btn_sign_up_second);
    }

}