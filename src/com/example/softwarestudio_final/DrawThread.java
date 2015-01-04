package com.example.softwarestudio_final;

import com.example.softwarestudio_final.GameView;

public class DrawThread extends Thread {
	GameView gv ;
	public DrawThread (GameView gv){
		
		this.gv=gv;
	}
	@Override
	public void run(){
		while(gv.drawThreadAlive){
			
			
			if(!gv.pause){
			gv.repaint();
			}
				
			try{
				Thread.sleep(40);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				
			}
			
		}
		gv.drawThreadAlive=true; //<---------------不確定要不要留著
	
	}
}
