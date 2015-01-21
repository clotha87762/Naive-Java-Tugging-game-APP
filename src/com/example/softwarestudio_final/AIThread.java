package com.example.softwarestudio_final;

import java.util.Random;

public class AIThread extends Thread {

	GameView gv;
	boolean flag;
	Random r;
	int runTime;
	int temp;
	int argument;

	public AIThread(GameView gv) {
		this.gv = gv;
		r = new Random();
		runTime = 0;
		this.flag = true;
		if (Constant.hard)
			argument = 200;
		else {
			argument = 250;
		}

	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {

		while (flag) {

			if (gv.pause || gv.countRunning || gv.resaultRunning)
				continue;

			runTime++;
			try {

				if (gv.playerB.item != null) {

					try {
						// Log.d("DEBUG","sleep");
						Thread.sleep(400);// �ίv��
					} catch (Exception e) {
						e.printStackTrace();
					} finally {

					}
					if (!Constant.hard) {
						temp = r.nextInt(3) + 1;
						if (temp == 1)
							continue;

					}
					if (Constant.soundOn)
						gv.mainActivity.soundUtil.playEffectsSound(3, 0);
					gv.playerB.item = null;
					while (gv.playerA.bombs.size() < 5) {
						gv.playerA.addBomb();
					}
				}

				if (!gv.playerB.bombs.isEmpty()) {

					gv.mainActivity.soundUtil.playEffectsSound(2, 0);
					gv.playerB.bombs.get(0).next();
					if (gv.playerB.bombs.get(0).cur >= 3) {
						gv.playerB.bombs.remove(0);
						if (gv.playerB.bombs.isEmpty())
							gv.playerB.isPullEnabled = true;
					}

				}

				if (gv.playerB.isPullEnabled) {

					gv.rope.setPosition(-40);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}

			try {
				// Log.d("DEBUG","sleep");
				Thread.sleep(argument);// �ίv��
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}

		}

	}

}
