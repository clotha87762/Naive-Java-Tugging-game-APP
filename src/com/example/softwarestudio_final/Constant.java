package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



public class Constant {
	public static int SCREEN_HEIGHT;
	public static int SCREEN_WIDTH;
	public static int WIDTH =1080;
	public static int HEIGHT = 1920;
	public static int LCUX;
	public static int LCUY;
	public static float RATIOX;
	public static float RATIOY;
	public static float RATIO;
	public static whichPattern pattern;
	
	public static Bitmap[] Pictures;
	public static boolean BGMenabled = true;
	public static boolean effectenabled = true;
	public static boolean bombOn = true;
	public static int timeLimit=30;
	public static int life=50;
	
	public static int[][] MainMenuOffset = {
		{123,500} // GAME
		,{123,930} // HELP
		,{123,1358} //OPTION
	};
	
	
	
	
	
	enum whichPattern { Vertical , Horizonal};
}
