package com.example.healthapp.ui.emergencyCall;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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


    private SharedPreferences mSharedPreferences;

    public String tLastName;
    public String tFirstName;
    public String tPhoneNumber;
    public String tEmail;

    private StringBuilder sb;

    protected WeakReference<View> mRootView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


            emergencyCallViewModel =
                    new ViewModelProvider(this).get(EmergencyCallViewModel.class);
            View root = inflater.inflate(R.layout.fragment_emergency_call, container, false);

            mSharedPreferences = getActivity().getSharedPreferences("data",MODE_PRIVATE);

            //引用 toolbar
            Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.emergencyCall_toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            iv1 = root.findViewById(R.id.phone_call_button);
            btn1=root.findViewById(R.id.add_contact);
            contactList=root.findViewById(R.id.contact_list);

            iv1.setOnClickListener(this);
            btn1.setOnClickListener(this);
            contactList.setOnClickListener(this);

            try{
                tLastName= mSharedPreferences.getString("LastName","");
                tFirstName=mSharedPreferences.getString("FirstName","");
                tPhoneNumber=mSharedPreferences.getString("PhoneNumber","");
                tEmail=mSharedPreferences.getString("Email","");
                addView();

            }catch(Exception e){

            }

            getCallLogs(tPhoneNumber);
            TextView callLog = root.findViewById(R.id.callLog_history);
            Log.d("log",sb.toString());
            callLog.setText(sb.toString());

            return root;

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
                startActivity(new Intent("getContactInfoActivity"));
//                addView();
                break;
            default:
                break;
        }
    }

    private void addView(){
        View contactView = getLayoutInflater().inflate(R.layout.contact_templte,null,false);
        contactList.addView(contactView);

    }

    // 获取通话记录
    private void getCallLogs(String phoneNumber){
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
}