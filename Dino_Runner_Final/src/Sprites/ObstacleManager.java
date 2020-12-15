package Sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import anime.Resource;
import Game.GameScreen;

public class ObstacleManager<GameScreen> {
	
	private List<Obstacle> obstacles;	//a list to hold different types of obstacles like cactus and birds
	private Random random, randomObstacle, randomNum;
	private BufferedImage imageCactus1, imageCactus2, imageSpookiz;
	private Dino dino;
	private Cactus cactus;
	private Spookiz bird;
	private GameScreen gameScreen;
	private int randNum;
	
	public ObstacleManager(Dino dino, GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		this.dino = dino;
		obstacles = new ArrayList<Obstacle>();
		imageCactus1 = Resource.getResourceImage("images/cactus01.png");
		imageCactus2 = Resource.getResourceImage("images/cactus02.png");
		imageSpookiz = Resource.getResourceImage("images/bird01.png");
		random = new Random();
		randomNum = new Random();
		obstacles.add(getRandomCactus());	//add cactus to the list
		obstacles.add(getBird());
	}
	
	public void draw(Graphics g) {	
		//draw obstacle from the list, in the game
		for(Obstacle obs : obstacles) {
			obs.draw(g);
		}
	}
	
	public void setObstacle() {
		obstacles.add(getRandomCactus());	//add cactus to the list
		obstacles.add(getBird());
	}
	
	public void update() {					//update position of every obstacle from the list
		for(Obstacle obs : obstacles) {
			obs.update();
			if(obs.isGameOver() && !obs.isScoreAdd()) {	//if game is not over and score is not added after dino crosses obstacle
				((Game.GameScreen) gameScreen).calculateScore(10);
				obs.setIsScoreAdd(true);
			}
			if(dino.isduck()) {
			if(dino.getBound().intersects(obs.getBound())) {
				dino.setAlive(false);
			}
			}
			else {
			if(dino.getBound1().intersects(obs.getBound())) {
				dino.setAlive(false);
			}
			}
				
		}
		randNum = randomNum.nextInt(100);
		Obstacle initialCactus = obstacles.get(0);		//get the first cactus from the list
		if(randNum>=0 && randNum<25) {
			if(initialCactus.isOutOfScreen()) {				//if the cactus has gone out of the screen
				obstacles.remove(initialCactus);	// then delete it from the list
				obstacles.add(getBird());						//add a bird
			} /*else if(initialCactus.isMiddleOfScreen()) {
				obstacles.add(getRandomCactus());
			}*/
		} else if(randNum>=25 && randNum<50) {
			if(initialCactus.isOutOfScreen()) {				//if the cactus has gone out of the screen
				obstacles.remove(initialCactus);			// then delete it from the list
				obstacles.add(getRandomCactus());
			} /*else if(initialCactus.isMiddleOfScreen()) {
				obstacles.add(getBird());
			}*/
		} else if(randNum>=50 && randNum<75) {
			if(initialCactus.isOutOfScreen()) {				//if the cactus has gone out of the screen
				obstacles.remove(initialCactus);	// then delete it from the list
				obstacles.add(getBird());						//add a bird
			}
		} else {
			if(initialCactus.isOutOfScreen()) {				//if the cactus has gone out of the screen
				obstacles.remove(initialCactus);			// then delete it from the list
				obstacles.add(getRandomCactus());			// and add a new random cactus
			}
		}
		System.out.println(obstacles.size());
	}
	
	public void resetObstacle() {
		obstacles.clear();
		obstacles.add(getRandomCactus());
	}
	
	private Cactus getRandomCactus() {		//get random order of cactus
		cactus = new Cactus(dino);
		cactus.setPositionY(105);
		if(random.nextBoolean()) {
			cactus.setPositionX(800);
			
			cactus.setImage(imageCactus1);
		} else {
			cactus.setPositionX(1000);
			
			cactus.setImage(imageCactus2);
		}
		return cactus;
	}
	
	private Spookiz getBird() {
		bird = new Spookiz(dino);
		bird.setPositionX(800);
		bird.setPositionY(75);
		bird.setImage(imageSpookiz);
		return bird;
	}
}
