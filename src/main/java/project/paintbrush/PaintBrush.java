
package project.paintbrush;
import javax.swing.*;
import java.awt.*;
public class PaintBrush {

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");	
        MyPainter board = new MyPainter();
        frame.setContentPane(board);
        frame.setSize(1200, 800);	
        frame.setBackground(Color.white);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
}
}