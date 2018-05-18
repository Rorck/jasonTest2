/**
 * 
 */
package helper;

import java.util.ArrayList;
import java.math.*;
import env.planetEnv;

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
		minX = originX < destX ? originX : destX;
		maxX = originX > destX ? originX : destX;
		minY = originY < destY ? originY : destY;
		maxY = originY > destY ? originY : destY;
		System.out.println(minX + ";" + minY + " "+ maxX +";"+maxY);
		explore(tunnel, already, steps, originX, originY, destX, destY);
		return minSteps;
	}
	
	public static int size=30;
	private static int minX, minY, maxX, maxY;
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
		System.out.println(origX + ";" + origY);
		if (origX > maxX || origY > maxY) return;
		if (origX == destX && origY == destY) {
			int distance = getDistance(tunnel, steps);
			if (distance < minTime) {
				minTime = distance;
				minSteps = new ArrayList<Step>(steps);
			}
			return;
		} else {
			//left
			if (origX != minX && !already[origX-1][origY]) {
				Step newStep = new Step();
				newStep.x = origX-1;
				newStep.y = origY;
				//System.out.println("balra");
				already[origX-1][origY] = true;
				ArrayList<Step> alreadyStepped = new ArrayList<>(steps);
				alreadyStepped.add(newStep);
				explore(tunnel, already, alreadyStepped, origX-1, origY, destX, destY);
				already[origX-1][origY] = false;	
			}
			//bottom
			if (origY != maxY && !already[origX][origY+1]) {
				Step newStep = new Step();
				newStep.x = origX;
				newStep.y = origY+1;
				//System.out.println("le");
				already[origX][origY+1] = true;
				ArrayList<Step> alreadyStepped = new ArrayList<>(steps);
				alreadyStepped.add(newStep);
				explore(tunnel, already, alreadyStepped, origX, origY+1, destX, destY);
				already[origX][origY+1] = false;
			}
			//right
			if (origX != maxX && !already[origX+1][origY]) {
				Step newStep = new Step();
				newStep.x = origX+1;
				newStep.y = origY;
				//System.out.println("jobbra");
				already[origX+1][origY] = true;
				ArrayList<Step> alreadyStepped = new ArrayList<>(steps);
				alreadyStepped.add(newStep);
				explore(tunnel, already, alreadyStepped, origX+1, origY, destX, destY);
				already[origX+1][origY] = false;
			}
			//top
			if (origY != minY && !already[origX][origY-1]) {
				Step newStep = new Step();
				newStep.x = origX;
				newStep.y = origY-1;
				//System.out.println("fel");
				already[origX][origY-1] = true;
				ArrayList<Step> alreadyStepped = new ArrayList<>(steps);
				alreadyStepped.add(newStep);
				explore(tunnel, already, alreadyStepped, origX+1, origY, destX, destY);
				already[origX][origY-1] = false;
			}
		}
	}
	
	public static void debug(){
		planetEnv.gui.out("Hahó");
		System.out.println("Megyen");
	}
	
	private static int distance[][] = null;
	private static int remaining[][] = new int[size][size];
	private static ArrayList<Step>[][] moving;
	
	public static void getDistanceInTunnel(boolean[][] tunnel, int origX, int origY){
		if (distance == null) {
			distance = new int[size][size];
			for (int i = 0; i < size; ++i)
				for  (int j = 0; j < size; ++j) {
					moving[i][j] = new ArrayList<>();
					distance[i][j] = 10000;
				}
		}
		
		ArrayList<Step> steps = new ArrayList<>();
		
		setDistance(tunnel, 0, origX, origY, steps);
		
		return;
	}
	
	public static int getFreeDistance(int origX, int origY, int destX, int destY) {
		return Math.abs(origX-destX)+Math.abs(origY-destY); 
	}
	
	public static void setDistance(boolean[][] tunnel, int origD, int origX, int origY, ArrayList<Step> steps){
		//moving[origX][origY] = new ArrayList<>(steps);
		Step newStep = new Step();
		newStep.x = origX;
		newStep.y = origY;
		ArrayList<Step> newSteps = new ArrayList<>(steps);
		if (origX!=0 && tunnel[origX-1][origY] && distance[origX-1][origY]>origD+1){
			distance[origX-1][origY] = origD + 1;
			newStep.x--;
			newSteps.add(newStep);
			setDistance(tunnel, origD+1, origX-1, origY, newSteps);
		}
		if (origX!=size-2 && tunnel[origX+1][origY] && distance[origX+1][origY]>origD+1){
			distance[origX+1][origY] = origD + 1;
			newStep.x++;
			newSteps.add(newStep);
			setDistance(tunnel, origD+1, origX+1, origY, newSteps);
		}
		if (origY!=0 && tunnel[origX][origY-1] && distance[origX][origY-1]>origD+1){
			distance[origX][origY-1] = origD + 1;
			newStep.y--;
			newSteps.add(newStep);
			setDistance(tunnel, origD+1, origX, origY-1, newSteps);
		}
		if (origY!=size-2 && tunnel[origX][origY+1] && distance[origX][origY+1]>origD+1){
			distance[origX][origY+1] = origD + 1;
			newStep.y--;
			newSteps.add(newStep);
			setDistance(tunnel, origD+1, origX, origY+1, newSteps);
		}
	}
	
	public static void setRemaining(boolean[][] tunnel, int destX, int destY){
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (tunnel[i][j]) remaining[i][j] = Math.abs(destX-i)+Math.abs(destY-j);
			}
		}
	}
	
	public static ArrayList<Step> getSteps(boolean[][] tunnel, int origX, int origY, int destX, int destY) {
		moving = new ArrayList[size][size];
		getDistanceInTunnel(tunnel, origX, origY);
		ArrayList<Step> anssteps = new ArrayList<>();
		int bestX = origX, bestY = origY;
		boolean oke = false;
		
		
		do {
			setRemaining(tunnel, origX, origY);
			for (int i=0; i<size; ++i)
				for (int j = 0; j<size; ++j) {
					if (tunnel[i][j] && distance[i][j]+2*remaining[i][j] < distance[bestX][bestY]+2*remaining[bestX][bestY]) {
						bestX = i;
						bestY = j;
					}
				}
			if (distance[bestX][bestY] == getFreeDistance(origX, origY, bestX, bestY)){
				oke = true;
				for (int i = moving[bestX][bestY].size()-1; i != 0; --i) {
					anssteps.add(moving[bestX][bestY].get(i));
				}
			}
			else {
				int delta = bestX>destX? 1 : -1;
				int i = destX;
				while (i != bestX) {
					Step newStep = new Step();
					newStep.y = destY;
					newStep.x = i;
					i += delta;
					anssteps.add(newStep);
				}
				delta = bestY>destY? 1 : -1;
				i = destY;
				while (i != bestY) {
					Step newStep = new Step();
					newStep.x = bestX;
					newStep.y = i;
					i += delta;
					anssteps.add(newStep);
				}
				origX = bestX;
				origY = bestY;
			}
		} while (!oke);
		return anssteps;
	}
}
