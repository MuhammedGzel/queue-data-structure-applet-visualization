package queueapplet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class QueueApplet extends Applet implements ActionListener {

    static int xUp = 600;
    static int xDown = 520;
    static int yUp = 320;
    static int yDown = 400;
    static int boxCount = 0;
    QueueClass numbers = new QueueClass();
    Button enQueue = new Button("Enqueue()");
    Button deQueue = new Button("Dequeue()");
    Button show = new Button("Show()");
    Label numberLabel = new Label("Number");
    TextField numbertoInsert = new TextField(10);

    public void init() {
        setSize(800, 600);
        setLayout(null);
        add(enQueue);
        enQueue.setLocation(100, 60);
        enQueue.setSize(120, 70);
        enQueue.setBackground(Color.LIGHT_GRAY);
        add(deQueue);
        deQueue.setLocation(320, 60);
        deQueue.setSize(120, 70);
        deQueue.setBackground(Color.LIGHT_GRAY);
        add(show);
        show.setLocation(540, 60);
        show.setSize(120, 70);
        show.setBackground(Color.LIGHT_GRAY);
        add(numbertoInsert);
        numbertoInsert.setLocation(320, 150);
        numbertoInsert.setSize(120, 40);
        add(numberLabel);
        numberLabel.setLocation(285, 162);
        numberLabel.setSize(30, 15);
        enQueue.addActionListener(this);
        deQueue.addActionListener(this);
        show.addActionListener(this);

    }

    void insertDownBox(int number) {
        Graphics g = getGraphics();
        g.drawRect(xDown, yDown, 50, 20);
        if (boxCount > 0) {
            g.setColor(Color.black);
            g.drawLine(xDown + 50, 410, xDown + 75, 410);
            g.drawString(">", xDown + 75, 415);
        }
        g.setColor(Color.white);
        g.fillRect(xDown + 80, 422, 50, 15);
        g.setColor(Color.black);
        g.drawString(String.valueOf(number), xDown + 17, yDown + 15);
        g.drawString("REAR", xDown + 10, 435);

        xDown = xDown - 80;
        boxCount++;
    }

    void insertUpBox(int number) {
        Graphics g = getGraphics();
        g.drawRect(xUp, yUp, 50, 20);
        if (boxCount > 6) {
            g.setColor(Color.black);
            g.drawLine(xUp + 50, 330, xUp + 75, 330);
            g.drawString(">", xUp + 75, 335);
        }
        g.setColor(Color.white);
        g.fillRect(xDown + 80, 422, 50, 15);

        g.fillRect(xUp + 80, 342, 50, 15);
        g.setColor(Color.black);
        g.drawString(String.valueOf(number), xUp + 17, yUp + 15);
        g.drawString("REAR", xUp + 10, 355);
        xUp = xUp - 80;
        boxCount++;
    }

    void removeDownBox() {
        Graphics g = getGraphics();
        g.setColor(Color.white);
        g.fillRect(xDown + 80, yDown, 80, 35);
        g.setColor(Color.BLACK);
        g.drawString("REAR", xDown + 170, 435);
        if (boxCount == 1) {
            g.setColor(Color.white);
            g.fillRect(600, 425, 50, 20);
        }

        xDown = xDown + 80;
        boxCount--;
    }

    void removeUpBox() {
        Graphics g = getGraphics();
        g.setColor(Color.white);
        g.fillRect(xUp + 80, yUp, 80, 35);
        g.setColor(Color.BLACK);
        g.drawString("REAR", xUp + 170, 355);
        if (boxCount == 7) {
            g.setColor(Color.white);
            g.fillRect(670, 340, 50, 20);
            g.setColor(Color.black);
            g.drawString("REAR", 130, 435);
        }
        xUp = xUp + 80;
        boxCount--;
    }

    void clearScreen() {
        Graphics g = getGraphics();
        g.setColor(Color.white);
        g.fillRect(100, 400, 550, 100);
        g.fillRect(100, 300, 650, 100);
        boxCount = 0;
        xUp = 600;
        xDown = 520;
        yUp = 320;
        yDown = 400;
    }

    void reInsert(int n) {
        Graphics g = getGraphics();
        Node tmp = numbers.front;
        for (int i = 1; i <= n; i++) {
            if (boxCount < 6) {
                insertDownBox(tmp.data);
            } else {
                insertUpBox(tmp.data);
            }
            if (boxCount == 7) {
                g.drawLine(100, 370, 670, 370);
                g.drawLine(650, 330, 670, 330);
                g.drawLine(670, 330, 670, 370);
                g.drawLine(100, 410, 115, 410);
                g.drawLine(100, 370, 100, 410);
                g.drawString(">", 115, 415);
            }
            tmp = tmp.next;
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == enQueue) {
            Graphics g = getGraphics();
            try {
                Node d = new Node(Integer.parseInt(numbertoInsert.getText()));
                g.drawString("FRONT", 575, 415);
                numbers.enQueue(d);
                if (boxCount < 13) {

                    if (boxCount < 6) {
                        insertDownBox(Integer.parseInt(numbertoInsert.getText()));
                    } else {
                        insertUpBox(Integer.parseInt(numbertoInsert.getText()));
                    }
                    if (boxCount == 7) {
                        g.drawLine(100, 370, 670, 370);
                        g.drawLine(650, 330, 670, 330);
                        g.drawLine(670, 330, 670, 370);
                        g.drawLine(100, 410, 115, 410);
                        g.drawLine(100, 370, 100, 410);
                        g.drawString(">", 115, 415);
                    }
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, numbertoInsert.getText() + " added.");
                } else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Capacity is full.");
                }
            } catch (NumberFormatException e) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Please enter a number.");
            }

        }

        if (ae.getSource() == deQueue) {

            int n = boxCount - 1;
            Graphics g = getGraphics();

            System.out.println(xUp);
            if (numbers.elementCount > 0) {
                numbers.deQueue();
                if (boxCount > 0) {
                    if (boxCount == 7) {
                        g.setColor(Color.white);
                        g.fillRect(100, 370, 550, 20);
                        g.fillRect(650, 310, 30, 62);
                        g.fillRect(100, 390, 20, 40);
                    }
                    if (boxCount > 6) {
                        removeUpBox();
                    } else {
                        removeDownBox();
                    }
                }

                clearScreen();
                reInsert(n);
                g.drawString("FRONT", 575, 415);
            } else {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Queue is empty.");
            }

        }

        if (ae.getSource() == show) {
            if (!numbers.isEmpty()) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Number at the front of the queue: " + String.valueOf(numbers.returnFront().data));
            } else {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Queue is empty.");
            }
        }

    }

}
