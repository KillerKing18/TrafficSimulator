package graphlayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import model.Junction;
import model.Junction.IncomingRoad;
import model.Road;
import model.Vehicle;

public class GraphComponent extends JComponent implements Runnable {
	
	private Thread hilo;
	private final int DELAY=5;
	protected Map<String, Integer> _x;
	protected Map<String, Integer> _y;
	protected Map<String, Integer> _x_past;
	protected Map<String, Integer> _y_past;
	protected Map<String, Integer> _x_current;
	protected Map<String, Integer> _y_current;
	private boolean empezado;
	private boolean avanzado;
	

	private static final long serialVersionUID = 1L;

	/**
	 * The radius of each node
	 */
	private static final int _nodeRadius = 20;

	/**
	 * The radius of each dot
	 */
	private static final int _dotRadius = 40;

	/**
	 * An inner class that represent a location of a node. Fields cX and cY are the
	 * center of the node, and fields tX and tY are the location where the label of
	 * the node is drawn. This is calculated for each node in the method
	 * {@code calculateNodeCoordinates()}
	 *
	 */
	private class Point {
		int cX;
		int cY;
		int tX;
		int tY;

		public Point(int cX, int cY, int tX, int tY) {
			this.cX = cX;
			this.cY = cY;
			this.tX = tX;
			this.tY = tY;
		}
	}

	/**
	 * The graph to layout
	 */
	private Graph _graph;

	/**
	 * A map to store the location of each node
	 */
	Map<String, Point> _nodesPisitions;

	/**
	 * width and height of the window when it was last resized. When change we
	 * recalculate the location of nodes to scale the graph, etc.
	 */
	private int _lastWidth;
	private int _lastHeight;

	public GraphComponent() {
		 setDoubleBuffered(true);
		 empezado = false;
		 avanzado = false;
		 _x = new HashMap<String, Integer>();
		 _y = new HashMap<String, Integer>();
		 _x_past = new HashMap<String, Integer>();
		 _y_past = new HashMap<String, Integer>();
		 _x_current = new HashMap<String, Integer>();
		 _y_current = new HashMap<String, Integer>();
		
		
		_nodesPisitions = new HashMap<>();
		setMinimumSize(new Dimension(500, 500));
		setPreferredSize(new Dimension(500, 500));
		_lastWidth = -1;
		_lastHeight = -1;
	}
	
	public void setAvanzado(boolean b) {
		avanzado = b;
	}
   
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if (_graph == null || _graph.getNodes().size() == 0) {
			g.setColor(Color.red);
			g.drawString("No graph yet!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			drawMap(g);
			Toolkit.getDefaultToolkit().sync();
	        g.dispose();
		}
	}

	private void drawMap(Graphics g) {

		// if the size of the component has changed since the last time we
		// calculated the positions of the nodes, then we recalculate again.
		// This way the map get scaled down/up.
		if (_lastHeight != getHeight() || _lastWidth != getWidth()) {
			_lastHeight = getHeight();
			_lastWidth = getWidth();
			calculateNodeCoordinates();
		}

		// draw nodes
		for (Junction j : _graph.getNodes()) {
			Point p = _nodesPisitions.get(j.getId());
			g.setColor(Color.blue);
			g.fillOval(p.cX - _nodeRadius / 2, p.cY - _nodeRadius / 2, _nodeRadius, _nodeRadius);
			g.setColor(Color.black);
			g.drawString(j.getId(), p.tX, p.tY);
		}

		// draw edges
		for (Road r : _graph.getEdges()) {
			Point p1 = _nodesPisitions.get(r.getSource().getId());
			Point p2 = _nodesPisitions.get(r.getDestination().getId());

			// draw the edge
			Color arrowColor = null;
			boolean found = false;
			IncomingRoad ir = null;
			for(int i = 0; i < r.getDestination().getRoadsInfo().size() && !found; i++) {
				ir = r.getDestination().getRoadsInfo().get(i);
				if(ir.getRoad() == r) {
					found = true;
					if(ir.hasGreenLight())
						arrowColor = Color.GREEN;
					else
						arrowColor = Color.RED;
				}
			}
			drawArrowLine(g, p1.cX, p1.cY, p2.cX, p2.cY, 15, 5, Color.BLACK, arrowColor);

			// draw dots as circles. Dots at the same location are drawn with circles of
			// different diameter.
			int lastLocation = -1;
			int diam = _dotRadius;
			for (Vehicle v : r.getVehicles()) {
				if (lastLocation != v.getLocation()) {
					lastLocation = v.getLocation();
					diam = _dotRadius;
				} else {
					diam -= _dotRadius / 2;
				}
				Color dotColor = Math.random() > 0.5 ? Color.MAGENTA : Color.ORANGE;
				drawCircleOnALine(g, p1.cX, p1.cY, p2.cX, p2.cY, r.getLength(), v.getLocation(), diam, dotColor,
						v.getId());
			}
		}
		empezado = true;
		avanzado = false;
	}

	/**
	 * put the objects in a circle, for each one store the center coordinate and a
	 * coordinate for a corresponding text.
	 */
	private void calculateNodeCoordinates() {

		int r = Math.min(_lastHeight, _lastWidth) / 2 - _nodeRadius - 50; // 50 for
																			// text
		int tr = (r + _nodeRadius + 10);

		int xc = _lastWidth / 2 - 10;
		int yc = _lastHeight / 2 - 10;

		double slice = 2 * Math.PI / _graph.getNodes().size();
		int i = 0;
		for (Junction j : _graph.getNodes()) {

			double angle = slice * i;
			int cX = (int) (xc + r * Math.cos(angle));
			int cY = (int) (yc + r * Math.sin(angle));
			int tX = (int) (xc + tr * Math.cos(angle));
			int tY = (int) (yc + tr * Math.sin(angle));

			_nodesPisitions.put(j.getId(), new Point(cX, cY, tX, tY));
			i++;
		}

	}

	/**
	 * Draws a circle on the line from (x1,y1) to (x2,y2). Assuming the (virtual)
	 * length of the line is virtualLength, the circles is drawn at location
	 * virtualLocation (0..virtualLength). The diameter is 'diam'
	 */
	private void drawCircleOnALine(Graphics g, int x1, int y1, int x2, int y2, int virtualLength, int virtualLocation,
			int diam, Color c, String txt) {

		// The actual length of the line
		double lineActualLength = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) - 45;

		// the angle of the line with the horizontal axis
		double alpha = Math.atan(((double) Math.abs(x1 - x2)) / ((double) Math.abs(y1 - y2)));

		// the actual location on the line (0..lineActualLength)
		double actualLocation = lineActualLength * ((double) virtualLocation) / ((double) virtualLength) + 15;

		// the coordinates of the location
		double x = Math.sin(alpha) * actualLocation;
		double y = Math.cos(alpha) * actualLocation;

		// signs repressing the direction of the line (left/right, up/down)
		int xDir = x1 < x2 ? 1 : -1;
		int yDir = y1 < y2 ? 1 : -1;

		// draw the point
		g.setColor(c);
		try {
			Image img = new ImageIcon(getClass().getResource("/images/" + txt + ".gif")).getImage();
			if(!empezado) {
				_x_past.put(txt, x1 + xDir * ((int) x) - diam / 2);
				_y_past.put(txt, y1 + yDir * ((int) y) - diam / 2);
				_x_current.put(txt, _x_past.get(txt));
				_y_current.put(txt, _y_past.get(txt));
			}
			else {
				if(avanzado) {
					_x_past.put(txt, _x.get(txt));
					_y_past.put(txt, _y.get(txt));
				}
			}
			_x.put(txt, x1 + xDir * ((int) x) - diam / 2);
			_y.put(txt, y1 + yDir * ((int) y) - diam / 2);
			int positionX = _x_current.get(txt);
			int positionY = _y_current.get(txt);
			g.drawImage(img,positionX, positionY, diam, diam, this);
		} catch (Exception e) {
			g.drawOval(x1 + xDir * ((int) x) - diam / 2, y1 + yDir * ((int) y) - diam / 2, diam, diam);
		}

		// draw the text
		g.setColor(Color.darkGray);
		g.drawString(txt, x1 + xDir * ((int) x) - diam / 2, y1 + yDir * ((int) y) - diam / 2);
	}

	/**
	 * Draws a line from (x1,y1) to (x2,y2) with an arrow of width d and height h.
	 * The color of the line is lineColor and that of the arrow is arrowColor.
	 */
	private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h, Color lineColor,
			Color arrowColor) {
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		g.setColor(lineColor);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(arrowColor);
		g.fillPolygon(xpoints, ypoints, 3);
	}

	public void setGraph(Graph graph) {
		_graph = graph;
		calculateNodeCoordinates();
		refresh();
	}

	public void refresh() {
		repaint();
	}

	@Override
	public void run() {
		while(true){
            ciclo();
            repaint();
            try{
                Thread.sleep(DELAY);
            }catch(InterruptedException err){
                System.out.println(err);
            }
        }
	}
	
	public void ciclo(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 	for (String key : _x.keySet()) {
		        int viejox = _x_past.get(key);
		        int viejoy = _y_past.get(key);
		        int nuevox = _x.get(key);
		        int nuevoy = _y.get(key);
		        
		        
		        int nextPositionX = _x_current.get(key) + ((nuevox - viejox) / 5);
		        int nextPositionY = _y_current.get(key) + ((nuevoy - viejoy) / 5);
		        	if((viejox < nuevox && nextPositionX >= nuevox) || 
			        		(viejox > nuevox && nextPositionX <= nuevox) ||
			        		(viejoy < nuevoy && nextPositionY >= nuevoy) ||
			        		(viejoy > nuevoy && nextPositionY <= nuevoy) ||
			        		nextPositionX == _x_current.get(key)) {
			        	_x_current.put(key, viejox);
			        	_y_current.put(key, viejoy);
			        }
			        else {
			        	_x_current.put(key, nextPositionX);
			        	_y_current.put(key, nextPositionY);
			        }
		    }
    }

	 @Override
    public void addNotify(){
        super.addNotify();
        hilo = new Thread(this);
        hilo.start();
    }
	 
	public void reset() {
		 empezado = false;
		 avanzado = false;
		 _x = new HashMap<String, Integer>();
		 _y = new HashMap<String, Integer>();
		 _x_past = new HashMap<String, Integer>();
		 _y_past = new HashMap<String, Integer>();
		 _x_current = new HashMap<String, Integer>();
		 _y_current = new HashMap<String, Integer>();
	}
}
