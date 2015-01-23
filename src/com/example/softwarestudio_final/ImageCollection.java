package com.example.softwarestudio_final;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ImageCollection{

	public static Bitmap helpButton, startButton, mainBack, optionButton, title;
	
	public static Bitmap background, background3;
	public static Bitmap goalA;
	public static Bitmap goalB;
	public static Bitmap ropebit;
	public static Bitmap rope2;
	
	public static Bitmap imgL, imgO, imgS, imgE;
	public static Bitmap imgW, imgI, imgN;
	public static Bitmap imgOne, imgTwo, imgThree;
	public static Bitmap player,music,mode,obstacle,time;
	public static Bitmap on,off,thirty,sixty,onep,twop,cute,rope;
	public static Bitmap gear;
	
	public static Bitmap helps[] = new Bitmap[8];
	
	public static Bitmap ropeWin, ropeLose;
	public static Bitmap ropeCuteWin, ropeCuteLose, ropeCuteNormal;
	
	public static Bitmap cute1, cute2, cute3;
	public static Bitmap rock1, rock2, rock3;
	
	public static Bitmap readBitmap(Context context, int resId){
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		
		InputStream is = context.getResources().openRawResource(resId);	
		return BitmapFactory.decodeStream(is, null, opt);
	}
}
