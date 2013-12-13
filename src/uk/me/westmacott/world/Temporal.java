package uk.me.westmacott.world;

public interface Temporal {

    /**
     * Like Runnable.run() but expected to be called repeatedly.
     */
    void tick();

}
