package Sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import anime.Resource;

public class Cactus extends Obstacle{
	
	private BufferedImage image;
	private int positionX, positionY;
	private Rectangle rect;
	private Dino dino;
	private boolean isScoreAdded = false;
	
	public Cactus(Dino dino) {
		this.dino = dino;
		image = Resource.getResourceImage("images/cactus1.png");
		positionX = 400;
		positionY = 65;
		rect = new Rectangle();
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, positionX, positionY, null);
	}
	
	public void update() {
		positionX = positionX - 5;
		rect.x = positionX;		//set rectangle position on x axis same as position of cactus image
		rect.y = positionY;		//set rectangle position on y axis same as position of cactus image
		rect.width = image.getWidth();		//set rectangle width same as width of cactus image
		rect.height = image.getHeight();	//set rectangle height same as height of cactus image
	}
	
	public Rectangle getBound() {
		return rect;
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
