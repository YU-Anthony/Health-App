import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Random;
import java.util.concurrent.*;

public class StationRunner {
    private static int count = 0;
    private static final Random r = new Random();
    public static void main(String[] args) throws MqttException {
        Sender sender = new Sender();
        ScheduledExecutorService exs = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            sender.publishData(r);
            count ++;
        };
        ScheduledFuture<?> scheduledFuture = exs.scheduleAtFixedRate(task, 3, 1, TimeUnit.SECONDS);
        while (true) {
            if (count >= 1000) {
                System.out.println("Reach 1000 random samples <: exit");
                scheduledFuture.cancel(true);
                exs.shutdown();
                break;
            }
        }
    }

}
