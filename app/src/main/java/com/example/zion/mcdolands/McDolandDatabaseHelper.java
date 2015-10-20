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
    private static final int DB_VERSION = 2; // the version of the database

    McDolandDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE DRINKS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertDrink(db, "The McDoland Drink", "Made from Secret Ingredients crafted by Doland the Duck", R.drawable.cup);

            if (oldVersion < 2) {
                db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            }
        }
    }

    private static void insertDrink(SQLiteDatabase db, String name,
                                    String description, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("Drinks", null, drinkValues);
    }
}
