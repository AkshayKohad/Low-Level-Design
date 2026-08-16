abstract class SupportHandler{
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    abstract public void handleTicket(SupportTicket ticket);

    protected void forwardTicket(SupportTicket ticket){
        if(nextHandler != null){
            nextHandler.handleTicket(ticket);
        }else{
            System.out.println("No support handler can resolve: " + ticket.getDescription());
        }
    }
}

enum SeverityType{
    LOW,
    MEDIUM,
    HIGH
}
class SupportTicket{
    private String description;
    private SeverityType severity;

    public SupportTicket(String description, SeverityType severity){
        this.description = description;
        this.severity = severity;
    }

    public String getDescription(){
        return description;
    }

    public SeverityType getSeverity(){
        return severity;
    }
}

class Level1Support extends SupportHandler{
    @Override
    public void handleTicket(SupportTicket ticket){
        if(ticket.getSeverity() == SeverityType.LOW){
            System.out.println("Issue is resolved by L1 Support handler");
        }else{
            forwardTicket(ticket);
        }
    }
}


class Level2Support extends SupportHandler{
    @Override
    public void handleTicket(SupportTicket ticket){
        if(ticket.getSeverity() == SeverityType.MEDIUM){
            System.out.println("Issue is resolved by L2 Support handler");
        }else{
            forwardTicket(ticket);
        }
    }
}


class Level3Support extends SupportHandler{
    @Override
    public void handleTicket(SupportTicket ticket){
        if(ticket.getSeverity() == SeverityType.HIGH){
            System.out.println("Issue is resolved by L3 Support handler");
        }else{
            System.out.println("Wrong Issue");
        }
    }
}


public class SupportEscalationDemo{
    public static void main(String[] args){
        SupportTicket ticket = new SupportTicket("Ticket raised for low issue",SeverityType.LOW);
        SupportHandler L1Support = new Level1Support();
        SupportHandler L2Support = new Level2Support();
        SupportHandler L3Support = new Level3Support();

        L1Support.setNextHandler(L2Support);
        L2Support.setNextHandler(L3Support);

        L1Support.handleTicket(
                new SupportTicket("Unable to reset password", SeverityType.LOW)
        );
        L1Support.handleTicket(
                new SupportTicket("Payment pending", SeverityType.MEDIUM)
        );
        L1Support.handleTicket(
                new SupportTicket("Payment deducted twice", SeverityType.HIGH)
        );
    }
}
