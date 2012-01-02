package uk.co.mrrobinsmith.planetsim.sim;
import uk.co.mrrobinsmith.planetsim.base.*;

/**
 * DataAnalyser stores and returns data for PlanetSim.
 * 
 * @author Robin Smith
 * @version 1 (16/11/2010)
 */

public class DataAnalyser
{

	private IntDataParameter simTime;
    private IntDataParameter bodiesOffScreen;
    private IntDataParameter bodiesOnScreen;
    private IntDataParameter mergedBodies;
    private final int N_PARAMS = 4;
    private Parameter[] data = new Parameter[N_PARAMS];

    
    /**
     * Creates a new DataAnalyser object with default initial data values.
     */
    public DataAnalyser()
    {
    	int i = 0;
    	
    	simTime = new IntDataParameter("Sim time", 0, false);
    	data[i++] = simTime;
    	
    	bodiesOffScreen = new IntDataParameter("Bodies off screen", 0, false);
    	data[i++] = bodiesOffScreen;
    	
    	bodiesOnScreen = new IntDataParameter("Bodies on screen", 0, false);
    	data[i++] = bodiesOnScreen;
    	
    	mergedBodies = new IntDataParameter("Merged bodies", 0, false);
    	data[i++] = mergedBodies;
    }
    
    /**
     * Gets the current simulation time step.
     * @return simTime the int time step.
     */
    public int getSimTime()
    {
    	return simTime.getValue();
    }
    
    /**
     * Gets the set of data parameters from DataAnalyser.
     * @return the array of Parameters.
     */
    public Parameter[] getParams()
    {
    	return data;
    }

    /**
     * Increments simTime by one.
     */
    public void incSimTime()
    {
    	simTime.incValue();
    }
    
    /**
     * Increments the number of off-screen balls by one.
     */
    public void incBodiesOffScreen()
    {
    	bodiesOffScreen.incValue();
    }
    
    /**
     * Decrements the number of off-screen balls by one.
     */
    public void decBodiesOffScreen()
    {
    	bodiesOffScreen.decValue();
    }
    
    /**
     * Increments the number of on-screen balls by one.
     */
    public void incBodiesOnScreen()
    {
    	bodiesOnScreen.incValue();
    }
    
    /**
     * Decrements the number of off-screen balls by one.
     */
    public void decBodiesOnScreen()
    {
    	bodiesOnScreen.decValue();
    }
    
    /**
     * Increments the number of merged bodies by one.
     */
    public void incMergedBodies()
    {
    	mergedBodies.incValue();
    }
    
    /**
     * Resets all of the data values to defaults.
     */
    public void reset()
    {
    	for (Parameter param : data) {
    		param.setDefault();
    	}
    }
    
}