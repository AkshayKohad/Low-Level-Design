import java.util.Stack;

// Memento
class TextMemento {
    private final String text;

    public TextMemento(String text) {
        this.text = text;
    }

    public String getSavedText() {
        return text;
    }
}

// Originator
class TextEditor {
    private String text = "";

    public void write(String newText) {
        text += newText;
    }

    public String getText() {
        return text;
    }

    public TextMemento save() {
        return new TextMemento(text);
    }

    public void restore(TextMemento memento) {
        text = memento.getSavedText();
    }
}

// Caretaker
class History {
    private Stack<TextMemento> history = new Stack<>();

    public void save(TextEditor editor) {
        history.push(editor.save());
    }

    public TextMemento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}

// Client
public class MementoPatternDemo {
    public static void main(String[] args) {

        TextEditor editor = new TextEditor();
        History history = new History();

        editor.write("Hello ");
        history.save(editor);

        editor.write("World!");
        history.save(editor);

        editor.write(" This text will be undone.");

        System.out.println("Current text:");
        System.out.println(editor.getText());

        TextMemento previousState = history.undo();
        if (previousState != null) {
            editor.restore(previousState);
        }

        System.out.println("\nAfter undo:");
        System.out.println(editor.getText());

        previousState = history.undo();
        if (previousState != null) {
            editor.restore(previousState);
        }

        System.out.println("\nAfter another undo:");
        System.out.println(editor.getText());
    }
}
Output
Current text:
Hello World! This text will be undone.

After undo:
Hello World!

After another undo:
Hello 
How it works

TextEditor is the Originator. It creates and restores saved states.

TextMemento is the Memento. It stores the text state.

History is the Caretaker. It keeps saved states and returns them when undo is needed.

This allows the text editor to restore previous text states without exposing its internal implementation.
