package com.quirko.logic;

public final class ViewData {

    private final int[][] brickData;																//int형 2차원배열 brickData 브릭의 정보
    private final int xPosition;																	//x좌표
    private final int yPosition;																	//y죄표
    private final int[][] nextBrickData;															//int형 2차원배열 nextBrickData 다음브릭의 정보를 의미

    public ViewData(int[][] brickData, int xPosition, int yPosition, int[][] nextBrickData) {		//생성자로 멤버변수 초기화
        this.brickData = brickData;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.nextBrickData = nextBrickData;
    }

    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int[][] getNextBrickData() {
        return MatrixOperations.copy(nextBrickData);
    }
}
