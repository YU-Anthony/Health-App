package com.example.healthapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView contactAdd;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public EditText mLastName;
    public EditText mFirstName;
    public EditText mPhoneNumber;
    public EditText mEmaill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_information);

        Toolbar toolbar = findViewById(R.id.contact_toolbar);
        //把布局中的Toolbar当作ActionBar
        setSupportActionBar(toolbar);
        // 设置返回键 icon
        toolbar.setNavigationIcon(R.drawable.left_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//返回
            }
        });

        contactAdd = findViewById(R.id.contact_add);
        mLastName  = findViewById(R.id.last_name);
        mFirstName  = findViewById(R.id.first_name);
        mPhoneNumber  = findViewById(R.id.phone_number);
        mEmaill  = findViewById(R.id.emaill_adress);

        mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        contactAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString("LastName",mLastName.getText().toString());
                mEditor.putString("FirstName",mFirstName.getText().toString());
                mEditor.putString("PhoneNumber",mPhoneNumber.getText().toString());
                mEditor.putString("Email",mEmaill.getText().toString());
                mEditor.apply();
                finish();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_add:
                finish();
                break;
            default:
                break;
        }
    }
}
