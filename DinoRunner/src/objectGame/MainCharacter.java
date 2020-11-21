package objectGame;

import java.awt.Color;
import java.awt.Graphics;
import static userinterface.GameScreen.gravity;
import static userinterface.GameScreen.groundY;

public class MainCharacter {
	 
	private float x = 10;
	private float y = 0;
	private float speedY = 0;
	
	
	public void update() {
		if(y >= groundY - 100) {	//To place the rectangle on the line. Line code for jumping
			speedY = 0;
			y = groundY - 100;
		} 
		else {
		speedY+=gravity;
		y+=speedY;	//rectangle moves on y coordinate according to speed given
		}		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.drawRect((int) x, (int) y, 100, 100); 	// coordinate x,y,width,height of the rectangle
	}
	
	public void jump() {
		speedY = -4;							//on press the rectangle jumps
		y+=speedY;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getSpeedY() {
		return speedY;
	}
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	

}
