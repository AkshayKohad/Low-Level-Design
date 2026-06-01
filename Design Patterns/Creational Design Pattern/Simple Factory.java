The factory decides which object to create.

So object creation is centralized in one place.



interface Button{
    void render();
}

class WindowsButton implements Button{
    @Override
    public void render(){
        System.out.println("Windows Button rendered");
    }
}

class MacButton implements Button{
    @Override
    public void render(){
        System.out.println("Mac Button rendered");
    }
}

class ButtonFactory {
    public static Button createButton(String button){
        switch(button.toUpperCase()){
            
            case "WINDOWS" :
                return new WindowsButton();
            
            case "MAC" :
                return new MacButton();
                
            default :
                throw new IllegalArgumentException("Unknown OS");
        }
    }
}

class Main {
  public static void main(String[] args) {
      
    Button button = ButtonFactory.createButton("WINDOWS");
    button.render();
  }
}
