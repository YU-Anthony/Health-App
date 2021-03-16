package com.example.healthapp;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.healthapp.ui.emergencyCall.EmergencyCallFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactInfoActivity extends AppCompatActivity  {

    private ImageView contactAdd;
   // private SharedPreferences mSharedPreferences;
   // private SharedPreferences.Editor mEditor;
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

       // mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
       // mEditor = mSharedPreferences.edit();

        contactAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLastName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Last name field cannot be empty",
                            Toast.LENGTH_LONG).show();
                } else if (mFirstName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "First name field cannot be empty",
                            Toast.LENGTH_LONG).show();
                } else if (mPhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Phone number field cannot be empty",
                            Toast.LENGTH_LONG).show();
                } else if (mEmaill.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email field cannot be empty",
                            Toast.LENGTH_LONG).show();
                } else {

                    ContentResolver contentResolver = getContentResolver();

                    ContentValues newContactPerson = new ContentValues();
                    newContactPerson.put(HealthAppContract.CONTACTPERSON_LASTNAME, mLastName.getText().toString());
                    newContactPerson.put(HealthAppContract.CONTACTPERSON_FIRSTNAME, mFirstName.getText().toString());
                    newContactPerson.put(HealthAppContract.CONTACTPERSON_PHONENUMBER, mPhoneNumber.getText().toString());
                    newContactPerson.put(HealthAppContract.CONTACTPERSON_EMAIL, mEmaill.getText().toString());
                    contentResolver.insert(HealthAppContract.CONTACTPERSON_URI, newContactPerson);
                    Toast.makeText(getApplicationContext(), "New contact person saved successfully", Toast.LENGTH_LONG).show();

                    finish();


                    Intent main = new Intent(ContactInfoActivity.this, EmergencyCallFragment.class);
                    setResult(1, main);
                }


            }
        });


    }



}
