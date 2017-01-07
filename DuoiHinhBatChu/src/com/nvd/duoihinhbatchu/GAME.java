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
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

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
	private ManagarGAME m;
	private int size_ChuDaChon;
	private ArrayList<String> ARR_DAPAN;
	private Cauhoi CAUHOI;
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
		m = new ManagarGAME();
		// Khai báo nhạc nền
		nhacnen = new MediaPlayer().create(this, R.raw.nhactronggame);
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

		showgame(level);

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
		number_help = 0;
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
		edit.commit();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		pre = getSharedPreferences("mydata", MODE_PRIVATE);
		SharedPreferences.Editor edit = pre.edit();
		edit.putInt("numberlevel", level);
		edit.putInt("numberDiem", diem);
		edit.commit();
	}

	/*
	 * 
	 */
	public boolean AnhXa() {
		// ẢNH
		img_cauhoi = (ImageView) findViewById(R.id.img_cauhoi);
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
		face = Typeface.createFromAsset(getAssets(), "font/ARIBLK.TTF"); // khai
																			// bao
																			// from
																			// chư
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
		ic_audio_off = (ImageView) findViewById(R.id.icon_audio_off);
		ic_audio_on = (ImageView) findViewById(R.id.icon_audio_on);
		pre = getSharedPreferences("mydata", MODE_PRIVATE);
		//
		level = pre.getInt("numberlevel", 1);
		diem = pre.getInt("numberDiem", 100);
		btn_diem.setText(diem + "");
		if (pre.getBoolean("speak", true)) {
			ic_audio_off.setVisibility(View.GONE);
			ic_audio_on.setVisibility(View.VISIBLE);
			nhacnen.start();
		} else {
			ic_audio_off.setVisibility(View.VISIBLE);
			ic_audio_on.setVisibility(View.GONE);

		}

		return true;
	}

	@Override
	public void onClick(View v) {
		m = new ManagarGAME();

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

								char[] M = CAUHOI.getDapan().toCharArray();

								switch (number_help) {
								case 0:
									btn_okq_1.setText(M[0] + "");
									XoaButton_help(btn_okq_1.getText()
											.toString());
									size_ChuDaChon--;
									if (size_ChuDaChon < 1)
										KiemTraDapAn();
									break;
								case 1:
									btn_okq_2.setText(M[1] + "");
									XoaButton_help(btn_okq_2.getText()
											.toString());
									size_ChuDaChon--;
									if (size_ChuDaChon < 1)
										KiemTraDapAn();
									break;
								case 2:
									btn_okq_3.setText(M[2] + "");
									XoaButton_help(btn_okq_3.getText()
											.toString());
									size_ChuDaChon--;
									if (size_ChuDaChon < 1)
										KiemTraDapAn();
									break;
								case 3:
									btn_okq_4.setText(M[4] + "");
									XoaButton_help(btn_okq_4.getText()
											.toString());
									size_ChuDaChon--;
									if (size_ChuDaChon < 1)
										KiemTraDapAn();
									break;
								default:
									break;
								}
								dialog.dismiss();
								diem = diem - 20;
								btn_diem.setText(diem + "");
								number_help++;
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
			SharedPreferences.Editor edit = pre.edit();
			edit.putBoolean("speak", false);
			edit.commit();

		}
		if (v == ic_audio_off) {
			ic_audio_on.setVisibility(View.VISIBLE);
			ic_audio_off.setVisibility(View.GONE);
			nhacnen.start();
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
			if (number_help > 0)
				number_help--;

		} else if (v == btn_okq_2 && XoaOChu(btn_okq_2.getText().toString())) {
			btn_okq_2.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_3 && XoaOChu(btn_okq_3.getText().toString())) {
			btn_okq_3.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_4 && XoaOChu(btn_okq_4.getText().toString())) {
			btn_okq_4.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_5 && XoaOChu(btn_okq_5.getText().toString())) {
			btn_okq_5.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_6 && XoaOChu(btn_okq_6.getText().toString())) {
			btn_okq_6.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_7 && XoaOChu(btn_okq_7.getText().toString())) {
			btn_okq_7.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_8 && XoaOChu(btn_okq_8.getText().toString())) {
			btn_okq_8.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_9 && XoaOChu(btn_okq_9.getText().toString())) {
			btn_okq_9.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_10 && XoaOChu(btn_okq_10.getText().toString())) {
			btn_okq_10.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_11 && XoaOChu(btn_okq_11.getText().toString())) {
			btn_okq_12.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_12 && XoaOChu(btn_okq_12.getText().toString())) {
			btn_okq_12.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_13 && XoaOChu(btn_okq_13.getText().toString())) {
			btn_okq_13.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else if (v == btn_okq_14 && XoaOChu(btn_okq_14.getText().toString())) {
			btn_okq_14.setText("");
			size_ChuDaChon++;
			if (number_help > 0)
				number_help--;
		} else {

		}

	}

	public void showgame(int id) {
		//

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

		CAUHOI = new Cauhoi();
		CAUHOI = managersqllite.getCauHoi(level);
		// // set dữ liệu cho câu hỏi
		// set level cho game la số id của câu hỏi
		btn_numberlevel.setText(CAUHOI.getId() + "");
		// // set hình ảnh cho câu hỏi
		img_cauhoi.setImageResource(CAUHOI.getHinhanh());
		// // set số lượng ô số cho câu hỏi
		size_ChuDaChon = CAUHOI.getDapan().length();
		HienButtonODapAn(CAUHOI.getDapan().length());
		// hiện ô chữ gợi ý
		ranDomTuGoiY(CAUHOI.getDapan());

	}

	// 13 ,12 ,11,10,9,8
	public void ranDomTuGoiY(String dapan) {

		ArrayList<String> M = new ArrayList<String>();

		char[] ds = { 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
				'A', 'A', 'A' };

		ds = dapan.toCharArray();
		for (int i = 0; i < ds.length; i++) {
			M.add(ds[i] + "");
		}

		Random rd = new Random();
		for (int i = ds.length; i < 14; i++) {
			M.add((char) (rd.nextInt(25) + 65) + "");
		}
		// đảo mảng
		String tmp;
		int a, b, c;
		for (int i = 0; i < 20; i++) {
			a = rd.nextInt(7);
			b = rd.nextInt(14);
			tmp = M.get(a);
			M.set(a, M.get(b));
			M.set(b, tmp);
		}
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
		btn_ogy_1.setText(M.get(0) + "");
		btn_ogy_2.setText(M.get(1) + "");
		btn_ogy_3.setText(M.get(2) + "");
		btn_ogy_4.setText(M.get(3) + "");
		btn_ogy_5.setText(M.get(4) + "");
		btn_ogy_6.setText(M.get(5) + "");
		btn_ogy_7.setText(M.get(6) + "");
		btn_ogy_8.setText(M.get(7) + "");
		btn_ogy_9.setText(M.get(8) + "");
		btn_ogy_10.setText(M.get(9) + "");
		btn_ogy_11.setText(M.get(10) + "");
		btn_ogy_12.setText(M.get(11) + "");
		btn_ogy_13.setText(M.get(12) + "");
		btn_ogy_14.setText(M.get(13) + "");

	}

	public boolean GetLevel(int lv) {
		ArrayList<String> dsOChuGoiY = new ArrayList<String>();
		dsOChuDapAn = new ArrayList<String>();
		Drawable dram = null;
		switch (lv) {
		case 1:
			dram = getResources().getDrawable(R.drawable.h1);
			break;
		case 2:
			dram = getResources().getDrawable(R.drawable.h2);
			break;
		case 3:
			dram = getResources().getDrawable(R.drawable.h3);
			break;
		case 4:
			dram = getResources().getDrawable(R.drawable.h4);
			break;
		case 5:
			dram = getResources().getDrawable(R.drawable.h5);
			break;
		case 6:
			dram = getResources().getDrawable(R.drawable.h6);
			break;
		case 7:
			dram = getResources().getDrawable(R.drawable.h7);
			break;
		case 8:
			dram = getResources().getDrawable(R.drawable.h8);
			break;
		case 9:
			dram = getResources().getDrawable(R.drawable.h9);
			break;
		case 10:
			dram = getResources().getDrawable(R.drawable.h10);
			break;
		case 11:
			dram = getResources().getDrawable(R.drawable.h11);
			break;
		case 12:
			dram = getResources().getDrawable(R.drawable.h12);
			break;
		case 13:
			dram = getResources().getDrawable(R.drawable.h13);
			break;
		case 14:
			dram = getResources().getDrawable(R.drawable.h14);
			break;
		case 15:
			dram = getResources().getDrawable(R.drawable.h15);
			break;
		case 16:
			dram = getResources().getDrawable(R.drawable.h16);
			break;
		case 17:
			dram = getResources().getDrawable(R.drawable.h17);
			break;
		case 18:
			dram = getResources().getDrawable(R.drawable.h18);
			break;
		case 19:
			dram = getResources().getDrawable(R.drawable.h19);
			break;
		case 20:
			dram = getResources().getDrawable(R.drawable.h20);
			break;
		case 21:
			dram = getResources().getDrawable(R.drawable.h21);
			break;
		case 22:
			dram = getResources().getDrawable(R.drawable.h22);
			break;
		case 23:
			dram = getResources().getDrawable(R.drawable.h23);
			break;
		case 24:
			dram = getResources().getDrawable(R.drawable.h24);
			break;
		case 25:
			dram = getResources().getDrawable(R.drawable.h25);
			break;
		case 26:
			dram = getResources().getDrawable(R.drawable.h26);
			break;
		case 27:
			dram = getResources().getDrawable(R.drawable.h27);
			break;
		case 28:
			dram = getResources().getDrawable(R.drawable.h28);
			break;
		case 29:
			dram = getResources().getDrawable(R.drawable.h29);
			break;
		case 30:
			dram = getResources().getDrawable(R.drawable.h30);
		default:
			Intent theend = new Intent(GAME.this, TheEnd.class);
			startActivity(theend);
			return false;
		}
		btn_numberlevel.setText("" + lv);
		btn_diem.setText(diem + "");
		img_cauhoi.setImageDrawable(dram);
		dsOChuGoiY = m.TaoDSOChuGoiY(lv);
		setDsOChuDapAn(m.TaoDSOChuDapAn(lv));

		setButtonOGoiY(dsOChuGoiY);
		//
		HienButtonODapAn(m.leght_DapAn);
		return true;
	}

	public void setButtonOGoiY(ArrayList<String> ds) {
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
		btn_ogy_1.setText(ds.get(0));
		btn_ogy_2.setText(ds.get(1));
		btn_ogy_3.setText(ds.get(2));
		btn_ogy_4.setText(ds.get(3));
		btn_ogy_5.setText(ds.get(4));
		btn_ogy_6.setText(ds.get(5));
		btn_ogy_7.setText(ds.get(6));
		btn_ogy_8.setText(ds.get(7));
		btn_ogy_9.setText(ds.get(8));
		btn_ogy_10.setText(ds.get(9));
		btn_ogy_11.setText(ds.get(10));
		btn_ogy_12.setText(ds.get(11));
		btn_ogy_13.setText(ds.get(12));
		btn_ogy_14.setText(ds.get(13));

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
		char[] ds = CAUHOI.getDapan().toCharArray();
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
			nhacchienthang = new MediaPlayer().create(this,
					R.raw.nhacchienthang);
			nhacchienthang.start();
			nhacnen.stop();

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
