/**
 * 
 */
package helper;

import java.util.ArrayList;

/**
 * @author Levente
 * 
 * Class Helper is providing useful methods that can be called by the agents.
 */
public class Helper {
	public static ArrayList<Step> getFastestPath(boolean tunnel[][], int originX, int originY, 
			int destX, int destY) {
		ArrayList<Step> steps = new ArrayList<Step>();
		return steps;
	}
	
	private static Step minSteps[];
	private static int minTime;
	private static int getDistance(boolean tunnel[][], ArrayList<Step> steps) {
		int distance = 0;
		for (Step s: steps) {
			if (tunnel[s.x][s.y] == true) distance++;
			else distance += 2;
		}
		return distance;
	}
	private static void explore(boolean already[][], int origX, int origY, int destX, int destY) {
		//TODO: Levi megcsinálja asap
	}
}
