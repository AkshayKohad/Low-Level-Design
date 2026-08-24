class ApplicationLogger{
    private ApplicationLogger(){
        System.out.println("Logger Instance is created");
    }

    private static class LoggerHolder {
        private static final ApplicationLogger INSTANCE = new ApplicationLogger();
    }

    public static ApplicationLogger getInstance(){
        return LoggerHolder.INSTANCE;
    }

    public void log(String message){
        System.out.println("[APP-LOG] " + message);
    }
}

public class ApplicationLoggerDemo{
    public static void main(String[] args){
        ApplicationLogger logger1 = ApplicationLogger.getInstance();
        ApplicationLogger logger2 = ApplicationLogger.getInstance();

        System.out.println(logger1 == logger2);
        logger1.log("Application started");
        logger2.log("User logged in");
    }
}


