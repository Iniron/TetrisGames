package com.quirko.gui;

import com.quirko.app.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");	//fxml의 URL설정
        ResourceBundle resources = null;
        FXMLLoader fxmlLoader = new FXMLLoader(location, resources);				//fxmlloader 생성
        Parent root = fxmlLoader.load();											//fxml을 로드해 root를 설정한다.
        GuiController c = fxmlLoader.getController();								//controller객체를 가져온다.

        primaryStage.setTitle("TetrisJFX");											//제목 설정
        Scene scene = new Scene(root, 400, 510);									//scene에 root설정
        primaryStage.setScene(scene);												//stage에 scene설정
        primaryStage.show();														//stage를 보인다.
        new GameController(c);														//GameController 객체를 생성한다. c(controller객체)를 인자로 생성자 호출
    }


    public static void main(String[] args) {
        launch(args);
    }
}
