package com.example.softwarestudio_final;

import java.util.Random;

import android.util.Log;

public class BombThread extends Thread {

	private boolean flag = true;
	GameView gv;
	int runTime;
	int temp;
	int itemTimeA=0,itemTimeB=0;
	boolean itemAflag=false,itemBflag=false;
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
			
			if(itemAflag)itemTimeA++;
			if(itemBflag)itemTimeB++;
			
			runTime++;
			try{
			
				if(itemTimeA>20){
					gv.playerA.item = null;
					itemAflag=false;
				}
				if(itemTimeB>20){
					gv.playerB.item = null;
					itemBflag=false;
				}
				
				if(runTime%15==0){
				runTime = 0;
				
				temp = r.nextInt(30)+1;
				Log.d("DEBUG","temp= "+temp);
				if(temp==1||temp==2||temp==3){ //�X�{�b�U��
					gv.playerA.addBomb();
				}
				else if(temp==5||temp==4||temp==6){ //�X�{�b�W��
					gv.playerB.addBomb();
				}
				else if(temp==7||temp==8||temp==9){  //�P�ɥX�{
					gv.playerA.addBomb();
					gv.playerB.addBomb();					
				}
				else if(temp==10){  //�D��X�{�b�U��
					if(!itemAflag&&gv.rope.getPosition()<-890){
					itemAflag=true;
					itemTimeA=0;
					gv.playerA.addBombItem();
					}
				}
				else if(temp==11){ //�D��X�{�b�W��
					if(!itemBflag&&gv.rope.getPosition()>-890){
					itemBflag=true;
					itemTimeB=0;
					gv.playerB.addBombItem();
					}
				}
				
				
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				
			}
			
			
			try{
				//Log.d("DEBUG","sleep");
				Thread.sleep(Constant.fps);//�ίv��
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				
			}
			
		}
	}
	
}
