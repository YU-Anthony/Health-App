package DataWrapper;

public class FromBaseOriginal {
    public int health_score;
    public boolean sit_status;
    public boolean sedentary;
    public boolean concentrated_pressure;
    public boolean unbalanced_pressure;
    public boolean lacking_shift;
    public boolean slow_rising;

    public FromBaseOriginal(int health_score, boolean sit_status, boolean sedentary, boolean concentrated_pressure,
                            boolean unbalanced_pressure, boolean lacking_shift, boolean slow_rising) {
        this.health_score = health_score;
        this.sit_status = sit_status;
        this.sedentary = sedentary;
        this.concentrated_pressure = concentrated_pressure;
        this.unbalanced_pressure = unbalanced_pressure;
        this.lacking_shift = lacking_shift;
        this.slow_rising = slow_rising;
    }
}
