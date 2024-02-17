
package project.paintbrush;
import java.awt.*;

public class Oval extends Shape{
    int width;
    int height;
    boolean filled;
    int xx;
    int yy;

    public Oval(int xx,int yy, int width, int height) {
        this.width = width;
        this.height = height;
        this.xx=xx;
        this.yy=yy;
    }
    public Oval(int xx,int yy, int width, int height, Color shapeColor) {
        super(shapeColor);
        this.width = width;
        this.height = height;
        this.xx=xx;
        this.yy=yy;
    }

    public Oval(int xx,int yy, int width, int height, Color shapeColor, boolean dotted,boolean filled) {
        super(shapeColor, dotted);

        this.width = width;
        this.height = height;
        this.filled = filled;
        this.xx=xx;
        this.yy=yy;
    }

    public boolean isFilled() {
        return filled;
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


    public int getHeight() {
        return height;
    }
}
   
