import MQTT.MQTTManager;
import MQTT.MQTTService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import params.Params;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MqttException, IOException {
        Scanner scanner = new Scanner(System.in);
        String broker = Params.broker;
        String clientId = Params.clientId;
        String topic = Params.topic;
        int qos = 0;
        MemoryPersistence persistence = new MemoryPersistence();

        MQTTService mqttService = new MQTTManager(new MqttClient(broker,clientId,persistence),new MqttConnectOptions());
        mqttService.connectBroker();

        while (true){
            System.out.println("1-Publish   2-Subscribe");
            System.out.print("Select: ");
            String selector = scanner.nextLine();
            switch (selector){
                case "1":
                    while (true){
                        System.out.print("Key :");
                        String key = scanner.nextLine();
                        if (key.equals("***")){
                            break;
                        }

                        System.out.print("Value : ");
                        String value = scanner.nextLine();
                        if (value.equals("***")){
                            break;
                        }

                        mqttService.publish(key,value,topic,qos);
                    }
                    break;
                case "2":
                    mqttService.subscribe();
                    String outSubscribe = scanner.nextLine();
                    if (outSubscribe.equals("***")){
                        break;
                    }
            }
            if (selector.equals("exit")){
                break;
            }
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
