package com.quirko.gui;

import com.quirko.logic.DownData;
import com.quirko.logic.ViewData;
import com.quirko.logic.events.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiController implements Initializable {

    private static final int BRICK_SIZE = 20;									//버튼의 크기를 int형 20으로 설정

    @FXML
    private GridPane gamePanel;

    @FXML
    private Text scoreValue;

    @FXML
    private Group groupNotification;

    @FXML
    private GridPane nextBrick;

    @FXML
    private GridPane brickPanel;

    @FXML
    private ToggleButton pauseButton;

    @FXML
    private GameOverPanel gameOverPanel;										//BorderPane을 상속받아 GAMEOVER Label을 가운데에 위치시키는 클래스 

    private Rectangle[][] displayMatrix;

    private InputEventListener eventListener;

    private Rectangle[][] rectangles;											

    private Timeline timeLine;													//애니메이션 객체

    private final BooleanProperty isPause = new SimpleBooleanProperty();		//게임의 멈춤여부를 나타내는 booleanProperty

    private final BooleanProperty isGameOver = new SimpleBooleanProperty();		//게임의 종료여부를 나타내는 booleanProperty

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font.loadFont(getClass().getClassLoader().getResource("digital.ttf").toExternalForm(), 38);		//toExternalForm: URL에 대한 문자열 객체 생성(toString으로대체가능)
        gamePanel.setFocusTraversable(true);															//포커스 이동을 허용, 주석처리해도 게임상에 큰 문제는없다. 다른 역할이 있는듯
        gamePanel.requestFocus();																		//포커스를 요청, 주석처리해도 게임상에 큰 문제는없다. 다른 역할이 있는듯
        gamePanel.setOnKeyPressed(new EventHandler<KeyEvent>() {										//gamePanel(GridPane)에 대한 키이벤트 설정
            @Override
            public void handle(KeyEvent keyEvent) {
                if (isPause.getValue() == Boolean.FALSE && isGameOver.getValue() == Boolean.FALSE) {	//isPause가 FALSE거나 isGameOver가 FALSE경우에만 동작(즉 게임이진행중)
                    if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {						//키값이 왼쪽이거나 A인경우
                    	System.out.println("in");
                        refreshBrick(eventListener.onLeftEvent(new MoveEvent(EventType.LEFT, EventSource.USER)));		//인자를 설정(ViewDat형식)해 refreshBick메소드 호출                        
                        keyEvent.consume();
                        //이벤트는 Scene의 root node부터 시작되어 그안에 포함된 모든 노드에 전달된다. 즉이벤트가 발생하면 Scene에 설정된 Pane부터 이벤트를 받고 
                        //이후 다른 모든 노드에 이벤트가 전달된다. 여기서 event.consume은 더이상 이벤트가 전달되는것을 막는다.
                        //주석처리해도 게임상에 큰 문제는 없다.
                    }
                    if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {						//키값이 오른쪽이거나 D인경우
                        refreshBrick(eventListener.onRightEvent(new MoveEvent(EventType.RIGHT, EventSource.USER)));		//인자를 설정(ViewDat형식)해 refreshBick메소드 호출
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W) {							//키값이 위이거나 W인경우
                        refreshBrick(eventListener.onRotateEvent(new MoveEvent(EventType.ROTATE, EventSource.USER)));	//인자를 설정(ViewDat형식)해 refreshBick메소드 호출
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.S) {						//키값이 아래이거나 S인경우
                        moveDown(new MoveEvent(EventType.DOWN, EventSource.USER));										//인자를 설정(DownData형식)해 moveDown메소드 호출
                        keyEvent.consume();
                    }
                }
                if (keyEvent.getCode() == KeyCode.N) {																	//키값이 N인경우
                    newGame(null);																						//newGame메소드 실행
                }
                if (keyEvent.getCode() == KeyCode.P) {																	//키값이 P인경우
                    pauseButton.selectedProperty().setValue(!pauseButton.selectedProperty().getValue());				//pauseButton의 속성을 반대로 바꾼다.
                }

            }
        });
        gameOverPanel.setVisible(false);									//gameOverPanel를 꺼둔다.
        pauseButton.selectedProperty().bindBidirectional(isPause);			//pauseButton의 선택속성을 isPause의 속성과 양방향 bind한다.
        pauseButton.selectedProperty().addListener(new ChangeListener<Boolean>() {	//pauseButton의 속성감시
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {							//변경값이 true일 경우
                    timeLine.pause();					//timeline을 중지
                    pauseButton.setText("Resume");		//pauseButton에 Resume이라고 표기
                } else {								//false인 경우
                    timeLine.play();					//timeline을 시작
                    pauseButton.setText("Pause");		//pauseButton에 Pause라고 표기
                }
            }
        });
        final Reflection reflection = new Reflection();		//JavaFX Reflection 생성 -> 글자아래에 물이 있듯이 물에 비치는 반사 효과
        reflection.setFraction(0.8);						//비치는 크기 -> 1.0이면 물체가 전부 비치고 내려갈수록 그 크기가 줄어듬
        reflection.setTopOpacity(0.9);						//투명도
        reflection.setTopOffset(-12);						//비치는 거리 -> 즉 물의 위치정도
        scoreValue.setEffect(reflection);					//scoreValue에 Reflction효과 적용
    }

    public void initGameView(int[][] boardMatrix, ViewData brick) {
        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - 2);
            }
        }

        rectangles = new Rectangle[brick.getBrickData().length][brick.getBrickData()[0].length];
        for (int i = 0; i < brick.getBrickData().length; i++) {
            for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(getFillColor(brick.getBrickData()[i][j]));
                rectangles[i][j] = rectangle;
                brickPanel.add(rectangle, j, i);
            }
        }
        brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
        brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);

        generatePreviewPanel(brick.getNextBrickData());


        timeLine = new Timeline(new KeyFrame(
                Duration.millis(400),
                ae -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
        ));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    private Paint getFillColor(int i) {
        Paint returnPaint;
        switch (i) {
            case 0:
                returnPaint = Color.TRANSPARENT;
                break;
            case 1:
                returnPaint = Color.AQUA;
                break;
            case 2:
                returnPaint = Color.BLUEVIOLET;
                break;
            case 3:
                returnPaint = Color.DARKGREEN;
                break;
            case 4:
                returnPaint = Color.YELLOW;
                break;
            case 5:
                returnPaint = Color.RED;
                break;
            case 6:
                returnPaint = Color.BEIGE;
                break;
            case 7:
                returnPaint = Color.BURLYWOOD;
                break;
            default:
                returnPaint = Color.WHITE;
                break;
        }
        return returnPaint;
    }

    private void generatePreviewPanel(int[][] nextBrickData) {
        nextBrick.getChildren().clear();
        for (int i = 0; i < nextBrickData.length; i++) {
            for (int j = 0; j < nextBrickData[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                setRectangleData(nextBrickData[i][j], rectangle);
                if (nextBrickData[i][j] != 0) {
                    nextBrick.add(rectangle, j, i);
                }
            }
        }
    }

    private void refreshBrick(ViewData brick) {										//인자로 ViewData를  받는다. 
        if (isPause.getValue() == Boolean.FALSE) {									//게임이 중지상태가 아닐경우
            brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
            brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);
            for (int i = 0; i < brick.getBrickData().length; i++) {
                for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                    setRectangleData(brick.getBrickData()[i][j], rectangles[i][j]);
                }
            }
            generatePreviewPanel(brick.getNextBrickData());
        }
    }

    public void refreshGameBackground(int[][] board) {
        for (int i = 2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(getFillColor(color));
        rectangle.setArcHeight(9);
        rectangle.setArcWidth(9);
    }

    private void moveDown(MoveEvent event) {
        if (isPause.getValue() == Boolean.FALSE) {
            DownData downData = eventListener.onDownEvent(event);
            if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
                NotificationPanel notificationPanel = new NotificationPanel("+" + downData.getClearRow().getScoreBonus());
                groupNotification.getChildren().add(notificationPanel);
                notificationPanel.showScore(groupNotification.getChildren());
            }
            refreshBrick(downData.getViewData());
        }
        gamePanel.requestFocus();
    }

    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void bindScore(IntegerProperty integerProperty) {
        scoreValue.textProperty().bind(integerProperty.asString());
    }

    public void gameOver() {
        timeLine.stop();
        gameOverPanel.setVisible(true);
        isGameOver.setValue(Boolean.TRUE);

    }

    public void newGame(ActionEvent actionEvent) {
        timeLine.stop();
        gameOverPanel.setVisible(false);
        eventListener.createNewGame();
        gamePanel.requestFocus();
        timeLine.play();
        isPause.setValue(Boolean.FALSE);
        isGameOver.setValue(Boolean.FALSE);
    }

    public void pauseGame(ActionEvent actionEvent) {
        gamePanel.requestFocus();
    }
}
