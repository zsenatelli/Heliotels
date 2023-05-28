package swing;
import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class yeniÜye extends JFrame {
    public yeniÜye() {

        setTitle("Üye Ol");
        setSize(425, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Başlık label
        JLabel lblTitle = new JLabel("Seni biraz tanıyalım");
        lblTitle.setBounds(65, 30, 250, 40);
        lblTitle.setFont(lblTitle.getFont().deriveFont(24.0f)); // Büyük yazı boyutu
        add(lblTitle);

        //TC
        JLabel lblTC = new JLabel("Kullanıcı TC'si:");
        lblTC.setBounds(50, 100, 100, 25);
        add(lblTC);

        JTextField txtTC = new JTextField();
        txtTC.setBounds(160, 100, 150, 25);
        add(txtTC);

        //Şifre
        JLabel lblPassword = new JLabel("Şifre:");
        lblPassword.setBounds(50, 150, 100, 25);
        add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 150, 150, 25);
        add(txtPassword);

        //Şifre Tekrar
        JLabel lblConfirmPassword = new JLabel("Şifre Tekrar:");
        lblConfirmPassword.setBounds(50, 200, 100, 25);
        add(lblConfirmPassword);

        JPasswordField txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(160, 200, 150, 25);
        add(txtConfirmPassword);

        //İsim
        JLabel lblName = new JLabel("İsim:");
        lblName.setBounds(50, 250, 100, 25);
        add(lblName);

        JTextField txtName = new JTextField();
        txtName.setBounds(160, 250, 150, 25);
        add(txtName);

        //Soyisim
        JLabel lblsurName = new JLabel("Soyisim:");
        lblsurName.setBounds(50, 300, 100, 25);
        add(lblsurName);

        JTextField txtsurName = new JTextField();
        txtsurName.setBounds(160, 300, 150, 25);
        add(txtsurName);

        //Cinsiyet
        JLabel lblGender = new JLabel("Cinsiyet:");
        lblGender.setBounds(50, 350, 100, 25);
        add(lblGender);

        String[] genderOptions = {"Kadın", "Erkek"};
        JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);

        genderComboBox.setBounds(160, 350, 150, 25);
        add(genderComboBox);

        //doğum yılı
        JLabel lblYear = new JLabel("Doğum yılı:");
        lblYear.setBounds(50, 400, 100, 25);
        add(lblYear);

        JTextField txtYear = new JTextField();
        txtYear.setBounds(160, 400, 150, 25);
        add(txtYear);

        //Şehir
        JLabel lblCity = new JLabel("Yaşadığınız şehir:");
        lblCity.setBounds(50, 450, 100, 25);
        add(lblCity);

        JTextField txtCity = new JTextField();
        txtCity.setBounds(160, 450, 150, 25);
        add(txtCity);

        JButton btnSignUp = new JButton("Kayıt Ol");
        btnSignUp.setBounds(160, 500, 100, 25);
        add(btnSignUp);

        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tc = txtTC.getText();
                String isim = txtName.getText();
                String soyisim = txtsurName.getText();
                String cinsiyet = (String) genderComboBox.getSelectedItem();
                String yıl = txtYear.getText();
                String şehir = txtCity.getText();
                String password = new String(txtPassword.getPassword());
                String confirmPassword = new String(txtConfirmPassword.getPassword());

                if(txtTC.getText().equals("") ) {
                    JOptionPane.showMessageDialog(yeniÜye.this, "Boş alanları doldurunuz!");
                }
                else if(txtName.getText().equals("") ) {
                    JOptionPane.showMessageDialog(yeniÜye.this, "Boş alanları doldurunuz!");
                }
                else if(txtsurName.getText().equals("") ) {
                    JOptionPane.showMessageDialog(yeniÜye.this, "Boş alanları doldurunuz!");
                }
                else if(txtYear.getText().equals("") ) {
                    JOptionPane.showMessageDialog(yeniÜye.this, "Boş alanları doldurunuz!");
                }
                else if(txtCity.getText().equals("") ) {
                    JOptionPane.showMessageDialog(yeniÜye.this, "Boş alanları doldurunuz!");
                }
                else if(txtPassword.getText().equals("") ) {
                    JOptionPane.showMessageDialog(yeniÜye.this, "Boş alanları doldurunuz!");
                }
                else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(yeniÜye.this, "Şifreler uyuşmuyor!");
                } else {
                    // MySQL veritabanına bağlanma bilgileri
                    String url = "jdbc:mysql://localhost:3306/heliotels";
                    String user = "root";
                    String userpassword = "1234";
                    try (Connection connection = DriverManager.getConnection(url, user, userpassword)) {
                        // SQL sorgusu için hazırlık
                        String query = "INSERT INTO kullanıcıbilgileri (TC,isim,soyisim,cinsiyet,doğumYılı,şehir,şifre) VALUES (?,?,?,?,?,?,?)";
                        PreparedStatement statement = connection.prepareStatement(query);

                        // Parametreleri ayarlama
                        statement.setString(1, tc);
                        statement.setString(2, isim);
                        statement.setString(3, soyisim);
                        statement.setString(4, cinsiyet);
                        statement.setString(5, yıl);
                        statement.setString(6, şehir);
                        statement.setString(7, password);


                        // Sorguyu çalıştırma
                        int rowsAffected = statement.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(yeniÜye.this, "Kayıt başarılı! TC numaranız: " + tc + ", Şifre: " + password);
                            swing.kullanıcıGirişi kg=new swing.kullanıcıGirişi();
                            kg.setVisible(true);;
                        } else {
                            JOptionPane.showMessageDialog(yeniÜye.this, "Kayıt başarısız!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(yeniÜye.this, "Veritabanı hatası!");
                    }
                    setVisible(false);
                }
                }


        });


    }

}




