package Sprites;

//import static userinterface.GameScreen.groundY;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import anime.Animation;
import anime.Resource;
public class Dino {
	
	public static final int LAND_POSY = 110;
	public static final float GRAVITY = 0.8f;
	
	private float x = 50;    //coordinates of dino
	private float y = LAND_POSY ;
	private float speed = 0;
	private BufferedImage charimg;
	Animation charrun;
	private Rectangle rect;
	private boolean isAlive = true;
	
	public Dino() {
		super();
		
		charrun=new Animation(200);
		charrun.addFrame(Resource.getResourceImage("Images/main-character1.png"));
		charrun.addFrame(Resource.getResourceImage("Images/main-character2.png"));
		rect = new Rectangle();
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawImage(charrun.getFrame(),(int)x,(int)y-14,null);
	}
	
	
	public void jump() {
		speed = -12;							//on press the dino jumps
		y = y + speed;
		System.out.println("jumps ");
	}
	
	public void update() {
		charrun.update();
		if(y >=  LAND_POSY ) {	//To place the dino on the line, so that he doesn't go below the line
			speed = 0;
			y = LAND_POSY ;
		} 
		else {
			speed = (float) (speed + 0.9);
			y = y + speed;	//to make the dino drop down to the line
			
		}
		rect.x = (int) x;
		rect.y = (int) y;
		rect.width = charrun.getFrame().getWidth();
		rect.height = charrun.getFrame().getHeight();
	}
	
	public Rectangle getBound() {
		return rect;
	}

	public float getY() {
		return y;
	}
	
	public float getX() {
		return x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void setAlive(boolean alive) {
		isAlive = alive;
	}
	
	public boolean getAlive() {
		return isAlive;
	}
}
