package com.example.softwarestudio_final;

import static com.example.softwarestudio_final.Constant.MainMenuOffset;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
public class MainView extends SurfaceView implements Callback {

	MainActivity mainActivity;
	Bitmap startButton;
	Bitmap helpButton;
	Bitmap optionButton;
	Bitmap mainBack;
	Bitmap rope;
	public MainView(MainActivity main){
		super(main);
		this.mainActivity=main;
		this.getHolder().addCallback(this);	
		startButton = BitmapFactory.decodeResource(getResources(), R.drawable.start_button);
		helpButton = BitmapFactory.decodeResource(getResources(),R.drawable.help_button);
		optionButton = BitmapFactory.decodeResource(getResources(), R.drawable.option_button);
		mainBack = BitmapFactory.decodeResource(getResources(), R.drawable.background);
	}
	
	public void draw(Canvas canvas){

		Paint p = new Paint();
		canvas.save();
		canvas.translate(Constant.LCUX, Constant.LCUY);
		canvas.scale(Constant.RATIO, Constant.RATIO);
		
		canvas.drawBitmap(mainBack,0,0,p);
		canvas.drawBitmap(startButton,MainMenuOffset[0][0],MainMenuOffset[0][1],p);
		canvas.drawBitmap(helpButton,MainMenuOffset[1][0],MainMenuOffset[1][1],p);
		canvas.drawBitmap(optionButton,MainMenuOffset[2][0],MainMenuOffset[2][1],p);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		int xx=(int)((e.getX()/Constant.RATIO)-Constant.LCUX);
	 	int yy=(int)((e.getY()/Constant.RATIO)-Constant.LCUY);
	 	Log.d("DEBUG","onMaintouch");
		if(xx>=MainMenuOffset[0][0]&&xx<=MainMenuOffset[0][0]+startButton.getWidth()
			&&yy>=MainMenuOffset[0][1]&&yy<=MainMenuOffset[0][1]+startButton.getHeight()){
			mainActivity.myHandler.sendEmptyMessage(2);
		}
		else if(xx>=MainMenuOffset[1][0]&&xx<=MainMenuOffset[1][0]+helpButton.getWidth()
				&&yy>=MainMenuOffset[1][1]&&yy<=MainMenuOffset[1][1]+helpButton.getHeight()){
			mainActivity.myHandler.sendEmptyMessage(3);
		}
		else if(xx>=MainMenuOffset[2][0]&&xx<=MainMenuOffset[2][0]+optionButton.getWidth()
				&&yy>=MainMenuOffset[2][1]&&yy<=MainMenuOffset[2][1]+optionButton.getHeight()){
			mainActivity.myHandler.sendEmptyMessage(4);
		}
		return super.onTouchEvent(e); 
		
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
