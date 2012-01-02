package uk.co.mrrobinsmith.planetsim.sim;

import java.util.Random;
import java.lang.Math;

import uk.co.mrrobinsmith.planetsim.base.*;
/**
 * PlanetSim runs a graphical simulation of planets orbiting a central Star.
 * User collisions with the system during run-time is possible using the GUI.
 *
 * @author Robin Smith
 * @version 1 (17/11/2010)
 */

public class PlanetSim implements Runnable
{
   private static final String[] PLANET_COLS = {"cyan", "yellow", "red",
                                                 "blue", "white", "pink",
                                                 "green"};
   private static final String[] STAR_COLS = {"yellow", "red", "green",
                                               "blue", "white", "pink",
                                               "cyan"};
   private static final String[] ROGUE_COLS = {"green", "yellow", "red",
                                              "blue", "white", "pink",
                                              "cyan"};
	
    private PlanetSimGUI gui; 
    private Body[] bodies;
    private Body star;
    private DataAnalyser data;
    private Random random;
    private static final double DELTA_T = 0.01;
    private static final double DEFAULT_TIME_STEP = 16;
    private static final int RANDOM_SEED = 200;
    
    /* condition for execution of run() method */
    private boolean isRunning = false;
    /* whether or not the simulation is isPaused */
    private boolean isPaused = false;
    
    //simulation parameters
    private BoundIntParameter simWidth;
    private BoundIntParameter simHeight;
    private BoundIntParameter nPlanets;
    private BoundDoubleParameter planSpd;
    private BoundDoubleParameter planOrb;
    private BoundDoubleParameter simSpeed;
    private BoundDoubleParameter planMass;
    private BoundDoubleParameter starMass;
    private BoundDoubleParameter rogueMass;
    private BoundDoubleParameter grvConst;
    private ColorParameter planCol;
    private ColorParameter starCol;
    private ColorParameter rogueCol;
    private BooleanParameter trails;
    private BooleanParameter collisions;
	private final int N_PARAMS = 15;
	private Parameter[] params;
    
    /**
     * Constructor for PlanetSim.
     */
    public PlanetSim(PlanetSimGUI gui)
    {
    	this.gui = gui;
    	data = new DataAnalyser();
    	random = new Random(RANDOM_SEED);
    	
    	params = new Parameter[N_PARAMS];
    	int i = 0;
    	
    	simWidth = new BoundIntParameter("Simulation width", 800, 200, 2000,
    	                                 false);
    	params[i++] = simWidth;
    	
    	simHeight = new BoundIntParameter("Simulation height", 600, 200, 2000,
    	                                  false);
    	params[i++] = simHeight;
    	
    	nPlanets = new BoundIntParameter("Number of planets", 50, 1, 1000, false);
    	params[i++] = nPlanets;
    	
    	planSpd = new BoundDoubleParameter("Planet speed", 4 * power(10, 3), 0.1,
    	                                 power(10, 10), false);
      	params[i++] = planSpd;
      	
    	planOrb = new BoundDoubleParameter("Planet orbit", 0.12, 0.0, 1.0, false);
     	params[i++] = planOrb;
     	
    	simSpeed = new BoundDoubleParameter("Simulation speed", 1.0, 0.125,
    	                                  DEFAULT_TIME_STEP, true);
    	params[i++] = simSpeed;
    	
    	planMass = new BoundDoubleParameter("Planet core mass", 1.0, 0.0,
    	                                  power(10, 10), true);
    	params[i++] = planMass;
    	
    	starMass = new BoundDoubleParameter("Star core mass", power(10, 6), 0.0,
    	                                  power(10, 10), true);
    	params[i++] = starMass;
    	
    	rogueMass = new BoundDoubleParameter("Rogue core mass", power(10, 2), 0.0,
    	                                  power(10, 10), true);
    	params[i++] = rogueMass;
    	
    	grvConst = new BoundDoubleParameter("Gravitational const.", 0.001, 0.0,
 	                                     100, true);
    	params[i++] = grvConst;
    	
    	planCol = new ColorParameter("Planet colour", PLANET_COLS, true);
    	params[i++] = planCol;

    	starCol = new ColorParameter("Star colour", STAR_COLS, true);
    	params[i++] = starCol;
    	
    	rogueCol = new ColorParameter("Rogue colour", ROGUE_COLS, true);
    	params[i++] = rogueCol;
    	
    	trails = new BooleanParameter("Trails on", false, true);
    	params[i++] = trails;
    	
    	collisions = new BooleanParameter("Body collisions", true, true);
    	params[i++] = collisions;
    }
    
    /**
     * Gets the array of setup Parameters for the simulation.
     * @return an array of type Parameter.
     */
    public Parameter[] getSetupParams()
    {
    	return params;
    }
    
    
    /**
     * Gets the dynamic data parameters of the simulation.
     * @return an array of type Parameter.
     */
    public Parameter[] getDataParams()
    {
    	return data.getParams();
    }
    
    /**
     * 
     * @return the width of the simulation space
     */
    public int getWidth()
    {
    	return simWidth.getValue();
    }
    
    /**
     * 
     * @return the height of the simulation space
     */
    public int getHeight()
    {
    	return simHeight.getValue();
    }
    
    /**
     * Gets all the bodies in this simulation.
     * @return an array of Body.
     */
    public Body[] getBodies()
    {
    	return bodies;
    }
    
    /**
     * Returns a boolean value to indicate whether or not the simulation is
     * currently running.
     * @return true or false.
     */
    public boolean isRunning()
    {
    	return isRunning;
    }
    
    /**
     * Checks whether 'trails' are currently on or off in this PlanetSim.
     * @return true or false.
     */
    public boolean trailsOn()
    {
    	return trails.getValue();
    }
    
    /**
     * Checks whether 'collisions' are currently on or off in this PlanetSim.
     * @return true or false.
     */
    public boolean collisionsOn()
    {
    	return collisions.getValue();
    }
    
    /**
     * Creates the planets for the simulation.
     */
    public void createBodies()
    {
    	int n = nPlanets.getValue();
        bodies = new Body[n + 1];
        
        int xBound = (int) (simWidth.getValue() * Body.SCALE_FACTOR);
        int yBound = (int) (simHeight.getValue() * Body.SCALE_FACTOR);
        
        double r = planOrb.getValue() * xBound;
        double xStar = (xBound / 2.0);
        double yStar = (yBound / 2.0);
        
        star = new Body(xStar, yStar, 0.0, 0.0, starMass, grvConst, starCol,
                        gui.getCanvas(), data, this);
        bodies[0] = star;
        
        double w;
        r = (planOrb.getValue() + 0.001 * random.nextInt(100)) * xBound;
        double x;
        double y;
        double speed = planSpd.getValue();
        double xVel;
        double yVel;
        for (int i = 0; i < n ; ++i) {
        	w = i * 2 * Math.PI / (double) n;
        	x = xStar + r * Math.sin(w);
        	y = yStar + r * Math.cos(w) * -1;
        	
        	xVel = speed * Math.cos(w);
        	yVel = speed * Math.sin(w);
        	
        	bodies[i + 1] = new Body(x, y, xVel, yVel, planMass, grvConst,
        	                         planCol, gui.getCanvas(), data, this);
        }
    }
    
    /**
     * Draws all the simObjs in the simulation.
     */
    public void drawBodies()
    {
    	for (Body body : bodies) {
    		body.draw();
    	}
    }
    
    /**
     * Removes the given Body from the simulation.
     * @param body the Body to be removed.
     */
    public void removeBody(Body deadBody)
    {
    	for (int i = 0; i < bodies.length; ++i) {
    		if (bodies[i] != null) {
    			if (bodies[i].equals(deadBody)) {
    				bodies[i] = null;
    				data.decBodiesOnScreen();
    		    	data.incMergedBodies();
    			}
    		}
    	}
    }
    
    /**
     * Introduces a rogue planet to the simulation to make it more interesting.
     */
    public void addRogue()
    {       
        double speed = planSpd.getValue();
        double r = planOrb.getValue() * simWidth.getValue()
        							  * Body.SCALE_FACTOR * 2.0;
        
    	double w =  0.01 * random.nextInt(200) * Math.PI;
    	double x = star.getXPos() + r * Math.sin(w);
    	double y = star.getYPos() + r * Math.cos(w) * - 1;
    	double xVel = 0.01 * random.nextInt(100) * speed * Math.cos(w);
    	double yVel = 0.01 * random.nextInt(100) * speed * Math.sin(w * -1);
        
        Body rogue = (new Body(x, y, xVel, yVel, rogueMass, grvConst, rogueCol,
                               gui.getCanvas(), data, this));
        
        Body[] newBodies = new Body[bodies.length + 1];
        int i = 0;
        while (i < bodies.length) {
        	newBodies[i] = bodies[i];
        	++i;
        }
        newBodies[i] = rogue;
        bodies = newBodies;
    }
         
    /**
     * Calculates n to the power of m.
     * @param n argument to the function.
     * @param m exponent to raise n to.
     * @return m to the power of n.
     */
    private double power(double n, int m)
    {
    	int result = 1;
    	for (int i = 0; i < m; ++i) {
    		result *= n;
    	}
    	return result;
    }
    
    /**
     * Causes the run() method to return.
     */
    public void stop()
    {
    	isRunning = false;
    }
    
    /**
     * Pauses the simulation.
     */
    public void pause() {
        isPaused = true;
    }
    
    /**
     * Resets the BallSim's DataAnalyser.
     */
    public void resetData()
    {
    	data.reset();
    }
    
    private void moveBodies()
    {
    	for (Body body : bodies) {
			if (body != null) {
				body.move(DELTA_T);
			}
		}    
    }
        
    /**
     * Runs the simulation.
     */
    public void run()
    {
    	isRunning = true;
    	isPaused = false;
    	moveBodies();
    	while(isRunning) {
    		if (isPaused) {
    			return;
    		}
    		moveBodies();		
    		data.incSimTime();
    		gui.wait((int) (DEFAULT_TIME_STEP / (simSpeed.getValue())));
    	}
    	gui.simFinished();
    }

}
