package com.example.hw81;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {
    private TextView mTextUserName;
    private TextView mTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViews();

        Bundle userPass = getIntent().getExtras();
        if (userPass != null) {
            String userName = userPass.getString(LoginActivity.EXTRA_USER_NAME);
            int userPassword = userPass.getInt(LoginActivity.EXTRA_PASSWORD_VALUE);
            mTextUserName.setText(userName);
            mTextPassword.setText("" + userPassword);
        }
    }

    private void findViews() {
        mTextUserName = (EditText)findViewById(R.id.txt_username);
        mTextPassword = (EditText)findViewById(R.id.txt_password);
    }
}