package com.trungnguyeen.exerciselesson3.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trungnguyeen.exerciselesson3.ConnectivityChangeReceiver;
import com.trungnguyeen.exerciselesson3.R;

public class SignupActivity extends AppCompatActivity {


    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnSignUp;
    private ConnectivityChangeReceiver checkNetwork;

    public static final String USERNAME = "USERNAME";
    public static final String TAG = SignupActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getWidgets();
        setEvents();



    }




    private void getWidgets() {
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtConfirmPassword = (EditText) findViewById(R.id.edt_confirmPassword);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
    }

    private void setEvents() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edtUsername.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) &&
                        !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)){

                    //check validate Email
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()){
                        Toast.makeText(SignupActivity.this, "Email is Invalid.", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if(!password.equals(confirmPassword)){
                        Toast.makeText(SignupActivity.this, "Confirm password does not match.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = new Intent(SignupActivity.this, UserInfoActivity.class);
                    intent.putExtra(USERNAME, username);
                    startActivity(intent);
                    Toast.makeText(SignupActivity.this, "Sign Up Success!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(SignupActivity.this, "Please complete all!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    //Hidden keyboard when touch outside
    public void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }
}
