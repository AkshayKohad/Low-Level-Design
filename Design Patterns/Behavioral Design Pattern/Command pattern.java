A classic example of the Command Pattern is a remote control where:

Receiver → Device that performs the action (TV).
Command → Encapsulates a request (TurnOn, TurnOff).
Invoker → Remote control.
Client → Creates and wires everything together.
1. Command Interface
interface Command {
    void execute();
}
2. Receiver

This is the actual object that knows how to perform the work.

class TV {

    public void turnOn() {
        System.out.println("TV is ON");
    }

    public void turnOff() {
        System.out.println("TV is OFF");
    }
}
3. Concrete Commands

Each command wraps a request.

class TurnOnCommand implements Command {

    private TV tv;

    public TurnOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
}
class TurnOffCommand implements Command {

    private TV tv;

    public TurnOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}
4. Invoker

The invoker doesn't know how the action is performed.

class RemoteControl {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
5. Client
public class Main {

    public static void main(String[] args) {

        TV tv = new TV();

        Command onCommand = new TurnOnCommand(tv);
        Command offCommand = new TurnOffCommand(tv);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(onCommand);
        remote.pressButton();

        remote.setCommand(offCommand);
        remote.pressButton();
    }
}
Output
TV is ON
TV is OFF
