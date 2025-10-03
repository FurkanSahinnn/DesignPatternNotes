package structural.facade.subsystem.storageHardwares;

public class Hdd extends Storage {
    public Hdd() {
        super("HDD");
    }

    @Override
    public void start() {
        System.out.println("[HDD] Sabit disk başlatılıyor...");
        System.out.println("[HDD] Diskler dönüyor... (Mekanik)");
        System.out.println("[HDD] HDD hazır!");
    }
}
