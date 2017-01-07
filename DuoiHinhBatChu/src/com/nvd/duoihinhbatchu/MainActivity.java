package com.nvd.duoihinhbatchu;

import java.io.IOException;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nvd.database.ManagerSqlite;
import com.nvd.duoihinhbatchu.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnStart;
	private Button btnInformation;
	private Button btnHuongDan;
	//
	private Button btn_on_volume;
	private Button btn_off_volume;
	private TextView btn_level;
	private Button btn_diem;
	private Boolean speak;
	//
	public MediaPlayer nhacMoGame;
	private int level;
	private int diem;
	private Dialog dialog;
	private InterstitialAd inters;
	AdView adview;
	// khai bao lop
	private ManagerSqlite managerdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//
		adview = (AdView) findViewById(R.id.adView);
		inters = new InterstitialAd(MainActivity.this);
		inters.setAdUnitId("ca-app-pub-1395380684132176/9741513842");
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adview.loadAd(adRequest);
		inters.loadAd(adRequest);
		// lần đầu tiền vào game
		//

		AnhXa();

		btnStart.setOnClickListener(this);
		btn_on_volume.setOnClickListener(this);
		btn_off_volume.setOnClickListener(this);
		btnInformation.setOnClickListener(this);
		btnHuongDan.setOnClickListener(this);

	}

	public void doCreatDb() {
		SQLiteDatabase database;
		database = openOrCreateDatabase("data_nhinhinhdoanchu.sqlite",
				MODE_PRIVATE, null);
	}

	public void deleteDb() {
		deleteDatabase("data_nhinhinhdoanchu.sqlite");
	}

	public void AnhXa() {
		btnStart = (Button) findViewById(R.id.btn_stargame);
		btnHuongDan = (Button) findViewById(R.id.btn_huongdan);

		btn_off_volume = (Button) findViewById(R.id.btn_off_Volume);
		btn_on_volume = (Button) findViewById(R.id.btn_on_Volume);

		btnInformation = (Button) findViewById(R.id.btn_information);
		btn_level = (TextView) findViewById(R.id.btn_lv_main);
		new MediaPlayer();
		nhacMoGame = MediaPlayer.create(this, R.raw.hello);
		/*
		 * check status volume on or off
		 */
		pre = getSharedPreferences("mydata", MODE_PRIVATE);
		level = pre.getInt("numberlevel", 1);
		btn_level.setText(level + "");

		if (pre.getBoolean("speak", true)) {
			btn_on_volume.setVisibility(View.VISIBLE);
			btn_off_volume.setVisibility(View.GONE);
			nhacMoGame.start();
		} else {
			btn_on_volume.setVisibility(View.GONE);
			btn_off_volume.setVisibility(View.VISIBLE);
		}
		if (pre.getBoolean("creat_database", true)) {
			doCreatDb();
			deleteDb();
			SharedPreferences.Editor edit = pre.edit();
			edit.putBoolean("creat_database", false);
			edit.putInt("numberDiem", 100);
			edit.commit();
		}

	}

	private SharedPreferences pre;

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn_stargame:
			Intent startgame = new Intent(MainActivity.this, GAME.class);
			startActivity(startgame);
			nhacMoGame.stop();
			break;
		case R.id.btn_on_Volume:
			nhacMoGame.pause();
			btn_on_volume.setVisibility(View.INVISIBLE);
			btn_off_volume.setVisibility(View.VISIBLE);
			SharedPreferences.Editor edit = pre.edit();
			edit.putBoolean("speak", false);
			edit.commit();
			break;
		case R.id.btn_off_Volume:
			nhacMoGame.start();
			btn_off_volume.setVisibility(View.INVISIBLE);
			btn_on_volume.setVisibility(View.VISIBLE);
			SharedPreferences.Editor edit2 = pre.edit();
			edit2.putBoolean("speak", true);
			edit2.commit();
			break;
		case R.id.btn_information:
			dialog = new Dialog(MainActivity.this, R.style.My_Dialog_Theme);
			dialog.setContentView(R.layout.information_admin);
			dialog.show();
			break;
		case R.id.btn_huongdan:
			dialog = new Dialog(MainActivity.this, R.style.My_Dialog_Theme);
			dialog.setContentView(R.layout.huongdan);
			dialog.show();

		default:
			break;
		}

	}

	@Override
	public void onBackPressed() {
		final Dialog dialog = new Dialog(this, R.style.My_Dialog_Theme);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setContentView(R.layout.dialog_back);
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		dialog.show();
		Button btb_co = (Button) dialog.findViewById(R.id.btb_co);
		Button btb_khong = (Button) dialog.findViewById(R.id.btb_khong);
		btb_co.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				nhacMoGame.stop();
				// Khoi tao lai Activity main
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
				// Tao su kien ket thuc app
				Intent startMain = new Intent(Intent.ACTION_MAIN);
				startMain.addCategory(Intent.CATEGORY_HOME);
				startActivity(startMain);
				finish();

			}
		});
		btb_khong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				dialog.dismiss();
			}
		});
	}

}
