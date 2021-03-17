import DataWrapper.BaseStation;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Random;

public class Sender {
    private MqttClient client;

    public Sender() throws MqttException {
        String host = "tcp://127.0.0.1:1883";
        String clientID = "BaseStationPublisher";

        this.client = new MqttClient(host, clientID);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);

        this.client.connect(connOpts);
    }

    public void publishData(Random r) {
        Gson g = new Gson();
        int hs = (int) (r.nextGaussian() * 20) + 70;
        boolean sit_status = hs >= 80;
        System.out.println("Generating ... sample: " + hs);

        BaseStation bs = new BaseStation(hs, sit_status, false, false,
                true, true, false);
        byte[] jsonObject = g.toJson(bs).getBytes();
        MqttMessage msg = new MqttMessage(jsonObject);
        try {
            client.publish("pttData", msg);
        } catch (Exception e){
            System.out.println("That's weird !");
        }
    }
}
