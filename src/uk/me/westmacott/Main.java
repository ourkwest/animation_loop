package uk.me.westmacott;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        World myWorld = new AbstractWorld("Test World", 300, 500) {

            double a = 0.0;

            @Override
            public void tick() {
                a += 0.01;
            }

            @Override
            public void render(Graphics2D g2) {
                int x = (int) (100.0 * Math.sin(a));
                int y = (int) (100.0 * Math.cos(a));
                g2.setColor(Color.BLACK);
                g2.drawLine(150, 150, 150 + x, 150 + y);
            }
        };

        new AnimationLoop(myWorld, myWorld).start(30);

    }
}