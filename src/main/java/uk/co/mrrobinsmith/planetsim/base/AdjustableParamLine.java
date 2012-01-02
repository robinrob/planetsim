package uk.co.mrrobinsmith.planetsim.base;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * AdjustableParamLine extends ParamLine to provide functionality for displaying
 * a Parameter's name, its value and either: a label indicating the Parameter's
 * value is fixed during simulation run-time; a pair of buttons for increasing
 * and decreasing the Parameter's value.
 * 
 * @author Robin Smith
 * @version 1 (11/11/2010)
 */

public class AdjustableParamLine extends ParamLine
{
	private static final long serialVersionUID = 6349796769436490639L;
	private static final int BUTTON_WIDTH = 50;
	
    private static final String INFO_TEXT = "fixed";
    private static final int INFO_WIDTH = 14;
	
    /**
     * Creates a new AdjustableParamLine given a Parameter and a width for the
     * text of the Parameter's name label. This width should be at least equal
     * to the number of characters in the Parameter's name + N_EXTRA_CHARS,
     * where N_EXTRA_CHARS is defined in the Parameter class.
	 * @param param Parameter to be held by this ParamLine.
	 * @param nameWidth integer width for the Parameter's name label.
     */
	public AdjustableParamLine(Parameter param, int nameWidth)
	{
		super(param, nameWidth);
		
		LINE_WIDTH = NAME_LABEL_WIDTH + VALUE_LABEL_WIDTH + 2 * BUTTON_WIDTH;
		this.setPreferredSize(new Dimension(LINE_WIDTH, LINE_HEIGHT));
		
		createParamLine();
	}
	
	/**
	 * Constructs the AdjustableParamLine; used by the constructor.
	 */
	protected void createParamLine()
	{
		this.setLayout(new BorderLayout());
		
		JPanel labelPanel = new JPanel();
    	labelPanel.setLayout(new BorderLayout());
    	labelPanel.add(createNameLabel(), BorderLayout.WEST);
        
    	JLabel valueLabel = createValueLabel();
    	labelPanel.add(valueLabel, BorderLayout.EAST);
    	this.add(labelPanel, BorderLayout.WEST);
            
    	this.add(createActionPanel(valueLabel), BorderLayout.EAST);
	}
	
	/**
	 * Creates the 'action' JPanel for this AdjustableParamLine's Parameter.
	 * For a non-adjustable parameter the 'action' panel contains a label
	 * indicating that this parameter's value is fixed. For an adjustable
	 * parameter the 'action' panel contains buttons for adjusting its value.
	 * @return the JPanel.
	 */
	private JPanel createActionPanel(JLabel valueLabel)
    {
    	JPanel actionPanel = new JPanel();
    	actionPanel.setLayout(new GridLayout(1, 0));
    	
    	if (param.isAdjustable()) {
    		actionPanel.add(createIncrementButton(valueLabel));
            actionPanel.add(createDecrementButton(valueLabel));
           }
        else {
        	JLabel label;
        	label = new JLabel(Formatter.centerJustify(INFO_TEXT, INFO_WIDTH));
        	label.setFont(NAME_FONT);
           	actionPanel.add(label);
        }
    	
    	return actionPanel;
    }
       
	/**
	 * Used by createActionPanel() to create the increment button.
	 * @param valueLabel the JLabel showing the Parameter's value which can be
	 * incremented using this button.
	 * @return the JButton.
	 */
	private JButton createIncrementButton(JLabel valueLabel)
	{
		JButton button = new JButton("+");
        //button.setPreferredSize(new Dimension(BUTTON_WIDTH, LINE_HEIGHT));
        final Parameter p = param;
        final JLabel l = valueLabel;
        button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	p.incValue();
        	String text = NUM_PREFIX + param.getValueString() + NUM_SUFFIX;
        	l.setText(Formatter.formatString(text, VALUE_WIDTH));
        	}
        });
        return button;
	}
	
	/**
	 * Used by createActionPanel() to create the decrement button.
	 * @param valueLabel the JLabel showing the Parameter's value which can be
	 * decremented using this button.
	 * @return the JButton.
	 */
	private JButton createDecrementButton(JLabel valueLabel)
	{
        JButton button = new JButton("-");
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, LINE_HEIGHT));
        final Parameter p = param;
        final JLabel l = valueLabel;
        button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	p.decValue();
        	String text = NUM_PREFIX + param.getValueString() + NUM_SUFFIX;
        	l.setText(Formatter.formatString(text, VALUE_WIDTH));
        	}
        });
        return button;
	}

}
