package env;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlanetGUI extends JPanel  {
	private PlanetCell[][] planet;
    private int cellWidth, cellHeight;
    private int[] agent1; //col1
    private int[] agent2; //col2
    private int[] agent3; //col3
    private int[] agent4; //smallDigger
    private int[] agent5; //bigDigger
    private int[] agent6; //rescueUnit
    private int[] agent7; //supplyUnit
    private planetEnv environment;
    private int gridSize;
    private int middle;

    public PlanetGUI(planetEnv env) {

        environment = env;
        update();
        cellWidth = 20;
        cellHeight = 20;
        setSize(600, 600);
        gridSize = 30;
        middle = gridSize/2;
    }

    public void paintComponent(Graphics g) {
    	
    		for(int row = 0; row<4; row++){
    			for(int col = 0; col < gridSize; col++){
                    if(col == middle && row == 3) {
                        g.setColor(Color.white);
                        g.fillRect((col * cellWidth) +3, (row * cellHeight) +3, cellWidth - 5, cellHeight - 5);
                    }else{
		    			g.setColor(Color.blue);
			            g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
			            g.setColor(Color.gray);
			            g.drawRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                    }
	            }
       		}
    		
    		for(int col = 0; col < gridSize; col++){
    			g.setColor(Color.green);
	            g.fillRect(col * cellWidth, 4 * cellHeight, cellWidth, cellHeight);
	            g.setColor(Color.gray);
	            g.drawRect(col * cellWidth, 4 * cellHeight, cellWidth, cellHeight);
    		}
    	
    	
        for(int row = 5; row<gridSize; row++) {        	
            for(int col = 0; col<gridSize; col++) {

                g.setColor(new Color(185, 58, 17));
                g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                g.setColor(Color.gray);
                g.drawRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);

                if(planet[col][row] != null) {

                        Resource r = (Resource) planet[col][row];
                        int type = r.getType();
                        int amount = r.getAmount();
                        switch(type) {
                        case 1:
                            g.setColor(Color.orange);
                            break;
                        case 2:
                            g.setColor(Color.green);
                            break;
                        case 3:
                            g.setColor(Color.blue);
                            break;
                        case 4:
                        	g.setColor(Color.LIGHT_GRAY);
                        	break;
                        case 5:
                        	g.setColor(Color.YELLOW);
                        }
                        
                        if(type < 4){
	                        g.fillOval(col*cellWidth +3, row*cellHeight +3, cellWidth - 5, cellHeight - 5);
	                        g.setColor(Color.black);
	                        g.setFont(new Font("Arial", Font.PLAIN, 11));
	                        g.drawString(""+amount, (col * cellWidth) +8, (row * cellHeight) +14);
                        }else if(type == 4){
                        	g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                        }else if(type == 5){
                        	g.fillOval(col*cellWidth +3, row*cellHeight +3, cellWidth - 5, cellHeight - 5);
                        }
                    

                }

            }
        }

        /*g.setColor(Color.yellow);
        g.fillRoundRect(agent1[0]*cellWidth +3, agent1[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
        g.setColor(Color.white);
        g.drawString("A", (agent1[0]*cellWidth) +7, (agent1[1]*cellHeight) +14);

        g.setColor(Color.cyan);
        g.fillRoundRect(agent2[0]*cellWidth +3, agent2[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
        g.setColor(Color.white);
        g.drawString("B", (agent2[0]*cellWidth) +7, (agent2[1]*cellHeight) +14);

        g.setColor(Color.black);
        g.fillRoundRect(agent3[0]*cellWidth +3, agent3[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
        g.setColor(Color.white);
        g.drawString("C", (agent3[0]*cellWidth) +7, (agent3[1]*cellHeight) +14);*/
        
        g.setColor(Color.red);
        g.fillRoundRect(agent4[0]*cellWidth +3, agent4[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
        g.setColor(Color.white);
        g.drawString("SD", (agent4[0]*cellWidth) +7, (agent4[1]*cellHeight) +14);

        g.setColor(Color.red);
        g.fillRoundRect(agent5[0]*cellWidth +3, agent5[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
        g.setColor(Color.white);
        g.drawString("BD", (agent5[0]*cellWidth) +7, (agent5[1]*cellHeight) +14);

        g.setColor(Color.red);
        g.fillRoundRect(agent6[0]*cellWidth +3, agent6[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
        g.setColor(Color.white);
        g.drawString("RU", (agent6[0]*cellWidth) +7, (agent6[1]*cellHeight) +14);
        
        g.setColor(Color.red);
        g.fillRoundRect(agent7[0]*cellWidth +3, agent7[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
        g.setColor(Color.white);
        g.drawString("SU", (agent7[0]*cellWidth) +7, (agent7[1]*cellHeight) +14);

    }


    public void update() {

        planet = environment.getPlanet();
        //agent1 = environment.geta1();
        //agent2 = environment.geta2();
        //agent3 = environment.geta3();
        agent4 = environment.getSmallDigger();
        agent5 = environment.getBigDigger();
        agent6 = environment.getRescueUnit();
        agent7 = environment.getSupplyUnit();
        
        repaint();
    }

    public Dimension getPreferredSize() {

        return new Dimension(800, 800);

    }

    public static void main(String[] args) {

    }
}
