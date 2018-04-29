package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class RacingToolBar extends ToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String[] _songs = {"BowserCastleMarioKart",
			"Kirby",
			"MooMooFarmMarioKart",
			"MountWarioMarioKart",
			"SuperMarioWorldAthletic",
			"SweetSweetCanyonMarioKart",
			"ToadHarborMarioKart",
			"WarioGoldMineMarioKart",
			"YoshiCircuitMarioKart"};

	public RacingToolBar(MainPanel mainPanel, ActionListener imagesPanel, ReportsAreaPanel reportsArea, int steps) {
		super(mainPanel, imagesPanel, reportsArea, steps);
	}
	
	@Override
	protected void initGUI() {
		// Simulator
		runButton = new JButton();
		createGenericButton(runButton, "RUN", _mainPanel, "/icons/play.png", "Run");
			
		resetButton = new JButton();
		createGenericButton(resetButton, "RESET", _mainPanel, "/icons/reset.png", "Reset");
			
		stepsLabel = new JLabel("Steps: ");
		
		stepsSpinner = new JSpinner(new SpinnerNumberModel(_steps, 0, null, 1));
		stepsSpinner.setMinimumSize(new Dimension(50, 50));
		stepsSpinner.setPreferredSize(new Dimension(50, 50));
		stepsSpinner.setMaximumSize(new Dimension(50, 50));
			
		timeLabel = new JLabel(" Time: ");
		
		timeTextField = new JTextField();
		timeTextField.setEditable(false);
		timeTextField.setMinimumSize(new Dimension(50, 50));
		timeTextField.setPreferredSize(new Dimension(50, 50));
		timeTextField.setMaximumSize(new Dimension(50, 50));
		
		this.addSeparator();
		
		// Music
		playMusicButton = new JButton();
		createGenericButton(playMusicButton, "PLAY", _mainPanel, "/icons/play_music.png", "Play music");
				
		stopMusicButton = new JButton();
		createGenericButton(stopMusicButton, "STOP", _mainPanel, "/icons/stop_music.png", "Pause music");
				
		randomMusicButton = new JButton();
		createGenericButton(randomMusicButton, "RANDOM", _mainPanel, "/icons/random_music.png", "Random song");	
		
		playList = new JComboBox<String>(_songs);
		playList.setToolTipText("Select a song");
		playList.setSelectedIndex(2);
		playList.setActionCommand("PLAYLIST");
		playList.addActionListener(_mainPanel);
		playList.setMinimumSize(new Dimension(170, 50));
		playList.setPreferredSize(new Dimension(170, 50));
		playList.setMaximumSize(new Dimension(170, 50));
		
		// Exit
		quitButton = new JButton();
		createGenericButton(quitButton, "QUIT", _mainPanel, "/icons/exit.png", "Exit");
		
		authors = new JLabel("Developed by Álvaro López & Carlos Bilbao");
		
		addComponents();
		
		setMinimumSize(new Dimension(1000, 50));
		setPreferredSize(new Dimension(1000, 50));
		setMaximumSize(new Dimension(1000, 50));
	}
	
	@Override
	protected void addComponents() {
		this.add(runButton);
		this.add(resetButton);
		this.add(stepsLabel);
		this.add(stepsSpinner);
		this.add(timeLabel);
		this.add(timeTextField);
		this.addSeparator();
		this.add(playMusicButton);
		this.add(stopMusicButton);
		this.add(randomMusicButton);
		this.add(playList);
		this.addSeparator();
		this.add(quitButton);
		this.addSeparator();
		this.addSeparator();
		this.addSeparator();
		this.add(authors);
	}

}
