package structural.facade.subsystem;

public class Display {
    public void start() {
        System.out.println("[Display] Ekran açılıyor...");
        System.out.println("[Display] Görüntü aktif!");
    }

    public void shutdown() {
        System.out.println("[Display] Ekran kapatılıyor...");
    }
}
