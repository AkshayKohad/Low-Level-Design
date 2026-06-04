In this example, processor, ram, and storage are required fields, while graphicsCard, bluetooth, and wifi are optional fields.
  The ComputerBuilder class constructs the Computer object step by step.


class Computer {
    private String processor;
    private String ram;
    private String storage;
    private String graphicsCard;
    private boolean bluetooth;
    private boolean wifi;

    private Computer(ComputerBuilder builder) {
        this.processor = builder.processor;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.bluetooth = builder.bluetooth;
        this.wifi = builder.wifi;
    }

    public void showSpecifications() {
        System.out.println("Processor: " + processor);
        System.out.println("RAM: " + ram);
        System.out.println("Storage: " + storage);
        System.out.println("Graphics Card: " + graphicsCard);
        System.out.println("Bluetooth: " + bluetooth);
        System.out.println("WiFi: " + wifi);
    }

    public static class ComputerBuilder {
        private String processor;
        private String ram;
        private String storage;
        private String graphicsCard;
        private boolean bluetooth;
        private boolean wifi;

        public ComputerBuilder(String processor, String ram, String storage) {
            this.processor = processor;
            this.ram = ram;
            this.storage = storage;
        }

        public ComputerBuilder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public ComputerBuilder setBluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }

        public ComputerBuilder setWifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder(
                "Intel i7",
                "16GB",
                "1TB SSD"
        )
        .setGraphicsCard("NVIDIA RTX 4060")
        .setBluetooth(true)
        .setWifi(true)
        .build();

        computer.showSpecifications();
    }
}
