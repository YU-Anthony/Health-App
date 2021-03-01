import org.eclipse.paho.client.mqttv3.MqttException;

public class ServerRunner {
    public static void main(String[] args) throws MqttException {
        Listener lis = new Listener();
    }
}
