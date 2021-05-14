package com.example.myfagments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabase<context> extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "postsDatabase";
    private static final int DATABASE_VERSION = 2;

    // Table Names
    private static final String TABLE_POSTS = "posts";

    // Post Table Columns
    private static final String KEY_POST_ID = "id";
    private static final String KEY_POST_USER_ID_FK = "userId";
    private static final String KEY_POST_TEXT = "text";
    private static final String TABLE_IMAGE = "contact_image";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
          String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_POSTS +
                "(" +
                KEY_POST_ID + " INTEGER PRIMARY KEY," +
                KEY_POST_USER_ID_FK + " TEXT, "  +
                KEY_POST_TEXT + " TEXT " +
                ")";
        db.execSQL(CREATE_POSTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_POSTS + " ADD COLUMN contact_image TEXT");
        }
    }

    public void Addcontact(String name, String description, byte []image){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_POST_USER_ID_FK,name);
        cv.put(KEY_POST_TEXT,description);
        cv.put(TABLE_IMAGE,image);
        System.out.println(image.length+"db\n\ndb\ndb\n");
        long res =db.insert(TABLE_POSTS, null, cv);
        if(res==-1)
            Toast.makeText(context,"OPERATION FAILED", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"contact ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

    }

    public Cursor ReadAllData(){
        String query="SELECT * FROM " + TABLE_POSTS;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null)
            cursor = db.rawQuery( "SELECT * FROM " + TABLE_POSTS +" ORDER BY " + KEY_POST_USER_ID_FK , null );
        return cursor;
    }

    public void update(String ID, String name, String description){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_POST_USER_ID_FK,name);
        cv.put(KEY_POST_TEXT,description);
        long res =db.update(TABLE_POSTS,cv, "contact_id=? ", new String[] {ID});
        if(res==-1)
            Toast.makeText(context,"OPERATION FAILED", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"contact UPDATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
    }

    public Integer deleteNote(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_POSTS,"ID = ?",new String[] {id});
    }

    public void deletAll() {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_POSTS, null, null);
    }
}
