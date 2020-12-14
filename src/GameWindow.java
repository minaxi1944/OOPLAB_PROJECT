import javax.swing.JFrame;


public class GameWindow extends JFrame{
	
private GameScreen gameScreen;
	
	public GameWindow() {
		super("Java Dino Runner Game");
		setSize(600,200);
		setLocation(400,200); //window position
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//To close the program when click on close "X"
		gameScreen = new GameScreen();
		add(gameScreen);
		addKeyListener(gameScreen);   					// have to add key listener in this j frame
	}
	
	public void StartGame() {
		gameScreen.StartGame();
	}

}
