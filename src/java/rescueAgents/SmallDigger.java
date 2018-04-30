package rescueAgents;

import java.util.ArrayList;

import helper.Step;

public class SmallDigger {
	private ArrayList<Step> steps;
	boolean[][] tunnels;
	public int x,y, actualStep = 0;
	private int counter = 0;
	
	public SmallDigger(ArrayList<Step> requiredSteps, boolean[][] alreadyTunnel){
		steps = new ArrayList<Step>(requiredSteps);
		tunnels = alreadyTunnel;
	}
	
	public void makeStep(){
		if (tunnels[x][y] || counter == 1) {
			x = steps.get(actualStep).x;
			y = steps.get(actualStep).y;
			actualStep++;
			counter = 0;
		} else counter++;
	}
}
