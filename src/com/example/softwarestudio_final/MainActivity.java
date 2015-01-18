package com.example.softwarestudio_final;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
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
	WhichView curr=WhichView.main;
	enum WhichView{game,option,help,main};
	Handler myHandler = new Handler(){//ノㄓ穝UI絬祘い北ン
        public void handleMessage(Message msg) {
        	if(msg.what == 1){//ち传MainView
        		gotoMain();
        		
        	}
        	else if(msg.what == 2){//Mainち传GameView
        		gotoGame();
        	}
        	else if(msg.what == 3){//Mainち传HelpView
        		gotoHelp();
        	}
        	else if(msg.what == 4){//Mainち传OptionView
        		gotoOption();
        		
        	}
        	   	
        }
	};
	public void gotoGame(){
		if(mainView!=null){
			mainView = null;
		}
		gameView = new GameView(this);
		setContentView(gameView);
		curr = WhichView.game;
	}
	public void gotoMain(){
		if(gameView!=null){
			gameView=null;
		}
		else if(helpView!=null){
			helpView=null;
		}
		else if(optionView!=null){
			optionView = null;
		}
		curr = WhichView.main;
		mainView = new MainView(this);
		setContentView(mainView);
	}
	public void gotoOption(){
		if(mainView!=null){
			mainView = null;
		}
		optionView = new OptionView(this);
		setContentView(optionView);
		curr = WhichView.option;
	}
	public void gotoHelp(){
		if(mainView!=null){
			mainView = null;
		}
		helpView = new HelpView(this);
		setContentView(helpView);
		curr = WhichView.help;
	}
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,   
	        WindowManager.LayoutParams. FLAG_FULLSCREEN); 
	        //砞竚绢Α棵辊
	    	setVolumeControlStream(AudioManager.STREAM_MUSIC);//羘北
	       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        audio = (AudioManager) getSystemService(Service.AUDIO_SERVICE); 
			//眔辊へ
	       temp = BitmapFactory.decodeResource(getResources(), R.drawable.background);
	        Constant.WIDTH = temp.getWidth();
	        Constant.HEIGHT = temp.getHeight();
	        DisplayMetrics dm=new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);  
	        
	        if(dm.widthPixels>dm.heightPixels)
	        {
	        	 Constant.SCREEN_WIDTH=dm.widthPixels;
	        	 Constant.SCREEN_HEIGHT=dm.heightPixels;
	        }
	        else
	        {
	        	Constant.SCREEN_WIDTH=dm.widthPixels;
	        	Constant.SCREEN_HEIGHT=dm.heightPixels;
	        }
	
		ScreenScale.calScale(Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);
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
			
		}
	    @Override
		protected void onResume() {
			super.onResume();
			
		}
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) 
	    {   
	    	
	    	Log.d("DEBUG","QQQ");
	    	
	    	if(keyCode==KeyEvent.KEYCODE_BACK){
	    		
	    		if(curr==WhichView.main){
	    			//铬dialog
	    			Log.d("DEBUG","AAA");
	    			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	    			builder.setTitle("Exit Game");
	    			builder.setMessage("Would You Like to Exit this Game??");
	    			builder.setIcon(android.R.drawable.ic_dialog_info);
	    			builder.setCancelable(false);
	    			builder.setPositiveButton("Yes",
	    					new DialogInterface.OnClickListener(){
	    						@Override
	    						public void onClick(DialogInterface dialog,int which){
	    							MainActivity.this.finish();
	    						}
	    				}
	    			);
	    			
	    			builder.setNegativeButton("No",
	    					new DialogInterface.OnClickListener(){
	    						@Override
	    						public void onClick(DialogInterface dialog,int which){
	    							
	    						}
	    				}
	    			);
	    			builder.show();
	    		}
	    		else{
	    			if(curr==WhichView.game)
	    			gameView.pause=true;
	    			
	    			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	    			builder.setTitle("Go to Main Menu");
	    			builder.setMessage("Would You Like to Go to Main Menu??");
	    			builder.setIcon(android.R.drawable.ic_dialog_info);
	    			builder.setCancelable(false);
	    			builder.setPositiveButton("Yes",
	    					new DialogInterface.OnClickListener(){
	    						@Override
	    						public void onClick(DialogInterface dialog,int which){
	    							MainActivity.this.myHandler.sendEmptyMessage(1);
	    							
	    						}
	    				}
	    			);
	    			
	    			builder.setNegativeButton("No",
	    					new DialogInterface.OnClickListener(){
	    						@Override
	    						public void onClick(DialogInterface dialog,int which){
	    							if(curr==WhichView.game)
	    							gameView.pause = false;
	    						}
	    				}
	    			);
	    			builder.show();
	    			
	    		}
	    		
	    		return true;
	    	}	
	    		
	    	
	    	
	    	
	    	//Main玥癶笴栏,獶main玥main;
	    	return super.onKeyDown(keyCode, event);
	    }
}
