package com.example.hw81.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.hw81.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME_LOGIN = "com.example.hw81.UserName";
//    public static final String EXTRA_PASSWORD_LOGIN = "com.example.hw81.PasswordValue";
    public static final String EXTRA_INDEX = "com.example.hw81.currentIndex";
    public static final int SIGNUP_CODE = 0;
    private TextView mTextUserName;
    private TextView mTextPassword;
    private Button mButtonLogin;
    private Button mButtonSignUp;
    private static UsersInfo[] mUsersInformations = new UsersInfo[10];
    private int mCurrentIndex = 0;


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
                UsersInfo user = new UsersInfo();
                String userName = "";
                int userPassword = 0;

                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);

                if (!mTextUserName.toString().trim().isEmpty()) {
                    userName = mTextUserName.getText().toString();
                }
                if (mTextPassword.length() != 0) {
                    userPassword = Integer.parseInt(mTextPassword.getText().toString());
                }

                if (!userName.isEmpty() && userPassword != 0) {
                    user.setUserName(userName);
                    user.setUserPassword(userPassword);
                } else if (userName.isEmpty() && userPassword != 0) {
                    user.setUserPassword(userPassword);
                    user.setUserName("");

                } else if (!userName.isEmpty() && userPassword == 0) {
                    user.setUserName(userName);
                }

                intent.putExtra(EXTRA_USER_NAME_LOGIN, user);
                startActivityForResult(intent, SIGNUP_CODE);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mCurrentIndex; i++) {
                    if (mUsersInformations[i].getUserName().equals(mTextUserName.getText().toString())
                            &&
                            (mUsersInformations[i].getUserPassword()==Integer.parseInt(mTextPassword.getText().toString()))) {

                        Snackbar snackbar = Snackbar.make(v, "You Can LOG IN", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        Snackbar snackbar = Snackbar.make(v, "You Can Not LOG IN", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

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

    private void setResultFromSignUp(String strInp, int intInp) {
        mTextUserName.setText(strInp);
        mTextPassword.setText("" + intInp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != SignupActivity.RESULT_OK || data == null) {
            return;
        }
        if (requestCode == SIGNUP_CODE) {
            mUsersInformations = (UsersInfo[]) data.getSerializableExtra(SignupActivity.EXTRA_USER_NAME_PASSWORD_SIGN_UP);
            mCurrentIndex = data.getIntExtra(SignupActivity.EXTRA_CURRENT_INDEX, 0);
        }
        setResultFromSignUp(mUsersInformations[mCurrentIndex-1].getUserName(), mUsersInformations[mCurrentIndex-1].getUserPassword());
    }
}