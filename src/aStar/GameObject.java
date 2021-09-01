package aStar;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	
	public int x, y, width, height;
	public Rectangle hitBox;
	
	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		setHitBox(new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
	
	public void updateHitBox() {
		setHitBox(new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}
}
