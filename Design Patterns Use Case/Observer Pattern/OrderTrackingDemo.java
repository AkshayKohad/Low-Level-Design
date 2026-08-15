import java.util.ArrayList;
import java.util.List;

interface OrderObserver{
    void update(String orderId,OrderStatus status);
}

enum OrderStatus{
    PLACED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED
}

class Customer implements OrderObserver{
    private final String name;
    public Customer(String name){
        this.name = name;
    }
    @Override
    public void update(String orderId,OrderStatus status){
        System.out.println(this.name + " Order update for " + orderId + " is " + status);
    }
}

class Order{
    private final String orderId;
    private OrderStatus status;
    private List<OrderObserver>subscribers = new ArrayList<>();

    public Order(String orderId, OrderStatus status){
        this.orderId = orderId;
        this.status = status;
    }

    public void addCustomer(OrderObserver observer){
        if(subscribers.contains(observer)){
            return;
        }
        subscribers.add(observer);
    }

    public void removeCustomer(OrderObserver observer){
        if(!subscribers.contains(observer)){
            return;
        }
        subscribers.remove(observer);
    }

    public void updateStatus(OrderStatus newStatus){
        if (newStatus == null) {
            throw new IllegalArgumentException("Order status cannot be null");
        }
        if (newStatus == status) {
            return;
        }
        this.status = newStatus;
        notifySubscribers();
    }

    public void notifySubscribers(){
        for(OrderObserver observer : subscribers){
            observer.update(orderId,status);
        }
    }
}

public class OrderTrackingDemo{
    public static void main(String[] args){
        Order order = new Order("1234",OrderStatus.PLACED);
        Customer ak = new Customer("AK");
        Customer rita = new Customer("Rita");
        order.addCustomer(ak);
        order.addCustomer(rita);

        order.updateStatus(OrderStatus.CONFIRMED);
        order.removeCustomer(rita);
        order.updateStatus(OrderStatus.SHIPPED);
    }
}
