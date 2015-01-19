package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



public class Constant {
	public static int SCREEN_HEIGHT;
	public static int SCREEN_WIDTH;
	public static int WIDTH =1080;
	public static int HEIGHT = 1920;
	public static int LCUX=0;
	public static int LCUY=0;
	public static float RATIOX;
	public static float RATIOY;
	public static float RATIO;
	public static whichPattern pattern;
	
	public static Bitmap[] Pictures;
	public static boolean BGMenabled = true;
	public static boolean effectenabled = true;
	public static boolean bombOn = false//////
			
			;

	public static int timeLimit=30;

	public static int life=50;
	public static boolean onePlayer = false;
	public static int fps = 25;
	public static int[][] MainMenuOffset = {
		{123,500} // GAME
		,{123,930} // HELP
		,{123,1358} //OPTION
	};
	
	public static int[][]numberOffset1P ={
		{200,805},
		{593,805},
	};
	public static int[][]numberOffset2P ={
		{200,1300},
		{593,1300},
	};
	
	public static int[][] goalOffset = {
		{0,0},
		{0,1728}
	};
	
	
	
	enum whowins { A , B};
	enum whichPattern { Vertical , Horizonal};
}
