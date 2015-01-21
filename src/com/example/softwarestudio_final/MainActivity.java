package com.example.softwarestudio_final;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
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
		helpView = new HelpView(this);
		setContentView(helpView);
		curr = WhichView.help;
	}

	public void loadBitmapImage() {
		ImageCollection.background = BitmapFactory.decodeResource(
				getResources(), R.drawable.playbackground);

		ImageCollection.background3 = BitmapFactory.decodeResource(
				getResources(), R.drawable.background3);

		ImageCollection.goalA = BitmapFactory.decodeResource(getResources(),
				R.drawable.goala);
		ImageCollection.goalB = BitmapFactory.decodeResource(getResources(),
				R.drawable.goalb);
		ImageCollection.ropebit = BitmapFactory.decodeResource(getResources(),
				R.drawable.ropenormal);

		ImageCollection.rope2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.rope2);

		ImageCollection.ropeWin = BitmapFactory.decodeResource(getResources(),
				R.drawable.ropewin);
		ImageCollection.ropeLose = BitmapFactory.decodeResource(getResources(),
				R.drawable.ropelose);

		ImageCollection.ropeCuteWin = BitmapFactory.decodeResource(getResources(),
				R.drawable.ropewin);
		
		ImageCollection.ropeCuteLose = BitmapFactory.decodeResource(getResources(),
				R.drawable.ropelose);
		
		ImageCollection.ropeCuteNormal = BitmapFactory.decodeResource(getResources(),
				R.drawable.ropenormal);
		
		ImageCollection.imgOne = BitmapFactory.decodeResource(getResources(),
				R.drawable.count1);

		ImageCollection.imgTwo = BitmapFactory.decodeResource(getResources(),
				R.drawable.count2);

		ImageCollection.imgThree = BitmapFactory.decodeResource(getResources(),
				R.drawable.count3);

		ImageCollection.imgL = BitmapFactory.decodeResource(getResources(),
				R.drawable.ll);

		ImageCollection.imgO = BitmapFactory.decodeResource(getResources(),
				R.drawable.o);

		ImageCollection.imgS = BitmapFactory.decodeResource(getResources(),
				R.drawable.s);

		ImageCollection.imgE = BitmapFactory.decodeResource(getResources(),
				R.drawable.e);

		ImageCollection.imgW = BitmapFactory.decodeResource(getResources(),
				R.drawable.w);

		ImageCollection.imgI = BitmapFactory.decodeResource(getResources(),
				R.drawable.i);

		ImageCollection.imgN = BitmapFactory.decodeResource(getResources(),
				R.drawable.n);
		ImageCollection.player = BitmapFactory.decodeResource(getResources(),
				R.drawable.player);
		ImageCollection.mode = BitmapFactory.decodeResource(getResources(),
				R.drawable.mode);
		ImageCollection.time = BitmapFactory.decodeResource(getResources(),
				R.drawable.time);
		ImageCollection.obstacle = BitmapFactory.decodeResource(getResources(),
				R.drawable.obstacle);
		ImageCollection.music = BitmapFactory.decodeResource(getResources(),
				R.drawable.music);
		ImageCollection.on = BitmapFactory.decodeResource(getResources(),
				R.drawable.on);
		ImageCollection.off = BitmapFactory.decodeResource(getResources(),
				R.drawable.off);
		ImageCollection.thirty = BitmapFactory.decodeResource(getResources(),
				R.drawable.thirty);
		ImageCollection.sixty = BitmapFactory.decodeResource(getResources(),
				R.drawable.sixty);
		ImageCollection.onep = BitmapFactory.decodeResource(getResources(),
				R.drawable.onep);
		ImageCollection.twop = BitmapFactory.decodeResource(getResources(),
				R.drawable.twop);
		ImageCollection.rope = BitmapFactory.decodeResource(getResources(),
				R.drawable.rope);
		ImageCollection.cute = BitmapFactory.decodeResource(getResources(),
				R.drawable.cute);
		ImageCollection.gear = BitmapFactory.decodeResource(getResources(),
				R.drawable.gear);
		ImageCollection.helps[0] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help1);
		ImageCollection.helps[1] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help2);
		ImageCollection.helps[2] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help3);
		ImageCollection.helps[3] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help4);
		ImageCollection.helps[4] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help5);
		ImageCollection.helps[5] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help6);
		ImageCollection.helps[6] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help7);
		ImageCollection.helps[7] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help8);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		loadBitmapImage();
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
