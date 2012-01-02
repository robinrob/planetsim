package uk.co.mrrobinsmith.planetsim.base;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * FramePosition is an abstract class which provides some useful functions for
 * positioning JFrames on the screen.
 * 
 * @author Robin Smith
 * @version 1 (11/11/2010)
 */

public abstract class FramePosition {
	
	public static final int CENTER = 0;
	public static final int NORTH = 1;
	public static final int NORTH_EAST = 2;
	public static final int EAST = 3;
	public static final int SOUTH_EAST = 4;
	public static final int SOUTH = 5;
	public static final int SOUTH_WEST = 6;
	public static final int WEST = 7;
	public static final int NORTH_WEST = 8;

	/**
	 * Positions a JFrame on the screen using the specified integer position
	 * value, e.g. FramePosition.NORTH_WEST, FramePosition.NORTH_EAST etc.
	 * @param frame the JFrame to be position on the screen.
	 * @param position an integer value.
	 */
	public static void positionFrame(JFrame frame, int position)
	{
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		switch (position) {
		case CENTER:
			frame.setLocation((d.width - frame.getWidth()) / 2,
			                  (d.height - frame.getHeight()) / 2);
			break;
			
		case NORTH:
			frame.setLocation((d.width - frame.getWidth()) / 2, 0);
			break;
			
		case NORTH_EAST:
			frame.setLocation(d.width - frame.getWidth(), 0);
			break;
			
		case EAST:
			frame.setLocation(d.width - frame.getWidth(),
			                  (d.height - frame.getHeight())/ 2);
			break;
			
		case SOUTH_EAST:
			frame.setLocation(d.width - frame.getWidth(),
			                  d.height - frame.getHeight());
			break;
			
		case SOUTH:
			frame.setLocation((d.width - frame.getWidth()) / 2,
			                  d.height - frame.getHeight());
			break;
			
		case SOUTH_WEST:
			frame.setLocation(0, d.height - frame.getHeight());
			break;
		
		case WEST:
			frame.setLocation(0, (d.height - frame.getHeight()) / 2);
			break;
			
		case NORTH_WEST:
			frame.setLocation(0, 0);
			break;
		}
	}
	
	/**
	 * Positions the given JFrame on the screen relative to another JFrame,
	 * using the specified integer position value,
	 * e.g. FramePosition.NORTH_WEST, FramePosition.CENTER etc.
	 * @param frame1 the JFrame to be positioned on the screen.
	 * @param position an integer value.
	 * @param frame2 the other JFrame to which frame1 is aligned.
	 */
	public static void positionFrame(JFrame frame1, int position, JFrame frame2)
	{
		//Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension d = frame2.getSize();
		
		switch (position) {
		case CENTER:
			frame1.setLocation((d.width - frame1.getWidth()) / 2,
			                  (d.height - frame1.getHeight()) / 2);
			break;
			
		case NORTH:
			frame1.setLocation((d.width - frame1.getWidth()) / 2, 0);
			break;
			
		case NORTH_EAST:
			frame1.setLocation(d.width - frame1.getWidth(), 0);
			break;
			
		case EAST:
			frame1.setLocation(d.width - frame1.getWidth(),
			                  (d.height - frame1.getHeight())/ 2);
			break;
			
		case SOUTH_EAST:
			frame1.setLocation(d.width - frame1.getWidth(),
			                  d.height - frame1.getHeight());
			break;
			
		case SOUTH:
			frame1.setLocation((d.width - frame1.getWidth()) / 2,
			                  d.height - frame1.getHeight());
			break;
			
		case SOUTH_WEST:
			frame1.setLocation(0, d.height - frame1.getHeight());
			break;
		
		case WEST:
			frame1.setLocation(0, (d.height - frame1.getHeight()) / 2);
			break;
			
		case NORTH_WEST:
			frame1.setLocation(0, 0);
			break;
		}
	}
	
}