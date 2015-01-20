package com.example.softwarestudio_final;

import com.example.softwarestudio_final.GameView;

public class DrawThread extends Thread {
	GameView gv;
	boolean isAlive;
	public DrawThread(GameView gv) {
		isAlive = true;
		this.gv = gv;
	}

	@Override
	public void run() {
		while ( isAlive ) {

			if (!gv.pause) {
				gv.repaint();
			}

			try {
				// 40 fps = 40 frames / s = 1 frame / (1/40)s = 1 / 0.025 s = 1/
				// 25ms
				Thread.sleep(Constant.fps);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}

		}
		//gv.drawThreadAlive = true; // <---------------���T�w�n���n�d��

	}
	
	public void drawStop(){
		isAlive = false;
	}
}
