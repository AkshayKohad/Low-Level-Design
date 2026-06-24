1. Iterator Interface
interface Iterator<T> {
    boolean hasNext();
    T next();
}
2. Aggregate Interface
interface Collection<T> {
    Iterator<T> createIterator();
}
3. Concrete Collection
class NameRepository implements Collection<String> {

    private String[] names = {
        "Alice",
        "Bob",
        "Charlie",
        "David"
    };

    @Override
    public Iterator<String> createIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator<String> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public String next() {
            if (hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}
4. Client Code
public class Main {

    public static void main(String[] args) {

        NameRepository repository = new NameRepository();

        Iterator<String> iterator = repository.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
Output
Alice
Bob
Charlie
David
Structure
           +----------------+
           |   Collection   |
           +----------------+
                   |
                   |
                   v
        +----------------------+
        |   NameRepository     |
        +----------------------+
                   |
 createIterator()
                   |
                   v
          +----------------+
          | NameIterator   |
          +----------------+
          | hasNext()      |
          | next()         |
          +----------------+
