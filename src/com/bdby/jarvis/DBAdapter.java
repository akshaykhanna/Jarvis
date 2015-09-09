package com.bdby.jarvis;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBAdapter 
{
 static final String KEY_ID="_id";
 static final String KEY_NAME="app_name";
 static final String KEY_PACKAGE="app_package";
 static final String KEY_COUNTER="app_counter";
 static final String DB_NAME="Akshay";
 static final String DB_TABLE ="contacts";
 static final int DB_VERSION = 1;
 static final String TAG = "DBAdapter";
 
 static final String DB_CREATE = "create table contacts (_id integer primary key autoincrement, "
		 + "app_name text not null, app_package text not null,app_counter integer default 0);";
 final Context context;
 SQLiteDatabase db;
 final DBOpenHelper dbh;
 
 public DBAdapter (Context c)
 
 {
	 this.context=c;
	 dbh=new DBOpenHelper(c);
 }
 
 private static class DBOpenHelper extends SQLiteOpenHelper
 {
  Context c;
	public DBOpenHelper(Context context) 
	{
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
		c=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try
		{
			db.execSQL(DB_CREATE);
		}
		catch (Exception e)
		{
			Toast.makeText(c, "Failed to create DB", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
				db.execSQL("DROP TABLE IF EXISTS contacts");
				onCreate(db);
	}
	
	
	 
 }
 
//---opens the database---
  public DBAdapter open() throws SQLException
{
db = dbh.getWritableDatabase();
return this;
}
//---closes the database---
public void close()
{
dbh.close();
}
//---
public long insertContact(String app_name, String app_package,String Counter)
{
ContentValues initialValues = new ContentValues();
initialValues.put(KEY_NAME, app_name);
initialValues.put(KEY_PACKAGE, app_package);
initialValues.put(KEY_COUNTER, Counter);
return db.insert(DB_TABLE, null, initialValues);
}
//---deletes a particular contact---
public boolean deleteContact(long rowId)
{
return db.delete(DB_TABLE, KEY_ID + "=" + rowId, null) > 0;
}
//---retrieves all the contacts---
public Cursor getAllContacts()
{
return db.query(DB_TABLE, new String[] {KEY_ID, KEY_NAME,KEY_PACKAGE,KEY_COUNTER}, null, null, null, null, null);
}
//---retrieves a particular contact---
public Cursor getContact(long rowId) throws SQLException
{

Cursor mCursor =db.query(true, DB_TABLE, new String[] {KEY_ID,KEY_NAME,KEY_PACKAGE,KEY_COUNTER}, KEY_ID + "=" + rowId, null,null, null, null, null);
if (mCursor != null) {
mCursor.moveToFirst();
}
return mCursor;
}
//---updates a contact---
public boolean updateContact(long rowId, String name, String email)
{
ContentValues args = new ContentValues();
args.put(KEY_NAME, name);
args.put(KEY_PACKAGE, email);
return db.update(DB_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
}

}
