package com.quirko.logic.bricks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBrickGenerator implements BrickGenerator {		//BricGenerator�� ����(getBrick, getNextBrick override)

    private final List<Brick> brickList;							//Brick�� ��� List����

    private final Deque<Brick> nextBricks = new ArrayDeque<>();		//Brick�� ��� Deque����(Deque : ť�� rear�� front�κκ� ��� ������� ������ �ڷᱸ��)

    public RandomBrickGenerator() {									//�����ڴ� ArrayList�� ����� ��� Brick�� add�Ѵ�.
        brickList = new ArrayList<>();
        brickList.add(new IBrick());
        brickList.add(new JBrick());
        brickList.add(new LBrick());
        brickList.add(new OBrick());
        brickList.add(new SBrick());
        brickList.add(new TBrick());
        brickList.add(new ZBrick());
        nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));	//nextBricks(Deque)�� 7���� �긯 �� �ϳ��� �������� ��´�.
        nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));	//nextBricks(Deque)�� 7���� �긯 �� �ϳ��� �������� ��´�.
    }

    @Override
    public Brick getBrick() {
        if (nextBricks.size() <= 1) {																//nextBricks�� 1�� ���Ϸ� ��������
            nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));	//�ڵ����� nextBricks�� �ϳ� �� �߰��Ѵ�.
        }
        return nextBricks.poll();		//nextBricks�� �ϳ� ������ �����Ѵ�.(poll�� ���� �����Ѵ�.)
    }

    @Override
    public Brick getNextBrick() {
        return nextBricks.peek();		//nextBricks�� �ϳ� ������ �����Ѵ�.(peek�� ���� �������� �ʴ´�.)
    }
}
