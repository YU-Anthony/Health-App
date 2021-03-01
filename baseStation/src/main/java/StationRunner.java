import org.eclipse.paho.client.mqttv3.MqttException;

public class StationRunner {
    public static void main(String[] args) throws MqttException {
        Sender sender = new Sender();
        sender.publishData(99, true, true, 99, 99,
                false, false);
    }

}
