package uk.co.mrrobinsmith.planetsim.base;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * SetupParamLine extends ParamLine to provide functionality to display a
 * Parameter's name and a text field to set its value.
 * 
 * @author Robin Smith
 * @version 1 (11/11/2010)
 */

public class SetupParamLine extends ParamLine
{
	private static final long serialVersionUID = 6914224192903329395L;
	
	private static final int FIELD_WIDTH = 120;
    
	private JTextField textField;
	private JComboBox colorBox = null;
	
	/**
	 * Creates a new SetupParamLine given a Parameter and an integer width for
	 * the text of the Parameter's name label.
	 * @param param Parameter held by this ParamLine.
	 * @param nameWidth int width for Parameter name label.
	 */
	public SetupParamLine(Parameter param, int nameWidth)
	{
		super(param, nameWidth);
		
		LINE_WIDTH = NAME_LABEL_WIDTH + FIELD_WIDTH;
		this.setPreferredSize(new Dimension(LINE_WIDTH, LINE_HEIGHT));
		
		createParamLine();
	}
	
	/**
	 * Constructs the SetupParamLine.
	 */
	protected void createParamLine()
	{
        this.setLayout(new BorderLayout());
        this.add(createNameLabel(), BorderLayout.WEST);
        
        if (param.getType() == Parameter.COLOR) {
        	colorBox = createColorBox();
        	this.add(colorBox, BorderLayout.CENTER);
        }
        else {
        	textField = createParamField();
        	this.add(textField, BorderLayout.CENTER);
        }
	}
	
    /**
     * Creates a JTextField for this SetupParamLine's Parameter for obtaining
     * user input to set its value.
     * @return a JTextField displaying the current value of the Parameter.
     */
    private JTextField createParamField()
    {
    	JTextField field = new JTextField(FIELD_WIDTH);
    	field.setText(param.getValueString());
    	field.setPreferredSize(new Dimension(FIELD_WIDTH, LINE_HEIGHT));
    	return field;
    }
    
    /**
     * Creates a JComboBox filled with strings representing color names.
     */
    private JComboBox createColorBox()
    {
    	JComboBox comboBox = new JComboBox();
    	for (String colorName : ((ColorParameter) param).getPossibleColors()) {
    		comboBox.addItem(colorName);
    	}
    	return comboBox;
    }
    
    /**
     * Gets the String from this SetupParamLine's text field.
     * @return the String from this SetupParamLine's text field.
     */
    public String getFieldString()
    {
    	if (!(param.getType() == Parameter.COLOR)) {
    		return textField.getText().trim();
    	}
    	else {
    		return ((String) colorBox.getSelectedItem()).trim();
    	}
    }
    
    /**
     * Updates the text of the text field of this SetupParamLine.
     */
    public void updateField()
    {
    	textField.setText(param.getValueString());
    }

}
