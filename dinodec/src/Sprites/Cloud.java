package Sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import anime.Resource;

public class Cloud {
	
	private BufferedImage cloudimg;
	private List<CloudC> cloud;
	
	public Cloud() {
		
		cloudimg=Resource.getResourceImage("images/cloud.png");
		cloud=new ArrayList<CloudC>();
		CloudC c=new CloudC();
		c.posX=100;
		c.posY=60;
		cloud.add(c);
		
		c=new CloudC();
		c.posX=250;
		c.posY=10;
		cloud.add(c);
		
		c=new CloudC();
		c.posX=400;
		c.posY=30;
		cloud.add(c);
		
		c=new CloudC();
		c.posX=500;
		c.posY=20;
		cloud.add(c);
		
	}
	
	public void update() {
		for(CloudC cloud: cloud) {
			cloud.posX--;
			
		}
		
		CloudC first=cloud.get(0);
		if(first.posX+cloudimg.getWidth()<0) {
			first.posX=cloud.get(cloud.size()-1).posX+100;
			cloud.remove(first);
			cloud.add(first);
		}
		
	}
	
	public void draw(Graphics g) {
		for(CloudC cloud: cloud) {
		g.drawImage(cloudimg,(int)cloud.posX,(int)cloud.posY,null);
		}
		CloudC first=cloud.get(0);
		if(first.posX+cloudimg.getWidth()<1) {
			//first.posX=cloud.get(cloud.size()-1).posX+100;
			
			first.posX=600;
			cloud.remove(first);
			cloud.add(first);
		}
		
	}
	
	public class CloudC {

		float posX,posY;
		
	}
	//cra stalcum;

}
