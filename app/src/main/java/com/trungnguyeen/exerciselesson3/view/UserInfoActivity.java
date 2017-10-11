package com.trungnguyeen.exerciselesson3.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.trungnguyeen.exerciselesson3.ConnectivityChangeReceiver;
import com.trungnguyeen.exerciselesson3.R;

public class UserInfoActivity extends AppCompatActivity implements UserInfoActivityListener {

    private TextView tvUsername;


    public static final String TAG = UserInfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        getDataUser();
        getWidgets();




        registerReceiver(new ConnectivityChangeReceiver(this),
                         new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    public void internetConnected() {
        Toast.makeText(UserInfoActivity.this, "Internet connected!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternet() {
        Toast.makeText(UserInfoActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
    }


    private void getDataUser() {
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
