package MQTT;

import org.eclipse.paho.client.mqttv3.*;
import params.Params;

import java.sql.Timestamp;

public class MQTTManager implements MQTTService, MqttCallback {

    private final MqttClient mqttClient;
    private final MqttConnectOptions options;

    public MQTTManager(MqttClient mqttClient, MqttConnectOptions options) {
        this.mqttClient = mqttClient;
        this.options = options;
    }

    @Override
    public void connectBroker() {
        try {
            this.options.setUserName(this.mqttClient.getClientId());
            this.options.setCleanSession(true);
            System.out.println("Connecting to broker: "+this.mqttClient.getServerURI());
            this.mqttClient.connect(this.options);
            System.out.println("Connected to broker");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void publish(String key, String value, String topic, int qos) {
        try {
            String content = String.format("{'%1$s':'%2$s'}",key,value);
            System.out.println("Publishing message:"+content);
            MqttMessage mqttMessage = new MqttMessage(content.getBytes());
            mqttMessage.setQos(qos);
            this.mqttClient.publish(topic,mqttMessage);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void subscribe() {
        this.mqttClient.setCallback(this);
        try {
            mqttClient.subscribe(Params.subTopic,0);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void disconnect() {
        try {
            this.mqttClient.disconnect();
            this.mqttClient.close();
            System.exit(0);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message: " + mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
