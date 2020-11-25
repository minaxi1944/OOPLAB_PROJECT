import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Sprites.Dino;


public class GameScreen extends JPanel implements Runnable, KeyListener {

	private float x=0,y=0;         //coordinates of window
	private float speed=0;
	public static float line=110;
	private Thread thread;
	private Dino dino;
	
	public GameScreen() {
		thread = new Thread(this);		//creating a thread.  if we don't the main thread will be free and can't do any thing on the program when its running 
		dino=new Dino();      //creating object of dino
	}
	
	
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());  //fills the game window with white color
		g.setColor(Color.blue);                    //color of line
		g.drawLine(0, (int)line, getWidth(), (int)line);   //puts a line on which dino jumps
		dino.draw(g);
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(dino.getY()>50)                   //prevents jump in air agin untill reached on line
		dino.jump();
		System.out.println("pressed");
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("released");
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
				
				dino.update();
				//System.out.println("abc");
				repaint();			// repaint will call paint method again and the rectangle will draw again
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void StartGame() {
		thread.start();			   		//To start the java thread start() is used
	}
	
}
