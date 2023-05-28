package swing;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class oteller extends JFrame {
    public oteller() {
        setTitle("Otel Sayfası");
        setSize(1920, 1030);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        int panelWidth = 400;
        int panelHeight = 350;
        int panelSpacing = 20;

        int startX = 20;
        int startY = 20;

        try {
            // Veritabanı bağlantısı kurma
            String url = "jdbc:mysql://localhost:3306/heliotels";
            String username = "root";
            String userpassword = "1234";
            Connection connection = DriverManager.getConnection(url, username, userpassword);

            // SQL sorgusu oluşturma
            String sql = "SELECT * FROM oteller ORDER BY puan DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int count = 0;

            while (resultSet.next() && count < 8) {
                int otelID = resultSet.getInt("otelID");
                String otelIsim = resultSet.getString("otelisim");
                String otelKonum = resultSet.getString("otelkonum");
                int kisiSayisi = resultSet.getInt("kişiSayısı");
                int puan = resultSet.getInt("puan");
                int fiyat = resultSet.getInt("fiyat");
            JPanel hotelPanel = new JPanel();
            hotelPanel.setBounds(startX + (count % 4) * (panelWidth + panelSpacing), startY + (count / 4) * (panelHeight + panelSpacing), panelWidth, panelHeight);
            hotelPanel.setLayout(null);
            add(hotelPanel);

            JLabel imageLabel = new JLabel(new ImageIcon("C:\\Users\\kilic\\OneDrive\\Masaüstü\\Heliotels\\oteller\\otel_" + (count + 1) + ".jpg"));
            imageLabel.setBounds(-50, 0, panelWidth, panelHeight * 2 / 3);
            hotelPanel.add(imageLabel);

            JLabel nameLabel = new JLabel(otelIsim);
            nameLabel.setBounds(10, panelHeight * 2 / 3, panelWidth - 20, 30);
            hotelPanel.add(nameLabel);

            JLabel cityLabel = new JLabel("Konum: " + otelKonum);
            cityLabel.setBounds(10, panelHeight * 2 / 3 + 40, panelWidth - 20, 20);
            hotelPanel.add(cityLabel);

            JLabel capacityLabel = new JLabel("Kapasite: " + kisiSayisi + " kişi");
            capacityLabel.setBounds(10, panelHeight * 2 / 3 + 60, panelWidth - 20, 20);
            hotelPanel.add(capacityLabel);

            JLabel ratingLabel = new JLabel("Puan: " + puan);
            ratingLabel.setBounds(10, panelHeight * 2 / 3 + 80, panelWidth - 20, 20);
            hotelPanel.add(ratingLabel);

            JLabel priceLabel = new JLabel("Fiyat: " + fiyat + " TL");
            priceLabel.setBounds(10, panelHeight * 2 / 3 + 100, panelWidth - 20, 20);
            hotelPanel.add(priceLabel);

            JButton rentButton = new JButton("Kirala");
            rentButton.setBounds(panelWidth / 2 - 45, panelHeight - 40, 90, 30);
            hotelPanel.add(rentButton);
            count++;
                rentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    swing.krediKartı krediKartı = new swing.krediKartı();
                    krediKartı.setVisible(true);
                }
            });
            }
            // Kaynakları serbest bırakma
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    }

