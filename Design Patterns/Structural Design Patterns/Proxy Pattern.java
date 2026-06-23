A classic example of the Proxy Pattern is an image viewer where loading a high-resolution image is expensive. The proxy delays the creation/loading of the real image until it's actually needed.

1. Subject Interface

Both the Real Object and Proxy implement the same interface.

interface Image {
    void display();
}
2. Real Subject

This is the actual expensive object.

class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
3. Proxy

Controls access to the real object.

class ProxyImage implements Image {

    private String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {

        if (realImage == null) {
            realImage = new RealImage(fileName);
        }

        realImage.display();
    }
}
4. Client
public class ProxyPatternDemo {

    public static void main(String[] args) {

        Image image = new ProxyImage("profile_photo.jpg");

        System.out.println("Image object created");

        System.out.println("\nFirst display:");
        image.display();

        System.out.println("\nSecond display:");
        image.display();
    }
}
Output
Image object created

First display:
Loading image from disk: profile_photo.jpg
Displaying image: profile_photo.jpg

Second display:
Displaying image: profile_photo.jpg
