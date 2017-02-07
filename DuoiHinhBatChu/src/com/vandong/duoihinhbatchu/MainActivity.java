package com.vandong.duoihinhbatchu;

import java.io.IOException;
import java.util.ArrayList;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nvd.database.ManagerSqlite;
import com.nvd.objcauhoi.Cauhoi;
import com.vandong.duoihinhbatchu.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
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
	private ManagerSqlite managersqllite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//
		adview = (AdView) findViewById(R.id.adView);
		inters = new InterstitialAd(MainActivity.this);
		inters.setAdUnitId("ca-app-pub-1395380684132176/8067045842");
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adview.loadAd(adRequest);
		inters.loadAd(adRequest);
		// lần đầu ti�?n vào game
		//

		AnhXa();
		Button btn_rate = (Button) findViewById(R.id.btn_rate);
		btn_rate.setOnClickListener(this);
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
		// Upload môi khi upload versition
		if (pre.getInt("upload_data", 4) == 4) {
			doCreatDb();
			deleteDb();
			SharedPreferences.Editor edit = pre.edit();
			edit.putInt("upload_data", 5);
			edit.putInt("numberDiem", 100);
			edit.commit();
			//
			//
			managersqllite = new ManagerSqlite(getApplication());
			managersqllite.opentdatabase();
			//
			managersqllite.UpdateImageView(R.drawable.bomtan, 1);
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
			managersqllite.UpdateImageView(R.drawable.binhma, 30);
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
			managersqllite.UpdateImageView(R.drawable.bantan, 153);
			managersqllite.UpdateImageView(R.drawable.baogia, 154);
			//
			managersqllite.UpdateImageView(R.drawable.camket, 155);
			managersqllite.UpdateImageView(R.drawable.chimsacalan, 156);
			managersqllite.UpdateImageView(R.drawable.chuanxac, 157);
			managersqllite.UpdateImageView(R.drawable.congcong, 158);
			managersqllite.UpdateImageView(R.drawable.cuphap, 159);
			//
			managersqllite.UpdateImageView(R.drawable.daxoay, 160);
			managersqllite.UpdateImageView(R.drawable.daolam, 161);
			managersqllite.UpdateImageView(R.drawable.dongdang, 162);
			managersqllite.UpdateImageView(R.drawable.giaotranh, 163);
			managersqllite.UpdateImageView(R.drawable.heohut, 164);
			managersqllite.UpdateImageView(R.drawable.hohao, 165);
			managersqllite.UpdateImageView(R.drawable.hoale, 166);
			managersqllite.UpdateImageView(R.drawable.hoamat, 167);
			managersqllite.UpdateImageView(R.drawable.kiemtien, 168);
			managersqllite.UpdateImageView(R.drawable.kientuong, 169);
			//
			managersqllite.UpdateImageView(R.drawable.lenmang, 170);
			managersqllite.UpdateImageView(R.drawable.lybiet, 171);
			managersqllite.UpdateImageView(R.drawable.matmat, 172);
			managersqllite.UpdateImageView(R.drawable.nhiethuyet, 173);
			managersqllite.UpdateImageView(R.drawable.phanbo, 174);
			managersqllite.UpdateImageView(R.drawable.quantam, 175);
			managersqllite.UpdateImageView(R.drawable.sohong, 176);
			managersqllite.UpdateImageView(R.drawable.tenlua, 177);
			managersqllite.UpdateImageView(R.drawable.thaido, 178);
			managersqllite.UpdateImageView(R.drawable.thiensu, 179);
			//
			managersqllite.UpdateImageView(R.drawable.tiendoan, 180);
			managersqllite.UpdateImageView(R.drawable.tinhluoc, 181);
			managersqllite.UpdateImageView(R.drawable.trungtuong, 182);
			managersqllite.UpdateImageView(R.drawable.tumu, 183);
			managersqllite.UpdateImageView(R.drawable.tuongthich, 184);
			managersqllite.UpdateImageView(R.drawable.voihoasen, 185);
			managersqllite.UpdateImageView(R.drawable.xabong, 186);
			managersqllite.UpdateImageView(R.drawable.xuhuong, 187);
			managersqllite.UpdateImageView(R.drawable.yeukieu, 188);
			managersqllite.UpdateImageView(R.drawable.tienphong, 189);
			//
			managersqllite.UpdateImageView(R.drawable.quangcao, 190);
			managersqllite.UpdateImageView(R.drawable.daydua, 191);
			managersqllite.UpdateImageView(R.drawable.banggia, 192);
			managersqllite.UpdateImageView(R.drawable.hocbua, 193);
			managersqllite.UpdateImageView(R.drawable.thanghoa, 194);
			managersqllite.UpdateImageView(R.drawable.toihauthu, 195);
			managersqllite.UpdateImageView(R.drawable.tuongdai, 196);
			managersqllite.UpdateImageView(R.drawable.tethap, 197);
			managersqllite.UpdateImageView(R.drawable.hoavang, 198);
			managersqllite.UpdateImageView(R.drawable.tanca, 199);
			//
			managersqllite.UpdateImageView(R.drawable.thieunien, 200);
			managersqllite.UpdateImageView(R.drawable.yta, 201);
			managersqllite.UpdateImageView(R.drawable.phanvan, 202);
			managersqllite.UpdateImageView(R.drawable.bidao, 203);
			managersqllite.UpdateImageView(R.drawable.catinh, 204);
			managersqllite.UpdateImageView(R.drawable.thantho, 205);
			managersqllite.UpdateImageView(R.drawable.caico, 206);
			managersqllite.UpdateImageView(R.drawable.cancau, 207);
			managersqllite.UpdateImageView(R.drawable.nhaga, 208);
			managersqllite.UpdateImageView(R.drawable.phaocau, 209);
			//
			managersqllite.UpdateImageView(R.drawable.sansoc, 210);
			managersqllite.UpdateImageView(R.drawable.ongbau, 211);
			managersqllite.UpdateImageView(R.drawable.quandao, 212);
			managersqllite.UpdateImageView(R.drawable.bicao, 213);
			managersqllite.UpdateImageView(R.drawable.thuvi, 214);
			managersqllite.UpdateImageView(R.drawable.cauco, 215);
			managersqllite.UpdateImageView(R.drawable.votu, 216);
			managersqllite.UpdateImageView(R.drawable.baoluc, 217);
			managersqllite.UpdateImageView(R.drawable.chimsonca, 218);
			managersqllite.UpdateImageView(R.drawable.giocaosu, 219);
			//
			managersqllite.UpdateImageView(R.drawable.truongquay, 220);
			managersqllite.UpdateImageView(R.drawable.hoatiet, 221);
			managersqllite.UpdateImageView(R.drawable.phatgiac, 222);
			managersqllite.UpdateImageView(R.drawable.hanhhinh, 223);
			managersqllite.UpdateImageView(R.drawable.ancomtruockeng, 224);
			managersqllite.UpdateImageView(R.drawable.antuong, 225);
			managersqllite.UpdateImageView(R.drawable.bansongbanchet, 226);
			managersqllite.UpdateImageView(R.drawable.bebong, 227);
			managersqllite.UpdateImageView(R.drawable.beobot, 228);
			managersqllite.UpdateImageView(R.drawable.bongban, 229);
			//
			managersqllite.UpdateImageView(R.drawable.camdo, 230);
			managersqllite.UpdateImageView(R.drawable.chanthat, 231);
			managersqllite.UpdateImageView(R.drawable.chatdocdacam, 232);
			managersqllite.UpdateImageView(R.drawable.damoc, 233);
			managersqllite.UpdateImageView(R.drawable.daotau, 234);
			managersqllite.UpdateImageView(R.drawable.dulongducanh, 235);
			managersqllite.UpdateImageView(R.drawable.lucduc, 236);
			managersqllite.UpdateImageView(R.drawable.quanngai, 237);
			managersqllite.UpdateImageView(R.drawable.sonhatky, 238);
			managersqllite.UpdateImageView(R.drawable.tuongtu, 239);
			//
			managersqllite.UpdateImageView(R.drawable.tuongtuong, 240);
			managersqllite.UpdateImageView(R.drawable.xuongsong, 241);
			managersqllite.UpdateImageView(R.drawable.tiethoc, 242);
			managersqllite.UpdateImageView(R.drawable.xucxich, 243);
			managersqllite.UpdateImageView(R.drawable.tyso, 244);
			managersqllite.UpdateImageView(R.drawable.tranhdongho, 245);
			managersqllite.UpdateImageView(R.drawable.taysai, 246);
			managersqllite.UpdateImageView(R.drawable.sonbong, 247);
			managersqllite.UpdateImageView(R.drawable.quattran, 248);
			managersqllite.UpdateImageView(R.drawable.quanquit, 249);
			//
			managersqllite.UpdateImageView(R.drawable.moihoranglanh, 250);
			managersqllite.UpdateImageView(R.drawable.moman, 251);
			managersqllite.UpdateImageView(R.drawable.hieuthuoc, 252);
			managersqllite.UpdateImageView(R.drawable.manao, 253);
			managersqllite.UpdateImageView(R.drawable.kienthuc, 254);
			managersqllite.UpdateImageView(R.drawable.hanhan, 255);
			managersqllite.UpdateImageView(R.drawable.doihinh, 256);
			managersqllite.UpdateImageView(R.drawable.damam, 257);
			managersqllite.UpdateImageView(R.drawable.datinh, 258);
			managersqllite.UpdateImageView(R.drawable.cautieu, 259);
			//
			managersqllite.UpdateImageView(R.drawable.banhrang, 260);
			managersqllite.UpdateImageView(R.drawable.baikien, 261);
			managersqllite.UpdateImageView(R.drawable.baihoc, 262);
			managersqllite.UpdateImageView(R.drawable.thandong, 263);
			managersqllite.UpdateImageView(R.drawable.uotat, 264);
			managersqllite.UpdateImageView(R.drawable.nhacaocuarong, 265);
			managersqllite.UpdateImageView(R.drawable.chamcong, 266);
			managersqllite.UpdateImageView(R.drawable.ongcongongta, 267);
			managersqllite.UpdateImageView(R.drawable.canhcam, 268);
			managersqllite.UpdateImageView(R.drawable.kemchongnang, 269);
			//
			managersqllite.UpdateImageView(R.drawable.camthu, 270);
			managersqllite.UpdateImageView(R.drawable.kichcau, 271);
			managersqllite.UpdateImageView(R.drawable.chianamxebay, 272);
			managersqllite.UpdateImageView(R.drawable.ngoisaocanhac, 273);
			managersqllite.UpdateImageView(R.drawable.tauhoanhapma, 274);
			managersqllite.UpdateImageView(R.drawable.noigaioc, 275);
			managersqllite.UpdateImageView(R.drawable.banhoc, 276);
			managersqllite.UpdateImageView(R.drawable.phantran, 277);
			managersqllite.UpdateImageView(R.drawable.dangky, 278);
			managersqllite.UpdateImageView(R.drawable.nangtinh, 279);
			//
			managersqllite.UpdateImageView(R.drawable.bomtan, 280);
			managersqllite.UpdateImageView(R.drawable.daomo, 281);
			managersqllite.UpdateImageView(R.drawable.baotuong, 282);
			managersqllite.UpdateImageView(R.drawable.boica, 283);
			managersqllite.UpdateImageView(R.drawable.matmang, 284);
			managersqllite.UpdateImageView(R.drawable.cachep, 285);
			managersqllite.UpdateImageView(R.drawable.borao, 286);
			managersqllite.UpdateImageView(R.drawable.daitruyenhinh, 287);
			managersqllite.UpdateImageView(R.drawable.dangquang, 288);
			managersqllite.UpdateImageView(R.drawable.sutu, 289);
			//
			managersqllite.UpdateImageView(R.drawable.danem, 290);
			managersqllite.UpdateImageView(R.drawable.caogia, 291);
			managersqllite.UpdateImageView(R.drawable.denkeoquan, 292);
			managersqllite.UpdateImageView(R.drawable.banhtroi, 293);
			managersqllite.UpdateImageView(R.drawable.pharoi, 294);
			managersqllite.UpdateImageView(R.drawable.haucan, 295);
			managersqllite.UpdateImageView(R.drawable.bobua, 296);
			managersqllite.UpdateImageView(R.drawable.camap, 297);
			managersqllite.UpdateImageView(R.drawable.biangam, 298);
			managersqllite.UpdateImageView(R.drawable.matbungrasua, 299);
			//
			managersqllite.UpdateImageView(R.drawable.caumay, 300);
			managersqllite.UpdateImageView(R.drawable.ele, 301);

			//
			ArrayList<Cauhoi> arr = new ArrayList<Cauhoi>();
			arr = managersqllite.getALL();

			for (int i = 0; i < arr.size(); i++) {
				managersqllite.UpdateOgoiy(arr.get(i).getDapan());

			}

			managersqllite.close();

		}

	}

	private SharedPreferences pre;

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_rate:
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse("market://details?id=" + getPackageName()));
			startActivity(i);
			break;

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
