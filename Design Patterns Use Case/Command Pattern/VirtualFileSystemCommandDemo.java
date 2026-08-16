import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

interface Command {
    void execute();
    void undo();
}

class VirtualFileSystem {
    private final Map<String, String> fileRecords = new HashMap<>();

    public void createFile(String fileName, String fileContent) {
        validateFileName(fileName);
        if (fileRecords.containsKey(fileName)) {
            throw new IllegalArgumentException("File already exists: " + fileName);
        }
        fileRecords.put(fileName, fileContent);
    }

    public String deleteFile(String fileName) {
        validateFileName(fileName);
        if (!fileRecords.containsKey(fileName)) {
            throw new IllegalArgumentException("File does not exist: " + fileName);
        }
        return fileRecords.remove(fileName);
    }

    public void restoreFile(String fileName, String fileContent) {
        fileRecords.put(fileName, fileContent);
    }

    public void showFiles() {
        System.out.println(fileRecords);
    }

    private void validateFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be blank");
        }
    }
}

class CreateFileCommand implements Command {
    private final VirtualFileSystem fileSystem;
    private final String fileName;
    private final String fileContent;

    public CreateFileCommand(VirtualFileSystem fileSystem, String fileName, String fileContent) {
        this.fileSystem = fileSystem;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    @Override
    public void execute() {
        fileSystem.createFile(fileName, fileContent);
    }

    @Override
    public void undo() {
        fileSystem.deleteFile(fileName);
    }
}

class DeleteFileCommand implements Command {
    private final VirtualFileSystem fileSystem;
    private final String fileName;
    private String deletedFileContent;

    public DeleteFileCommand(VirtualFileSystem fileSystem, String fileName) {
        this.fileSystem = fileSystem;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        deletedFileContent = fileSystem.deleteFile(fileName);
    }

    @Override
    public void undo() {
        fileSystem.restoreFile(fileName, deletedFileContent);
    }
}

class FileManagerInvoker {
    private final Deque<Command> commandHistory = new ArrayDeque<>();

    public void executeCommand(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (commandHistory.isEmpty()) {
            throw new IllegalStateException("No command has been executed to undo");
        }
        commandHistory.pop().undo();
    }
}

public class VirtualFileSystemCommandDemo {
    public static void main(String[] args) {
        VirtualFileSystem fileSystem = new VirtualFileSystem();
        FileManagerInvoker invoker = new FileManagerInvoker();

        invoker.executeCommand(new CreateFileCommand(fileSystem, "notes.txt", "LLD patterns"));
        fileSystem.showFiles();

        invoker.executeCommand(new DeleteFileCommand(fileSystem, "notes.txt"));
        fileSystem.showFiles();

        invoker.undoLastCommand();
        fileSystem.showFiles();

        invoker.undoLastCommand();
        fileSystem.showFiles();
    }
}
