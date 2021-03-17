import DataWrapper.FromBase;
import DataWrapper.FromBaseOriginal;
import DataWrapper.ToDataBase;

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

    // check whether the user is sitting right, return a boolean array with
    // [isLR, isAP, isRight]
    static boolean[] isSittingRight(int[] sensorValues) {
        boolean[] ans = new boolean[3];
        double sum = Arrays.stream(sensorValues).sum();
        ans[0] = isAPBalanced(sensorValues, sum);
        ans[1] = isLRBalanced(sensorValues, sum);
        ans[2] = ans[0] && ans[1];
        return ans;
    }

    static double se(int a, int b) {
        return Math.pow((a-b), 2);
    }

    // get health score of a client
//    static ToDataBase getHealthScore(FromBase fb) {
//        int[] sensorValues = fb.sensorValues;
//        boolean[] sittingStatus = isSittingRight(sensorValues);
//    }

    static  ToDataBase analyseInsert(FromBaseOriginal fb) {
        boolean isSittingRight = !(fb.concentrated_pressure || fb.unbalanced_pressure);
        return new ToDataBase(fb.health_score, fb.sit_status, fb.sedentary, !fb.concentrated_pressure, !fb.unbalanced_pressure,
                fb.slow_rising, fb.lacking_shift, isSittingRight);
    }
}
