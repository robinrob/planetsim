package uk.co.mrrobinsmith.planetsim.sim;

import java.awt.*;
import java.lang.Math;

import uk.co.mrrobinsmith.planetsim.base.Canvas;
import uk.co.mrrobinsmith.planetsim.base.ColorParameter;
import uk.co.mrrobinsmith.planetsim.base.DoubleParameter;
/**
 * Body represents an astronomical object which moves under the force of
 * gravity from other external bodies.
 *
 * @author Robin Smith
 * @version 1 (16/11/2010)
 */
public class Body
{
	
    public static final double SCALE_FACTOR = 1000.0;
	
	private double xPos;
    private double yPos;
    private double xVel;
    private double yVel;
    private double accrMass;
    private double mass;
    private int diameter;
    private DoubleParameter planMass;
    private DoubleParameter gravConst; 
    private ColorParameter color;
    private Canvas canvas;
    private DataAnalyser data;
    private PlanetSim sim;
    private boolean isOffScreen = false;
    private boolean isDead = false;
         
	/**
	 * Creates a new Body given all the following parameters:
	 * @param xPos starting x-position.
	 * @param yPos starting y-position.
	 * @param xVel starting x-velocity.
	 * @param yVel starting y-velocity.
	 * @param mass mass of the Body.
	 * @param diameter diameter of the Body.
	 * @param color color of the Body.
	 * @param simBounds horizontal and vertical bounds of the simulation.
	 * @param canvas canvas for drawing the Body on.
	 * @param data DataAnalyser object to send data to.
	 */
    public Body(double xPos, double yPos, double xVel, double yVel,
                DoubleParameter planMass, DoubleParameter gravConst,
                ColorParameter color, Canvas canvas, DataAnalyser data,
                PlanetSim sim)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        mass = planMass.getValue();
        accrMass = 0.0;
        diameter = calculateDiameter();
        this.planMass = planMass;
        this.gravConst = gravConst;
        this.color = color;
        this.canvas = canvas;
        this.data = data;
        this.sim = sim;
        data.incBodiesOnScreen();
    }
    
    /**
     * Gets the Body's x-position.
     * @return the x-position.
     */
    public double getXPos()
    {
    	return xPos;
    }
    
    /**
     * Gets this Body's y-position.
     * @return the y-position.
     */
    public double getYPos()
    {
    	return yPos;
    }
    
    /**
     * Gets this Body's current x-velocity.
     * @return the x-velocity.
     */
    public double getXVel()
    {
    	return xVel;
    }
    
    /**
     * Gets this Body's current y-velocity.
     * @return the y-velocity.
     */
    public double getYVel()
    {
    	return yVel;
    }
    
    /**
     * Gets this Body's mass.
     * @return the mass.
     */
    public double getMass()
    {
    	return mass + accrMass;
    }
    
    /**
     * Gets the Diameter of this Body.
     * @return the diameter.
     */
    public int getDiameter()
    {
    	return diameter;
    }
    
    /**
     * Gets the Color of this Body.
     * @return the color.
     */
    public Color getColor()
    {
    	return color.getValue();
    }
    
    /**
     * Sets the x-position of this Body.
     * @param x the new x-position.
     */
    public void setXPos(double x)
    {
    	xPos = x;
    }
    
    /**
     * Sets the y-position of this Body.
     * @param y the new y-position.
     */
    public void setYPos(double y)
    {
    	yPos = y;
    }
    
    /**
     * Sets the x-velocity of this Body.
     * @param vel the new x-velocity.
     */
    public void setXVel(double vel)
    {
    	xVel = vel;
    }
    
    /**
     * Sets the y-velocity of this Body.
     * @param vel the new y-velocity.
     */
    public void setYVel(double vel)
    {
    	yVel = vel;
    }
    
    /**
     * Sets the boolean isDead field of this Body to the given boolean value.
     * @param bool true or false.
     */
    public void setDead(boolean bool)
    {
    	isDead = bool;
    }
        
    /**
     * Adds an extra mass onto this Body.
     * @param extraMass the extra mass.
     */
    public void addMass(double extraMass)
    {
    	accrMass += extraMass;
    }
    
    /**
     * Draws the Body at its current position onto its Canvas.
     */
    public void draw()
    {
    	diameter = calculateDiameter();
    	if (bodyInXBounds() && bodyInYBounds()) {
        	int d = diameter;
    		canvas.setForegroundColor(color.getValue());
        	canvas.fillCircle((int) (xPos / SCALE_FACTOR - d / 2),
        	                  (int) (yPos / SCALE_FACTOR - d / 2), d);
        	if (isOffScreen) {
        		isOffScreen = false;
        		data.decBodiesOffScreen();
        		data.incBodiesOnScreen();
        	}
    	}
    	else if (!isOffScreen){
    		isOffScreen = true;
    		data.incBodiesOffScreen();
    		data.decBodiesOnScreen();
    	}
    }

    /**
     * Erases this ball at its current position.
     */
    public void erase()
    {
    	if (!sim.trailsOn() && bodyInXBounds() && bodyInYBounds()) {
    		int d = diameter;
    		canvas.eraseCircle((int) (xPos / SCALE_FACTOR - d / 2),
    		                   (int) (yPos / SCALE_FACTOR - d / 2), d);
    	}
    }   
    
    /** 
     * Calculate the instantaneous net acceleration on the Body and its new
     * velocity after a time interval deltaT.
     */
    public void move(double deltaT)
    {   	
    	erase();
    	mass = planMass.getValue() + accrMass;
    	
    	double xSep;
    	double ySep;
    	double r;
        
    	Body[] bodies = sim.getBodies();
    	for (Body body : bodies) {
    		if ((body != null) && !(this.equals(body))) {
    			xSep = body.getXPos() - xPos;
    			ySep = body.getYPos() - yPos;
    			r = Math.sqrt(xSep * xSep + ySep * ySep);    			
			  	double G = gravConst.getValue();
			   	double m2 = body.getMass();
			   	xVel += G * (m2 / r * r) * (xSep / r) * deltaT;
			   	yVel += G * (m2 / r * r) * (ySep / r) * deltaT;
			   	xPos += (xVel * deltaT);
			   	yPos += (yVel * deltaT);
    			
			   	if (sim.collisionsOn()) {
			   		double minSep = (diameter + body.getDiameter()) / 4.0;
			   		if ((r / SCALE_FACTOR) < minSep) {
			   			body.erase();
			   			merge(body);
				   		if (isDead) {
	    					return;
				   		}
			   		}
			   	}
    		}
        }
    	
    	draw();
    }
    
    /**
     * This method is used by the move() method. It merges this Body with
     * another Body (body2), assigning to this Body the total mass of the two
     * bodies and also the mass-weighted average position. The body body2 is
     * then set to null.
     * @param body2 the Body to merge this Body with.
     */
    private void merge(Body body2)
    {
    	double m2 = body2.getMass();
    	double x2 = body2.getXPos();
    	double y2 = body2.getYPos();
    	double vX2 = body2.getXVel();
    	double vY2 = body2.getYVel();
    	double totalMass = mass + m2;
    	double avgXPos = (mass * xPos + m2 * x2) / (totalMass);
    	double avgYPos = (mass * yPos + m2 * y2) / (totalMass);
    	double avgXVel = (mass * xVel + m2 * vX2) / (totalMass);
    	double avgYVel = (mass * yVel + m2 * vY2) / (totalMass);
    	
    	if (m2 > mass) {
    		body2.addMass(mass);
    		body2.setXPos(avgXPos);
    		body2.setYPos(avgYPos);
    		body2.setXVel(avgXVel);
    		body2.setYVel(avgYVel);
    		isDead = true;
    		sim.removeBody(this);
    	}
    	else {
    		addMass(m2);
    		xPos = avgXPos;
    		yPos = avgYPos;
    		xVel = avgXVel;
    		yVel = avgYVel;
    		sim.removeBody(body2);
    	}
    }
    
    /**
     * Calculates the diameter of this Body according to its current mass.
     * @return the diameter.
     */
    private int calculateDiameter()
    {
    	int d = 2;
    	if (mass > 1.0) {
    		double upprBnd = 2.0;
    		int multiplier = 1;
    		while (mass > upprBnd) {
    			upprBnd *= 2;
    			multiplier++;
    		}
    		d = multiplier * 2;
    	}
    	return d;
    }
    
    /**
     * Checks whether the ball is within the simulation's horizontal bounds.
     * @return true or false.
     */
    private boolean bodyInXBounds()
    {
    	int x = (int) (xPos / SCALE_FACTOR);
    	if (x >= 0.0 && x <= sim.getWidth()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Checks whether the ball is within the simulation's vertical bounds.
     * @return true or false.
     */
    private boolean bodyInYBounds()
    {
    	int y = (int) (yPos / SCALE_FACTOR);
    	if (y >= 0.0 && y <= sim.getHeight())
    		return true;
    	else return false;
    }
    
    
    
}
