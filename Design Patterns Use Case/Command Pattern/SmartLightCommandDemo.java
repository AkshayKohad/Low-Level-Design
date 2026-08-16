import java.util.ArrayDeque;
import java.util.Deque;

interface Command {
    void execute();
    void undo();
}

class Light {
    private boolean onStatus;

    public void turnOn() {
        setOn(true);
    }

    public void turnOff() {
        setOn(false);
    }

    public void setOn(boolean onStatus) {
        this.onStatus = onStatus;
        System.out.println("Light is " + (onStatus ? "ON" : "OFF"));
    }

    public boolean isOn() {
        return onStatus;
    }
}

class TurnOnLightCommand implements Command {
    private final Light light;
    private boolean previousState;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        previousState = light.isOn();
        light.turnOn();
    }

    @Override
    public void undo() {
        light.setOn(previousState);
    }
}

class TurnOffLightCommand implements Command {
    private final Light light;
    private boolean previousState;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        previousState = light.isOn();
        light.turnOff();
    }

    @Override
    public void undo() {
        light.setOn(previousState);
    }
}

class RemoteControl {
    private final Deque<Command> commandHistory = new ArrayDeque<>();

    public void executeCommand(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (commandHistory.isEmpty()) {
            throw new IllegalStateException("No command has been executed to undo");
        }
        commandHistory.pop().undo();
    }
}

public class SmartLightCommandDemo {
    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl remote = new RemoteControl();

        remote.executeCommand(new TurnOnLightCommand(light));
        remote.executeCommand(new TurnOffLightCommand(light));
        remote.undoLastCommand();
    }
}
