package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Controller;
import model.Observable;
import model.RacingSimulatorObserver;
import model.TrafficSimulator;
import music.Music;

public class RacingPanel extends MainPanel implements Observable<RacingSimulatorObserver>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String[] mario_songs = {"BowserCastleMarioKart.wav",
			"Kirby.wav",
			"MooMooFarmMarioKart.wav",
			"MountWarioMarioKart.wav",
			"SuperMarioWorldAthletic.wav",
			"SweetSweetCanyonMarioKart.wav",
			"ToadHarborMarioKart.wav",
			"WarioGoldMineMarioKart.wav",
			"YoshiCircuitMarioKart.wav"};
	
	private static String[] mario_characters = {"mario",
			"bowser",
			"donkey",
			"luigi",
			"peach",
			"koopa",
			"yoshi",
			"toad"};
	
	private static String[] mario_circuits = {"Mushroom",
			"Flower",
			"Star",
			"Special",
			"Shell",
			"Banana",
			"Leaf",
			"Lightning"};
	
	private List<RacingSimulatorObserver> _observers;
	
	private Music _music;
	private boolean playingMusic;
	private CharacterChooserPanel _characterChooserPanel;
	private ImagesPanel _imagesPanel;
	private CupChooserPanel _circuitChooserPanel;
	private SelectedCharactersTable _selectedCharactersTable;
	private SelectedCupTable _selectedCupTable;

	public RacingPanel(TrafficSimulator model, String inFile, Controller control, int steps) throws IOException {
		super(model, inFile, control, steps);
		playingMusic = false;
		this.setMinimumSize(new Dimension(1000, 1000));
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setMaximumSize(new Dimension(1000, 1000));
		this.setResizable(false);
	}
	
	@Override
	protected void initGUI() throws IOException {
		_observers = new ArrayList<>();
		
		this.setTitle("Racing Simulator");
		
		createMainPanel();
		this.setContentPane(_mainPanel);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/welcome.gif"));
		
		//Music
		_music = new Music("src/music/" + "start_simulator.wav");
		_music.play();
		JOptionPane.showMessageDialog(this, "Welcome to the simulator!\nWe hope you have much fun!", "Welcome", JOptionPane.INFORMATION_MESSAGE, icon);
		_music.stop();
		_music = null;
		_music = new Music("src/music/" + mario_songs[2]);
	}

	@Override
	protected void createMainPanel() throws IOException {
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		createTopPanel();
		
		createDownPanel();
		
		createToolBar();
		
		_mainPanel.add(_toolBar);
		
		_mainPanel.add(_topPanel);
		
		_mainPanel.add(_downPanel);
	}
	
	@Override
	protected void createTopPanel() throws IOException {
		_topPanel = new JPanel();
		_topPanel.setMinimumSize(new Dimension(1000, 150));
		_topPanel.setPreferredSize(new Dimension(1000, 150));
		_topPanel.setMaximumSize(new Dimension(1000, 150));
		_topPanel.setLayout(new BoxLayout(_topPanel, BoxLayout.X_AXIS));
		
		createCharacterChooserPanel();
		createCircuitChooserPanel();
		createImagesPanel();
		_characterChooserPanel.initGUI();
		_circuitChooserPanel.initGUI();
	
		_topPanel.add(_characterChooserPanel);
		_topPanel.add(_imagesPanel);
		_topPanel.add(_circuitChooserPanel);		
	}
	
	protected void createCharacterChooserPanel() {
		_characterChooserPanel = new CharacterChooserPanel(mario_characters);
		_characterChooserPanel.setMinimumSize(new Dimension(300, 150));
		_characterChooserPanel.setPreferredSize(new Dimension(300, 150));
		_characterChooserPanel.setMaximumSize(new Dimension(300, 150));
	}
	
	protected void createImagesPanel() {
		_imagesPanel = new ImagesPanel(this, "/images/mariokart.png", _model, _characterChooserPanel);
		_imagesPanel.setMinimumSize(new Dimension(400, 150));
		_imagesPanel.setPreferredSize(new Dimension(400, 150));
		_imagesPanel.setMaximumSize(new Dimension(400, 150));
		_characterChooserPanel.setImagesPanel(_imagesPanel);
		_circuitChooserPanel.setImagesPanel(_imagesPanel);
	}
	
	protected void createCircuitChooserPanel() {
		_circuitChooserPanel = new CupChooserPanel(mario_circuits);
		_circuitChooserPanel.setMinimumSize(new Dimension(300, 150));
		_circuitChooserPanel.setPreferredSize(new Dimension(300, 150));
		_circuitChooserPanel.setMaximumSize(new Dimension(300, 150));
	}
	
	@Override
	protected void createDownLeftPanel() {
		_downLeftPanel = new JPanel();
		_downLeftPanel.setLayout(new BoxLayout(_downLeftPanel, BoxLayout.Y_AXIS));
		
		createSelectedCharactersTable();
		_downLeftPanel.add(_selectedCharactersTable);
		
		createSelectedCupTable();
		_downLeftPanel.add(_selectedCupTable);
	}
	
	protected void createSelectedCharactersTable() {
		_selectedCharactersTable = new SelectedCharactersTable();
		addObserver(_selectedCharactersTable);
		_selectedCharactersTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Pilots"));
	}
	
	protected void createSelectedCupTable() {
		_selectedCupTable = new SelectedCupTable();
		addObserver(_selectedCupTable);
		_selectedCupTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Cup: "));
	}
	
	@Override
	protected void createToolBar() {
		_toolBar = new RacingToolBar(this, _imagesPanel, _reportsArea, _steps);
		_model.addObserver(_toolBar);
	}
	
	@Override
	protected void createRoadMapGraph() {
		_roadmapGraph = new RacingRoadMapGraph();
		_model.addObserver(_roadmapGraph);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		switch (str){
		case "RESET":
			notifyReset();
			_control.reset();
			_imagesPanel.reset();
			_circuitChooserPanel.reset();
			_selectedCupTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Cup: "));
			break;
		case "RUN":
			boolean begin = true;
			if(_model.getTime() == 0)
				begin = _imagesPanel.checkIn();
			if(begin) {
				try {
					if(_model.getTime() == 0) {
						if(playingMusic)
							_music.stop();
						Music temp = _music;
						_music = new Music("src/music/start_race.wav");
						_music.play();
						ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/lakitu.gif"));
						JOptionPane.showMessageDialog(this, "READY\n\nSET\n\nGO!", "The race is starting!", JOptionPane.INFORMATION_MESSAGE, icon);
						_music.stop();
						_music = null;
						_music = temp;
						if(playingMusic)
							_music.loop();
					}
					_control.run(_toolBar.getTime());
					if(_model.getTotalVehicles() == _model.getArrivedVehicles()) {
						if(playingMusic)
							_music.stop();
						Music temp = _music;
						_music = new Music("src/music/CourseClear.wav");
						_music.play();
						ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/finish.gif"));
						JOptionPane.showMessageDialog(this, "The race has finished!", "CONGRATULATIONS", JOptionPane.INFORMATION_MESSAGE, icon);
						_music.stop();
						_music = null;
						_music = temp;
						if(playingMusic)
							_music.loop();
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			break;
		case "QUIT":
			System.exit(0);
			break;
		case "PLAY":
			_music.loop();
			playingMusic = true;
			break;
		case "STOP":
			_music.stop();
			playingMusic = false;
			break;
		case "RANDOM":
			_music.stop();
			Random rnd = new Random();
			int selected = rnd.nextInt(mario_songs.length);
			_music = null;
			_music = new Music("src/music/" + mario_songs[selected]);
			_toolBar.getComboBox().setSelectedIndex(selected);
			_music.loop();
			playingMusic = true;
			break;
		case "PLAYLIST":
			@SuppressWarnings("unchecked") JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
			_music.stop();
			_music = null;
			_music = new Music("src/music/" + (String)comboBox.getSelectedItem() + ".wav");
			_music.loop();
			playingMusic = true;
			break;
		case "REDIRECT":
			JCheckBoxMenuItem redirect = (JCheckBoxMenuItem)e.getSource();
			if(redirect.isSelected())
				_model.setOutputStream(_outputStream);
			else
				_model.setOutputStream(null);
			break;
		default:
			break;
		}
	}

	public void addObserver(RacingSimulatorObserver obs) {
		if(obs != null && !_observers.contains(obs)) {
			_observers.add(obs);
			notifyRegistered(obs);
		}
	}
	
	public void removeObserver(RacingSimulatorObserver obs) {
		if(obs != null && _observers.contains(obs))
			_observers.remove(obs);
	}
	
	private void notifyRegistered(RacingSimulatorObserver obs) {
		obs.registered(_characterChooserPanel, _imagesPanel, _circuitChooserPanel);
	}
	
	public void notifyCupSelected() {
		for(RacingSimulatorObserver observer : _observers)
			observer.cupSelected();
	}

	public void notifyCharacterAdded() {
		for(RacingSimulatorObserver observer : _observers)
			observer.characterAdded();
	}
	
	private void notifyReset() {
		for(RacingSimulatorObserver observer : _observers)
			observer.reset();
	}
	
	public Music getMusic() {
		return _music;
	}
}
