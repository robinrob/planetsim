package uk.co.mrrobinsmith.planetsim.base;
import java.awt.*;


/**
 * AdjustableParamPanel extends ParamPanel to provide a JPanel which displays a
 * set of Parameters using AdjustableParamLines. AdjustableParamPanel allows
 * adjustment of adjustable Parameters during running of the simulation.
 * 
 * @author Robin Smith
 * @version 1 (19/10/2010)
 */

public class AdjustableParamPanel extends ParamPanel
{
    private static final long serialVersionUID = 2407717884636741269L;
    
    /**
     * Creates a new AdjustableParamPanel given an array of Parameters.
     * @param params Parameters to be displayed by this AdjustableParamPanel.
     */
    public AdjustableParamPanel(Parameter[] params)
    {
    	super(params);
    	
    	paramLines = new AdjustableParamLine[params.length];
    	
    	PANEL_HEIGHT = N_PARAMS * LINE_HEIGHT;
    	
        createPanel();
    }
    
    /**
     * Constructs the AdjustableParamPanel; used by the constructor.
     */
    protected void createPanel()
    {
        this.setLayout(new GridLayout(0, 1));
        
        AdjustableParamLine paramLine;
        for (int i = 0; i < N_PARAMS; ++i) {
        	paramLine = new AdjustableParamLine(params[i], MAX_NAME_WIDTH);
        	paramLines[i] = paramLine;
        	this.add(paramLine);
        }
        
        PANEL_WIDTH = paramLines[0].getLineWidth();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

}


