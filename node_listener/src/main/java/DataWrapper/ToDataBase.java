package DataWrapper;

public class ToDataBase {
    public int healthScore;
    public boolean isSitting;
    public boolean sedentary;
    public boolean isAPBalanced;
    public boolean isLRBalanced;
    public boolean slowRising;
    public boolean lackingShift;
    public boolean isSittingRight;

    public ToDataBase(int healthScore, boolean isSitting, boolean sedentary, boolean isAPBalanced, boolean isLRBalanced, boolean slowRising, boolean lackingShift, boolean isSittingRight) {
        this.healthScore = healthScore;
        this.isSitting = isSitting;
        this.sedentary = sedentary;
        this.isAPBalanced = isAPBalanced;
        this.isLRBalanced = isLRBalanced;
        this.slowRising = slowRising;
        this.lackingShift = lackingShift;
        this.isSittingRight = isSittingRight;
    }
}
