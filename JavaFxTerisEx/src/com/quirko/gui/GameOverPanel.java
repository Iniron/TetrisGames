package com.quirko.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class GameOverPanel extends BorderPane {							//BorderPane을 상속

    public GameOverPanel() {
        final Label gameOverLabel = new Label("GAME OVER");				//GAME OVER라는 Label생성
        gameOverLabel.getStyleClass().add("gameOverStyle");				//CSS적용
        setCenter(gameOverLabel);										//Label을 borderPane의 가운데 위치시킨다.
    }

}
