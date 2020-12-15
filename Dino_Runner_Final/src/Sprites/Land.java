package Sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

//import static userinterface.GameScreen.groundY;
import anime.Resource;
import Game.GameScreen;

public class Land {
	
	
	private List<ImageLand> listimg;
	private BufferedImage imgland1,imgland2,imgland3;
	private Vector<ImageLand>listimg2;
	private Random random;
	
	
	public Land(GameScreen game) {
		
		random=new Random();
		imgland1=Resource.getResourceImage("images/land33.png");
		imgland2=Resource.getResourceImage("images/land33.png");
		imgland3=Resource.getResourceImage("images/land33.png");
		//imgland3=Resource.getResourceImage("data/land3.png");
		//listimg=new ArrayList<ImageLand>();
		
		listimg2=new Vector<ImageLand>();
		
		
		int no_of_land=800/imgland1.getWidth()+2;
		
		for(int i=0;i<no_of_land;i++) {
			ImageLand img=new ImageLand();
			img.posX=(int)(i*imgland1.getWidth());
			img.img=getImageLand();
			listimg2.add(img);
		}
	}
	
	public void update() {
		for(ImageLand img:listimg2) {
			img.posX-=2;
		}
		
		ImageLand first=listimg2.get(0);
		if(first.posX+imgland1.getWidth()<0) {
			
			first.posX=listimg2.get(listimg2.size()-1).posX+imgland1.getWidth();
			
			listimg2.add(listimg2.get(0));
			listimg2.remove(0);
		}
	}
	
	
	public void draw(Graphics g) {
			
		for(int i=0;i<listimg2.size();i++)
			
			g.drawImage(listimg2.get(i).img,listimg2.get(i).posX,130,imgland1.getWidth(),imgland1.getHeight(),null);
		
		
	/*	for(ImageLand img:listimg2) {
			g.drawImage(img.img,img.posX,130,imgland1.getWidth(),imgland1.getHeight(),null);
		}*/
		
	}
	
	private BufferedImage getImageLand() {
		int i=random.nextInt(15);       // random int from 0->3 (0,1,2)
		switch(i) {
		case 0:
			return imgland1;
		case 1:
			return imgland2;
		
		default:return imgland3;
		}
	}
	
	public class ImageLand {
		
		int posX;
		BufferedImage img;

	}
	
	
	
	

}
