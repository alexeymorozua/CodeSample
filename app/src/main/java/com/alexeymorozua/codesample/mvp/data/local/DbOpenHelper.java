package com.alexeymorozua.codesample.mvp.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by john on 24.12.2016.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

  private static final String DB_NAME = "repository_db";

  public DbOpenHelper(Context context) {
    super(context, DB_NAME, null, 1);
  }

  @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(RepositoriesDetailTable.getCreateTableQuery());
  }

  @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }
}
