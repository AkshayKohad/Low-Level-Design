Why this satisfies LSP

❌ Bad Design:

abstract class Vehicle {
    abstract void startEngine();
}

class Bicycle extends Vehicle {
    @Override
    void startEngine() {
        throw new UnsupportedOperationException();
    }
}

A Bicycle cannot truly substitute a Vehicle because it doesn't have an engine.

✅ Good Design:

Vehicle
 ├── Motorized
 │     └── Car
 └── Manual
       └── Bicycle

Now:

Car can replace Motorized
Bicycle can replace Manual
No subclass is forced to implement behavior it doesn't support

This is the essence of the Liskov Substitution Principle: Derived classes should be substitutable for their base classes without altering program correctness.



Code Implementation



abstract class Vehicle{
    protected String name;
    protected double speed;
    
    public Vehicle(String name, double speed){
        this.name = name;
        this.speed = speed;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getSpeed(){
        return this.speed;
    }
}

abstract class Motorized extends Vehicle{
    public Motorized(String name, double speed){
        super(name,speed);
    }
    
    abstract void startEngine();
}

abstract class Manual extends Vehicle{
    public Manual(String name, double speed){
        super(name,speed);
    }
    
    abstract void startMoving();
}

 class Car extends Motorized{
    public Car(String name, double speed){
        super(name,speed);
    }
    
    @Override
    void startEngine(){
        System.out.println(name + " Car will start the engine");
    }
}

 class Bicycle extends Manual{
    public Bicycle(String name, double speed){
        super(name,speed);
    }
    
    @Override
    void startMoving(){
        System.out.println(name + " Cycle will start moving");
    }
}

public class Main {

    public static void main(String[] args) {

        Motorized car = new Car("BMW", 220);

        Manual bicycle = new Bicycle("Mountain Bike", 35);

        car.startEngine();
        bicycle.startMoving();

        System.out.println("Car Speed: " + car.getSpeed());
        System.out.println("Bicycle Speed: " + bicycle.getSpeed());
    }
}
