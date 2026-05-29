Why this follows DIP
Headmaster depends on the abstraction Faculty, not on concrete classes like Teacher or Assistant.
New classes like Secretary can be added without changing Headmaster.
High-level and low-level modules both depend on the abstraction.


Implementation of Dependency Inversion Principle.



import java.util.ArrayList;
import java.util.List;


abstract class Faculty{
    protected String name;
    
    public Faculty(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public abstract void performDuty();
}

class Teacher extends Faculty{
    public Teacher(String name){
        super(name);
    }
    
    public void performDuty(){
        System.out.println(name + " is teaching students.");
    }
}

class Assistant extends Faculty{
    public Assistant(String name){
        super(name);
    }
    
    public void performDuty(){
        System.out.println(name + " is Assisting students.");
    }
        
}

class Helper extends Faculty {

    public Helper(String name) {
        super(name);
    }

    @Override
    public void performDuty() {
        System.out.println(name + " is helping with school work.");
    }
}

class Secretary extends Faculty {

    public Secretary(String name) {
        super(name);
    }

    @Override
    public void performDuty() {
        System.out.println(name + " is managing office tasks.");
    }
}

class Headmaster {
    private List<Faculty>facultyList;
    
    public Headmaster(){
        facultyList = new ArrayList<>();
    }
    
    public void addFaculty(Faculty faculty){
        facultyList.add(faculty);
    }
    
    public void showFaculty(){
        System.out.println("Faculty under Headmaster:");
        for (Faculty faculty : facultyList) {
            System.out.println("- " + faculty.getName());
        }
    }
    
    public void assignDuties(){
        System.out.println("Faculty duties");
        for (Faculty faculty : facultyList) {
            faculty.performDuty();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Headmaster headmaster = new Headmaster();

        Faculty teacher = new Teacher("Mr. Sharma");
        Faculty assistant = new Assistant("Ms. Roy");
        Faculty helper = new Helper("John");
        Faculty secretary = new Secretary("Anna");

        headmaster.addFaculty(teacher);
        headmaster.addFaculty(assistant);
        headmaster.addFaculty(helper);
        headmaster.addFaculty(secretary);

        headmaster.showFaculty();
        System.out.println();

        headmaster.assignDuties();
    }
}
