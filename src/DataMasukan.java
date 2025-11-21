import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DataMasukan extends JFrame {
    private String name;
    private String phone;
    private String email;
    private String date;
    private String orderSummary;
    private String totalAmount;

    public DataMasukan(String name, String phone, String email, String date, String orderSummary, String totalAmount) {
        this.name = name == null ? "" : name;
        this.phone = phone == null ? "" : phone;
        this.email = email == null ? "" : email;
        this.date = date == null ? "" : date;
        this.orderSummary = orderSummary == null ? "" : orderSummary;
        this.totalAmount = totalAmount == null ? "" : totalAmount;

        setTitle("Informasi Pemesanan - Arunika World");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        initUI();
    }

    private void initUI() {

        // Background luar
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(173, 240, 255));
        root.setBorder(new EmptyBorder(24, 24, 24, 24));
        setContentPane(root);

        // Panel isi utama
        JPanel content = new JPanel(new BorderLayout(24, 24));
        content.setOpaque(false);
        root.add(content, BorderLayout.CENTER);

        // ======== LEFT COLUMN ========
        JPanel leftColumn = new JPanel();
        leftColumn.setOpaque(false);
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        leftColumn.setBorder(new EmptyBorder(0, 0, 0, 0));

        leftColumn.add(createInfoBox("Informasi Tiket", orderSummary));
        leftColumn.add(Box.createRigidArea(new Dimension(0, 24)));
        leftColumn.add(createDataDiriBox(name, phone, email, date));

        content.add(leftColumn, BorderLayout.CENTER);

        // ======== RIGHT COLUMN ========
        JPanel rightColumn = new JPanel(new BorderLayout());
        rightColumn.setOpaque(false);
        rightColumn.setPreferredSize(new Dimension(360, 0));

        JPanel summary = new JPanel(new BorderLayout(16, 16));
        summary.setBackground(new Color(7, 146, 136));
        summary.setBorder(new EmptyBorder(24, 24, 24, 24));

        JLabel summaryTitle = new JLabel("Ringkasan Pesanan");
        summaryTitle.setForeground(Color.WHITE);
        summaryTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        summary.add(summaryTitle, BorderLayout.NORTH);

        JPanel mid = new JPanel();
        mid.setOpaque(false);
        mid.setLayout(new BoxLayout(mid, BoxLayout.Y_AXIS));

        JLabel price = new JLabel(totalAmount.isEmpty() ? "" : totalAmount);
        price.setForeground(Color.WHITE);
        price.setFont(new Font("SansSerif", Font.PLAIN, 16));
        mid.add(price);

        summary.add(mid, BorderLayout.CENTER);

        JButton pay = new JButton("selesaikan pembayaran");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setFocusPainted(false);

        JButton edit = new JButton("Edit Pesanan");
        edit.setBackground(Color.DARK_GRAY);
        edit.setForeground(Color.WHITE);
        edit.setFocusPainted(false);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnRow.setOpaque(false);
        btnRow.add(edit);
        btnRow.add(pay);
        summary.add(btnRow, BorderLayout.SOUTH);

        pay.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Pembayaran telah diproses (simulasi).", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        edit.addActionListener(e -> {
            // Open DataPembelian with the current values so the user can edit
            SwingUtilities.invokeLater(() -> {
                DataPembelian dp = new DataPembelian(name, phone, email, date, orderSummary, totalAmount);
                dp.setVisible(true);
            });
            // close current summary window
            this.dispose();
        });
        
        
        rightColumn.add(summary, BorderLayout.NORTH);

        content.add(rightColumn, BorderLayout.EAST);
    }

    // ======== BOX: Informasi Tiket ========
        private JPanel createInfoBox(String title, String orderText) {
            JPanel box = new JPanel();
            box.setBackground(new Color(9, 165, 147));
            box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
            box.setBorder(new EmptyBorder(24, 24, 24, 24));

            // ==== JUDUL ====
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            titleLabel.setForeground(Color.WHITE);
            box.add(titleLabel);

            // ==== GARIS TIPIS (sesuai gambar) ====
            JSeparator sep = new JSeparator();
            sep.setForeground(Color.WHITE);
            sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
            box.add(Box.createRigidArea(new Dimension(0, 8)));
            box.add(sep);
            box.add(Box.createRigidArea(new Dimension(0, 12)));

            // ==== DETAIL PESANAN ====
            JTextArea ta = new JTextArea();
            ta.setEditable(false);
            ta.setOpaque(false);
            ta.setForeground(Color.WHITE);
            ta.setFont(new Font("SansSerif", Font.PLAIN, 16));
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            ta.setBorder(null);

            ta.setText(orderText == null || orderText.isEmpty()
                    ? "- Belum ada pesanan -"
                    : orderText);

            box.add(ta);

            return box;
        }


         /// ======== BOX: Data Diri ========
        private JPanel createDataDiriBox(String name, String phone, String email, String date) {
            JPanel box = new JPanel();
            box.setBackground(new Color(9, 165, 147));
            box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
            box.setBorder(new EmptyBorder(24, 24, 24, 24));

            // ==== JUDUL ====
            JLabel titleLabel = new JLabel("Data Diri");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            titleLabel.setForeground(Color.WHITE);
            box.add(titleLabel);

            // ==== GARIS TIPIS ====
            JSeparator sep = new JSeparator();
            sep.setForeground(Color.WHITE);
            sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
            box.add(Box.createRigidArea(new Dimension(0, 8)));
            box.add(sep);
            box.add(Box.createRigidArea(new Dimension(0, 12)));

            // ==== ISI DATA DIRI ====
            JTextArea ta = new JTextArea();
            ta.setEditable(false);
            ta.setOpaque(false);
            ta.setForeground(Color.WHITE);
            ta.setFont(new Font("SansSerif", Font.PLAIN, 15));
            ta.setBorder(null);

            ta.setText(
                "Nama : " + (name.isEmpty() ? "-" : name) + "\n" +
                "No Telepon : " + (phone.isEmpty() ? "-" : phone) + "\n" +
                "Email : " + (email.isEmpty() ? "-" : email) + "\n" +
                "Tanggal Kedatangan : " + (date.isEmpty() ? "-" : date)
            );

            box.add(ta);

            return box;
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataMasukan f = new DataMasukan("", "", "", "", "", "");
            f.setVisible(true);
        });
    }
}
