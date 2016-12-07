package Ex;

import java.net.URL;
import java.util.ResourceBundle;

import com.quirko.gui.GuiController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LayoutEx extends Application{
	
	//Gamecon
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Layout.fxml"));				//fxmlloader 생성
	    Parent root = fxmlLoader.load();											//fxml을 로드해 root를 설정한다.
	    LayoutEx_ViewController abc = fxmlLoader.getController();						//controller객체를 가져온다.	
	     
		Scene scene = new Scene(root, 220, 540);
		primaryStage.setScene(scene);
		primaryStage.setTitle("LayoutEx");
		primaryStage.show();
		new LayoutEx_GameController(abc);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
