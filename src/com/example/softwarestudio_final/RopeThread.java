package com.example.softwarestudio_final;

public class RopeThread extends Thread {
	Rope father;
	private int sleepSpan = 3;
	private boolean flag;
	
	public RopeThread(Rope father){
		this.father = father;
		this.flag = true; 
	}
	
	@Override
	public void run(){
		while( flag ){
			father.moveToPosition();
			try{
				Thread.sleep(sleepSpan);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void stopThread(){
		this.flag = false;
	}
	
	public boolean isRunning(){
		return this.flag;
	}
}
