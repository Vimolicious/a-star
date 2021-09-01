package aStar;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Node {

	public int x, y, width, height, gridX, gridY;
	public double startToNode, nodeToEnd;
	public Node parent;

	public boolean clear, start, end, searched, searching;
	public static ArrayList<ArrayList<Node>> grid;

	public Node(int x, int y, int width, int height, int gridX, int gridY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.gridX = gridX;
		this.gridY = gridY;

		this.parent = null;

		clear = true;
		searched = false;
		searching = false;

		this.startToNode = Double.POSITIVE_INFINITY;
	}
	
	public Node(boolean start, boolean end, boolean clear) {
		this.start = start;
		this.end = end;
		
		this.clear = clear;
	}

	public void render(Graphics2D g) {
		if(start)
			g.setColor(new Color(10, 255, 100));
		else if(end)
			g.setColor(new Color(224, 74, 74));
		else if(clear)
			if(searched)
				g.setColor(new Color(100, 100, 100));
			else if(searching)
				g.setColor(new Color(125, 125, 125));
			else
				g.setColor(new Color(71, 71, 71));

		else 
			g.setColor(new Color(25, 25, 25));

		g.fillRect(x, y, width, height);

		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}

	public ArrayList<Node> getNeighbors() {
		ArrayList<Node> tmp = new ArrayList<Node>();

		for(int i = -1; i < 2; i++)
			for(int j = -1; j < 2; j++) {
				if(i+gridY < grid.size() && i+gridY >= 0)
					if(j+gridX < grid.get(i+gridY).size() && j+gridX >= 0)
						tmp.add(grid.get(i+gridY).get(j+gridX));
			}

		return tmp;
	}

	public double getCompound() {
		return startToNode + nodeToEnd;
	}
}
