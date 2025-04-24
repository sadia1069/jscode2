import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame {
    JButton addBtn, viewBtn, updateBtn, deleteBtn, exitBtn;

    public Main() {
        setTitle("Travel Management System");
        setLayout(null);
        setSize(400, 350);

        addBtn = new JButton("Add Trip");
        viewBtn = new JButton("View Trips");
        updateBtn = new JButton("Update Trip");
        deleteBtn = new JButton("Delete Trip");
        exitBtn = new JButton("Exit");

        addBtn.setBounds(100, 30, 200, 40);
        viewBtn.setBounds(100, 80, 200, 40);
        updateBtn.setBounds(100, 130, 200, 40);
        deleteBtn.setBounds(100, 180, 200, 40);
        exitBtn.setBounds(100, 230, 200, 40);

        add(addBtn);
        add(viewBtn);
        add(updateBtn);
        add(deleteBtn);
        add(exitBtn);

        addBtn.addActionListener(e -> new AddTrip());
        viewBtn.addActionListener(e -> new ViewTrip());
        updateBtn.addActionListener(e -> new UpdateTrip());
        deleteBtn.addActionListener(e -> new DeleteTrip());
        exitBtn.addActionListener(e -> System.exit(0));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}