package com.example.healthapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HealthAppProvider extends ContentProvider {
    DBHelper dbHelper = null;

    private static final UriMatcher uriMatcher;

    //Creating the URI matcher to map codes
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(HealthAppContract.AUTHORITY, HealthAppContract.CONTACTPERSON_TABLE, 1);
        uriMatcher.addURI(HealthAppContract.AUTHORITY, HealthAppContract.CONTACTPERSON_TABLE + "/#", 2);

    }


    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(this.getContext(),"data14",null,1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)) {
            case 2:
                selection = HealthAppContract.CONTACTPERSON_ID + " = " + uri.getLastPathSegment();
            case 1:
                return db.query(HealthAppContract.CONTACTPERSON_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName;
        // given uri -> table name
        switch(uriMatcher.match(uri)) {
            case 1:
                tableName = HealthAppContract.CONTACTPERSON_TABLE;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + uriMatcher.match(uri));
        }
        long id = db.insert(tableName, null, contentValues);
        db.close();

        Uri nu = ContentUris.withAppendedId(uri, id);
        getContext().getContentResolver().notifyChange(nu, null);

        return nu;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName;
        int count;
        switch(uriMatcher.match(uri)) {
            case 2:
                selection = HealthAppContract.CONTACTPERSON_ID + " = " + uri.getLastPathSegment();
            case 1:
                tableName = HealthAppContract.CONTACTPERSON_TABLE;
                count = db.delete(tableName, selection, selectionArgs);
                break;
            default:
                return 0;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName;
        int count;

        // given uri -> table name
        switch(uriMatcher.match(uri)) {
            case 2:
                // gave /# URI so they want a specific row
                selection = HealthAppContract.CONTACTPERSON_ID + " = " + uri.getLastPathSegment();
            case 1:
                tableName = HealthAppContract.CONTACTPERSON_TABLE;
                count = db.update(tableName, contentValues, selection, selectionArgs);
                break;
            default:
                return 0;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        String contentType;

        if (uri.getLastPathSegment() == null) {
            contentType = HealthAppContract.CONTENT_TYPE_MULTIPLE;
        } else {
            contentType = HealthAppContract.CONTENT_TYPE_SINGLE;
        }

        return contentType;
    }
}
