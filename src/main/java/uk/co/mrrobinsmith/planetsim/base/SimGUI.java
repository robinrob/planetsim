package uk.co.mrrobinsmith.planetsim.base;

/**
 * 
 * 
 * @author Robin Smith
 * @version 1 (xx/xx/xxxx)
 */


public interface SimGUI
{
	
	/**
	 * Makes the GUI wait for a specified amount of time.
	 * @param time the time in milliseconds.
	 */
	public void wait(int time);
	
    /**
     * Shows the simulation view ready for the simulation to begin.
     */
    public void showSimView();
    
    /**
     * Makes changes to the GUI relevant to the simulation finishing.
     */
    public void simFinished();
    

}


