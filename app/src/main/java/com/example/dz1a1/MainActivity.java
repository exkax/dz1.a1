package com.example.dz1a1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ImageView image;
    private TextInputEditText password, username;
    private TextInputLayout user_lt,pass_lt;
    private String login;
    private int lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        user_lt = findViewById(R.id.layout1);
        pass_lt = findViewById(R.id.layout2);
        image = findViewById(R.id.image);
        initListeners();
        button.setOnClickListener(view -> go_j());


        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void afterTextChanged(Editable s) {


                if (password.getText().toString().length()>0){
                    button.setBackgroundColor(getColor(R.color.black));
                } else {
                    button.setBackgroundColor(getColor(R.color.gray));

                }
            }
        });
    }


    private void initListeners() {
        Glide.with(this).load("https://i.pinimg.com/474x/23/ab/a6/23aba60b66ef08174bb7455c4a8a2d2f.jpg").into(image);
    }


    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }


    }

    private boolean validate_name() {
        if (username.getText().toString().trim().isEmpty()) {
            user_lt.setError("EnterName");
            requestFocus(username);
            return false;
        }
        return true;


    }private boolean validate_pass() {
        if (password.getText().toString().trim().isEmpty()) {
            pass_lt.setError("Введите пароль!");
            requestFocus(password);
            return false;
        } else {
            if (password.getText().toString().trim().length() < 6) {
                pass_lt.setError("Пароль не должен быть меньше 6 ");
                requestFocus(password);
                return false;
            }
        }
        return true;


    }

    private void go_j() {
        if (!validate_name()){
            return;
        }
        if (!validate_pass())
        {
            return;
        }


        String sname = username.getText().toString().trim() + "";
        String snam = password.getText().toString().trim() + "";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDate();
            }
        });
    }
    public void sendDate()
    {
        login = username.getText().toString().trim();
        lock = Integer.parseInt(password.getText().toString().trim());
        Intent i = new Intent(MainActivity.this,SecondActivity.class);


        i.putExtra(SecondActivity.NAME,login);
        i.putExtra(SecondActivity.AGE,lock);


        startActivity(i);





    }





}