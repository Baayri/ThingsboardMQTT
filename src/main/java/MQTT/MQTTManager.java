package MQTT;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTManager implements MQTTService {

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
            String content = "{'" +  key + "' : '"+ value + "'}";
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
}
