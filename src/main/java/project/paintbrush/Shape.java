
package project.paintbrush;

import java.awt.*;
public class Shape {
    Color color;
    boolean dotted;
    public Shape(Color color ,boolean dotted){
        this.color=color;
        this.dotted=dotted;
    }

    public Shape(Color color) {
        this.color=color;
    }

    public boolean isDotted() {
        return dotted;
    }
    public void setDotted() {
        this.dotted=dotted;
    }
    public Color getColor() {
        return color;
    }
    public void  setColor (Color color) {
        this.color = color;
    }
    public Shape () {}
}

