import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateTrip extends JFrame {
    JTextField idField, nameField, destinationField, priceField;
    JButton searchBtn, updateBtn;

    public UpdateTrip() {
        setTitle("Update Trip");
        setLayout(null);
        setSize(400, 400);

        JLabel idLabel = new JLabel("Enter ID:");
        JLabel nameLabel = new JLabel("Travel Name:");
        JLabel destinationLabel = new JLabel("Destination:");
        JLabel priceLabel = new JLabel("Ticket Price:");

        idField = new JTextField();
        nameField = new JTextField();
        destinationField = new JTextField();
        priceField = new JTextField();
        searchBtn = new JButton("Search");
        updateBtn = new JButton("Update");

        idLabel.setBounds(50, 30, 100, 30);
        idField.setBounds(160, 30, 150, 30);
        searchBtn.setBounds(130, 70, 100, 30);

        nameLabel.setBounds(50, 120, 100, 30);
        nameField.setBounds(160, 120, 150, 30);
        destinationLabel.setBounds(50, 170, 100, 30);
        destinationField.setBounds(160, 170, 150, 30);
        priceLabel.setBounds(50, 220, 100, 30);
        priceField.setBounds(160, 220, 150, 30);
        updateBtn.setBounds(130, 270, 100, 30);

        add(idLabel); add(idField); add(searchBtn);
        add(nameLabel); add(nameField);
        add(destinationLabel); add(destinationField);
        add(priceLabel); add(priceField);
        add(updateBtn);

        searchBtn.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid ID!");
                return;
            }

            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM travel_info WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    nameField.setText(rs.getString("travel_name"));
                    destinationField.setText(rs.getString("destination"));
                    priceField.setText(String.valueOf(rs.getDouble("ticket_price")));
                } else {
                    JOptionPane.showMessageDialog(null, "No data found for ID: " + id);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String destination = destinationField.getText();
                double price = Double.parseDouble(priceField.getText());

                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                    "UPDATE travel_info SET travel_name = ?, destination = ?, ticket_price = ? WHERE id = ?");
                ps.setString(1, name);
                ps.setString(2, destination);
                ps.setDouble(3, price);
                ps.setInt(4, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Trip updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}