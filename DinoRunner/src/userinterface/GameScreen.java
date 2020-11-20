package userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GameScreen extends JPanel implements Runnable, KeyListener {
	
	public static final float gravity = 0.1f;
	private float x = 0;
	private float y = 0;
	private float speedY = 0;
	private Thread thread;

	public GameScreen() {
		thread = new Thread(this);		//creating a thread.  if we don't the main thread will be free and can't do any thing on the program when its running 
	}
	
	public void StartGame() {
		thread.start();			   		//To start the java thread start() is used
	}
	
	@Override
	public void run() { 			 //when the thread starts it will call the run method. Whatever the thread is supposed to do when it executes must be included in the implementation of the run() method.
		while(true) {				//Game loop :Control infinity loop the makes the game keep running
			
			try {
				speedY+=gravity;
				y+=speedY;			//rectangle moves on y coordinate according to speed given
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
		g.drawRect((int) x, (int) y, 100, 100); 	// coordinate x,y,width,height of the rectangle
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {	
		speedY = -4;							//on press the rectangle jumps
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("Key released");
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }

}
