package model;

import view.CharacterChooserPanel;
import view.CupChooserPanel;
import view.ImagesPanel;

public interface RacingSimulatorObserver {
	public void registered(CharacterChooserPanel characterChooserPanel, ImagesPanel imagesPanel, CupChooserPanel cupChooserPanel);
	
	public void cupSelected();
	
	public void characterAdded();
	
	public void reset();	
}
