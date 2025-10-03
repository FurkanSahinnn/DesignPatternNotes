package structural.facade.subsystem.storageHardwares;

public abstract class Storage {
    protected String type;

    public Storage(String type) {
        this.type = type;
    }

    public abstract void start();

    public void shutdown() {
        System.out.println("[" + type + "] Veriler güvenle kaydediliyor...");
    }
}
