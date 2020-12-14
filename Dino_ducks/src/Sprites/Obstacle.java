package Sprites;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Obstacle {

		public abstract Rectangle getBound();
		public abstract void draw(Graphics g);
		public abstract void update();
		public abstract boolean isOutOfScreen();
		public abstract boolean isGameOver();
		public abstract boolean isScoreAdd();
		public abstract void setIsScoreAdd(boolean isScoreAdded);
}
