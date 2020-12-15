package Game;

import javax.swing.JFrame;


public class GameWindow extends JFrame{
	public static final int SCREEN_WIDTH =800;
	
private GameScreen gameScreen;
	
	public GameWindow() {
		super("Java Dino Runner Game");
		setSize(SCREEN_WIDTH, 200);
		setLocation(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//To close the program when click on close "X"
		gameScreen = new GameScreen();
		add(gameScreen);
		addKeyListener(gameScreen);   					// have to add key listener in this j frame
	}
	
	public void StartGame() {
		gameScreen.StartGame();
	}

}
