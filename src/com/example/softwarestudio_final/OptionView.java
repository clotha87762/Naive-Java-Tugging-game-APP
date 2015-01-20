package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class OptionView extends SurfaceView implements Callback {

	MainActivity mainActivity;
	
	 Bitmap player,music,mode,obstacle,time;
	 Bitmap on,off,thirty,sixty,onep,twop,cute,rope;
	 Bitmap gear,back;
	
	
	
	
	
	
	
	public OptionView(MainActivity main){
		super(main);
		this.mainActivity = main;
		this.getHolder().addCallback(this);
		initBitmap();
	}
	
	public void draw(Canvas canvas){
		
		/*
		順序:
		玩家     1p 2p
		障礙     on  off
		音樂     on  off
		時間    thirty  sixty
		模式    繩子   可愛
		
		*/
		Paint p = new Paint();
		canvas.save();
		canvas.translate(Constant.LCUX, Constant.LCUY);
		canvas.scale(Constant.RATIO, Constant.RATIO);
		canvas.drawBitmap(back,0,0,p );
		canvas.drawBitmap(gear,Constant.gearOffset[0][0],
				Constant.gearOffset[0][1],p );
		canvas.drawBitmap(gear,Constant.gearOffset[1][0],
				Constant.gearOffset[1][1],p );
		canvas.drawBitmap(gear,Constant.gearOffset[2][0],
				Constant.gearOffset[2][1],p );
		canvas.drawBitmap(gear,Constant.gearOffset[3][0],
				Constant.gearOffset[3][1],p );
		canvas.drawBitmap(gear,Constant.gearOffset[4][0],
				Constant.gearOffset[4][1],p );
		canvas.drawBitmap(player,Constant.whatOffset[0][0],
				Constant.whatOffset[0][1], p);
		canvas.drawBitmap(obstacle,Constant.whatOffset[1][0],
				Constant.whatOffset[1][1], p);
		canvas.drawBitmap(music,Constant.whatOffset[2][0],
				Constant.whatOffset[2][1], p);
		canvas.drawBitmap(time,Constant.whatOffset[3][0],
				Constant.whatOffset[3][1], p);
		canvas.drawBitmap(mode,Constant.whatOffset[4][0],
				Constant.whatOffset[4][1], p);
		if(Constant.onePlayer){
		canvas.drawBitmap(onep,Constant.switchBtn[0][0] ,
				Constant.switchBtn[0][1], p);
		}else{
		canvas.drawBitmap(twop,Constant.switchBtn[0][0] ,
					Constant.switchBtn[0][1], p);
		}
		
		if(Constant.bombOn){
			canvas.drawBitmap(on,Constant.switchBtn[1][0] ,
					Constant.switchBtn[1][1], p);
			
		}
		else{
			canvas.drawBitmap(off,Constant.switchBtn[1][0] ,
					Constant.switchBtn[1][1], p);
		}
		
		if(){
			
		}
		else{
			
		}

		if(){
	
		}
		else{
	
		}
		
		
		if(){
			
		}
		else{
			
		}
		
		
		canvas.restore();
	}
	
	
	public void repaint(){
		
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//取得畫布
		try{
			synchronized(holder){
				draw(canvas);//繪製
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	public void initBitmap() {
		player = ImageCollection.player;
		mode = ImageCollection.mode;
		time = ImageCollection.time;
		obstacle = ImageCollection.obstacle;
		music = ImageCollection.music;
		onep = ImageCollection.onep;
		twop = ImageCollection.twop;
		thirty = ImageCollection.thirty;
		sixty = ImageCollection.sixty;
		on = ImageCollection.on;
		off = ImageCollection.off;
		rope = ImageCollection.rope;
		cute = ImageCollection.cute;
		gear= ImageCollection.gear;
		back = ImageCollection.background;
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		
		
		return super.onTouchEvent(e);
	}
	
	
	
	// stop sound ---> sound_bg_off();soundOn = false; 
	// start sound ---> soundOn = true; sound_bg_off(); sound_bg_on();
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}
