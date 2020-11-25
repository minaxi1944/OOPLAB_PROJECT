package Sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import anime.Resource;

public class ObstacleManager {
	
	private List<Obstacle> obstacles;	//a list to hold different types of obstacles like cactus and birds
	private Random random;
	private BufferedImage imageCactus1, imageCactus2;
	
	public ObstacleManager() {
		obstacles = new ArrayList<Obstacle>();
		imageCactus1 = Resource.getResourceImage("images/cactus1.png");
		imageCactus2 = Resource.getResourceImage("images/cactus2.png");
		random = new Random();
		obstacles.add(getRandomCactus());			//add cactus to the list
	}
	
	public void draw(Graphics g) {			//draw every obstacle from the list, in the game
		for(Obstacle obs : obstacles) {
			obs.draw(g);
		}
	}
	
	public void update() {					//update position of every obstacle from the list
		for(Obstacle obs : obstacles) {
			obs.update();
		}
		Obstacle initialCactus = obstacles.get(0);		//get the first cactus from the list
		if(initialCactus.isOutOfScreen()) {				//if the cactus has gone out of the screen
			obstacles.remove(initialCactus);			// then delete it from the list
			obstacles.add(getRandomCactus());			// and add a new random cactus
		}
	}
	
	private Cactus getRandomCactus() {
		Cactus cactus = new Cactus();
		cactus.setPositionX(600);
		cactus.setPositionY(65);
		if(random.nextBoolean()) {
			cactus.setImage(imageCactus1);
		} else {
			cactus.setImage(imageCactus2);
		}
		return cactus;
	}
}
