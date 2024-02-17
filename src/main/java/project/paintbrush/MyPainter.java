package project.paintbrush;
import java.awt.BasicStroke;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MyPainter extends JPanel implements MouseListener, MouseMotionListener {

    ArrayList<Shape> componnet;
    int width;
    int height;
    int x1;
    int y1;
    int x2;
    int y2;
    Color color;
    boolean isDragged;
    boolean isDotted;
    boolean isFilled;
    SelectShape selected;
    int switcher = 1;

    public MyPainter() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(1, 8, 10, 0));
        this.setLayout(new FlowLayout(3, 2, 0));
        this.setVisible(true);
        this.componnet = new ArrayList();
        JButton red = new JButton("RED");
        red.setBackground(Color.RED);
        JButton green = new JButton("Green");
        green.setBackground(Color.GREEN);
        JButton blue = new JButton("Blue");
        blue.setBackground(Color.BLUE);
        JButton oval = new JButton("Draw Oval");
        JButton rectangle = new JButton("Darw Rectangle");
        JButton line = new JButton("Draw line");
        JButton eraser = new JButton("Eraser");
        Checkbox dotted = new Checkbox("Dotted");
        JButton freehand = new JButton("Free Hand");
        Checkbox filled = new Checkbox("Filled");
        JButton clear = new JButton("Clear All");
        red.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MyPainter.this.color = Color.red;
            }
        });
        this.add(red);

        green.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MyPainter.this.color = Color.green;
            }
        });
        this.add(green);

        blue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MyPainter.this.color = Color.blue;
            }
        });
        this.add(blue);

        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyPainter.this.selected = SelectShape.Rectangle;
            }
        });
        this.add(rectangle);

        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyPainter.this.selected = SelectShape.Oval;
            }
        });
        this.add(oval);

        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyPainter.this.selected = SelectShape.Line;
            }
        });
        this.add(line);

        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyPainter.this.selected = SelectShape.Eraser;
            }
        });
        this.add(eraser);

        freehand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyPainter.this.selected = SelectShape.Freehand;

            }
        });
        this.add(freehand);

        filled.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    MyPainter.this.isFilled = true;
                } else {
                    MyPainter.this.isFilled = false;
                }

            }
        });
        this.add(filled);

        dotted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    MyPainter.this.isDotted = true;
                } else {
                    MyPainter.this.isDotted = false;
                }

            }
        });
        this.add(dotted);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyPainter.this.componnet.clear();
                MyPainter.this.updateUI();
            }
        });
        this.add(clear);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (MyPainter.this.selected == SelectShape.Line) {
            MyPainter.this.x1 = e.getX();
            MyPainter.this.y1 = e.getY();
            MyPainter.this.isDragged = true;
            MyPainter.this.updateUI();
        }

        if (MyPainter.this.selected == SelectShape.Rectangle) {
            MyPainter.this.x1 = e.getX();
            MyPainter.this.y1 = e.getY();
            MyPainter.this.isDragged = true;
            MyPainter.this.updateUI();
        }

        if (MyPainter.this.selected == SelectShape.Oval) {
            MyPainter.this.x1 = e.getX();
            MyPainter.this.y1 = e.getY();
            MyPainter.this.isDragged = false;
            MyPainter.this.updateUI();
        }

        if (MyPainter.this.selected == SelectShape.Eraser) {
            MyPainter.this.componnet.add(new Eraser(MyPainter.this.x1, MyPainter.this.y1));
            MyPainter.this.isDragged = true;
            MyPainter.this.updateUI();
        }

        if (MyPainter.this.selected == SelectShape.Freehand) {
            MyPainter.this.componnet.add(new FreeHand(MyPainter.this.x1, MyPainter.this.y1, MyPainter.this.color));
            MyPainter.this.isDragged = true;
            MyPainter.this.updateUI();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (MyPainter.this.selected == SelectShape.Line) {
            MyPainter.this.x2 = e.getX();
            MyPainter.this.y2 = e.getY();
            MyPainter.this.componnet.add(new Line(MyPainter.this.x1, MyPainter.this.y1, MyPainter.this.x2, MyPainter.this.y2, MyPainter.this.color, MyPainter.this.isDotted));
            MyPainter.this.isDragged = true;
            MyPainter.this.repaint();
        }

        if (MyPainter.this.selected == SelectShape.Rectangle) {
            MyPainter.this.width = Math.abs(MyPainter.this.x1 - MyPainter.this.x2);
            MyPainter.this.height = Math.abs(MyPainter.this.y1 - MyPainter.this.y2);
            MyPainter.this.componnet.add(new Rectangle(Math.min(MyPainter.this.x1, MyPainter.this.x2), Math.min(MyPainter.this.y1, MyPainter.this.y2), MyPainter.this.width, MyPainter.this.height, MyPainter.this.color, MyPainter.this.isFilled, MyPainter.this.isDotted));
            MyPainter.this.isDragged = true;
            MyPainter.this.repaint();
        }

        if (MyPainter.this.selected == SelectShape.Oval) {
            MyPainter.this.width = Math.abs(MyPainter.this.x1 - MyPainter.this.x2);
            MyPainter.this.height = Math.abs(MyPainter.this.y1 - MyPainter.this.y2);
            MyPainter.this.componnet.add(new Oval(Math.min(MyPainter.this.x1, MyPainter.this.x2), Math.min(MyPainter.this.y1, MyPainter.this.y2), MyPainter.this.width, MyPainter.this.height, MyPainter.this.color, MyPainter.this.isDotted, MyPainter.this.isFilled));
            MyPainter.this.isDragged = true;
            MyPainter.this.repaint();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (MyPainter.this.selected == SelectShape.Line) {
            MyPainter.this.x2 = MyPainter.this.getX();
            MyPainter.this.y2 = MyPainter.this.getY();
            MyPainter.this.isDragged = true;
            MyPainter.this.updateUI();
        }

        if (MyPainter.this.selected == SelectShape.Rectangle) {
            MyPainter.this.x2 = e.getX();
            MyPainter.this.y2 = e.getY();
            MyPainter.this.isDragged = true;
            MyPainter.this.updateUI();
        }

        if (MyPainter.this.selected == SelectShape.Oval) {
            MyPainter.this.x2 = e.getX();
            MyPainter.this.y2 = e.getY();
            MyPainter.this.isDragged = true;
            MyPainter.this.updateUI();
        }

        if (MyPainter.this.selected == SelectShape.Eraser) {
            MyPainter.this.componnet.add(new Eraser(e.getX(), e.getY()));
            MyPainter.this.isDragged = true;
            MyPainter.this.updateUI();
        }

        if (MyPainter.this.selected == SelectShape.Freehand) {
            MyPainter.this.componnet.add(new FreeHand(e.getX(), e.getY(), MyPainter.this.color));
            MyPainter.this.isDragged = true;
            MyPainter.this.updateUI();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (this.switcher != 0) {
            Iterator var3 = this.componnet.iterator();

            while (var3.hasNext()) {
                Shape sh = (Shape) var3.next();
                g.setColor(sh.getColor());
                if (sh.isDotted()) {
                    g2d.setStroke(new BasicStroke(1.0F, 0, 2, 10.0F, new float[]{9.0F}, 0.0F));
                } else {
                    g2d.setStroke(new BasicStroke(0.0F));
                }

                if (sh instanceof Line) {
                    g.drawLine(((Line) sh).getXx1(), ((Line) sh).getYy1(), ((Line) sh).getXx2(), ((Line) sh).getYy2());
                }

                if (sh instanceof Rectangle) {
                    if (((Rectangle) sh).isFilled()) {
                        g.fillRect(((Rectangle) sh).getXx(), ((Rectangle) sh).getYy(), ((Rectangle) sh).getWidth(), ((Rectangle) sh).getHeight());
                    } else {
                        g.drawRect(((Rectangle) sh).getXx(), ((Rectangle) sh).getYy(), ((Rectangle) sh).getWidth(), ((Rectangle) sh).getHeight());
                    }
                }

                if (sh instanceof Oval) {
                    if (((Oval) sh).isFilled()) {
                        g.fillOval(((Oval) sh).getXx(), ((Oval) sh).getYy(), ((Oval) sh).getWidth(), ((Oval) sh).getHeight());
                    } else {
                        g.drawOval(((Oval) sh).getXx(), ((Oval) sh).getYy(), ((Oval) sh).getWidth(), ((Oval) sh).getHeight());
                    }
                }

                if (sh instanceof Eraser) {
                    g.fillRect(((Eraser) sh).getXx(), ((Eraser) sh).getYy(), ((Eraser) sh).getWidth(), ((Eraser) sh).getHeight());
                }
            }

        }
    }
}
