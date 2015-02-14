package mazeEmulator;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import mazeADT.Maze;

public class MainWindow extends JFrame implements ActionListener
{

	private JButton debugViewButton;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;

	private MazePanel myPanel;

	private Simulator theSimulator;

	protected MainWindow(int heightParam, int widthParam, Simulator simulatorParam, Robot robotParam, Maze mazeParam)
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(widthParam,heightParam);
		//this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);//1100-600
		this.setLayout(null);

		theSimulator = simulatorParam;

		debugViewButton = new JButton("Debug view");
		this.getContentPane().add(debugViewButton);
		debugViewButton.setBounds(10,10,100,30);
		debugViewButton.addActionListener(this);


		myPanel = new MazePanel(simulatorParam, robotParam, mazeParam);
		this.getContentPane().add(myPanel);
		myPanel.setBounds(10,40,myPanel.getWidth(),myPanel.getHeight());





		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==debugViewButton){
			theSimulator.switchDebugView();
		}		
	}
	
	protected void updateGUI()
	{
		getContentPane().repaint();
	}
}