package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Breakout {
    private BreakoutPanel p;
    private Timer t;
    private boolean alive;
    public Breakout() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        frame.add(p);
        frame.pack();
        frame.setVisible(true);
        t.start();
    }

    private void initComponents(){
        final ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.moveEntities();
                p.revalidate();
                p.repaint();
            }
        };
        t = new Timer(20, action);
        p = new BreakoutPanel();
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                p.addEntity(e.getX(), e.getY(), 50, 10, Color.GREEN);
                System.out.println("Clicked");
            }
        });

        p.setFocusable(true);
        p.grabFocus();
        p.addKeyListener(p);

        p.setBackground(Color.BLACK);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Breakout();
            }
        });
    }
}









