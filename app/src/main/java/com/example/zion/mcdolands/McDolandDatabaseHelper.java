package com.example.zion.mcdolands;

/**
 * Created by Zion on 10/20/2015.
 */
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class McDolandDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mcdolands"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    McDolandDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE DRINK ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");

        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", "The McDoland Drink");
        drinkValues.put("DESCRIPTION", "Contains Secret Ingredients made by Doland the Duck");
        drinkValues.put("IMAGE_RESOURCE_ID", R.drawable.cup);

        db.insert("DRINK", null, drinkValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private static void insertDrink(SQLiteDatabase db, String name,
                                    String description, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK", null, drinkValues);
    }
}

