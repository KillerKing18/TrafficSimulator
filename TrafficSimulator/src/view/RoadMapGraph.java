package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import graphlayout.Graph;
import graphlayout.GraphComponent;
import model.Event;
import model.Junction;
import model.Road;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulatorObserver;

public class RoadMapGraph extends JPanel implements TrafficSimulatorObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GraphComponent _graph;
	protected RoadMap _map;
	
	public RoadMapGraph() {
		initGUI();
	}
	
	protected void initGUI() {
		this.setLayout(new BorderLayout());	
		_graph = new GraphComponent();
		this.add(_graph, BorderLayout.CENTER);
	}

	@Override
	public void registered(int time, RoadMap map, List<Event> events) {
		_map = map;
	}

	@Override
	public void simulatorError(int time, RoadMap map, List<Event> events, SimulatorError e) {
	}

	@Override
	public void advanced(int time, RoadMap map, List<Event> events) {
		_graph.setAvanzado(true);
		generateGraph();
	}

	@Override
	public void eventAdded(int time, RoadMap map, List<Event> events) {
	}

	@Override
	public void reset(int time, RoadMap map, List<Event> events) {
		_graph.reset();
		generateGraph();
	}
	
	protected void generateGraph() {
		Graph g = new Graph();
		
		for(Junction j : _map.getJunctions()) {
			g.addNode(j);
		}
		
		for (Road r : _map.getRoads()) {
			g.addEdge(r);
		}
		
		_graph.setGraph(g);
	}

	
}
