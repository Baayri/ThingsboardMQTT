import MQTT.MQTTManager;
import MQTT.MQTTService;
import initiater.Initiater;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import params.Params;
import responder.Responder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MqttException {
        Scanner scanner = new Scanner(System.in);
        String broker = Params.broker;
        String clientId = Params.clientId;
        String topic = Params.topic;
        int qos = 0;
        MemoryPersistence persistence = new MemoryPersistence();

        MQTTService mqttService = new MQTTManager(new MqttClient(broker,clientId,persistence),new MqttConnectOptions());
        mqttService.connectBroker();
        while (true){
            System.out.print("Key :");
            String key = scanner.next();
            if (key.equals("***")){
                break;
            }

            System.out.print("Value : ");
            String value = scanner.next();
            if (value.equals("***")){
                break;
            }

            mqttService.publish(key,value,topic,qos);
        }

        mqttService.disconnect();


//-----------------------------------------------------------------------------
//        Scanner scanner = new Scanner(System.in);
//
//        Initiater initiater = new Initiater("initiater");
//
//        while (true){
//            System.out.print("Responder : ");
//            String name = scanner.next();
//            Responder responder = new Responder(name);
//
//            if (name.equals("exit")) {
//                break;
//            }
//            else if (initiater.isEmpty(responder)){
//                initiater.addListener(responder);
//            }
//            else if(!initiater.isEmpty(responder)){
//                System.out.println(name +" already used");
//            }
//        }
//        initiater.sayHello();
    }
}
