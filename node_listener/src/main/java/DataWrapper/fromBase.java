package DataWrapper;

public class fromBase {
    public int[] sensorValues = new int[8];
    public boolean isSitting;
    public boolean slowRising;

    public fromBase(int[] sensorValues, boolean isSitting,boolean slowRising) {
        this.sensorValues = sensorValues;
        this.isSitting = isSitting;
        this.slowRising = slowRising;
    }
}
