package aStar;

import java.awt.Color;
import java.awt.Graphics2D;

public class Square extends GameObject{
	
	public Color c;
	
	public Square(int x, int y, int width, int height, Color c) {
		super(x, y, width, height);
		
		this.c = c;
		
		this.updateHitBox();
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics2D g) {
		g.setColor(c);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
