Here's a classic implementation of the Composite Design Pattern in Java using the example of a file system where a Folder can contain files and other folders, while a File is a leaf node.

1. Component Interface
interface FileSystemComponent {
    void showDetails();
}
2. Leaf Class
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}
3. Composite Class
import java.util.ArrayList;
import java.util.List;

class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children;

    public Folder(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);

        for (FileSystemComponent component : children) {
            component.showDetails();
        }
    }
}
4. Client Code
public class CompositePatternDemo {
    public static void main(String[] args) {

        // Leaf objects
        File file1 = new File("resume.pdf");
        File file2 = new File("photo.jpg");
        File file3 = new File("notes.txt");

        // Composite objects
        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder root = new Folder("Root");

        documents.add(file1);
        documents.add(file3);

        pictures.add(file2);

        root.add(documents);
        root.add(pictures);

        root.showDetails();
    }
}


Output
Folder: Root
Folder: Documents
File: resume.pdf
File: notes.txt
Folder: Pictures
File: photo.jpg

Structure

                Root
               /    \
       Documents   Pictures
        /    \         \
 resume.pdf notes.txt photo.jpg


How it demonstrates Composite Pattern
Component → FileSystemComponent
Leaf → File
Composite → Folder
Client interacts with both File and Folder through the same interface (FileSystemComponent).
A Folder can contain both File objects and other Folder objects, enabling recursive tree structures.
