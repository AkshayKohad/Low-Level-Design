// Subsystem 1
class MenuService {

    public void showMenu() {
        System.out.println("Showing food menu...");
    }
}

// Subsystem 2
class OrderService {

    public void placeOrder(String item) {
        System.out.println("Order placed for: " + item);
    }
}

// Subsystem 3
class PaymentService {

    public void makePayment(double amount) {
        System.out.println("Payment of $" + amount + " completed.");
    }
}

// Subsystem 4
class KitchenService {

    public void prepareFood(String item) {
        System.out.println("Preparing food: " + item);
    }
}

// Subsystem 5
class DeliveryService {

    public void deliverFood(String item) {
        System.out.println("Delivering food: " + item);
    }
}

class FoodOrderFacade {

    private MenuService menuService;
    private OrderService orderService;
    private PaymentService paymentService;
    private KitchenService kitchenService;
    private DeliveryService deliveryService;

    public FoodOrderFacade() {
        this.menuService = new MenuService();
        this.orderService = new OrderService();
        this.paymentService = new PaymentService();
        this.kitchenService = new KitchenService();
        this.deliveryService = new DeliveryService();
    }

    public void orderFood(String item, double amount) {
        menuService.showMenu();
        orderService.placeOrder(item);
        paymentService.makePayment(amount);
        kitchenService.prepareFood(item);
        deliveryService.deliverFood(item);

        System.out.println("Food order completed successfully.");
    }
}

public class FacadePatternDemo {

    public static void main(String[] args) {

        FoodOrderFacade foodOrder = new FoodOrderFacade();

        foodOrder.orderFood("Pizza", 12.99);
    }
}
