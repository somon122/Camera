package com.example.shariful.camera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MydbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="image.db";
    private static final String TABLE_NAME="image_table";
    private static final String COLM1="id";
    private static final String COLM2="image_name";
    private static final int VERSION=1;
    private Context context;
    private static  final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+COLM1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLM2+" TEXT);";

    SQLiteDatabase sqLiteDatabase;

    public MydbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Toast.makeText(context, "OnCreate Is Called", Toast.LENGTH_SHORT).show();
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(ImageMethod image)
    {

        sqLiteDatabase =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLM2,image.getImage());
        long isImage = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

        if (isImage>0){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<ImageMethod> GetImage (){

        ArrayList<ImageMethod>imageList = new ArrayList<>();
        sqLiteDatabase = this.getReadableDatabase();

        String quary = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(quary,null);

        if (cursor.moveToFirst()){
            do {

                int id  = cursor.getInt(cursor.getColumnIndex(COLM1));
                String image = cursor.getString(cursor.getColumnIndex(COLM2));
                ImageMethod imageMethod = new ImageMethod(id,image);

                imageList.add(imageMethod);

            }while (cursor.moveToNext());

            sqLiteDatabase.close();

        }
        return imageList;

    }


}
