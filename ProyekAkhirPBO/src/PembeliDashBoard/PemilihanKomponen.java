/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PembeliDashBoard;

import javax.swing.JComboBox;

/**
 *
 * @author Ridho Gymnastiar Al Rasyid
 */
public class PemilihanKomponen implements ItemProduk{
    private JComboBox pemilihan, barang;
    
    public PemilihanKomponen(JComboBox pemilihan, JComboBox barang){
        this.pemilihan = pemilihan;
        this.barang = barang;
    }
    
    public PemilihanKomponen(){
        this(null, null);
    }
    
    public void setPemilihan(JComboBox barang){
        switch (pemilihan.getSelectedIndex()) {
            case 1 -> {
                barang.removeAllItems();
                barang.setEnabled(true);
                for (int i = 0; i < procie.length; i++) {
                    barang.insertItemAt(procie[i], i);
                }
            }
            case 2 -> {
                barang.removeAllItems();
                barang.setEnabled(true);
                for (int i = 0; i < mobo.length; i++) {
                    barang.insertItemAt(mobo[i], i);
                }
            }
            case 3 -> {
                barang.removeAllItems();
                barang.setEnabled(true);
                for (int i = 0; i < ram.length; i++) {
                    barang.insertItemAt(ram[i], i);
                }
            }
            case 4 -> {
                barang.removeAllItems();
                barang.setEnabled(true);
                for (int i = 0; i < storage1.length; i++) {
                    barang.insertItemAt(storage1[i], i);
                }
            }
            case 5 -> {
                barang.removeAllItems();
                barang.setEnabled(true);
                for (int i = 0; i < storage2.length; i++) {
                    barang.insertItemAt(storage2[i], i);
                }
            }
            case 6 -> {
                barang.removeAllItems();
                barang.setEnabled(true);
                for (int i = 0; i < psu.length; i++) {
                    barang.insertItemAt(psu[i], i);
                }
            }
            case 7 -> {
                barang.removeAllItems();
                barang.setEnabled(true);
                for (int i = 0; i < vga.length; i++) {
                    barang.insertItemAt(vga[i], i);
                }
            }
            case 8 -> {
                barang.removeAllItems();
                barang.setEnabled(true);
                for (int i = 0; i < casing.length; i++) {
                    barang.insertItemAt(casing[i], i);
                }
            }
            default -> {
                barang.removeAllItems();
                barang.insertItemAt("Pilihan tidak tersedia!", 0);
                barang.setEnabled(false);
            }
        }
        barang.setSelectedIndex(0);
    }

    @Override
    public String[] getProcie() {
        return procie;
    }

    @Override
    public String[] getMobo() {
        return mobo;
    }

    @Override
    public String[] getRam() {
        return ram;
    }

    @Override
    public String[] getStorage1() {
        return storage1;
    }

    @Override
    public String[] getStorage2() {
        return storage2;
    }

    @Override
    public String[] getPsu() {
        return psu;
    }

    @Override
    public String[] getVga() {
        return vga;
    }

    @Override
    public String[] getCasing() {
        return casing;
    }
    
    public String[] procie = {"---Pilih Processor---",
        "AMD Ryzen 9 5900X 3.7Ghz Up To 4.8Ghz Cache 64MB 105W AM4 [Box] - 12 Core",
        "AMD Ryzen 5 3500 3.6Ghz Up To 4.1Ghz Cache 16MB 65W AM4 [Box] - 6 Core",
        "AMD Ryzen 7 5800X 3.8Ghz Up To 4.7Ghz Cache 32MB 105W AM4 [Box] - 8 Core",
        "AMD Athlon 3000G (Radeon Vega 3) 3.5Ghz Cache 4MB 35W Socket AM4 [BOX] - 2 Core",
        "AMD Ryzen 5 5600X 3.7Ghz Up To 4.6Ghz Cache 32MB 65W AM4 [Box] - 6 Core",
        "Intel Core i5-12600K 3.7GHz Up to 4.9GHz - Cache 20Mb - LGA 1700 - Alder Lake Series",
        "Intel Core i5-12700KF 3.6GHz Up to 5.0GHz - Cache 25Mb - LGA 1700 - Alder Lake Series",
        "Intel Core i5-12500 3.0GHz Up to 4.6GHz - Cache 18Mb - LGA 1700 - Alder Lake Series",
        "Intel Core i7-12700K 3.6GHz Up to 5.0GHz - Cache 25Mb - LGA 1700 - Alder Lake Series",
        "Intel Core i9-12900 2.4GHz Up to 5.1GHz - Cache 30 Mb - LGA 1700 - Alder Lake Series"};

    public String[] mobo = {"---Pilih Motherboard---",
        "MSI PRO B660M-A Wifi DDR4 (LGA1700 B660 DDR4 USB3.2 SATA3)",
        "Gigabyte Z690 Aorus Elite AX DDR4 (LGA1700 Z690DDR4 USB3.2 SATA3)",
        "Asus ROG Strix Z690-F Gaming WIFI (LGA1700 Z690 DDR5 USB3.2 SATA3)",
        "Asus ROG Strix B660-A Gaming WIFI D4 (LGA1700 B660 DDR4 USB3.2SATA3)",
        "MSI MAG Z690 Tomahawk WiFi DDR4 (LGA1700 Z690 DDR4USB3.2 SATA3)",
        "Biostar A320MH (AM4, AMD Promontory A320, DDR4, USB 3.1, SATA3)",
        "ASRock A320M-HDV (AM4, AMD Promontory A320, DDR4, USB 3.1, SATA3)",
        "Asus TUF B450M-PLUS Gaming (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)",
        "Biostar Racing B450GT3 (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)",
        "Gigabyte B450 Aorus Elite (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)"};

    public String[] ram = {"---Pilih RAM---",
        "Corsair DDR4 Vengeance LPX PC19200 4GB (1X4GB)",
        "V-GeN Platinum DDR4 8GB PC17000",
        "Team Elite Plus Black DDR4 PC19200 2400Mhz Dual Channel 8GB (2X4GB)",
        "Team Elite Plus Black DDR4 PC21000 2666Mhz 8GB",
        "Team EXtreme DDR4 PC28800 3600Mhz Dual Channel 16GB (2x8GB)"};

    public String[] storage1 = {"---Pilih Storage 1---",
        "WDC 1TB SATA3 64MB - Blue - WD10EZEX - Garansi 2 Th",
        "Seagate 3TB SATA3 256MB - BarraCuda Series",
        "Seagate 2TB SATA3 - BarraCuda Series",
        "Kingston SSD Now SA400 SA400S37/480G SATA3",
        "ADATA SSD SU650 120GB SATA III ( R/W Up to 520 / 450MB/s ) ASU650SS-120GT-R",
        "Transcend TS512GMTE220S NVMe PCIe Gen3 x4 M.2 512GB"};

    public String[] storage2 = {"---Pilih Storage 2---",
        "WDC Purple 6TB For CCTV 24 Hours - WD62PURZ - Garansi 3 Th",
        "V-GeN SSD M.2 1TB",
        "Seagate Firecuda Gaming SSD 2TB",
        "Seagate 10TB For NAS - IronWolf Series",
        "Seagate 12TB For NAS - IronWolf Series"};

    public String[] psu = {"---Pilih Power Supply---",
        "Corsair CV Series 550W - 80 Plus Bronze",
        "Simbadda 500W",
        "Thermaltake TR2 S 600W",
        "Corsair HX Series 1200W Full Modular - Platinum",
        "Digital Alliance Gaming PSU 1300W 80+ Gold BTC"};

    public String[] vga = {"---Pilih VGA---",
        "Zotac GeForce RTX 2060 12GB GDDR6",
        "Gigabyte Quadro RTX5000 16GB DDR6 256 Bit",
        "MSI GeForce GTX 1650 SUPER 4GB DDR6",
        "Colorful GeForce GT 1030 2GB DDR4",
        "PALIT GeForce GTX 1050 Ti 4GB DDR5 StormX Series",
        "MSI GeForce RTX 3080 Ti 12GB GDDR6X - SUPRIM X"};

    public String[] casing = {"---Pilih Casing---",
        "PRIME T-[Q] - ALUMUNIUM GAMING CASE - DUAL SIDE TEMPERED GLASS",
        "CUBE GAMING EXORA - ATX - LEFT SIDE GLASS DOOR - FRONT RGB BAR",
        "PRIME D-[K] V2.0 - ALUMUNIUM GAMING CASE - DUAL SIDE TEMPERED GLASS",
        "CUBE GAMING NOCTURNE - SIDE TEMPERED GLASS - FRONT GLASS PANEL",
        "Corsair 4000D Airflow Tempered Glass ATX Case - Black"};
}
