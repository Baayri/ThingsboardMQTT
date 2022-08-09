package initiater;

import helloListener.HelloListener;

import java.util.ArrayList;

public class Initiater {
    String name;
    public Initiater(String name) {
        this.name = name;
    }
    ArrayList<HelloListener> listeners = new ArrayList<>();
    public void addListener(HelloListener helloListener){
        listeners.add(helloListener);
    }
    public void sayHello(){
        for (HelloListener listener: listeners) {
            listener.someoneSaidHello();
        }
    }
    public boolean isEmpty(HelloListener listener){
        for (HelloListener myListener: listeners) {
            if (myListener.getName().equals(listener.getName())){
                return false;
            }
        }
        return true;
    }
}
