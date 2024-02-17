
package project.paintbrush;

import java.awt.*;

public class Rectangle extends Shape{
    int width;
    int height;
    boolean filled;
    int xx;
    int yy;

    public Rectangle(){
        this.height=height;
        this.width=width;
    }
    public Rectangle(int xx,int yy,int width,int height,Color color){
        super(color);
        this.height=height;
        this.width=width;
        this.xx=xx;
        this.yy=yy;
    }
    public Rectangle(int xx,int yy,int width,int height,Color c,boolean filled,boolean dotted){
        super(c,dotted);
        this.height=height;
        this.width=width;
        this.xx=xx;
        this.yy=yy;
        this.filled=filled;

    }

    public int getHeight() {
        return height;
    }
    public int getXx() {
        return xx;
    }
    public int getYy() {
        return yy;
    }
    public int getWidth() {
        return width;
    }

    public boolean isFilled() {
        return filled;
    }
}
  
