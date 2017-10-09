package com.trungnguyeen.exerciselesson3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {

    private TextView tvUsername;


    public static final String TAG = UserInfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        getWidgets();

        Intent intent = getIntent();
        if (intent != null){
            String username = intent.getExtras().getString(SignupActivity.USERNAME);
            tvUsername.setText(username);
        }
    }


    private void getWidgets() {
        tvUsername = (TextView) findViewById(R.id.tv_username);
    }
}
