package com.example.softwarestudio_final;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;
import android.view.MotionEvent;


public class PlayerB extends Player {
	
	float downX,upX,downY,upY;
	long start,end;
	boolean isPullEnabled = true;
	Random r;
	ArrayList<Bomb> bombs ;
	BombItem item;
	
	public PlayerB(GameView gv,int life,Rope rope){
		super(gv,life,rope);
		r = new Random();
		bombs = new ArrayList<Bomb>();
		isPullEnabled =true;
	}
	
	public void addBomb(){
		
		if(this.bombs.size()<=4){
			int x = r.nextInt(800);
			int y =r.nextInt(660);
			
			this.bombs.add(new Bomb(x,y,gv));
			this.isPullEnabled=false;
			Log.d("DEBUG","ADDB");
		}
	}
		
	
	public void addBombItem(){
		if(item==null){
			int x = r.nextInt(820);
			int y = r.nextInt(700);
			item = new BombItem(x,y,gv);		
		}	
	}
	
	public void judgeBombDel(int x , int y){

		Log.d("DEBUG","JUDGE");	
		for(Bomb b:bombs){
			if(x>=b.getX()&&x<=b.getX()+300&&
					y>=b.getY()&&y<=b.getY()+300){
				b.next();
				if(b.cur==3)bombs.remove(b);
				if(bombs.isEmpty())this.isPullEnabled=true;
				break;
			}
		}
	}
	
		public void eventHandler(MotionEvent e,int which){
			
			float xx=((e.getX(which)/Constant.RATIO)-Constant.LCUX);
		 	float yy=((e.getY(which)/Constant.RATIO)-Constant.LCUY);
			switch(e.getAction()){
			
			
			
			case MotionEvent.ACTION_DOWN:
				Log.d("DEBUG","onBdown");
				start = e.getEventTime();
				downX=xx;
				downY=yy;
				
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d("DEBUG","onBmove");
				break;
			case MotionEvent.ACTION_UP:
				Log.d("DEBUG","onBup");
				end = e.getEventTime();
				upX=xx;
				upY=yy;
				if(Math.abs(upX-downX)>100){Log.d("DEBUG","BbreakFirst");break;}
				if(upY-downY>(-70)||(downY-upY)/((end-start)/1000)<100){Log.d("DEBUG","BbreakSecond");break;}
				
				rope.setPosition(-10);
				break;
			default:break;
			
			}
			
		
		}
}
