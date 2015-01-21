package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
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
		repaint();
	}
	
	public void draw(Canvas canvas){
		
		/*
		����:
		���a     1p 2p
		��ê     on  off
		����     on  off
		�ɶ�    thirty  sixty
		�Ҧ�    ÷�l   �i�R
		
		*/
		//Log.d("DEBUG","draw!?!?");
		Paint p = new Paint();
		canvas.save();
		canvas.translate(Constant.LCUX, Constant.LCUY);
		canvas.scale(Constant.RATIO, Constant.RATIO);
		
		canvas.drawBitmap(back,0,0,p );
		canvas.drawBitmap(gear,Constant.gearOffset[0][0],
				Constant.gearOffset[0][1],p);
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
		
		if(Constant.soundOn){
			canvas.drawBitmap(on,Constant.switchBtn[2][0] ,
					Constant.switchBtn[2][1], p);
			
		}
		else{
			canvas.drawBitmap(off,Constant.switchBtn[2][0] ,
					Constant.switchBtn[2][1], p);
		}

		if(Constant.timeLimit==30){
			canvas.drawBitmap(thirty,Constant.switchBtn[3][0] ,
					Constant.switchBtn[3][1], p);
			
		}
		else{
			canvas.drawBitmap(sixty,Constant.switchBtn[3][0] ,
					Constant.switchBtn[3][1], p);
		}
		
		
		if(Constant.StlyeCute){
			canvas.drawBitmap(cute,Constant.switchBtn[4][0] ,
					Constant.switchBtn[4][1], p);
		}
		else{
			canvas.drawBitmap(rope,Constant.switchBtn[4][0] ,
					Constant.switchBtn[4][1], p);
		}
		
		
	}
	
	
	public void repaint(){
		
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//���o�e��
		try{
			synchronized(holder){
				draw(canvas);//ø�s
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				//Log.d("DEBUG","unlock!?!?");
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	public void initBitmap() {
		Log.d("DEBUG","init!?!?");
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
		back =  ImageCollection.background3;
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		
		int xx=(int)((e.getX()/Constant.RATIO)-Constant.LCUX);
	 	int yy=(int)((e.getY()/Constant.RATIO)-Constant.LCUY);
	 	
	 	if(xx>=Constant.switchBtn[0][0]&&xx<=Constant.switchBtn[0][0]+350
	 			&&yy>=Constant.switchBtn[0][1]&&yy<=Constant.switchBtn[0][1]+350){
	 		if(Constant.onePlayer)Constant.onePlayer=false;
	 		else{
	 			Constant.onePlayer=true;
	 		}
	 		if(Constant.soundOn)
		 		mainActivity.soundUtil.playEffectsSound(7, 0);
	 		
	 		repaint();
	 		
	 		
	 	}
	 	else if(xx>=Constant.switchBtn[1][0]&&xx<=Constant.switchBtn[1][0]+350
	 			&&yy>=Constant.switchBtn[1][1]&&yy<=Constant.switchBtn[1][1]+350){
	 		if(Constant.bombOn)Constant.bombOn=false;
	 		else{
	 			Constant.bombOn=true;
	 		}
	 		
	 		if(Constant.soundOn)
		 		mainActivity.soundUtil.playEffectsSound(7, 0);
	 		
	 		repaint();
	 		
	 	}
	 	else if(xx>=Constant.switchBtn[2][0]&&xx<=Constant.switchBtn[2][0]+350
	 			&&yy>=Constant.switchBtn[2][1]&&yy<=Constant.switchBtn[2][1]+350){
	 		
	 		if(Constant.soundOn){
	 			mainActivity.soundUtil.stop_bg_sound();
	 			Constant.soundOn=false;
	 			
	 		}
	 		else{
	 			Constant.soundOn=true;
	 			mainActivity.soundUtil.play_bg_sound();
	 		}
	 		if(Constant.soundOn)
		 		mainActivity.soundUtil.playEffectsSound(7, 0);
	 		repaint();
	 		
	 	}
	 	else if(xx>=Constant.switchBtn[3][0]&&xx<=Constant.switchBtn[3][0]+350
	 			&&yy>=Constant.switchBtn[3][1]&&yy<=Constant.switchBtn[3][1]+350){
	 		
	 		if(Constant.timeLimit==30)Constant.timeLimit=60;
	 		else{
	 			Constant.timeLimit=30;
	 		}
	 		
	 		if(Constant.soundOn)
		 		mainActivity.soundUtil.playEffectsSound(7, 0);
	 		
	 		repaint();
	 		
	 	}
	 	else if(xx>=Constant.switchBtn[4][0]&&xx<=Constant.switchBtn[4][0]+350
	 			&&yy>=Constant.switchBtn[4][1]&&yy<=Constant.switchBtn[4][1]+350){
	 		
	 		if(Constant.StlyeCute){
	 			Constant.StlyeCute=false; //�����w�o�䪺getresource()���o���귽�O���@�˪��H�H�H
	 			ImageCollection.ropeLose= ImageCollection.rope2;
	 			ImageCollection.ropeWin = ImageCollection.rope2;
	 			ImageCollection.ropebit = ImageCollection.rope2;
	 		}
	 		else{
	 			Constant.StlyeCute=true;
	 			ImageCollection.ropeLose = ImageCollection.ropeCuteLose;  
	 			ImageCollection.ropeWin = ImageCollection.ropeCuteWin;
	 			ImageCollection.ropebit = ImageCollection.ropeCuteNormal;
	 		}
	 		
	 		if(Constant.soundOn)
		 		mainActivity.soundUtil.playEffectsSound(7, 0);
	 		
	 		repaint();	
	 		
	 	}
	 	
		
		
		
		return super.onTouchEvent(e);
	}
	
	
	
	// stop sound ---> sound_bg_off();soundOn = false; 
	// start sound ---> soundOn = true; sound_bg_off(); sound_bg_on();
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Canvas canvas=holder.lockCanvas();
		try
		{
			synchronized(holder)
			{
				draw(canvas);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas!=null)
			{
				holder.unlockCanvasAndPost(canvas);
			}
		}
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
