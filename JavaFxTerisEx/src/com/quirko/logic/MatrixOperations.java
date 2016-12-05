package com.quirko.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixOperations {


    //We don't want to instantiate this utility class
    private MatrixOperations(){

    }

    public static boolean intersect(final int[][] matrix, final int[][] brick, int x, int y) {
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[i].length; j++) {
                int targetX = x + i;
                int targetY = y + j;
                if (brick[j][i] != 0 && (checkOutOfBound(matrix, targetX, targetY) || matrix[targetY][targetX] != 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkOutOfBound(int[][] matrix, int targetX, int targetY) {
        boolean returnValue = true;
        if (targetX >= 0 && targetY < matrix.length && targetX < matrix[targetY].length) {
            returnValue = false;
        }
        return returnValue;
    }

    //인자로 들어온 배열을 복사해 리턴한다.
    public static int[][] copy(int[][] original) {				//인자로 int형 2차원 배열을 받아서
        int[][] myInt = new int[original.length][];				//myInt라는 새로운 변수에 대입
        for (int i = 0; i < original.length; i++) {				//2차원배열의 열(row)길이만큼 반복 -> i는 0~3, 즉 4번 반복
            int[] aMatrix = original[i];						//각 열정보를 int형 배열 aMatrix에 저장
            int aLength = aMatrix.length;						//aMatix배열의 길이를 얻어와서
            myInt[i] = new int[aLength];						//그 길이만큼의 새로운 2차원배열 myInt를 만든다.
            System.arraycopy(aMatrix, 0, myInt[i], 0, aLength); //System.arraycopy(원본, 원본으로부터 읽어올 위치, 복사대상, 복사시작위치, 원본에서 복사본까지 읽어올 양)
        }
        return myInt;											//int[][] myInt를 리턴
    }

    public static int[][] merge(int[][] filledFields, int[][] brick, int x, int y) {
        int[][] copy = copy(filledFields);
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[i].length; j++) {
                int targetX = x + i;
                int targetY = y + j;
                if (brick[j][i] != 0) {
                    copy[targetY][targetX] = brick[j][i];
                }
            }
        }
        return copy;
    }

    public static ClearRow checkRemoving(final int[][] matrix) {
        int[][] tmp = new int[matrix.length][matrix[0].length];
        Deque<int[]> newRows = new ArrayDeque<>();
        List<Integer> clearedRows = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            int[] tmpRow = new int[matrix[i].length];
            boolean rowToClear = true;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowToClear = false;
                }
                tmpRow[j] = matrix[i][j];
            }
            if (rowToClear) {
                clearedRows.add(i);
            } else {
                newRows.add(tmpRow);
            }
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
            int[] row = newRows.pollLast();
            if (row != null) {
                tmp[i] = row;
            } else {
                break;
            }
        }
        int scoreBonus = 50 * clearedRows.size() * clearedRows.size();
        return new ClearRow(clearedRows.size(), tmp, scoreBonus);
    }

    public static List<int[][]> deepCopyList(List<int[][]> list){
        return list.stream().map(MatrixOperations::copy).collect(Collectors.toList());
        //스트림 API로 콜렉션을 질의형식으로 처리한다. list에서 stream을 얻어 copy메소드를 실행한 결과를 다른 list로 저장해 리턴  
    }

}
