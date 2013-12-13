package uk.me.westmacott.world;

import uk.me.westmacott.world.World;

import java.awt.*;

public abstract class AbstractWorld implements World {

    private final Dimension size;
    private String name;

    public AbstractWorld(String name, int width, int height) {
        this.size = new Dimension(width, height);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Dimension getSize() {
        return size;
    }
}
