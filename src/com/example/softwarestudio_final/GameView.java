package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Callback {

	MainActivity mainActivity;
	boolean drawThreadAlive;
	DrawThread dt;
	boolean pause=true;
	PlayTimeCounter playTime;
	PlayerA playerA;
	PlayerB playerB;
	Rope rope;
	Rect rec;
	
	public GameView(MainActivity main){
		super(main);
		this.mainActivity = main;
		this.getHolder().addCallback(this);
		dt = new DrawThread(this);
		rec = new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);
		this.setLongClickable(true);
		drawThreadAlive =true;
		dt.start();
		this.getHolder().addCallback(this);
		Bitmap ropebit = BitmapFactory.decodeResource(getResources(), R.drawable.rope);
		rope = new Rope (this,ropebit);
		playerA = new PlayerA(this,Constant.life,rope);
		playerB = new PlayerB(this,Constant.life,rope);
		PlayTimeCounter timeCounter = new PlayTimeCounter(this);
		drawReady();
	
	}
	
	public void drawReady(){
		//要加入預備動畫
		pause= false;
	}
	
	public void drawResault(){
		
	}
	
	
	public void repaint()
	{
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
	public void draw(Canvas canvas){
		
		Paint p = new Paint();
		
		p.setARGB(255,255,255,255);
		canvas.drawRect(rec, p);
		canvas.save();
		canvas.translate(Constant.LCUX, Constant.LCUY);
		canvas.scale(Constant.RATIO, Constant.RATIO);
		p=new Paint();
		rope.drawself(canvas);
			canvas.restore();
			
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e){
		
		int xx=(int)((e.getX()/Constant.RATIO)-Constant.LCUX);
	 	int yy=(int)((e.getY()/Constant.RATIO)-Constant.LCUY);
	 	
	 	
	 	
	 	
	 	
	 	if(yy<Constant.HEIGHT/2){
	 		Log.d("DEBUG","giveB");
	 		playerB.th.eventHandler(e);
	 		
	 	}
	 	else{
	 		Log.d("DEBUG","giveA");
	 		playerA.eventHandler(e);
	 	}
	 	
	 
	 	
		return super.onTouchEvent(e);
	}
	
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
