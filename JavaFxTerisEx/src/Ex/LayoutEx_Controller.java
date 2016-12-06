package Ex;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LayoutEx_Controller implements Initializable {
	@FXML
	private GridPane gamePanel;
	
	Rectangle rect= null;
	Rectangle curr_rect= null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		gamePanel.setOnKeyPressed(event->{
			if(event.getCode()==KeyCode.UP){
				System.out.println("in");
				
			}
		});
		
		gamePanel.setOnKeyPressed(event->{
			if(event.getCode() == KeyCode.DOWN){
				
			}
		});
	}
	
	public void addPanel(int width, int height) {
		// TODO Auto-generated method stub
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				rect = new Rectangle(20,20);
				rect.setFill(Color.RED);
				gamePanel.add(rect, i, j);
			}
		}
	}
	
	public void createbrick() {
		// TODO Auto-generated method stub
		curr_rect = new Rectangle(20,20);
		curr_rect.setFill(Color.BLUE);
		gamePanel.add(rect, 3, 3);
	}
}
