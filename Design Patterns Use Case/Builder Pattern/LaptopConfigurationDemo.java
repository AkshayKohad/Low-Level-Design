class Laptop {
    private final String brand;
    private final String processor;
    private final int ramGb;
    private final int storageGb;
    private final boolean dedicatedGraphics;
    private final boolean touchScreen;
    private final String operatingSystem;

    private Laptop(Builder builder) {
        this.brand = builder.brand;
        this.processor = builder.processor;
        this.ramGb = builder.ramGb;
        this.storageGb = builder.storageGb;
        this.dedicatedGraphics = builder.dedicatedGraphics;
        this.touchScreen = builder.touchScreen;
        this.operatingSystem = builder.operatingSystem;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", processor='" + processor + '\'' +
                ", ramGb=" + ramGb +
                ", storageGb=" + storageGb +
                ", dedicatedGraphics=" + dedicatedGraphics +
                ", touchScreen=" + touchScreen +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }

    static class Builder {
        private final String brand;
        private final String processor;
        private final int ramGb;
        private int storageGb;
        private boolean dedicatedGraphics;
        private boolean touchScreen;
        private String operatingSystem;

        public Builder(String brand, String processor, int ramGb) {
            if (brand == null || brand.trim().isEmpty()) {
                throw new IllegalArgumentException("Brand cannot be blank");
            }
            if (processor == null || processor.trim().isEmpty()) {
                throw new IllegalArgumentException("Processor cannot be blank");
            }
            if (ramGb <= 0) {
                throw new IllegalArgumentException("RAM must be greater than zero");
            }
            this.brand = brand;
            this.processor = processor;
            this.ramGb = ramGb;
        }

        public Builder storageGb(int storageGb) {
            if (storageGb < 0) {
                throw new IllegalArgumentException("Storage cannot be negative");
            }
            this.storageGb = storageGb;
            return this;
        }

        public Builder dedicatedGraphics(boolean dedicatedGraphics) {
            this.dedicatedGraphics = dedicatedGraphics;
            return this;
        }

        public Builder touchScreen(boolean touchScreen) {
            this.touchScreen = touchScreen;
            return this;
        }

        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Laptop build() {
            return new Laptop(this);
        }
    }
}

public class LaptopConfigurationDemo {
    public static void main(String[] args) {
        Laptop laptop = new Laptop.Builder("Lenovo", "Intel i7", 16)
                .storageGb(512)
                .dedicatedGraphics(true)
                .operatingSystem("Windows 11")
                .build();

        System.out.println(laptop);
    }
}
