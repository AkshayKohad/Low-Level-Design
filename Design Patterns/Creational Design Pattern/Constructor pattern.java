// Product class
class Employee {
    private String name;
    private int age;
    private String department;

    // Constructor
    public Employee(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    // Method to display employee details
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Department: " + department);
        System.out.println("-------------------");
    }
}

// Client code
public class ConstructorPatternDemo {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Akshay", 25, "Engineering");
        Employee emp2 = new Employee("John", 30, "HR");

        emp1.displayDetails();
        emp2.displayDetails();
    }
}
