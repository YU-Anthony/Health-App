package com.example.healthapp;
import android.net.Uri;

public class HealthAppContract {
    public static final String AUTHORITY = "com.example.healthapp";

    //Tables
    public static final String CONTACTPERSON_TABLE = "contactpersons";
    public static final String SITTINGPOSTURE_TABLE = "sittingpostures";

    public static final String CONTACTPERSON_ID = "_id";
    public static final String CONTACTPERSON_LASTNAME = "lastname";
    public static final String CONTACTPERSON_FIRSTNAME = "firstname";
    public static final String CONTACTPERSON_PHONENUMBER = "phonenumber";
    public static final String CONTACTPERSON_EMAIL = "email";

    public static final String SITTINGPOSTURE_ID = "_id";
    public static final String SITTINGPOSTURE_ISSITTINGRIGHT = "issittingright";
    public static final String SITTINGPOSTURE_HEALTHSCORE = "healthscore";
    public static final String SITTINGPOSTURE_SLOWRISING = "slowrising";
    public static final String SITTINGPOSTURE_ISSITTING = "issitting";
    public static final String SITTINGPOSTURE_ISLRBALANCED = "islrbalanced";
    public static final String SITTINGPOSTURE_ISAPBALANCED = "isapbalanced";


    public static final String CONTENT_TYPE_SINGLE = "vnd.android.cursor.item/HealthAppProvider.data.text";
    public static final String CONTENT_TYPE_MULTIPLE = "vnd.android.cursor.dir/HealthAppProvider.data.text";

    //URIs
    public static final Uri ALL_URI = Uri.parse("content://"+AUTHORITY+"");
    public static final Uri CONTACTPERSON_URI = Uri.parse("content://" + AUTHORITY + "/" +  CONTACTPERSON_TABLE);
    public static final Uri SITTINGPOSTURE_URI = Uri.parse("content://" + AUTHORITY + "/" +  SITTINGPOSTURE_TABLE);




}
