import java.util.Arrays;

public class MsgAnalysis {

    static final double LRT = 20.0;
    static final double APT = 10.0;
    // sensor values distribution:
    //      L        R
    // A   3 2      5 4
    // P   7 6      0 1

    // sensor values: [3, 5, 2, 4, 7, 1, 6, 0]

    // check whether the user is not leaning to left or right.
    static boolean isLRBalanced(int[] sensorValues, double sum) {
        double mse = (se(sensorValues[2], sensorValues[5]) + se(sensorValues[6], sensorValues[0]) +
                      se(sensorValues[3], sensorValues[4]) + se(sensorValues[7], sensorValues[0])) / sum;
        return LRT > mse;
    }

    // check whether the user is not leaning to posterior or anterior
    static boolean isAPBalanced(int[] sensorValues, double sum) {
        double mse = (se(sensorValues[3], sensorValues[7]) + se(sensorValues[2], sensorValues[6]) +
                se(sensorValues[5], sensorValues[0]) + se(sensorValues[4], sensorValues[1])) / sum;
        return APT > mse;
    }

    // check whether the user is sitting right
    static boolean isSittingRight(int[] sensorValues) {
        double sum = Arrays.stream(sensorValues).sum();
        return isAPBalanced(sensorValues, sum) && isLRBalanced(sensorValues, sum);
    }

    static double se(int a, int b) {
        return Math.pow((a-b), 2);
    }
}
