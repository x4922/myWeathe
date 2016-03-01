package com.myweathe.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyWeatherOpenHelper extends SQLiteOpenHelper {
	private String Create_sheng="create table Sheng ("
			+ "id integer primary key autoincrement,"
			+ "sheng_name text"
			+ "sheng_code text";
	private String Create_shi="create table Shi ("
			+ "id integer primary key autoincrement,"
			+ "shi_name text"
			+ "shi_code text"
			+ "sheng_id integer";
	private String Create_qu="create table Qu ("
			+ "id integer primary key autoincrement,"
			+ "qu_name text"
			+ "qu_code text"
			+ "shi_id integer";
	
	public MyWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		db.execSQL(Create_sheng);
		db.execSQL(Create_shi);
		db.execSQL(Create_qu);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
		
	}

}
