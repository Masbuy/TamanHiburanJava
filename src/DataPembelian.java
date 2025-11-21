import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DataPembelian extends JFrame {
    private final Color BACK = new Color(204, 255, 255);
    private final Color BRAND = new Color(9, 165, 147);
    private final Color INPUT_BG = Color.BLACK;
    private final Color PLACEHOLDER = new Color(160, 160, 160);

    public DataPembelian() {
        super("Data Pembelian - Arunika World");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 720);
        setLocationRelativeTo(null);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        initUI();
    }

    // ================== SHADOW LABEL PRO ==================
    class ShadowLabel extends JLabel {
        private Color shadowColor = new Color(0, 0, 0, 110);
        private int shadowOffset = 4;

        public ShadowLabel(String text) {
            super(text);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(getFont());

            FontMetrics fm = g2.getFontMetrics();
            int w = fm.stringWidth(getText());
            int h = fm.getHeight();
            int ascent = fm.getAscent();

            int x = (getWidth() - w) / 2;
            int y = (getHeight() - h) / 2 + ascent;

            // SHADOW
            g2.setColor(shadowColor);
            g2.drawString(getText(), x + shadowOffset, y + shadowOffset);

            // OUTLINE
            g2.setColor(new Color(0, 0, 0, 180));
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (i == 0 && j == 0) continue;
                    g2.drawString(getText(), x + i, y + j);
                }
            }

            // GLOW PUTIH
            g2.setColor(new Color(255, 255, 255, 130));
            g2.drawString(getText(), x, y);

            // TEKS UTAMA
            g2.setColor(getForeground());
            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }
    // =====================================================

    private void initUI() {
        getContentPane().setBackground(BACK);
        getContentPane().setLayout(new BorderLayout(16, 16));

        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.setOpaque(false);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BRAND);
        header.setBorder(new EmptyBorder(28, 28, 28, 28));

        ShadowLabel title = new ShadowLabel(spaced("ARUNIKA WORLD"));
        title.setForeground(new Color(255, 255, 255));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 72));
        header.add(title, BorderLayout.CENTER);
        

        topContainer.add(header);

        JPanel lightStrip = new JPanel();
        lightStrip.setBackground(BACK);
        lightStrip.setPreferredSize(new Dimension(0, 28));
        topContainer.add(lightStrip);

        getContentPane().add(topContainer, BorderLayout.NORTH);

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(BRAND);
        main.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(18, 18, 18, 18);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5;
        nameField = createPlaceholderField("Masukkan Nama lengkap anda",300);
        form.add(labeledPanel("Nama Lengkap", nameField), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.5;
        emailField = createPlaceholderField("Exc: arunikaworld@gmail.com",300);
        form.add(labeledPanel("Email", emailField), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        phoneField = createPlaceholderField("Exc: 085700000000",300);
        form.add(labeledPanel("No Telepon", phoneField), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        dateField = createDateField(300);
        form.add(labeledPanel("Tanggal", dateField), gbc);

        main.add(form, BorderLayout.CENTER);
        // If this form was opened with initial values, populate fields (replace placeholders)
        if (initialName != null && !initialName.isEmpty()) {
            nameField.setText(initialName);
            nameField.setForeground(Color.WHITE);
        }
        if (initialEmail != null && !initialEmail.isEmpty()) {
            emailField.setText(initialEmail);
            emailField.setForeground(Color.WHITE);
        }
        if (initialPhone != null && !initialPhone.isEmpty()) {
            phoneField.setText(initialPhone);
            phoneField.setForeground(Color.WHITE);
        }
        if (initialDate != null && !initialDate.isEmpty()) {
            dateField.setText(initialDate);
            dateField.setForeground(Color.WHITE);
        }
        getContentPane().add(main, BorderLayout.CENTER);

        // If navigated from ticket selection, show a right-side summary panel
        if (orderSummaryText != null && !orderSummaryText.trim().isEmpty()) {
            JPanel summaryPanel = new JPanel(new BorderLayout());
            summaryPanel.setBackground(BRAND);
            summaryPanel.setPreferredSize(new Dimension(360, 0));
            summaryPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

            JPanel summaryBox = new JPanel(new BorderLayout());
            summaryBox.setBackground(new Color(134, 221, 209));
            summaryBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            JTextArea orderArea = new JTextArea(orderSummaryText);
            orderArea.setEditable(false);
            orderArea.setBackground(new Color(134, 221, 209));
            orderArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
            orderArea.setForeground(Color.BLACK);
            orderArea.setLineWrap(true);
            orderArea.setWrapStyleWord(true);
            orderArea.setMargin(new Insets(10, 10, 10, 10));

            JScrollPane sp = new JScrollPane(orderArea);
            sp.setBorder(null);
            sp.setBackground(new Color(134, 221, 209));
            summaryBox.add(sp, BorderLayout.CENTER);

            JPanel bottomSummaryPanel = new JPanel(new GridLayout(2, 1, 0, 10));
            bottomSummaryPanel.setBackground(new Color(134, 221, 209));
            bottomSummaryPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JLabel totalText = new JLabel("Jumlah");
            totalText.setFont(new Font("SansSerif", Font.BOLD, 18));
            totalText.setForeground(Color.BLACK);

            JLabel totLabel = new JLabel(totalAmount != null ? totalAmount : "");
            totLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            totLabel.setForeground(Color.BLACK);

            JPanel totalRow = new JPanel(new BorderLayout());
            totalRow.setBackground(new Color(134, 221, 209));
            totalRow.add(totalText, BorderLayout.WEST);
            totalRow.add(totLabel, BorderLayout.EAST);

            bottomSummaryPanel.add(totalRow);

            JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            btnRow.setBackground(new Color(134, 221, 209));
            JButton editButton = new JButton("Edit Pesanan");
            editButton.setBackground(Color.BLACK);
            editButton.setForeground(Color.WHITE);
            editButton.setFocusPainted(false);
            editButton.addActionListener(ev -> {
                SwingUtilities.invokeLater(() -> {
                    TiketArunikaApp t = new TiketArunikaApp();
                    t.setVisible(true);
                });
                DataPembelian.this.dispose();
            });
            btnRow.add(editButton);
            bottomSummaryPanel.add(btnRow);

            summaryBox.add(bottomSummaryPanel, BorderLayout.SOUTH);
            summaryPanel.add(summaryBox, BorderLayout.CENTER);
            getContentPane().add(summaryPanel, BorderLayout.EAST);
        }

        JButton submit = new JButton("Klik Disini");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setPreferredSize(new Dimension(220,36));
        submit.addActionListener(e -> onSubmit());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.setOpaque(false);
        bottom.add(submit);

        getContentPane().add(bottom, BorderLayout.SOUTH);
    }

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField dateField;
    // optional order data passed from ticket selection
    private String orderSummaryText;
    private String totalAmount;
    // optional initial values when returning from DataMasukan
    private String initialName = "";
    private String initialEmail = "";
    private String initialPhone = "";
    private String initialDate = "";

    public DataPembelian(String orderSummary, String total) {
        super("Data Pembelian - Arunika World");
        this.orderSummaryText = orderSummary;
        this.totalAmount = total;
        this.initialName = "";
        this.initialEmail = "";
        this.initialPhone = "";
        this.initialDate = "";
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 720);
        setLocationRelativeTo(null);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        initUI();
    }

    public DataPembelian(String name, String phone, String email, String date, String orderSummary, String total) {
        super("Data Pembelian - Arunika World");
        this.initialName = name == null ? "" : name;
        this.initialPhone = phone == null ? "" : phone;
        this.initialEmail = email == null ? "" : email;
        this.initialDate = date == null ? "" : date;
        this.orderSummaryText = orderSummary;
        this.totalAmount = total;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 720);
        setLocationRelativeTo(null);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        initUI();
    }

    private JTextField createPlaceholderField(String placeholder, int width) {
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(width,30));
        tf.setBackground(INPUT_BG);
        tf.setForeground(PLACEHOLDER);
        tf.setBorder(BorderFactory.createEmptyBorder(6,8,6,8));
        tf.setText(placeholder);

        tf.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                if (tf.getForeground().equals(PLACEHOLDER)){
                    tf.setText("");
                    tf.setForeground(Color.WHITE);
                }
            }
            public void focusLost(FocusEvent e){
                if (tf.getText().trim().isEmpty()){
                    tf.setForeground(PLACEHOLDER);
                    tf.setText(placeholder);
                }
            }
        });

        return tf;
    }

    private JPanel labeledPanel(String labelText, JTextField field){
        JPanel p = new JPanel(new BorderLayout(6,6));
        p.setOpaque(false);

        JLabel lbl = new JLabel(labelText);
        lbl.setForeground(new Color(230,245,240));
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));

        p.add(lbl, BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);

        return p;
    }

    private JTextField createDateField(int width){
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(width,30));
        tf.setBackground(INPUT_BG);
        tf.setForeground(PLACEHOLDER);
        tf.setBorder(BorderFactory.createEmptyBorder(6,8,6,8));
        tf.setText("Klik untuk memilih tanggal");
        tf.setEditable(false);
        tf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        tf.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent e){
                java.util.Date d = showDatePicker(DataPembelian.this);
                if (d!=null){
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                    tf.setText(sdf.format(d));
                    tf.setForeground(Color.WHITE);
                }
            }
        });

        return tf;
    }

    private java.util.Date showDatePicker(Component parent){
        JDialog d = new JDialog(SwingUtilities.getWindowAncestor(parent), "Pilih Tanggal",
                Dialog.ModalityType.APPLICATION_MODAL);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(new EmptyBorder(8,8,8,8));

        SpinnerDateModel model = new SpinnerDateModel(new java.util.Date(), null, null,
                java.util.Calendar.DAY_OF_MONTH);
        JSpinner spinner = new JSpinner(model);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd-MM-yyyy"));

        p.add(spinner, BorderLayout.CENTER);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        btns.add(cancel);
        btns.add(ok);
        p.add(btns, BorderLayout.SOUTH);

        final java.util.Date[] result = new java.util.Date[1];
        ok.addActionListener(ev -> {
            result[0] = (java.util.Date)spinner.getValue();
            d.dispose();
        });
        cancel.addActionListener(ev -> {
            result[0] = null;
            d.dispose();
        });

        d.getContentPane().add(p);
        d.pack();
        d.setLocationRelativeTo(parent);
        d.setVisible(true);

        return result[0];
    }

    private void onSubmit(){
        // collect values but ignore placeholder hints
        String name = (nameField.getForeground().equals(PLACEHOLDER) || nameField.getText().trim().isEmpty()) ? "" : nameField.getText().trim();
        String email = (emailField.getForeground().equals(PLACEHOLDER) || emailField.getText().trim().isEmpty()) ? "" : emailField.getText().trim();
        String phone = (phoneField.getForeground().equals(PLACEHOLDER) || phoneField.getText().trim().isEmpty()) ? "" : phoneField.getText().trim();
        String date = (dateField.getForeground().equals(PLACEHOLDER) || dateField.getText().trim().isEmpty()) ? "" : dateField.getText().trim();

        // Open final confirmation / data display page (DataMasukan) and pass collected data + order summary
        SwingUtilities.invokeLater(() -> {
            DataMasukan dm = new DataMasukan(name, phone, email, date, orderSummaryText, totalAmount);
            dm.setVisible(true);
        });
        // close this form
        this.dispose();
    }

    private static String spaced(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (i < s.length()-1) sb.append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataPembelian f = new DataPembelian();
            f.setVisible(true);
        });
    }
}
