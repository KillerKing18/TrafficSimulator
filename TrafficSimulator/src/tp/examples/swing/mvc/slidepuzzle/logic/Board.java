package tp.examples.swing.mvc.slidepuzzle.logic;

public interface Board {
	public int getPosition(int row, int col);
	public boolean setPosition(int row, int col, int num);
	public int getWidth();
	public int getHeight();
}
