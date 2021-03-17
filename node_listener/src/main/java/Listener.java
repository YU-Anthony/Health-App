import DataWrapper.FromBase;
import DataWrapper.FromBaseOriginal;
import DataWrapper.ToDataBase;
import org.eclipse.paho.client.mqttv3.*;
import com.google.gson.Gson;

import javax.xml.crypto.Data;
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
//        FromBase bs = g.fromJson(mqttMessage.toString(), FromBase.class);
//        System.out.println("Topic: " + topic + " Msg: " + new String(mqttMessage.getPayload()) + " Json: " + Arrays.toString(bs.sensorValues));
//        Database.write(bs);
        FromBaseOriginal bso = g.fromJson(mqttMessage.toString(), FromBaseOriginal.class);
//        System.out.println("Msg: " + new String(mqttMessage.getPayload()));
        ToDataBase tb = MsgAnalysis.analyseInsert(bso);
        Database.write(tb);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

}
