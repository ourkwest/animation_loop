package uk.me.westmacott;

class AnimationLoop {

    private final AnimationView renderable;
    private final Runnable tickable;

    public AnimationLoop(final World world) {
        renderable = new AnimationView(world);
        tickable = new Runnable() {
            @Override
            public void run() {
                world.tick();
            }
        };
    }

    public AnimationLoop renderAt(int framesPerSecond) {
        Thread thread = new Thread(new Metronome(renderable, framesPerSecond));
        thread.start();
        return this;
    }

    public AnimationLoop tickAt(int framesPerSecond) {
        Thread thread = new Thread(new Metronome(tickable, framesPerSecond));
        thread.start();
        return this;
    }

}
