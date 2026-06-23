A classic example of the Flyweight Pattern is a text editor where many characters share the same font information.

Problem without Flyweight

Suppose a document contains:

Hello World

Every character might store:

character = 'H'
font = "Arial"
size = 12
color = "Black"

This font information gets duplicated for every character, wasting memory.

With Flyweight:

Intrinsic State (shared): font, size, color
Extrinsic State (unique): character position, actual character
1. Flyweight Interface
interface CharacterFlyweight {
    void display(char ch, int row, int col);
}
2. Concrete Flyweight

Stores the intrinsic state.

class CharacterStyle implements CharacterFlyweight {

    private String font;
    private int size;
    private String color;

    public CharacterStyle(String font, int size, String color) {
        this.font = font;
        this.size = size;
        this.color = color;
    }

    @Override
    public void display(char ch, int row, int col) {
        System.out.println(
            "Character: " + ch +
            " Position: (" + row + "," + col + ")" +
            " Font: " + font +
            " Size: " + size +
            " Color: " + color
        );
    }
}
3. Flyweight Factory

Creates and caches flyweights.

import java.util.HashMap;
import java.util.Map;

class CharacterStyleFactory {

    private static final Map<String, CharacterFlyweight> styles =
            new HashMap<>();

    public static CharacterFlyweight getStyle(
            String font,
            int size,
            String color) {

        String key = font + "-" + size + "-" + color;

        if (!styles.containsKey(key)) {
            styles.put(key,
                new CharacterStyle(font, size, color));

            System.out.println("Creating style: " + key);
        }

        return styles.get(key);
    }
}
4. Client
public class FlyweightDemo {

    public static void main(String[] args) {

        CharacterFlyweight style1 =
                CharacterStyleFactory.getStyle(
                        "Arial", 12, "Black");

        CharacterFlyweight style2 =
                CharacterStyleFactory.getStyle(
                        "Arial", 12, "Black");

        CharacterFlyweight style3 =
                CharacterStyleFactory.getStyle(
                        "Times", 14, "Blue");

        style1.display('H', 1, 1);
        style2.display('e', 1, 2);
        style1.display('l', 1, 3);
        style3.display('A', 2, 1);

        System.out.println(style1 == style2);
    }
}
Output
Creating style: Arial-12-Black
Creating style: Times-14-Blue

Character: H Position: (1,1) Font: Arial Size: 12 Color: Black
Character: e Position: (1,2) Font: Arial Size: 12 Color: Black
Character: l Position: (1,3) Font: Arial Size: 12 Color: Black
Character: A Position: (2,1) Font: Times Size: 14 Color: Blue

true
