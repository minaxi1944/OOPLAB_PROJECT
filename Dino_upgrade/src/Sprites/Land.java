package Sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import static userinterface.GameScreen.groundY;
import anime.Resource;
import Game.GameScreen;

public class Land {
	
	
	private List<ImageLand> listimg;
	private BufferedImage imgland1,imgland2,imgland3;
	
	private Random random;
	
	
	public Land(GameScreen game) {
		
		random=new Random();
		imgland1=Resource.getResourceImage("images/gameplay1A.png");
		imgland2=Resource.getResourceImage("images/gameplay1B.png");
		imgland3=Resource.getResourceImage("images/gameplay1C.png");
		//imgland3=Resource.getResourceImage("data/land3.png");
		listimg=new ArrayList<ImageLand>();
		
		int no_of_land=800/imgland1.getWidth()+2;
		
		for(int i=0;i<no_of_land;i++) {
			ImageLand img=new ImageLand();
			img.posX=(int)(i*imgland1.getWidth());
			img.img=getImageLand();
			listimg.add(img);
		}
	}
	
	public void update() {
		for(ImageLand img:listimg) {
			img.posX-=2;
		}
		
		ImageLand first=listimg.get(0);
		if(first.posX+imgland1.getWidth()<0) {
			
			first.posX=listimg.get(listimg.size()-1).posX+imgland1.getWidth();
			
			listimg.add(listimg.get(0));
			listimg.remove(0);
		}
	}
	
	
	public void draw(Graphics g) {
			
		for(ImageLand img:listimg) {
			g.drawImage(img.img,img.posX,90,imgland1.getWidth(),100,null);
		}
		
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
	
	
	

}
