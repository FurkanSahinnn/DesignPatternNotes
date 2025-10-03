package structural.facade;

import structural.facade.subsystem.storageHardwares.Hdd;
import structural.facade.subsystem.storageHardwares.Ssd;
import structural.facade.subsystem.storageHardwares.Storage;
import structural.facade.subsystem.Cpu;
import structural.facade.subsystem.Display;
import structural.facade.subsystem.tempMemoryHardwares.Ram;

public class Computer {
    // Private Fields - Subsystem components
    private Cpu cpu;
    private Ram ram;
    private Storage fast_storage;
    private Storage slow_storage;
    private Display display;

    public Computer() {
        System.out.println("════════════════════════════════════════");
        System.out.println("  🔧 BİLGİSAYAR BİLEŞENLERİ KURULUYOR");
        System.out.println("════════════════════════════════════════\n");

        cpu = new Cpu();
        ram = new Ram();
        fast_storage = new Ssd();
        slow_storage = new Hdd();
        display = new Display();

        System.out.println("\n✅ Tüm bileşenler başarıyla kuruldu!\n");
    }

    public void start() {
        System.out.println("════════════════════════════════════════");
        System.out.println("  🚀 BİLGİSAYAR BAŞLATILIYOR...");
        System.out.println("════════════════════════════════════════\n");

        cpu.start();
        System.out.println();

        fast_storage.start();
        slow_storage.start();
        System.out.println();

        ram.start();
        System.out.println();

        display.start();

        System.out.println("\n════════════════════════════════════════");
        System.out.println("  ✅ Computer started.");
        System.out.println("════════════════════════════════════════");
    }

    public void shutdown() {
        System.out.println("\n════════════════════════════════════════");
        System.out.println("  ⏻ BİLGİSAYAR KAPATILIYOR...");
        System.out.println("════════════════════════════════════════\n");

        display.shutdown();

        fast_storage.shutdown();
        slow_storage.shutdown();

        ram.shutdown();
        cpu.shutdown();

        System.out.println("\n✅ Bilgisayar güvenle kapatıldı.");
    }
}
