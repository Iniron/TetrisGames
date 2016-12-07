package Ex;

public class Location {
	private int x;
	private int y;

	public Location() {
	}
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void moveXY(int offset_x, int offset_y){
		x+=offset_x;
		y+=offset_y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}	
}
