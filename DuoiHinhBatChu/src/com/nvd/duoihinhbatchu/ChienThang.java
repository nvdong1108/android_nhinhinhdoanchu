package com.nvd.duoihinhbatchu;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChienThang extends Activity implements OnClickListener {

	private LinearLayout btn_next;
	private TextView txt_dapan;
	private Typeface face;
	private MediaPlayer NhacVotay;
	private InterstitialAd inters;
	AdView adview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chien_thang);
		GAME g = new GAME();
		//
		adview = (AdView) findViewById(R.id.adView);
		inters = new InterstitialAd(ChienThang.this);
		inters.setAdUnitId("ca-app-pub-1395380684132176/9741513842");
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adview.loadAd(adRequest);
		inters.loadAd(adRequest);
		//
		AnhXa();
		XuatDapAn(g.level - 1);
		NhacVotay = new MediaPlayer().create(this, R.raw.nhacvotay2);
		NhacVotay.start();
		btn_next.setOnClickListener(this);

	}

	public void AnhXa() {
		btn_next = (LinearLayout) findViewById(R.id.btn_next_leve);
		txt_dapan = (TextView) findViewById(R.id.txt_ketqua);
		face = Typeface.createFromAsset(getAssets(), "font/BOOKOS.TTF");
		txt_dapan.setTypeface(face);
	}

	@Override
	public void onClick(View v) {
		if (v == btn_next) {
			Intent next_level = new Intent(ChienThang.this, GAME.class);
			startActivity(next_level);
		} else {

		}

	}

	public void XuatDapAn(int lv) {
		switch (lv) {
		case 1:
			txt_dapan.setText("NGỰA Ô");
			break;
		case 2:
			txt_dapan.setText("THÔNG LỆ");
			break;
		case 3:
			txt_dapan.setText("SAO BĂNG");
			break;
		case 4:
			txt_dapan.setText("Ý TỨ");
			break;
		case 5:
			txt_dapan.setText("HO GÀ");
			break;
		case 6:
			txt_dapan.setText("HÀO HỨNG");
			break;
		case 7:
			txt_dapan.setText("CAU CÓ");
			break;
		case 8:
			txt_dapan.setText("Cá Tính");
			break;
		case 9:
			txt_dapan.setText("TRANH CƯỚP");
			break;
		case 10:
			txt_dapan.setText("CÒ CỬA ");
			break;
		case 11:
			txt_dapan.setText("Y Tá");
			break;
		case 12:
			txt_dapan.setText("HÓA VÀNG");
			break;
		case 13:
			txt_dapan.setText("TAN CA");
			break;
		case 14:
			txt_dapan.setText("THIẾU NIÊN");
			break;
		case 15:
			txt_dapan.setText("THĂNG HOA");
			break;
		case 16:
			txt_dapan.setText("TÊ THẤP");
			break;
		case 17:
			txt_dapan.setText("THÚ THẬT");
			break;
		case 18:
			txt_dapan.setText("NHỊ CA");
			break;
		case 19:
			txt_dapan.setText("HỌA TIẾT");
			break;
		case 20:
			txt_dapan.setText("SĂN SÓC");
			break;
		case 21:
			txt_dapan.setText("VÔ TƯ");
			break;
		case 22:
			txt_dapan.setText("ÔNG BẦU");
			break;
		case 23:
			txt_dapan.setText("CẦN CẨU");
			break;
		case 24:
			txt_dapan.setText("CÃI CỌ");
			break;
		case 25:
			txt_dapan.setText("BI �?AO");
			break;
		case 26:
			txt_dapan.setText("PHÂN VÂN");
			break;
		case 27:
			txt_dapan.setText("HEO HÚT");
			break;
		case 28:
			txt_dapan.setText("DAO LAM");
			break;
		case 29:
			txt_dapan.setText("TÙ MÙ");
			break;
		case 30:
			txt_dapan.setText("NHIỆT HUYẾT");
			break;

		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(ChienThang.this);
		builder.setTitle("Exit alert");

		builder.setMessage("Are you sure exit ?");

		builder.setNegativeButton(android.R.string.yes,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
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
		builder.setNeutralButton(android.R.string.no,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		builder.create().show();
	}

}
