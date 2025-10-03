package structural.facade.subsystem;

public class Cpu {
    public void start() {
        System.out.println("[CPU] İşlemci başlatılıyor...");
        System.out.println("[CPU] İşlemci hazır!");
    }

    public void shutdown() {
        System.out.println("[CPU] İşlemci durduruluyor...");
    }
}
