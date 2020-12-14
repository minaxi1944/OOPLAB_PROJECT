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
	private static final int CHAR_RUN = 0;
	private static final int CHAR_DUCK = 1;
	private int state = CHAR_RUN;
	
	private float x = 50;    //coordinates of dino
	private float y = LAND_POSY ;
	private float speed = 0;
	private BufferedImage charimg;
	Animation charrun,charduck;
	private Rectangle rect;
	private boolean isAlive = true;
	
	public Dino() {
		super();
		
		charrun=new Animation(200);
		charrun.addFrame(Resource.getResourceImage("Images/main-character1.png"));
		charrun.addFrame(Resource.getResourceImage("Images/main-character2.png"));
		rect = new Rectangle();
		
		charduck = new Animation(200);
		charduck.addFrame(Resource.getResourceImage("Images/main-character5.png"));
		charduck.addFrame(Resource.getResourceImage("Images/main-character6.png"));
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		switch(state) {
			case CHAR_RUN:
			g.drawImage(charrun.getFrame(),(int)x,(int)y-14,null);
			//g.drawRect((int)x,(int)y-14, charrun.getFrame().getWidth(), charrun.getFrame().getHeight());
			break;
			
			case CHAR_DUCK:
			g.drawImage(charduck.getFrame(),(int)x,(int)y,null);
			//g.drawRect((int)x,(int)y, charduck.getFrame().getWidth(), charduck.getFrame().getHeight());
			break;
		}
	}
	
	
	public void jump() {
		speed = -12;							//on press the dino jumps
		y = y + speed;
		//System.out.println("jumps ");
	}
	
	public void duck(boolean isDuck) {
		if(isDuck) {
			state = CHAR_DUCK;
		} else {
			state = CHAR_RUN;
		}
	}
	
	public boolean isduck() {
		if(state ==CHAR_DUCK) {
			return true;
		}
		return false;
	}
	
	public Rectangle getBound1() {
		rect = new Rectangle();
		rect.x = (int) x;
			rect.y = (int) y-10;
			rect.width = charrun.getFrame().getWidth()-10;
			rect.height = charrun.getFrame().getHeight()+10-20;
	
		return rect;
	}
	
	public void update() {
		charrun.update();
		charduck.update();
		if(y >=  LAND_POSY ) {	//To place the dino on the line, so that he doesn't go below the line
			speed = 0;
			y = LAND_POSY;
			/*if(state != CHAR_DUCK) {
				state = CHAR_RUN;
			}*/
		} 
		else {
			speed = (float) (speed + 0.9);
			y = y + speed;	//to make the dino drop down to the line
		}
	}
	
	public Rectangle getBound() {
		rect = new Rectangle();
		if(state == CHAR_RUN) {
			rect.x = (int) x;
			rect.y = (int) y;
			rect.width = charrun.getFrame().getWidth()-10;
			rect.height = charrun.getFrame().getHeight()-10;
		} else {
			rect.x = (int) x;
			rect.y = (int) y;
			rect.width = charduck.getFrame().getWidth();
			rect.height = charduck.getFrame().getHeight();
		}
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
