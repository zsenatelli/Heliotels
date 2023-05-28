package swing;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class krediKartı extends JFrame  {
    public krediKartı(){
        setTitle("Kredi Kartı Bilgileri");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel numaraLabel = new JLabel("Kart Numarası:");
        numaraLabel.setBounds(40, 50, 100, 20);
        add(numaraLabel);

        JTextField numaraText = new JTextField();
        numaraText.setBounds(160, 50, 200, 20);
        add(numaraText);

        JLabel isimsoyisimLabel = new JLabel("İsim Soyisim:");
        isimsoyisimLabel.setBounds(40, 80, 100, 20);
        add(isimsoyisimLabel);

        JTextField isimsoyisimText = new JTextField();
        isimsoyisimText.setBounds(160, 80, 200, 20);
        add(isimsoyisimText);

        JLabel sonkullanmaLabel = new JLabel("Son Kullanma Tarihi:");
        sonkullanmaLabel.setBounds(40, 110, 120, 20);
        add(sonkullanmaLabel);

        String[] sonkullanmaAyOptions = {"1", "2","3", "4","5", "6","7", "8","9", "10","11", "12",};
        JComboBox<String> sonkullanmaAyComboBox = new JComboBox<>(sonkullanmaAyOptions);
        sonkullanmaAyComboBox.setBounds(160, 110, 60, 20);
        add(sonkullanmaAyComboBox);

        String[] sonkullanmaYılOptions = {"2023", "2024","2025", "2026","2027", "2028","2029", "2030"};
        JComboBox<String> sonkullanmaYılComboBox = new JComboBox<>(sonkullanmaYılOptions);
        sonkullanmaYılComboBox.setBounds(230, 110, 60, 20);
        add(sonkullanmaYılComboBox);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setBounds(40, 140, 100, 20);
        add(cvvLabel);

        JTextField cvvText = new JTextField();
        cvvText.setBounds(160, 140, 60, 20);
        add(cvvText);

        JButton submitButton = new JButton("Gönder");
        submitButton.setBounds(160, 180, 80, 30);
        add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numara = numaraText.getText();
                String isimsoyisim = isimsoyisimText.getText();
                String sonkullanma = (String)sonkullanmaAyComboBox.getSelectedItem()+"/"+(String)sonkullanmaYılComboBox.getSelectedItem();
                String cvv = cvvText.getText();
                // Database connection information
                String url = "jdbc:mysql://localhost:3306/heliotels";
                String username = "root";
                String userpassword = "1234";
                if(numaraText.getText().equals("") ) {
                    JOptionPane.showMessageDialog(swing.krediKartı.this, "Boş alanları doldurunuz!");
                }
                else if(isimsoyisimText.getText().equals("") ) {
                    JOptionPane.showMessageDialog(swing.krediKartı.this, "Boş alanları doldurunuz!");
                }
                else if(cvvText.getText().equals("") ) {
                    JOptionPane.showMessageDialog(swing.krediKartı.this, "Boş alanları doldurunuz!");
                }
                else {
                    try {
                        // Establish database connection
                        Connection connection = DriverManager.getConnection(url, username, userpassword);

                        // Prepare SQL statement
                        String sql = "INSERT INTO kredikartları (kartNumarası, TC, isimSoyisim, sonKullanma, CVV) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, numara);
                        swing.kullanıcıGirişi kg = new swing.kullanıcıGirişi();
                        statement.setString(2, "0");
                        statement.setString(3, isimsoyisim);
                        statement.setString(4, sonkullanma);
                        statement.setString(5, cvv);

                        // Execute the SQL statement
                        int rowsInserted = statement.executeUpdate();

                        if (rowsInserted > 0) {
                            swing.oteller oteller = new swing.oteller();
                            swing.kullanıcıGirişi a = new swing.kullanıcıGirişi();
                            JOptionPane.showMessageDialog(null, "Kredi kartı bilgileriniz başarıyla kaydedildi ve ödeme işleminiz gerçekleşti");
                            setVisible(false);
                        }

                        // Close resources
                        statement.close();
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Kredi kartı bilgileri kaydedilirken bir hata oluştu!");
                    }
                }
            }
        });


    }
            }





