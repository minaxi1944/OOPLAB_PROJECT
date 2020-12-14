package Game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JPanel;

import Sprites.Land;
import Sprites.Backg;
import Sprites.Cactus;
import Sprites.Dino;
import Sprites.ObstacleManager;
import Sprites.Spookiz;
import anime.Resource;
//import objectGame.Land;
import Sprites.Cloud;


public class GameScreen extends JPanel implements Runnable, KeyListener {

	private float x=0,y=0;         //coordinates of window
	private float speed=0;
	public static final int START_GAME_STATE = 0;
	public static final int GAMEPLAY_STATE = 1;
	public static final int GAME_OVER_STATE = 2;
	public static float line=140;
	private Thread thread;
	private Dino dino;
	private ObstacleManager obstacleManager;
	private int gameState = START_GAME_STATE;
	private BufferedImage imageGameOver,highscore;
	private int score;
	private AudioClip scoreSound, jumpSound, gameOverSound;
	private Cloud c;

	private Land land;
	private Backg g1;
	
	public GameScreen() {
		thread = new Thread(this);		//creating a thread.  if we don't the main thread will be free and can't do any thing on the program when its running 
		dino=new Dino();	//creating object of dino
		dino.setX(50);		
		dino.setY(60);
		
		g1=new Backg(this);
		c=new Cloud();
		land=new Land(this);
		obstacleManager = new ObstacleManager(dino, this);	//creating object of ObstacleManager
		imageGameOver = Resource.getResourceImage("images/game_over_PNG14.png");
		highscore = Resource.getResourceImage("images/highscore.png");
		try {
			scoreSound = Applet.newAudioClip(new URL("file","","images/scoreup.wav"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			jumpSound = Applet.newAudioClip(new URL("file","","images/jump.wav"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			gameOverSound = Applet.newAudioClip(new URL("file","","images/dead.wav"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void calculateScore(int score) {		//add score
		this.score = this.score + score;
		scoreSound.play();
	}
	
	public void paint(Graphics g) {
		BufferedImage backg = Resource.getResourceImage("images/gameplay1.jpg");
		BufferedImage backg2= Resource.getResourceImage("images/gameplay2.jpg");
		BufferedImage start= Resource.getResourceImage("images/startgame.png");
		
		g.setColor(Color.white);
		
		g.fillRect(0, 0, getWidth(), getHeight());  //fills the game screen with white color
		
		if(score>60) {
			g.drawImage(backg,0,0,backg.getWidth()-290,backg.getHeight()-290,null);
			c.draw(g);
			land.draw(g);
		}
		else {
			g.drawImage(backg2,0,0,backg2.getWidth()-320,backg2.getHeight()-320,null);
			
			g1.draw(g);
			c.draw(g);
		}
		
		
		g.setColor(Color.DARK_GRAY);                    //color of line
		
		//g.drawLine(0, (int)line, getWidth(), (int)line);   //puts a line on which dino jumps
		
		switch(gameState) {
			case START_GAME_STATE:
				dino.draw(g);
				g.drawImage(start,30,80,null);
				break;
				
			case GAMEPLAY_STATE:		//draw obstacles and dino during running game
				obstacleManager.draw(g);
				dino.draw(g);
				g.setColor(Color.WHITE);
				g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
				//g.drawImage(highscore, 250, 20, null);
				g.drawString("HIGHEST SCORE "+ ScoreFile.read_from_file(), 300, 20);
				g.drawString("SCORE " + String.valueOf(score), 500, 20);		//display score on game screen
				break;
				
			case GAME_OVER_STATE:
				obstacleManager.draw(g);
				dino.draw(g);
				g.setColor(Color.WHITE);
				g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
				g.drawString("HIGHEST SCORE " + ScoreFile.read_from_file(), 300, 20);
				g.drawString("SCORE " + String.valueOf(score), 500, 20);		//display score on game screen
				if(ScoreFile.read_from_file()<score)
					g.drawImage(highscore, 200, 0, null);
				g.drawImage(imageGameOver, 200, 50,100,100, null);
				break;
		}
	}
	
	private void resetGame() {	//when collision detected reset game
		dino.setAlive(true);
		if(ScoreFile.read_from_file()<this.score)
		ScoreFile.write_to_file(this.score);
		this.score = 0;
		dino.setX(50);
		dino.setY(60);
		dino.duck(false);
		obstacleManager.resetObstacle();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("pressed");
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if(gameState == START_GAME_STATE) {
				gameState = GAMEPLAY_STATE;
				
			} else if(gameState == GAMEPLAY_STATE) {
				if(dino.getY()>50) {	//prevents jump in air again until reached on line
					dino.jump();
					jumpSound.play();
				}
			} else if(gameState == GAME_OVER_STATE) {
				resetGame();
				gameState = GAMEPLAY_STATE;
			}
			break;
			
		case KeyEvent.VK_DOWN:
			if(gameState == GAMEPLAY_STATE) {
				dino.duck(true);
			}
			break;
	}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getKeyCode()) {
			case KeyEvent.VK_DOWN:
			if(gameState == GAMEPLAY_STATE) {
				dino.duck(false);
			}
			break;
		}
		//System.out.println("released");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {             //when the thread starts it will call the run method. Whatever the thread is supposed to do when it executes must be included in the implementation of the run() method.
		
		// TODO Auto-generated method stub
while(true) {				//Game loop :Control infinity loop the makes the game keep running
			
			try {
				
				update();
				/*if(cactus1.getBound().intersects(dino.getBound())) {	//check if dino object and cactus object collide each other
					System.out.println("Collision Detected");
				}*/
				//System.out.println("abc");
				repaint();			// repaint will call paint method again and the dino, obstacles will be drawn again
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		switch(gameState) {
			case GAMEPLAY_STATE:
				dino.update();
				c.update();
				land.update();
				g1.update();
				obstacleManager.update();
				if(dino.getAlive() == false) {
					gameOverSound.play();
					gameState = GAME_OVER_STATE;
				}
				break;
		}
	}
	
	public void StartGame() {
		thread.start();			   		//To start the java thread start() is used
	}
	
}
