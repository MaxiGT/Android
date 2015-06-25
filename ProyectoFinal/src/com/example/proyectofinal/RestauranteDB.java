package com.example.proyectofinal;

import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RestauranteDB extends SQLiteOpenHelper {
	
	private RestauranteDB dbHelper;
	public static final String DB_NAME = "Restaurant.db3";
	public static final Integer DB_VERSION = 1;
	
	public static final String DB_CREATION_QUERY = "CREATE TABLE restaurante " + 
	"(id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
	"nombre VARCHAR(20) NOT NULL, " +
	"direccion VAR CHAR(20) NOT NULL, " +
	"observaciones VAR CHAR(20) NOT NULL, " +
	"foto VAR CHAR(20), " +
	"telefono VAR CHAR(20), " +
	"rate FLOAT NOT NULL)";
	
	public RestauranteDB(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATION_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
	

}
