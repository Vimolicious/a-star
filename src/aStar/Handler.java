package aStar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Handler {
	private ArrayList<ArrayList<Node>> grid;
	private PathFinder aStar;

	private boolean searching, started, pushing, infoPanel;
	private double distance;

	private Node start, end, palette;

	private Button startStop, reset, pathClear, info;
	private HUD dist;

	private Paragraph p;


	public Handler() {
		grid = new ArrayList<ArrayList<Node>>();

		searching = false;
		started = false;
		pushing = false;
		infoPanel = false;

		this.distance = Double.POSITIVE_INFINITY;

		this.startStop = new Button(50, 15, "Start", 20, 100, 30, new Color(225, 225, 225), new Color(100, 100, 100), Color.BLACK);
		this.reset = new Button(50, 55, "Reset", 20, 100, 30, new Color(225, 225, 225), new Color(100, 100, 100), Color.BLACK);
		this.pathClear = new Button(350, 55, "Clear Path", 20, 130, 30, new Color(225, 225, 225), new Color(100, 100, 100), Color.BLACK);
		this.info = new Button(Game.WIDTH - 60, 10, "i", 23, 50, 50, new Color(225, 225, 225), new Color(100, 100, 100), Color.BLACK);
		
		this.dist = new HUD((int)(Game.WIDTH / 2.8), 50, "Path: " + distance, 30, "Trebuchet MS", Color.BLACK, true);

		generateField();

		p = new Paragraph(30, 0, "OldSketches/aStar_Resources/test.txt", 10);
	}

	public void tick() {
		/*UPDATE BUTTONS*/
		if(!infoPanel) {
			startStop.tick();
			reset.tick();
			pathClear.tick();
		}
			info.tick();

		/*START STOP BUTTON ACTIONS*/
		if(startStop.actuated) {

			//If the stop/start button is pushed and the search hasn't begun
			if(!started) {
				//Start the search
				started = true;

				//Go through each node and set the distance from that node to the end node
				for(ArrayList<Node> a : grid)
					for(Node n : a)
						n.nodeToEnd = Math.sqrt(Math.pow(n.x - end.x, 2) + Math.pow(n.y - end.y, 2));

				//Set the start and end's status to clear
				start.clear = true;
				end.clear = true;

				//Set the static ArrayList 'Grid' in the Node class equal to the local grid 
				Node.grid = grid;
				//Construct a new path finding agent
				aStar = new PathFinder(start, end);
			}

			//If the button is pushed, flip the status of the searching boolean
			searching = !searching;
		}

		//Based on the status of the search, change the button label
		if(searching && !aStar.done)
			startStop.label.setMessage("Stop");
		else
			startStop.label.setMessage("Start");

		/*RESET BUTTON ACTIONS*/
		if(reset.actuated || KeyInput.getKey(KeyEvent.VK_R)) {
			//When reseting set searching and started values to false

			searching = false;
			started = false;

			//Change the distance to the default of 'Infinity'
			distance = Double.POSITIVE_INFINITY;
			//Update the HUD
			dist.setMessage("Path: " + distance);

			//Generate the default field
			generateField();
		}

		if(pathClear.actuated) {
			
			started = false;
			searching = false;
			
			for(ArrayList<Node> a : grid)
				for(Node n : a) {
					n.parent = null;
					n.searched = false;
					n.searching = false;
					
					distance = Double.POSITIVE_INFINITY;
					dist.setMessage("Path: " + distance);
				}
			aStar = new PathFinder(start, end);
		}
		
		/*INFORMATION BUTTON ACTIONS*/
		if(info.actuated) {
			infoPanel = !infoPanel;
		}

		/*CUSTOMIZATION ACTIONS*/
		if(!started) {

			//If the search has not begun
			//Go through each element in the grid list
			for(ArrayList<Node> a : grid)
				for(Node n : a) {
					//Temporary booleans to determine which node the mouse is on 
					boolean inX = MouseMove.x >= n.x && MouseMove.x <= n.x + n.width;
					boolean inY = MouseMove.y >= n.y && MouseMove.y <= n.y + n.height;

					//If the mouse is on the node
					if(inX && inY) {
						//Check if the mouse is being pushed and the pushing value is false
						if(MouseInput.getPressed() && !pushing) {
							//Then set the pushing value to true
							pushing = true;
							//and change the 'model node' palette to what the current node being clicked
							palette = new Node(n.start, n.end, n.clear);
						}
						//Otherwise if the mouse is not being pushed, have the pushing value reflect that
						else if(!MouseInput.getPressed())
							pushing = false;

						//If the value of pushing is now true
						if(pushing){
							//And the palette has the value of start set to true, and the current node is not the end
							if(palette.start && !n.end) 
								//Set the start node to the current node
								start = n;
							//Otherwise if the palette has the value of end, and the current node is not the start
							else if(palette.end && !n.start)
								//Set the end node to the current node
								end = n;
							//In any other case, if the current node is not the start or end
							else if(!n.start && !n.end)
								//Set the current node clear value to the opposite of the palette's
								n.clear = !palette.clear;
						}
					}

					//Set the value of the start to, the start node is the current node
					n.start = start == n;
					//Set the value of the end to, the end node is the current node
					n.end = end == n;
				}
		}

		//If the search is on
		if(searching)
			//Update the path finder
			aStar.tick();

	}

	public void render(Graphics2D g) {
		g.setColor(new Color(249, 249, 249));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		//Draw all of the nodes
		g.setStroke(new BasicStroke(1));
		for(ArrayList<Node> a : grid)
			for(Node n : a)
				n.render(g);

		//If the search has started
		if(started) {
			//Sets the color and stroke to their specifications
			g.setColor(Color.WHITE);
			g.setStroke(new BasicStroke(4));

			//If the search is on
			if(searching) 
				//Set the distance of the path to zero(temporarily so that it can be added to later)
				distance = 0.0;

			//Create a 'target' node to the current node in the aStar object
			Node target = aStar.current;
			//While the target still has a parent
			while(target.parent != null) {
				//Draw a line from the target node's center to that of the target's parent
				g.drawLine(target.x + target.width / 2, target.y + target.height / 2, target.parent.x + target.parent.width / 2, target.parent.y + target.parent.height / 2);
				//Set the target to the target's parent
				target = target.parent;

				//If the search is on
				if(searching)
					//Each layer will add to the distance
					distance++;
			}

			//If the aStar path finding object is done searching and the ending node is not the desired end
			if(aStar.done && aStar.current != aStar.end)
				//Set the HUD to 'Un-Reachable'
				dist.setMessage("Un-Reachable");
			//Otherwise
			else
				//Set the HUD to 'Path: ' along with the distance's value
				dist.setMessage("Path: " + distance);
		}

		//Render the buttons and HUD
		startStop.render(g);
		reset.render(g);
		pathClear.render(g);
		dist.render(g);
		
		if(infoPanel)
			p.render(g);
		
		info.render(g);
	}

	private void generateField() {
		//Clear the grid list
		grid.clear();

		//Make an int which determines the width and height of the nodes
		int scl = 20;
		//Make an int which determines the horizontal offset(for the HUD and Buttons) of the nodes
		int offset = 100;
		//Make an int which determines the number of nodes to go from left to right
		int gridHeight = (Game.HEIGHT - offset) / scl;
		//Make an int which determines the number of nodes to go from top to bottom
		int gridWidth = Game.WIDTH / scl; 

		//Make the nodes
		for(int i = 0; i < gridHeight; i++) {
			grid.add(new ArrayList<Node>());
			for(int j = 0; j < gridWidth; j++)
				grid.get(i).add(new Node(scl*j, offset + scl*i, scl, scl, j, i));
		}

		//Get the default starting location
		int y1 = grid.size() / 2;
		int x1 = grid.get(y1).size() / 4;

		//Get the default ending location
		int y2 = grid.size() / 2;
		int x2 = grid.get(y2).size() * 3 / 4;

		//Set the start and end nodes to the locations created above
		this.start = grid.get(y1).get(x1);
		this.end = grid.get(y2).get(x2);
	}

	/**
	 * Takes in a value, the range of the value, and maps those values to a new range of given values
	 */
	public double map(double n, double start1, double stop1, double start2, double stop2) {
		return (n - start1) / (stop1 - start1) * (stop2 - start2) + start2;
	}
}
