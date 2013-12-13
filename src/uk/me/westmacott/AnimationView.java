package uk.me.westmacott;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AnimationView extends JFrame implements Runnable {

    private static final Map<RenderingHints.Key, Object> HINTS;
    static {
        final Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        HINTS = Collections.unmodifiableMap(hints);
    }

    public AnimationView(Renderable renderable) throws HeadlessException {
        super(renderable.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new AnimationPanel(renderable), BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    @Override
    public void run() {
        repaint();
    }

    private static class AnimationPanel extends JPanel {

        private final Dimension size;
        private Renderable target;

        private AnimationPanel(Renderable target) {
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

