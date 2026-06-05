Here, Employee is the prototype. The clone() method creates a new object by copying the existing object’s values. 
  The cloned object gets the same default values, but you can modify it independently.



// Prototype interface
interface Prototype {
    Prototype clone();
}

// Concrete prototype class
class Employee implements Prototype {
    private String name;
    private String department;
    private int salary;

    public Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Copy constructor
    public Employee(Employee employee) {
        this.name = employee.name;
        this.department = employee.department;
        this.salary = employee.salary;
    }

    @Override
    public Prototype clone() {
        return new Employee(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
        System.out.println();
    }
}

// Client code
public class PrototypePatternDemo {
    public static void main(String[] args) {

        // Original object
        Employee originalEmployee = new Employee("Akshay", "Engineering", 50000);

        // Cloned object
        Employee clonedEmployee = (Employee) originalEmployee.clone();

        // Changing only cloned object's name
        clonedEmployee.setName("Rahul");

        System.out.println("Original Employee:");
        originalEmployee.display();

        System.out.println("Cloned Employee:");
        clonedEmployee.display();
    }
}
