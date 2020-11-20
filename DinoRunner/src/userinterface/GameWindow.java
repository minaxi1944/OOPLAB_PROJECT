package userinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameWindow extends JFrame {	
	
	private GameScreen gameScreen;
	
	public GameWindow() {
		super("Java Dino Runner Game");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//To close the program when click on close "X"
		gameScreen = new GameScreen();
		add(gameScreen);
		addKeyListener(gameScreen);   					// have to add key listener in this j frame
	}
	
	public void StartGame() {
		gameScreen.StartGame();
	}
	
	public static void main(String args[]) {
		GameWindow gw = new GameWindow();
		gw.setVisible(true);		//displays the window
		gw.StartGame();
	}
}
