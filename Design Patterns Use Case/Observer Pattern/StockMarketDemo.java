import java.util.ArrayList;
import java.util.List;

interface StockObserver{
    void update(String stockSymbol, double price);
}

class Investor implements StockObserver{
    private String name;
    public Investor(String name){
        this.name = name;
    }
    @Override
    public void update(String stockSymbol, double price){
        System.out.println("Notification send to " + this.name + " Stock value for " + stockSymbol + " is : " + price);
    }
}

class Stock{
    private final String symbol;
    private double currentPrice;
    private List<StockObserver>subscribers = new ArrayList<>();

    public Stock(String symbol, double currentPrice){
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    void addSubscriber(StockObserver observer){
        if(subscribers.contains(observer)){
            return;
        }
        subscribers.add(observer);
    }

    void removeSubscriber(StockObserver observer){
        if(!subscribers.contains(observer)){
            return;
        }
        subscribers.remove(observer);
    }

    void setPrice(double price){
        this.currentPrice = price;
        notifySubscribers();
    }

    void notifySubscribers(){
        for(StockObserver observer : subscribers){
            observer.update(this.symbol,this.currentPrice);
        }
    }
}

public class StockMarketDemo{
    public static void main(String[] args){
        Stock Apple = new Stock("APPL",100.0);
        StockObserver amit = new Investor("Amit");
        StockObserver rita = new Investor("Rita");

        Apple.addSubscriber(amit);
        Apple.addSubscriber(rita);

        Apple.setPrice(10.0);
        Apple.removeSubscriber(rita);
        Apple.setPrice(120.0);
    }
}
