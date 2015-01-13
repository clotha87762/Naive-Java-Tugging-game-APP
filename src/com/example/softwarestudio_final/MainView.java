package com.example.softwarestudio_final;

import static com.example.softwarestudio_final.Constant.MainMenuOffset;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import aurelienribon.tweenengine.*;
public class MainView extends SurfaceView implements Callback {

	MainActivity mainActivity;
	/*Bitmap startButton;
	Bitmap helpButton;
	Bitmap optionButton;
	Bitmap mainBack;
	Bitmap rope;*/
	Movable startButton;
	Movable helpButton;
	Movable optionButton;
	Movable rope;
	Movable mainBack;
	TweenManager tm ;
	Thread inAnimation;
	MainDrawThread drawThread=new MainDrawThread();
	boolean isAnimationRunning=true;
	public MainView(MainActivity main){
		super(main);
		this.mainActivity=main;
		this.getHolder().addCallback(this);
		
		Tween.registerAccessor(Movable.class,new MovableAccessor());
		tm = new TweenManager();
	
		helpButton = new Movable ( BitmapFactory.decodeResource(getResources(), R.drawable.help_button),123f,-500f,255,0);
		startButton = new Movable ( BitmapFactory.decodeResource(getResources(), R.drawable.start_button),123f,-500f,255,0);
		mainBack = new Movable( BitmapFactory.decodeResource(getResources(), R.drawable.background),0,0);
		optionButton = new Movable ( BitmapFactory.decodeResource(getResources(), R.drawable.option_button),123f,-500f,255,0);
		
		
		Timeline.createSequence()
				.beginParallel()
				.push(Tween.to(startButton,MovableAccessor.POSITION_Y,1.0f).target(500f))
				.push(Tween.to(startButton,MovableAccessor.SCALE,1.0f).target(1))
				.end()
				.beginParallel()
				.push(Tween.to(helpButton,MovableAccessor.POSITION_Y,1.0f).target(930f))
				.push(Tween.to(helpButton,MovableAccessor.SCALE,1.0f).target(1))
				.end()
				.beginParallel()
				.push(Tween.to(optionButton,MovableAccessor.POSITION_Y,1.0f).target(1358f))
				.push(Tween.to(optionButton,MovableAccessor.SCALE,1.0f).target(1))
				.end()
				.start(tm);
		drawThread= new MainDrawThread();
		drawThread.start();
		
		inAnimation = new Thread(new Runnable() {
		    private long lastMillis = -1;
		    
		    @Override
		    public void run() {
		        while (isAnimationRunning) {
		            if (lastMillis > 0) {
		                long currentMillis = System.currentTimeMillis();
		                final float delta = (currentMillis - lastMillis) / 1000f;

		               
		                mainActivity.runOnUiThread(new Runnable() {
		                    public void run() {
		                        tm.update(delta);
		                        if(optionButton.getY()==1358f)isAnimationRunning = false;
		                    }
		                });

		                lastMillis = currentMillis;
		            } else {
		                lastMillis = System.currentTimeMillis();
		            }

		            try {
		                Thread.sleep(40);
		            } catch(InterruptedException ex) {
		            }
		        }
		    }
		}); 
		inAnimation.start();
		
		
		
		
	}
	
	public void draw(Canvas canvas){

		Paint p = new Paint();
		canvas.save();
		canvas.translate(Constant.LCUX, Constant.LCUY);
		canvas.scale(Constant.RATIO, Constant.RATIO);
		mainBack.draw(canvas);
		startButton.draw(canvas);
		helpButton.draw(canvas);
		optionButton.draw(canvas);
		
		/*canvas.drawBitmap(mainBack,0,0,p);
		canvas.drawBitmap(startButton,MainMenuOffset[0][0],MainMenuOffset[0][1],p);
		canvas.drawBitmap(helpButton,MainMenuOffset[1][0],MainMenuOffset[1][1],p);
		
		canvas.drawBitmap(optionButton,MainMenuOffset[2][0],MainMenuOffset[2][1],p);*/
		
		p.setARGB (200,255,0,0);
		p.setTextSize(60);
		canvas.drawText("ratio ="+Constant.RATIO+" LCUX = "+Constant.LCUX+" LCUY = "+ Constant.LCUY,0,0,p);
		canvas.drawText("SWidth="+Constant.SCREEN_WIDTH+"SHeight="+Constant.SCREEN_HEIGHT,0,100,p);
		canvas.drawText("Width="+Constant.WIDTH+"Height="+Constant.HEIGHT,0,200,p);
		canvas.restore();
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		int xx=(int)((e.getX()/Constant.RATIO)-Constant.LCUX);
	 	int yy=(int)((e.getY()/Constant.RATIO)-Constant.LCUY);
	 	Log.d("DEBUG","onMaintouch");
		if(!isAnimationRunning ){
	 	
	 	if(xx>=MainMenuOffset[0][0]&&xx<=MainMenuOffset[0][0]+startButton.bitmap.getWidth()
			&&yy>=MainMenuOffset[0][1]&&yy<=MainMenuOffset[0][1]+startButton.bitmap.getHeight()){
			mainActivity.myHandler.sendEmptyMessage(2);
		}
		else if(xx>=MainMenuOffset[1][0]&&xx<=MainMenuOffset[1][0]+helpButton.bitmap.getWidth()
				&&yy>=MainMenuOffset[1][1]&&yy<=MainMenuOffset[1][1]+helpButton.bitmap.getHeight()){
			mainActivity.myHandler.sendEmptyMessage(3);
		}
		else if(xx>=MainMenuOffset[2][0]&&xx<=MainMenuOffset[2][0]+optionButton.bitmap.getWidth()
				&&yy>=MainMenuOffset[2][1]&&yy<=MainMenuOffset[2][1]+optionButton.bitmap.getHeight()){
			mainActivity.myHandler.sendEmptyMessage(4);
		}
		 
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
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	private class MainDrawThread extends Thread {
	
		//這邊可能也要記得釋放?????????? oAo
		@Override
		public void run(){
		
				while(isAnimationRunning)
					repaint();		
				try{
					Thread.sleep(40);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{
					
				}
				
			
		//	gv.drawThreadAlive=true; //<---------------不確定要不要留著
		}
	}
	
	
	
	
	
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
