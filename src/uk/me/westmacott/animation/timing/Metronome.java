package uk.me.westmacott.animation.timing;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Metronome implements Runnable {

    private final Runnable runnable;
    private final long periodMillis;
    private final int windowSize;

    public Metronome(Runnable runnable, double hertz) {
        this.runnable = runnable;
        this.periodMillis = (long) (1000.0 / hertz);
        this.windowSize = (int) Math.ceil(hertz);
    }

    @Override
    public void run() {
        CompressionWindow window = monitoredWindow();
        long due = System.currentTimeMillis();
        while (true) {
            this.runnable.run();
            due += this.periodMillis;
            window.add(due - System.currentTimeMillis());
            sleepUntil(due);
        }
    }

    private CompressionWindow monitoredWindow() {
        final CompressionWindow window = new CompressionWindow(windowSize, periodMillis);
        Runnable monitor = new Runnable() {
            @Override
            public void run() {
                System.out.format(
                        "Spare milliseconds per frame: %1$.2f / %2$d \n",
                        window.currentAverage(),
                        periodMillis);
            }
        };
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                monitor,
                1,
                1,
                TimeUnit.SECONDS);
        return window;
    }

    private long timeUntil(long due) {
        return due - System.currentTimeMillis();
    }

    private void sleepUntil(long due) {
        for (long pause = timeUntil(due); pause > 0; pause = timeUntil(due)) {
            try {
                Thread.sleep(pause);
                return;
            }
            catch (InterruptedException e) { /* no-op */ }
        }
    }

}
