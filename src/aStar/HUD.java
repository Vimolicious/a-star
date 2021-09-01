package aStar;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class HUD extends GameObject{
	
	private Font font;
	private int size;
	private Color color;
	private String message, face;
	
	public HUD(int x, int y, String message, int size, String face, Color color, boolean bold) {
		super(x, y, 10, 10);
		
		this.message = message;
		this.size = size;
		this.face = face;
		this.color = color;
		
		if(bold)
			this.font = new Font(this.face, Font.BOLD, this.size);
		else
			this.font = new Font(this.face, Font.PLAIN, this.size);
		
		updateHitBox();
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics2D g) {
		g.setColor(this.color);
		g.setFont(font);
		
		FontMetrics met = g.getFontMetrics();
		
		g.drawString(message, getX() - met.stringWidth(message) / 2, getY() + met.getAscent() / 3);
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public void setMessage(String m) {
		this.message = m;
	}
	
}
