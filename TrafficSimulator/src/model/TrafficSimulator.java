package model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import exceptions.*;

public class TrafficSimulator implements Observable<TrafficSimulatorObserver> {
	
	private List<TrafficSimulatorObserver> _observers;
	private RoadMap _map;
	private ArrayList<Event> _events;
	private int _time;
	private OutputStream _outStream;
	protected Comparator<Event> _comparator;
	protected int _numEvents;

	
	public TrafficSimulator() {
		_outStream = null;
		_observers = new ArrayList<>();
		_time = 0;
		_map = new RoadMap();
		_events = new ArrayList<Event>();
		_comparator = new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
				if(e1._time < e2._time)
					return -1;
				else if (e1._time > e2._time)
					return 1;
				else {
					if(e1.getPositionIndex() < e2.getPositionIndex())
						return -1;
					else
						return 1;
				}
			}
		};
	}
	
	public void run(int ticks) throws IOException, SimulatorError {
		int limit = _time + ticks - 1;
		while(_time <= limit) {
			while (!_events.isEmpty() && _events.get(0).getScheduledTime() == _time) {
				_events.get(0).execute(_map, _time);
				_events.remove(0);
			}
			for(Road r: _map.getRoads())
					r.advance();
			for(Junction j: _map.getJunctions())
					j.advance();
			_time++;
			notifyAdvanced();
			if(_outStream != null)
				_outStream.write(_map.generateReport(_time).getBytes());
		}
	}

	public void addEvent(Event e) throws SimulatorError{
		if(e != null) {
			if(e.getScheduledTime() < _time) {
				SimulatorError err = new OutOfTimeException("An event was tried to be introduced after its time of execution.");
				notifyError(err);
				throw err;
			}
			_numEvents++;
			if(_events.size() == 0 || e.getScheduledTime() > _events.get(_events.size() - 1).getScheduledTime())
				e.setPosition(_numEvents);
			else {
				int newIndex = 0;
				for(; newIndex < _events.size() && _events.get(newIndex).getScheduledTime() <= e.getScheduledTime(); newIndex++) {}
				e.setPosition(newIndex + 1);
				for(; newIndex < _events.size(); newIndex++)
					_events.get(newIndex).increaseIndexPosition();
			}
			_events.add(e);
			notifyEventAdded();
			_events.sort(_comparator);
		}
		else {
			SimulatorError err = new SimulatorError("Null event tried to be added.");
			notifyError(err);
			throw err;
		}
	}
	
	public void reset() {
		_numEvents = 0;
		_time = 0;
		_events.clear();
		_map.clear();
		notifyReset();
	}
	
	public void setOutputStream(OutputStream outStream) {
		_outStream = outStream;
	}
	
	private void notifyAdvanced() {
		for(TrafficSimulatorObserver observer : _observers)
			observer.advanced(_time, _map, _events);
	}
	
	private void notifyRegistered(TrafficSimulatorObserver obs) {
		obs.registered(_time, _map, _events);
	}
	
	private void notifyError(SimulatorError err) {
		for(TrafficSimulatorObserver observer : _observers)
			observer.simulatorError(_time, _map, _events, err);
	}

	private void notifyEventAdded() {
		for(TrafficSimulatorObserver observer : _observers)
			observer.eventAdded(_time, _map, _events);
	}
	
	private void notifyReset() {
		for(TrafficSimulatorObserver observer : _observers)
			observer.reset(_time, _map, _events);
	}

	public void addObserver(TrafficSimulatorObserver obs) {
		if(obs != null && !_observers.contains(obs)) {
			_observers.add(obs);
			notifyRegistered(obs);
		}
	}
	
	public void removeObserver(TrafficSimulatorObserver obs) {
		if(obs != null && _observers.contains(obs))
			_observers.remove(obs);
	}
	/*
	public String toString() {
		
	}*/
}
