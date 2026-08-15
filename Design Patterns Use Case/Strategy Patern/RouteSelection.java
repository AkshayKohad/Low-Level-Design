interface RouteSelectionStrategy{
    void calculateTime(double distance);
}

class CarRouteStrategy implements RouteSelectionStrategy{
    private static final double SPEED = 40.0;

    @Override
    public void calculateTime(double distance){
        double carTime = distance/SPEED;
        System.out.println("Time required to travel via Car : " + carTime); 
    }
}

class BikeRouteStrategy implements RouteSelectionStrategy{
    private static final double SPEED = 25.0;

    @Override
    public void calculateTime(double distance){
        double bikeTime = distance/SPEED;
        System.out.println("Time required to travel via Bike : " + bikeTime); 
    }
}

class WalkingRouteStrategy implements RouteSelectionStrategy{
    private static final double SPEED = 5.0;

    @Override
    public void calculateTime(double distance){
        double walkingTime = distance/SPEED;
        System.out.println("Time required to travel via Walking : " + walkingTime); 
    }
}


class RouteStrategy{
    private RouteSelectionStrategy strategy;

    public void setRouteStrategy(RouteSelectionStrategy strategy){
        if(strategy == null){
            throw new IllegalArgumentException("Strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public void calculateTime(double distance){
        if(distance < 0){
            throw new IllegalArgumentException("Distance cannot be nagative");
        }
        if(strategy == null){
            throw new IllegalStateException("Route strategy need to be state first");
        }
        strategy.calculateTime(distance);
    }
}

public class RouteSelection{
    public static void main(String[] args){
        RouteStrategy route = new RouteStrategy();
        route.setRouteStrategy(new CarRouteStrategy());
        route.calculateTime(20.0);

        route.setRouteStrategy(new BikeRouteStrategy());
        route.calculateTime(20.0);

        route.setRouteStrategy(new WalkingRouteStrategy());
        route.calculateTime(20.0);

    }
}
