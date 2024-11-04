import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel implements ActionListener {
    private Timer timer;
    private double angle = 0; // Угол для орбитального движения
    private int a = 150; // Большая полуось эллипса
    private int b = 100; // Малая полуось эллипса
    private int centerX = 300; // Центр планеты по X
    private int centerY = 300; // Центр планеты по Y
    private int radiusPlanet = 50; // Радиус планеты

    public Main() {
        timer = new Timer(50, this);
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(centerX - radiusPlanet, centerY - radiusPlanet, 2 * radiusPlanet, 2 * radiusPlanet);
        int satelliteX = (int) (centerX + a * Math.cos(angle));
        int satelliteY = (int) (centerY + b * Math.sin(angle));
        double distance = Math.sqrt(Math.pow(satelliteX - centerX, 2) + Math.pow(satelliteY - centerY, 2));
        if (distance > radiusPlanet) {
            g.setColor(Color.RED);
            g.fillOval(satelliteX - 10, satelliteY - 10, 20, 20);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 0.05;
        if (angle > 2 * Math.PI) {
            angle = 0;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Satellite");
        Main simulation = new Main();
        frame.add(simulation);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}