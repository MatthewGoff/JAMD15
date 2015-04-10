package mazeGUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import mazeADT.Maze;
import mazeEmulator.Environment;

public class MainWindow extends JFrame implements ActionListener
{

	private JButton debugViewButton;
	private JButton generateMazeButton;
	private JButton runRobotButton;

	private MazePanel myPanel;

	private Environment theEnvironment;

	public MainWindow(int heightParam, int widthParam, Environment environmentParam, Maze mazeParam)
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(widthParam,heightParam);
		//this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);//Fullscreen
		this.setLayout(null);

		theEnvironment = environmentParam;

		debugViewButton = new JButton("Debug view");
		this.getContentPane().add(debugViewButton);
		debugViewButton.setBounds(10,10,100,30);
		debugViewButton.addActionListener(this);

		generateMazeButton = new JButton("Generate maze");
		this.getContentPane().add(generateMazeButton);
		generateMazeButton.setBounds(120,10,150,30);
		generateMazeButton.addActionListener(this);

		runRobotButton = new JButton("Run Robot");
		this.getContentPane().add(runRobotButton);
		runRobotButton.setBounds(270,10,150,30);
		runRobotButton.addActionListener(this);

		myPanel = new MazePanel(environmentParam, mazeParam);
		this.getContentPane().add(myPanel);
		myPanel.setBounds(10,40,myPanel.getWidth(),myPanel.getHeight());

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==debugViewButton)
		{
			theEnvironment.switchDebugView();
		}
		else if (e.getSource()==generateMazeButton)
		{
			theEnvironment.generateMaze();
		}
		else if (e.getSource()==runRobotButton)
		{
			theEnvironment.runRobot();
		}
	}
	
	public void updateGUI()
	{
		System.out.println("UPDATING");
		getContentPane().repaint();
		myPanel.updateForMove();
	}
}