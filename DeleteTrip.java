import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteTrip extends JFrame {
    JTextField idField;
    JButton deleteBtn;

    public DeleteTrip() {
        setTitle("Delete Trip");
        setLayout(null);
        setSize(350, 200);

        JLabel idLabel = new JLabel("Enter ID to Delete:");
        idField = new JTextField();
        deleteBtn = new JButton("Delete");

        idLabel.setBounds(50, 30, 150, 30);
        idField.setBounds(160, 30, 100, 30);
        deleteBtn.setBounds(110, 80, 100, 30);

        add(idLabel);
        add(idField);
        add(deleteBtn);

        deleteBtn.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid ID!");
                return;
            }

            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM travel_info WHERE id = ?");
                ps.setInt(1, id);
                int rows = ps.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Trip deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No trip found with ID: " + id);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}