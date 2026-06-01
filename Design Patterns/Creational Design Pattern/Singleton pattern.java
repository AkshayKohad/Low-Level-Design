class Singleton{
    private static Singleton SingletonInstance;
    
    private Singleton(){
        System.out.println("Singleton Class is Created");
    }
    
    public static Singleton getInstance(){
        if(SingletonInstance == null){
            SingletonInstance = new Singleton();
        }
        
        return SingletonInstance;
    }
}


class Main {
  public static void main(String[] args) {
    Singleton instance1 = Singleton.getInstance();
    Singleton instance2 = Singleton.getInstance();
    
    if(instance1 == instance2){
        System.out.println("True, same Instance");
    }
    
  }
}
