package com.example.softwarestudio_final;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Player {
	
	Rope rope;
	int life;
	ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	enum Status {normal,reversed,change}
	Bitmap lifecage;
	Bitmap lifebar;
	
	GameView gv;
	public Player(GameView gv,int life,Rope rope){
		this.gv=gv;
		this.life = life;
		this.rope = rope;
		 
	}
	
	
	public void draw(Canvas canvas){
		
	}
	
	
}
