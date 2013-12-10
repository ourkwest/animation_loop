package uk.me.westmacott;

import java.awt.*;

interface World {

    public String getName();

    public Dimension getSize();

    public void tick();

    public void render(Graphics2D g2);

}
