package com.quirko.logic;

public final class ViewData {

    private final int[][] brickData;																//int�� 2�����迭 brickData �긯�� ����
    private final int xPosition;																	//x��ǥ
    private final int yPosition;																	//y��ǥ
    private final int[][] nextBrickData;															//int�� 2�����迭 nextBrickData �����긯�� ������ �ǹ�

    public ViewData(int[][] brickData, int xPosition, int yPosition, int[][] nextBrickData) {		//�����ڷ� ������� �ʱ�ȭ
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
