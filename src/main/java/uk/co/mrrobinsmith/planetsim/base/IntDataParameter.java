package uk.co.mrrobinsmith.planetsim.base;

/**
 * IntDataParameter is to be used to store a simulation parameter which is
 * dynamic during running of the simulation. It can be displayed in a
 * DataParamPanel by setting its parent DataParamLine using the setParamLine()
 * method.
 * 
 * @author Robin Smith
 * @version 1 (12/11/2010)
 */

public class IntDataParameter extends IntParameter
{

    private DataParamLine paramLine = null;
    
    /**
     * Creates a new IntDataParameter, given a name, initial value and a boolean
     * value which should always be false.
     * @param name name of the parameter.
     * @param value initial value of the parameter.
     * @param isAdjustable should always be false for IntDataParameters.
     */
    public IntDataParameter(String name, int value, boolean isAdjustable)
    {
        super(name, value, isAdjustable);
        this.value = value;
    }
    
	/**
	 * Sets this IntDataParameter's parent DataParamLine for displaying the
	 * parameter in a GUI.
	 * @param paramLine the DataParamLine displaying this IntDataParameter.
	 */
    public void setParamLine(DataParamLine paramLine)
    {
        this.paramLine = paramLine;
    }
    
    /**
     * Increments the value of this IntDataParameter.
     */
    public void incValue()
    {
    	value++;
    	if (!(paramLine == null)) {
    		paramLine.updateDisplay();
    	}
    }
    
	/**
	 * Decrements the value of this IntDataParameter.
	 */
    public void decValue()
    {
        value--;
        if (!(paramLine == null)) {
            paramLine.updateDisplay();
        }
    }
    
}
