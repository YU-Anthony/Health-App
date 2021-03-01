import DataWrapper.BaseStation;
import org.eclipse.paho.client.mqttv3.*;
import com.google.gson.Gson;

import java.util.*;


public class Listener implements MqttCallback {
    private MqttClient client;
    private ArrayList<String> topics;

    public Listener() throws MqttException {
        // set up Influx connection


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
        BaseStation bs = g.fromJson(mqttMessage.toString(), BaseStation.class);
        System.out.println("Topic: " + topic + " Msg: " + new String(mqttMessage.getPayload()) + " Json: " + bs.health_score);
        Database.write(bs);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

}
