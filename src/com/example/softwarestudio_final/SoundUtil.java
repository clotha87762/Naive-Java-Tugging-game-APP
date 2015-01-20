package com.example.softwarestudio_final;

import java.io.IOException;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundUtil {
	public MediaPlayer mp;
	SoundPool soundPool;//聲音池
	HashMap<Integer, Integer> soundPoolMap;
	MainActivity ma;
	public SoundUtil(MainActivity ma)
	{
		this.ma=ma;
	}
	//初始化
    public void initSounds()
    {
    	 //建立緩衝池
	     soundPool = new SoundPool
	     (
	    		 7, 							//同時播放個數
	    		 AudioManager.STREAM_MUSIC,     //音頻的類別
	    		 100							//聲音的播放質量
	     );
	     
	     //建立聲音資源Map	     
	     soundPoolMap = new HashMap<Integer, Integer>();   
	     //將加載的聲音資源id放進此Map
	     soundPoolMap.put(0, soundPool.load(ma, R.raw.tug, 1)); //拉繩子
	     soundPoolMap.put(1, soundPool.load(ma, R.raw.pop, 1)); // 障礙出現
	     soundPoolMap.put(2, soundPool.load(ma, R.raw.kill, 1)); // 削掉障礙
	     soundPoolMap.put(3, soundPool.load(ma, R.raw.crystal, 1)); //使用道具
	     soundPoolMap.put(4, soundPool.load(ma, R.raw.press, 1)); // 按下標題介面按鈕
	     soundPoolMap.put(5, soundPool.load(ma, R.raw.whistle, 1));
	     soundPoolMap.put(6, soundPool.load(ma, R.raw.count, 1));
	     //有幾個音效就有當前這個幾句  R.raw.gamestart返回編號 不定     後面的1為優先級 目前不考慮
	} 
       
   //播放音效的方法
   public void playEffectsSound(int sound, int loop) {
	   
		   AudioManager mgr = (AudioManager)ma.getSystemService(Context.AUDIO_SERVICE);
		    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);//當前音量   
		    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//最大音量       
		    float volume = (streamVolumeCurrent+streamVolumeMax) / streamVolumeMax;   
		    
		    soundPool.play
		    (
	    		soundPoolMap.get(sound), //聲音資源id
	    		volume, 				 //左聲道音量
	    		volume, 				 //右聲道音量
	    		1, 						 //優先級				 
	    		loop, 					 //循環次數 -1帶表永遠循環
	    		1f					 //回放速度0.5f～2.0f之間
		    );
	   
	}
   public void play_bg_sound()//
   {
	   if(Constant.soundOn)
	   {
		   //透過assets 加載音樂
	     AssetManager assetManager = ma.getAssets();  
	     try {  
	     mp = new MediaPlayer();  
	     String s;
	     if(Constant.soundTitle)
	     {
	    	 s="title.ogg";
	     }
	     else
	     {
	    	 s= "battle.ogg"; 
	     }
	     AssetFileDescriptor fileDescriptor= assetManager.openFd(s); 
	     mp.setDataSource(fileDescriptor.getFileDescriptor(),  
	     fileDescriptor.getStartOffset(),  
	     fileDescriptor.getLength()); 
	     mp.setLooping(true);
	     mp.prepare();  
	     mp.start();  
	     } catch (IOException e) 
	     {  
	      e.printStackTrace();  
	     } 
	    
	   }
   }
   
   public void stop_bg_sound()
   {
	   if(Constant.soundOn&&mp!=null)
	   {
		   mp.stop();
		   mp.release();
		  
	   }
   }
}
