package uk.co.mrrobinsmith.planetsim.base;
import javax.swing.*;

/**
 * ParamPanel is an abstract class which extends JPanel to provide the basic 
 * functionality to display a set of Parameters using ParamLines. Construction
 * and specific functionality of the ParamLine are defined by concrete
 * subclasses which implement the method createParaml().
 * 
 * @author Robin Smith
 * @version 1 (19/10/2010)
 */

public abstract class ParamPanel extends JPanel
{
    private static final long serialVersionUID = 2407717884636741269L;
	
    protected static final int LINE_HEIGHT = 20;
    protected int PANEL_WIDTH;
    protected int PANEL_HEIGHT;
    
    protected final int MAX_NAME_WIDTH;
    
    protected Parameter[] params;
    protected ParamLine[] paramLines;
    protected int N_PARAMS;

    /**
     * Creates a ParamPanel given an array of Parameters.
     * @param params the Parameters.
     */
    public ParamPanel(Parameter[] params)
    {
    	this.params = params;
    	
    	N_PARAMS = params.length;
        MAX_NAME_WIDTH = getMaxNameWidth();
    }
    
    /**
     * Gets the width of the ParamPanel.
     * @return the width of the ParamPanel
     */
    public int getWidth()
    {
    	return PANEL_WIDTH;
    }
    
    /**
     * Gets the height of the ParamPanel.
     * @return the height of the ParamPanel
     */
    public int getHeight()
    {
    	return PANEL_HEIGHT;
    }
    

    
    /**
     * Gets the length of the longest Parameter name of the Parameters displayed
     * by this ParamPanel.
     * @return the int length.
     */
    private int getMaxNameWidth()
    {
    	int length = 0;
    	for (Parameter param : params) {
    		String name = param.getName();
    		if (name.length() > length) {
    			length = name.length();
    		}
    	}
    	return length;
    }
    
    /**
     * Creates the ParamPanel.
     */
    protected abstract void createPanel();
        


}