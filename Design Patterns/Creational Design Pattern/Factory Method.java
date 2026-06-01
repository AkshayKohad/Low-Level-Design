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
