package aStar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMove implements MouseMotionListener{
	
	public static int x, y;
	
	public MouseMove() {
		super();
		x = 0;
		y = 0;
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
}
