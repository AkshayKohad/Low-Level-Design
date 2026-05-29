How ISP is satisfied

❌ Bad Design:

interface Shape {
    void display();
    double area();
    double volume();
}

Square and Rectangle would be forced to implement volume() even though 2D shapes don't have volume.

✅ Good Design:

Shape
 ├── TwoDimensionalShape
 │      ├── Square
 │      └── Rectangle
 │
 └── ThreeDimensionalShape
        └── Cube
Square and Rectangle only implement area().
Cube only implements volume().
No class is forced to depend on methods it does not use.

This is exactly what the Interface Segregation Principle recommends.




interface Shape {
    void display();
}

interface TwoDimensionalShape extends Shape {

    double area();
}

interface ThreeDimensionalShape extends Shape {

    double volume();
}

class Square implements TwoDimensionalShape {

    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public void display() {
        System.out.println("Square");
    }

    @Override
    public double area() {
        return side * side;
    }
}

class Rectangle implements TwoDimensionalShape {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void display() {
        System.out.println("Rectangle");
    }

    @Override
    public double area() {
        return length * width;
    }
}

class Cube implements ThreeDimensionalShape {

    private double side;

    public Cube(double side) {
        this.side = side;
    }

    @Override
    public void display() {
        System.out.println("Cube");
    }

    @Override
    public double volume() {
        return side * side * side;
    }
}


public class Main {

    public static void main(String[] args) {

        TwoDimensionalShape square = new Square(5);
        TwoDimensionalShape rectangle = new Rectangle(10, 4);

        ThreeDimensionalShape cube = new Cube(3);

        square.display();
        System.out.println("Square Area = " + square.area());

        System.out.println();

        rectangle.display();
        System.out.println("Rectangle Area = " + rectangle.area());

        System.out.println();

        cube.display();
        System.out.println("Cube Volume = " + cube.volume());
    }
}
