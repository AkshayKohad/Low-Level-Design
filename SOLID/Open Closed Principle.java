Why this follows OCP

The VolumeCalculator class is:

Closed for modification → no need to change existing logic
Open for extension → new shapes can be added easily

Example:
If tomorrow you add a Sphere class in below class setup:

class Sphere extends Shape {

    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    double volume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }
}

No changes are required in VolumeCalculator.



abstract class Shape {

    // Every shape must implement its own volume logic
    abstract double volume();
}

class Cuboid extends Shape{

    private double length;
    private double breath;
    private double width;
    
    public Cuboid(double length, double breath, double width){
        this.length = length;
        this.breath = breath;
        this.width = width;
    }
    @Override double volume(){
        
    }
}

// Cone.java
class Cone extends Shape {

    private double radius;
    private double height;

    public Cone(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    double volume() {
        return (Math.PI * radius * radius * height) / 3;
    }
}

// Cylinder.java
class Cylinder extends Shape {

    private double radius;
    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    double volume() {
        return Math.PI * radius * radius * height;
    }
}

// VolumeCalculator.java
class VolumeCalculator {

    public double sumVolume(Shape[] shapes) {

        double totalVolume = 0;

        for (Shape shape : shapes) {
            totalVolume += shape.volume();
        }

        return totalVolume;
    }
}

// Main.java
public class Main {

    public static void main(String[] args) {

        Shape cuboid = new Cuboid(10, 5, 2);

        Shape cone = new Cone(3, 7);

        Shape cylinder = new Cylinder(4, 10);

        Shape[] shapes = {cuboid, cone, cylinder};

        VolumeCalculator calculator = new VolumeCalculator();

        double totalVolume = calculator.sumVolume(shapes);

        System.out.println("Total Volume = " + totalVolume);
    }
}
