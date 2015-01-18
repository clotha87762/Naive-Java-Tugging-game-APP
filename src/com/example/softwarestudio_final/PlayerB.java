package com.example.softwarestudio_final;

import android.util.Log;
import android.view.MotionEvent;


public class PlayerB extends Player {
	
	float downX,upX,downY,upY;
	long start,end;
	public PlayerB(GameView gv,int life,Rope rope){
		super(gv,life,rope);
		
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
