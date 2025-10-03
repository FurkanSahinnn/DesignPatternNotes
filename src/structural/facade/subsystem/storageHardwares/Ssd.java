package structural.facade.subsystem.storageHardwares;

public class Ssd extends Storage {
    public Ssd() {
        super("SSD");
    }

    @Override
    public void start() {
        System.out.println("[SSD] SSD başlatılıyor...");
        System.out.println("[SSD] Flash bellek hazır! (Hızlı)");
    }
}
