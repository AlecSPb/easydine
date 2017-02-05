package com.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Gaurav on 15-07-2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "EasyDine2";

    private static final int DATA_BASE_VERSION = 18;

    SQLiteDatabase db;

    Cursor cursor;

    //table name
    public static final String CUSTOMER_TABLE = "cust_tab";


    //column name of cust_tab
    public static final String C_ID = "_id";
    public static final String C_NAME = "cust_name";
    public static final String C_PHONE = "cust_phone";
    public static final String C_EMAIL = "cust_email";
    public static final String AMOUNT = "amt";
    //public static final String LIST = "order";

    String CREATE_C_TABLE = "CREATE TABLE " + CUSTOMER_TABLE + "("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME + " TEXT NOT NULL,"
            + C_PHONE + " TEXT,"
            + C_EMAIL + " TEXT,"
            + AMOUNT + " INTEGER );";

    public DBHandler(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_C_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
        onCreate(db);
    }

    public void addCustomer(ModelClass myClass) {

        ContentValues cv = new ContentValues();
        cv.put(C_NAME, myClass.getName());
        cv.put(C_PHONE, myClass.getPhone());
        cv.put(C_EMAIL, myClass.getEmail());

        //insert row
        db = this.getWritableDatabase();
        db.insert(CUSTOMER_TABLE, null, cv);
        db.close();

    }

    public void addOrder(ModelClass myClass) {

        ContentValues cv = new ContentValues();
        cv.put(AMOUNT, myClass.getAmount());

        String arry = String.valueOf(myClass.getAl());
        Log.e("achintyaorder", String.format("%s", arry));
        //cv.put(LIST, String.valueOf(myClass.getAl()));

        //insert row
        db = this.getWritableDatabase();
        db.insert(CUSTOMER_TABLE, null, cv);
        db.close();

    }

    public boolean validateUser(String userName, String mphone, String memail) {
         db=getReadableDatabase();
        db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + CUSTOMER_TABLE + " WHERE "
                + C_NAME + "='" + userName + "'AND " + C_PHONE + "='" + mphone + "'AND " + C_EMAIL + "='" + memail + "'", null);
        if (cursor.getCount() >= 0)
            return true;
        else
            return false;
    }

    public Integer getAmount(String userName, String mphone, String memail) {
         db=getReadableDatabase();
        db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT "+ AMOUNT +" FROM " + CUSTOMER_TABLE + " WHERE "
                + C_NAME + "='" + userName + "'AND " + C_PHONE + "='" + mphone + "'AND " + C_EMAIL + "='" + memail + "'", null);
        if (cursor.getCount() > 0){
            int amnt = Integer.parseInt(String.valueOf(cursor.getColumnIndex(AMOUNT)));
            return amnt;}
        else {return 4;}
    }


}
