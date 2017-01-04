package com.nvd.duoihinhbatchu;

import java.util.ArrayList;

import android.app.Activity;



public class ManagarGAME extends Activity {
	
	private GAME g ;
	public static int leght_DapAn ; 

	public ManagarGAME() {
		super();
		g = new GAME();
		
	}
	
	public int getLeght_DapAn() {
		return leght_DapAn;
	}

	public void setLeght_DapAn(int leght_DapAn) {
		this.leght_DapAn = leght_DapAn;
	}
	
	public ArrayList<String> TaoDSOChuGoiY (int lv )
	{
		
		ArrayList<String> ds  = new ArrayList<String>();
		
		switch (lv) {
		case 1:
			ds.add("T");	ds.add("G");	ds.add("M");	ds.add("A");	ds.add("U");	ds.add("P");	ds.add("G");
			ds.add("O");	ds.add("N");	ds.add("S");	ds.add("N");	ds.add("H");	ds.add("A");	ds.add("H");
			break;
		case 2 :
			ds.add("X");	ds.add("G");	ds.add("U");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("D");
			ds.add("T");	ds.add("K");	ds.add("P");	ds.add("L");	ds.add("O");	ds.add("N");	ds.add("H");
			break;
		case 3 :
			ds.add("S");	ds.add("B");	ds.add("M");	ds.add("R");	ds.add("G");	ds.add("O");	ds.add("C");
			ds.add("Q");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("T");	ds.add("A");	ds.add("N");
			break;
		case 4 :
			ds.add("Y");	ds.add("G");	ds.add("E");	ds.add("A");	ds.add("E");	ds.add("S");	ds.add("O");
			ds.add("R");	ds.add("M");	ds.add("P");	ds.add("T");	ds.add("X");	ds.add("U");	ds.add("D");
			break;
		case 5 :
			ds.add("D");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("R");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("G");	ds.add("H");
			break;
		case 6 :
			ds.add("H");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("N");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("U");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("G");	ds.add("H");
			break;
		case 7 :
			ds.add("D");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("U");	ds.add("A");
			ds.add("C");	ds.add("T");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("C");	ds.add("H");
			break;
		case 8 :
			ds.add("T");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("I");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("R");	ds.add("C");	ds.add("H");	ds.add("O");	ds.add("N");	ds.add("H");
			break;
		case 9 :
			ds.add("U");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("T");	ds.add("R");	ds.add("C");	ds.add("H");	ds.add("O");	ds.add("N");	ds.add("H");
			break;
		case 10 :
			ds.add("C");	ds.add("G");	ds.add("O");	ds.add("U");	ds.add("E");	ds.add("C");	ds.add("A");
			ds.add("M");	ds.add("A");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("G");	ds.add("H");
			break;
		case 11 :
			ds.add("Y");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("R");	ds.add("T");	ds.add("H");	ds.add("O");	ds.add("G");	ds.add("H");
			break;
		case 12 :
			ds.add("D");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("N");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("V");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("G");	ds.add("H");
			break;
		case 13 :
			ds.add("C");	ds.add("T");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("R");	ds.add("E");	ds.add("H");	ds.add("N");	ds.add("G");	ds.add("H");
			break;
		case 14 :
			ds.add("T");	ds.add("G");	ds.add("O");	ds.add("I");	ds.add("E");	ds.add("I");	ds.add("N");
			ds.add("N");	ds.add("U");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("G");	ds.add("H");
			break;
		case 15 :
			ds.add("D");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("G");	ds.add("A");
			ds.add("T");	ds.add("R");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("N");	ds.add("H");
			break;
		case 16 :
			ds.add("D");	ds.add("T");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("T");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("G");	ds.add("H");
			break;
		case 17 :
			ds.add("T");	ds.add("G");	ds.add("O");	ds.add("T");	ds.add("U");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("R");	ds.add("T");	ds.add("H");	ds.add("O");	ds.add("G");	ds.add("H");
			break;
		case 18 :
			ds.add("D");	ds.add("G");	ds.add("I");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("N");	ds.add("R");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("C");	ds.add("H");
			break;
		case 19 :
			ds.add("H");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("I");	ds.add("T");
			ds.add("E");	ds.add("O");	ds.add("E");	ds.add("T");	ds.add("O");	ds.add("G");	ds.add("H");
			break;
		case 20 :
			ds.add("S");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("V");	ds.add("S");	ds.add("H");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		case 21 :
			ds.add("S");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("U");	ds.add("P");	ds.add("A");
			ds.add("V");	ds.add("R");	ds.add("S");	ds.add("T");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		case 22 :
			ds.add("O");	ds.add("G");	ds.add("B");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("R");	ds.add("S");	ds.add("U");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		case 23 :
			ds.add("S");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("C");	ds.add("U");	ds.add("C");	ds.add("H");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		case 24 :
			ds.add("S");	ds.add("G");	ds.add("A");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("C");	ds.add("R");	ds.add("S");	ds.add("I");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		case 25 :
			ds.add("B");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("R");	ds.add("I");	ds.add("D");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		case 26 :
			ds.add("S");	ds.add("N");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("P");	ds.add("R");	ds.add("V");	ds.add("H");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		case 27 :
			ds.add("T");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("U");
			ds.add("M");	ds.add("R");	ds.add("S");	ds.add("H");	ds.add("O");	ds.add("H");	ds.add("N");
			break;
		case 28 :
			ds.add("D");	ds.add("G");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("P");	ds.add("A");
			ds.add("M");	ds.add("A");	ds.add("L");	ds.add("H");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		case 29 :
			ds.add("S");	ds.add("M");	ds.add("O");	ds.add("A");	ds.add("E");	ds.add("U");	ds.add("A");
			ds.add("U");	ds.add("R");	ds.add("T");	ds.add("H");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		case 30 :
			ds.add("S");	ds.add("Y");	ds.add("H");	ds.add("T");	ds.add("E");	ds.add("P");	ds.add("T");
			ds.add("U");	ds.add("I");	ds.add("E");	ds.add("H");	ds.add("O");	ds.add("C");	ds.add("N");
			break;
		default:
			break;
		}
		return ds;
	}
	public ArrayList<String> TaoDSOChuDapAn (int lv )
	{
		ArrayList<String> ds  = new ArrayList<String>();
		switch (lv) {
		case 1:
			ds.add("N");	ds.add("G");	ds.add("U");	ds.add("A");	ds.add("O");	ds.add("");		ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 2:
			ds.add("T");	ds.add("H");	ds.add("O");	ds.add("N");	ds.add("G");	ds.add("L");		ds.add("E");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 3:
			ds.add("S");	ds.add("A");	ds.add("O");	ds.add("B");	ds.add("A");	ds.add("N");		ds.add("G");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 4:
			ds.add("Y");	ds.add("T");	ds.add("U");	ds.add("");		ds.add("");		ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 5:
			ds.add("H");	ds.add("O");	ds.add("G");	ds.add("A");	ds.add("");		ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 6:
			ds.add("H");	ds.add("A");	ds.add("O");	ds.add("H");	ds.add("U");	ds.add("N");		ds.add("G");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 7:
			ds.add("C");	ds.add("A");	ds.add("U");	ds.add("C");	ds.add("O");	ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 8:
			ds.add("C");	ds.add("A");	ds.add("T");	ds.add("I");	ds.add("N");	ds.add("H");		ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 9:
			ds.add("T");	ds.add("R");	ds.add("A");	ds.add("N");	ds.add("H");	ds.add("C");		ds.add("U");
			ds.add("O");	ds.add("P");	ds.add("");	 	ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break; 
		case 10:
			ds.add("C");	ds.add("O");	ds.add("C");	ds.add("U");	ds.add("A");	ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 11:
			ds.add("Y");	ds.add("T");	ds.add("A");	ds.add("");		ds.add("");		ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 12:
			ds.add("H");	ds.add("O");	ds.add("A");	ds.add("V");	ds.add("A");	ds.add("N");		ds.add("G");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 13:
			ds.add("T");	ds.add("A");	ds.add("N");	ds.add("C");	ds.add("A");	ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 14:
			ds.add("T");	ds.add("H");	ds.add("I");	ds.add("E");	ds.add("U");	ds.add("N");		ds.add("I");
			ds.add("E");	ds.add("N");	ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 15:
			ds.add("T");	ds.add("H");	ds.add("A");	ds.add("N");	ds.add("G");	ds.add("H");			ds.add("O");
			ds.add("A");	ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 16:
			ds.add("T");	ds.add("E");	ds.add("T");	ds.add("H");	ds.add("A");	ds.add("P");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 17:
			ds.add("T");	ds.add("H");	ds.add("U");	ds.add("T");	ds.add("H");	ds.add("A");		ds.add("T");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 18:
			ds.add("N");	ds.add("H");	ds.add("I");	ds.add("C");	ds.add("A");	ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 19:
			ds.add("H");	ds.add("O");	ds.add("A");	ds.add("T");	ds.add("I");	ds.add("E");		ds.add("T");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 20:
			ds.add("S");	ds.add("A");	ds.add("N");	ds.add("S");	ds.add("O");	ds.add("C");		ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 21:
			ds.add("V");	ds.add("O");	ds.add("T");	ds.add("U");	ds.add("");		ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 22:
			ds.add("O");	ds.add("N");	ds.add("G");	ds.add("B");	ds.add("A");	ds.add("U");		ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 23:
			ds.add("C");	ds.add("A");	ds.add("N");	ds.add("C");	ds.add("A");	ds.add("U");		ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 24:
			ds.add("C");	ds.add("A");	ds.add("I");	ds.add("C");	ds.add("O");	ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 25:
			ds.add("B");	ds.add("I");	ds.add("D");	ds.add("A");	ds.add("O");	ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 26:
			ds.add("P");	ds.add("H");	ds.add("A");	ds.add("N");	ds.add("V");	ds.add("A");		ds.add("N");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 27:
			ds.add("H");	ds.add("E");	ds.add("O");	ds.add("H");	ds.add("U");	ds.add("T");		ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 28:
			ds.add("D");	ds.add("A");	ds.add("O");	ds.add("L");	ds.add("A");	ds.add("M");		ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 29:
			ds.add("T");	ds.add("U");	ds.add("M");	ds.add("U");	ds.add("");		ds.add("");			ds.add("");
			ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		case 30:
			ds.add("N");	ds.add("H");	ds.add("I");	ds.add("E");	ds.add("T");	ds.add("H");		ds.add("U");
			ds.add("Y");	ds.add("E");	ds.add("T");	ds.add("");		ds.add("");		ds.add("");			ds.add("");
			break;
		default:
			break ;
			
		}
		leght_DapAn = 0 ;
		for (int i = 0 ; i < 14 ; i++)
		{
			if (ds.get(i).equals("")==false)
			{
				leght_DapAn++;
			}
		}
		return ds;
	}
	
	
}
