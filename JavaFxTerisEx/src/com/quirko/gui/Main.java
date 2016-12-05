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

        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");	//fxml�� URL����
        ResourceBundle resources = null;
        FXMLLoader fxmlLoader = new FXMLLoader(location, resources);				//fxmlloader ����
        Parent root = fxmlLoader.load();											//fxml�� �ε��� root�� �����Ѵ�.
        GuiController c = fxmlLoader.getController();								//controller��ü�� �����´�.

        primaryStage.setTitle("TetrisJFX");											//���� ����
        Scene scene = new Scene(root, 400, 510);									//scene�� root����
        primaryStage.setScene(scene);												//stage�� scene����
        primaryStage.show();														//stage�� ���δ�.
        new GameController(c);														//GameController ��ü�� �����Ѵ�. c(controller��ü)�� ���ڷ� ������ ȣ��
    }


    public static void main(String[] args) {
        launch(args);
    }
}
