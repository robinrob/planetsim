package uk.co.mrrobinsmith.planetsim.base;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;


/**
 * DataParamLine displays a Parameter associated with a DataAnalyser object.
 * As such the displayed Parameters are dynamic during running of the
 * simulation that is using the DataAnalyser object and the DataParamLine's
 * display is updated when these values change.
 * 
 * @author Robin Smith
 * @version 1 (11/11/2010)
 */

public class DataParamLine extends ParamLine
{
	private static final long serialVersionUID = 4873906016259042710L;
    
    private JLabel valueLabel;

    /**
     * Creates a DataParamLine given a Parameter (which must be one of the 
     * xDataParameter types, where x is Int, Double, Color etc.), and a width.
     * @param param the Parameter to be displayed.
     * @param lineWidth the int width for the DataParamLine.
     */
	public DataParamLine(Parameter param, int lineWidth)
	{
		super(param, lineWidth);
		
		this.param = param;
		if (param.getType() == Parameter.INT) {
			((IntDataParameter) param).setParamLine(this);
		}
		else if (param.getType() == Parameter.DOUBLE) {
			((DoubleDataParameter) param).setParamLine(this);
		}
		
		LINE_WIDTH = NAME_LABEL_WIDTH + VALUE_LABEL_WIDTH;
		this.setPreferredSize(new Dimension(LINE_WIDTH, LINE_HEIGHT));
		
		createParamLine();
	}
	
	/**
	 * Creates the DataParamLine, used by the constructor.
	 */
	protected void createParamLine()
	{
		this.setLayout(new BorderLayout());
    	this.add(createNameLabel(), BorderLayout.WEST);
    	valueLabel = createValueLabel();
    	this.add(valueLabel, BorderLayout.EAST);
	}
    
    /**
     * Updates the Parameter value displayed by this DataParamLine.
     */
    public void updateDisplay()
    {
    	String s = param.getValueString();
    	valueLabel.setText(Formatter.leftJustify(s, VALUE_WIDTH));
    }
	
}
