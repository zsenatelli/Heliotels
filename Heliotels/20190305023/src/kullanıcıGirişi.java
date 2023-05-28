package swing;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class kullanıcıGirişi extends JFrame {
    public kullanıcıGirişi() {

        // JFrame oluştur
        setTitle("Kullanıcı Girişi");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Başlık label
        JLabel lblTitle = new JLabel("HELİOTELS");
        lblTitle.setBounds(200, 50, 200, 40);
        lblTitle.setFont(lblTitle.getFont().deriveFont(24.0f)); // Büyük yazı boyutu
        add(lblTitle);

        // Kullanıcı adı etiketi
        JLabel lblUsername = new JLabel("Kullanıcı TC'si:");
        lblUsername.setBounds(150, 150, 100, 25);
        add(lblUsername);

        // Kullanıcı adı alanı
        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(250, 150, 200, 25);
        add(txtUsername);

        // Şifre etiketi
        JLabel lblPassword = new JLabel("Şifre:");
        lblPassword.setBounds(150, 200, 100, 25);
        add(lblPassword);

        // Şifre alanı
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(250, 200, 200, 25);
        add(txtPassword);

        // Giriş butonu
        JButton btnLogin = new JButton("Giriş");
        btnLogin.setBounds(250, 250, 90, 25);
        add(btnLogin);

        // Üye Ol butonu
        JButton btnsignUp = new JButton("Üye Ol");
        btnsignUp.setBounds(360, 250, 90, 25);
        add(btnsignUp);

        btnsignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                swing.yeniÜye yeniÜye = new swing.yeniÜye();
                yeniÜye.setVisible(true);
            }
        });


        // Giriş butonu için ActionListener
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                // MySQL veritabanına bağlanma bilgileri
                String url = "jdbc:mysql://localhost:3306/heliotels";
                String user = "root";
                String userpassword = "1234";


                // Bağlantı nesnesi oluşturma
                try (Connection connection = DriverManager.getConnection(url, user, userpassword)) {
                    // SQL sorgusu için hazırlık
                    String query = "SELECT * FROM kullanıcıbilgileri WHERE TC = ? AND şifre = ?";
                    PreparedStatement statement = connection.prepareStatement(query);

                    // Parametreleri ayarlama
                    statement.setString(1, username);
                    statement.setString(2, password);

                    // Sorguyu çalıştırma
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        int sonTC = resultSet.getInt("ID");
                        JOptionPane.showMessageDialog(kullanıcıGirişi.this, "Giriş başarılı!");
                        swing.oteller otel=new swing.oteller();
                        otel.setVisible(true);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(kullanıcıGirişi.this, "Kullanıcı adı veya şifre yanlış!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(kullanıcıGirişi.this, "Veritabanı hatası!");
                }
            }
        });

    }

}



