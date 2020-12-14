package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreFile {
	
	public static void write_to_file(int highscore){
		
		
		try {
		      FileWriter myWriter = new FileWriter("images/Score.txt",false);
		      myWriter.write(String.valueOf(highscore));
		      
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		}
	
	public static int read_from_file(){
		 int score=0;
		
		     // FileWriter myWriter = FileWriter("images/Score.txt",false);
		     // myWriter.write(String.valueOf(highscore));
			Scanner scanner;
			try {
				scanner = new Scanner(new File("images/Score.txt"));
				while(scanner.hasNext())
				score=scanner.nextInt();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  
		 return(score);

		}

}
