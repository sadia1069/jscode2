import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddTrip extends JFrame {
    JTextField nameField, destinationField, priceField;
    JButton saveButton;

    public AddTrip() {
        setTitle("Add New Trip");
        setLayout(null);
        setSize(400, 300);

        JLabel nameLabel = new JLabel("Travel Name:");
        JLabel destinationLabel = new JLabel("Destination:");
        JLabel priceLabel = new JLabel("Ticket Price:");

        nameField = new JTextField();
        destinationField = new JTextField();
        priceField = new JTextField();
        saveButton = new JButton("Save");

        nameLabel.setBounds(50, 30, 100, 30);
        nameField.setBounds(160, 30, 150, 30);
        destinationLabel.setBounds(50, 80, 100, 30);
        destinationField.setBounds(160, 80, 150, 30);
        priceLabel.setBounds(50, 130, 100, 30);
        priceField.setBounds(160, 130, 150, 30);
        saveButton.setBounds(130, 190, 100, 30);

        add(nameLabel); add(nameField);
        add(destinationLabel); add(destinationField);
        add(priceLabel); add(priceField);
        add(saveButton);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String destination = destinationField.getText();
            double price;

            try {
                price = Double.parseDouble(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid ticket price!");
                return;
            }

            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO travel_info (travel_name, destination, ticket_price) VALUES (?, ?, ?)");
                ps.setString(1, name);
                ps.setString(2, destination);
                ps.setDouble(3, price);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Trip Added Successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}