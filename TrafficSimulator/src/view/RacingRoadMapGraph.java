package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

import graphlayout.RacingGraphComponent;

public class RacingRoadMapGraph extends RoadMapGraph {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RacingRoadMapGraph() {
		initGUI();
	}
	
	@Override
	protected void initGUI() {
		this.setLayout(new BorderLayout());	
		_graph = new RacingGraphComponent();
		this.add(_graph, BorderLayout.CENTER);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		String pathBackground = "/images/background.png";  
		URL urlBackground = this.getClass().getResource(pathBackground);  
		ImageIcon iconBackground = new ImageIcon(urlBackground);
		g.drawImage(iconBackground.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public void setEmpezado(boolean b) {
		_graph.setEmpezado(b);
	}
}
