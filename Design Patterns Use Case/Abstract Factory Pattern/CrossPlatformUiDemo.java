interface Button {
    void render();
}

interface Checkbox {
    void render();
}

class LightButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering light button");
    }
}

class LightCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering light checkbox");
    }
}

class DarkButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering dark button");
    }
}

class DarkCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering dark checkbox");
    }
}

interface UiFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class LightUiFactory implements UiFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}

class DarkUiFactory implements UiFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}

class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(UiFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void renderUi() {
        button.render();
        checkbox.render();
    }
}

public class CrossPlatformUiDemo {
    public static void main(String[] args) {
        Application application = new Application(new DarkUiFactory());
        application.renderUi();
    }
}
