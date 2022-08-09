package responder;

import helloListener.HelloListener;

public class Responder implements HelloListener {
    String name;
    public Responder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public void someoneSaidHello() {
        System.out.println("Hello " + this.name);
    }
}
