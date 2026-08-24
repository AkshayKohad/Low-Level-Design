import java.util.ArrayList;
import java.util.List;

interface FileSystemComponent {
    void showDetails(String indent);
    int getSize();
}

class File implements FileSystemComponent {
    private final String name;
    private final int sizeInKb;

    public File(String name, int sizeInKb) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be blank");
        }
        if (sizeInKb < 0) {
            throw new IllegalArgumentException("File size cannot be negative");
        }
        this.name = name;
        this.sizeInKb = sizeInKb;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "- " + name + " (" + sizeInKb + " KB)");
    }

    @Override
    public int getSize() {
        return sizeInKb;
    }
}

class Directory implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Directory name cannot be blank");
        }
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("File-system component cannot be null");
        }
        children.add(component);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "+ " + name);
        for (FileSystemComponent child : children) {
            child.showDetails(indent + "  ");
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }
}

public class FileSystemCompositeDemo {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        root.add(new File("readme.txt", 10));

        Directory sourceDirectory = new Directory("src");
        sourceDirectory.add(new File("Main.java", 20));

        Directory utilsDirectory = new Directory("utils");
        utilsDirectory.add(new File("Helper.java", 15));
        sourceDirectory.add(utilsDirectory);

        root.add(sourceDirectory);

        root.showDetails("");
        System.out.println("Total size: " + root.getSize() + " KB");
    }
}
