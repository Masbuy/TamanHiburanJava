import javax.swing.*;
import java.awt.*;


public class ArunikaWorldApp extends JFrame {
    private JButton orderButton;

    public ArunikaWorldApp() {
        setTitle("Arunika World");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Panel utama dengan latar warna sesuai gambar (cyan muda)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(173, 240, 255)); // Cyan muda sesuai gambar
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel);

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;

        // Label Logo dengan gambar dari src/logo.png
// Label Logo dengan ukuran yang bisa diatur
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); // <-- Ubah ukuran di sini
        ImageIcon logoIcon = new ImageIcon(scaledImage);

        JLabel logoLabel = new JLabel(logoIcon);
        gbc.gridy = 0;
        mainPanel.add(logoLabel, gbc);
        

        // Tombol persis seperti di gambar
        orderButton = new JButton("TEKAN DISINI UNTUK MEMESAN");
        orderButton.setBackground(new Color(0, 150, 150)); // Warna hijau kebiruan
        orderButton.setForeground(Color.WHITE);
        orderButton.setFocusPainted(false);
        orderButton.setFont(new Font("Arial", Font.PLAIN, 14));
        orderButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));  // Round corners di default Swing tidak ada, ini mengatur padding
        orderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Membuat tombol rounded corners dengan override paintComponent
        orderButton.setContentAreaFilled(false);
        orderButton.setOpaque(true);
        orderButton.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 150)));

        // Aksi tombol
        orderButton.addActionListener(e -> {
            new TiketArunikaApp().setVisible(true); // buka halaman tiket
            dispose(); // tutup halaman awal
        });

        gbc.gridy = 1;
        mainPanel.add(orderButton, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ArunikaWorldApp().setVisible(true);
        });
    }
}
