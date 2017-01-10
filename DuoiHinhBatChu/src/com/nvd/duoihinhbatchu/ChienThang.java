package com.nvd.duoihinhbatchu;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.LinearLayout;
import android.widget.TextView;

public class ChienThang extends Activity implements OnClickListener {

	private LinearLayout btn_next;
	private TextView txt_dapan;
	private Typeface face;

	private InterstitialAd inters;
	AdView adview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chien_thang);

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

		Bundle extras = getIntent().getExtras();
		extras.getString("dapan");
		txt_dapan.setText(extras.getString("dapan"));
		btn_next.setOnClickListener(this);

	}

	public void AnhXa() {
		btn_next = (LinearLayout) findViewById(R.id.btn_next_leve);
		txt_dapan = (TextView) findViewById(R.id.txt_ketqua);
		face = Typeface.createFromAsset(getAssets(), "font/TIMESBD.TTF");
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
