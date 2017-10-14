package com.trungnguyeen.exerciselesson3.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.trungnguyeen.exerciselesson3.ConnectivityChangeReceiver;
import com.trungnguyeen.exerciselesson3.PlaySongService;
import com.trungnguyeen.exerciselesson3.R;

public class UserInfoActivity extends AppCompatActivity implements UserInfoActivityListener {

    private TextView tvUsername;
    private Button btnPlaySong;
    private Button btnStopSong;

    private boolean isOnline = false;

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

        registerReceiver(new ConnectivityChangeReceiver(this),
                         new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        btnPlaySong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSong();
            }
        });


        btnStopSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSong();
            }
        });
    }

    private void stopSong() {
        Intent intent = new Intent(UserInfoActivity.this, PlaySongService.class);
        stopService(intent);
    }

    private void playSong() {
        if(!isOnline){
            Toast.makeText(this, "No internet. Please turn on internnet.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(UserInfoActivity.this, PlaySongService.class);
        startService(intent);

    }


    @Override
    public void internetConnected() {
        Toast.makeText(UserInfoActivity.this, "Internet connected!", Toast.LENGTH_SHORT).show();
        isOnline = true;
    }


    @Override
    public void noInternet() {
        Toast.makeText(UserInfoActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
        isOnline = false;
        if(PlaySongService.isPlaying == true){
            stopSong();
        }
    }


    private void getWidgets() {
        tvUsername = (TextView) findViewById(R.id.tv_username);
        btnPlaySong = (Button) findViewById(R.id.btn_playSong);
        btnStopSong = (Button) findViewById(R.id.btn_stopSong);
    }



}
