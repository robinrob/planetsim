package uk.co.mrrobinsmith.planetsim.base;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ParamLine is an abstract class that provides a superclass for concrete types
 * of ParamLine. A ParamLine takes a Parameter and creates a JPanel with various
 * labels and buttons related to the Parameter. Construction and functionality
 * of the ParamLine are defined by concrete subclasses which implement the
 * method createParamLine().
 * 
 * @author Robin Smith
 * @version 1 (11/11/2010)
 */

public abstract class ParamLine extends JPanel
{
	private static final long serialVersionUID = -2252632661498155602L;
	
    protected static final int LINE_HEIGHT = 20;
    protected static final Font NAME_FONT = new Font("Courier", Font.PLAIN, 12);
    protected static final Font VALUE_FONT = new Font("Courier", Font.BOLD, 12);
    
    private static final int LABEL_FACTOR = 7;
    private static final String NAME_PREFIX = " ";
    private static final String NAME_SUFFIX = ":";
    protected static final String NUM_PREFIX = " ";
    protected static final String NUM_SUFFIX = "";
    private static final int EXTRA_NAME_CHARS = NAME_PREFIX.length()
    										  + NAME_SUFFIX.length();
    
    private final int NAME_WIDTH;
    protected final int NAME_LABEL_WIDTH;
    
    protected static final int VALUE_WIDTH = 10;
    protected static final int VALUE_LABEL_WIDTH = VALUE_WIDTH * LABEL_FACTOR;
    
    protected int LINE_WIDTH = 0;

	
	protected Parameter param;
	
	/**
	 * Sets up a new ParamLine given a Parameter and an integer width for the
	 * text of the Parameter label. This width should be at least equal to the
	 * number of characters in the Parameter's name + N_EXTRA_CHARS.
	 * @param param Parameter to be held by this ParamLine.
	 * @param nameWidth integer width.
	 */
	public ParamLine(Parameter param, int nameWidth)
	{
		this.param = param;
		
		NAME_WIDTH = nameWidth + EXTRA_NAME_CHARS;
		NAME_LABEL_WIDTH = NAME_WIDTH * LABEL_FACTOR;
		
		createParamLine();
	}
	
	protected abstract void createParamLine();
	
    /**
     * Creates a JLabel showing the name of this ParamLine's Parameter.
     * @return a JLabel displaying the Parameter's name.
     */
    protected JLabel createNameLabel()
    {
        JLabel label;
        String s = NAME_PREFIX + param.getName() + NAME_SUFFIX;
        label = new JLabel(Formatter.leftJustify(s, NAME_WIDTH));
        label.setFont(NAME_FONT);
        return label;
    }
    
    /**
     * Used by createParamLine() to create a JLabel displaying the current
     * value of this ParamLine's Parameter.
     * @return the JLabel.
     */
    protected JLabel createValueLabel()
    {
        JLabel label;
        String text = NUM_PREFIX + param.getValueString() + NUM_SUFFIX;
        String s = Formatter.formatString(text, VALUE_WIDTH);
        label = new JLabel(Formatter.leftJustify(s, VALUE_WIDTH));
        label.setFont(VALUE_FONT);
        return label;
    }
    
    /**
     * Gets the width of the ParamLine.
     * @return the int width.
     */
    protected int getLineWidth()
    {
    	return LINE_WIDTH;
    }
    
}