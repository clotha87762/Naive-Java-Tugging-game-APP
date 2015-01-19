package com.example.softwarestudio_final;

import java.util.Random;

import android.util.Log;

public class BombThread extends Thread {

	private boolean flag = true;
	GameView gv;
	int runTime;
	int temp;
	Random r = new Random();
	public  BombThread(GameView gv ){
		this.gv = gv;
		runTime = 0;
	}
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	
	@Override
	public void run(){
		
		while(flag){
			
			if(gv.pause||gv.countRunning||gv.resaultRunning)continue;
			
			runTime++;
			try{
			if(runTime%12==0){
				runTime = 0;
				
				temp = r.nextInt(20)+1;
				Log.d("DEBUG","temp= "+temp);
				if(temp==1||temp==2){ //出現在下面
					gv.playerA.addBomb();
				}
				else if(temp==3||temp==4){ //出現在上面
					gv.playerB.addBomb();
				}
				else if(temp==4||temp==5){  //同時出現
					gv.playerA.addBomb();
					gv.playerB.addBomb();					
				}
				else if(temp==6){  //道具出現在下面
					gv.playerA.addBombItem();
				}
				else if(temp==7){ //道具出現在上面
					gv.playerB.addBombItem();
				}
				
				
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				
			}
			
			
			try{
				Log.d("DEBUG","sleep");
				Thread.sleep(Constant.fps);//睡眠休息
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				
			}
			
		}
	}
	
}
