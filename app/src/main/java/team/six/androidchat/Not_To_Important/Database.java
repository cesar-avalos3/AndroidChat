package team.six.androidchat.Not_To_Important;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cesaravalos on 4/27/16.
 */
public class Database extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "androidChat.db";
    public static final String TABLE_NAME = "taburu";
    public static final String COLUMN_POST_ID = "postid";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_SESSION = "sesh";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    //Whenever we create, this will be called
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_POST_ID + " INTEGER, " + COLUMN_AUTHOR + " VARCHAR(255), " + COLUMN_TEXT + "VARCHAR(2048), " + COLUMN_SESSION + " INTEGER, PRIMARY KEY ("+COLUMN_POST_ID+","+COLUMN_SESSION+")");
    }

    public void onUpgrade(SQLiteDatabase db, int n, int y)
    {

    }

}
