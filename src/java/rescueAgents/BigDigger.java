package rescueAgents;

import java.util.ArrayList;

import helper.Step;

public class BigDigger {
	public static boolean supplierIsFaster(ArrayList<Step> steps, boolean[][] tunnel) {
		int lepese = 0;
		for (Step s: steps) {
			if (tunnel[s.x][s.y]) lepese += 2;
			else lepese += 4; 
		}
		return 6*steps.size() > lepese;
	}
}
