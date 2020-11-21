package userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import objectGame.MainCharacter;

public class GameScreen extends JPanel implements Runnable, KeyListener {
	
	public static final float gravity = 0.1f;
	public static final float groundY = 300;  //by pixel
	private Thread thread;

	private MainCharacter mainCharacter;
	
	public GameScreen() {
		thread = new Thread(this);		//creating a thread.  if we don't the main thread will be free and can't do any thing on the program when its running 
		mainCharacter = new MainCharacter();
	}
	
	public void StartGame() {
		thread.start();			   		//To start the java thread start() is used
	}
	
	@Override
	public void run() { 			 //when the thread starts it will call the run method. Whatever the thread is supposed to do when it executes must be included in the implementation of the run() method.
		while(true) {				//Game loop :Control infinity loop the makes the game keep running
			
			try {
				mainCharacter.update();		//Line code for jumping
				repaint();			// repaint will call paint method again and the rectangle will draw again
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.blue);
		g.drawLine(0, (int) groundY, getWidth(), (int) groundY);
		mainCharacter.draw(g);
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {	
		mainCharacter.jump();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("Key released");
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }

}
