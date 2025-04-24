import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewTrip extends JFrame {
    JTable table;
    DefaultTableModel model;

    public ViewTrip() {
        setTitle("All Trips");
        setSize(500, 400);
        setLayout(null);

        model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("ID");
        model.addColumn("Travel Name");
        model.addColumn("Destination");
        model.addColumn("Ticket Price");

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 450, 300);
        add(scrollPane);

        loadData();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadData() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM travel_info");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("travel_name");
                String destination = rs.getString("destination");
                double price = rs.getDouble("ticket_price");
                model.addRow(new Object[]{id, name, destination, price});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}