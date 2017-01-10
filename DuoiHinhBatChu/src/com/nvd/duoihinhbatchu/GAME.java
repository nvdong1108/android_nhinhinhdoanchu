package com.nvd.duoihinhbatchu;

import java.util.ArrayList;
import java.util.Random;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nvd.database.ManagerSqlite;
import com.nvd.duoihinhbatchu.R.layout;
import com.nvd.objcauhoi.Cauhoi;

import android.app.Activity;

import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;

public class GAME extends Activity implements OnClickListener {
	// gọi lớp
	private ManagerSqlite managersqllite;

	private int size_ChuDaChon;

	private Cauhoi CAUHOI;
	// khai bao da tien trinh
	private Handler handler;
	private Runmusic runmusics;

	//
	private ArrayList<String> dsOChuDapAn;
	public static int level;
	public static int diem;
	public TextView btn_numberlevel;
	public TextView btn_diem;
	private Typeface face;
	private ImageView home;
	private ImageView ic_audio_off;
	private ImageView ic_audio_on;
	private SharedPreferences pre;
	//
	public ImageView img_cauhoi;
	private ImageView icon_help;
	//
	public Button btn_okq_1;
	public Button btn_okq_2;
	public Button btn_okq_3;
	public Button btn_okq_4;
	public Button btn_okq_5;
	public Button btn_okq_6;
	public Button btn_okq_7;
	public Button btn_okq_8;
	public Button btn_okq_9;
	public Button btn_okq_10;
	public Button btn_okq_11;
	public Button btn_okq_12;
	public Button btn_okq_13;
	public Button btn_okq_14;
	//
	public Button btn_ogy_1;
	public Button btn_ogy_2;
	public Button btn_ogy_3;
	public Button btn_ogy_4;
	public Button btn_ogy_5;
	public Button btn_ogy_6;
	public Button btn_ogy_7;
	public Button btn_ogy_8;
	public Button btn_ogy_9;
	public Button btn_ogy_10;
	public Button btn_ogy_11;
	public Button btn_ogy_12;
	public Button btn_ogy_13;
	public Button btn_ogy_14;
	//
	private Dialog dialog;
	private TextView btn_oke_dialog_help;

	private int number_help;
	public MediaPlayer nhacnen;
	public MediaPlayer nhacchienthang;
	// quang cáo
	private InterstitialAd inters;
	AdView adview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		// Quảng cáo
		adview = (AdView) findViewById(R.id.adView);
		inters = new InterstitialAd(GAME.this);
		inters.setAdUnitId("ca-app-pub-1395380684132176/9741513842");
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adview.loadAd(adRequest);
		inters.loadAd(adRequest);
		// khai báo lớp manager

		// Khai báo nhạc nền
		new MediaPlayer();
		nhacnen = MediaPlayer.create(this, R.raw.nhactronggame);
		// vào hàm ánh xạ
		AnhXa();

		if (pre.getBoolean("welcome", true)) {
			SharedPreferences.Editor edit = pre.edit();
			edit.putBoolean("welcome", false);
			showwelcome();
			edit.commit();
		}

		// bắt đầu game với câu đang chơi
		// GetLevel(level);

		/*
		 * sự kiện trên thạnh công cụng trên cùng home , âm thanh , trợ giúp
		 */
		icon_help.setOnClickListener(this);
		home.setOnClickListener(this);
		ic_audio_off.setOnClickListener(this);
		ic_audio_on.setOnClickListener(this);
		// bắt sự kiện
		btn_ogy_1.setOnClickListener(this);
		btn_ogy_2.setOnClickListener(this);
		btn_ogy_3.setOnClickListener(this);
		btn_ogy_4.setOnClickListener(this);
		btn_ogy_5.setOnClickListener(this);
		btn_ogy_6.setOnClickListener(this);
		btn_ogy_7.setOnClickListener(this);
		btn_ogy_8.setOnClickListener(this);
		btn_ogy_9.setOnClickListener(this);
		btn_ogy_10.setOnClickListener(this);
		btn_ogy_11.setOnClickListener(this);
		btn_ogy_12.setOnClickListener(this);
		btn_ogy_13.setOnClickListener(this);
		btn_ogy_14.setOnClickListener(this);
		//
		btn_okq_1.setOnClickListener(this);
		btn_okq_2.setOnClickListener(this);
		btn_okq_3.setOnClickListener(this);
		btn_okq_4.setOnClickListener(this);
		btn_okq_5.setOnClickListener(this);
		btn_okq_6.setOnClickListener(this);
		btn_okq_7.setOnClickListener(this);
		btn_okq_8.setOnClickListener(this);
		btn_okq_9.setOnClickListener(this);
		btn_okq_10.setOnClickListener(this);
		btn_okq_11.setOnClickListener(this);
		btn_okq_12.setOnClickListener(this);
		btn_okq_13.setOnClickListener(this);
		btn_okq_14.setOnClickListener(this);
		//
		registerForContextMenu(icon_help);
		showgame(level);
	}

	// lop tao da tien trinh chay nha khi ket thuc
	public class Runmusic implements Runnable {

		@Override
		public void run() {
			nhacnen.start();
			handler.postDelayed(runmusics, 1);
		}

	}

	private void showwelcome() {
		final Dialog dl;
		dl = new Dialog(GAME.this, R.style.My_Dialog_Theme);
		dl.setContentView(R.layout.gioithieu);
		dl.getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		dl.setCanceledOnTouchOutside(false);
		dl.setCancelable(false);
		dl.show();
		Button btndong = (Button) dl.findViewById(R.id.btn_dong_dialog_welcome);
		btndong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dl.dismiss();

			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		pre = getSharedPreferences("mydata", MODE_PRIVATE);
		SharedPreferences.Editor edit = pre.edit();
		edit.putInt("numberlevel", level);
		edit.putInt("numberDiem", diem);
		edit.putInt("num_help", number_help);
		edit.commit();
	}

	@Override
	protected void onStop() {

		super.onStop();
		pre = getSharedPreferences("mydata", MODE_PRIVATE);
		SharedPreferences.Editor edit = pre.edit();
		edit.putInt("numberlevel", level);
		edit.putInt("numberDiem", diem);
		edit.putInt("num_help", number_help);
		edit.commit();
	}

	/*
	 * 
	 */
	public boolean AnhXa() {
		// lớp runmusic
		runmusics = new Runmusic();
		handler = new Handler();
		// ẢNH
		pre = getSharedPreferences("mydata", MODE_PRIVATE);
		//
		img_cauhoi = (ImageView) findViewById(R.id.img_cauhoi);
		ic_audio_off = (ImageView) findViewById(R.id.icon_audio_off);
		ic_audio_on = (ImageView) findViewById(R.id.icon_audio_on);
		//
		btn_numberlevel = (TextView) findViewById(R.id.btn_level);
		btn_diem = (TextView) findViewById(R.id.btn_diem);
		// Himh Ảnh
		icon_help = (ImageView) findViewById(R.id.icon_help);
		home = (ImageView) findViewById(R.id.icon_home);
		// Ô CHỮ
		btn_okq_1 = (Button) findViewById(R.id.btn_okq_1);
		btn_okq_2 = (Button) findViewById(R.id.btn_okq_2);
		btn_okq_3 = (Button) findViewById(R.id.btn_okq_3);
		btn_okq_4 = (Button) findViewById(R.id.btn_okq_4);
		btn_okq_5 = (Button) findViewById(R.id.btn_okq_5);
		btn_okq_6 = (Button) findViewById(R.id.btn_okq_6);
		btn_okq_7 = (Button) findViewById(R.id.btn_okq_7);
		btn_okq_8 = (Button) findViewById(R.id.btn_okq_8);
		btn_okq_9 = (Button) findViewById(R.id.btn_okq_9);
		btn_okq_10 = (Button) findViewById(R.id.btn_okq_10);
		btn_okq_11 = (Button) findViewById(R.id.btn_okq_11);
		btn_okq_12 = (Button) findViewById(R.id.btn_okq_12);
		btn_okq_13 = (Button) findViewById(R.id.btn_okq_13);
		btn_okq_14 = (Button) findViewById(R.id.btn_okq_14);

		btn_ogy_1 = (Button) findViewById(R.id.btn_ogy_1);
		btn_ogy_2 = (Button) findViewById(R.id.btn_ogy_2);
		btn_ogy_3 = (Button) findViewById(R.id.btn_ogy_3);
		btn_ogy_4 = (Button) findViewById(R.id.btn_ogy_4);
		btn_ogy_5 = (Button) findViewById(R.id.btn_ogy_5);
		btn_ogy_6 = (Button) findViewById(R.id.btn_ogy_6);
		btn_ogy_7 = (Button) findViewById(R.id.btn_ogy_7);
		btn_ogy_8 = (Button) findViewById(R.id.btn_ogy_8);
		btn_ogy_9 = (Button) findViewById(R.id.btn_ogy_9);
		btn_ogy_10 = (Button) findViewById(R.id.btn_ogy_10);
		btn_ogy_11 = (Button) findViewById(R.id.btn_ogy_11);
		btn_ogy_12 = (Button) findViewById(R.id.btn_ogy_12);
		btn_ogy_13 = (Button) findViewById(R.id.btn_ogy_13);
		btn_ogy_14 = (Button) findViewById(R.id.btn_ogy_14);
		//
		// tạo forn chữ
		face = Typeface.createFromAsset(getAssets(), "font/ARIBLK.TTF");
		//
		btn_ogy_1.setTypeface(face);
		btn_ogy_2.setTypeface(face);
		btn_ogy_3.setTypeface(face);
		btn_ogy_4.setTypeface(face);
		btn_ogy_5.setTypeface(face);
		btn_ogy_6.setTypeface(face);
		btn_ogy_7.setTypeface(face);
		btn_ogy_8.setTypeface(face);
		btn_ogy_9.setTypeface(face);
		btn_ogy_10.setTypeface(face);
		btn_ogy_11.setTypeface(face);
		btn_ogy_12.setTypeface(face);
		btn_ogy_13.setTypeface(face);
		btn_ogy_14.setTypeface(face);
		//
		btn_okq_1.setTextColor(Color.parseColor("#009900"));
		btn_okq_2.setTextColor(Color.parseColor("#009900"));
		btn_okq_3.setTextColor(Color.parseColor("#009900"));
		btn_okq_4.setTextColor(Color.parseColor("#009900"));
		btn_okq_5.setTextColor(Color.parseColor("#009900"));
		btn_okq_6.setTextColor(Color.parseColor("#009900"));
		btn_okq_7.setTextColor(Color.parseColor("#009900"));
		btn_okq_8.setTextColor(Color.parseColor("#009900"));
		btn_okq_9.setTextColor(Color.parseColor("#009900"));
		btn_okq_10.setTextColor(Color.parseColor("#009900"));
		btn_okq_11.setTextColor(Color.parseColor("#009900"));
		btn_okq_12.setTextColor(Color.parseColor("#009900"));
		btn_okq_13.setTextColor(Color.parseColor("#009900"));
		btn_okq_14.setTextColor(Color.parseColor("#009900"));
		//
		btn_okq_1.setTypeface(face);
		btn_okq_2.setTypeface(face);
		btn_okq_3.setTypeface(face);
		btn_okq_4.setTypeface(face);
		btn_okq_5.setTypeface(face);
		btn_okq_6.setTypeface(face);
		btn_okq_7.setTypeface(face);
		btn_okq_8.setTypeface(face);
		btn_okq_9.setTypeface(face);
		btn_okq_10.setTypeface(face);
		btn_okq_11.setTypeface(face);
		btn_okq_12.setTypeface(face);
		btn_okq_13.setTypeface(face);
		btn_okq_14.setTypeface(face);
		//
		/*
		 * check speak on or off
		 */

		return true;
	}

	public void Click_help() {

		char[] M = CAUHOI.getDapan().toCharArray();

		switch (number_help) {
		case 1:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			break;
		case 2:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			//
			btn_okq_2.setText(M[1] + "");
			btn_okq_2.setOnClickListener(null);
			btn_okq_2.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[1] + "");
			break;
		case 3:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			//
			btn_okq_2.setText(M[1] + "");
			btn_okq_2.setOnClickListener(null);
			btn_okq_2.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[1] + "");
			//
			btn_okq_3.setText(M[2] + "");
			btn_okq_3.setOnClickListener(null);
			btn_okq_3.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[2] + "");

			break;
		case 4:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			//
			btn_okq_2.setText(M[1] + "");
			btn_okq_2.setOnClickListener(null);
			btn_okq_2.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[1] + "");
			//
			btn_okq_3.setText(M[2] + "");
			btn_okq_3.setOnClickListener(null);
			btn_okq_3.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[2] + "");
			//
			btn_okq_4.setText(M[3] + "");
			btn_okq_4.setOnClickListener(null);
			btn_okq_4.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[3] + "");
			break;
		case 5:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			//
			btn_okq_2.setText(M[1] + "");
			btn_okq_2.setOnClickListener(null);
			btn_okq_2.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[1] + "");
			//
			btn_okq_3.setText(M[2] + "");
			btn_okq_3.setOnClickListener(null);
			btn_okq_3.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[2] + "");
			//
			btn_okq_4.setText(M[3] + "");
			btn_okq_4.setOnClickListener(null);
			btn_okq_4.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[3] + "");
			//
			btn_okq_5.setText(M[4] + "");
			btn_okq_5.setOnClickListener(null);
			btn_okq_5.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[4] + "");
			break;
		case 6:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			//
			btn_okq_2.setText(M[1] + "");
			btn_okq_2.setOnClickListener(null);
			btn_okq_2.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[1] + "");
			//
			btn_okq_3.setText(M[2] + "");
			btn_okq_3.setOnClickListener(null);
			btn_okq_3.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[2] + "");
			//
			btn_okq_4.setText(M[3] + "");
			btn_okq_4.setOnClickListener(null);
			btn_okq_4.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[3] + "");
			//
			btn_okq_5.setText(M[4] + "");
			btn_okq_5.setOnClickListener(null);
			btn_okq_5.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[4] + "");
			//
			btn_okq_6.setText(M[5] + "");
			btn_okq_6.setOnClickListener(null);
			btn_okq_6.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[5] + "");
			break;
		case 7:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			//
			btn_okq_2.setText(M[1] + "");
			btn_okq_2.setOnClickListener(null);
			btn_okq_2.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[1] + "");
			//
			btn_okq_3.setText(M[2] + "");
			btn_okq_3.setOnClickListener(null);
			btn_okq_3.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[2] + "");
			//
			btn_okq_4.setText(M[3] + "");
			btn_okq_4.setOnClickListener(null);
			btn_okq_4.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[3] + "");
			//
			btn_okq_5.setText(M[4] + "");
			btn_okq_5.setOnClickListener(null);
			btn_okq_5.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[4] + "");
			//
			btn_okq_6.setText(M[5] + "");
			btn_okq_6.setOnClickListener(null);
			btn_okq_6.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[5] + "");
			//
			btn_okq_7.setText(M[6] + "");
			btn_okq_7.setOnClickListener(null);
			btn_okq_7.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[6] + "");
			break;
		case 8:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			//
			btn_okq_2.setText(M[1] + "");
			btn_okq_2.setOnClickListener(null);
			btn_okq_2.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[1] + "");
			//
			btn_okq_3.setText(M[2] + "");
			btn_okq_3.setOnClickListener(null);
			btn_okq_3.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[2] + "");
			//
			btn_okq_4.setText(M[3] + "");
			btn_okq_4.setOnClickListener(null);
			btn_okq_4.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[3] + "");
			//
			btn_okq_5.setText(M[4] + "");
			btn_okq_5.setOnClickListener(null);
			btn_okq_5.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[4] + "");
			//
			btn_okq_6.setText(M[5] + "");
			btn_okq_6.setOnClickListener(null);
			btn_okq_6.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[5] + "");
			//
			btn_okq_7.setText(M[6] + "");
			btn_okq_7.setOnClickListener(null);
			btn_okq_7.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[6] + "");
			//
			btn_okq_8.setText(M[7] + "");
			btn_okq_8.setOnClickListener(null);
			btn_okq_8.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[7] + "");
			//

			break;
		case 9:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			//
			btn_okq_2.setText(M[1] + "");
			btn_okq_2.setOnClickListener(null);
			btn_okq_2.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[1] + "");
			//
			btn_okq_3.setText(M[2] + "");
			btn_okq_3.setOnClickListener(null);
			btn_okq_3.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[2] + "");
			//
			btn_okq_4.setText(M[3] + "");
			btn_okq_4.setOnClickListener(null);
			btn_okq_4.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[3] + "");
			//
			btn_okq_5.setText(M[4] + "");
			btn_okq_5.setOnClickListener(null);
			btn_okq_5.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[4] + "");
			//
			btn_okq_6.setText(M[5] + "");
			btn_okq_6.setOnClickListener(null);
			btn_okq_6.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[5] + "");
			//
			btn_okq_7.setText(M[6] + "");
			btn_okq_7.setOnClickListener(null);
			btn_okq_7.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[6] + "");
			//
			btn_okq_8.setText(M[7] + "");
			btn_okq_8.setOnClickListener(null);
			btn_okq_8.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[7] + "");
			//
			btn_okq_9.setText(M[8] + "");
			btn_okq_9.setOnClickListener(null);
			btn_okq_9.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[8] + "");
			break;
		case 10:
			btn_okq_1.setText(M[0] + "");
			btn_okq_1.setOnClickListener(null);
			btn_okq_1.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[0] + "");
			//
			btn_okq_2.setText(M[1] + "");
			btn_okq_2.setOnClickListener(null);
			btn_okq_2.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[1] + "");
			//
			btn_okq_3.setText(M[2] + "");
			btn_okq_3.setOnClickListener(null);
			btn_okq_3.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[2] + "");
			//
			btn_okq_4.setText(M[3] + "");
			btn_okq_4.setOnClickListener(null);
			btn_okq_4.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[3] + "");
			//
			btn_okq_5.setText(M[4] + "");
			btn_okq_5.setOnClickListener(null);
			btn_okq_5.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[4] + "");
			//
			btn_okq_6.setText(M[5] + "");
			btn_okq_6.setOnClickListener(null);
			btn_okq_6.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[5] + "");
			//
			btn_okq_7.setText(M[6] + "");
			btn_okq_7.setOnClickListener(null);
			btn_okq_7.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[6] + "");
			//
			btn_okq_8.setText(M[7] + "");
			btn_okq_8.setOnClickListener(null);
			btn_okq_8.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[7] + "");
			//
			btn_okq_9.setText(M[8] + "");
			btn_okq_9.setOnClickListener(null);
			btn_okq_9.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[8] + "");
			//
			btn_okq_10.setText(M[9] + "");
			btn_okq_10.setOnClickListener(null);
			btn_okq_10.setTextColor(Color.parseColor("#FFFF00"));
			XoaButton_help(M[9] + "");
			break;
		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {

		if (v == icon_help) {
			// click vaof icon help

			if (diem < 20) {
				Toast.makeText(getApplicationContext(),
						"Bạn cần có 20 điểm để được trợ giúp nhé !!! ",
						Toast.LENGTH_LONG).show();
			} else {

				dialog = new Dialog(GAME.this, R.style.My_Dialog_Theme);
				dialog.setContentView(R.layout.help);
				dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT);
				dialog.setCanceledOnTouchOutside(false);
				dialog.setCancelable(false);
				dialog.show();
				btn_oke_dialog_help = (TextView) dialog
						.findViewById(R.id.ic_oke_dialog_help);
				Button btn_dong = (Button) dialog
						.findViewById(R.id.ic_no_dialog_help);
				btn_dong.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						dialog.dismiss();
					}
				});

				btn_oke_dialog_help
						.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								//
								HienOGoiy(CAUHOI.getGoiy());
								HienButtonODapAn(CAUHOI.getDapan().length());
								number_help++;
								Click_help();
								size_ChuDaChon = CAUHOI.getDapan().length()
										- number_help;
								if (size_ChuDaChon < 1)
									KiemTraDapAn();

								dialog.dismiss();
								diem = diem - 20;
								btn_diem.setText(diem + "");
								//
								SharedPreferences.Editor edit = pre.edit();
								edit.putInt("num_help", number_help);
								edit.commit();
							}
						});

			}

		} else if (v == home) {
			// click vao icon home
			nhacnen.stop();
			Intent home = new Intent(this, MainActivity.class);
			startActivity(home);
		}
		if (v == ic_audio_on) {
			ic_audio_on.setVisibility(View.GONE);
			ic_audio_off.setVisibility(View.VISIBLE);
			nhacnen.pause();
			handler.removeCallbacks(runmusics);
			SharedPreferences.Editor edit = pre.edit();
			edit.putBoolean("speak", false);
			edit.commit();

		}
		if (v == ic_audio_off) {
			ic_audio_on.setVisibility(View.VISIBLE);
			ic_audio_off.setVisibility(View.GONE);
			nhacnen.start();
			handler.postDelayed(runmusics, 7000);
			SharedPreferences.Editor edit = pre.edit();
			edit.putBoolean("speak", true);
			edit.commit();
		}
		//
		if (v == btn_ogy_1 && GhiKetQua(btn_ogy_1.getText().toString())) {
			btn_ogy_1.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_2 && GhiKetQua(btn_ogy_2.getText().toString())) {
			btn_ogy_2.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_3 && GhiKetQua(btn_ogy_3.getText().toString())) {
			btn_ogy_3.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_4 && GhiKetQua(btn_ogy_4.getText().toString())) {
			btn_ogy_4.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_5 && GhiKetQua(btn_ogy_5.getText().toString())) {
			btn_ogy_5.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_6 && GhiKetQua(btn_ogy_6.getText().toString())) {
			btn_ogy_6.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_7 && GhiKetQua(btn_ogy_7.getText().toString())) {
			btn_ogy_7.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_8 && GhiKetQua(btn_ogy_8.getText().toString())) {
			btn_ogy_8.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_9 && GhiKetQua(btn_ogy_9.getText().toString())) {
			btn_ogy_9.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_10
				&& GhiKetQua(btn_ogy_10.getText().toString())) {
			btn_ogy_10.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_11
				&& GhiKetQua(btn_ogy_11.getText().toString())) {
			btn_ogy_11.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_12
				&& GhiKetQua(btn_ogy_12.getText().toString())) {
			btn_ogy_12.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_13
				&& GhiKetQua(btn_ogy_13.getText().toString())) {
			btn_ogy_13.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		} else if (v == btn_ogy_14
				&& GhiKetQua(btn_ogy_14.getText().toString())) {
			btn_ogy_14.setVisibility(View.INVISIBLE);
			size_ChuDaChon--;
			if (size_ChuDaChon < 1)
				KiemTraDapAn();
		}
		//
		//
		else if (v == btn_okq_1 && XoaOChu(btn_okq_1.getText().toString())) {
			btn_okq_1.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_2 && XoaOChu(btn_okq_2.getText().toString())) {
			btn_okq_2.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_3 && XoaOChu(btn_okq_3.getText().toString())) {
			btn_okq_3.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_4 && XoaOChu(btn_okq_4.getText().toString())) {
			btn_okq_4.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_5 && XoaOChu(btn_okq_5.getText().toString())) {
			btn_okq_5.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_6 && XoaOChu(btn_okq_6.getText().toString())) {
			btn_okq_6.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_7 && XoaOChu(btn_okq_7.getText().toString())) {
			btn_okq_7.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_8 && XoaOChu(btn_okq_8.getText().toString())) {
			btn_okq_8.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_9 && XoaOChu(btn_okq_9.getText().toString())) {
			btn_okq_9.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_10 && XoaOChu(btn_okq_10.getText().toString())) {
			btn_okq_10.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_11 && XoaOChu(btn_okq_11.getText().toString())) {
			btn_okq_11.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_12 && XoaOChu(btn_okq_12.getText().toString())) {
			btn_okq_12.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_13 && XoaOChu(btn_okq_13.getText().toString())) {
			btn_okq_13.setText("");
			size_ChuDaChon++;

		} else if (v == btn_okq_14 && XoaOChu(btn_okq_14.getText().toString())) {
			btn_okq_14.setText("");
			size_ChuDaChon++;

		} else {

		}

	}

	public void showgame(int id) {

		//
		managersqllite = new ManagerSqlite(getApplication());
		managersqllite.opentdatabase();

		managersqllite.UpdateImageView(R.drawable.binhma, 1);
		managersqllite.UpdateImageView(R.drawable.tranhsondau, 2);
		managersqllite.UpdateImageView(R.drawable.toida, 3);
		managersqllite.UpdateImageView(R.drawable.dongdao, 4);
		managersqllite.UpdateImageView(R.drawable.haohung, 5);
		//
		managersqllite.UpdateImageView(R.drawable.camcannhaymuc, 6);
		managersqllite.UpdateImageView(R.drawable.tuongtuong, 7);
		managersqllite.UpdateImageView(R.drawable.ngucoc, 8);
		managersqllite.UpdateImageView(R.drawable.bitruyen, 9);
		managersqllite.UpdateImageView(R.drawable.totien, 10);
		//
		managersqllite.UpdateImageView(R.drawable.maimoi, 11);
		managersqllite.UpdateImageView(R.drawable.donglong, 12);
		managersqllite.UpdateImageView(R.drawable.baobi, 13);
		managersqllite.UpdateImageView(R.drawable.hoclienthong, 14);
		managersqllite.UpdateImageView(R.drawable.chithi, 15);
		//
		managersqllite.UpdateImageView(R.drawable.bibach, 16);
		managersqllite.UpdateImageView(R.drawable.saobang, 17);
		managersqllite.UpdateImageView(R.drawable.giatrentroi, 18);
		managersqllite.UpdateImageView(R.drawable.tongtulenh, 19);
		managersqllite.UpdateImageView(R.drawable.baohanh, 20);
		//
		managersqllite.UpdateImageView(R.drawable.bosung, 21);
		managersqllite.UpdateImageView(R.drawable.maclua, 22);
		managersqllite.UpdateImageView(R.drawable.thutuong, 23);
		managersqllite.UpdateImageView(R.drawable.dancataitu, 24);
		managersqllite.UpdateImageView(R.drawable.somui, 25);
		//
		managersqllite.UpdateImageView(R.drawable.giaithich, 26);
		managersqllite.UpdateImageView(R.drawable.cannao, 27);
		managersqllite.UpdateImageView(R.drawable.tutung, 28);
		managersqllite.UpdateImageView(R.drawable.sodaohoa, 29);
		managersqllite.UpdateImageView(R.drawable.ytu, 30);
		//
		managersqllite.UpdateImageView(R.drawable.thatkinh, 31);
		managersqllite.UpdateImageView(R.drawable.dainhan, 32);
		managersqllite.UpdateImageView(R.drawable.giantiep, 33);
		managersqllite.UpdateImageView(R.drawable.lichthiep, 34);
		managersqllite.UpdateImageView(R.drawable.thieuta, 35);
		//
		managersqllite.UpdateImageView(R.drawable.hanhhung, 36);
		managersqllite.UpdateImageView(R.drawable.tongbithu, 37);
		managersqllite.UpdateImageView(R.drawable.phico, 38);
		managersqllite.UpdateImageView(R.drawable.chiencong, 39);
		managersqllite.UpdateImageView(R.drawable.nokia, 40);
		//
		managersqllite.UpdateImageView(R.drawable.ovuong, 41);
		managersqllite.UpdateImageView(R.drawable.luudan, 42);
		managersqllite.UpdateImageView(R.drawable.matthiet, 43);
		managersqllite.UpdateImageView(R.drawable.phongkhong, 44);
		managersqllite.UpdateImageView(R.drawable.holytinh, 45);
		//
		managersqllite.UpdateImageView(R.drawable.thucongmynghe, 46);
		managersqllite.UpdateImageView(R.drawable.chieutruc, 47);
		managersqllite.UpdateImageView(R.drawable.nambancau, 48);
		managersqllite.UpdateImageView(R.drawable.chiensy, 49);
		managersqllite.UpdateImageView(R.drawable.xangang, 50);
		//
		managersqllite.UpdateImageView(R.drawable.khongkich, 51);
		managersqllite.UpdateImageView(R.drawable.giandiep, 52);
		managersqllite.UpdateImageView(R.drawable.lonhbong, 53);
		managersqllite.UpdateImageView(R.drawable.quanham, 54);
		managersqllite.UpdateImageView(R.drawable.tinhco, 55);
		//
		managersqllite.UpdateImageView(R.drawable.hangngu, 56);
		managersqllite.UpdateImageView(R.drawable.casio, 57);
		managersqllite.UpdateImageView(R.drawable.daugio, 58);
		managersqllite.UpdateImageView(R.drawable.thieutuong, 59);
		managersqllite.UpdateImageView(R.drawable.danhgiaplaca, 60);
		//
		managersqllite.UpdateImageView(R.drawable.canhtranh, 61);
		managersqllite.UpdateImageView(R.drawable.sangsua, 62);
		managersqllite.UpdateImageView(R.drawable.dola, 63);
		managersqllite.UpdateImageView(R.drawable.guongcau, 64);
		managersqllite.UpdateImageView(R.drawable.daumoi, 65);
		//

		managersqllite.UpdateImageView(R.drawable.chihuy, 66);
		managersqllite.UpdateImageView(R.drawable.caobuoc, 67);
		managersqllite.UpdateImageView(R.drawable.linhlienlac, 68);
		managersqllite.UpdateImageView(R.drawable.linhdao, 69);
		managersqllite.UpdateImageView(R.drawable.ducnuocbeoco, 70);
		//
		managersqllite.UpdateImageView(R.drawable.yamaha, 71);
		managersqllite.UpdateImageView(R.drawable.hoaluc, 72);
		managersqllite.UpdateImageView(R.drawable.camtu, 73);
		managersqllite.UpdateImageView(R.drawable.tubi, 74);
		managersqllite.UpdateImageView(R.drawable.cattuong, 75);
		//
		managersqllite.UpdateImageView(R.drawable.dautranh, 76);
		managersqllite.UpdateImageView(R.drawable.hoga, 77);
		managersqllite.UpdateImageView(R.drawable.phuchau, 78);
		managersqllite.UpdateImageView(R.drawable.epcung, 79);
		managersqllite.UpdateImageView(R.drawable.thangthung, 80);
		//
		managersqllite.UpdateImageView(R.drawable.thientai, 81);
		managersqllite.UpdateImageView(R.drawable.muabongmay, 82);
		managersqllite.UpdateImageView(R.drawable.toitam, 83);
		managersqllite.UpdateImageView(R.drawable.longlangdathu, 84);
		managersqllite.UpdateImageView(R.drawable.cacao, 85);
		//
		managersqllite.UpdateImageView(R.drawable.oto, 86);
		managersqllite.UpdateImageView(R.drawable.caohoc, 87);
		managersqllite.UpdateImageView(R.drawable.gauquan, 88);
		managersqllite.UpdateImageView(R.drawable.taobon, 89);
		managersqllite.UpdateImageView(R.drawable.daongu, 90);
		//
		managersqllite.UpdateImageView(R.drawable.daolon, 91);
		managersqllite.UpdateImageView(R.drawable.vattu, 92);
		managersqllite.UpdateImageView(R.drawable.chinchan, 93);
		managersqllite.UpdateImageView(R.drawable.vuaphaluoi, 94);
		managersqllite.UpdateImageView(R.drawable.laca, 95);
		//
		managersqllite.UpdateImageView(R.drawable.langben, 96);
		managersqllite.UpdateImageView(R.drawable.nhonho, 97);
		managersqllite.UpdateImageView(R.drawable.baodong, 98);
		managersqllite.UpdateImageView(R.drawable.bankinh, 99);
		managersqllite.UpdateImageView(R.drawable.caobiet, 100);
		//
		managersqllite.UpdateImageView(R.drawable.data, 101);
		managersqllite.UpdateImageView(R.drawable.baola, 102);
		managersqllite.UpdateImageView(R.drawable.tatyeu, 103);
		managersqllite.UpdateImageView(R.drawable.dacuoi, 104);
		managersqllite.UpdateImageView(R.drawable.bolao, 105);
		//
		managersqllite.UpdateImageView(R.drawable.nghesinhanhdan, 106);
		managersqllite.UpdateImageView(R.drawable.otrong, 107);
		managersqllite.UpdateImageView(R.drawable.baoham, 108);
		managersqllite.UpdateImageView(R.drawable.thoihan, 109);
		//
		managersqllite.UpdateImageView(R.drawable.hoclech, 110);
		managersqllite.UpdateImageView(R.drawable.quaibi, 111);
		managersqllite.UpdateImageView(R.drawable.tranhcodong, 112);
		managersqllite.UpdateImageView(R.drawable.batcoc, 113);
		managersqllite.UpdateImageView(R.drawable.lancan, 114);
		//
		managersqllite.UpdateImageView(R.drawable.hutchankhong, 115);
		managersqllite.UpdateImageView(R.drawable.caito, 116);
		managersqllite.UpdateImageView(R.drawable.docnhatvonhi, 117);
		managersqllite.UpdateImageView(R.drawable.tieuchay, 118);
		managersqllite.UpdateImageView(R.drawable.thuocla, 119);
		//
		//
		managersqllite.UpdateImageView(R.drawable.dinhcao, 120);
		managersqllite.UpdateImageView(R.drawable.duongcai, 121);
		managersqllite.UpdateImageView(R.drawable.guongchieuhau, 122);
		managersqllite.UpdateImageView(R.drawable.quabao, 123);
		managersqllite.UpdateImageView(R.drawable.thienduong, 124);
		//
		managersqllite.UpdateImageView(R.drawable.dinhcao, 120);
		managersqllite.UpdateImageView(R.drawable.duongcai, 121);
		managersqllite.UpdateImageView(R.drawable.guongchieuhau, 122);
		managersqllite.UpdateImageView(R.drawable.quabao, 123);
		managersqllite.UpdateImageView(R.drawable.thienduong, 124);
		//
		managersqllite.UpdateImageView(R.drawable.dongbao, 125);
		managersqllite.UpdateImageView(R.drawable.chotreomeoday, 126);
		managersqllite.UpdateImageView(R.drawable.giaothong, 127);
		managersqllite.UpdateImageView(R.drawable.giaythonghanh, 128);
		managersqllite.UpdateImageView(R.drawable.congty, 129);
		//
		managersqllite.UpdateImageView(R.drawable.laban, 130);
		managersqllite.UpdateImageView(R.drawable.luoile, 131);
		managersqllite.UpdateImageView(R.drawable.saunang, 132);
		managersqllite.UpdateImageView(R.drawable.nhacchoctroi, 133);
		managersqllite.UpdateImageView(R.drawable.canthiep, 134);
		//
		managersqllite.UpdateImageView(R.drawable.thanthong, 135);
		managersqllite.UpdateImageView(R.drawable.danphong, 136);
		managersqllite.UpdateImageView(R.drawable.nhosy, 137);
		managersqllite.UpdateImageView(R.drawable.thothe, 138);
		managersqllite.UpdateImageView(R.drawable.hoanhtrang, 139);
		//
		managersqllite.UpdateImageView(R.drawable.thuydau, 140);
		managersqllite.UpdateImageView(R.drawable.tieuthu, 141);
		managersqllite.UpdateImageView(R.drawable.totbung, 142);
		managersqllite.UpdateImageView(R.drawable.dagiac, 143);
		managersqllite.UpdateImageView(R.drawable.canhdong, 144);
		//
		managersqllite.UpdateImageView(R.drawable.danhgia, 145);
		managersqllite.UpdateImageView(R.drawable.aulac, 146);
		managersqllite.UpdateImageView(R.drawable.tinhhoa, 147);
		managersqllite.UpdateImageView(R.drawable.thattruyen, 148);
		managersqllite.UpdateImageView(R.drawable.bachcau, 149);
		//
		managersqllite.UpdateImageView(R.drawable.tyte, 150);
		managersqllite.UpdateImageView(R.drawable.hatnhan, 151);
		managersqllite.UpdateImageView(R.drawable.gokien, 152);
		// managersqllite.UpdateImageView(R.drawable.quabao, 143);
		// managersqllite.UpdateImageView(R.drawable.thienduong, 144);
		managersqllite.UpdateImageView(R.drawable.gokien, 152);
		//
		level = pre.getInt("numberlevel", 1);
		diem = pre.getInt("numberDiem", 100);
		number_help = pre.getInt("num_help", 0);
		//
		CAUHOI = new Cauhoi();
		CAUHOI = managersqllite.getCauHoi(level);
		// // set dữ liệu cho câu hỏi

		pre = getSharedPreferences("mydata", MODE_PRIVATE);
		//

		size_ChuDaChon = CAUHOI.getDapan().length();
		//
		btn_diem.setText(diem + "");
		btn_numberlevel.setText(CAUHOI.getId() + "");
		//
		if (pre.getBoolean("speak", true)) {
			ic_audio_off.setVisibility(View.GONE);
			ic_audio_on.setVisibility(View.VISIBLE);
			nhacnen.start();
			handler.postDelayed(runmusics, 7000);
		} else {
			ic_audio_off.setVisibility(View.VISIBLE);
			ic_audio_on.setVisibility(View.GONE);

		}

		// set level cho game la số id của câu hỏi
		// // set hình ảnh cho câu hỏi
		img_cauhoi.setImageResource(CAUHOI.getHinhanh());
		// // set số lượng ô số cho câu hỏi

		HienButtonODapAn(CAUHOI.getDapan().length());
		// hiện ô chữ gợi ý
		HienOGoiy(CAUHOI.getGoiy());
		//

		if (number_help > 0) {
			Click_help();
			size_ChuDaChon = size_ChuDaChon - number_help;
		}

	}

	// 13 ,12 ,11,10,9,8
	public void HienOGoiy(String dapan) {

		char[] M = dapan.toCharArray();

		btn_ogy_1.setVisibility(View.VISIBLE);
		btn_ogy_2.setVisibility(View.VISIBLE);
		btn_ogy_3.setVisibility(View.VISIBLE);
		btn_ogy_4.setVisibility(View.VISIBLE);
		btn_ogy_5.setVisibility(View.VISIBLE);
		btn_ogy_6.setVisibility(View.VISIBLE);
		btn_ogy_7.setVisibility(View.VISIBLE);
		btn_ogy_8.setVisibility(View.VISIBLE);
		btn_ogy_9.setVisibility(View.VISIBLE);
		btn_ogy_10.setVisibility(View.VISIBLE);
		btn_ogy_11.setVisibility(View.VISIBLE);
		btn_ogy_12.setVisibility(View.VISIBLE);
		btn_ogy_13.setVisibility(View.VISIBLE);
		btn_ogy_14.setVisibility(View.VISIBLE);
		//
		btn_ogy_1.setText(M[0] + "");
		btn_ogy_2.setText(M[1] + "");
		btn_ogy_3.setText(M[2] + "");
		btn_ogy_4.setText(M[3] + "");
		btn_ogy_5.setText(M[4] + "");
		btn_ogy_6.setText(M[5] + "");
		btn_ogy_7.setText(M[6] + "");
		btn_ogy_8.setText(M[7] + "");
		btn_ogy_9.setText(M[8] + "");
		btn_ogy_10.setText(M[9] + "");
		btn_ogy_11.setText(M[10] + "");
		btn_ogy_12.setText(M[11] + "");
		btn_ogy_13.setText(M[12] + "");
		btn_ogy_14.setText(M[13] + "");

	}

	public void HienButtonODapAn(int SoOChu) {
		btn_okq_1.setVisibility(View.GONE);
		btn_okq_2.setVisibility(View.GONE);
		btn_okq_3.setVisibility(View.GONE);
		btn_okq_4.setVisibility(View.GONE);
		btn_okq_5.setVisibility(View.GONE);
		btn_okq_6.setVisibility(View.GONE);
		btn_okq_7.setVisibility(View.GONE);
		btn_okq_8.setVisibility(View.GONE);
		btn_okq_9.setVisibility(View.GONE);
		btn_okq_10.setVisibility(View.GONE);
		btn_okq_11.setVisibility(View.GONE);
		btn_okq_12.setVisibility(View.GONE);
		btn_okq_13.setVisibility(View.GONE);
		btn_okq_14.setVisibility(View.GONE);
		//
		btn_okq_1.setText("");
		btn_okq_2.setText("");
		btn_okq_3.setText("");
		btn_okq_4.setText("");
		btn_okq_5.setText("");
		btn_okq_6.setText("");
		btn_okq_7.setText("");
		btn_okq_8.setText("");
		btn_okq_9.setText("");
		btn_okq_10.setText("");
		btn_okq_11.setText("");
		btn_okq_12.setText("");
		btn_okq_13.setText("");
		btn_okq_14.setText("");
		//
		while (SoOChu >= 1) {
			switch (SoOChu) {

			case 1:
				btn_okq_1.setVisibility(View.VISIBLE);
				break;
			case 2:
				btn_okq_2.setVisibility(View.VISIBLE);
				break;
			case 3:
				btn_okq_3.setVisibility(View.VISIBLE);
				break;
			case 4:
				btn_okq_4.setVisibility(View.VISIBLE);
				break;
			case 5:
				btn_okq_5.setVisibility(View.VISIBLE);
				break;
			case 6:
				btn_okq_6.setVisibility(View.VISIBLE);
				break;
			case 7:
				btn_okq_7.setVisibility(View.VISIBLE);
				break;
			case 8:
				btn_okq_8.setVisibility(View.VISIBLE);
				break;
			case 9:
				btn_okq_9.setVisibility(View.VISIBLE);
				break;
			case 10:
				btn_okq_10.setVisibility(View.VISIBLE);
				break;
			case 11:
				btn_okq_11.setVisibility(View.VISIBLE);
				break;
			case 12:
				btn_okq_12.setVisibility(View.VISIBLE);
				break;
			case 13:
				btn_okq_13.setVisibility(View.VISIBLE);
				break;
			case 14:
				btn_okq_14.setVisibility(View.VISIBLE);
				break;
			default:
				break;
			}
			SoOChu--;
		}
	}

	public boolean GhiKetQua(String key) {
		if (btn_okq_1.getText().toString().equals("")
				&& btn_okq_1.getVisibility() == View.VISIBLE) {
			btn_okq_1.setText(key);
		} else if (btn_okq_2.getText().toString().equals("")
				&& btn_okq_2.getVisibility() == View.VISIBLE) {
			btn_okq_2.setText(key);
		} else if (btn_okq_3.getText().toString().equals("")
				&& btn_okq_3.getVisibility() == View.VISIBLE) {
			btn_okq_3.setText(key);
		} else if (btn_okq_4.getText().toString().equals("")
				&& btn_okq_4.getVisibility() == View.VISIBLE) {
			btn_okq_4.setText(key);
		} else if (btn_okq_5.getText().toString().equals("")
				&& btn_okq_5.getVisibility() == View.VISIBLE) {
			btn_okq_5.setText(key);
		} else if (btn_okq_6.getText().toString().equals("")
				&& btn_okq_6.getVisibility() == View.VISIBLE) {
			btn_okq_6.setText(key);
		} else if (btn_okq_7.getText().toString().equals("")
				&& btn_okq_7.getVisibility() == View.VISIBLE) {
			btn_okq_7.setText(key);
		} else if (btn_okq_8.getText().toString().equals("")
				&& btn_okq_8.getVisibility() == View.VISIBLE) {
			btn_okq_8.setText(key);
		} else if (btn_okq_9.getText().toString().equals("")
				&& btn_okq_9.getVisibility() == View.VISIBLE) {
			btn_okq_9.setText(key);
		} else if (btn_okq_10.getText().toString().equals("")
				&& btn_okq_10.getVisibility() == View.VISIBLE) {
			btn_okq_10.setText(key);
		} else if (btn_okq_11.getText().toString().equals("")
				&& btn_okq_11.getVisibility() == View.VISIBLE) {
			btn_okq_11.setText(key);
		} else if (btn_okq_12.getText().toString().equals("")
				&& btn_okq_12.getVisibility() == View.VISIBLE) {
			btn_okq_12.setText(key);
		} else if (btn_okq_13.getText().toString().equals("")
				&& btn_okq_13.getVisibility() == View.VISIBLE) {
			btn_okq_13.setText(key);
		} else if (btn_okq_14.getText().toString().equals("")
				&& btn_okq_14.getVisibility() == View.VISIBLE) {
			btn_okq_14.setText(key);
		} else { // ô chữ đã đầy không thể ghi thêm vào được
			return false;
		}
		return true;

	}

	public boolean XoaOChu(String key) {
		if (btn_ogy_1.getVisibility() == View.INVISIBLE
				&& btn_ogy_1.getText().equals(key)) {
			btn_ogy_1.setVisibility(View.VISIBLE);
		} else if (btn_ogy_2.getVisibility() == View.INVISIBLE
				&& btn_ogy_2.getText().equals(key)) {
			btn_ogy_2.setVisibility(View.VISIBLE);
		} else if (btn_ogy_3.getVisibility() == View.INVISIBLE
				&& btn_ogy_3.getText().equals(key)) {
			btn_ogy_3.setVisibility(View.VISIBLE);
		} else if (btn_ogy_4.getVisibility() == View.INVISIBLE
				&& btn_ogy_4.getText().equals(key)) {
			btn_ogy_4.setVisibility(View.VISIBLE);
		} else if (btn_ogy_5.getVisibility() == View.INVISIBLE
				&& btn_ogy_5.getText().equals(key)) {
			btn_ogy_5.setVisibility(View.VISIBLE);
		} else if (btn_ogy_6.getVisibility() == View.INVISIBLE
				&& btn_ogy_6.getText().equals(key)) {
			btn_ogy_6.setVisibility(View.VISIBLE);
		} else if (btn_ogy_7.getVisibility() == View.INVISIBLE
				&& btn_ogy_7.getText().equals(key)) {
			btn_ogy_7.setVisibility(View.VISIBLE);
		} else if (btn_ogy_8.getVisibility() == View.INVISIBLE
				&& btn_ogy_8.getText().equals(key)) {
			btn_ogy_8.setVisibility(View.VISIBLE);
		} else if (btn_ogy_9.getVisibility() == View.INVISIBLE
				&& btn_ogy_9.getText().equals(key)) {
			btn_ogy_9.setVisibility(View.VISIBLE);
		} else if (btn_ogy_10.getVisibility() == View.INVISIBLE
				&& btn_ogy_10.getText().equals(key)) {
			btn_ogy_10.setVisibility(View.VISIBLE);
		} else if (btn_ogy_11.getVisibility() == View.INVISIBLE
				&& btn_ogy_11.getText().equals(key)) {
			btn_ogy_11.setVisibility(View.VISIBLE);
		} else if (btn_ogy_12.getVisibility() == View.INVISIBLE
				&& btn_ogy_12.getText().equals(key)) {
			btn_ogy_12.setVisibility(View.VISIBLE);
		} else if (btn_ogy_13.getVisibility() == View.INVISIBLE
				&& btn_ogy_13.getText().equals(key)) {
			btn_ogy_13.setVisibility(View.VISIBLE);
		} else if (btn_ogy_14.getVisibility() == View.INVISIBLE
				&& btn_ogy_14.getText().equals(key)) {
			btn_ogy_14.setVisibility(View.VISIBLE);
		} else {
			return false;
		}
		return true;
	}

	public void KiemTraDapAn() {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.mycustomtoast_layout, null);

		//
		String dapan = btn_okq_1.getText().toString()
				+ btn_okq_2.getText().toString()
				+ btn_okq_3.getText().toString()
				+ btn_okq_4.getText().toString()
				+ btn_okq_5.getText().toString()
				+ btn_okq_6.getText().toString()
				+ btn_okq_7.getText().toString()
				+ btn_okq_8.getText().toString()
				+ btn_okq_9.getText().toString()
				+ btn_okq_10.getText().toString()
				+ btn_okq_11.getText().toString()
				+ btn_okq_12.getText().toString()
				+ btn_okq_13.getText().toString()
				+ btn_okq_14.getText().toString() + "";
		if (dapan.equals(CAUHOI.getDapan())) {
			/*
			 * thong báo kết quả
			 */
			//
			level = level + 1;
			diem = diem + 10;
			nhacnen.stop();
			if (pre.getBoolean("speak", true)) {
				new MediaPlayer();
				nhacchienthang = MediaPlayer.create(this, R.raw.musicwin);
				nhacchienthang.start();
			}
			// luu help
			number_help = 0;
			pre = getSharedPreferences("mydata", MODE_PRIVATE);
			SharedPreferences.Editor edit = pre.edit();
			edit.putInt("num_help", number_help);
			edit.commit();

			// medi

			Intent good = new Intent(GAME.this, ChienThang.class);
			good.putExtra("id", CAUHOI.getId());
			good.putExtra("dapan", CAUHOI.getKetqua());
			startActivity(good);

		} else {
			Animation anima = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.shake);
			btn_okq_1.startAnimation(anima);
			btn_okq_2.startAnimation(anima);
			btn_okq_3.startAnimation(anima);
			btn_okq_4.startAnimation(anima);
			btn_okq_5.startAnimation(anima);
			btn_okq_6.startAnimation(anima);
			btn_okq_7.startAnimation(anima);
			btn_okq_8.startAnimation(anima);
			btn_okq_9.startAnimation(anima);
			btn_okq_10.startAnimation(anima);
			btn_okq_11.startAnimation(anima);
			btn_okq_12.startAnimation(anima);
			btn_okq_13.startAnimation(anima);
			btn_okq_14.startAnimation(anima);
			//
			Toast toat = new Toast(this);
			toat.setGravity(Gravity.CENTER, 0, 0);
			toat.setDuration(Toast.LENGTH_LONG);
			toat.setView(layout);
			toat.show();
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
				nhacnen.stop();
				// Khoi tao lai Activity main

				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				pre = getSharedPreferences("mydata", MODE_PRIVATE);
				SharedPreferences.Editor edit = pre.edit();
				edit.putInt("numberlevel", level);
				edit.putInt("numberDiem", diem);
				edit.putInt("num_help", number_help);
				edit.commit();
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

	public ArrayList<String> getDsOChuDapAn() {
		return dsOChuDapAn;
	}

	public void setDsOChuDapAn(ArrayList<String> dsOChuDapAn) {
		this.dsOChuDapAn = dsOChuDapAn;
	}

	public void XoaButton_help(String key) {
		if (btn_ogy_1.getText().equals(key)) {
			btn_ogy_1.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_2.getText().equals(key)) {
			btn_ogy_2.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_3.getText().equals(key)) {
			btn_ogy_3.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_4.getText().equals(key)) {
			btn_ogy_4.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_5.getText().equals(key)) {
			btn_ogy_5.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_6.getText().equals(key)) {
			btn_ogy_6.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_7.getText().equals(key)) {
			btn_ogy_7.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_8.getText().equals(key)) {
			btn_ogy_8.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_9.getText().equals(key)) {
			btn_ogy_9.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_10.getText().equals(key)) {
			btn_ogy_10.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_11.getText().equals(key)) {
			btn_ogy_11.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_12.getText().equals(key)) {
			btn_ogy_12.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_13.getText().equals(key)) {
			btn_ogy_13.setVisibility(View.INVISIBLE);
		} else if (btn_ogy_14.getText().equals(key)) {
			btn_ogy_14.setVisibility(View.INVISIBLE);
		} else {

		}
	}

}
