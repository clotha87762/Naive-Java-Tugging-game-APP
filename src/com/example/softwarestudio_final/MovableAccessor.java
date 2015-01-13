package com.example.softwarestudio_final;

import aurelienribon.tweenengine.TweenAccessor;

public class MovableAccessor implements TweenAccessor<Movable> {
	 
	 public static final int POSITION_X = 1;
	 public static final int POSITION_Y = 2;
	 public static final int POSITION_XY = 3;
	 public static final int ALPHA = 4;
	 public static final int SCALE = 5;
	@Override
	public int getValues(Movable target, int tweenType, float[] returnValues) {
	        switch (tweenType) {
	            case POSITION_X: returnValues[0] = target.getX(); return 1;
	            case POSITION_Y: returnValues[0] = target.getY(); return 1;
	            case POSITION_XY:
	                returnValues[0] = target.getX();
	                returnValues[1] = target.getY();
	                return 2;
	            case ALPHA: returnValues[0] = target.getAlpha();return 1;
	            case SCALE: returnValues[0] = target.getScale();return 1;
	            default: assert false; return -1;
	        }
	    }
	    
	    @Override
	    public void setValues(Movable target, int tweenType, float[] newValues) {
	        switch (tweenType) {
	            case POSITION_X: target.setX(newValues[0]); break;
	            case POSITION_Y: target.setY(newValues[0]); break;
	            case POSITION_XY:
	                target.setX(newValues[0]);
	                target.setY(newValues[1]);
	                break;
	            case ALPHA : target.setAlpha((int)newValues[0]);break;    
	            case SCALE: target.setScale(newValues[0]);break;
	            default: assert false; break;
	        }
	    }
}
