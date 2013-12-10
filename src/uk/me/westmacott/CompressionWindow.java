package uk.me.westmacott;

class CompressionWindow {

    private final int size;
    private final double scaleFactor;
    private volatile double runningTotal;

    public CompressionWindow(int size, double initialValue) {
        this.size = size;
        this.scaleFactor = size / (size + 1.0);
        this.runningTotal = initialValue * size;
    }

    public void add(double datum) {
        runningTotal += datum;
        runningTotal *= scaleFactor;
    }

    public double currentAverage() {
        return runningTotal / size;
    }

}
