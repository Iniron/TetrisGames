package com.javalec.tetrisex;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class Tetris
{
    public static void main(String[] args)
        throws Exception
    {
        TetrisFrame tetrisFrame = new TetrisFrame();
        
        tetrisFrame.setDefaultCloseOperation(TetrisFrame.EXIT_ON_CLOSE);
        tetrisFrame.pack();
        moveToCenter(tetrisFrame);
        tetrisFrame.setVisible(true);
    }
    
    public static void moveToCenter(Component component)
    {
        Dimension dParent;
        Dimension dComponent = component.getSize();
        Component parent = component.getParent();
        Point offset;

        if (parent == null)
        {
            dParent = Toolkit.getDefaultToolkit().getScreenSize();
            offset = new Point(0, 0);
        }
        else
        {
            dParent = parent.getSize();
            offset = parent.getLocation();
        }
        
        component.setLocation(offset.x + (int)(dParent.getWidth()-dComponent.getWidth())/2,
                              offset.y + (int)(dParent.getHeight()-dComponent.getHeight())/2);
    }
}
