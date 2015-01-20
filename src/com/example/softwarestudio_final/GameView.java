package com.example.softwarestudio_final;

import java.util.ArrayList;
import java.util.List;

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
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class GameView extends SurfaceView implements Callback {

	MainActivity mainActivity;
	boolean drawThreadAlive;
	DrawThread dt;
	boolean pause = true;
	PlayTimeCounter playTime;
	Bitmap background;
	PlayerA playerA;
	PlayerB playerB;
	Rope rope;
	Rect rec;
	Bitmap goalA;
	Bitmap goalB;
	Bitmap ropebit;
	Movable one;
	Movable two;
	Movable three;

	Movable L, O, S, E;
	Movable W, I, N;

	BombThread bombThread;
	Thread count;

	Thread resault;
	TweenManager tm;
	boolean countRunning = true;
	boolean resaultRunning = false;
	int whoWins = 0;

	public GameView(MainActivity main) {
		super(main);
		this.mainActivity = main;
		this.getHolder().addCallback(this);
		dt = new DrawThread(this);
		rec = new Rect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
		this.setLongClickable(true);
		initBitmap();

		drawThreadAlive = true;
		countRunning = true;
		resaultRunning = false;

		tm = new TweenManager();
		this.getHolder().addCallback(this);
		rope = new Rope(this, ropebit);

		playerA = new PlayerA(this, Constant.life, rope);
		playerB = new PlayerB(this, Constant.life, rope);
		playTime = new PlayTimeCounter(this);

		bombThread = new BombThread(this);

		dt.start();
		if (Constant.bombOn) {
			bombThread.start();
		}

		drawReady();

	}

	public void initBitmap() {
		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.gameback);
		goalA = BitmapFactory.decodeResource(getResources(), R.drawable.goala);
		goalB = BitmapFactory.decodeResource(getResources(), R.drawable.goalb);
		ropebit = BitmapFactory.decodeResource(getResources(),
				R.drawable.rope2);
		one = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.count1), 0, 0, 255, 0.5f);
		two = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.count2), 0, 0, 255, 0.5f);
		three = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.count3), 0, 0, 255, 0.5f);
		two.visible = false;
		one.visible = false;
		L = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.ll), 140, -300);
		O = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.o), 340, -300);
		S = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.s), 540, -300);
		E = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.e), 740, -300);
		W = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.w), 290, -300);
		I = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.i), 490, -300);
		N = new Movable(BitmapFactory.decodeResource(getResources(),
				R.drawable.n), 590, -300);

	}

	public void drawReady() {
		// ï¿½nï¿½[ï¿½Jï¿½wï¿½Æ°Êµe
		pause = false;
		Timeline.createSequence()
				.push(Tween.to(three, MovableAccessor.SCALE, 1.0f).target(0))
				.push(Tween.to(two, MovableAccessor.SCALE, 1.0f).target(0))
				.push(Tween.to(one, MovableAccessor.SCALE, 1.0f).target(0))
				.start(tm);

		count = new Thread(new Runnable() {

			private long lastMillis = -1;
			boolean whistle = true;
			@Override
			public void run() {
				while (countRunning) {
					if (lastMillis > 0) {
						long currentMillis = System.currentTimeMillis();
						final float delta = (currentMillis - lastMillis) / 1000f;

						/*mainActivity.runOnUiThread(new Runnable() {
							public void run() {*/
								tm.update(delta);
								if (three.visible == false) {
									two.visible = true;
								} else if (three.visible == false
										&& two.visible == false) {
									one.visible = true;
								}
								
								if(one.getScale()<0.3){
									if(whistle){
										mainActivity.soundUtil.playEffectsSound(5, 0);
										whistle=false;
									}
								}
								
								if (one.getScale() == 0) {
									
									try {
										Thread.sleep(500);
									} catch (InterruptedException ex) {
									}
									countRunning = false;
									
									Constant.fps = 40;
								}
							//}
						//});

						lastMillis = currentMillis;
					} else {
						lastMillis = System.currentTimeMillis();
					}

					try {
						Thread.sleep(Constant.fps );
					} catch (InterruptedException ex) {
					}
				}
			}
		});
		count.start();

	}

	public void judgeResault() {

		if (rope.getCurrentPosition() < -890)
			drawResault(1);
		else {
			drawResault(0);
		}
	}

	public void endGameViewbyKEYBACK(){
		countRunning = false;
		resaultRunning = false;
		drawThreadAlive=false;
		bombThread.setFlag(false);
		Constant.soundTitle =true;
		
		
	}
	
	public void drawResault(int who) { // 0==A , 1==B;

		whoWins = who;
		resaultRunning = true;
		bombThread.setFlag(false);
		tm = new TweenManager();

		Constant.fps = 25;
		Timeline.createSequence()
				.beginParallel()
				.push(Tween.to(W, MovableAccessor.POSITION_Y, 2.0f)
						.target(1300))
				.push(Tween.to(L, MovableAccessor.POSITION_Y, 2.5f)
						.target(1300))
				.push(Tween.to(I, MovableAccessor.POSITION_Y, 3.0f)
						.target(1300))
				.push(Tween.to(O, MovableAccessor.POSITION_Y, 2.0f)
						.target(1300))
				.push(Tween.to(N, MovableAccessor.POSITION_Y, 2.33f).target(
						1300))
				.push(Tween.to(S, MovableAccessor.POSITION_Y, 2.66f).target(
						1300))
				.push(Tween.to(E, MovableAccessor.POSITION_Y, 3.0f)
						.target(1300)).end().start(tm);
		resault = new Thread(new Runnable() {
			private long lastMillis = -1;
			private int time = 0;

			@Override
			public void run() {
				while (resaultRunning) {
					if (lastMillis > 0) {
						long currentMillis = System.currentTimeMillis();
						final float delta = (currentMillis - lastMillis) / 1000f;

						//mainActivity.runOnUiThread(new Runnable() {
							//public void run() {
								tm.update(delta);
								time++;
								if (time > 150) {
									resaultRunning = false;
									Constant.fps = 40;
									drawThreadAlive = false;
									Constant.soundTitle =true;
									
									mainActivity.myHandler.sendEmptyMessage(1);
								}

							//}
						//});

						lastMillis = currentMillis;
					} else {
						lastMillis = System.currentTimeMillis();
					}

					try {
						Thread.sleep(Constant.fps);
					} catch (InterruptedException ex) {
					}
				}
			}
		});

		resault.start();

		// mainActivity.myHandler.sendEmptyMessage(1);
	}

	public void freeBitmap() {
		background.recycle();
		goalA.recycle();
		goalB.recycle();
		ropebit.recycle();
		one = null;
		two = null;
		three = null;
		L = null;
		O = null;
		S = null;
		E = null;
		W = null;
		I = null;
		N = null;
	}

	public void repaint() {
		SurfaceHolder holder = this.getHolder();
		Canvas canvas = holder.lockCanvas();// ï¿½ï¿½ï¿½oï¿½eï¿½ï¿½

		try {
			synchronized (holder) {
				draw(canvas);// Ã¸ï¿½s
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void draw(Canvas canvas) {

		Paint p = new Paint();

		p.setARGB(255, 255, 255, 255);

		canvas.drawRect(rec, p);
		canvas.save();
		canvas.translate(Constant.LCUX, Constant.LCUY);
		canvas.scale(Constant.RATIO, Constant.RATIO);
		p = new Paint();
		p.setAntiAlias(true);
		canvas.drawBitmap(background, 0, 0, p);
		canvas.drawBitmap(goalA, Constant.goalOffset[1][0],
				Constant.goalOffset[1][1], p);
		canvas.drawBitmap(goalB, Constant.goalOffset[0][0],
				Constant.goalOffset[0][1], p);
		playTime.draw(canvas);
		rope.drawself(canvas);

		for (Bomb b : playerA.bombs) {
			// Log.d("DEBUG","drawbomba");
			b.draw(canvas);
		}
		if(playerA.item!=null)playerA.item.draw(canvas);
		for (Bomb b : playerB.bombs) {
			// Log.d("DEBUG","drawbombb");
			b.draw(canvas);
		}
		if(playerB.item!=null){
			
			playerB.item.draw(canvas);
			
		}
		
		
		if (countRunning) {
			three.draw(canvas);
			two.draw(canvas);
			one.draw(canvas);
		} else if (resaultRunning) {
			if (resaultRunning && whoWins == 1)
				canvas.rotate(180, 540, 960);

			W.draw(canvas);
			I.draw(canvas);
			N.draw(canvas);
			L.drawReverse(canvas);
			O.drawReverse(canvas);
			S.drawReverse(canvas);
			E.drawReverse(canvas);

		}
		canvas.restore();

	}

	float HDownX, HDownY, HUpX, HUpY, LDownX, LDownY, LUpX, LUpY;
	int mainWhich, subWhich; // 1 => ï¿½Wï¿½ï¿½ 0 => ï¿½Uï¿½ï¿½
	int moveRange = 50;

	@Override
	public boolean onTouchEvent(MotionEvent e) {

		if (countRunning || resaultRunning || pause)
			return false;
		int action = e.getAction() & MotionEvent.ACTION_MASK;
		int xx0, xx1 = 0, yy0, yy1 = 0;

		int pointerCount = e.getPointerCount();
		int id = (e.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >>> MotionEvent.ACTION_POINTER_ID_SHIFT;

		/*
		 * xx0=(int)((e.getX(0)/Constant.RATIO)-Constant.LCUX);
		 * yy0=(int)((e.getY(0)/Constant.RATIO)-Constant.LCUY);
		 * if(pointerCount>=2){
		 * xx1=(int)((e.getX(1)/Constant.RATIO)-Constant.LCUX);
		 * yy1=(int)((e.getY(1)/Constant.RATIO)-Constant.LCUY); }
		 */
		float xx = ((e.getX(id) / Constant.RATIO) - Constant.LCUY);
		float yy = ((e.getY(id) / Constant.RATIO) - Constant.LCUY);

		switch (action) {

		case MotionEvent.ACTION_DOWN:
			Log.d("DEBUG", "MD " + xx + "  " + yy);

			if (yy < 960) {
				Log.d("DEBUG", "YY<960");
				if (!playerB.judgeBombDel((int) xx, (int) yy))
					playerB.judgeItemGet((int) xx, (int) yy);

				HDownY = yy;
				HDownX = xx;
			} else {
				if (!playerA.judgeBombDel((int) xx, (int) yy))
					playerA.judgeItemGet((int) xx, (int) yy);
				LDownY = yy;
				LDownX = xx;
			}

			/*
			 * mainDownX=e.getX(0); mainDownY=e.getY(0); Log.d("DEBUG","MD");
			 * if(mainDownY<960) { mainWhich = 1; } else{ mainWhich=0; }
			 */
			break;
		case MotionEvent.ACTION_UP:
			Log.d("DEBUG", "MU " + xx + "  " + yy);
			if (yy < 960) {
				if (yy - HDownY < -90 && Math.abs(xx - HDownX) < 200
						&& playerB.isPullEnabled) {
					moveRange = Math.abs((int) 40/* ( yy - HDownY) */);
					mainActivity.soundUtil.playEffectsSound(0, 0);
					rope.setPosition(-moveRange);
				}
			} else {
				if (yy - LDownY > 90 && Math.abs(xx - LDownX) < 200
						&& playerA.isPullEnabled) {
					moveRange = Math.abs((int) 40 /* (yy - HDownY) */);
					mainActivity.soundUtil.playEffectsSound(0, 0);
					rope.setPosition(moveRange);
				}
			}
			/*
			 * mainUpX = e.getX(0); mainUpY = e.getY(0); Log.d("DEBUG","MU");
			 * if(
			 * mainWhich==1&&mainUpY-mainDownY<-70&&Math.abs(mainDownX-mainUpX
			 * )<100) rope.setPosition(-10); else
			 * if(mainWhich==0&&mainUpY-mainDownY
			 * >70&&Math.abs(mainDownX-mainUpX)<100) rope.setPosition(10);
			 */
			break;
		case MotionEvent.ACTION_POINTER_DOWN:

			Log.d("DEBUG", "SD " + xx + "  " + yy);
			if (yy < 960) {
				if (!playerB.judgeBombDel((int) xx, (int) yy))
					playerB.judgeItemGet((int) xx, (int) yy);
				HDownY = yy;
				HDownX = xx;
			} else {
				if (!playerA.judgeBombDel((int) xx, (int) yy))
					playerA.judgeItemGet((int) xx, (int) yy);
				LDownY = yy;
				LDownX = xx;
			}

			/*
			 * subDownX = e.getX(1); subDownY = e.getY(1); Log.d("DEBUG","SD");
			 * if(subDownY<960) { subWhich = 1; } else{ subWhich=0; }
			 */

			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.d("DEBUG", "SU " + xx + "  " + yy);

			if (yy < 960) {
				if (yy - HDownY < -90 && Math.abs(xx - HDownX) < 200
						&& playerB.isPullEnabled) {
					moveRange = Math.abs((int) (40/* yy - HDownY */));
					mainActivity.soundUtil.playEffectsSound(0, 0);
					rope.setPosition(-moveRange);
				}
			} else {
				if (yy - LDownY > 90 && Math.abs(xx - LDownX) < 200
						&& playerA.isPullEnabled) {
					moveRange = Math.abs((int) (40/* yy - HDownY */));
					mainActivity.soundUtil.playEffectsSound(0, 0);
					rope.setPosition(moveRange);
				}
			}
			/*
			 * subUpX = e.getX(1); subUpY = e.getY(1); Log.d("DEBUG","SU");
			 * if(subWhich
			 * ==1&&mainUpY-mainDownY<-70&&Math.abs(mainDownX-mainUpX)<100)
			 * rope.setPosition(-10); else
			 * if(subWhich==0&&mainUpY-mainDownY>70&&
			 * Math.abs(mainDownX-mainUpX)<100) rope.setPosition(10);
			 */

			break;
		case MotionEvent.ACTION_MOVE:

			break;

		}

		/*
		 * int pointerCount = e.getPointerCount();
		 * 
		 * if(pointerCount==1){ int
		 * xx1=(int)((e.getX()/Constant.RATIO)-Constant.LCUX); int
		 * yy1=(int)((e.getY()/Constant.RATIO)-Constant.LCUY);
		 * Log.d("DEBUG",xx1+"  "+yy1); if(yy1<Constant.HEIGHT/2){
		 * Log.d("DEBUG","giveB"); playerB.eventHandler(e,0);
		 * 
		 * } else{ Log.d("DEBUG","giveA"); playerA.eventHandler(e,0); }
		 * 
		 * 
		 * 
		 * } else if(pointerCount==2){
		 * 
		 * int xx1=(int)((e.getX(0)/Constant.RATIO)-Constant.LCUX); int
		 * yy1=(int)((e.getY(0)/Constant.RATIO)-Constant.LCUY); int
		 * xx2=(int)((e.getX(1)/Constant.RATIO)-Constant.LCUX); int
		 * yy2=(int)((e.getY(1)/Constant.RATIO)-Constant.LCUY);
		 * Log.d("DEBUG","1!  "+xx1+"  "+yy1);
		 * Log.d("DEBUG","2!  "+xx2+"  "+yy2); if(yy1<Constant.HEIGHT/2){
		 * Log.d("DEBUG","giveB"); playerB.eventHandler(e,0);
		 * playerA.eventHandler(e,1);
		 * 
		 * } else{ Log.d("DEBUG","giveA"); playerA.eventHandler(e,0);
		 * playerB.eventHandler(e,1);
		 * 
		 * 
		 * } }
		 */

		return super.onTouchEvent(e);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if(Constant.soundOn)
		{
		
				
				this.mainActivity.soundUtil.stop_bg_sound();//°±¤î¼½©ñ­I´º­µ¼Ö
				this.mainActivity.soundUtil.play_bg_sound();//¶}©l°±¤î¼½©ñ­I´º­µ¼Ö
			
		
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
		freeBitmap();
	}

}
