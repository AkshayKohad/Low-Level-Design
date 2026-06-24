A classic example of the Mediator Pattern is a chat room where users do not communicate with each other directly. Instead, they send messages through a central ChatRoom (Mediator).

1. Mediator Interface
interface ChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}
2. Concrete Mediator
import java.util.ArrayList;
import java.util.List;

class ChatRoom implements ChatMediator {

    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receive(message, sender.getName());
            }
        }
    }
}
3. Colleague (Abstract User)
abstract class User {

    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void send(String message);

    public abstract void receive(String message, String sender);
}
4. Concrete User
class ChatUser extends User {

    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message, String sender) {
        System.out.println(name + " received from "
                + sender + ": " + message);
    }
}
5. Client Code
public class Main {

    public static void main(String[] args) {

        ChatMediator chatRoom = new ChatRoom();

        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        User charlie = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.send("Hello everyone!");
        System.out.println();

        bob.send("Hi Alice!");
    }
}
Output
Alice sends: Hello everyone!
Bob received from Alice: Hello everyone!
Charlie received from Alice: Hello everyone!

Bob sends: Hi Alice!
Alice received from Bob: Hi Alice!
Charlie received from Bob: Hi Alice!
Structure
                +------------------+
                |   ChatMediator   |
                +------------------+
                         ^
                         |
                +------------------+
                |    ChatRoom      |
                +------------------+
                  ^      ^      ^
                  |      |      |
             Alice     Bob   Charlie
