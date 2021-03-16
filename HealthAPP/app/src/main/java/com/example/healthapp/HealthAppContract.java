package com.example.healthapp;
import android.net.Uri;

public class HealthAppContract {
    public static final String AUTHORITY = "com.example.healthapp";

    //Tables
    public static final String CONTACTPERSON_TABLE = "contactpersons";

    //activity table fields
    public static final String CONTACTPERSON_ID = "_id";
    public static final String CONTACTPERSON_LASTNAME = "lastname";
    public static final String CONTACTPERSON_FIRSTNAME = "firstname";
    public static final String CONTACTPERSON_PHONENUMBER = "phonenumber";
    public static final String CONTACTPERSON_EMAIL = "email";

    public static final String CONTENT_TYPE_SINGLE = "vnd.android.cursor.item/HealthAppProvider.data.text";
    public static final String CONTENT_TYPE_MULTIPLE = "vnd.android.cursor.dir/HealthAppProvider.data.text";

    //URIs
    public static final Uri ALL_URI = Uri.parse("content://"+AUTHORITY+"");
    public static final Uri CONTACTPERSON_URI = Uri.parse("content://" + AUTHORITY + "/" +  CONTACTPERSON_TABLE);








}
