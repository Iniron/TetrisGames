package com.quirko.logic.bricks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBrickGenerator implements BrickGenerator {		//BricGenerator를 구현(getBrick, getNextBrick override)

    private final List<Brick> brickList;							//Brick을 담는 List선언

    private final Deque<Brick> nextBricks = new ArrayDeque<>();		//Brick을 담는 Deque선언(Deque : 큐의 rear과 front두부분 모두 입출력이 가능한 자료구조)

    public RandomBrickGenerator() {									//생성자는 ArrayList를 만들고 모든 Brick을 add한다.
        brickList = new ArrayList<>();
        brickList.add(new IBrick());
        brickList.add(new JBrick());
        brickList.add(new LBrick());
        brickList.add(new OBrick());
        brickList.add(new SBrick());
        brickList.add(new TBrick());
        brickList.add(new ZBrick());
        nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));	//nextBricks(Deque)에 7개의 브릭 중 하나를 랜덤으로 담는다.
        nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));	//nextBricks(Deque)에 7개의 브릭 중 하나를 랜덤으로 담는다.
    }

    @Override
    public Brick getBrick() {
        if (nextBricks.size() <= 1) {																//nextBricks가 1개 이하로 떨어지면
            nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));	//자동으로 nextBricks를 하나 더 추가한다.
        }
        return nextBricks.poll();		//nextBricks를 하나 꺼내어 리턴한다.(poll은 값을 제거한다.)
    }

    @Override
    public Brick getNextBrick() {
        return nextBricks.peek();		//nextBricks를 하나 꺼내어 리턴한다.(peek은 값을 제거하지 않는다.)
    }
}
