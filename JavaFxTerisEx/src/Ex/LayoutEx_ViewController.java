package Ex;

import java.net.URL;
import java.util.ResourceBundle;

import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.EventType;
import com.quirko.logic.events.MoveEvent;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LayoutEx_ViewController implements Initializable {
	@FXML
	private GridPane gamePanel;
	
	@FXML
	private GridPane blockPanel;
	
	Rectangle rect= null;
	Rectangle curr_rect= null;
	Rectangle[][] rectangles = null;
	Block curr_block = null;
	Block temp_block = null;
	Scene scene = null;
	Timeline timeLine = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		gamePanel.setFocusTraversable(true);
		gamePanel.requestFocus();
		gamePanel.setOnKeyPressed(event->{
			if(event.getCode()==KeyCode.UP){
				System.out.println("up");
				
				
			}
			if(event.getCode()==KeyCode.DOWN){
				System.out.println("down");
				//gamePanel.getChildren();
				//int index = gamePanel.getChildren().indexOf(curr_rect);
				moveBlock(0, 1);
			}
			if(event.getCode()==KeyCode.LEFT){
				System.out.println("left");
				moveBlock(-1, 0);
			}
			if(event.getCode()==KeyCode.RIGHT){
				System.out.println("right");
				moveBlock(1, 0);				
			}
			
		});

	}
	public void moveBlock(int off_x, int off_y) {
		// TODO Auto-generated method stub		
		//temp_block = new Block();
		temp_block = curr_block;
		
		if(isCrash(off_x, off_y)){
			refresh(); //안결렸을때 
		}else{
			curr_block=temp_block; //브레이크 걸렸을때
		}
		
		
	}
											//1
	public boolean isCrash(int off_x, int off_y) {
		// TODO Auto-generated method stub
		int x=0;
		int y=0;
		
		for(int i=0; i<curr_block.locations.length; i++){
			x = curr_block.locations[i].getX();
			y = curr_block.locations[i].getY();
			 	x = x + off_x;
				y = y + off_y;
				if(x>=9) return false; //x=9;
				if(x<1) return false;//x=1;
				if(y>=19){
					y = 19;
					//timeLine.stop();
				}
	    		curr_block.locations[i].setXY(x, y);
		}
		
		return true;
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
		int x=0;
		int y=0;
		
		for(int i=0; i<temp_block.locations.length; i++){
			x = temp_block.locations[i].getX();
			y = temp_block.locations[i].getY();
			if(y>=0)
			rectangles[y][x].setFill(Color.RED);
		}
		
		for(int i=0; i<curr_block.locations.length; i++){
			x = curr_block.locations[i].getX();
			y = curr_block.locations[i].getY();
			if(y>=0)
			rectangles[y][x].setFill(Color.BLUE);
		}
	}
	
	public void addPanel(int height, int width) {
		// TODO Auto-generated method stub
	
		rectangles = new Rectangle[height][width];
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				rect = new Rectangle(20,20);
				rect.setFill(Color.RED);
				rectangles[i][j] = rect;
				gamePanel.add(rect, j, i);
			}
		}
		
	}
	
	public void createbrick() {
//		curr_rect = new Rectangle(20,20);
//		curr_rect.setFill(Color.BLUE);
//		gamePanel.add(curr_rect, 4, 0);
		curr_block = new Block();
        for(int i=0; i<curr_block.locations.length; i++){
//        	curr_rect = new Rectangle(20,20);
//        	curr_rect.setFill(curr_block.color);
   
        	int x = curr_block.locations[i].getX()+4;
        	int y = curr_block.locations[i].getY();
        	//rectangles[y][x].setFill(Color.BLUE);
        	curr_block.locations[i].setXY(x, y);
    		
        	//gamePanel.add(rect, curr_block.locations[i].getY(), curr_block.locations[i].getX());
        }
		
//		timeLine = new Timeline(new KeyFrame(
//                Duration.millis(500),
//                ae -> moveBlock(0, 1)
//        ));
//        timeLine.setCycleCount(Timeline.INDEFINITE);
//        timeLine.play();
        
        
	}

}
