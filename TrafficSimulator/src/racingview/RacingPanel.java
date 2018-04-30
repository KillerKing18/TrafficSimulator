package racingview;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Controller;
import model.Kart;
import model.Observable;
import model.RacingSimulator;
import model.RacingSimulatorObserver;
import model.TrafficSimulator;
import model.Vehicle;
import music.Music;
import view.MainPanel;

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
	private JPanel _selectedCupPanel;
	private SelectedCupTable _selectedCupTable;
	private JPanel _selectedCupImage;
	private ClassificationTable _classificationTable;

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
		_music = new Music("src/music/start_simulator.wav");
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
		
		createImagesPanel();
		createCharacterChooserPanel();
		createCircuitChooserPanel();
	
		_topPanel.add(_characterChooserPanel);
		_topPanel.add(_imagesPanel);
		_topPanel.add(_circuitChooserPanel);	
	}
	
	protected void createCharacterChooserPanel() {
		_characterChooserPanel = new CharacterChooserPanel(mario_characters, this, _imagesPanel);
		_characterChooserPanel.setMinimumSize(new Dimension(300, 150));
		_characterChooserPanel.setPreferredSize(new Dimension(300, 150));
		_characterChooserPanel.setMaximumSize(new Dimension(300, 150));
	}
	
	protected void createImagesPanel() {
		_imagesPanel = new ImagesPanel("/images/mariokart.png", _model);
		_imagesPanel.setLayout(new BoxLayout(_imagesPanel, BoxLayout.X_AXIS));
		_imagesPanel.setMinimumSize(new Dimension(400, 150));
		_imagesPanel.setPreferredSize(new Dimension(400, 150));
		_imagesPanel.setMaximumSize(new Dimension(400, 150));
	}
	
	protected void createClassificationTable() {
		_classificationTable = new ClassificationTable();
		_model.addObserver(_classificationTable);
		_classificationTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Clasification: "));
	}
	
	protected void createCircuitChooserPanel() {
		_circuitChooserPanel = new CupChooserPanel(mario_circuits, this, _imagesPanel);
		_circuitChooserPanel.setMinimumSize(new Dimension(300, 150));
		_circuitChooserPanel.setPreferredSize(new Dimension(300, 150));
		_circuitChooserPanel.setMaximumSize(new Dimension(300, 150));
	}
	
	@Override
	protected void createDownLeftPanel() {
		_downLeftPanel = new JPanel();
		_downLeftPanel.setLayout(new BoxLayout(_downLeftPanel, BoxLayout.Y_AXIS));
		_downLeftPanel.setMinimumSize(new Dimension(440, 850));
		_downLeftPanel.setPreferredSize(new Dimension(440, 850));
		_downLeftPanel.setMaximumSize(new Dimension(440, 850));
		
		createSelectedCupPanel();
		createSelectedCharactersTable();
		
		_downLeftPanel.add(_selectedCharactersTable);
		_downLeftPanel.add(_selectedCupPanel);
	}
	
	protected void createSelectedCupPanel(){
		_selectedCupPanel = new JPanel();
		_selectedCupPanel.setMinimumSize(new Dimension(420, 380));
		_selectedCupPanel.setPreferredSize(new Dimension(420, 380));
		_selectedCupPanel.setMaximumSize(new Dimension(420, 380));
		_selectedCupPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), _circuitChooserPanel.getSelectedCup() + " Cup"));
		
		createSelectedCupImage();
		createSelectedCupTable();
		
		_selectedCupPanel.add(_selectedCupImage);
		_selectedCupPanel.add(_selectedCupTable);
	}
	
	protected void createSelectedCupImage() {
		_selectedCupImage = new JPanel();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/" + _circuitChooserPanel.getSelectedCup() + "trophy" + ".jpg"));
		icon.setImage(icon.getImage().getScaledInstance(390, 230, 1));
		_selectedCupImage.add(new JLabel(icon));
	}
	
	protected void createSelectedCharactersTable() {
		_selectedCharactersTable = new SelectedCharactersTable();
		addObserver(_selectedCharactersTable);
		_selectedCharactersTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Pilots"));
		_selectedCharactersTable.setMinimumSize(new Dimension(420, 385));
		_selectedCharactersTable.setPreferredSize(new Dimension(420, 385));
		_selectedCharactersTable.setMaximumSize(new Dimension(420, 385));
	}
	
	protected void createSelectedCupTable() {
		_selectedCupTable = new SelectedCupTable();
		addObserver(_selectedCupTable);
		_selectedCupTable.setMinimumSize(new Dimension(410, 100));
		_selectedCupTable.setPreferredSize(new Dimension(410, 100));
		_selectedCupTable.setMaximumSize(new Dimension(410, 100));
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
		case "ITEM BOX":
			ImageIcon itemboxicon = new ImageIcon(this.getClass().getResource("/icons/itembox.png"));
			itemboxicon.setImage(itemboxicon.getImage().getScaledInstance(150, 150, 1));
			String activated = !((RacingToolBar) _toolBar).getItemBoxActivated() ? "enabled!\n(Each pilot will get one"
					+ " when he/she starts a new lap)" : "disabled!";				
			JOptionPane.showMessageDialog(this, "Item Boxes are now " + activated, "Item Box", JOptionPane.INFORMATION_MESSAGE, itemboxicon);
			((RacingToolBar) _toolBar).setItemBoxActivated(!((RacingToolBar) _toolBar).getItemBoxActivated());
			for(Vehicle v : _model.getRoadMap().getVehicles())
				((Kart)v).changeItemBox();
			break;
		case "RESET":
			((RacingToolBar) _toolBar).setLapsSpinnerEnabled(true);
			((RacingToolBar) _toolBar).setItemBoxEnabled(false);
			((RacingToolBar) _toolBar).setItemBoxActivated(true);
			notifyReset();
			_control.reset();
			_characterChooserPanel.reset();
			_circuitChooserPanel.reset();
			_downLeftPanel.removeAll();
			createSelectedCharactersTable();
			createSelectedCupPanel();
			_downLeftPanel.add(_selectedCharactersTable);
			_downLeftPanel.add(_selectedCupPanel);
			_downLeftPanel.repaint();
			_downLeftPanel.updateUI();
			break;
		case "RUN":
			boolean begin = true;
			if(_model.getTime() == 0) {
				_circuitChooserPanel.setSelectedCup(((RacingToolBar) _toolBar).getLaps());
				begin = _imagesPanel.checkIn(_circuitChooserPanel.getSelectedCupJunctions(), 
						_circuitChooserPanel.getSelectedCupItinerary(), _characterChooserPanel.getSpeedMap(), 
						_characterChooserPanel.getLuckMap(), _characterChooserPanel.getSelectedCharacters());
			}
			if(begin) {
				((RacingToolBar) _toolBar).setItemBoxEnabled(true);
				((RacingToolBar) _toolBar).setLapsSpinnerEnabled(false);
				_downLeftPanel.removeAll();
				createClassificationTable();
				_downLeftPanel.add(_classificationTable);
				_downLeftPanel.repaint();
				_downLeftPanel.updateUI();
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
					if(_model.getTotalVehicles() == ((RacingSimulator)_model).getArrivedVehicles()) {
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
			((RacingToolBar)_toolBar).getComboBox().setSelectedIndex(selected);
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
		obs.registered(_characterChooserPanel, _imagesPanel, _circuitChooserPanel, _selectedCupImage, _selectedCupPanel);
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
