// Strategy interface
interface RouteStrategy {
    void calculateRoute(String startLocation, String endLocation);
}

// Concrete Strategy: Fastest Route
class FastestRoute implements RouteStrategy {

    @Override
    public void calculateRoute(String startLocation, String endLocation) {
        System.out.println("Calculating fastest route from "
                + startLocation + " to " + endLocation);
    }
}

// Concrete Strategy: Shortest Route
class ShortestRoute implements RouteStrategy {

    @Override
    public void calculateRoute(String startLocation, String endLocation) {
        System.out.println("Calculating shortest route from "
                + startLocation + " to " + endLocation);
    }
}

// Concrete Strategy: Scenic Route
class ScenicRoute implements RouteStrategy {

    @Override
    public void calculateRoute(String startLocation, String endLocation) {
        System.out.println("Calculating scenic route from "
                + startLocation + " to " + endLocation);
    }
}

// Context
class NavigationApp {
    private RouteStrategy routeStrategy;

    public NavigationApp(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public void navigate(String startLocation, String endLocation) {
        routeStrategy.calculateRoute(startLocation, endLocation);
    }
}

// Client
public class StrategyPatternDemo {
    public static void main(String[] args) {

        NavigationApp app = new NavigationApp(new FastestRoute());

        app.navigate("Home", "Office");

        app.setRouteStrategy(new ShortestRoute());
        app.navigate("Home", "Office");

        app.setRouteStrategy(new ScenicRoute());
        app.navigate("Home", "Office");
    }
}
Output
Calculating fastest route from Home to Office
Calculating shortest route from Home to Office
Calculating scenic route from Home to Office
How it works

RouteStrategy is the Strategy interface. It defines the route calculation method.

FastestRoute, ShortestRoute, and ScenicRoute are Concrete Strategies. Each class has its own route calculation logic.

NavigationApp is the Context. It stores a reference to a RouteStrategy object and delegates route calculation to it.

The strategy can be changed at runtime using setRouteStrategy(), so the navigation app can use different algorithms without changing its own code.
