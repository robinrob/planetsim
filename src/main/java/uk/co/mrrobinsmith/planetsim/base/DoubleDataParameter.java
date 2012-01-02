package uk.co.mrrobinsmith.planetsim.base;

/**
 * DoubleDataParameter extends DataParameter to implement functionality to
 * display a DoubleParameter within a GUI. DoubleDataParameter contains a
 * DataParamLine field which can be set using the setParamLine() method. The
 * DataParamLine is used to display and update the parameter's value dynamically
 * during running of the simulation using the DoubleDataParameter.
 * 
 * @author Robin Smith
 * @version 1 (03/11/2010)
 */

public class DoubleDataParameter extends DoubleParameter
{
	private DataParamLine paramLine = null;
	
	/**
	 * Creates a DoubleDataParameter given a name, value and a boolean value to
	 * indicate whether or not the parameter is adjustable during running of
	 * the simulation using it.
	 * @param name the name for the parameter.
	 * @param value the value.
	 * @param isAdjustable true or false.
	 */
	public DoubleDataParameter(String name, double value, boolean isAdjustable)
	{
		super(name, value, isAdjustable);
	}
	
	/**
	 * Sets this DoubleDataParameter's DataParamLine.
	 * @param paramLine the DataParamLine using this DoubleDataParameter.
	 */
    public void setParamLine(DataParamLine paramLine)
    {
        this.paramLine = paramLine;
    }
	
    /**
     * Increments the value of this DoubleDataParameter.
     */
	public void incValue()
	{
		value += 0.1;
		paramLine.updateDisplay();
	}
	
	/**
	 * Decrements the value of this DoubleDataParameter.
	 */
	public void decValue()
	{
		value += 0.1;
		paramLine.updateDisplay();
	}
	
}
