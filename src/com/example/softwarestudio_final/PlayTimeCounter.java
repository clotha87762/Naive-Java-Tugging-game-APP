package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;


public class PlayTimeCounter {
		
	int runTime=-1;
	GameView gv;
	int index10S,indexS;
	Bitmap[] numbers=new Bitmap[10];
	

	public PlayTimeCounter(GameView gv){
		index10S=Constant.timeLimit/10;
		indexS=Constant.timeLimit%10;
		this.gv = gv;
		numbers[0]= BitmapFactory.decodeResource(gv.getResources(), R.drawable.zero);
		numbers[1]  = BitmapFactory.decodeResource(gv.getResources(), R.drawable.one);
		numbers[2]  = BitmapFactory.decodeResource(gv.getResources(), R.drawable.two);
		numbers[3] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.three);
		numbers[4] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.four);
		numbers[5] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.five);
		numbers[6] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.six);
		numbers[7] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.seven);
		numbers[8] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.eight);
		numbers[9] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.nine);
		
		
		TimeThread timeThread = new TimeThread();
		timeThread.start();
	}
	
	public void draw(Canvas canvas){
		Paint p = new Paint();	
		if(runTime>=Constant.timeLimit-5){
			float[] mat = new float[] {
			        1f, 1f, 1f, 1f, 1f,
			        0f, 0f, 0f, 0f, 0f,
			        0f, 0f, 0f, 0f, 0f,
			        0f, 0f, 0f, 1f, 0f,
			        2f, 0f, 0f, 0f, 1f };

			ColorMatrix colorMatrix = new ColorMatrix(mat);
			ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
			p.setColorFilter(colorFilter);
		}
		if(Constant.onePlayer){
		canvas.drawBitmap(numbers[index10S], Constant.numberOffset1P[0][0], Constant.numberOffset1P[0][1], p);
		canvas.drawBitmap(numbers[indexS], Constant.numberOffset1P[1][0], Constant.numberOffset1P[1][1], p);
		}
		else{
			canvas.drawBitmap(numbers[index10S], Constant.numberOffset2P[0][0], Constant.numberOffset2P[0][1], p);
			canvas.drawBitmap(numbers[indexS], Constant.numberOffset2P[1][0], Constant.numberOffset2P[1][1], p);
			canvas.save();
			canvas.rotate(180,540,960);
			canvas.drawBitmap(numbers[index10S], Constant.numberOffset2P[0][0], Constant.numberOffset2P[0][1], p);
			canvas.drawBitmap(numbers[indexS], Constant.numberOffset2P[1][0], Constant.numberOffset2P[1][1], p);
			canvas.restore();
		}
	}
	public int getRunTime(){
		return runTime;
	}
	
	private class TimeThread extends Thread
	{
		@Override
		public void run() {
			while(runTime<Constant.timeLimit)
			{
				if(!gv.pause&&!gv.countRunning&&!gv.resaultRunning)
				{
					runTime++;
				
					
					index10S=(Constant.timeLimit-runTime)/10;//絤策家Α10
					indexS=(Constant.timeLimit-runTime)%10;//絤策家Α
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(runTime>=Constant.timeLimit)
			{
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				//gv.drawThreadAlive=false;//gameview 礶絬祘氨ゎ
				gv.judgeResault();
			}
		}
	}
	
}
