package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Movable {
	float x,y,scale;
	int alpha;
	Bitmap bitmap;
	public Movable(Bitmap b,float x,float y){
		this.bitmap= b;
		this.x=x;
		this.y=y;
		alpha = 255;
		this.scale = 1f;
	}

	public Movable(Bitmap b,float x,float y,int alpha,float scale){
		this.bitmap= b;
		this.x=x;
		this.y=y;
		this.alpha = alpha;
		this.scale = scale;
	}

	
	public void draw(Canvas canvas){
		Paint p = new Paint();
		p.setAlpha(alpha);
		canvas.save();
		canvas.scale(scale, scale, x, y);
		canvas.drawBitmap(bitmap, x, y,p);
		canvas.restore();
	}
	public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public int getAlpha(){
    	return alpha;
    }
    public void setAlpha(int a){
    	this.alpha = a;
    }
    public float getScale(){
    	return scale;
    }
    public void setScale(float scale){
    	this.scale = scale;
    }
	
}
