package Ex;

import javafx.scene.paint.Color;

public class Block{
	Color color;
	Location[] locations;
	
	public Block() {
		// TODO Auto-generated constructor stub
		 locations = new Location[4];
		 locations[0] = new Location(0,0);
		 locations[1] = new Location(0,1);
		 locations[2] = new Location(1,0);
		 locations[3] = new Location(1,1);
		 color = Color.BLACK;
	}
}
