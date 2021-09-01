package aStar;

import java.awt.Color;
import java.awt.Graphics2D;

public class Button extends GameObject{
	
	public boolean pushing, actuated, circle;
	private Color primary, secondary;
	public HUD label;
	
	public Button(int x, int y, String message, int size, int width, int height, Color primary, Color secondary, Color text) {
		super(x, y, width, height);
		
		this.primary = primary;
		this.secondary = secondary;
		
		pushing = false;
		actuated = false;
		
		label = new HUD(x + width / 2, y + height / 2, message, size, "Trebuchet MS", text, true);
		
		this.circle = false;
	}
	
	public void tick() {
		if(actuated)
			actuated = false;
		
		boolean inX = MouseMove.x >= x && MouseMove.x <= x + width;
		boolean inY = MouseMove.y >= y && MouseMove.y <= y + height;
		
		if(inX && inY && MouseInput.getPressed())
			pushing = true;
		else if(inX && inY && !MouseInput.getPressed() && pushing) {
			actuated = true;
			pushing = false;
		}
		else
			pushing = false;
	}
	
	public void render(Graphics2D g) {
		g.setColor(primary);
		if(pushing)
			g.setColor(secondary);
		
		if(circle) 
			g.fillOval(x, y, width, height);
		else
			g.fillRect(x, y, width, height);
		label.render(g);
	}
}
