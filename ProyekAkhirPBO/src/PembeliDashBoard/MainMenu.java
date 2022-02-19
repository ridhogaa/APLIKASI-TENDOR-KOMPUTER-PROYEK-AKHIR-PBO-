/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PembeliDashBoard;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.*;
import ConnectionDB.KoneksiDB;
import LoginRegister.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Evuaa
 */
public class MainMenu extends javax.swing.JFrame implements Format {

    private int height = 0;
    private int height_silper = 0;
    private int height_gold = 0;
    private int hargaProci, hargaMobo, hargaRam, hargaStorage1, hargaStorage2, hargaPSU, hargaVGA, hargaCasing;
    private int total = 0;
    private KoneksiDB db = new KoneksiDB();
    private boolean flagBronze, flagSilver, flagGold;
    private User user = LoginAndRegisterForm.user;
    private int[] arr = new int[8];
    private int grandtotal = 0;
    PemilihanKomponen pkm = new Produk();
    Produk pr = (Produk) pkm;
    private String status = "";
    boolean bronzeBenar = false;
    boolean silperBenar = false;
    boolean goldBenar = false;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/logoapk.png"));
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
        panel_profile.setVisible(false); // Ada Biodata
        //panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang 
        panel_dashboard.setVisible(true); // Ada Dashboard
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        panel_bronze.setVisible(false);
        panel_silper.setVisible(false);
        panel_gold.setVisible(false);
        cb_pilihprosesoramd.setVisible(false);
        cb_pilihprosesorintel.setVisible(false);
        cb_pilihprosesorkosong.setVisible(true);
        cb_pilihmoboamd.setVisible(false);
        cb_pilihmobointel.setVisible(false);
        cb_pilihmobokosong.setVisible(true);
        this.setResizable(false);
        scroll_tambahbarang.getVerticalScrollBar().setUnitIncrement(16);
        scrollpane_dashboard.getVerticalScrollBar().setUnitIncrement(16);
        scrollpane_stock.getVerticalScrollBar().setUnitIncrement(16);
        scrollpane_lihatuser.getVerticalScrollBar().setUnitIncrement(16);

        if (LoginAndRegisterForm.user.getUsername().equals("admin")) {
            nama_dashboard.setText(user.getFullname());
            total_pengguna.setText(banyakUser() + "");
            label_namalengkap.setText(user.getFullname());
            label_username.setText(user.getUsername());
            label_nohp.setText(user.getPhonenumber());
            label_email.setText(user.getEmail());
            total_point.setText(0 + " Points");
            total_transaksi.setText("Rp. 0" + "");
        } else {
            profileMember();
            nama_dashboard.setText(user.getFullname());
            panel_kat_administrator.setVisible(false);
            total_pengguna.setText(banyakUser() + "");
            label_namalengkap.setText(user.getFullname());
            label_username.setText(user.getUsername());
            label_nohp.setText(user.getPhonenumber());
            label_email.setText(user.getEmail());
            total_point.setText(totalPoint() + " Points");
            total_transaksi.setText(formatRp(getTransaksi()) + "");
        }
    }
    private String memberStatus = "";
    private int id_user = 0;
    private int total_transaksi_now = 0;

    public int totalPoint() {
        int points = 0;
        try {
            String query = "SELECT * FROM user WHERE username = '"
                    + user.getUsername() + "'";
            Statement st = db.getKoneksi().createStatement();
            ResultSet rsLogin = st.executeQuery(query);
            rsLogin.next();
            if (rsLogin.getRow() == 1) {
                id_user = rsLogin.getInt("ID");
                total_transaksi_now = rsLogin.getInt("total_transaction");
                try {
                    setTransaksi(rsLogin.getInt("total_transaction"));
                    String sql = "SELECT * FROM membership WHERE id = '" + id_user + "'";
                    Statement stm = db.getKoneksi().createStatement();
                    ResultSet rsLoginn = st.executeQuery(sql);
                    rsLoginn.next();
                    if (rsLoginn.getRow() == 1) {
                        points = rsLoginn.getInt("points");
                        memberStatus = rsLoginn.getString("status");
                    }
                } catch (Exception e) {

                }

            }
        } catch (Exception e) {
        }
        return points;
    }

    public void setTransaksi(int total) {
        this.total = total;
    }

    public int getTransaksi() {
        return total;
    }

    public int banyakUser() {
        int tampung = 0;
        try {
            String query = "SELECT id FROM user";
            Statement st = db.getKoneksi().createStatement();
            ResultSet rsLogin = st.executeQuery(query);
            while (rsLogin.next()) {
                tampung++;
            }
        } catch (Exception e) {

        }
        return tampung - 1;
    }

    public void statmember() {
        try {
            String query = "SELECT * FROM membership WHERE id = '" + user.getID() + "'";
            Statement st = db.getKoneksi().createStatement();
            ResultSet rsLogin = st.executeQuery(query);
            rsLogin.next();
            if (rsLogin.getRow() == 1) {

            }
        } catch (Exception e) {
        }
    }

    public void profileMember() {
        db.koneksiDatabase();
        try {
            Statement statement = db.getKoneksi().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM membership m INNER JOIN user u ON m.id = u.id WHERE u.username = '" + user.getUsername() + "'");
            rs.next();
            id = rs.getInt("ID");
            String st = rs.getString("status");
            if (st.equals("Bronze")) {
                icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERBRONZE.jpeg")));
                jLabel1.setVisible(false);
            } else if (st.equals("Silver")) {
                icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERSILPER.jpeg")));
                jLabel1.setVisible(false);
            } else if (st.equals("Gold")) {
                icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERGOLD.jpeg")));
                jLabel1.setVisible(false);
            }
        } catch (Exception e) {
        }
    }

    public void lebarKolom() {
        TableColumn column;
        tabel_stok.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_stok.getColumnModel().getColumn(0);
        column.setPreferredWidth(600);
        column = tabel_stok.getColumnModel().getColumn(1);
        column.setPreferredWidth(50);
        column = tabel_stok.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
    }

    public int totalAmount() {
        int tampung1 = 0;
        try {
            String query = "SELECT total_transaction FROM user";
            Statement st = db.getKoneksi().createStatement();
            ResultSet rsLogin = st.executeQuery(query);
            while (rsLogin.next()) {
                tampung1 += rsLogin.getInt("total_transaction");

            }

        } catch (Exception e) {
        }
        return tampung1;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_utama = new javax.swing.JPanel();
        label_logout = new javax.swing.JLabel();
        label_baristiga = new javax.swing.JLabel();
        label_baristigalagi = new javax.swing.JLabel();
        label_header = new javax.swing.JLabel();
        label_bg_baristiga = new javax.swing.JLabel();
        panel_bg_kategori = new javax.swing.JPanel();
        panel_kat_dashboard = new javax.swing.JPanel();
        label_db = new javax.swing.JLabel();
        panel_kat_tambahbarang = new javax.swing.JPanel();
        label_tbarang = new javax.swing.JLabel();
        panel_kat_checkbarang = new javax.swing.JPanel();
        label_cbarang = new javax.swing.JLabel();
        panel_kat_member = new javax.swing.JPanel();
        label_memb = new javax.swing.JLabel();
        panel_kat_profile = new javax.swing.JPanel();
        label_profile = new javax.swing.JLabel();
        panel_kat_faq = new javax.swing.JPanel();
        label_faq = new javax.swing.JLabel();
        panel_kat_aboutus = new javax.swing.JPanel();
        label_aboutus = new javax.swing.JLabel();
        panel_kat_administrator = new javax.swing.JPanel();
        label_admin = new javax.swing.JLabel();
        panel_container = new javax.swing.JPanel();
        panel_profile = new javax.swing.JPanel();
        icon_member = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        label_namalengkap = new javax.swing.JLabel();
        label_username = new javax.swing.JLabel();
        label_ic_nama = new javax.swing.JLabel();
        label_ic_wa = new javax.swing.JLabel();
        label_ic_email = new javax.swing.JLabel();
        label_email = new javax.swing.JLabel();
        label_updatepass = new javax.swing.JLabel();
        label_bg = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        label_nohp = new javax.swing.JLabel();
        scrollpane_dashboard = new javax.swing.JScrollPane();
        panel_dashboard = new javax.swing.JPanel();
        panel_db_welcome = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nama_dashboard = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        panel_db_tPengguna = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        total_pengguna = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        panel_db_totaltransaksi = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        total_transaksi = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        total_point = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btn_beliIntel = new javax.swing.JButton();
        btn_beliMobo = new javax.swing.JButton();
        btn_beliVga = new javax.swing.JButton();
        btn_beliCasis = new javax.swing.JButton();
        btn_beliAmd = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        scroll_tambahbarang = new javax.swing.JScrollPane();
        panel_tambah_barang = new javax.swing.JPanel();
        label_kodesimulasi = new javax.swing.JLabel();
        tf_kodesimulasi = new javax.swing.JTextField();
        button_konversi = new javax.swing.JButton();
        label_GTotal = new javax.swing.JLabel();
        cb_brandprosesor = new javax.swing.JComboBox<>();
        label_MCDesktop = new javax.swing.JLabel();
        cb_pilihprosesoramd = new javax.swing.JComboBox<>();
        cb_pilihmobointel = new javax.swing.JComboBox<>();
        cb_pilihram = new javax.swing.JComboBox<>();
        cb_storage1 = new javax.swing.JComboBox<>();
        cb_storage2 = new javax.swing.JComboBox<>();
        cb_psu = new javax.swing.JComboBox<>();
        cb_pilihvga = new javax.swing.JComboBox<>();
        tf_GTotal = new javax.swing.JTextField();
        tf_harga_prosesor = new javax.swing.JTextField();
        tf_harga_mobo = new javax.swing.JTextField();
        tf_harga_ram = new javax.swing.JTextField();
        tf_harga_sto1 = new javax.swing.JTextField();
        tf_harga_sto2 = new javax.swing.JTextField();
        tf_harga_psu = new javax.swing.JTextField();
        tf_harga_vga = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        label_pembayaran = new javax.swing.JLabel();
        tf_masukanpoin = new javax.swing.JTextField();
        label_txt_stock = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        label_masukanpoinanda = new javax.swing.JLabel();
        cb_pilihcasing = new javax.swing.JComboBox<>();
        tf_harga_casing = new javax.swing.JTextField();
        button_preview = new javax.swing.JButton();
        label_stokcasing = new javax.swing.JLabel();
        label_stokproci = new javax.swing.JLabel();
        label_stokmobo = new javax.swing.JLabel();
        label_stokram = new javax.swing.JLabel();
        label_stokst1 = new javax.swing.JLabel();
        label_stokst2 = new javax.swing.JLabel();
        label_stockpsu = new javax.swing.JLabel();
        label_stockvga = new javax.swing.JLabel();
        cb_pilihprosesorintel = new javax.swing.JComboBox<>();
        cb_pilihprosesorkosong = new javax.swing.JComboBox<>();
        cb_pilihmoboamd = new javax.swing.JComboBox<>();
        cb_pilihmobokosong = new javax.swing.JComboBox<>();
        label_harga1 = new javax.swing.JLabel();
        label_brandprocie = new javax.swing.JLabel();
        label_casing = new javax.swing.JLabel();
        label_mobo = new javax.swing.JLabel();
        label_ram = new javax.swing.JLabel();
        label_st1 = new javax.swing.JLabel();
        label_st2 = new javax.swing.JLabel();
        label_psu = new javax.swing.JLabel();
        label_vga = new javax.swing.JLabel();
        label_procie = new javax.swing.JLabel();
        label_masukanuanganda1 = new javax.swing.JLabel();
        tf_masukanuang = new javax.swing.JTextField();
        button_calculate = new javax.swing.JButton();
        ikon_backdb = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panel_member = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        label_bronze_open = new javax.swing.JLabel();
        label_bronze_close = new javax.swing.JLabel();
        label_silper_open = new javax.swing.JLabel();
        label_silper_close = new javax.swing.JLabel();
        label_gold_open = new javax.swing.JLabel();
        label_gold_close = new javax.swing.JLabel();
        button_bayar_memb = new javax.swing.JButton();
        panel_gold = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel50 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        panel_silper = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        panel_bronze = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        tf_masukanuang_memb = new javax.swing.JTextField();
        label_masukanuangmember = new javax.swing.JLabel();
        panel_admin = new javax.swing.JPanel();
        panel_lihatuserdanstock = new javax.swing.JPanel();
        label_txt_checkstock = new javax.swing.JLabel();
        label_checkstock = new javax.swing.JLabel();
        label_txt_searchuser = new javax.swing.JLabel();
        label_searchuser1 = new javax.swing.JLabel();
        scrollpane_lihatuser = new javax.swing.JScrollPane();
        panel_lihatuser = new javax.swing.JPanel();
        label_txtuser = new javax.swing.JLabel();
        label_totaluser = new javax.swing.JLabel();
        label_bg_totaluser = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_user = new javax.swing.JTable();
        ikon_backlihatuser = new javax.swing.JLabel();
        label_kembaliuser = new javax.swing.JLabel();
        scrollpane_stock = new javax.swing.JScrollPane();
        panel_stocktabel = new javax.swing.JPanel();
        combobarang = new javax.swing.JComboBox<>();
        cb_komponen = new javax.swing.JComboBox<>();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        spin_add = new javax.swing.JSpinner();
        btntambahstock = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_stok = new javax.swing.JTable();
        ikon_backlihatstok = new javax.swing.JLabel();
        label_backlihatstok = new javax.swing.JLabel();
        label_txt_pendapatan = new javax.swing.JLabel();
        label_pendapatan = new javax.swing.JLabel();
        panel_checkbarang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tf_procicb = new javax.swing.JTextField();
        tf_mobocb = new javax.swing.JTextField();
        tf_ramcb = new javax.swing.JTextField();
        tf_vgacb = new javax.swing.JTextField();
        tf_casingcb = new javax.swing.JTextField();
        tf_storage1cb = new javax.swing.JTextField();
        tf_storage2cb = new javax.swing.JTextField();
        tf_psucb = new javax.swing.JTextField();
        label_casingcb = new javax.swing.JLabel();
        label_prociecb = new javax.swing.JLabel();
        label_mobocb = new javax.swing.JLabel();
        label_ramcb = new javax.swing.JLabel();
        label_st1cb = new javax.swing.JLabel();
        label_st2cb = new javax.swing.JLabel();
        label_psucb = new javax.swing.JLabel();
        label_vgacb = new javax.swing.JLabel();
        ikon_back = new javax.swing.JLabel();
        label_back = new javax.swing.JLabel();
        panel_aboutus = new javax.swing.JPanel();
        label_about = new javax.swing.JLabel();
        panel_faq = new javax.swing.JPanel();
        label_FAQ = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TENDOR KOMPUTER");

        panel_utama.setMinimumSize(new java.awt.Dimension(1000, 625));
        panel_utama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Logout_32px_1.png"))); // NOI18N
        label_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_logoutMouseClicked(evt);
            }
        });
        panel_utama.add(label_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 20, 30, 30));

        label_baristiga.setBackground(new java.awt.Color(255, 255, 255));
        label_baristiga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_baristiga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_menu_40px.png"))); // NOI18N
        label_baristiga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_baristiga.setOpaque(true);
        label_baristiga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_baristigaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_baristigaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_baristigaMouseExited(evt);
            }
        });
        panel_utama.add(label_baristiga, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 40, 40));

        label_baristigalagi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_baristigalagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_menu_40px.png"))); // NOI18N
        label_baristigalagi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_baristigalagi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_baristigalagiMouseClicked(evt);
            }
        });
        panel_utama.add(label_baristigalagi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 40, 40));

        label_header.setBackground(new java.awt.Color(255, 255, 255));
        label_header.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/bisa.jpg"))); // NOI18N
        label_header.setOpaque(true);
        panel_utama.add(label_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1200, 74));

        label_bg_baristiga.setBackground(new java.awt.Color(255, 255, 255));
        label_bg_baristiga.setOpaque(true);
        panel_utama.add(label_bg_baristiga, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 74));

        panel_bg_kategori.setBackground(new java.awt.Color(54, 70, 78));
        panel_bg_kategori.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 0));
        panel_bg_kategori.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_kat_dashboard.setBackground(new java.awt.Color(54, 70, 78));
        panel_kat_dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_db.setBackground(new java.awt.Color(54, 70, 78));
        label_db.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_db.setForeground(new java.awt.Color(255, 255, 255));
        label_db.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_home_40px.png"))); // NOI18N
        label_db.setText("     Dashboard");
        label_db.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_db.setOpaque(true);
        label_db.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_dbMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_dbMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_dbMouseExited(evt);
            }
        });
        panel_kat_dashboard.add(label_db, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, 40));

        panel_bg_kategori.add(panel_kat_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, 50));

        panel_kat_tambahbarang.setBackground(new java.awt.Color(54, 70, 78));
        panel_kat_tambahbarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_tbarang.setBackground(new java.awt.Color(54, 70, 78));
        label_tbarang.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_tbarang.setForeground(new java.awt.Color(255, 255, 255));
        label_tbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_add_shopping_cart_40px.png"))); // NOI18N
        label_tbarang.setText("     Tambahkan Barang");
        label_tbarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_tbarang.setOpaque(true);
        label_tbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_tbarangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_tbarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_tbarangMouseExited(evt);
            }
        });
        panel_kat_tambahbarang.add(label_tbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, 40));

        panel_bg_kategori.add(panel_kat_tambahbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 210, -1));

        panel_kat_checkbarang.setBackground(new java.awt.Color(54, 70, 78));
        panel_kat_checkbarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_cbarang.setBackground(new java.awt.Color(54, 70, 78));
        label_cbarang.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_cbarang.setForeground(new java.awt.Color(255, 255, 255));
        label_cbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_list_view_40px.png"))); // NOI18N
        label_cbarang.setText("     Check Barang");
        label_cbarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_cbarang.setOpaque(true);
        label_cbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_cbarangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_cbarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_cbarangMouseExited(evt);
            }
        });
        panel_kat_checkbarang.add(label_cbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, 40));

        panel_bg_kategori.add(panel_kat_checkbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 210, 50));

        panel_kat_member.setBackground(new java.awt.Color(54, 70, 78));
        panel_kat_member.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_memb.setBackground(new java.awt.Color(54, 70, 78));
        label_memb.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_memb.setForeground(new java.awt.Color(255, 255, 255));
        label_memb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_conference_40px.png"))); // NOI18N
        label_memb.setText("     Member");
        label_memb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_memb.setOpaque(true);
        label_memb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_membMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_membMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_membMouseExited(evt);
            }
        });
        panel_kat_member.add(label_memb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, 40));

        panel_bg_kategori.add(panel_kat_member, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 210, 50));

        panel_kat_profile.setBackground(new java.awt.Color(54, 70, 78));
        panel_kat_profile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_profile.setBackground(new java.awt.Color(54, 70, 78));
        label_profile.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_profile.setForeground(new java.awt.Color(255, 255, 255));
        label_profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_administrative_tools_40px.png"))); // NOI18N
        label_profile.setText("     Profile");
        label_profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_profile.setOpaque(true);
        label_profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_profileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_profileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_profileMouseExited(evt);
            }
        });
        panel_kat_profile.add(label_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, 40));

        panel_bg_kategori.add(panel_kat_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 210, 50));

        panel_kat_faq.setBackground(new java.awt.Color(54, 70, 78));
        panel_kat_faq.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_faq.setBackground(new java.awt.Color(54, 70, 78));
        label_faq.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_faq.setForeground(new java.awt.Color(255, 255, 255));
        label_faq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_magazine_40px.png"))); // NOI18N
        label_faq.setText("     FAQ");
        label_faq.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_faq.setOpaque(true);
        label_faq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_faqMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_faqMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_faqMouseExited(evt);
            }
        });
        panel_kat_faq.add(label_faq, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, 40));

        panel_bg_kategori.add(panel_kat_faq, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 210, 50));

        panel_kat_aboutus.setBackground(new java.awt.Color(54, 70, 78));
        panel_kat_aboutus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_aboutus.setBackground(new java.awt.Color(54, 70, 78));
        label_aboutus.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_aboutus.setForeground(new java.awt.Color(255, 255, 255));
        label_aboutus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_about_40px.png"))); // NOI18N
        label_aboutus.setText("      About Us");
        label_aboutus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_aboutus.setOpaque(true);
        label_aboutus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_aboutusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_aboutusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_aboutusMouseExited(evt);
            }
        });
        panel_kat_aboutus.add(label_aboutus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, 40));

        panel_bg_kategori.add(panel_kat_aboutus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 210, 50));

        panel_kat_administrator.setBackground(new java.awt.Color(54, 70, 78));
        panel_kat_administrator.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_admin.setBackground(new java.awt.Color(54, 70, 78));
        label_admin.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_admin.setForeground(new java.awt.Color(255, 255, 255));
        label_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_user_shield_40px.png"))); // NOI18N
        label_admin.setText("     Administator");
        label_admin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_admin.setOpaque(true);
        label_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_adminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_adminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_adminMouseExited(evt);
            }
        });
        panel_kat_administrator.add(label_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -2, 200, 50));

        panel_bg_kategori.add(panel_kat_administrator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 210, 50));

        panel_utama.add(panel_bg_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 210, 650));

        panel_container.setOpaque(false);
        panel_container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_profile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon_member.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/user.png"))); // NOI18N
        panel_profile.add(icon_member, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 200, 190));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/square.png"))); // NOI18N
        panel_profile.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 250, 240));

        label_namalengkap.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        panel_profile.add(label_namalengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 350, 40));

        label_username.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        panel_profile.add(label_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 350, 40));

        label_ic_nama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ic_nama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_name_tag_40px_1.png"))); // NOI18N
        panel_profile.add(label_ic_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 30, 40));

        label_ic_wa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ic_wa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_whatsapp_48px.png"))); // NOI18N
        panel_profile.add(label_ic_wa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 40, 40));

        label_ic_email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ic_email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_send_email_40px.png"))); // NOI18N
        panel_profile.add(label_ic_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 40, 50));

        label_email.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        panel_profile.add(label_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 350, 40));

        label_updatepass.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        label_updatepass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_change_40px.png"))); // NOI18N
        label_updatepass.setText("Update Password");
        label_updatepass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_updatepass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_updatepassMouseClicked(evt);
            }
        });
        panel_profile.add(label_updatepass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 170, 40));

        label_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rsz_—pngtree—dark_vector_abstract_background_1159556.png"))); // NOI18N
        panel_profile.add(label_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 1280, 190));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/id-card.png"))); // NOI18N
        panel_profile.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 40, 40));

        label_nohp.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        panel_profile.add(label_nohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 350, 40));

        panel_container.add(panel_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 1070, 650));

        scrollpane_dashboard.setBorder(null);
        scrollpane_dashboard.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel_dashboard.setPreferredSize(new java.awt.Dimension(1050, 1300));
        panel_dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_db_welcome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Welcome");
        panel_db_welcome.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 210, 70));

        nama_dashboard.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 24)); // NOI18N
        nama_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        panel_db_welcome.add(nama_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 470, 60));

        jButton1.setBackground(new java.awt.Color(0, 102, 204));
        jButton1.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Belanja Sekarang!");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        panel_db_welcome.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 150, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/background.jpg"))); // NOI18N
        panel_db_welcome.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 630, 230));

        panel_dashboard.add(panel_db_welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 680, 250));

        panel_db_tPengguna.setForeground(new java.awt.Color(255, 255, 255));
        panel_db_tPengguna.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/user_120px1.png"))); // NOI18N
        panel_db_tPengguna.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 120, 120));

        jLabel14.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Pengguna");
        panel_db_tPengguna.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 120, 40));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/analysis (2).png"))); // NOI18N
        panel_db_tPengguna.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 80, 80));

        total_pengguna.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 36)); // NOI18N
        total_pengguna.setForeground(new java.awt.Color(255, 255, 255));
        panel_db_tPengguna.add(total_pengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 170, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/vecteezyabstract-background-panoramicRD0421-01_generated.jpg"))); // NOI18N
        panel_db_tPengguna.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 360, 200));

        panel_dashboard.add(panel_db_tPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 400, 220));

        panel_db_totaltransaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Total Transaksi Anda");
        panel_db_totaltransaksi.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 180, 40));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/transaction_120px.png"))); // NOI18N
        panel_db_totaltransaksi.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 130, 100));

        total_transaksi.setFont(new java.awt.Font("Lucida Sans", 1, 24)); // NOI18N
        total_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        panel_db_totaltransaksi.add(total_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 200, 60));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/analysis (2).png"))); // NOI18N
        panel_db_totaltransaksi.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 80, 70));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/vecteezyabstract-background-panoramicRD0421-01_generated.jpg"))); // NOI18N
        panel_db_totaltransaksi.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 380, 170));

        panel_dashboard.add(panel_db_totaltransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 400, 170));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        total_point.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 24)); // NOI18N
        total_point.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(total_point, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 220, 60));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/compass (1).png"))); // NOI18N
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/analysis (2).png"))); // NOI18N
        jPanel2.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 80, 70));

        jLabel34.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Total Poin Anda");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/vecteezyabstract-background-panoramicRD0421-01_generated.jpg"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 380, 160));

        panel_dashboard.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 400, 170));

        jSeparator4.setForeground(new java.awt.Color(51, 0, 255));
        panel_dashboard.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 1070, 10));

        btn_beliIntel.setBackground(new java.awt.Color(51, 0, 255));
        btn_beliIntel.setText("Beli");
        btn_beliIntel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_beliIntel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_beliIntel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_beliIntelMouseClicked(evt);
            }
        });
        panel_dashboard.add(btn_beliIntel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 730, 50, -1));

        btn_beliMobo.setBackground(new java.awt.Color(102, 0, 255));
        btn_beliMobo.setText("Beli");
        btn_beliMobo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_beliMobo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_beliMobo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_beliMoboMouseClicked(evt);
            }
        });
        panel_dashboard.add(btn_beliMobo, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 890, 50, -1));

        btn_beliVga.setBackground(new java.awt.Color(102, 0, 255));
        btn_beliVga.setText("Beli");
        btn_beliVga.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_beliVga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_beliVga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_beliVgaMouseClicked(evt);
            }
        });
        panel_dashboard.add(btn_beliVga, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 1240, 50, -1));

        btn_beliCasis.setBackground(new java.awt.Color(153, 0, 255));
        btn_beliCasis.setText("Beli");
        btn_beliCasis.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_beliCasis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_beliCasis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_beliCasisMouseClicked(evt);
            }
        });
        panel_dashboard.add(btn_beliCasis, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1100, 50, -1));

        btn_beliAmd.setBackground(new java.awt.Color(102, 0, 255));
        btn_beliAmd.setText("Beli");
        btn_beliAmd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_beliAmd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_beliAmd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_beliAmdMouseClicked(evt);
            }
        });
        panel_dashboard.add(btn_beliAmd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 890, 40, -1));

        jLabel18.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 204));
        jLabel18.setText("NEWS");
        panel_dashboard.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 340, 60));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rsz_amd.jpg"))); // NOI18N
        panel_dashboard.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 770, 610, 170));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rsz_intelxrog.jpg"))); // NOI18N
        panel_dashboard.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 630, 170));

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rsz_iklan_mobo.jpg"))); // NOI18N
        panel_dashboard.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 590, 340, 340));

        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rsz_iklan_vga.jpg"))); // NOI18N
        panel_dashboard.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 950, 550, 330));

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rsz_1casing.jpg"))); // NOI18N
        panel_dashboard.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 950, 330, 330));

        scrollpane_dashboard.setViewportView(panel_dashboard);

        panel_container.add(scrollpane_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 1070, 650));

        scroll_tambahbarang.setBorder(null);
        scroll_tambahbarang.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll_tambahbarang.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_tambahbarang.setViewportView(null);

        panel_tambah_barang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 0));
        panel_tambah_barang.setPreferredSize(new java.awt.Dimension(1150, 1150));
        panel_tambah_barang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_kodesimulasi.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_kodesimulasi.setText("Kode Simulasi");
        panel_tambah_barang.add(label_kodesimulasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 160, 30));
        panel_tambah_barang.add(tf_kodesimulasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 360, 50));

        button_konversi.setBackground(new java.awt.Color(0, 51, 204));
        button_konversi.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        button_konversi.setForeground(new java.awt.Color(255, 255, 255));
        button_konversi.setText("Konversi Poin");
        button_konversi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_konversi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_konversiMouseClicked(evt);
            }
        });
        panel_tambah_barang.add(button_konversi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 870, 130, 50));

        label_GTotal.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_GTotal.setText("Grand Total");
        panel_tambah_barang.add(label_GTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 820, 120, -1));

        cb_brandprosesor.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_brandprosesor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Brand---", "Intel", "AMD" }));
        cb_brandprosesor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_brandprosesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_brandprosesorActionPerformed(evt);
            }
        });
        cb_brandprosesor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cb_brandprosesorKeyReleased(evt);
            }
        });
        panel_tambah_barang.add(cb_brandprosesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 490, 50));

        label_MCDesktop.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_MCDesktop.setText("Main Component Desktop");
        panel_tambah_barang.add(label_MCDesktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 250, -1));

        cb_pilihprosesoramd.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_pilihprosesoramd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Processor---", "AMD Ryzen 9 5900X 3.7Ghz Up To 4.8Ghz Cache 64MB 105W AM4 [Box] - 12 Core", "AMD Ryzen 5 3500 3.6Ghz Up To 4.1Ghz Cache 16MB 65W AM4 [Box] - 6 Core", "AMD Ryzen 7 5800X 3.8Ghz Up To 4.7Ghz Cache 32MB 105W AM4 [Box] - 8 Core", "AMD Athlon 3000G (Radeon Vega 3) 3.5Ghz Cache 4MB 35W Socket AM4 [BOX] - 2 Core", "AMD Ryzen 5 5600X 3.7Ghz Up To 4.6Ghz Cache 32MB 65W AM4 [Box] - 6 Core" }));
        cb_pilihprosesoramd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_pilihprosesoramd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihprosesoramdActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_pilihprosesoramd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 490, 50));

        cb_pilihmobointel.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_pilihmobointel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Motherboard Intel---", "MSI PRO B660M-A Wifi DDR4 (LGA1700 B660 DDR4 USB3.2 SATA3)", "Gigabyte Z690 Aorus Elite AX DDR4 (LGA1700 Z690DDR4 USB3.2 SATA3)", "Asus ROG Strix Z690-F Gaming WIFI (LGA1700 Z690 DDR5 USB3.2 SATA3)", "Asus ROG Strix B660-A Gaming WIFI D4 (LGA1700 B660 DDR4 USB3.2SATA3)", "MSI MAG Z690 Tomahawk WiFi DDR4 (LGA1700 Z690 DDR4USB3.2 SATA3)" }));
        cb_pilihmobointel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_pilihmobointel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihmobointelActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_pilihmobointel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 490, 50));

        cb_pilihram.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_pilihram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih RAM---", "Corsair DDR4 Vengeance LPX PC19200 4GB (1X4GB)", "V-GeN Platinum DDR4 8GB PC17000", "Team Elite Plus Black DDR4 PC19200 2400Mhz Dual Channel 8GB (2X4GB)", "Team Elite Plus Black DDR4 PC21000 2666Mhz 8GB", "Team EXtreme DDR4 PC28800 3600Mhz Dual Channel 16GB (2x8GB)" }));
        cb_pilihram.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_pilihram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihramActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_pilihram, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 490, 50));

        cb_storage1.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_storage1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Storage 1---", "WDC 1TB SATA3 64MB - Blue - WD10EZEX - Garansi 2 Th", "Seagate 3TB SATA3 256MB - BarraCuda Series", "Seagate 2TB SATA3 - BarraCuda Series", "Kingston SSD Now SA400 SA400S37/480G SATA3", "ADATA SSD SU650 120GB SATA III ( R/W Up to 520 / 450MB/s ) ASU650SS-120GT-R", "Transcend TS512GMTE220S NVMe PCIe Gen3 x4 M.2 512GB" }));
        cb_storage1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_storage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_storage1ActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_storage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 490, 50));

        cb_storage2.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_storage2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Storage 2---", "WDC Purple 6TB For CCTV 24 Hours - WD62PURZ - Garansi 3 Th", "V-GeN SSD M.2 1TB", "Seagate Firecuda Gaming SSD 2TB", "Seagate 10TB For NAS - IronWolf Series", "Seagate 12TB For NAS - IronWolf Series" }));
        cb_storage2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_storage2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_storage2ActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_storage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 530, 490, 50));

        cb_psu.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_psu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Power Supply---", "Corsair CV Series 550W - 80 Plus Bronze", "Simbadda 500W", "Thermaltake TR2 S 600W", "Corsair HX Series 1200W Full Modular - Platinum", "Digital Alliance Gaming PSU 1300W 80+ Gold BTC" }));
        cb_psu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_psu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_psuActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_psu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 600, 490, 50));

        cb_pilihvga.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_pilihvga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih VGA---", "Zotac GeForce RTX 2060 12GB GDDR6", "Gigabyte Quadro RTX5000 16GB DDR6 256 Bit", "MSI GeForce GTX 1650 SUPER 4GB DDR6", "Colorful GeForce GT 1030 2GB DDR4", "PALIT GeForce GTX 1050 Ti 4GB DDR5 StormX Series", "MSI GeForce RTX 3080 Ti 12GB GDDR6X - SUPRIM X" }));
        cb_pilihvga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_pilihvga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihvgaActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_pilihvga, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, 490, 50));

        tf_GTotal.setEditable(false);
        tf_GTotal.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        tf_GTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_GTotalActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(tf_GTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 810, 290, 50));

        tf_harga_prosesor.setEditable(false);
        tf_harga_prosesor.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        tf_harga_prosesor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_harga_prosesorKeyReleased(evt);
            }
        });
        panel_tambah_barang.add(tf_harga_prosesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 290, 50));

        tf_harga_mobo.setEditable(false);
        tf_harga_mobo.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        panel_tambah_barang.add(tf_harga_mobo, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, 290, 50));

        tf_harga_ram.setEditable(false);
        tf_harga_ram.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        panel_tambah_barang.add(tf_harga_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 400, 290, 50));

        tf_harga_sto1.setEditable(false);
        tf_harga_sto1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        panel_tambah_barang.add(tf_harga_sto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 460, 290, 50));

        tf_harga_sto2.setEditable(false);
        tf_harga_sto2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        panel_tambah_barang.add(tf_harga_sto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 530, 290, 50));

        tf_harga_psu.setEditable(false);
        tf_harga_psu.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        panel_tambah_barang.add(tf_harga_psu, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 600, 290, 50));

        tf_harga_vga.setEditable(false);
        tf_harga_vga.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        panel_tambah_barang.add(tf_harga_vga, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 660, 290, 50));

        jSeparator1.setForeground(new java.awt.Color(0, 51, 204));
        panel_tambah_barang.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 940, 1000, 10));

        label_pembayaran.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_pembayaran.setText("Pembayaran");
        panel_tambah_barang.add(label_pembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 900, 120, -1));

        tf_masukanpoin.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_masukanpoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_masukanpoinActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(tf_masukanpoin, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 870, 210, 50));

        label_txt_stock.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_txt_stock.setText("Stock");
        panel_tambah_barang.add(label_txt_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 160, 60, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 204));
        panel_tambah_barang.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 1100, 10));

        jSeparator3.setForeground(new java.awt.Color(0, 51, 204));
        panel_tambah_barang.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 790, 1000, 10));

        label_masukanpoinanda.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_masukanpoinanda.setText("Masukan Poin Anda");
        panel_tambah_barang.add(label_masukanpoinanda, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 880, 190, -1));

        cb_pilihcasing.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_pilihcasing.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Casing---", "PRIME T-[Q] - ALUMUNIUM GAMING CASE - DUAL SIDE TEMPERED GLASS", "CUBE GAMING EXORA - ATX - LEFT SIDE GLASS DOOR - FRONT RGB BAR", "PRIME D-[K] V2.0 - ALUMUNIUM GAMING CASE - DUAL SIDE TEMPERED GLASS", "CUBE GAMING NOCTURNE - SIDE TEMPERED GLASS - FRONT GLASS PANEL", "Corsair 4000D Airflow Tempered Glass ATX Case - Black" }));
        cb_pilihcasing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_pilihcasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihcasingActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_pilihcasing, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 730, 490, 50));

        tf_harga_casing.setEditable(false);
        tf_harga_casing.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        panel_tambah_barang.add(tf_harga_casing, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 730, 290, 50));

        button_preview.setBackground(new java.awt.Color(0, 51, 204));
        button_preview.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        button_preview.setForeground(new java.awt.Color(255, 255, 255));
        button_preview.setText("Preview");
        button_preview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_preview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_previewActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(button_preview, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 80, 50));

        label_stokcasing.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_stokcasing.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_tambah_barang.add(label_stokcasing, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 730, 60, 50));

        label_stokproci.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_stokproci.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_tambah_barang.add(label_stokproci, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 270, 60, 50));

        label_stokmobo.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_stokmobo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_tambah_barang.add(label_stokmobo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 340, 60, 50));

        label_stokram.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_stokram.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_tambah_barang.add(label_stokram, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 400, 60, 50));

        label_stokst1.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_stokst1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_tambah_barang.add(label_stokst1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 460, 60, 50));

        label_stokst2.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_stokst2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_tambah_barang.add(label_stokst2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 530, 60, 50));

        label_stockpsu.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_stockpsu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_tambah_barang.add(label_stockpsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 600, 60, 50));

        label_stockvga.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_stockvga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_tambah_barang.add(label_stockvga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 660, 60, 50));

        cb_pilihprosesorintel.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_pilihprosesorintel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Processor---", "Intel Core i5-12600K 3.7GHz Up to 4.9GHz - Cache 20Mb - LGA 1700 - Alder Lake Series", "Intel Core i5-12700KF 3.6GHz Up to 5.0GHz - Cache 25Mb - LGA 1700 - Alder Lake Series", "Intel Core i5-12500 3.0GHz Up to 4.6GHz - Cache 18Mb - LGA 1700 - Alder Lake Series", "Intel Core i7-12700K 3.6GHz Up to 5.0GHz - Cache 25Mb - LGA 1700 - Alder Lake Series", "Intel Core i9-12900 2.4GHz Up to 5.1GHz - Cache 30 Mb - LGA 1700 - Alder Lake Series" }));
        cb_pilihprosesorintel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_pilihprosesorintel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihprosesorintelActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_pilihprosesorintel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 490, 50));

        cb_pilihprosesorkosong.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_pilihprosesorkosong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Silahkan Pilih Brand Processor ---", " ", " ", " ", " ", " ", " " }));
        cb_pilihprosesorkosong.setEnabled(false);
        cb_pilihprosesorkosong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihprosesorkosongActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_pilihprosesorkosong, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 490, 50));

        cb_pilihmoboamd.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_pilihmoboamd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Motherboard AMD---", "Biostar A320MH (AM4, AMD Promontory A320, DDR4, USB 3.1, SATA3)", "ASRock A320M-HDV (AM4, AMD Promontory A320, DDR4, USB 3.1, SATA3)", "Asus TUF B450M-PLUS Gaming (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)", "Biostar Racing B450GT3 (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)", "Gigabyte B450 Aorus Elite (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)" }));
        cb_pilihmoboamd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_pilihmoboamd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihmoboamdActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(cb_pilihmoboamd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 490, 50));

        cb_pilihmobokosong.setFont(new java.awt.Font("Lucida Sans", 0, 9)); // NOI18N
        cb_pilihmobokosong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Silahkan Pilih Brand Processor---", " " }));
        cb_pilihmobokosong.setEnabled(false);
        panel_tambah_barang.add(cb_pilihmobokosong, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 490, 50));

        label_harga1.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_harga1.setText("Harga");
        panel_tambah_barang.add(label_harga1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 60, -1));

        label_brandprocie.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_brandprocie.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_brandprocie.setText("Brand Processor");
        panel_tambah_barang.add(label_brandprocie, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 160, 50));

        label_casing.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_casing.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_casing.setText("Casing");
        panel_tambah_barang.add(label_casing, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 730, 140, 50));

        label_mobo.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_mobo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_mobo.setText("Motherboard");
        panel_tambah_barang.add(label_mobo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 140, 50));

        label_ram.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_ram.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_ram.setText("RAM");
        panel_tambah_barang.add(label_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 140, 50));

        label_st1.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_st1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_st1.setText("Storage 1");
        panel_tambah_barang.add(label_st1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 140, 50));

        label_st2.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_st2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_st2.setText("Storage 2");
        panel_tambah_barang.add(label_st2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 140, 50));

        label_psu.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_psu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_psu.setText("Power Supply");
        panel_tambah_barang.add(label_psu, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 140, 50));

        label_vga.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_vga.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_vga.setText("VGA");
        panel_tambah_barang.add(label_vga, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 660, 140, 50));

        label_procie.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_procie.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_procie.setText("Processor");
        panel_tambah_barang.add(label_procie, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 140, 50));

        label_masukanuanganda1.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_masukanuanganda1.setText("Masukan Uang Anda");
        panel_tambah_barang.add(label_masukanuanganda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 970, 190, -1));

        tf_masukanuang.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_masukanuang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_masukanuangActionPerformed(evt);
            }
        });
        panel_tambah_barang.add(tf_masukanuang, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 960, 300, 50));

        button_calculate.setBackground(new java.awt.Color(0, 51, 204));
        button_calculate.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        button_calculate.setForeground(new java.awt.Color(255, 255, 255));
        button_calculate.setText("Bayar Sekarang!");
        button_calculate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_calculate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_calculateMouseClicked(evt);
            }
        });
        panel_tambah_barang.add(button_calculate, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 1020, 300, 40));

        ikon_backdb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/left-arrow (1).png"))); // NOI18N
        ikon_backdb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ikon_backdbMouseClicked(evt);
            }
        });
        panel_tambah_barang.add(ikon_backdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 210, 40));

        jLabel15.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 153, 255));
        jLabel15.setText("Kembali ke Dashboard");
        panel_tambah_barang.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        scroll_tambahbarang.setViewportView(panel_tambah_barang);

        panel_container.add(scroll_tambahbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 1070, 650));

        panel_member.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel65.setFont(new java.awt.Font("Lucida Bright", 1, 36)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("MEMBERSHIP");
        panel_member.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 270, 70));

        jSeparator5.setForeground(new java.awt.Color(0, 51, 204));
        panel_member.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 880, 10));

        label_bronze_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERBRONZE.jpeg"))); // NOI18N
        label_bronze_open.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_bronze_open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_bronze_openMouseClicked(evt);
            }
        });
        panel_member.add(label_bronze_open, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, 250));

        label_bronze_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERBRONZE.jpeg"))); // NOI18N
        label_bronze_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_bronze_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_bronze_closeMouseClicked(evt);
            }
        });
        panel_member.add(label_bronze_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 210, 250));

        label_silper_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERSILPER.jpeg"))); // NOI18N
        label_silper_open.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_silper_open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_silper_openMouseClicked(evt);
            }
        });
        panel_member.add(label_silper_open, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, 250));

        label_silper_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERSILPER.jpeg"))); // NOI18N
        label_silper_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_silper_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_silper_closeMouseClicked(evt);
            }
        });
        panel_member.add(label_silper_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, 250));

        label_gold_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERGOLD.jpeg"))); // NOI18N
        label_gold_open.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_gold_open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_gold_openMouseClicked(evt);
            }
        });
        panel_member.add(label_gold_open, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, -1, 250));

        label_gold_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERGOLD.jpeg"))); // NOI18N
        label_gold_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_gold_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_gold_closeMouseClicked(evt);
            }
        });
        panel_member.add(label_gold_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, -1, 250));

        button_bayar_memb.setBackground(new java.awt.Color(0, 51, 204));
        button_bayar_memb.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        button_bayar_memb.setForeground(new java.awt.Color(255, 255, 255));
        button_bayar_memb.setText("Bayar Sekarang");
        button_bayar_memb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_bayar_memb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_bayar_membActionPerformed(evt);
            }
        });
        panel_member.add(button_bayar_memb, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 580, 240, 40));

        panel_gold.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel_gold.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel30.setText("Rp. 80.000");
        panel_gold.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));
        panel_gold.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 10));

        jLabel50.setText("✓  Mendapatkan 20 point tanpa");
        panel_gold.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel54.setText("  pembelian pertama");
        panel_gold.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel44.setText("✓  Rp. 3000 / 1 point untuk  ");
        panel_gold.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel29.setText("penukaran");
        panel_gold.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        panel_member.add(panel_gold, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 210, 200));

        panel_silper.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel_silper.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel42.setText("Rp. 60.000 ");
        panel_silper.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));
        panel_silper.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 10));

        jLabel55.setText("✓  Mendapatkan 15 point tanpa");
        panel_silper.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel56.setText("  pembelian pertama");
        panel_silper.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel57.setText("✓  Rp. 2.500 / 1 point untuk  ");
        panel_silper.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel58.setText("penukaran");
        panel_silper.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        panel_member.add(panel_silper, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 210, 200));

        panel_bronze.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel_bronze.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel41.setText("Rp. 50.000");
        panel_bronze.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));
        panel_bronze.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 10));

        jLabel51.setText("✓  Mendapatkan 10 point tanpa");
        panel_bronze.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel52.setText("  pembelian pertama");
        panel_bronze.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel53.setText("✓  Rp. 2000 / 1 point untuk penukaran ");
        panel_bronze.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        panel_member.add(panel_bronze, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 210, 200));
        panel_member.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, 910, 10));

        tf_masukanuang_memb.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        tf_masukanuang_memb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_member.add(tf_masukanuang_memb, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 530, 240, 40));

        label_masukanuangmember.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        label_masukanuangmember.setText("Masukan Uang Anda");
        panel_member.add(label_masukanuangmember, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 540, 160, -1));

        panel_container.add(panel_member, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 1070, 650));

        panel_admin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_lihatuserdanstock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_txt_checkstock.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        label_txt_checkstock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_txt_checkstock.setText("Check Stock");
        panel_lihatuserdanstock.add(label_txt_checkstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 150, 30));

        label_checkstock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_checkstock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/trolley.png"))); // NOI18N
        label_checkstock.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label_checkstock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_checkstock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_checkstockMouseClicked(evt);
            }
        });
        panel_lihatuserdanstock.add(label_checkstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 280, 350));

        label_txt_searchuser.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        label_txt_searchuser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_txt_searchuser.setText("Search User");
        panel_lihatuserdanstock.add(label_txt_searchuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 150, 40));

        label_searchuser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_searchuser1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/user (2).png"))); // NOI18N
        label_searchuser1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label_searchuser1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_searchuser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_searchuser1MouseClicked(evt);
            }
        });
        panel_lihatuserdanstock.add(label_searchuser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 260, 360));

        panel_admin.add(panel_lihatuserdanstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1030, 610));

        scrollpane_lihatuser.setBorder(null);
        scrollpane_lihatuser.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollpane_lihatuser.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollpane_lihatuser.setPreferredSize(new java.awt.Dimension(1100, 1100));

        panel_lihatuser.setMinimumSize(new java.awt.Dimension(1150, 540));
        panel_lihatuser.setPreferredSize(new java.awt.Dimension(1150, 650));
        panel_lihatuser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_txtuser.setBackground(new java.awt.Color(255, 255, 255));
        label_txtuser.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_txtuser.setText("Total Users :");
        panel_lihatuser.add(label_txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 120, 50));

        label_totaluser.setBackground(new java.awt.Color(255, 255, 255));
        label_totaluser.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_totaluser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_lihatuser.add(label_totaluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, 100, 50));

        label_bg_totaluser.setBackground(new java.awt.Color(255, 255, 255));
        label_bg_totaluser.setForeground(new java.awt.Color(255, 255, 255));
        panel_lihatuser.add(label_bg_totaluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 1350, 70));
        panel_lihatuser.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 1320, 20));

        jScrollPane1.setBorder(null);

        tabel_user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_user.setEnabled(false);
        tabel_user.setShowGrid(true);
        jScrollPane1.setViewportView(tabel_user);

        panel_lihatuser.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1110, -1));

        ikon_backlihatuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/left-arrow (1).png"))); // NOI18N
        ikon_backlihatuser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ikon_backlihatuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ikon_backlihatuserMouseClicked(evt);
            }
        });
        panel_lihatuser.add(ikon_backlihatuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, -1));

        label_kembaliuser.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        label_kembaliuser.setForeground(new java.awt.Color(51, 102, 255));
        label_kembaliuser.setText("Kembali");
        panel_lihatuser.add(label_kembaliuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        scrollpane_lihatuser.setViewportView(panel_lihatuser);

        panel_admin.add(scrollpane_lihatuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 650));

        scrollpane_stock.setBorder(null);
        scrollpane_stock.setPreferredSize(new java.awt.Dimension(1100, 1100));

        panel_stocktabel.setMinimumSize(new java.awt.Dimension(1400, 540));
        panel_stocktabel.setPreferredSize(new java.awt.Dimension(1000, 950));
        panel_stocktabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combobarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilihan tidak tersedia!--" }));
        combobarang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        combobarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combobarang.setEnabled(false);
        panel_stocktabel.add(combobarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 710, 550, 50));

        cb_komponen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Komponen--", "Processor", "Motherboard", "RAM", "Storage 1", "Storage 2", "Power Supply", "VGA", "Casing" }));
        cb_komponen.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cb_komponen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_komponen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_komponenMouseClicked(evt);
            }
        });
        cb_komponen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_komponenActionPerformed(evt);
            }
        });
        panel_stocktabel.add(cb_komponen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 710, 190, 50));

        jSeparator10.setForeground(new java.awt.Color(51, 0, 204));
        panel_stocktabel.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 1380, 20));

        jLabel8.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 0, 255));
        jLabel8.setText("Tambah Stok");
        panel_stocktabel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 130, 40));

        jLabel12.setForeground(new java.awt.Color(51, 0, 204));
        jLabel12.setText("Total yang Ditambahkan");
        panel_stocktabel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 680, -1, -1));

        jLabel16.setForeground(new java.awt.Color(0, 0, 204));
        jLabel16.setText("Pilih Tipe");
        panel_stocktabel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 680, 90, -1));

        jLabel21.setForeground(new java.awt.Color(51, 0, 204));
        jLabel21.setText("Pilih Komponen");
        panel_stocktabel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 680, -1, -1));

        spin_add.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        spin_add.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        spin_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        spin_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                spin_addKeyReleased(evt);
            }
        });
        panel_stocktabel.add(spin_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 710, 130, 50));

        btntambahstock.setBackground(new java.awt.Color(102, 51, 255));
        btntambahstock.setText("Tambah");
        btntambahstock.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btntambahstock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btntambahstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahstockActionPerformed(evt);
            }
        });
        panel_stocktabel.add(btntambahstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 710, 110, 50));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tabel_stok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_stok.setEnabled(false);
        tabel_stok.setShowGrid(true);
        jScrollPane2.setViewportView(tabel_stok);

        panel_stocktabel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 770, 530));

        ikon_backlihatstok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/left-arrow (1).png"))); // NOI18N
        ikon_backlihatstok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ikon_backlihatstok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ikon_backlihatstokMouseClicked(evt);
            }
        });
        panel_stocktabel.add(ikon_backlihatstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, -1));

        label_backlihatstok.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        label_backlihatstok.setForeground(new java.awt.Color(51, 153, 255));
        label_backlihatstok.setText("Kembali");
        panel_stocktabel.add(label_backlihatstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 25, 60, 20));

        label_txt_pendapatan.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_txt_pendapatan.setForeground(new java.awt.Color(0, 0, 255));
        label_txt_pendapatan.setText("Total Pendapatan :");
        panel_stocktabel.add(label_txt_pendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 800, -1, -1));

        label_pendapatan.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_pendapatan.setForeground(new java.awt.Color(0, 0, 255));
        panel_stocktabel.add(label_pendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 780, 450, 60));

        scrollpane_stock.setViewportView(panel_stocktabel);

        panel_admin.add(scrollpane_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 650));

        panel_container.add(panel_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 1070, 650));

        panel_checkbarang.setPreferredSize(new java.awt.Dimension(1060, 650));
        panel_checkbarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        jLabel5.setText("Cek Barang ");
        panel_checkbarang.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        jSeparator11.setForeground(new java.awt.Color(51, 0, 204));
        panel_checkbarang.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1010, 20));

        jLabel7.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("* Catatan ");
        panel_checkbarang.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 100, 20));

        jLabel9.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("Anda bisa melihat barang anda yang telah dipesan dengan cara memasukan kode simulasi yang berada di tambah barang !");
        panel_checkbarang.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));
        panel_checkbarang.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 1010, 10));

        jLabel11.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        jLabel11.setText("Pada setiap pembelian masing - masing 1 item, bila ingin membeli lagi, anda bisa menekan kolom tambah barang yang di sebelah kiri");
        panel_checkbarang.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, -1, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Admin Tendor Komputer");
        panel_checkbarang.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 600, -1, -1));

        tf_procicb.setEditable(false);
        tf_procicb.setBackground(new java.awt.Color(255, 255, 255));
        tf_procicb.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_procicb.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panel_checkbarang.add(tf_procicb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 780, 40));

        tf_mobocb.setEditable(false);
        tf_mobocb.setBackground(new java.awt.Color(255, 255, 255));
        tf_mobocb.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_mobocb.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panel_checkbarang.add(tf_mobocb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 780, 40));

        tf_ramcb.setEditable(false);
        tf_ramcb.setBackground(new java.awt.Color(255, 255, 255));
        tf_ramcb.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_ramcb.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panel_checkbarang.add(tf_ramcb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 780, 40));

        tf_vgacb.setEditable(false);
        tf_vgacb.setBackground(new java.awt.Color(255, 255, 255));
        tf_vgacb.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_vgacb.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panel_checkbarang.add(tf_vgacb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 780, 40));

        tf_casingcb.setEditable(false);
        tf_casingcb.setBackground(new java.awt.Color(255, 255, 255));
        tf_casingcb.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_casingcb.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panel_checkbarang.add(tf_casingcb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 500, 780, 40));

        tf_storage1cb.setEditable(false);
        tf_storage1cb.setBackground(new java.awt.Color(255, 255, 255));
        tf_storage1cb.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_storage1cb.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panel_checkbarang.add(tf_storage1cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 780, 40));

        tf_storage2cb.setEditable(false);
        tf_storage2cb.setBackground(new java.awt.Color(255, 255, 255));
        tf_storage2cb.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_storage2cb.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panel_checkbarang.add(tf_storage2cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 780, 40));

        tf_psucb.setEditable(false);
        tf_psucb.setBackground(new java.awt.Color(255, 255, 255));
        tf_psucb.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tf_psucb.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panel_checkbarang.add(tf_psucb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 780, 40));

        label_casingcb.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_casingcb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_casingcb.setText("Casing");
        panel_checkbarang.add(label_casingcb, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 140, 40));

        label_prociecb.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_prociecb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_prociecb.setText("Processor");
        panel_checkbarang.add(label_prociecb, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 140, 40));

        label_mobocb.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_mobocb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_mobocb.setText("Motherboard");
        panel_checkbarang.add(label_mobocb, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 140, 40));

        label_ramcb.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_ramcb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_ramcb.setText("RAM");
        panel_checkbarang.add(label_ramcb, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 140, 40));

        label_st1cb.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_st1cb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_st1cb.setText("Storage 1");
        panel_checkbarang.add(label_st1cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 140, 40));

        label_st2cb.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_st2cb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_st2cb.setText("Storage 2");
        panel_checkbarang.add(label_st2cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 140, 40));

        label_psucb.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_psucb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_psucb.setText("Power Supply");
        panel_checkbarang.add(label_psucb, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 140, 40));

        label_vgacb.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        label_vgacb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_vgacb.setText("VGA");
        panel_checkbarang.add(label_vgacb, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 140, 40));

        ikon_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/left-arrow (1).png"))); // NOI18N
        ikon_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ikon_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ikon_backMouseClicked(evt);
            }
        });
        panel_checkbarang.add(ikon_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, -1));

        label_back.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        label_back.setForeground(new java.awt.Color(0, 102, 255));
        label_back.setText("Kembali");
        panel_checkbarang.add(label_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 25, 50, 20));

        panel_container.add(panel_checkbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 1070, 650));

        panel_aboutus.setBackground(new java.awt.Color(255, 255, 255));
        panel_aboutus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_about.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/aboutus.png"))); // NOI18N
        panel_aboutus.add(label_about, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 650));

        panel_container.add(panel_aboutus, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 1070, 650));

        panel_faq.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_FAQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/faq.png"))); // NOI18N
        panel_faq.add(label_FAQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 650));

        panel_container.add(panel_faq, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 1070, 650));

        panel_utama.add(panel_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 1280, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_utama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_utama, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int x = 210;
    private void label_baristigaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_baristigaMouseClicked
        if (x == 210) {
            panel_bg_kategori.setSize(210, 720);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 210; i >= 0; i--) {
                            //Thread.sleep(1);
                            panel_bg_kategori.setSize(i, 720);
                            panel_faq.setBounds(0, 0, 1280, 650);
                            panel_aboutus.setBounds(0, 0, 1280, 650);
                            label_about.setBounds(0, 0, 1280, 650);
                            label_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/aboutus2.png"))); // NOI18N
                            label_FAQ.setBounds(0, 0, 1280, 650);
                            label_FAQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/faq2.png"))); // NOI18N
                            scrollpane_dashboard.setBounds(0, 0, 1280, 650);
                            panel_dashboard.setBounds(0, 0, 1090, 1280);
                            scroll_tambahbarang.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                            scrollpane_lihatuser.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                            scroll_tambahbarang.setBounds(0, 0, 1280, 650);
                            scrollpane_lihatuser.setBounds(0, 0, 1280, 650);
                            scrollpane_stock.setBounds(0, 0, 1280, 650);
                            panel_lihatuser.setBounds(0, 0, 1280, 900);
                            panel_stocktabel.setBounds(0, 0, 1400, 540);
                            panel_tambah_barang.setBounds(0, 0, 1140, 1060);
                            panel_member.setBounds(0, 0, 1000, 650);
                            panel_checkbarang.setBounds(0, 0, 1280, 650);
                            panel_profile.setBounds(0, 0, 1280, 650);
                            panel_admin.setBounds(0, 0, 1280, 650);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            x = 0;
        }
        label_baristiga.setVisible(false);
        label_baristigalagi.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_baristigaMouseClicked

    private void label_baristigalagiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_baristigalagiMouseClicked
        if (x == 0) {
            panel_bg_kategori.show();
            panel_bg_kategori.setSize(x, 720);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i <= x; i++) {
                            //Thread.sleep(0,5);
                            panel_bg_kategori.setSize(i, 720);
                        }
                        panel_faq.setBounds(210, 0, 1070, 650);
                        panel_aboutus.setBounds(210, 0, 1070, 650);
                        label_about.setBounds(210, 0, 1070, 650);
                        label_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/aboutus.png"))); // NOI18N
                        label_FAQ.setBounds(210, 0, 1070, 650);
                        label_FAQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/faq.png"))); // NOI18N
                        panel_checkbarang.setBounds(210, 0, 1070, 650);
                        panel_profile.setBounds(210, 0, 1070, 650);

                        scroll_tambahbarang.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scrollpane_lihatuser.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            x = 210;
        }
        label_baristigalagi.setVisible(false);
        label_baristiga.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_baristigalagiMouseClicked

    private void label_baristigaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_baristigaMouseEntered
        //labelcolor(jLabel2);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_baristigaMouseEntered

    private void label_baristigaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_baristigaMouseExited
        // resetlabelcolor(jLabel2);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_baristigaMouseExited
    int y = 210;
    private void label_profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_profileMouseClicked
        //jPanel10
        panel_profile.setVisible(true); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scrollpane_dashboard.setVisible(false);
        scroll_tambahbarang.setVisible(false);
        label_baristiga.setVisible(true);
        label_baristigalagi.setVisible(true);
        if (status.equalsIgnoreCase("bronze")) {
            icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERBRONZE.jpeg")));
            jLabel1.setVisible(false);
        } else if (status.equalsIgnoreCase("silver")) {
            icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERSILPER.jpeg")));
            jLabel1.setVisible(false);
        } else if (status.equalsIgnoreCase("gold")) {
            icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERGOLD.jpeg")));
            jLabel1.setVisible(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_label_profileMouseClicked

    private void label_profileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_profileMouseEntered
        labelcolor(label_profile);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_profileMouseEntered

    private void label_profileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_profileMouseExited
        resetlabelcolor(label_profile);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_profileMouseExited
    int s = 210;
    private void label_tbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_tbarangMouseClicked
        //jPanel11
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(true); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(true);
        label_baristiga.setVisible(true);
        label_baristigalagi.setVisible(true);
        clearCekBarang();
    }//GEN-LAST:event_label_tbarangMouseClicked

    private void label_dbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_dbMouseClicked
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        scrollpane_dashboard.setVisible(true); // Ada Dashboard
        panel_dashboard.setVisible(true);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        if (totalPoint() < 0) {
            total_point.setText("0");
        }
        label_baristiga.setVisible(true);
        label_baristigalagi.setVisible(true);
        clearCekBarang();
    }//GEN-LAST:event_label_dbMouseClicked

    private void label_faqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_faqMouseClicked
        //jLabel13
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        scrollpane_dashboard.setVisible(false); // Ada Dashboard
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(true); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        scrollpane_lihatuser.setVisible(false);
        label_baristiga.setVisible(true);
        label_baristigalagi.setVisible(true);
        clearCekBarang();
        // TODO add your handling code here:
    }//GEN-LAST:event_label_faqMouseClicked

    private void label_membMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_membMouseClicked
        //jLabel14
        panel_gold.setVisible(false);
        panel_silper.setVisible(false);
        panel_bronze.setVisible(false);
        label_bronze_close.setVisible(false);
        label_bronze_open.setVisible(true);
        label_silper_close.setVisible(false);
        label_silper_open.setVisible(true);
        label_gold_close.setVisible(false);
        label_gold_open.setVisible(true);
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        panel_member.setVisible(true); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        scrollpane_dashboard.setVisible(false);
        label_baristiga.setVisible(false);
        label_baristigalagi.setVisible(false);
        clearCekBarang();
        // TODO add your handling code here:
    }//GEN-LAST:event_label_membMouseClicked

    private void label_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_adminMouseClicked
        //jLabel15
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(true); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        scrollpane_dashboard.setVisible(false);
        scrollpane_stock.setVisible(false);
        scrollpane_lihatuser.setVisible(false);
        panel_lihatuserdanstock.setVisible(true);
        label_baristiga.setVisible(true);
        label_baristigalagi.setVisible(true);
        clearCekBarang();
        // TODO add your handling code here:
    }//GEN-LAST:event_label_adminMouseClicked

    private void label_cbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_cbarangMouseClicked
        //jLabel16
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(true); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        label_baristiga.setVisible(true);
        label_baristigalagi.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_cbarangMouseClicked
    public void clearCekBarang() {
        tf_procicb.setText(Format.formatKosong());
        tf_mobocb.setText(Format.formatKosong());
        tf_ramcb.setText(Format.formatKosong());
        tf_storage1cb.setText(Format.formatKosong());
        tf_storage2cb.setText(Format.formatKosong());
        tf_psucb.setText(Format.formatKosong());
        tf_vgacb.setText(Format.formatKosong());
        tf_casingcb.setText(Format.formatKosong());
    }

    private void label_aboutusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_aboutusMouseClicked
        //jLabel17
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(true); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        scrollpane_dashboard.setVisible(false);
        scrollpane_lihatuser.setVisible(false);
        label_baristiga.setVisible(true);
        label_baristigalagi.setVisible(true);
        clearCekBarang();
        // TODO add your handling code here:
    }//GEN-LAST:event_label_aboutusMouseClicked

    private void label_dbMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_dbMouseEntered
        labelcolor(label_db);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_dbMouseEntered

    private void label_dbMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_dbMouseExited
        resetlabelcolor(label_db);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_dbMouseExited

    private void label_faqMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_faqMouseEntered
        labelcolor(label_faq);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_faqMouseEntered

    private void label_faqMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_faqMouseExited
        resetlabelcolor(label_faq);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_faqMouseExited

    private void label_membMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_membMouseEntered
        labelcolor(label_memb);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_membMouseEntered

    private void label_membMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_membMouseExited
        resetlabelcolor(label_memb);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_membMouseExited

    private void label_tbarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_tbarangMouseEntered
        labelcolor(label_tbarang);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_tbarangMouseEntered

    private void label_tbarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_tbarangMouseExited
        resetlabelcolor(label_tbarang);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_tbarangMouseExited

    private void label_adminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_adminMouseEntered
        labelcolor(label_admin);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_adminMouseEntered

    private void label_adminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_adminMouseExited
        resetlabelcolor(label_admin);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_adminMouseExited

    private void label_cbarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_cbarangMouseEntered
        labelcolor(label_cbarang);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_cbarangMouseEntered

    private void label_cbarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_cbarangMouseExited
        resetlabelcolor(label_cbarang);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_cbarangMouseExited

    private void label_aboutusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_aboutusMouseEntered
        labelcolor(label_aboutus);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_aboutusMouseEntered

    private void label_aboutusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_aboutusMouseExited
        resetlabelcolor(label_aboutus);
        // TODO add your handling code here:
    }//GEN-LAST:event_label_aboutusMouseExited

    private void cb_storage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_storage1ActionPerformed
        if (pr instanceof Produk) {
            if (cb_storage1.getSelectedIndex() == 1) {
                tf_harga_sto1.setText("Rp. 610.000");
                hargaStorage1 = 610_000;
                arr[3] = hargaStorage1;
                tf_GTotal.setText(menghitungTotal());
                label_stokst1.setText(queryStock(pr.getStorage1()[1]) + "");
            } else if (cb_storage1.getSelectedIndex() == 2) {
                tf_harga_sto1.setText("Rp. 1.200.000");
                hargaStorage1 = 1_200_000;
                arr[3] = hargaStorage1;
                tf_GTotal.setText(menghitungTotal());
                label_stokst1.setText(queryStock(pr.getStorage1()[2]) + "");
            } else if (cb_storage1.getSelectedIndex() == 3) {
                tf_harga_sto1.setText("Rp. 860.000");
                hargaStorage1 = 860_000;
                arr[3] = hargaStorage1;
                tf_GTotal.setText(menghitungTotal());
                label_stokst1.setText(queryStock(pr.getStorage1()[3]) + "");
            } else if (cb_storage1.getSelectedIndex() == 4) {
                tf_harga_sto1.setText("Rp. 770.000");
                hargaStorage1 = 770_000;
                arr[3] = hargaStorage1;
                tf_GTotal.setText(menghitungTotal());
                label_stokst1.setText(queryStock(pr.getStorage1()[4]) + "");
            } else if (cb_storage1.getSelectedIndex() == 5) {
                tf_harga_sto1.setText("Rp. 300.000");
                hargaStorage1 = 300_000;
                arr[3] = hargaStorage1;
                tf_GTotal.setText(menghitungTotal());
                label_stokst1.setText(queryStock(pr.getStorage1()[5]) + "");
            } else if (cb_storage1.getSelectedIndex() == 6) {
                tf_harga_sto1.setText("Rp. 1.100.000");
                hargaStorage1 = 1_100_000;
                arr[3] = hargaStorage1;
                tf_GTotal.setText(menghitungTotal());
                label_stokst1.setText(queryStock(pr.getStorage1()[6]) + "");
            } else {
                tf_harga_sto1.setText(Format.formatKosong());
                hargaStorage1 = 0;
                arr[3] = hargaStorage1;
                tf_GTotal.setText(menghitungTotal());
                label_stokst1.setText(Format.formatKosong());
            }
        }
    }//GEN-LAST:event_cb_storage1ActionPerformed

    private void cb_storage2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_storage2ActionPerformed
        if (cb_storage2.getSelectedIndex() == 1) {
            tf_harga_sto2.setText("Rp. 2.500.000");
            hargaStorage2 = 2_500_000;
            arr[4] = hargaStorage2;
            tf_GTotal.setText(menghitungTotal());
            label_stokst2.setText(queryStock(pkm.storage2[1]) + "");
        } else if (cb_storage2.getSelectedIndex() == 2) {
            tf_harga_sto2.setText("Rp. 1.400.000");
            hargaStorage2 = 1_400_000;
            arr[4] = hargaStorage2;
            tf_GTotal.setText(menghitungTotal());
            label_stokst2.setText(queryStock(pkm.storage2[2]) + "");
        } else if (cb_storage2.getSelectedIndex() == 3) {
            tf_harga_sto2.setText("Rp. 7.300.000");
            hargaStorage2 = 7_300_000;
            arr[4] = hargaStorage2;
            tf_GTotal.setText(menghitungTotal());
            label_stokst2.setText(queryStock(pkm.storage2[3]) + "");
        } else if (cb_storage2.getSelectedIndex() == 4) {
            tf_harga_sto2.setText("Rp. 4.200.000");
            hargaStorage2 = 4_200_000;
            arr[4] = hargaStorage2;
            tf_GTotal.setText(menghitungTotal());
            label_stokst2.setText(queryStock(pkm.storage2[4]) + "");
        } else if (cb_storage2.getSelectedIndex() == 5) {
            tf_harga_sto2.setText("Rp. 6.300.000");
            hargaStorage2 = 6_300_000;
            arr[4] = hargaStorage2;
            tf_GTotal.setText(menghitungTotal());
            label_stokst2.setText(queryStock(pkm.storage2[5]) + "");
        } else {
            tf_harga_sto2.setText(Format.formatKosong());
            hargaStorage2 = 0;
            arr[4] = hargaStorage2;
            tf_GTotal.setText(menghitungTotal());
            label_stokst2.setText(Format.formatKosong());
        }
    }//GEN-LAST:event_cb_storage2ActionPerformed

    private void cb_psuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_psuActionPerformed
        if (pr instanceof Produk) {
            if (cb_psu.getSelectedIndex() == 1) {
                tf_harga_psu.setText("Rp. 800.000");
                hargaPSU = 800_000;
                arr[5] = hargaPSU;
                tf_GTotal.setText(menghitungTotal());
                label_stockpsu.setText(queryStock(pr.getPsu()[1]) + "");
            } else if (cb_psu.getSelectedIndex() == 2) {
                tf_harga_psu.setText("Rp. 550.000");
                hargaPSU = 550_000;
                arr[5] = hargaPSU;
                tf_GTotal.setText(menghitungTotal());
                label_stockpsu.setText(queryStock(pr.getPsu()[2]) + "");
            } else if (cb_psu.getSelectedIndex() == 3) {
                tf_harga_psu.setText("Rp. 900.000");
                hargaPSU = 900_000;
                arr[5] = hargaPSU;
                tf_GTotal.setText(menghitungTotal());
                label_stockpsu.setText(queryStock(pr.getPsu()[3]) + "");
            } else if (cb_psu.getSelectedIndex() == 4) {
                tf_harga_psu.setText("Rp. 4.300.000");
                hargaPSU = 4_300_000;
                arr[5] = hargaPSU;
                tf_GTotal.setText(menghitungTotal());
                label_stockpsu.setText(queryStock(pr.getPsu()[4]) + "");
            } else if (cb_psu.getSelectedIndex() == 5) {
                tf_harga_psu.setText("Rp. 1.250.000");
                hargaPSU = 1_250_000;
                arr[5] = hargaPSU;
                tf_GTotal.setText(menghitungTotal());
                label_stockpsu.setText(queryStock(pr.getPsu()[4]) + "");
            } else {
                tf_harga_psu.setText(Format.formatKosong());
                hargaPSU = 0;
                arr[5] = hargaPSU;
                tf_GTotal.setText(menghitungTotal());
                label_stockpsu.setText(Format.formatKosong());
            }
        }
    }//GEN-LAST:event_cb_psuActionPerformed

    private void cb_pilihvgaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihvgaActionPerformed
        if (cb_pilihvga.getSelectedIndex() == 1) {
            tf_harga_vga.setText("Rp. 13.200.000");
            hargaVGA = 13_200_000;
            arr[6] = hargaVGA;
            tf_GTotal.setText(menghitungTotal());
            label_stockvga.setText(queryStock(pkm.vga[1]) + "");
        } else if (cb_pilihvga.getSelectedIndex() == 2) {
            tf_harga_vga.setText("Rp. 34.000.000");
            hargaVGA = 34_000_000;
            arr[6] = hargaVGA;
            tf_GTotal.setText(menghitungTotal());
            label_stockvga.setText(queryStock(pkm.vga[2]) + "");
        } else if (cb_pilihvga.getSelectedIndex() == 3) {
            tf_harga_vga.setText("Rp. 6.700.000");
            hargaVGA = 6_700_000;
            arr[6] = hargaVGA;
            tf_GTotal.setText(menghitungTotal());
            label_stockvga.setText(queryStock(pkm.vga[3]) + "");
        } else if (cb_pilihvga.getSelectedIndex() == 4) {
            tf_harga_vga.setText("Rp. 1.500.000");
            hargaVGA = 1_500_000;
            arr[6] = hargaVGA;
            tf_GTotal.setText(menghitungTotal());
            label_stockvga.setText(queryStock(pkm.vga[4]) + "");
        } else if (cb_pilihvga.getSelectedIndex() == 5) {
            tf_harga_vga.setText("Rp. 3.700.000");
            hargaVGA = 3_700_000;
            arr[6] = hargaVGA;
            tf_GTotal.setText(menghitungTotal());
            label_stockvga.setText(queryStock(pkm.vga[5]) + "");
        } else if (cb_pilihvga.getSelectedIndex() == 6) {
            tf_harga_vga.setText("Rp. 35.000.000");
            hargaVGA = 35_000_000;
            arr[6] = hargaVGA;
            tf_GTotal.setText(menghitungTotal());
            label_stockvga.setText(queryStock(pkm.vga[6]) + "");
        } else {
            tf_harga_vga.setText(Format.formatKosong());
            hargaVGA = 0;
            arr[6] = hargaVGA;
            tf_GTotal.setText(menghitungTotal());
            label_stockvga.setText(Format.formatKosong());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_pilihvgaActionPerformed
    // PILIHAN CASING
    double nilai;
    private void cb_pilihcasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihcasingActionPerformed
        if (pr instanceof Produk) {
            if (cb_pilihcasing.getSelectedIndex() == 1) {
                tf_harga_casing.setText("Rp. 1.900.000");
                hargaCasing = 1_900_000;
                arr[7] = hargaCasing;
                tf_GTotal.setText(menghitungTotal());
                label_stokcasing.setText(queryStock(pr.getCasing()[1]) + "");
            } else if (cb_pilihcasing.getSelectedIndex() == 2) {
                tf_harga_casing.setText("Rp. 460.000");
                hargaCasing = 460_000;
                arr[7] = hargaCasing;
                tf_GTotal.setText(menghitungTotal());
                label_stokcasing.setText(queryStock(pr.getCasing()[2]) + "");
            } else if (cb_pilihcasing.getSelectedIndex() == 3) {
                tf_harga_casing.setText("Rp. 1.300.000");
                hargaCasing = 1_300_000;
                arr[7] = hargaCasing;
                tf_GTotal.setText(menghitungTotal());
                label_stokcasing.setText(queryStock(pr.getCasing()[3]) + "");
            } else if (cb_pilihcasing.getSelectedIndex() == 4) {
                tf_harga_casing.setText("Rp. 650.000");
                hargaCasing = 650_000;
                arr[7] = hargaCasing;
                tf_GTotal.setText(menghitungTotal());
                label_stokcasing.setText(queryStock(pr.getCasing()[4]) + "");
            } else if (cb_pilihcasing.getSelectedIndex() == 5) {
                tf_harga_casing.setText("Rp. 1.400.000");
                hargaCasing = 1_400_000;
                arr[7] = hargaCasing;
                tf_GTotal.setText(menghitungTotal());
                label_stokcasing.setText(queryStock(pr.getCasing()[5]) + "");
            } else {
                tf_harga_casing.setText(Format.formatKosong());
                hargaCasing = 0;
                arr[7] = hargaCasing;
                tf_GTotal.setText(menghitungTotal());
                label_stokcasing.setText(Format.formatKosong());
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_cb_pilihcasingActionPerformed

    private void tf_masukanpoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_masukanpoinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_masukanpoinActionPerformed

    private void label_bronze_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_bronze_closeMouseClicked
        // TODO add your handling code here:
        //nutup
        height_silper = 0;
        height_gold = 0;
        if (height == 200) {
            panel_bronze.setSize(210, 200);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 200; i >= 0; i--) {
                            Thread.sleep(1);
                            panel_bronze.setSize(210, i);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            height = 0;
        }
        label_bronze_close.setVisible(false);
        label_bronze_open.setVisible(true);
        flagBronze = false;
        flagSilver = false;
        flagGold = false;
    }//GEN-LAST:event_label_bronze_closeMouseClicked

    private void label_bronze_openMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_bronze_openMouseClicked
        // TODO add your handling code here:
        height_silper = 0;
        height_gold = 0;
        if (height == 0) {
            panel_bronze.show();
            panel_bronze.setSize(210, 200);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i <= height; i++) {
                            Thread.sleep(1);
                            panel_bronze.setSize(210, i);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            height = 200;
        }
        label_bronze_open.setVisible(false);
        label_bronze_close.setVisible(true);
        panel_silper.setVisible(false);
        label_silper_close.setVisible(false);
        label_silper_open.setVisible(true);
        panel_gold.setVisible(false);
        label_gold_open.setVisible(true);
        label_gold_close.setVisible(false);
        flagBronze = true;
        flagSilver = false;
        flagGold = false;
    }//GEN-LAST:event_label_bronze_openMouseClicked

    private void label_silper_openMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_silper_openMouseClicked
        // TODO add your handling code here:
        height = 0;
        height_gold = 0;
        if (height_silper == 0) {
            panel_silper.show();
            panel_silper.setSize(210, 200);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i <= height_silper; i++) {
                            Thread.sleep(1);
                            panel_silper.setSize(210, i);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            height_silper = 200;
        }
        label_silper_open.setVisible(false);
        label_silper_close.setVisible(true);
        panel_bronze.setVisible(false);
        label_bronze_close.setVisible(false);
        label_bronze_open.setVisible(true);
        panel_gold.setVisible(false);
        label_gold_open.setVisible(true);
        label_gold_close.setVisible(false);
        flagBronze = false;
        flagSilver = true;
        flagGold = false;
    }//GEN-LAST:event_label_silper_openMouseClicked

    private void label_silper_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_silper_closeMouseClicked
        // TODO add your handling code here:
        height = 0;
        height_gold = 0;
        if (height_silper == 200) {
            panel_silper.setSize(210, 200);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 200; i >= 0; i--) {
                            Thread.sleep(1);
                            panel_silper.setSize(210, i);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            height_silper = 0;
        }
        label_silper_close.setVisible(false);
        label_silper_open.setVisible(true);
        flagBronze = false;
        flagSilver = false;
        flagGold = false;
    }//GEN-LAST:event_label_silper_closeMouseClicked

    private void label_gold_openMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_gold_openMouseClicked
        // TODO add your handling code here:
        height = 0;
        height_silper = 0;
        if (height_gold == 0) {
            panel_gold.show();
            panel_gold.setSize(210, 200);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i <= height_gold; i++) {
                            Thread.sleep(1);
                            panel_gold.setSize(210, i);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            height_gold = 200;
        }
        label_silper_open.setVisible(true);
        label_silper_close.setVisible(false);
        panel_bronze.setVisible(false);
        label_bronze_close.setVisible(false);
        label_bronze_open.setVisible(true);
        panel_silper.setVisible(false);
        label_gold_open.setVisible(false);
        label_gold_close.setVisible(true);
        flagBronze = false;
        flagSilver = false;
        flagGold = true;
    }//GEN-LAST:event_label_gold_openMouseClicked

    private void label_gold_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_gold_closeMouseClicked
        // TODO add your handling code here:
        height = 0;
        height_silper = 0;
        if (height_gold == 200) {
            panel_gold.setSize(210, 200);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 200; i >= 0; i--) {
                            Thread.sleep(1);
                            panel_gold.setSize(210, i);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };
            th.start();
            height_gold = 0;
        }
        label_gold_close.setVisible(false);
        label_gold_open.setVisible(true);
        flagBronze = false;
        flagSilver = false;
        flagGold = false;
    }//GEN-LAST:event_label_gold_closeMouseClicked

    private void button_bayar_membActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_bayar_membActionPerformed
        // TODO add your handling code here:
        int bronze = 50_000;
        int silver = 60_000;
        int gold = 80_000;
        int points = 0;
        try {
            int duit = Integer.parseInt(tf_masukanuang_memb.getText());
            if (flagBronze) {
                if (bronze <= duit) {
                    //icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERBRONZE.jpeg")));
                    bronzeBenar = true;
                    jLabel1.setVisible(false);
                    points = 10;
                    updateMember("Bronze", points, duit - bronze);
                } else {
                    JOptionPane.showMessageDialog(null, "Duit kurang!");
                    tf_masukanuang_memb.setText(null);
                }
            } else if (flagSilver) {
                if (silver <= duit) {
                    //icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERSILPER.jpeg")));
                    silperBenar = true;
                    jLabel1.setVisible(false);
                    points = 15;
                    updateMember("Silver", points, duit - silver);
                } else {
                    JOptionPane.showMessageDialog(null, "Duit kurang!");
                    tf_masukanuang_memb.setText(null);
                }
            } else if (flagGold) {
                if (gold <= duit) {
                    //icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERGOLD.jpeg")));
                    goldBenar = true;
                    jLabel1.setVisible(false);
                    points = 20;
                    updateMember("Gold", points, duit - gold);
                } else {
                    JOptionPane.showMessageDialog(null, "Duit kurang!");
                    tf_masukanuang_memb.setText(null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Anda sudah terdaftar dalam member!");
                tf_masukanuang_memb.setText(null);
                status = "";
            }
            tf_masukanuang_memb.setText(Format.formatKosong());
        } catch (Exception e) {
            if (tf_masukanuang_memb.getText().equals("") && flagBronze || flagSilver || flagGold) {
                JOptionPane.showMessageDialog(null, "Input Tidak boleh kosong!!", "Warning", JOptionPane.ERROR_MESSAGE);
                tf_masukanuang_memb.setText(null);
            } else if (!tf_masukanuang_memb.getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Input harus Angka!!", "Warning", JOptionPane.ERROR_MESSAGE);
                tf_masukanuang_memb.setText(null);
            }
        }
    }//GEN-LAST:event_button_bayar_membActionPerformed
    private int id = 0;

    public void updateMember(String status, int points, int duit) {
        db.koneksiDatabase();
        try {
            Statement statement = db.getKoneksi().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM membership m INNER JOIN user u ON m.id = u.id WHERE u.username = '" + user.getUsername() + "'");
            rs.next();
            id = rs.getInt("ID");
            String st = rs.getString("status");

            if (st.equals("")) {
                String query = "update membership set status = ?, points = ? where id = ? ";
                PreparedStatement state = db.getKoneksi().prepareStatement(query);
                state.setString(1, status);
                state.setInt(2, totalPoint() + points);
                state.setInt(3, id);
                state.execute();
                total_point.setText(totalPoint() + " Points");
                JOptionPane.showMessageDialog(null, "Transaksi Berhasil! \nSisa Uang Kembalian Anda " + formatRp(duit) + "\nSelamat Anda Terdaftar Sebagai " + status + " Member"
                        + "\nAnda mendapatkan " + points + " Points");
                if (bronzeBenar) {
                    icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERBRONZE.jpeg")));
                    jLabel1.setVisible(false);
                } else if (silperBenar) {
                    icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERSILPER.jpeg")));
                    jLabel1.setVisible(false);
                } else if (goldBenar) {
                    icon_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MEMBERGOLD.jpeg")));
                    jLabel1.setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Anda sudah terdaftar sebagai Member!");
            }
        } catch (Exception e) {

        }
    }

    public int queryStock(String namaBarang) {
        db.koneksiDatabase();
        int stock = 0;
        try {
            String query = "SELECT * FROM stock WHERE TipeProduct = '" + namaBarang + "'";
            Statement st = db.getKoneksi().createStatement();
            ResultSet rsLogin = st.executeQuery(query);
            rsLogin.next();
            if (rsLogin.getRow() == 1) {
                stock = rsLogin.getInt("stock");
            }
        } catch (Exception e) {

        }
        return stock;
    }
    private void cb_pilihprosesorkosongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihprosesorkosongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_pilihprosesorkosongActionPerformed

    private void cb_brandprosesorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_brandprosesorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_brandprosesorKeyReleased

    private void cb_brandprosesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_brandprosesorActionPerformed
        if (cb_brandprosesor.getSelectedItem().equals("Intel")) {
            cb_pilihprosesorkosong.setVisible(false);
            cb_pilihprosesorintel.setVisible(true);
            cb_pilihprosesoramd.setVisible(false);

            cb_pilihmoboamd.setVisible(false);
            cb_pilihmobointel.setVisible(true);
            cb_pilihmobokosong.setVisible(false);

            cb_pilihprosesoramd.setSelectedIndex(0);
            cb_pilihprosesorintel.setSelectedIndex(0);
            cb_pilihmoboamd.setSelectedIndex(0);
            cb_pilihmobointel.setSelectedIndex(0);
        } else if (cb_brandprosesor.getSelectedItem().equals("AMD")) {
            cb_pilihprosesorkosong.setVisible(false);
            cb_pilihprosesorintel.setVisible(false);
            cb_pilihprosesoramd.setVisible(true);

            cb_pilihmoboamd.setVisible(true);
            cb_pilihmobointel.setVisible(false);
            cb_pilihmobokosong.setVisible(false);

            cb_pilihprosesoramd.setSelectedIndex(0);
            cb_pilihprosesorintel.setSelectedIndex(0);
            cb_pilihmoboamd.setSelectedIndex(0);
            cb_pilihmobointel.setSelectedIndex(0);
        } else {
            cb_pilihprosesorkosong.setVisible(true);
            cb_pilihprosesorintel.setVisible(false);
            cb_pilihprosesoramd.setVisible(false);

            cb_pilihmoboamd.setVisible(false);
            cb_pilihmobointel.setVisible(false);
            cb_pilihmobokosong.setVisible(true);

            cb_pilihprosesoramd.setSelectedIndex(0);
            cb_pilihprosesorintel.setSelectedIndex(0);
            cb_pilihmoboamd.setSelectedIndex(0);
            cb_pilihmobointel.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cb_brandprosesorActionPerformed

    private void label_updatepassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_updatepassMouseClicked
        LoginAndRegisterForm larf = new LoginAndRegisterForm();
        larf.setVisible(true);
        larf.setUsername(user.getUsername());
        larf.getPanelCP().setVisible(true);
        larf.getPanelForgot().setVisible(false);
        larf.getPanelMain().setVisible(false);
        this.dispose();
    }//GEN-LAST:event_label_updatepassMouseClicked

    private void cb_pilihprosesoramdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihprosesoramdActionPerformed
        if (cb_pilihprosesoramd.getSelectedIndex() == 1) {
            tf_harga_prosesor.setText("Rp. 8.800.000");
            hargaProci = 8_800_000;
            arr[0] = hargaProci;
            tf_GTotal.setText(menghitungTotal());
            label_stokproci.setText(queryStock(pkm.procie[1]) + "");
        } else if (cb_pilihprosesoramd.getSelectedIndex() == 2) {
            tf_harga_prosesor.setText("Rp. 2.700.000");
            hargaProci = 2_700_000;
            arr[0] = hargaProci;
            tf_GTotal.setText(menghitungTotal());
            label_stokproci.setText(queryStock(pkm.procie[2]) + "");
        } else if (cb_pilihprosesoramd.getSelectedIndex() == 3) {
            tf_harga_prosesor.setText("Rp. 6.300.000");
            hargaProci = 6_300_000;
            arr[0] = hargaProci;
            tf_GTotal.setText(menghitungTotal());
            label_stokproci.setText(queryStock(pkm.procie[3]) + "");
        } else if (cb_pilihprosesoramd.getSelectedIndex() == 4) {
            tf_harga_prosesor.setText("Rp. 1.400.000");
            hargaProci = 1_400_000;
            arr[0] = hargaProci;
            tf_GTotal.setText(menghitungTotal());
            label_stokproci.setText(queryStock(pkm.procie[4]) + "");
        } else if (cb_pilihprosesoramd.getSelectedIndex() == 5) {
            tf_harga_prosesor.setText("Rp. 4.500.000");
            hargaProci = 4_500_000;
            arr[0] = hargaProci;
            tf_GTotal.setText(menghitungTotal());
            label_stokproci.setText(queryStock(pkm.procie[5]) + "");
        } else {
            tf_harga_prosesor.setText(Format.formatKosong());
            hargaProci = 0;
            arr[0] = hargaProci;
            tf_GTotal.setText(menghitungTotal());
            label_stokproci.setText(Format.formatKosong());
        }
    }//GEN-LAST:event_cb_pilihprosesoramdActionPerformed

    private void cb_pilihprosesorintelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihprosesorintelActionPerformed
        if (pr instanceof Produk) {
            if (cb_pilihprosesorintel.getSelectedIndex() == 1) {
                tf_harga_prosesor.setText("Rp. 4.700.000");
                hargaProci = 4_700_000;
                arr[0] = hargaProci;
                tf_GTotal.setText(menghitungTotal());
                label_stokproci.setText(queryStock(pr.getProcie()[6]) + "");
            } else if (cb_pilihprosesorintel.getSelectedIndex() == 2) {
                tf_harga_prosesor.setText("Rp. 6.500.000");
                hargaProci = 6_500_000;
                arr[0] = hargaProci;
                tf_GTotal.setText(menghitungTotal());
                label_stokproci.setText(queryStock(pr.getProcie()[7]) + "");
            } else if (cb_pilihprosesorintel.getSelectedIndex() == 3) {
                tf_harga_prosesor.setText("Rp. 3.450.000");
                hargaProci = 3_450_000;
                arr[0] = hargaProci;
                tf_GTotal.setText(menghitungTotal());
                label_stokproci.setText(queryStock(pr.getProcie()[8]) + "");
            } else if (cb_pilihprosesorintel.getSelectedIndex() == 4) {
                tf_harga_prosesor.setText("Rp. 6.850.000");
                hargaProci = 6_850_000;
                arr[0] = hargaProci;
                tf_GTotal.setText(menghitungTotal());
                label_stokproci.setText(queryStock(pr.getProcie()[9]) + "");
            } else if (cb_pilihprosesorintel.getSelectedIndex() == 5) {
                tf_harga_prosesor.setText("Rp. 8.700.000");
                hargaProci = 8_700_000;
                arr[0] = hargaProci;
                tf_GTotal.setText(menghitungTotal());
                label_stokproci.setText(queryStock(pr.getProcie()[10]) + "");
            } else {
                tf_harga_prosesor.setText(Format.formatKosong());
                hargaProci = 0;
                arr[0] = hargaProci;
                tf_GTotal.setText(menghitungTotal());
                label_stokproci.setText(Format.formatKosong());
            }
        }
    }//GEN-LAST:event_cb_pilihprosesorintelActionPerformed

    private void cb_pilihmobointelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihmobointelActionPerformed
        if (cb_pilihmobointel.getSelectedIndex() == 1) {
            tf_harga_mobo.setText("Rp. 2.800.000");
            hargaMobo = 2_800_000;
            arr[1] = hargaMobo;
            tf_GTotal.setText(menghitungTotal());
            label_stokmobo.setText(queryStock(pkm.mobo[1]) + "");
        } else if (cb_pilihmobointel.getSelectedIndex() == 2) {
            tf_harga_mobo.setText("Rp. 5.000.000");
            hargaMobo = 5_000_000;
            arr[1] = hargaMobo;
            tf_GTotal.setText(menghitungTotal());
            label_stokmobo.setText(queryStock(pkm.mobo[2]) + "");
        } else if (cb_pilihmobointel.getSelectedIndex() == 3) {
            tf_harga_mobo.setText("Rp. 6.500.000");
            hargaMobo = 6_500_000;
            arr[1] = hargaMobo;
            tf_GTotal.setText(menghitungTotal());
            label_stokmobo.setText(queryStock(pkm.mobo[3]) + "");
        } else if (cb_pilihmobointel.getSelectedIndex() == 4) {
            tf_harga_mobo.setText("Rp. 4.000.000");
            hargaMobo = 4_000_000;
            arr[1] = hargaMobo;
            tf_GTotal.setText(menghitungTotal());
            label_stokmobo.setText(queryStock(pkm.mobo[4]) + "");
        } else if (cb_pilihmobointel.getSelectedIndex() == 5) {
            tf_harga_mobo.setText("Rp. 5.500.000");
            hargaMobo = 5_500_000;
            arr[1] = hargaMobo;
            tf_GTotal.setText(menghitungTotal());
            label_stokmobo.setText(queryStock(pkm.mobo[5]) + "");
        } else {
            tf_harga_mobo.setText(Format.formatKosong());
            hargaMobo = 0;
            arr[1] = hargaMobo;
            tf_GTotal.setText(menghitungTotal());
            label_stokmobo.setText(Format.formatKosong());
        }
    }//GEN-LAST:event_cb_pilihmobointelActionPerformed

    private void cb_pilihmoboamdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihmoboamdActionPerformed
        if (pr instanceof Produk) {
            if (cb_pilihmoboamd.getSelectedIndex() == 1) {
                tf_harga_mobo.setText("Rp. 718.000");
                hargaMobo = 718_000;
                arr[1] = hargaMobo;
                tf_GTotal.setText(menghitungTotal());
                label_stokmobo.setText(queryStock(pr.getMobo()[6]) + "");
            } else if (cb_pilihmoboamd.getSelectedIndex() == 2) {
                tf_harga_mobo.setText("Rp. 748.000");
                hargaMobo = 748_000;
                arr[1] = hargaMobo;
                tf_GTotal.setText(menghitungTotal());
                label_stokmobo.setText(queryStock(pr.getMobo()[7]) + "");
            } else if (cb_pilihmoboamd.getSelectedIndex() == 3) {
                tf_harga_mobo.setText("Rp. 1.665.000");
                hargaMobo = 1_665_000;
                arr[1] = hargaMobo;
                tf_GTotal.setText(menghitungTotal());
                label_stokmobo.setText(queryStock(pr.getMobo()[8]) + "");
            } else if (cb_pilihmoboamd.getSelectedIndex() == 4) {
                tf_harga_mobo.setText("Rp. 1.250.000");
                hargaMobo = 1_250_000;
                arr[1] = hargaMobo;
                tf_GTotal.setText(menghitungTotal());
                label_stokmobo.setText(queryStock(pr.getMobo()[9]) + "");
            } else if (cb_pilihmoboamd.getSelectedIndex() == 5) {
                tf_harga_mobo.setText("Rp. 1.780.000");
                hargaMobo = 1_780_000;
                arr[1] = hargaMobo;
                tf_GTotal.setText(menghitungTotal());
                label_stokmobo.setText(queryStock(pr.getMobo()[10]) + "");
            } else {
                tf_harga_mobo.setText(Format.formatKosong());
                hargaMobo = 0;
                arr[1] = hargaMobo;
                tf_GTotal.setText(menghitungTotal());
                label_stokmobo.setText(Format.formatKosong());
            }
        }
    }//GEN-LAST:event_cb_pilihmoboamdActionPerformed

    private void cb_pilihramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihramActionPerformed
        if (cb_pilihram.getSelectedIndex() == 1) {
            tf_harga_ram.setText("Rp. 380.000");
            hargaRam = 380_000;
            arr[2] = hargaRam;
            tf_GTotal.setText(menghitungTotal());
            label_stokram.setText(queryStock(pkm.ram[1]) + "");
        } else if (cb_pilihram.getSelectedIndex() == 2) {
            tf_harga_ram.setText("Rp. 470.000");
            hargaRam = 470_000;
            arr[2] = hargaRam;
            tf_GTotal.setText(menghitungTotal());
            label_stokram.setText(queryStock(pkm.ram[2]) + "");
        } else if (cb_pilihram.getSelectedIndex() == 3) {
            tf_harga_ram.setText("Rp. 600.000");
            hargaRam = 600_000;
            arr[2] = hargaRam;
            tf_GTotal.setText(menghitungTotal());
            label_stokram.setText(queryStock(pkm.ram[3]) + "");
        } else if (cb_pilihram.getSelectedIndex() == 4) {
            tf_harga_ram.setText("Rp. 490.000");
            hargaRam = 490_000;
            arr[2] = hargaRam;
            tf_GTotal.setText(menghitungTotal());
            label_stokram.setText(queryStock(pkm.ram[4]) + "");
        } else if (cb_pilihram.getSelectedIndex() == 5) {
            tf_harga_ram.setText("Rp. 1.200.000");
            hargaRam = 1_200_000;
            arr[2] = hargaRam;
            tf_GTotal.setText(menghitungTotal());
            label_stokram.setText(queryStock(pkm.ram[5]) + "");
        } else {
            tf_harga_ram.setText(Format.formatKosong());
            hargaRam = 0;
            arr[2] = hargaRam;
            tf_GTotal.setText(menghitungTotal());
            label_stokram.setText(Format.formatKosong());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_pilihramActionPerformed

    private void tf_GTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_GTotalActionPerformed

    }//GEN-LAST:event_tf_GTotalActionPerformed

    private void tf_harga_prosesorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_harga_prosesorKeyReleased

    }//GEN-LAST:event_tf_harga_prosesorKeyReleased

    private void label_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_logoutMouseClicked
        LoginAndRegisterForm larf = new LoginAndRegisterForm();
        larf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_label_logoutMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(true); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void label_searchuser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_searchuser1MouseClicked
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(true); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        scrollpane_lihatuser.setVisible(true);
        scrollpane_stock.setVisible(false);
        panel_lihatuserdanstock.setVisible(false);
        lihatuser();
        label_totaluser.setText(banyakUser() + "");
        // TODO add your handling code here:
    }//GEN-LAST:event_label_searchuser1MouseClicked
    public void lihatuser() {
        DefaultTableModel tipe1 = new DefaultTableModel(new String[]{"ID", "FullName", "Username", "Email", "PhoneNumber", "TotalTransaksi", "Status Member", "Points"}, 0);
        db.koneksiDatabase();
        try {
            String sql = "SELECT * FROM user m INNER JOIN membership u ON m.id = u.id";
            Statement stm = db.getKoneksi().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String fullname = rs.getString("fullname");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phonenumber = rs.getString("phonenumber");
                int totaltransaksi = rs.getInt("total_transaction");
                String status = rs.getString("status");
                int points = rs.getInt("points");
                tipe1.addRow(new Object[]{id, fullname, username, email, phonenumber, totaltransaksi, status, points});
                tabel_user.setModel(tipe1);
            }
        } catch (Exception e) {

        }
    }
    private void label_checkstockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_checkstockMouseClicked
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(true); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        scrollpane_lihatuser.setVisible(false);
        scrollpane_stock.setVisible(true);
        panel_lihatuserdanstock.setVisible(false);
        label_pendapatan.setText(formatRp(totalAmount()) + "");

        liatStock();
    }//GEN-LAST:event_label_checkstockMouseClicked
    public void liatStock() {
        DefaultTableModel tipe1 = new DefaultTableModel(new String[]{"Product", "Stock", "TipeKomponen"}, 0);
        db.koneksiDatabase();
        try {
            String sql = "SELECT * FROM stock";
            Statement stm = db.getKoneksi().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String produk = rs.getString("TipeProduct"); //Vga Ram Dll
                int stock = rs.getInt("Stock");
                String component = rs.getString("Component");//Jenis Product
                tipe1.addRow(new Object[]{produk, stock, component});
                tabel_stok.setModel(tipe1);
            }
            lebarKolom();
        } catch (Exception e) {

        }
    }

    public void updatePoint(int masukanPoint) {
        db.koneksiDatabase();
        try {
            String query = "update membership set points = ? where id = ? ";
            PreparedStatement state = db.getKoneksi().prepareStatement(query);
            state.setInt(1, masukanPoint);
            state.setInt(2, id_user);
            state.execute();
            tf_masukanpoin.setText(Format.formatKosong());
        } catch (Exception e) {

        }
    }

    public void updateTotalTransaksi() {
        db.koneksiDatabase();
        try {
            String query = "update user set total_transaction = ? where id = ? ";
            PreparedStatement state = db.getKoneksi().prepareStatement(query);
            state.setInt(1, total_transaksi_now + grandtotal);
            state.setInt(2, id_user);
            state.execute();
        } catch (Exception e) {

        }
    }

    public void updateStock(int stockKurang, String namaBarang) {
        db.koneksiDatabase();
        try {
            String query = "update stock set stock = ? where TipeProduct = ? ";
            PreparedStatement state = db.getKoneksi().prepareStatement(query);
            state.setInt(1, stockKurang);
            state.setString(2, namaBarang);
            state.execute();
        } catch (Exception e) {

        }
    }

    private void button_konversiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_konversiMouseClicked
        if (totalPoint() == 0) {
            JOptionPane.showMessageDialog(null, "Anda tidak memiliki point!");
            tf_masukanpoin.setText(Format.formatKosong());
        } else {
            if (tf_masukanpoin.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Input tidak boleh kosong!");
                tf_masukanpoin.setText(Format.formatKosong());
            } else if (!tf_masukanpoin.getText().matches("[0-9]*")) {
                JOptionPane.showMessageDialog(null, "Input harus angka !");
                tf_masukanpoin.setText(Format.formatKosong());
            } else if (Integer.parseInt(tf_masukanpoin.getText()) > totalPoint()) {
                JOptionPane.showMessageDialog(null, "Point anda tidak sesuai!");
                tf_masukanpoin.setText(Format.formatKosong());
            } else {
                int a = Integer.parseInt(tf_masukanpoin.getText());
                try {
                    if (memberStatus.equalsIgnoreCase("bronze")) {
                        int conBronze = 2000;
                        conBronze *= a;
                        tf_GTotal.setText(formatRp(grandtotal - conBronze) + "");
                        grandtotal -= conBronze;
                        updatePoint(totalPoint() - a);
                    } else if (memberStatus.equalsIgnoreCase("Silver")) {
                        int conSilper = 2500;
                        conSilper *= a;
                        tf_GTotal.setText(formatRp(grandtotal - conSilper) + "");
                        grandtotal -= conSilper;
                        updatePoint(totalPoint() - a);
                    } else if (memberStatus.equalsIgnoreCase("Gold")) {
                        int conGold = 3000;
                        conGold *= a;
                        tf_GTotal.setText(formatRp(grandtotal - conGold) + "");
                        grandtotal -= conGold;
                        updatePoint(totalPoint() - a);
                    } else {
                        JOptionPane.showMessageDialog(null, "Anda tidak terdaftar sebagai member!");
                        tf_masukanpoin.setText(Format.formatKosong());
                    }
                } catch (Exception e) {

                }
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_button_konversiMouseClicked
    public int cetakkode() {
        db.koneksiDatabase();
        int tampung = 0;
        try {
            String query1 = "SELECT * FROM costumers_things";
            Statement st = db.getKoneksi().createStatement();
            ResultSet rsLogin = st.executeQuery(query1);
            while (rsLogin.next()) {
                tampung++;
            }

        } catch (Exception e) {

        }
        return tampung;
    }
    private void button_previewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_previewActionPerformed
        String id = tf_kodesimulasi.getText().substring(3, tf_kodesimulasi.getText().length());
        int kode = Integer.parseInt(id);
        db.koneksiDatabase();
        try {
            String query1 = "SELECT * FROM costumers_things WHERE code = " + kode + "";
            Statement st = db.getKoneksi().createStatement();
            ResultSet rsLogin = st.executeQuery(query1);
            rsLogin.next();
            if (rsLogin.getRow() == 1) {
                String proci = rsLogin.getString("Processor");
                String mobo = rsLogin.getString("Motherboard");
                String ram = rsLogin.getString("RAM");
                String st1 = rsLogin.getString("Storage1");
                String st2 = rsLogin.getString("Storage2");
                String psu = rsLogin.getString("PowerSupply");
                String vga = rsLogin.getString("VGA");
                String casing = rsLogin.getString("Casing");
                tf_procicb.setText(proci);
                tf_mobocb.setText(mobo);
                tf_ramcb.setText(ram);
                tf_storage1cb.setText(st1);
                tf_storage2cb.setText(st2);
                tf_psucb.setText(psu);
                tf_vgacb.setText(vga);
                tf_casingcb.setText(casing);
                panel_profile.setVisible(false); // Ada Biodata
                panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
                panel_dashboard.setVisible(false); // Ada Dashboard
                panel_member.setVisible(false); // Ada Member
                panel_admin.setVisible(false); // Ada Admin
                panel_checkbarang.setVisible(true); // Ada CheckBarang
                panel_aboutus.setVisible(false); // Ada AboutUs
                panel_faq.setVisible(false); // Ada FAQ
                scroll_tambahbarang.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Kode Simulasi yang Anda Masukan Tidak Tersedia !");
            }
            tf_kodesimulasi.setText(Format.formatKosong());
        } catch (Exception e) {

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_button_previewActionPerformed

    private void cb_komponenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_komponenActionPerformed
        PemilihanKomponen pk = new PemilihanKomponen(cb_komponen, combobarang);
        pk.setPemilihan(combobarang);
    }//GEN-LAST:event_cb_komponenActionPerformed

    private void cb_komponenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_komponenMouseClicked

    }//GEN-LAST:event_cb_komponenMouseClicked

    private void btntambahstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahstockActionPerformed
        if (Integer.parseInt(spin_add.getValue().toString()) < 0) {
            JOptionPane.showMessageDialog(null, "Angka Tidak Boleh Kurang dari Sama Dengan 0 !");
            spin_add.setValue(0);
        } else if (cb_komponen.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Pilihan tidak boleh kosong!");
            spin_add.setValue(0);
        } else if (Integer.parseInt(spin_add.getValue().toString()) > 0) {
            int angkastock = Integer.parseInt(spin_add.getValue().toString());
            String namabarang = combobarang.getSelectedItem().toString();
            tambahStock(angkastock, namabarang);
        } else {
            JOptionPane.showMessageDialog(null, "Input Harus Berupa Angka dan Tidak Boleh 0 !");
            spin_add.setValue(0);
        }
    }//GEN-LAST:event_btntambahstockActionPerformed
    public void tambahStock(int angkastock, String barang) {
        db.koneksiDatabase();
        try {
            Statement statement = db.getKoneksi().createStatement();
            ResultSet rs = statement.executeQuery("SELECT stock FROM stock WHERE TipeProduct = '" + barang + "'");
            rs.next();
            int stocksekarang = angkastock + rs.getInt("Stock");

            String query = "update stock set stock = ? where TipeProduct = ? ";
            PreparedStatement state = db.getKoneksi().prepareStatement(query);
            state.setInt(1, stocksekarang);
            state.setString(2, barang);
            state.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Menambahkan Stock Barang !");
            cb_komponen.setSelectedIndex(0);
            combobarang.setSelectedIndex(0);
            spin_add.setValue(0);
            liatStock();
        } catch (Exception e) {

        }
    }
    private void spin_addKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spin_addKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_spin_addKeyReleased

    private void tf_masukanuangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_masukanuangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_masukanuangActionPerformed

    private void button_calculateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_calculateMouseClicked
        if (tf_masukanuang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Input tidak boleh kosong !");
        } else if (!tf_masukanuang.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Input harus Angka !");
        } else {
            int duit = Integer.parseInt(tf_masukanuang.getText());
            if (duit < grandtotal) {
                JOptionPane.showMessageDialog(null, "Uang yang anda masukan kurang !");
            } else {
                db.koneksiDatabase();
                try {
                    Connection dbconnect = db.getKoneksi();
                    String query = " insert into costumers_things (processor, motherboard, ram, storage1, storage2, powersupply, vga, casing)"
                            + " values(?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement myStatement = dbconnect.prepareStatement(query);
                    String proces = "";
                    String mobo = "";
                    if (cb_brandprosesor.getSelectedItem().equals("Intel")) {
                        myStatement.setString(1, cb_pilihprosesorintel.getSelectedItem().toString());
                        myStatement.setString(2, cb_pilihmobointel.getSelectedItem().toString());
                        proces = cb_pilihprosesorintel.getSelectedItem().toString();
                        mobo = cb_pilihmobointel.getSelectedItem().toString();
                    } else if (cb_brandprosesor.getSelectedItem().equals("AMD")) {
                        myStatement.setString(1, cb_pilihprosesoramd.getSelectedItem().toString());
                        myStatement.setString(2, cb_pilihmoboamd.getSelectedItem().toString());
                        proces = cb_pilihprosesoramd.getSelectedItem().toString();
                        mobo = cb_pilihmoboamd.getSelectedItem().toString();
                    } else {
                        myStatement.setString(1, Format.formatKosong());
                        myStatement.setString(2, Format.formatKosong());
                    }
                    String pertama = cb_pilihram.getSelectedIndex() == 0 ? "" : cb_pilihram.getSelectedItem().toString();
                    String dua = cb_storage1.getSelectedIndex() == 0 ? "" : cb_storage1.getSelectedItem().toString();
                    String tiga = cb_storage2.getSelectedIndex() == 0 ? "" : cb_storage2.getSelectedItem().toString();
                    String empat = cb_psu.getSelectedIndex() == 0 ? "" : cb_psu.getSelectedItem().toString();
                    String lima = cb_pilihvga.getSelectedIndex() == 0 ? "" : cb_pilihvga.getSelectedItem().toString();
                    String enam = cb_pilihcasing.getSelectedIndex() == 0 ? "" : cb_pilihcasing.getSelectedItem().toString();
                    myStatement.setString(3, pertama);
                    myStatement.setString(4, dua);
                    myStatement.setString(5, tiga);
                    myStatement.setString(6, empat);
                    myStatement.setString(7, lima);
                    myStatement.setString(8, enam);
                    boolean checkBarang = !pertama.equals("") || !dua.equals("") || !tiga.equals("") || !empat.equals("")
                            || !lima.equals("") || !enam.equals("") || !proces.equals("") || !mobo.equals("");
                    boolean checkStock = queryStock(pertama) >= 0 || queryStock(dua) >= 0 || queryStock(tiga) >= 0
                            || queryStock(empat) >= 0 || queryStock(lima) >= 0 || queryStock(enam) >= 0 || queryStock(proces) >= 0
                            || queryStock(mobo) >= 0;
                    boolean flag1 = true;
                    if (checkBarang) {

                        if (!pertama.equals("")) {
                            if (queryStock(pertama) > 0) {
                                updateStock(queryStock(pertama) - 1, pertama);

                            } else {
                                JOptionPane.showMessageDialog(null, "Stock Habis !");
                                flag1 = false;
                                handlingTambahBarang();
                            }
                        } else if (!dua.equals("")) {
                            if (queryStock(dua) > 0) {
                                updateStock(queryStock(dua) - 1, dua);

                            } else {
                                JOptionPane.showMessageDialog(null, "Stock Habis !");
                                flag1 = false;
                                handlingTambahBarang();
                            }
                        } else if (!tiga.equals("")) {
                            if (queryStock(tiga) > 0) {
                                updateStock(queryStock(tiga) - 1, tiga);

                            } else {
                                JOptionPane.showMessageDialog(null, "Stock Habis !");
                                flag1 = false;
                                handlingTambahBarang();
                            }
                        } else if (!empat.equals("")) {
                            if (queryStock(empat) > 0) {
                                updateStock(queryStock(empat) - 1, empat);

                            } else {
                                JOptionPane.showMessageDialog(null, "Stock Habis !");
                                flag1 = false;
                                handlingTambahBarang();
                            }
                        } else if (!lima.equals("")) {
                            if (queryStock(lima) > 0) {
                                updateStock(queryStock(lima) - 1, lima);

                            } else {
                                JOptionPane.showMessageDialog(null, "Stock Habis !");
                                flag1 = false;
                                handlingTambahBarang();
                            }
                        } else if (!enam.equals("")) {
                            if (queryStock(enam) > 0) {
                                updateStock(queryStock(enam) - 1, enam);

                            } else {
                                JOptionPane.showMessageDialog(null, "Stock Habis !");
                                flag1 = false;
                                handlingTambahBarang();
                            }
                        } else if (!proces.equals("")) {
                            if (queryStock(proces) > 0) {
                                updateStock(queryStock(proces) - 1, proces);

                            } else {
                                JOptionPane.showMessageDialog(null, "Stock Habis !");
                                flag1 = false;
                                handlingTambahBarang();
                            }
                        } else if (!mobo.equals("")) {
                            if (queryStock(mobo) > 0) {
                                updateStock(queryStock(mobo) - 1, mobo);

                            } else {
                                JOptionPane.showMessageDialog(null, "Stock Habis !");
                                flag1 = false;
                                handlingTambahBarang();
                            }
                        }
                        if (checkStock && flag1) {
                            myStatement.execute();
                            JOptionPane.showMessageDialog(null, "Transaksi Berhasil !! \nKode Simulasi Anda PC-" + cetakkode()
                                    + "\nKembalian Anda Sebesar " + formatRp((double) duit - grandtotal)
                                    + "\nSelamat Anda mendapatkan penambahan 2 Point!");
                            updateTotalTransaksi();
                            updatePoint(totalPoint() + 2);
                            total_transaksi.setText(formatRp((double) total_transaksi_now) + "");
                            total_point.setText(totalPoint() + " Points");
                            handlingTambahBarang();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Harap Masukan Barang Anda !");
                        handlingTambahBarang();
                    }
                } catch (Exception e) {

                }
            }
        }


    }//GEN-LAST:event_button_calculateMouseClicked

    public void handlingTambahBarang() {
        cb_brandprosesor.setSelectedIndex(0);
        cb_pilihprosesoramd.setSelectedIndex(0);
        cb_pilihprosesorintel.setSelectedIndex(0);
        cb_pilihmobointel.setSelectedIndex(0);
        cb_pilihmoboamd.setSelectedIndex(0);
        cb_pilihram.setSelectedIndex(0);
        cb_storage1.setSelectedIndex(0);
        cb_storage2.setSelectedIndex(0);
        cb_psu.setSelectedIndex(0);
        cb_pilihvga.setSelectedIndex(0);
        cb_pilihcasing.setSelectedIndex(0);
        tf_harga_prosesor.setText("");
        tf_harga_mobo.setText("");
        tf_harga_ram.setText("");
        tf_harga_sto1.setText("");
        tf_harga_sto2.setText("");
        tf_harga_psu.setText("");
        tf_harga_vga.setText("");
        tf_harga_casing.setText("");
        tf_GTotal.setText("");
        tf_masukanpoin.setText("");
        tf_masukanuang.setText("");
        label_stokcasing.setText("");
        label_stokproci.setText("");
        label_stokram.setText("");
        label_stokst1.setText("");
        label_stokst2.setText("");
        label_stockpsu.setText("");
        label_stockvga.setText("");
        label_stokmobo.setText("");
    }
    private void btn_beliIntelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_beliIntelMouseClicked
        // TODO add your handling code here:
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(true); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(true);
    }//GEN-LAST:event_btn_beliIntelMouseClicked

    private void btn_beliAmdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_beliAmdMouseClicked
        // TODO add your handling code here:
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(true); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(true);
    }//GEN-LAST:event_btn_beliAmdMouseClicked

    private void btn_beliMoboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_beliMoboMouseClicked
        // TODO add your handling code here:
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(true); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(true);
    }//GEN-LAST:event_btn_beliMoboMouseClicked

    private void btn_beliCasisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_beliCasisMouseClicked
        // TODO add your handling code here:
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(true); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(true);
    }//GEN-LAST:event_btn_beliCasisMouseClicked

    private void btn_beliVgaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_beliVgaMouseClicked
        // TODO add your handling code here:
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(true); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(true);
    }//GEN-LAST:event_btn_beliVgaMouseClicked

    private void ikon_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ikon_backMouseClicked
        // TODO add your handling code here:
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(true); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(true);

    }//GEN-LAST:event_ikon_backMouseClicked

    private void ikon_backlihatstokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ikon_backlihatstokMouseClicked
        // TODO add your handling code here:
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(true); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        panel_lihatuserdanstock.setVisible(true);
        scrollpane_stock.setVisible(false);

    }//GEN-LAST:event_ikon_backlihatstokMouseClicked

    private void ikon_backlihatuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ikon_backlihatuserMouseClicked
        // TODO add your handling code here:
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        panel_dashboard.setVisible(false); // Ada Dashboard
        scrollpane_dashboard.setVisible(false);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(true); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        panel_lihatuserdanstock.setVisible(true);
        scrollpane_lihatuser.setVisible(false);
    }//GEN-LAST:event_ikon_backlihatuserMouseClicked

    private void ikon_backdbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ikon_backdbMouseClicked
        // TODO add your handling code here:
        panel_profile.setVisible(false); // Ada Biodata
        panel_tambah_barang.setVisible(false); // Ada Tambahkan Barang
        panel_dashboard.setVisible(true); // Ada Dashboard
        scrollpane_dashboard.setVisible(true);
        panel_member.setVisible(false); // Ada Member
        panel_admin.setVisible(false); // Ada Admin
        panel_checkbarang.setVisible(false); // Ada CheckBarang
        panel_aboutus.setVisible(false); // Ada AboutUs
        panel_faq.setVisible(false); // Ada FAQ
        scroll_tambahbarang.setVisible(false);
        panel_lihatuserdanstock.setVisible(false);
        scrollpane_lihatuser.setVisible(false);
    }//GEN-LAST:event_ikon_backdbMouseClicked

    public String menghitungTotal() {
        int i = 0;
        double count = 0;
        while (i < 8) {
            count += arr[i];
            i++;
        }
        grandtotal = (int) count;
        if (count <= 0) {
            return "";
        }
        return formatRp(count);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_beliAmd;
    private javax.swing.JButton btn_beliCasis;
    private javax.swing.JButton btn_beliIntel;
    private javax.swing.JButton btn_beliMobo;
    private javax.swing.JButton btn_beliVga;
    private javax.swing.JButton btntambahstock;
    private javax.swing.JButton button_bayar_memb;
    private javax.swing.JButton button_calculate;
    private javax.swing.JButton button_konversi;
    private javax.swing.JButton button_preview;
    private javax.swing.JComboBox<String> cb_brandprosesor;
    private javax.swing.JComboBox<String> cb_komponen;
    private javax.swing.JComboBox<String> cb_pilihcasing;
    private javax.swing.JComboBox<String> cb_pilihmoboamd;
    private javax.swing.JComboBox<String> cb_pilihmobointel;
    private javax.swing.JComboBox<String> cb_pilihmobokosong;
    private javax.swing.JComboBox<String> cb_pilihprosesoramd;
    private javax.swing.JComboBox<String> cb_pilihprosesorintel;
    private javax.swing.JComboBox<String> cb_pilihprosesorkosong;
    private javax.swing.JComboBox<String> cb_pilihram;
    private javax.swing.JComboBox<String> cb_pilihvga;
    private javax.swing.JComboBox<String> cb_psu;
    private javax.swing.JComboBox<String> cb_storage1;
    private javax.swing.JComboBox<String> cb_storage2;
    private javax.swing.JComboBox<String> combobarang;
    private javax.swing.JLabel icon_member;
    private javax.swing.JLabel ikon_back;
    private javax.swing.JLabel ikon_backdb;
    private javax.swing.JLabel ikon_backlihatstok;
    private javax.swing.JLabel ikon_backlihatuser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel label_FAQ;
    private javax.swing.JLabel label_GTotal;
    private javax.swing.JLabel label_MCDesktop;
    private javax.swing.JLabel label_about;
    private javax.swing.JLabel label_aboutus;
    private javax.swing.JLabel label_admin;
    private javax.swing.JLabel label_back;
    private javax.swing.JLabel label_backlihatstok;
    private javax.swing.JLabel label_baristiga;
    private javax.swing.JLabel label_baristigalagi;
    private javax.swing.JLabel label_bg;
    private javax.swing.JLabel label_bg_baristiga;
    private javax.swing.JLabel label_bg_totaluser;
    private javax.swing.JLabel label_brandprocie;
    private javax.swing.JLabel label_bronze_close;
    private javax.swing.JLabel label_bronze_open;
    private javax.swing.JLabel label_casing;
    private javax.swing.JLabel label_casingcb;
    private javax.swing.JLabel label_cbarang;
    private javax.swing.JLabel label_checkstock;
    private javax.swing.JLabel label_db;
    private javax.swing.JLabel label_email;
    private javax.swing.JLabel label_faq;
    private javax.swing.JLabel label_gold_close;
    private javax.swing.JLabel label_gold_open;
    private javax.swing.JLabel label_harga1;
    private javax.swing.JLabel label_header;
    private javax.swing.JLabel label_ic_email;
    private javax.swing.JLabel label_ic_nama;
    private javax.swing.JLabel label_ic_wa;
    private javax.swing.JLabel label_kembaliuser;
    private javax.swing.JLabel label_kodesimulasi;
    private javax.swing.JLabel label_logout;
    private javax.swing.JLabel label_masukanpoinanda;
    private javax.swing.JLabel label_masukanuanganda1;
    private javax.swing.JLabel label_masukanuangmember;
    private javax.swing.JLabel label_memb;
    private javax.swing.JLabel label_mobo;
    private javax.swing.JLabel label_mobocb;
    private javax.swing.JLabel label_namalengkap;
    private javax.swing.JLabel label_nohp;
    private javax.swing.JLabel label_pembayaran;
    private javax.swing.JLabel label_pendapatan;
    private javax.swing.JLabel label_procie;
    private javax.swing.JLabel label_prociecb;
    private javax.swing.JLabel label_profile;
    private javax.swing.JLabel label_psu;
    private javax.swing.JLabel label_psucb;
    private javax.swing.JLabel label_ram;
    private javax.swing.JLabel label_ramcb;
    private javax.swing.JLabel label_searchuser1;
    private javax.swing.JLabel label_silper_close;
    private javax.swing.JLabel label_silper_open;
    private javax.swing.JLabel label_st1;
    private javax.swing.JLabel label_st1cb;
    private javax.swing.JLabel label_st2;
    private javax.swing.JLabel label_st2cb;
    private javax.swing.JLabel label_stockpsu;
    private javax.swing.JLabel label_stockvga;
    private javax.swing.JLabel label_stokcasing;
    private javax.swing.JLabel label_stokmobo;
    private javax.swing.JLabel label_stokproci;
    private javax.swing.JLabel label_stokram;
    private javax.swing.JLabel label_stokst1;
    private javax.swing.JLabel label_stokst2;
    private javax.swing.JLabel label_tbarang;
    private javax.swing.JLabel label_totaluser;
    private javax.swing.JLabel label_txt_checkstock;
    private javax.swing.JLabel label_txt_pendapatan;
    private javax.swing.JLabel label_txt_searchuser;
    private javax.swing.JLabel label_txt_stock;
    private javax.swing.JLabel label_txtuser;
    private javax.swing.JLabel label_updatepass;
    private javax.swing.JLabel label_username;
    private javax.swing.JLabel label_vga;
    private javax.swing.JLabel label_vgacb;
    private javax.swing.JLabel nama_dashboard;
    private javax.swing.JPanel panel_aboutus;
    private javax.swing.JPanel panel_admin;
    private javax.swing.JPanel panel_bg_kategori;
    private javax.swing.JPanel panel_bronze;
    private javax.swing.JPanel panel_checkbarang;
    private javax.swing.JPanel panel_container;
    private javax.swing.JPanel panel_dashboard;
    private javax.swing.JPanel panel_db_tPengguna;
    private javax.swing.JPanel panel_db_totaltransaksi;
    private javax.swing.JPanel panel_db_welcome;
    private javax.swing.JPanel panel_faq;
    private javax.swing.JPanel panel_gold;
    private javax.swing.JPanel panel_kat_aboutus;
    private javax.swing.JPanel panel_kat_administrator;
    private javax.swing.JPanel panel_kat_checkbarang;
    private javax.swing.JPanel panel_kat_dashboard;
    private javax.swing.JPanel panel_kat_faq;
    private javax.swing.JPanel panel_kat_member;
    private javax.swing.JPanel panel_kat_profile;
    private javax.swing.JPanel panel_kat_tambahbarang;
    private javax.swing.JPanel panel_lihatuser;
    private javax.swing.JPanel panel_lihatuserdanstock;
    private javax.swing.JPanel panel_member;
    private javax.swing.JPanel panel_profile;
    private javax.swing.JPanel panel_silper;
    private javax.swing.JPanel panel_stocktabel;
    private javax.swing.JPanel panel_tambah_barang;
    private javax.swing.JPanel panel_utama;
    private javax.swing.JScrollPane scroll_tambahbarang;
    private javax.swing.JScrollPane scrollpane_dashboard;
    private javax.swing.JScrollPane scrollpane_lihatuser;
    private javax.swing.JScrollPane scrollpane_stock;
    private javax.swing.JSpinner spin_add;
    private javax.swing.JTable tabel_stok;
    private javax.swing.JTable tabel_user;
    private javax.swing.JTextField tf_GTotal;
    private javax.swing.JTextField tf_casingcb;
    private javax.swing.JTextField tf_harga_casing;
    private javax.swing.JTextField tf_harga_mobo;
    private javax.swing.JTextField tf_harga_prosesor;
    private javax.swing.JTextField tf_harga_psu;
    private javax.swing.JTextField tf_harga_ram;
    private javax.swing.JTextField tf_harga_sto1;
    private javax.swing.JTextField tf_harga_sto2;
    private javax.swing.JTextField tf_harga_vga;
    private javax.swing.JTextField tf_kodesimulasi;
    private javax.swing.JTextField tf_masukanpoin;
    private javax.swing.JTextField tf_masukanuang;
    private javax.swing.JTextField tf_masukanuang_memb;
    private javax.swing.JTextField tf_mobocb;
    private javax.swing.JTextField tf_procicb;
    private javax.swing.JTextField tf_psucb;
    private javax.swing.JTextField tf_ramcb;
    private javax.swing.JTextField tf_storage1cb;
    private javax.swing.JTextField tf_storage2cb;
    private javax.swing.JTextField tf_vgacb;
    private javax.swing.JLabel total_pengguna;
    private javax.swing.JLabel total_point;
    private javax.swing.JLabel total_transaksi;
    // End of variables declaration//GEN-END:variables
    private void labelcolor(JLabel label) {
        label.setBackground(new java.awt.Color(53, 162, 107));
    }

    private void resetlabelcolor(JLabel label) {
        label.setBackground(new java.awt.Color(54, 70, 78));
    }
}
