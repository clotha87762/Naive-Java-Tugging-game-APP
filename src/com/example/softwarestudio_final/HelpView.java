package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class HelpView extends SurfaceView implements Callback {

	MainActivity mainActivity;
	Bitmap helpPic[];
	int now;
	
	
	public HelpView(MainActivity main){
		super(main);
		now = 0;
		helpPic = new Bitmap[8];
		initBitmap();
		this.mainActivity=main;
		this.getHolder().addCallback(this);
		
		
	}	
	
	
	
	public void initBitmap(){
		helpPic = ImageCollection.helps;
	}
	
	
	public void draw(Canvas canvas){
		
		Paint p = new Paint();
		canvas.save();
		canvas.translate(Constant.LCUX, Constant.LCUY);
		canvas.scale(Constant.RATIO, Constant.RATIO);
		canvas.drawBitmap(helpPic[now], 0, 0, p);
		canvas.restore();
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		int action = e.getAction();
		
		switch(action){
			
		case MotionEvent.ACTION_DOWN:
				if(Constant.soundOn)
				mainActivity.soundUtil.playEffectsSound(4,0);
				
				now++;
				if(now>=8) mainActivity.myHandler.sendEmptyMessage(1);
				else{
					
					repaint();
				}
			break;
		
		}
		
		
		return super.onTouchEvent(e);
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
				//Log.d("DEBUG","unlock!?!?");
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
		if(Constant.soundOn)
		{
		
				
				this.mainActivity.soundUtil.stop_bg_sound();//停止播放背景音樂
				this.mainActivity.soundUtil.play_bg_sound();//開始停止播放背景音樂
			
		
		}
		
		
		
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
