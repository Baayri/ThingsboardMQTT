package MQTT;

import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public interface MQTTService {
    void connectBroker();
    void publish(String key, String value, String topic, int qos);
    void subscribe();
    void disconnect();
}
