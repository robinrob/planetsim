package uk.co.mrrobinsmith.planetsim.base;
import java.awt.*;


/**
 * DataParamPanel extends ParamPanel to provide a JPanel displaying the
 * Parameters of a DataAnalyser, which are dynamic during running of the
 * simulation.
 * 
 * @author Robin Smith
 * @version 1 (11/11/2010)
 */

public class DataParamPanel extends ParamPanel {
	
	private static final long serialVersionUID = -1567796512005906973L;

	/**
	 * Creates a new DataParamPanel given an array of Parameters.
	 * @param params Parameters to be displayed by this DataParamPanel.
	 */
	public DataParamPanel(Parameter[] params)
	{
		super(params);
		
    	paramLines = new DataParamLine[params.length];
    	PANEL_HEIGHT = N_PARAMS * LINE_HEIGHT;
		
		createPanel();
	}
	
	/**
	 * Constructs the DataParamPanel.
	 */
	protected void createPanel()
	{
        this.setLayout(new GridLayout(0, 1));
        
        DataParamLine paramLine;
        for (int i = 0; i < N_PARAMS; ++i) {
        	paramLine = new DataParamLine(params[i], MAX_NAME_WIDTH);
        	paramLines[i] = paramLine;
        	this.add(paramLine);
        }
        
        PANEL_WIDTH = paramLines[0].getLineWidth();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}

}
