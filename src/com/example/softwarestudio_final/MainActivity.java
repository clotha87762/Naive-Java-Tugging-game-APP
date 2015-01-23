package com.example.softwarestudio_final;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends ActionBarActivity {

	GameView gameView;
	HelpView helpView;
	OptionView optionView;
	MainView mainView;
	AudioManager audio;
	Bitmap temp;

	WhichView curr = WhichView.main;

	enum WhichView {
		game, option, help, main
	};

	SoundUtil soundUtil;

	Handler myHandler = new Handler() {// �Ψӧ�sUI�u�{��������
		public void handleMessage(Message msg) {
			if (msg.what == 1) {// �����^��MainView
				Log.d("DEBUG", "----------> I GO TO MAIN <---------");
				gotoMain();

			} else if (msg.what == 2) {// Main������GameView
				gotoGame();
			} else if (msg.what == 3) {// Main������HelpView
				
				gotoHelp();
			} else if (msg.what == 4) {// Main������OptionView
				gotoOption();

			}

		}

	};

	public void gotoGame() {
		if (mainView != null) {
			mainView = null;
		}
		gameView = new GameView(this);
		setContentView(gameView);
		curr = WhichView.game;
	}

	public void gotoMain() {
		
		if (gameView != null) {
			gameView = null;
		} else if (helpView != null) {
			//freeHelpImage();
			//loadBitmapImage();
			helpView = null;
		} else if (optionView != null) {
			optionView = null;
		}
		
		
		curr = WhichView.main;
		mainView = new MainView(this);
		setContentView(mainView);
	}

	public void gotoOption() {
		if (mainView != null) {
			mainView = null;
		}
		optionView = new OptionView(this);
		setContentView(optionView);
		curr = WhichView.option;
	}

	public void gotoHelp() {
		if (mainView != null) {
			mainView = null;
		}
		//freeGameImage();
		//loadHelpBitmap();
		
		helpView = new HelpView(this);
		setContentView(helpView);
		curr = WhichView.help;
	}

	public void loadBitmapImage() {
		ImageCollection.helpButton = ImageCollection.readBitmap(this,
				R.drawable.help_button);
		ImageCollection.startButton = ImageCollection.readBitmap(this,
				R.drawable.start_button);
		ImageCollection.mainBack = ImageCollection.readBitmap(this, 
				R.drawable.background3);
		ImageCollection.optionButton = ImageCollection.readBitmap(this,
				R.drawable.option_button);
		ImageCollection.title = ImageCollection.readBitmap(this,
				R.drawable.title2);
		
		ImageCollection.background = ImageCollection.readBitmap(
				this, R.drawable.playbackground);

		ImageCollection.background3 = ImageCollection.readBitmap(
				this, R.drawable.background3);

		ImageCollection.goalA = ImageCollection.readBitmap(this,
				R.drawable.goala);
		ImageCollection.goalB = ImageCollection.readBitmap(this,
				R.drawable.goalb);
		
		ImageCollection.rope2 = ImageCollection.readBitmap(this,
				R.drawable.rope2);


		ImageCollection.ropeCuteWin = ImageCollection.readBitmap(this,
				R.drawable.ropewin);
		
		ImageCollection.ropeCuteLose = ImageCollection.readBitmap(this,
				R.drawable.ropelose);
		
		ImageCollection.ropeCuteNormal = ImageCollection.readBitmap(this,
				R.drawable.ropenormal);
		
		ImageCollection.ropeWin = ImageCollection.ropeCuteWin;
		ImageCollection.ropeLose = ImageCollection.ropeCuteLose;
		ImageCollection.ropebit = ImageCollection.ropeCuteNormal;
		
		ImageCollection.imgOne = ImageCollection.readBitmap(this,
				R.drawable.count1);

		ImageCollection.imgTwo = ImageCollection.readBitmap(this,
				R.drawable.count2);

		ImageCollection.imgThree = ImageCollection.readBitmap(this,
				R.drawable.count3);

		ImageCollection.imgL = ImageCollection.readBitmap(this,
				R.drawable.ll);

		ImageCollection.imgO = ImageCollection.readBitmap(this,
				R.drawable.o);

		ImageCollection.imgS = ImageCollection.readBitmap(this,
				R.drawable.s);

		ImageCollection.imgE = ImageCollection.readBitmap(this,
				R.drawable.e);

		ImageCollection.imgW = ImageCollection.readBitmap(this,
				R.drawable.w);

		ImageCollection.imgI = ImageCollection.readBitmap(this,
				R.drawable.i);

		ImageCollection.imgN = ImageCollection.readBitmap(this,
				R.drawable.n);
		ImageCollection.player = ImageCollection.readBitmap(this,
				R.drawable.player);
		ImageCollection.mode = ImageCollection.readBitmap(this,
				R.drawable.mode);
		ImageCollection.time = ImageCollection.readBitmap(this,
				R.drawable.time);
		ImageCollection.obstacle = ImageCollection.readBitmap(this,
				R.drawable.obstacle);
		ImageCollection.music = ImageCollection.readBitmap(this,
				R.drawable.music);
		ImageCollection.on = ImageCollection.readBitmap(this,
				R.drawable.on);
		ImageCollection.off = ImageCollection.readBitmap(this,
				R.drawable.off);
		ImageCollection.thirty = ImageCollection.readBitmap(this,
				R.drawable.thirty);
		ImageCollection.sixty = ImageCollection.readBitmap(this,
				R.drawable.sixty);
		ImageCollection.onep = ImageCollection.readBitmap(this,
				R.drawable.onep);
		ImageCollection.twop = ImageCollection.readBitmap(this,
				R.drawable.twop);
		ImageCollection.rope = ImageCollection.readBitmap(this,
				R.drawable.rope);
		ImageCollection.cute = ImageCollection.readBitmap(this,
				R.drawable.cute);
		ImageCollection.gear = ImageCollection.readBitmap(this,
				R.drawable.gear);

		ImageCollection.cute1 = ImageCollection.readBitmap(this, R.drawable.cute1);
		ImageCollection.cute2 = ImageCollection.readBitmap(this, R.drawable.cute2);
		ImageCollection.cute3 = ImageCollection.readBitmap(this, R.drawable.cute3);

		ImageCollection.rock1 = ImageCollection.readBitmap(this, R.drawable.rock1);
		ImageCollection.rock2 = ImageCollection.readBitmap(this, R.drawable.rock2);
		ImageCollection.rock3 = ImageCollection.readBitmap(this, R.drawable.rock3);
	}

	public void loadHelpBitmap(){
		ImageCollection.helps[0] = ImageCollection.readBitmap(this, R.drawable.help1);
		ImageCollection.helps[1] = ImageCollection.readBitmap(this, R.drawable.help2);
		ImageCollection.helps[2] = ImageCollection.readBitmap(this, R.drawable.help3);
		ImageCollection.helps[3] = ImageCollection.readBitmap(this, R.drawable.help4);
		ImageCollection.helps[4] = ImageCollection.readBitmap(this, R.drawable.help5);
		ImageCollection.helps[5] = ImageCollection.readBitmap(this, R.drawable.help6);
		ImageCollection.helps[6] = ImageCollection.readBitmap(this, R.drawable.help7);
		ImageCollection.helps[7] = ImageCollection.readBitmap(this, R.drawable.help8);

	}
	
	public void freeHelpImage(){
		ImageCollection.helps[0].recycle();
		ImageCollection.helps[1].recycle();
		ImageCollection.helps[2].recycle();
		ImageCollection.helps[3].recycle();
		ImageCollection.helps[4].recycle();
		ImageCollection.helps[5].recycle();
		ImageCollection.helps[6].recycle();
		ImageCollection.helps[7].recycle();
	}
	
	public void freeGameImage() {
		ImageCollection.background.recycle();

		ImageCollection.background3.recycle();

		ImageCollection.goalA.recycle();
		ImageCollection.goalB.recycle();
		
		ImageCollection.rope2.recycle();


		ImageCollection.ropeCuteWin.recycle();
		ImageCollection.ropeCuteLose.recycle();
		ImageCollection.ropeCuteNormal.recycle();
		ImageCollection.imgOne.recycle();
		ImageCollection.imgTwo.recycle();
		ImageCollection.imgThree.recycle();
		ImageCollection.imgL.recycle();
		ImageCollection.imgO.recycle();
		ImageCollection.imgS.recycle();
		ImageCollection.imgE.recycle();
		ImageCollection.imgW.recycle();
		ImageCollection.imgI.recycle();
		ImageCollection.imgN.recycle();
		ImageCollection.player.recycle();
		ImageCollection.mode.recycle();
		ImageCollection.time.recycle();
		ImageCollection.obstacle.recycle();
		ImageCollection.music.recycle();
		ImageCollection.on.recycle();
		ImageCollection.off.recycle();
		ImageCollection.thirty.recycle();
		ImageCollection.sixty.recycle();
		ImageCollection.onep.recycle();
		ImageCollection.twop.recycle();
		ImageCollection.rope.recycle();
		ImageCollection.cute.recycle();
		ImageCollection.gear.recycle();
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		loadBitmapImage();
		loadHelpBitmap();
		// �]�m����ù�
		setVolumeControlStream(AudioManager.STREAM_MUSIC);// �n������
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		audio = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
		// ���o�̹��ؤo
		temp = BitmapFactory.decodeResource(getResources(),
				R.drawable.background);
		Constant.WIDTH = temp.getWidth();
		Constant.HEIGHT = temp.getHeight();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		if (dm.widthPixels > dm.heightPixels) {
			Constant.SCREEN_WIDTH = dm.widthPixels;
			Constant.SCREEN_HEIGHT = dm.heightPixels;
		} else {
			Constant.SCREEN_WIDTH = dm.widthPixels;
			Constant.SCREEN_HEIGHT = dm.heightPixels;
		}

		ScreenScale.calScale(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
		soundUtil = new SoundUtil(this);
		soundUtil.initSounds();

		myHandler.sendEmptyMessage(1);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.soundUtil.stop_bg_sound();
		if (gameView != null)
			this.gameView.pause = true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.soundUtil.play_bg_sound();
		if (gameView != null)
			this.gameView.pause = false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		Log.d("DEBUG", "QQQ");

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (curr == WhichView.main) {
				// ��dialog
				Log.d("DEBUG", "AAA");
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("Exit Game");
				builder.setMessage("Would You Like to Exit this Game??");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setCancelable(false);
				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								MainActivity.this.finish();
								
							}
						});

				builder.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				builder.show();
			} else {
				if (curr == WhichView.game)
					gameView.pause = true;

				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("Go to Main Menu");
				builder.setMessage("Would You Like to Go to Main Menu??");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setCancelable(false);
				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Constant.fps = 25;
								Constant.soundTitle=true;
								if( curr == WhichView.game )
									gameView.endGameViewbyKEYBACK();
								MainActivity.this.myHandler.sendEmptyMessage(1);

							}
						});

				builder.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (curr == WhichView.game)
									gameView.pause = false;
							}
						});
				builder.show();

			}

			return true;
		}

		// Main�h�h�X�C��,�Dmain�h�^��main;
		return super.onKeyDown(keyCode, event);
	}
}
