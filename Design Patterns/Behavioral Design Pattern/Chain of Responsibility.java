1. Abstract Handler
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String issue);
}
2. Concrete Handlers
class Level1Support extends SupportHandler {

    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("password reset")) {
            System.out.println("Level 1 Support handled: " + issue);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        }
    }
}
class Level2Support extends SupportHandler {

    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("software installation")) {
            System.out.println("Level 2 Support handled: " + issue);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        }
    }
}
class ManagerSupport extends SupportHandler {

    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("server down")) {
            System.out.println("Manager handled: " + issue);
        } else {
            System.out.println("No handler found for: " + issue);
        }
    }
}
3. Client Code
public class Main {
    public static void main(String[] args) {

        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler manager = new ManagerSupport();

        // Build chain
        level1.setNextHandler(level2);
        level2.setNextHandler(manager);

        level1.handleRequest("password reset");
        level1.handleRequest("software installation");
        level1.handleRequest("server down");
        level1.handleRequest("network issue");
    }
}
Output
Level 1 Support handled: password reset
Level 2 Support handled: software installation
Manager handled: server down
No handler found for: network issue
Flow
Client
  |
  v
Level1 ---> Level2 ---> Manager
   |           |          |
 Handle?    Handle?    Handle?
   |           |          |
  Yes         No        Yes

The key idea is that the client only sends the request to the first handler. Each handler decides:
