package com.example.softwarestudio_final;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;
import android.view.MotionEvent;

public class PlayerA extends Player {
	float downX, upX, downY, upY;
	long start, end;
	boolean isPullEnabled = true;
	ArrayList<Bomb> bombs;
	Random r;
	BombItem item;
	
	public PlayerA(GameView gv, int life, Rope rope) {
		super(gv, life, rope);
		r = new Random();
		bombs = new ArrayList<Bomb>();
		isPullEnabled = true;
	}

	public void addBomb() {

		if (this.bombs.size() <= 4) {

			int x = r.nextInt(800);
			int y = r.nextInt(660) + 960;
			if(Constant.soundOn)
			gv.mainActivity.soundUtil.playEffectsSound(1, 0);
			this.bombs.add(new Bomb(x, y, gv));
			this.isPullEnabled = false;
			Log.d("DEBUG", "ADDA");
		}

	}
	
	
	public void addBombItem(){
		if(item==null){
			int x = r.nextInt(820);
			int y = r.nextInt(700)+960;
			item = new BombItem(x,y,gv,true);		
		}	
	}
	
	public void judgeItemGet(int x,int y){
		Log.d("DEBUG", "JUDGE Item");
		if(item!=null){
		if(x >= item.getX() && x <= item.getX() + 250 && y >= item.getY()
					&& y <= item.getY() +250){
			//���񭵮�
			if(Constant.soundOn)
			gv.mainActivity.soundUtil.playEffectsSound(3, 0);
			item = null;
			while(gv.playerB.bombs.size()<5){
				gv.playerB.addBomb();
			}
		}
		}
	}
	
	
	public boolean judgeBombDel(int x, int y) {

		Log.d("DEBUG", "JUDGE");
		for (Bomb b : bombs) {
			if (x >= b.getX() && x <= b.getX() + 300 && y >= b.getY()
					&& y <= b.getY() + 300) {
				b.next();
				if(Constant.soundOn)
				gv.mainActivity.soundUtil.playEffectsSound(2, 0);
				if (b.cur == 3)
					bombs.remove(b);
				if (bombs.isEmpty())
					this.isPullEnabled = true;
				return true;
			}
		}
		return false;
	}

	public void eventHandler(MotionEvent e, int which) {

		float xx = ((e.getX(which) / Constant.RATIO) - Constant.LCUX);
		float yy = ((e.getY(which) / Constant.RATIO) - Constant.LCUY);
		switch (e.getAction()) {

		case MotionEvent.ACTION_DOWN:
			Log.d("DEBUG", "onAdown");
			start = e.getEventTime();
			downX = xx;
			downY = yy;

			break;
		case MotionEvent.ACTION_MOVE:
			Log.d("DEBUG", "onAmove");
			break;
		case MotionEvent.ACTION_UP:
			Log.d("DEBUG", "onAup");
			end = e.getEventTime();
			upX = xx;
			upY = yy;
			if (Math.abs(upX - downX) > 100) {
				Log.d("DEBUG", "AbreakFirst");
				break;
			}
			if (upY - downY < 70
					|| (upY - downY) / ((end - start) / 1000) < 100) {
				Log.d("DEBUG", "AbreakSecond");
				break;
			}
			Log.d("DEBUG", "setA");
			rope.setPosition(10);
			break;
		default:
			break;

		}

	}

}
