package com.example.softwarestudio_final;

import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class HelpView extends SurfaceView implements Callback {

	MainActivity mainActivity;
	public HelpView(MainActivity main){
		super(main);
		this.mainActivity=main;
		this.getHolder().addCallback(this);
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}
