A simple example of the Observer Pattern is a news website where users subscribe and get notified whenever a new article is published.

1. Observer Interface
interface Observer {
    void update(String article);
}
2. Subject Interface
interface Subject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers();
}
3. Concrete Subject (News Website)
import java.util.ArrayList;
import java.util.List;

class NewsWebsite implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private String latestArticle;

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(latestArticle);
        }
    }

    public void publishArticle(String article) {
        this.latestArticle = article;
        System.out.println("\nNew article published: " + article);

        notifyObservers();
    }
}
4. Concrete Observer (Subscriber)
class Subscriber implements Observer {

    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String article) {
        System.out.println(
            name + " received notification: " + article
        );
    }
}
5. Client Code
public class Main {

    public static void main(String[] args) {

        NewsWebsite website = new NewsWebsite();

        Observer alice = new Subscriber("Alice");
        Observer bob = new Subscriber("Bob");
        Observer charlie = new Subscriber("Charlie");

        website.subscribe(alice);
        website.subscribe(bob);
        website.subscribe(charlie);

        website.publishArticle("Observer Pattern Explained");

        website.unsubscribe(bob);

        website.publishArticle("Java Design Patterns Guide");
    }
}
Output
New article published: Observer Pattern Explained
Alice received notification: Observer Pattern Explained
Bob received notification: Observer Pattern Explained
Charlie received notification: Observer Pattern Explained

New article published: Java Design Patterns Guide
Alice received notification: Java Design Patterns Guide
Charlie received notification: Java Design Patterns Guide
