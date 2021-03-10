package com.example.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.ParseException;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SittingPostureActivity extends AppCompatActivity {

    private Handler handler;
    private JSONArray jArray;
    private String result = null;
    private InputStream is = null;
    private StringBuilder sb;
    private TextView tv;
    private ImageView currentPosture;
    String isWrong;
    private String locationTime;
    TextView currentPostureTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitting_posture);

        Toolbar toolbar= findViewById(R.id.dailyMetric_toolbar);

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


//        tv = findViewById(R.id.editView);
        currentPosture = findViewById(R.id.current_posture);
        currentPostureTime=findViewById(R.id.posture_time);

        locationTime = getPostureTime();
        currentPostureTime.append(locationTime);

        postureThread thread=new postureThread();
        thread.start();

        if (isWrong=="0"){
            currentPosture.setImageResource(R.drawable.correct_posture);
        }else{
            currentPosture.setImageResource(R.drawable.wrong_posture);
        }
    }

    class postureThread extends Thread{
        @Override
        //从SDK3.0开始，google不再允许网络请求（HTTP、Socket）等相关操作直接在主线程中
        public void run() {

            try {
                HttpClient httpclient = new DefaultHttpClient();
                // Cleartext traffic not permitted
                HttpGet httpget = new HttpGet(
                        "http://192.168.125.4/data.php");      //ip地址不能设置为127.0.0.1， 手机上设置为电脑的ip地址，模拟器可以用android内置IP 10.0.2.2
                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            } catch (Exception e) {
                Log.e("con_tag", "Error in http connection" + e.toString());
            }
            // convert response to string
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                sb = new StringBuilder();
                sb.append(reader.readLine() + "\n");

                String line = "0";
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();
                Log.d("result_tag", result);    //获取的所有数据
            } catch (Exception e) {
                Log.e("con_tag", "Error converting result " + e.toString());
            }

            SittingPostureActivity.this.runOnUiThread(updateThread);
        }
    }

    Runnable updateThread = new Runnable()
    {

        @Override
        public void run()
        {
            // paring data
            String ct_name;
            try {

                jArray = new JSONArray(result);
                JSONObject json_data ;
                int i = jArray.length()-1;
                json_data = jArray.getJSONObject(i);
                ct_name = json_data.getString("id");  //将键值对取出来
                if(ct_name=="1"){
                    isWrong="0";
                }else{
                    isWrong="1";
                }
                Log.d("wrong", String.valueOf(isWrong));
//                tv.append(ct_name + " \n");

            } catch (JSONException e1) {

            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

    };

    private String getPostureTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }


}
