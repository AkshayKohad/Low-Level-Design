interface Icecream{
    String getDescription();
    double getCost();
}

class Vanilla implements Icecream{
    
    @Override
    public String getDescription(){
        return "Vanilla Cone";
    }
    
    @Override
    public double getCost(){
        return 2.0;
    }
}

abstract class IceCreamDecorator implements Icecream{
    protected Icecream icecream;
    
    IceCreamDecorator(Icecream icecream){
        this.icecream = icecream;
    }
    
    @Override
    public String getDescription(){
        return icecream.getDescription();
    }
    
    @Override
    public double getCost(){
        return icecream.getCost();
    }
}


class ChocolateTopping extends IceCreamDecorator{
    
    ChocolateTopping(Icecream icecream){
        super(icecream);
    }
    
    @Override
    public String getDescription(){
        return icecream.getDescription() + " ChocolateToppings";
    }
    
    @Override
    public double getCost(){
        return icecream.getCost() + 1.0;
    }
}

class NutsTopping extends IceCreamDecorator {

    public NutsTopping(Icecream icecream) {
        super(icecream);
    }

    @Override
    public String getDescription() {
        return icecream.getDescription() + ", Nuts";
    }

    @Override
    public double getCost() {
        return icecream.getCost() + 1.00;
    }
}

class SprinklesTopping extends IceCreamDecorator {

    public SprinklesTopping(Icecream icecream) {
        super(icecream);
    }

    @Override
    public String getDescription() {
        return icecream.getDescription() + ", Sprinkles";
    }

    @Override
    public double getCost() {
        return icecream.getCost() + 0.50;
    }
}

public class DecoratorPatternDemo {
  public static void main(String[] args) {
    Icecream iceCream = new Vanilla();
    System.out.println(iceCream.getDescription());
    System.out.println("Cost: $" + iceCream.getCost());
    iceCream = new ChocolateTopping(iceCream);
    iceCream = new NutsTopping(iceCream);
    System.out.println(iceCream.getDescription());
    System.out.println("Cost: $" + iceCream.getCost());
  }
}


