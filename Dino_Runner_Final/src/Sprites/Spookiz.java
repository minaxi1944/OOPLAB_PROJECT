package Sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import anime.Resource;

public class Spookiz extends Obstacle{
	
	private BufferedImage image;
	private int positionX, positionY;
	private Rectangle rect;
	private Dino dino;
	private boolean isScoreAdded = false;
	private Graphics g;
	
	public Spookiz(Dino dino) {
		this.dino = dino;
		image = Resource.getResourceImage("images/bird01.png");
		positionX = 450;
		positionY = 35;
		rect = new Rectangle();
	}
	
	@Override
	public void draw(Graphics g) {
		//g.drawRect(positionX, positionY, image.getWidth(), image.getHeight());
		g.drawImage(image, positionX, positionY,image.getWidth(),image.getHeight(), null);
	}
	
	public void update() {
		positionX = positionX - 7;
		rect.x = positionX;		//set rectangle position on x axis same as position of bird image
		rect.y = positionY;		//set rectangle position on y axis same as position of bird image
		rect.width = image.getWidth();		//set rectangle width same as width of bird image
		rect.height = image.getHeight();	//set rectangle height same as height of bird image
	}

	public Rectangle getBound() {
		
		return rect;
	}
	
	@Override
	public boolean isMiddleOfScreen() {
		return (positionX + image.getWidth() == 75);
	}
	
	public void setPositionX(int x) {
		positionX = x;
	}
	
	public void setPositionY(int y) {
		positionY = y;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public boolean isOutOfScreen() {
		return (positionX + image.getWidth() < 0);
	}
	
	@Override
	public boolean isGameOver() {
		return (dino.getX() > positionX);	// if dino crosses obstacle
	}
	
	@Override
	public boolean isScoreAdd() {
		return isScoreAdded;
	}
	
	@Override
	public void setIsScoreAdd(boolean isScoreAdded) {
		this.isScoreAdded = isScoreAdded;
	}
}
