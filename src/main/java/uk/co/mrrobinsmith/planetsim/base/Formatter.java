package uk.co.mrrobinsmith.planetsim.base;

import java.io.Serializable;

/**
 * Abstract class with static methods to format strings.
 * 
 * @author Pablo Romero, extended by Robin Smith (2009.01.18)
 * @version 1.0
 */
public class Formatter implements Serializable
{
	private static final long serialVersionUID = 2447862773134764551L;

	/**
     * Right-justifies the String representation of the given int, given the
     * required length of the justified String, by inserting left spaces.
     * @param number the int to justify.
     * @param length the requied length of the justified int.
     * @return a String containing the right-justified int.
     */
    public static String rightIntJustify(int number, int length)
    {
        Integer intNumber = new Integer(number);
        String numberString = intNumber.toString();
        int spaces = length - numberString.length();
        for (int i = 0; i < spaces; i++) {
            numberString = " " + numberString;
        }
        return numberString;
    }
    
	/**
     * Left-justifies the String representation of the given int, given the
     * required length of the justified String, by inserting right spaces.
     * @param number the number to justify.
     * @param length the required length of the justified String.
     * @return a String containing the left-justified int.
     */
    public static String leftIntJustify(int number, int length)
    {
        Integer intNumber = new Integer(number);
        String numberString = intNumber.toString();
        int spaces = length - numberString.length();
        for (int i = 0; i < spaces; i++) {
            numberString = numberString + " ";
        }  
        return numberString;
    }

	/**
     * Right-justifies the given String, given the required length of the
     * justified String, by inserting left spaces.
     * @param string the String to justify.
     * @param length the required length of the justified String.
     * @return the right-justified String.
     */
    public static String rightJustify(String string, int length)
    {
        int spaces = length - string.length();
        for (int i = 0; i < spaces; i++) {
            string = " " + string;
        }    
        return string;
    }

	/**
     * Left-justifies the given String, given the required length of the
     * justified String, by inserting right spaces.
     * @param string the String to justify.
     * @param length the required length of the justified String.
     * @return the left-justified String.
     */
    public static String leftJustify(String string, int length)
    {
        int spaces = length - string.length();
        for (int i = 0; i < spaces; i++) {
            string = string + " ";
        }
        return string;
    }
    
	/**
     * Center-justifies the given String, given the required length of the
     * justified String, by inserting left and right spaces alternately.
     * @param string the String to justify.
     * @param length the required length of the justified String.
     * @return the center-justified String.
     */
    public static String centerJustify(String string, int length)
    {
        int spaces = length - string.length();
        for (int i = 0; i < spaces; i++) {
        	if ((i % 2) == 0) {
        		string = " " + string;
        	}
        	else {
        		string = string + " ";
        	}
        }
        return string;
    }
    
    /**
     * Shortens a string to the given length. If the input string contains a
     * number in scientific notation e.g. 1.234E5, the output string will
     * contain at least the E... part and then the digits before the E until the
     * whole string is of length n.
     * @param inString the input string.
     * @param n the number of characters in the returned string.
     * @return the shortened string.
     */
    public static String formatString(String inString, int n)
    {
    	char[] strArr = inString.toCharArray();
    	int l = strArr.length;
    	String sciSuffix = "";
    	int nDigits = n;
    	
    	for (int i = 1; (i < inString.length()) && (i < 4); ++i) {
    		if ((strArr[l - i] == 'E')) {
    			nDigits = n - i;
    			for (int j = (l - i); j < l; ++j) {
    				sciSuffix += strArr[j];
    			}
    			break;
    		}
    	}
    	
    	String outString = "";
    	for (int i = 0; i < nDigits; ++i) {
    		if (i < inString.length()) {
    			if (strArr[i] == 'E') {
    				break;
    			}
    			else {
    				outString += strArr[i];
    			}
    		}
    		else {
    			outString += ' ';
    		}
    	}
    	
    	return outString + sciSuffix;
    }
    
}

