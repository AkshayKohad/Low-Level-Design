interface Image{
    void display();
}

class RealImage implements Image{
    private final String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        System.out.println("Loading image: " + fileName);
    }

    @Override
    public void display(){
        System.out.println("Displaying image: " + fileName);
    }
}

class ProxyImage implements Image{
    private final String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display(){
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

public class LazyImageProxyDemo{
    public static void main(String[] args){
        Image image = new ProxyImage("vacation.jpg");
        image.display();
        image.display();
    }
}
