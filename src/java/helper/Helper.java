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
		boolean already[][] = new boolean[size][size];
		already[originX][originY] = true;
		ArrayList<Step> steps = new ArrayList<>();
		explore(tunnel, already, steps, originX, originY, destX, destY);
		return minSteps;
	}
	
	public static int size=6;
	private static ArrayList<Step> minSteps;
	private static int minTime = 10000;
	private static int getDistance(boolean tunnel[][], ArrayList<Step> steps) {
		int distance = 0;
		for (Step s: steps) {
			if (tunnel[s.x][s.y] == true) distance++;
			else distance += 2;
		}
		return distance;
	}
	private static void explore(boolean tunnel[][], boolean already[][], ArrayList<Step> steps, int origX, int origY, int destX, int destY) {
		if (origX == destX && origY == destY) {
			int distance = getDistance(tunnel, steps);
			if (distance < minTime) {
				minTime = distance;
				minSteps = new ArrayList<Step>(steps);
			}
			return;
		} else {
			//left
			if (origX != 0 && !already[origX-1][origY]) {
				Step newStep = new Step();
				newStep.x = origX-1;
				newStep.y = origY;
				already[origX-1][origY] = true;
				ArrayList<Step> alreadyStepped = new ArrayList<>(steps);
				alreadyStepped.add(newStep);
				explore(tunnel, already, alreadyStepped, origX-1, origY, destX, destY);
				already[origX-1][origY] = false;	
			}
			//bottom
			if (origY != size-1 && !already[origX][origY+1]) {
				Step newStep = new Step();
				newStep.x = origX;
				newStep.y = origY+1;
				already[origX][origY+1] = true;
				ArrayList<Step> alreadyStepped = new ArrayList<>(steps);
				alreadyStepped.add(newStep);
				explore(tunnel, already, alreadyStepped, origX, origY+1, destX, destY);
				already[origX][origY+1] = false;
			}
			//right
			if (origX != size-1 && !already[origX+1][origY]) {
				Step newStep = new Step();
				newStep.x = origX+1;
				newStep.y = origY;
				already[origX+1][origY] = true;
				ArrayList<Step> alreadyStepped = new ArrayList<>(steps);
				alreadyStepped.add(newStep);
				explore(tunnel, already, alreadyStepped, origX+1, origY, destX, destY);
				already[origX+1][origY] = false;
			}
			//top
			if (origY != 0 && !already[origX][origY-1]) {
				Step newStep = new Step();
				newStep.x = origX;
				newStep.y = origY-1;
				already[origX][origY-1] = true;
				ArrayList<Step> alreadyStepped = new ArrayList<>(steps);
				alreadyStepped.add(newStep);
				explore(tunnel, already, alreadyStepped, origX+1, origY, destX, destY);
				already[origX][origY-1] = false;
			}
		}
	}
}
