package uk.me.westmacott.world;

import java.awt.*;

public interface Renderable {

    String getName();

    Dimension getSize();

    void render(Graphics2D g2);

}
