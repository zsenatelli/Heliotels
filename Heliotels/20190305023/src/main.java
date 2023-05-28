package swing;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {
        swing.kullanıcıGirişi kullanıcıGirişi = new swing.kullanıcıGirişi();
        kullanıcıGirişi.setVisible(true);
    }
}
