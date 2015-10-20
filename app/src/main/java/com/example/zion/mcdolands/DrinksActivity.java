package com.example.zion.mcdolands;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinksActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
//Get the drink from the intent
        int drinkNo;
        drinkNo = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);
//Create a cursor
        try {
            SQLiteOpenHelper mcdolandDatabaseHelper = new McDolandDatabaseHelper(this);
            SQLiteDatabase db = mcdolandDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)},
                    null, null, null);
            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
//Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
//Populate the drink name
                TextView name = (TextView)findViewById(R.id.drinknameTV);
                name.setText(nameText);
//Populate the drink description
                TextView description = (TextView)findViewById(R.id.drinkdescTV);
                description.setText(descriptionText);
//Populate the drink image
                ImageView photo = (ImageView)findViewById(R.id.picIV);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
