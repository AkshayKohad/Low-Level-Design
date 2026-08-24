class CPU{
    public void freeze(){
        System.out.println("CPU freezes");
    }

    public void execute(){
        System.out.println("CPU executes instructions");
    }
}

class Memory{
    public void load(){
        System.out.println("Memory loads boot data");
    }
}

class HardDrive{
    public void readBootSector(){
        System.out.println("Hard drive reads boot sector");
    }
}

class OperatingSystem{
    public void boot(){
        System.out.println("Operating system boots");
    }
}

class ComputerFacade{
    private final CPU cpu = new CPU();
    private final Memory memory = new Memory();
    private final HardDrive hardDrive = new HardDrive();
    private final OperatingSystem operatingSystem = new OperatingSystem();

    public void startComputer(){
        cpu.freeze();
        hardDrive.readBootSector();
        memory.load();
        cpu.execute();
        operatingSystem.boot();
    }
}


public class ComputerStartupFacadeDemo{
    public static void main(String[] args){
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.startComputer();
    }
}
