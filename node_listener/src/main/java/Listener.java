import DataWrapper.fromBase;
import org.eclipse.paho.client.mqttv3.*;
import com.google.gson.Gson;

import java.util.*;


public class Listener implements MqttCallback {
    private MqttClient client;
    private ArrayList<String> topics;

    public Listener() throws MqttException {
        // set up MQTT client
        String host = "tcp://127.0.0.1:1883";
        String clientID = "MockReceiver";
        topics = new ArrayList<>();
        topics.add("pttData");

        this.client = new MqttClient(host, clientID);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);

        this.client.setCallback(this);
        this.client.connect(connOpts);
        for (String topic : topics) {
            client.subscribe(topic);
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("The connection was lost :( due to " + throwable);
        System.exit(1);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        Gson g = new Gson();
        fromBase bs = g.fromJson(mqttMessage.toString(), fromBase.class);
//        System.out.println("Topic: " + topic + " Msg: " + new String(mqttMessage.getPayload()) + " Json: " + Arrays.toString(bs.sensorValues));
//        Database.write(bs);
        boolean isSittingRight = MsgAnalysis.isSittingRight(bs.sensorValues);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

}
