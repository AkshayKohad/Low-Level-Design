// Abstract base class
abstract class BeverageMaker {

    // Template method
    // This defines the fixed algorithm structure
    public final void makeBeverage() {
        boilWater();
        brew();
        pourIntoCup();
        addExtras();
    }

    private void boilWater() {
        System.out.println("Boiling water...");
    }

    private void pourIntoCup() {
        System.out.println("Pouring into cup...");
    }

    // Steps that subclasses must define
    protected abstract void brew();

    protected abstract void addExtras();
}

// Concrete class for Tea
class TeaMaker extends BeverageMaker {

    @Override
    protected void brew() {
        System.out.println("Steeping the tea leaves...");
    }

    @Override
    protected void addExtras() {
        System.out.println("Adding lemon...");
    }
}

// Concrete class for Coffee
class CoffeeMaker extends BeverageMaker {

    @Override
    protected void brew() {
        System.out.println("Brewing coffee grounds...");
    }

    @Override
    protected void addExtras() {
        System.out.println("Adding sugar and milk...");
    }
}

// Client
public class TemplateMethodPatternDemo {
    public static void main(String[] args) {

        System.out.println("Making Tea:");
        BeverageMaker tea = new TeaMaker();
        tea.makeBeverage();

        System.out.println("\nMaking Coffee:");
        BeverageMaker coffee = new CoffeeMaker();
        coffee.makeBeverage();
    }
}
Output
Making Tea:
Boiling water...
Steeping the tea leaves...
Pouring into cup...
Adding lemon...

Making Coffee:
Boiling water...
Brewing coffee grounds...
Pouring into cup...
Adding sugar and milk...
How it works

BeverageMaker is the abstract base class. It defines the template method makeBeverage().

makeBeverage() contains the fixed order of steps: boil water, brew, pour into cup, and add extras.

TeaMaker and CoffeeMaker are concrete subclasses. They provide their own implementation for brew() and addExtras().

The common process stays in the base class, while the variable steps are implemented by subclasses.
