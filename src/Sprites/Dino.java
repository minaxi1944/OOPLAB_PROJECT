package Sprites;

//import static userinterface.GameScreen.groundY;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import anime.Animation;
import anime.Resource;
public class Dino {
	
	private float x = 10;    //coordinates of dino
	private float y = 0;
	private float speed = 0;
	private BufferedImage charimg;
	Animation charrun;
	
	public Dino() {
		super();
		
		//charimg=Resource.getResourceImage("images/main-character1.png");
		
		charrun=new Animation(200);
		charrun.addFrame(Resource.getResourceImage("Images/main-character1.png"));
		charrun.addFrame(Resource.getResourceImage("Images/main-character2.png"));
		
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.cyan);
		g.drawImage(charrun.getFrame(),(int)x+40,(int)y,null);
	}
	
	
	public void jump() {
		speed = -4;							//on press the rectangle jumps
		y+=speed;
	}
	
	public void update() {
		charrun.update();
		if(y >=  110 - 40) {	//To place the rectangle on the line. Line code for jumping
			speed = 0;
			y = 110 - 40;
		} 
		else {
			speed+=0.1;
			y+=speed;	//rectangle moves on y coordinate according to speed given
			
		}
		
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	

}
