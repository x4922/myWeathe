package com.myweathe.app.db;

import java.util.ArrayList;
import java.util.List;

import com.myweathe.app.model.Qu;
import com.myweathe.app.model.Sheng;
import com.myweathe.app.model.Shi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyWeatherDB {
	public static final String DB_NAME = "my_weather";
	public static final int VERSION = 1;
	private static MyWeatherDB myWeatherDB;
	private SQLiteDatabase db;
	private MyWeatherDB(Context context) {
		// TODO 自动生成的构造函数存根
		MyWeatherOpenHelper myWeatherOpenHelper=new MyWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db=myWeatherOpenHelper.getWritableDatabase();
	}
	/**
	 * 获取CoolWeatherDB的实例。
	 */
	public synchronized static MyWeatherDB getInstance(Context context) {
		if (myWeatherDB == null) {
			myWeatherDB = new MyWeatherDB(context);
		}
		return myWeatherDB;
	}
	//将实例储存到数据库
	public void saveSheng(Sheng sheng) {
		if(sheng!=null){
			ContentValues values=new ContentValues();
			values.put("sheng_name", sheng.getShengName());
			values.put("sheng_code", sheng.getShengCode());
			db.insertOrThrow("Sheng", null, values);
		}
	}
	public void saveShi(Shi shi) {
		if(shi!=null){
			ContentValues values=new ContentValues();
			values.put("shi_name", shi.getShiName());
			values.put("shi_code", shi.getShiCode());
			values.put("sheng_id", shi.getShengId());
			db.insertOrThrow("Shi", null, values);
		}
	}
	public void saveShi(Qu qu) {
		if(qu!=null){
			ContentValues values=new ContentValues();
			values.put("qu_name", qu.getQuName());
			values.put("qu_code", qu.getQuCode());
			values.put("shi_id", qu.getShiId());
			db.insertOrThrow("Qu", null, values);
		}
	}
	//读取实例信息
	public List<Sheng> loadSheng() {
		List<Sheng> list=new ArrayList<Sheng>();
		Cursor cursor=db.query("Sheng", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do {
				Sheng sheng=new Sheng();
				sheng.setShengId(cursor.getInt(cursor.getColumnIndex("id")));
				sheng.setShengName(cursor.getString(cursor.getColumnIndex("sheng_name")));
				sheng.setShengCode(cursor.getString(cursor.getColumnIndex("sheng_code")));
				list.add(sheng);
			} while (cursor.moveToNext());
		}
		return list;
	}
	public List<Shi> loadShi(int shengId) {
		List<Shi> list=new ArrayList<Shi>();
		Cursor cursor=db.query("Shi", null, "sheng_id=?",new String[]{String.valueOf(shengId)}, null, null, null);
		if(cursor.moveToFirst()){
			do {
				Shi shi=new Shi();
				shi.setShiId(cursor.getInt(cursor.getColumnIndex("id")));
				shi.setShiName(cursor.getString(cursor.getColumnIndex("shi_name")));
				shi.setShiCode(cursor.getString(cursor.getColumnIndex("shi_code")));
				shi.setShengId(shengId);
				list.add(shi);
			} while (cursor.moveToNext());
		}
		return list;		
	}
	public List<Qu> loadQu(int shiId) {
		List<Qu> list=new ArrayList<Qu>();
		Cursor cursor=db.query("Qu", null, "shi_id=?",new String[]{String.valueOf(shiId)}, null, null, null);
		if(cursor.moveToFirst()){
			do {
				Qu qu=new Qu();
				qu.setQuId(cursor.getInt(cursor.getColumnIndex("id")));
				qu.setQuName(cursor.getString(cursor.getColumnIndex("qu_name")));
				qu.setQuCode(cursor.getString(cursor.getColumnIndex("qu_code")));
				qu.setShiId(shiId);
				list.add(qu);
			} while (cursor.moveToNext());
		}
		return list;		
	}
}
