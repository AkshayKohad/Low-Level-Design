"Why do I need Factory Method if Simple Factory already works?"

For small applications, you probably don't need Factory Method. Simple Factory is often enough.

The Factory Method pattern exists mainly to satisfy the Open/Closed Principle:

Software should be open for extension but closed for modification.

Let's see the problem with Simple Factory

Suppose you start with:

class NotificationFactory {

    public static Notification create(String type) {

        if ("EMAIL".equals(type))
            return new EmailNotification();

        if ("SMS".equals(type))
            return new SmsNotification();

        throw new IllegalArgumentException();
    }
}

Client:

Notification notification =
        NotificationFactory.create(type);

Everything looks good.

New Requirement

Tomorrow business says:

Add WhatsApp notification.

You must modify:

class NotificationFactory {

    public static Notification create(String type) {

        if ("EMAIL".equals(type))
            return new EmailNotification();

        if ("SMS".equals(type))
            return new SmsNotification();

        if ("WHATSAPP".equals(type))
            return new WhatsAppNotification();

        throw new IllegalArgumentException();
    }
}

Notice:

Existing code changed.

Every new notification requires touching the factory.

What if this factory becomes huge?

Imagine a framework like Spring, Hibernate, AWS SDK, etc.

if (...)
if (...)
if (...)
if (...)
if (...)
if (...)

Hundreds of product types.

Every new feature means modifying the central factory.

That's risky.

Factory Method Solves This

Instead of:
NotificationFactory.create(type)

you create new factories.

Use Factory Method when
Building frameworks/libraries
New product types are added frequently
You want extension through inheritance
You want to follow Open/Closed Principle


interface Button{
    void render();
}

class WindowsButton implements Button{
    @Override
    public void render(){
        System.out.println("Windows Button rendering");
    }
    
}

class MacButton implements Button{
    @Override 
    public void render(){
        System.out.println("Mac Buttons rendering");
    }
}

abstract class Dialog{
    public abstract Button createButton();
    
    public void show(){
        Button button = createButton();
        button.render();
        System.out.println("Dialog displayed");
    }
}

class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

// Concrete Creator 2
class MacDialog extends Dialog {
    @Override
    public Button createButton() {
        return new MacButton();
    }
}


class Main {
  public static void main(String[] args) {
    Dialog dialog = new WindowsDialog();
    dialog.show();
    
    System.out.println();
    
    dialog = new MacDialog();
    dialog.show();
  }
}
    

    
