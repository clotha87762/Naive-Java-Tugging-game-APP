package com.example.softwarestudio_final;

import android.util.Log;
import android.view.MotionEvent;



public class PlayerA extends Player {
	float downX,upX,downY,upY;
	long start,end;
	public PlayerA(GameView gv,int life,Rope rope){
		super(gv,life,rope);
	
	}
	
	
		
		
		
		public void eventHandler(MotionEvent e){
			
			float xx=((e.getX()/Constant.RATIO)-Constant.LCUX);
		 	float yy=((e.getY()/Constant.RATIO)-Constant.LCUY);
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

