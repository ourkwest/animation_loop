package uk.me.westmacott;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

        private static final Map<RenderingHints.Key, Object> HINTS;
        static {
            final Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            HINTS = Collections.unmodifiableMap(hints);
        }

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
            g2.addRenderingHints(HINTS);
            target.render(g2);
        }

    }
}
