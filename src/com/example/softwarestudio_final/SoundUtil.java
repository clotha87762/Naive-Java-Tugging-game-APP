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
	SoundPool soundPool;//�n����
	HashMap<Integer, Integer> soundPoolMap;
	MainActivity ma;
	public SoundUtil(MainActivity ma)
	{
		this.ma=ma;
	}
	//��l��
    public void initSounds()
    {
    	 //�إ߽w�Ħ�
	     soundPool = new SoundPool
	     (
	    		 7, 							//�P�ɼ���Ӽ�
	    		 AudioManager.STREAM_MUSIC,     //���W�����O
	    		 100							//�n���������q
	     );
	     
	     //�إ��n���귽Map	     
	     soundPoolMap = new HashMap<Integer, Integer>();   
	     //�N�[�����n���귽id��i��Map
	     soundPoolMap.put(0, soundPool.load(ma, R.raw.tug, 1)); //��÷�l
	     soundPoolMap.put(1, soundPool.load(ma, R.raw.pop, 1)); // ��ê�X�{
	     soundPoolMap.put(2, soundPool.load(ma, R.raw.kill, 1)); // �d����ê
	     soundPoolMap.put(3, soundPool.load(ma, R.raw.crystal, 1)); //�ϥιD��
	     soundPoolMap.put(4, soundPool.load(ma, R.raw.press, 1)); // ���U���D�������s
	     soundPoolMap.put(5, soundPool.load(ma, R.raw.whistle, 1));
	     soundPoolMap.put(6, soundPool.load(ma, R.raw.count, 1));
	     soundPoolMap.put(7, soundPool.load(ma, R.raw.exchange, 1));
	     //���X�ӭ��ĴN����e�o�ӴX�y  R.raw.gamestart��^�s�� ���w     �᭱��1���u���� �ثe���Ҽ{
	} 
       
   //���񭵮Ī���k
   public void playEffectsSound(int sound, int loop) {
	   
		   AudioManager mgr = (AudioManager)ma.getSystemService(Context.AUDIO_SERVICE);
		    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);//��e���q   
		    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//�̤j���q       
		    float volume = (streamVolumeCurrent+streamVolumeMax) / streamVolumeMax;   
		    
		    soundPool.play
		    (
	    		soundPoolMap.get(sound), //�n���귽id
	    		volume, 				 //���n�D���q
	    		volume, 				 //�k�n�D���q
	    		1, 						 //�u����				 
	    		loop, 					 //�`������ -1�a��û��`��
	    		1f					 //�^��t��0.5f��2.0f����
		    );
	   
	}
   public void play_bg_sound()//
   {
	   if(Constant.soundOn)
	   {
		   //�z�Lassets �[������
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
