package com.example.softwarestudio_final;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;
import android.view.MotionEvent;



public class PlayerA extends Player {
	float downX,upX,downY,upY;
	long start,end;
	boolean isPullEnabled = true;
	ArrayList<Bomb> bombs ;
	Random r;
	public PlayerA(GameView gv,int life,Rope rope){
		super(gv,life,rope);
		r= new Random();
		bombs = new ArrayList<Bomb>();
		isPullEnabled =true;
	}
	
	
	public void addBomb(){
		
		if(this.bombs.size()<=4){
			
			int x = r.nextInt(800);
			int y =r.nextInt(660)+960;
			
			this.bombs.add(new Bomb(x,y,gv));
			this.isPullEnabled=false;
			Log.d("DEBUG","ADDA");
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
				Log.d("DEBUG","onAdown");
				start = e.getEventTime();
				downX=xx;
				downY=yy;
				
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d("DEBUG","onAmove");
				break;
			case MotionEvent.ACTION_UP:
				Log.d("DEBUG","onAup");
				end = e.getEventTime();
				upX=xx;
				upY=yy;
				if(Math.abs(upX-downX)>100){Log.d("DEBUG","AbreakFirst");break;}
				if(upY-downY<70||(upY-downY)/((end-start)/1000)<100){Log.d("DEBUG","AbreakSecond");break;}
				Log.d("DEBUG","setA");
				rope.setPosition(10);
				break;
			default:break;
			
			}
			
			
		}
		
		
	}

