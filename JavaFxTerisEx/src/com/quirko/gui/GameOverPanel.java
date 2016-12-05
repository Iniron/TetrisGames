package com.quirko.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class GameOverPanel extends BorderPane {							//BorderPane�� ���

    public GameOverPanel() {
        final Label gameOverLabel = new Label("GAME OVER");				//GAME OVER��� Label����
        gameOverLabel.getStyleClass().add("gameOverStyle");				//CSS����
        setCenter(gameOverLabel);										//Label�� borderPane�� ��� ��ġ��Ų��.
    }

}
