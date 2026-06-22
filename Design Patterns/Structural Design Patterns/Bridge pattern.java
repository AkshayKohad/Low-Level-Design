The idea is:

Remote control = abstraction
Air conditioner = implementation

The remote can change independently, and the AC type can also change independently.

// Implementation interface
interface AirConditioner {
    void turnOn();
    void turnOff();
    void setTemperature(int temperature);
}
// Concrete implementation 1
class SamsungAC implements AirConditioner {

    @Override
    public void turnOn() {
        System.out.println("Samsung AC is turned ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Samsung AC is turned OFF");
    }

    @Override
    public void setTemperature(int temperature) {
        System.out.println("Samsung AC temperature set to " + temperature + "°C");
    }
}
// Concrete implementation 2
class LGAC implements AirConditioner {

    @Override
    public void turnOn() {
        System.out.println("LG AC is turned ON");
    }

    @Override
    public void turnOff() {
        System.out.println("LG AC is turned OFF");
    }

    @Override
    public void setTemperature(int temperature) {
        System.out.println("LG AC temperature set to " + temperature + "°C");
    }
}

Now create the remote abstraction:

// Abstraction
abstract class RemoteControl {

    protected AirConditioner airConditioner;

    public RemoteControl(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    public abstract void turnOn();

    public abstract void turnOff();

    public abstract void setTemperature(int temperature);
}
// Refined abstraction 1
class BasicRemote extends RemoteControl {

    public BasicRemote(AirConditioner airConditioner) {
        super(airConditioner);
    }

    @Override
    public void turnOn() {
        airConditioner.turnOn();
    }

    @Override
    public void turnOff() {
        airConditioner.turnOff();
    }

    @Override
    public void setTemperature(int temperature) {
        airConditioner.setTemperature(temperature);
    }
}
// Refined abstraction 2
class SmartRemote extends RemoteControl {

    public SmartRemote(AirConditioner airConditioner) {
        super(airConditioner);
    }

    @Override
    public void turnOn() {
        System.out.println("Smart remote connecting to AC...");
        airConditioner.turnOn();
    }

    @Override
    public void turnOff() {
        System.out.println("Smart remote disconnecting from AC...");
        airConditioner.turnOff();
    }

    @Override
    public void setTemperature(int temperature) {
        System.out.println("Smart remote using app control...");
        airConditioner.setTemperature(temperature);
    }

    public void enableSleepMode() {
        System.out.println("Sleep mode enabled");
        airConditioner.setTemperature(24);
    }
}

Client code:

public class BridgePatternDemo {

    public static void main(String[] args) {

        AirConditioner samsungAC = new SamsungAC();
        RemoteControl basicRemote = new BasicRemote(samsungAC);

        basicRemote.turnOn();
        basicRemote.setTemperature(22);
        basicRemote.turnOff();

        System.out.println();

        AirConditioner lgAC = new LGAC();
        SmartRemote smartRemote = new SmartRemote(lgAC);

        smartRemote.turnOn();
        smartRemote.setTemperature(20);
        smartRemote.enableSleepMode();
        smartRemote.turnOff();
    }
}

Output:

Samsung AC is turned ON
Samsung AC temperature set to 22°C
Samsung AC is turned OFF

Smart remote connecting to AC...
LG AC is turned ON
Smart remote using app control...
LG AC temperature set to 20°C
Sleep mode enabled
LG AC temperature set to 24°C
Smart remote disconnecting from AC...
LG AC is turned OFF

Here:

AirConditioner is the implementation interface.

SamsungAC and LGAC are concrete implementations.

RemoteControl is the abstraction.

BasicRemote and SmartRemote are refined abstractions.

Because of the Bridge Pattern, you can add a new remote without changing AC classes, and you can add a new AC brand without changing remote classes.
