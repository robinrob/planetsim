package uk.co.mrrobinsmith.planetsim.base;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * SetupParamPanel extends ParamPanel to provide a JPanel which displays a set
 * of Parameters using SetupParamLines. SetupParamPanel also contains a
 * 'Set to defaults' button and a 'Submit' button.
 * 
 * @author Robin Smith
 * @version 1 (19/10/2010)
 */

public class SetupParamPanel extends ParamPanel
{
    private static final long serialVersionUID = 2407717884636741269L;
    
    private static final int BUTTON_HEIGHT = 20;
    
    private SimGUI gui;

    /**
     * Creates a new SetupParamPanel given an array of Parameters.
     * @param gui the BallSimGUI object of the simulation.
     * @param params Parameters to be displayed by this SetupParamPanel.
     */
    public SetupParamPanel(Parameter[] params, SimGUI gui)
    {
    	super(params);
    	this.gui = gui;
    	
    	paramLines = new SetupParamLine[N_PARAMS];
    	
    	PANEL_HEIGHT = N_PARAMS * LINE_HEIGHT + 2 * BUTTON_HEIGHT;
    	
        createPanel();
    }
    
    /**
     * Constructs the SetupParamPanel.
     */
    protected void createPanel()
    {
        this.setLayout(new GridLayout(0, 1));
        
        /* add a 'Set to defaults' button to the form */
        JButton button = new JButton("Set to defaults");
        button.setPreferredSize(new Dimension(PANEL_WIDTH, BUTTON_HEIGHT));
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { setDefaults(); }
        	});
        this.add(button);
        
        /* this loop adds a line for each parameter containing a label showing
         * the parameter prompt plus a text field for setting the parameter.
         */
        SetupParamLine paramLine;
        for (int i = 0; i < N_PARAMS; ++i) {
        	paramLine = new SetupParamLine(params[i], MAX_NAME_WIDTH);
        	paramLines[i] = paramLine;
        	this.add(paramLine);
        }
        
        PANEL_WIDTH = paramLines[0].getLineWidth();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        /* add a 'Submit' button to the form */
        button = new JButton("Submit");
        button.setPreferredSize(new Dimension(PANEL_WIDTH, BUTTON_HEIGHT));
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { submit(); }
        	});
        this.add(button);
    }
    
    /**
     * Sets the values of all the Parameters from the values in their
     * ParamLines.
     */
    private void submit()
    {
    	for (int i = 0; i < N_PARAMS; ++i) {
    		String s = ((SetupParamLine) paramLines[i]).getFieldString();
    		params[i].setValue(s);
    	}
    	gui.showSimView();
    }
    
    /**
     * Sets all the parameter values to their defaults and updates the text
     * fields for those parameters with the new values.
     */
    private void setDefaults()
    {
    	for (int i = 0; i < N_PARAMS; ++i) {
    		int type = params[i].getType();
    		if (type == Parameter.INT || type == Parameter.DOUBLE) { 
    			params[i].setDefault();
    			((SetupParamLine) paramLines[i]).updateField();
    		}
    	}
    }

}