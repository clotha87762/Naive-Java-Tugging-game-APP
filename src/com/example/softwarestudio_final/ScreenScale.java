package com.example.softwarestudio_final;

import com.example.testyo.Constant;


public class ScreenScale {
		
	static  float sHpWidth=1920;//原始橫式螢幕的寬度
	static  float sHpHeight=1080;//原始橫式螢幕的高度
	static  float whHpRatio=sHpWidth/sHpHeight;//原始橫式螢幕的寬高比
	
	static  float sSpWidth=1080;//原始直式螢幕的寬度
	static  float sSpHeight=1920;//原始直式螢幕的高度
	static  float whSpRatio=sSpWidth/sSpHeight;//原始直式螢幕的寬高比
	
	public static void calScale
	(
		float targetWidth,	//目標寬度
		float targetHeight	//目標高度
	)
	{
	
		 sHpWidth=Constant.WIDTH;//原始橫式螢幕的寬度
		 sHpHeight=Constant.HEIGHT;//原始橫式螢幕的高度
		 whHpRatio=sHpWidth/sHpHeight;//原始橫式螢幕的寬高比
		
		 sSpWidth=Constant.WIDTH;//原始直式螢幕的寬度
		 sSpHeight=Constant.HEIGHT;//原始直式螢幕的高度
		 whSpRatio=sSpWidth/sSpHeight;//原始直式螢幕的寬高比
		//首先判斷目標是橫式螢幕還是直式螢幕
		/*if(targetWidth>targetHeight)
		{
			Constant.pattern=Constant.whichPattern.Horizonal;
		}
		else
		{
			Constant.pattern=Constant.whichPattern.Vertical;
		}*/
		
		//進行橫式螢幕結果的計算
		/*if(Constant.pattern==Constant.whichPattern.Horizonal)
		{
			//計算目標的寬高比
			float targetRatio=targetWidth/targetHeight;
			
			if(targetRatio>whHpRatio)
			{
				//若目標寬高比大於原始寬高比則以目標的高度計算結果			    
			    float ratio=targetHeight/sHpHeight;
			    float realTargetWidth=sHpWidth*ratio;
			    float lcuX=(targetWidth-realTargetWidth)/2.0f;
			    float lcuY=0;
			   Constant.LCUX = (int)lcuX;
			   Constant.LCUY = (int)lcuY;
			   Constant.RATIO=ratio;
			}
			else
			{
				//若目標寬高比小於原始寬高比則以目標的寬度計算結果	
				float ratio=targetWidth/sHpWidth;
				float realTargetHeight=sHpHeight*ratio;
				float lcuX=0;
				float lcuY=(targetHeight-realTargetHeight)/2.0f;
				Constant.LCUX = (int)lcuX;
				   Constant.LCUY = (int)lcuY;
				   Constant.RATIO=ratio;
			}
		}
		*/
		//進行直式螢幕結果的計算
		//if(Constant.pattern==Constant.whichPattern.Vertical)
		
			//計算目標的寬高比
			float targetRatio=targetWidth/targetHeight;
			
			if(targetRatio>whSpRatio)
			{
				//若目標寬高比大於原始寬高比則以目標的高度計算結果			    
			    float ratio=targetHeight/sSpHeight;
			    float realTargetWidth=sSpWidth*ratio;
			    float lcuX=(targetWidth-realTargetWidth)/2.0f;
			    float lcuY=0;
			    Constant.LCUX = (int)lcuX;
				   Constant.LCUY = (int)lcuY;
				   Constant.RATIO=ratio;
			}
			else
			{
				//若目標寬高比小於原始寬高比則以目標的寬度計算結果	
				float ratio=targetWidth/sSpWidth;
				float realTargetHeight=sSpHeight*ratio;
				float lcuX=0;
				float lcuY=(targetHeight-realTargetHeight)/2.0f;
				Constant.LCUX = (int)lcuX;
				   Constant.LCUY = (int)lcuY;
				   Constant.RATIO=ratio;
			}
		
		
		Constant.RATIOX = targetWidth/sSpHeight;
		Constant.RATIOY = targetHeight/sSpWidth;
		return;
	}
}
