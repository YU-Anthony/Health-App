package DataWrapper;

public class FromBase {
    public int[] sensorValues;
    public boolean isSitting;
    public boolean slowRising;

    public FromBase(int[] sensorValues, boolean isSitting, boolean slowRising) {
        this.sensorValues = sensorValues;
        this.isSitting = isSitting;
        this.slowRising = slowRising;
    }
}
