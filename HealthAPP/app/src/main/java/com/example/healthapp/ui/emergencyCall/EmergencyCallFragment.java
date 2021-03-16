package com.example.healthapp.ui.emergencyCall;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthapp.HealthAppContract;
import com.example.healthapp.R;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class EmergencyCallFragment extends Fragment implements View.OnClickListener {

    private EmergencyCallViewModel emergencyCallViewModel;
    private LinearLayout contactList;
    private ImageView iv1;
    private ImageView btn1;
    private ImageView contact1, contact2, contact3;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private RadioGroup rg;
    private long id1,id2,id3;
    public String tPhoneNumber = "";
    Handler handler = new Handler();
    private StringBuilder sb;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


            emergencyCallViewModel =
                    new ViewModelProvider(this).get(EmergencyCallViewModel.class);
            View root = inflater.inflate(R.layout.fragment_emergency_call, container, false);

            TextView callLog = root.findViewById(R.id.callLog_history);

            System.out.println("count");
            //引用 toolbar
            Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.emergencyCall_toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            iv1 = root.findViewById(R.id.phone_call_button);
            btn1=root.findViewById(R.id.add_contact);
            contactList=root.findViewById(R.id.contact_list);
            contact1 = root.findViewById(R.id.contact1);
            contact2 = root.findViewById(R.id.contact2);
            contact3 = root.findViewById(R.id.contact3);
            radioButton1 = root.findViewById(R.id.radioButton1);
            radioButton2 = root.findViewById(R.id.radioButton2);
            radioButton3 = root.findViewById(R.id.radioButton3);
            rg = root.findViewById(R.id.rg);
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        System.out.println("button1");
                        Cursor cursor1 = getContext().getContentResolver().query(HealthAppContract.CONTACTPERSON_URI, null,
                                null, null, HealthAppContract.CONTACTPERSON_ID + " ASC");
                        cursor1.moveToFirst();
                        tPhoneNumber = cursor1.getString(cursor1.getColumnIndex(HealthAppContract.CONTACTPERSON_PHONENUMBER));
                        getCallLogs(tPhoneNumber);
                        callLog.setText(sb.toString());
                        break;
                    case R.id.radioButton2:
                        System.out.println("button2");
                        Cursor cursor2 = getContext().getContentResolver().query(HealthAppContract.CONTACTPERSON_URI, null,
                                null, null, HealthAppContract.CONTACTPERSON_ID + " ASC");
                        cursor2.moveToFirst();
                        cursor2.moveToNext();
                        tPhoneNumber = cursor2.getString(cursor2.getColumnIndex(HealthAppContract.CONTACTPERSON_PHONENUMBER));
                        getCallLogs(tPhoneNumber);
                        callLog.setText(sb.toString());
                        break;
                    case R.id.radioButton3:
                        System.out.println("button3");
                        Cursor cursor3 = getContext().getContentResolver().query(HealthAppContract.CONTACTPERSON_URI, null,
                                null, null, HealthAppContract.CONTACTPERSON_ID + " ASC");
                        cursor3.moveToFirst();
                        cursor3.moveToNext();
                        cursor3.moveToNext();
                        tPhoneNumber = cursor3.getString(cursor3.getColumnIndex(HealthAppContract.CONTACTPERSON_PHONENUMBER));
                        getCallLogs(tPhoneNumber);
                        callLog.setText(sb.toString());
                        break;
                }

            }
        });

            iv1.setOnClickListener(this);
            btn1.setOnClickListener(this);
            contactList.setOnClickListener(this);

            contact1.setVisibility(View.GONE);
            contact2.setVisibility(View.GONE);
            contact3.setVisibility(View.GONE);
            radioButton1.setVisibility(View.GONE);
            radioButton2.setVisibility(View.GONE);
            radioButton3.setVisibility(View.GONE);
            displayThreePersons();

            return root;

    }

   private void displayThreePersons() {
        Cursor cursor;

        //Data that will be displayed on listview rows about each session
        String[] columns = new String[]{
                HealthAppContract.CONTACTPERSON_LASTNAME,
                HealthAppContract.CONTACTPERSON_FIRSTNAME,
                HealthAppContract.CONTACTPERSON_PHONENUMBER,
                HealthAppContract.CONTACTPERSON_EMAIL,
                HealthAppContract.CONTACTPERSON_ID,
        };

        //Query for all the sessions, date descending so the newest sessions are on the top
        cursor = getContext().getContentResolver().query(HealthAppContract.CONTACTPERSON_URI, columns,
                null, null, HealthAppContract.CONTACTPERSON_ID + " ASC");

       if (cursor.getCount() == 0) {
           contact1.setVisibility(View.GONE);
           contact2.setVisibility(View.GONE);
           contact3.setVisibility(View.GONE);
           radioButton1.setVisibility(View.GONE);
           radioButton2.setVisibility(View.GONE);
           radioButton3.setVisibility(View.GONE);
       } else if(cursor.getCount() == 1){
           contact1.setVisibility(View.VISIBLE);
           radioButton1.setVisibility(View.VISIBLE);

       } else if (cursor.getCount() == 2) {
           contact1.setVisibility(View.VISIBLE);
           contact2.setVisibility(View.VISIBLE);
           radioButton1.setVisibility(View.VISIBLE);
           radioButton2.setVisibility(View.VISIBLE);
       } else {
           contact1.setVisibility(View.VISIBLE);
           contact2.setVisibility(View.VISIBLE);
           contact3.setVisibility(View.VISIBLE);
           radioButton1.setVisibility(View.VISIBLE);
           radioButton2.setVisibility(View.VISIBLE);
           radioButton3.setVisibility(View.VISIBLE);
       }



    }

    //方法：按钮的单击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.phone_call_button:
                String number=tPhoneNumber;
                Intent intent= new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+number));
                if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(intent);
                break;
            case R.id.add_contact:
                startActivityForResult(new Intent("getContactInfoActivity"),1);
                break;
            default:
                break;
        }
    }

    // 获取通话记录
    private void getCallLogs(String phoneNumber){

        if(phoneNumber=="")
        {
            Toast.makeText(getActivity(), "Currently, there is no phone number ",
                    Toast.LENGTH_LONG).show();
        }
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy.MM.dd HH:mm");

        Cursor mCursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,null,null,null,null);

        if(mCursor.moveToLast()) {
            String prenumber = mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.NUMBER));
            Date predate = new Date(Long.parseLong(mCursor.getString(mCursor.getColumnIndexOrThrow(CallLog.Calls.DATE))));
            String pretime = sfd.format(predate);
            String preduration = mCursor.getString(mCursor.getColumnIndexOrThrow(CallLog.Calls.DURATION));

            sb = new StringBuilder();

            int i = 0;
            while(mCursor.moveToPrevious()){
                    String number = mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.NUMBER));
                    Date date = new Date(Long.parseLong(mCursor.getString(mCursor.getColumnIndexOrThrow(CallLog.Calls.DATE))));
                    String time = sfd.format(date);
                    String duration = mCursor.getString(mCursor.getColumnIndexOrThrow(CallLog.Calls.DURATION));
                    //判断电话是否为选定号码
                    // 由于字符串是对象类型，所以不能 简单的用“==” （双等号）判断两个字符串是否相等
                    if(i<5 & number.equals(phoneNumber)){
                            Log.d("fffff","---------");
                            sb.append("Phone Number: "+number);
                            sb.append(System.getProperty("line.separator"));
                            sb.append("Date: "+time);
                            sb.append(" Duration: "+duration);
                            sb.append(System.getProperty("line.separator"));
                            sb.append(System.getProperty("line.separator"));
                            i++;
                        }
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        displayThreePersons();
        //If a session was deleted, remake the listview, requerying for data (refresh)
        if (requestCode == 1) {
            if (resultCode == 1) {
                displayThreePersons();
            }
        }
    }




}