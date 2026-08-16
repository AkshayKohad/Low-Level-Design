interface Coffee{
    double getCost();
    String getDescription();
}

class Espresso implements Coffee{
    @Override
    public double getCost(){
        return 100.0;
    }
    
    @Override
    public String getDescription(){
        return "Espresso";
    }
}

class Cappuccino implements Coffee{
    @Override
    public double getCost(){
        return 150.0;
    }

    @Override
    public String getDescription(){
        return "Cappuccino";
    }
}

abstract class CoffeeDecorator implements Coffee{
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }
    @Override
     abstract public double getCost();

    @Override
    abstract public String getDescription();
}

class MilkDecorator extends CoffeeDecorator{

    public MilkDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public double getCost(){
        return coffee.getCost() + 20;
    }

    @Override
    public String getDescription(){
        return coffee.getDescription() + ", Milk";
    }
}

class WhippedCreamDecorator extends CoffeeDecorator{

    public WhippedCreamDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public double getCost(){
        return coffee.getCost() + 30;
    }

    @Override
    public String getDescription(){
        return coffee.getDescription() + ", Whipped Cream";
    }
}

class ExtraShotDecorator extends CoffeeDecorator{

    public ExtraShotDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public double getCost(){
        return coffee.getCost() + 40;
    }

    @Override
    public String getDescription(){
        return coffee.getDescription() + ", ExtraShot";
    }
}

public class CoffeeShopDemo{
    public static void main(String[] args){
        Coffee coffee = new MilkDecorator(new Espresso());
        System.out.println(coffee.getDescription() + " : ₹" + coffee.getCost());

        Coffee customCoffee = new ExtraShotDecorator(
                new MilkDecorator(new Cappuccino())
        );
        System.out.println(customCoffee.getDescription() + " : ₹" + customCoffee.getCost());
    }
}
