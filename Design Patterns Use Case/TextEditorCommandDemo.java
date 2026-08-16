import java.util.ArrayDeque;
import java.util.Deque;

interface Command {
    void execute();
    void undo();
}

class TextEditor {
    private String content = "";

    public void append(String text) {
        content += text;
    }

    public void removeLast(int length) {
        content = content.substring(0, content.length() - length);
    }

    public void clear() {
        content = "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

class WriteCommand implements Command {
    private final TextEditor editor;
    private final String text;

    public WriteCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.text = text;
    }

    @Override
    public void execute() {
        editor.append(text);
    }

    @Override
    public void undo() {
        editor.removeLast(text.length());
    }
}

class ClearCommand implements Command {
    private final TextEditor editor;
    private String previousContent;

    public ClearCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        previousContent = editor.getContent();
        editor.clear();
    }

    @Override
    public void undo() {
        editor.setContent(previousContent);
    }
}

class EditorInvoker {
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

public class TextEditorCommandDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorInvoker invoker = new EditorInvoker();

        invoker.executeCommand(new WriteCommand(editor, "Hello"));
        invoker.executeCommand(new WriteCommand(editor, " World"));
        System.out.println(editor.getContent()); // Hello World

        invoker.undoLastCommand();
        System.out.println(editor.getContent()); // Hello

        invoker.executeCommand(new ClearCommand(editor));
        System.out.println(editor.getContent()); // empty

        invoker.undoLastCommand();
        System.out.println(editor.getContent()); // Hello
    }
}
