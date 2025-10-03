package structural.facade.subsystem.tempMemoryHardwares;

public class Bios {
    private Rom rom;

    public Bios() {
        this.rom = new Rom();
    }

    public void initialize() {
        System.out.println(" [BIOS] BIOS başlatılıyor...");
        rom.load(); // ROM'u yükle
        System.out.println(" [BIOS] POST testi başarılı!");
    }
}
