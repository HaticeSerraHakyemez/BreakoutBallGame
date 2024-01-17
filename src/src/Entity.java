package BreakoutBallGame.src.src;
import java.awt.Color;
import java.awt.Rectangle;

public class Entity extends Rectangle{ 
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected Color color;
	protected double speed;
	
	public void draw() {}
	public double getWidth() {
		return this.width;
	}
	public void setSpeed(double speed) {
		this.speed=speed;
	}
	public double getHeight() {
		return this.height;
	}
	public void updatePosition() {}
}
