package userinterface;

import java.awt.Color;

import javax.swing.JPanel;

public class GameScreen extends JPanel implements Runnable {
	
	private int i=0;
	private Thread thread;

	public GameScreen() {
		setBackground(Color.CYAN);
		thread = new Thread(this);		//creating a thread.  if we don't the main thread will be free and can't do any thing on the program when its running 
	}
	
	public void StartGame() {
		thread.start();			   		//To start the java thread start() is used
	}
	
	@Override
	public void run() { 			 //when the thread starts it will call the run method. Whatever the thread is supposed to do when it executes must be included in the implementation of the run() method.
		while(true) {				//Game loop :Control infinity loop the makes the game keep running
			System.out.println(i++);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
