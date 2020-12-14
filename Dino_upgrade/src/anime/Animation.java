package anime;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	
	private List frame;
	private int frameIndex=0;
	private int dtime;
	private long ptime;
	
	public Animation(int dtime) {
		this.dtime=dtime;
		frame=new ArrayList<BufferedImage>();
	}
	
	public void update() {
		if(System.currentTimeMillis()-ptime>dtime) {
		
		frameIndex++;
		if(frameIndex>=frame.size()) {
			frameIndex=0;
		}
		ptime=System.currentTimeMillis();
		}
		
	}
	
	
	public void addFrame(BufferedImage image) {
		frame.add(image);
	}
	
	
	public BufferedImage getFrame() {
		if(frame.size()>0) {
			return (BufferedImage) frame.get(frameIndex);
		}
		return null;
	}

}
