interface Notification{
    void send(String message);
}

class EmailNotification implements Notification{
    @Override
    public void send(String message){
        System.out.println("Email sent: " + message);
    }
}

class SmsNotification implements Notification{
    @Override
    public void send(String message){
        System.out.println("SMS sent: " + message);
    }
}

class PushNotification implements Notification{
    @Override
    public void send(String message){
        System.out.println("Push notification sent: " + message);
    }
}

enum NotificationType {
    EMAIL, SMS, PUSH
}

class NotificationFactory {

    public Notification createNotification(NotificationType type){
        if (type == null) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }
        switch(type){
            case SMS:
                return new SmsNotification();
            case EMAIL:
                return new EmailNotification();
            case PUSH:
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unsupported notification type: " + type);
        }
    }
}

class NotificationService{
    private final NotificationFactory factory = new NotificationFactory();
    public void sendNotification(NotificationType type,String message){
        Notification notificationService = factory.createNotification(type);
        notificationService.send(message);
    }
}

public class NotificationServiceDemo{
    public static void main(String[] args){
        NotificationService notificationService = new NotificationService();
        notificationService.sendNotification(NotificationType.SMS, "Hello World");
        notificationService.sendNotification(NotificationType.EMAIL, "Your order has been placed.");
        notificationService.sendNotification(NotificationType.PUSH, "A new update is available.");
    }
}
