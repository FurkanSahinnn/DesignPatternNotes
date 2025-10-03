package structural.facade.subsystem.tempMemoryHardwares;

public class Ram {
    private Bios bios;

    public Ram() {
        this.bios = new Bios();
    }

    public void start() {
        System.out.println("[RAM] RAM başlatılıyor...");
        bios.initialize(); // BIOS'u başlat (BIOS de ROM'u yükler)
        System.out.println("[RAM] Bellek hazır!");
    }

    public void shutdown() {
        System.out.println("[RAM] RAM temizleniyor...");
    }
}
