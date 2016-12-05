package com.quirko.logic.bricks;

import com.quirko.logic.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

final class OBrick implements Brick {									//Brick�� �����Ѵ�.(getShapeMatrix override)

    private final List<int[][]> brickMatrix = new ArrayList<>();		//int[][] 2�����迭�� ��� List�� �����Ѵ�. -> brickMatri

    public OBrick() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 4, 4, 0},
                {0, 4, 4, 0},
                {0, 0, 0, 0}
        });
    }

    @Override
    public List<int[][]> getShapeMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }

}
