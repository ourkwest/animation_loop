package uk.me.westmacott;

import javax.swing.*;
import java.awt.*;

class AnimationLoop implements Runnable {

    private final MyPanel panel;
    private final World world;
    private final int framesPerSecond;

    public AnimationLoop(World world, int framesPerSecond) {
        this.world = world;
        this.panel = new MyPanel(world);
        this.framesPerSecond = framesPerSecond;
    }

    public void start() {
        JFrame frame = new JFrame(world.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this.panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        Thread thread = new Thread(new Metronome(this, framesPerSecond));
        thread.start();
    }

    @Override
    public void run() {
        this.world.tick();
        this.panel.repaint();
    }

    private static class MyPanel extends JPanel {

        private final Dimension size;
        private World target;

        private MyPanel(World target) {
            this.target = target;
            this.size = target.getSize();
            setPreferredSize(size);
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, size.width, size.height);
            target.render(g2);
        }

    }
}
