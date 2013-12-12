package uk.me.westmacott;

import java.awt.*;


public interface Renderable {

    String getName();

    Dimension getSize();

    void render(Graphics2D g2);

}
