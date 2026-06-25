The Visitor Pattern is useful when you want to add new operations to existing classes without modifying those classes.

A classic example is a shopping cart where different items can be visited to calculate their price.

1. Visitor Interface
interface Visitor {
    void visit(Book book);
    void visit(Fruit fruit);
}

The visitor knows how to handle each concrete type.

2. Element Interface
interface Item {
    void accept(Visitor visitor);
}

Every element accepts a visitor.

3. Concrete Elements
Book
class Book implements Item {

    private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
Fruit
class Fruit implements Item {

    private String name;
    private int pricePerKg;
    private int weight;

    public Fruit(String name, int pricePerKg, int weight) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
4. Concrete Visitor

This visitor calculates the total cost.

class PriceCalculatorVisitor implements Visitor {

    private int totalPrice = 0;

    @Override
    public void visit(Book book) {
        System.out.println(
            "Book: " + book.getTitle()
            + ", Price = " + book.getPrice()
        );

        totalPrice += book.getPrice();
    }

    @Override
    public void visit(Fruit fruit) {

        int cost = fruit.getPricePerKg() * fruit.getWeight();

        System.out.println(
            "Fruit: " + fruit.getName()
            + ", Cost = " + cost
        );

        totalPrice += cost;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
5. Client Code
public class Main {

    public static void main(String[] args) {

        Item[] items = {
            new Book("Design Patterns", 500),
            new Fruit("Apple", 100, 2),
            new Fruit("Mango", 150, 3)
        };

        PriceCalculatorVisitor visitor =
                new PriceCalculatorVisitor();

        for (Item item : items) {
            item.accept(visitor);
        }

        System.out.println(
            "\nTotal Price = " + visitor.getTotalPrice()
        );
    }
}
Output
Book: Design Patterns, Price = 500
Fruit: Apple, Cost = 200
Fruit: Mango, Cost = 450

Total Price = 1150
