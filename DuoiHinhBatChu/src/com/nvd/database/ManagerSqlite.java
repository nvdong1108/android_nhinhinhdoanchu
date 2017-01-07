package com.nvd.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nvd.objcauhoi.Cauhoi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class ManagerSqlite extends SQLiteOpenHelper {
	SQLiteDatabase database = null;
	private static final String name = "data_nhinhinhdoanchu.sqlite";
	private static final String path = "/data/data/com.nvd.duoihinhbatchu/databases/";
	Context mContex;

	public ManagerSqlite(Context context) {
		super(context, name, null, 1);
		this.mContex = context;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void opentdatabase() {
		if (!checkDataBase()) {
			try {
				copydatabase();
				String urn = path + name;
				database = SQLiteDatabase.openDatabase(urn, null,
						SQLiteDatabase.CREATE_IF_NECESSARY);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String urn = path + name;
			database = SQLiteDatabase.openDatabase(urn, null,
					SQLiteDatabase.CREATE_IF_NECESSARY);
		}

	}

	public void UpdateImageView(int img, int id) {
		String sql = "UPDATE cauhoi SET hinh = " + img + " WHERE id = " + id
				+ "";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}

	public Cauhoi getCauHoi(int id) {
		Cauhoi ch;
		Cursor c = null;
		c = database.query("cauhoi", null, "id=" + id + "", null, null, null,
				null);
		c.moveToNext();
		ch = new Cauhoi(c.getInt(0), c.getInt(1), c.getString(2), "",
				c.getString(4), "");
		c.close();
		return ch;
	}

	public boolean checkDataBase() {
		File dbFile = new File(path + name);
		Log.v("dbFile", dbFile + "   " + dbFile.exists());
		return dbFile.exists();
	}

	public void copydatabase() throws IOException {
		if (!checkDataBase()) {
			OutputStream myOutput = new FileOutputStream(path + name);
			byte[] buffer = new byte[1024];
			int length;
			InputStream myInput = mContex.getAssets().open(
					"data_nhinhinhdoanchu.sqlite");

			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myInput.close();
			myOutput.flush();
			myOutput.close();
			Toast.makeText(mContex, "*** Copy data thành công ***",
					Toast.LENGTH_LONG).show();
		} else
			Toast.makeText(mContex, "Khong Copy", Toast.LENGTH_LONG).show();

	}

}
