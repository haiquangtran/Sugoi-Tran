package tools;

import main.Constants;

public class Vector2D {

	public static final Vector2D ZERO_VECTOR = new Vector2D(0,0);

	public float x;
	public float y;

	public Vector2D(float x_, float y_){
		x = x_;
		y = y_;
	}

	public Vector2D add(Vector2D other){
		float x2 = x+other.x;
		float y2 = y+other.y;
		return new Vector2D(x2,y2);
	}

	public Vector2D sub(Vector2D other){
		return new Vector2D(x-other.x,y-other.y);
	}

	public void setX(float x_){
		x = x_;
	}

	public void setY(float y_){
		y = y_;
	}

	public float magnitude(){
		return (float) Math.sqrt(x*x + y*y);
	}

	/**
	 * Return true if this is the zero vector, false otherwise
	 * @return boolean
	 */
	public boolean isZeroVector(){
		return x == 0 && y == 0;
	}

	public boolean atTerminalVelocity(){
		return y >= Constants.TERMINAL_VELOCITY.y;
	}

	/**
	 * Return the vector with the same direction, but magnitude 1.
	 * @return: a vector2D
	 */
	public Vector2D unitVector(){
		float mag = magnitude();
		if (mag == 0) return new Vector2D(0,0);
		float newX = x/mag;
		float newY = y/mag;
		return new Vector2D(newX,newY);
	}

	public float x(){
		return x;
	}

	public float y(){
		return y;
	}

	public String toString(){
		return "("+x+","+y+")";
	}

	public Vector2D horizontalFlip(){
		return new Vector2D(-x, y);
	}

}
