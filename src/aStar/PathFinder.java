package aStar;

import java.util.ArrayList;

public class PathFinder {

	private ArrayList<Node> checking, checked;
	public Node current, start, end;
	public boolean done;
	
	public PathFinder(Node start, Node end) {
		checking = new ArrayList<Node>();
		checked = new ArrayList<Node>();
		
		this.start = start;
		this.end = end;
		this.current = start;
		
		this.done = false;
		
		start.startToNode = 0.0;
		checking.add(start);
	}
	
	
	public void tick() {
		/*
		 * Algorithm Setup:
		 * 	1. There are two lists of Nodes, One checked, One not;
		 * 	2. There is a 'Current' Node;
		 * Algorithm Steps:
		 * 	1. if the current node is the end stop;
		 * 	2. remove the current node from the unChecked to the Checked list;
		 * 	3. go through each neighbor of the current node;
		 * 	4. if the neighbor is in the checked list skip it;
		 * 	5. if the neighbor is not in the unChecked list, add it;
		 * 	6. other wise if the new distance to that neighbor is greater than or equal to the current distance it has, skip it;
		 * 	7. set the parent of that neighbor to the current node;
		 * 	8. set the neighbor's distance from the start to the new distance;
		 * 	9. repeat this until either the current node is the end or the unChecked list is empty  
		 */
		
		
		if(checking.size() > 0 && current != end) {	
			
			int smallIndex = 0;
			for(int i = 0; i < checking.size(); i++)
				if(checking.get(i).getCompound() < checking.get(smallIndex).getCompound())
					smallIndex = i;
			
			current = checking.get(smallIndex);
			
			if(current == end)
				return;
			
			checking.remove(current);
			current.searching = false;
			current.searched = true;
			checked.add(current);
			
			ArrayList<Node> tmpNeigh = current.getNeighbors();
			
			for(Node n : tmpNeigh) {
				if(checked.contains(n) || !n.clear)
					continue;
				
				double tmpDistance = current.startToNode + Math.sqrt(Math.pow(n.x - current.x, 2) + Math.pow(n.y - current.y, 2));
				
				if(!checking.contains(n)) {
					checking.add(n);
					n.searching = true;
				}
				else if(tmpDistance >= n.startToNode)
					continue;
				
				n.parent = current;
				n.startToNode = tmpDistance;
			}
		}
		else 
			done = true;
	}
	
}
