package uk.co.mrrobinsmith.planetsim.sim;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import uk.co.mrrobinsmith.planetsim.base.*;
import uk.co.mrrobinsmith.planetsim.base.Canvas;

/**
 * PlanetSimGUI is the GUI and main class for the PlanetSim project.
 *
 * @author Robin Smith
 * @version 1 (17/11/2010)
 */

public class PlanetSimGUI implements SimGUI
{
	
	//program details
    private static final String VERSION = "Version 2";
    private static final String ICON_IMAGE = "icons/icon.jpg";
    private static final String DATE = "17/11/2010";
    private static final String AUTHOR = "Robin Smith";
    
    //canvas display parameters
    private final Color BG_COLOR = Color.black;
    private final Color TEXT_COLOR = Color.white;
	
    //GUI components
    private static final int SHORTCUT_MASK = 
    	Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    private JFrame mainFrame;
    private Container contentPane;
    private JFrame paramFrame;
    private JFrame dataFrame;
    private Canvas canvas; //drawing canvas on which the simulation is drawn
    private JButton runButton;
    private JButton stopButton;
    private JButton pauseButton;
    private JButton resumeButton;
    private JButton resetButton;
    private JButton addRogueButton;
    private JPanel buttonPanel;
    
    //simulation objects
    private PlanetSim sim;
    private Thread simThread; //separate thread for the simulation
         
    /**
     * Creates a new PlanetSimGUI and a PlanetSim.
     */
    public PlanetSimGUI()
    {
        sim = new PlanetSim(this);
        makeSimButtons();
    	makeMainFrame();
    	makeParamFrame();
    	makeDataFrame();
    	showSetupView();
    }
    
    /**
     * Gets the drawing canvas of the GUI.
     * @return the GUI's Canvas
     */
    public Canvas getCanvas()
    {
    	return canvas;
    }
    
    /**
     * Pauses the GUI thread for a specified number of milliseconds.
     * @param time an integer number of milliseconds
     */
    public void wait (int time)
    {
    	canvas.wait(time);
    }
    
    /**
     * Obtains an image with the relative path 'filename'.
     * @param filename the relative path of the image file
     * @return a BufferedImage object if file 'filename' exists, null otherwise
     */
    private BufferedImage loadImage(String filename)
    {
        BufferedImage returnImage = null;
        File file = new File(filename);
        
        if (file.exists()) {
            try {
                returnImage = ImageIO.read(file);
            } 
            catch (IOException e) {
            }

        }
        return returnImage;
    }
    
    /**
     * Makes the main GUI window.
     */
    private void makeMainFrame()
    {
    	mainFrame = new JFrame("Planet Simulation");
    	mainFrame.setResizable(false);
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	BufferedImage iconImage = loadImage(ICON_IMAGE);
        mainFrame.setIconImage(iconImage);
    	
    	makeMenuBar();
    	
        contentPane = mainFrame.getContentPane();
    	mainFrame.setLayout(new BorderLayout());
    }
    
    /**
     * Makes the menu bar and adds it to the main GUI window.
     */
    private void makeMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);
        
        makeFileMenu();
        makeViewMenu();
        makeHelpMenu();
    }
    
    /**
     * Makes the 'File' menu for the main frame's menubar.
     */
    private void makeFileMenu()
    {
    	JMenuBar menuBar = mainFrame.getJMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem item = new JMenuItem("Quit");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                                                   SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { System.exit(0); }
        	});
        menu.add(item);
    }
    
    /**
     * Makes the 'View' menu for the main frame's menubar.
     */
    private void makeViewMenu()
    {
    	JMenuBar menuBar = mainFrame.getJMenuBar();
        JMenu menu = new JMenu("View");
        menuBar.add(menu);
      
        JMenuItem item = new JMenuItem("Setup");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                                   SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { resetSim(); }
        	});
        menu.add(item);
        
        item = new JMenuItem("Parameters");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                                                        SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        			showParamFrame();
        		}
        	});
        menu.add(item);
        
        item = new JMenuItem("Data");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
                                                        SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { showDataFrame(); }
        	});
        menu.add(item);
    }
    
    /**
     * Makes the 'Help' menu for the main frame's menubar.
     */
    private void makeHelpMenu()
    {
    	JMenuBar menuBar = mainFrame.getJMenuBar();
        JMenu menu = new JMenu("Help");
        menuBar.add(menu);
        
        JMenuItem item = new JMenuItem("About PlanetSim ...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { showAbout(); }
            });
        menu.add(item);
    }
    
    /**
     * Makes all the buttons of the simulation display.
     */
    private void makeSimButtons()
    {
    	buttonPanel = new JPanel();
    	buttonPanel.setLayout(new GridLayout(1, 0));
    	
    	runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { runSim(); }
        	});
        
    	stopButton = new JButton("Stop");
    	stopButton.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { stopSim(); }
         	});
    	
    	pauseButton = new JButton("Pause");
    	pauseButton.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { pauseSim(); }
         	});
    	
    	resumeButton = new JButton("Resume");
    	resumeButton.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { resumeSim(); }
         	});
    	
    	resetButton = new JButton("Reset");
    	resetButton.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { resetSim(); }
         	});
    	
    	addRogueButton = new JButton("Add rogue planet");
    	addRogueButton.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { sim.addRogue(); }
         	});
    }
    
    /**
     * Makes the window used for displaying the simulation parameters.
     */
    private void makeParamFrame()
    {
    	paramFrame = new JFrame("Parameters");
    	//paramFrame.setResizable(false);
    	BufferedImage iconImage = loadImage(ICON_IMAGE);
        paramFrame.setIconImage(iconImage);
    }
    
    /**
     * Makes the window used for displaying the simulation data.
     */
    private void makeDataFrame()
    {
    	dataFrame = new JFrame("Data");
    	dataFrame.setResizable(false);
    	BufferedImage iconImage = loadImage(ICON_IMAGE);
        dataFrame.setIconImage(iconImage);
    }
        
    /**
     * Displays an 'About' window for the program.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(mainFrame, 
                    "PlanetSim\n" + VERSION + " (" + DATE + ")\n" + AUTHOR,
                    "About PlanetSim", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Displays the window showing the simulation parameters.
     */
    private void showParamFrame()
    {
    	paramFrame.getContentPane().removeAll();
        paramFrame.add(new AdjustableParamPanel(sim.getSetupParams()));
        paramFrame.pack();
        paramFrame.setVisible(true);
        FramePosition.positionFrame(paramFrame, FramePosition.WEST, mainFrame);
    }
    
    /**
     * Displays the window showing the simulation data.
     */
    private void showDataFrame()
    {
    	DataParamPanel dataPanel = new DataParamPanel(sim.getDataParams());
    	dataFrame.getContentPane().removeAll();
    	dataFrame.add(dataPanel);
    	dataFrame.pack();
    	dataFrame.setVisible(true);
        FramePosition.positionFrame(dataFrame, FramePosition.CENTER);
    }
    
    /**
     * Displays the simulation setup window.
     */
    private void showSetupView()
    {
    	mainFrame.setVisible(false);
    	paramFrame.setVisible(false);
    	dataFrame.setVisible(false);
    	SetupParamPanel setupPanel = new SetupParamPanel(sim.getSetupParams(),
    	                                                 this);
    	contentPane.removeAll();
    	contentPane.add(setupPanel);
        mainFrame.pack();
        FramePosition.positionFrame(mainFrame, FramePosition.CENTER);
        mainFrame.setTitle("PlanetSim setup");
        mainFrame.setVisible(true);
    }
    
    /**
     * Shows the simulation view ready for the simulation to begin.
     */
    public void showSimView()
    {
    	int simWidth = sim.getWidth();
    	int simHeight = sim.getHeight();
    	canvas = new Canvas(simWidth, simHeight, BG_COLOR);
    	mainFrame.setVisible(false);
    	contentPane.removeAll();
    	contentPane.setLayout(new BorderLayout());
    	contentPane.add(canvas.getCanvasPane(), BorderLayout.CENTER);
    	canvas.setVisible(false);
    	buttonPanel.setPreferredSize(new Dimension(simWidth, 30));
        buttonPanel.removeAll();
        buttonPanel.add(runButton);
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        mainFrame.pack();
        
        drawStartMessage();
        sim.createBodies();
        sim.drawBodies();

        FramePosition.positionFrame(mainFrame, FramePosition.EAST);
        mainFrame.setTitle("PlanetSim");
        mainFrame.setVisible(true);
        showParamFrame();
        showDataFrame();
    }
    
    /**
     * Draws a start message on the simulation canvas.
     */
    private void drawStartMessage()
    {
    	canvas.setForegroundColor(TEXT_COLOR);
    	
    	int width = 26;
    	String message = Formatter.centerJustify("PlanetSim " + VERSION,
    	                                         width);
    	canvas.drawString(message,
    	                  (int) (sim.getWidth() * 0.415),
    	                  (int) (sim.getHeight() * 0.2));
    	message = Formatter.centerJustify("Press 'Run' to begin", width);
    	canvas.drawString(message,
    	                  (int) (sim.getWidth() * 0.418),
    	                  (int) (sim.getHeight() * 0.23));
    }
    
    /**
     * Erases the start message from the simulation canvas.
     */
    private void eraseStartMessage()
    {
    	 Rectangle fillArea = new Rectangle(
    	                                    (int) (sim.getWidth() * 0.385),
    	                                    (int) (sim.getHeight() * 0.17),
    	                                    200,
    	                                    (int) (sim.getHeight() * 0.075));
         canvas.setForegroundColor(BG_COLOR);
         canvas.fill(fillArea);
    }
    
    /**
     * Runs the simulation.
     */
    private void runSim()
    {
    	buttonPanel.removeAll();
        buttonPanel.add(pauseButton);
        buttonPanel.add(addRogueButton);
        buttonPanel.add(stopButton);
    	mainFrame.pack();
    	
    	eraseStartMessage();
    	mainFrame.repaint();
    	
    	simThread = new Thread(sim);
    	simThread.start();
    }
    
    /**
     * Stops the simulation.
     */
    private void stopSim()
    {
    	sim.stop();
    	buttonPanel.removeAll();
    	buttonPanel.add(resetButton);
    	mainFrame.pack();
    	mainFrame.repaint();
    }
    
    /**
     * Pauses the simulation.
     */
    private void pauseSim()
    {
    	sim.pause();
    	buttonPanel.removeAll();
    	buttonPanel.add(resumeButton);
    	buttonPanel.add(addRogueButton);
    	buttonPanel.add(stopButton);
    	mainFrame.pack();
    	mainFrame.repaint();
    }
    
    /**
     * Resumes running of the after a pause simulation.
     */
    private void resumeSim()
    {
    	simThread = new Thread(sim);
    	simThread.start();
    	buttonPanel.removeAll();
    	buttonPanel.add(pauseButton);
    	buttonPanel.add(addRogueButton);
    	buttonPanel.add(stopButton);
    	mainFrame.pack();
    	mainFrame.repaint();
    }
    
    /**
     * Resets the GUI to the setup window.
     */
    public void resetSim()
    {
    	if (sim.isRunning()) {
    		sim.stop();
    	}
    	sim.resetData();
    	showSetupView();
    }
    
    /**
     * Makes changes to the GUI relevant to the simulation finishing.
     */
    public void simFinished()
    {
    	buttonPanel.removeAll();
    	buttonPanel.add(resetButton);
    	mainFrame.pack();
    }
    
    /**
     * Main method for the PlanetSim project.
     * @param args
     */
    public static void main(String[] args)
    {
    	new PlanetSimGUI();
    }
}
    
    
    
   
