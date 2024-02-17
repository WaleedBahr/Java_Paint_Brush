
package project.paintbrush;

import java.awt.*;

public class Line extends Shape{
    Point p1;
    Point p2;
    int xx1;
    int yy1;
    int xx2;
    int yy2;
    public Line(int xx1,int yy1,int xx2,int yy2, Color shapeColor, boolean dotted) {
        super(shapeColor, dotted);
        this.xx1=xx1;
        this.xx2=xx2;
        this.yy1=yy1;
        this.yy2=yy2;
    }
    public int getXx1() {
        return xx1;
    }

    public int getYy1() {
        return yy1;
    }

    public int getXx2() {
        return xx2;
    }

    public int getYy2() {
        return yy2;
    }
}

    

