import DataWrapper.BaseStation;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;

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

    public void publishData(int health_score, boolean sit_status, boolean sedentary, int concentrated_pressure,
                            int unbalanced_pressure, boolean lacking_shift, boolean slow_rising) {
        Gson g = new Gson();
        BaseStation bs = new BaseStation(health_score, sit_status, sedentary, concentrated_pressure,
                unbalanced_pressure, lacking_shift, slow_rising);
        byte[] jsonObject = g.toJson(bs).getBytes();
        MqttMessage msg = new MqttMessage(jsonObject);
        try {
            client.publish("pttData", msg);
        } catch (Exception e){
            System.out.println("That's weird !");
        }
    }
}
