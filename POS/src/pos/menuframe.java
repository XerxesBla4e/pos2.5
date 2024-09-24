package pos;

import com.itextpdf.text.DocumentException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
  
public class MenuFrame extends JFrame {

    public MenuFrame() {
        // Set the frame title
        setTitle("Menu Example");

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the "Reports" menu
        JMenu reportsMenu = new JMenu("Reports");

        // Create submenus for "Reports"
        JMenuItem salesReport = new JMenuItem("Sales Report");
        JMenuItem inventoryReport = new JMenuItem("Inventory Report");
        JMenuItem customerReport = new JMenuItem("Customer Report");

        // Add action listeners to the submenus (optional)
        inventoryReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GeneratePDF gpdf = new GeneratePDF();
               gpdf.createPDF();
           
            } 
        });

        inventoryReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Inventory Report Selected");
            }
        });

        customerReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Customer Report Selected");
            }
        });

        // Add the submenu items to the "Reports" menu
        reportsMenu.add(salesReport);
        reportsMenu.add(inventoryReport);
        reportsMenu.add(customerReport);

        // Add "Reports" to the menu bar
        menuBar.add(reportsMenu);

        // Create another main menu (e.g., "Help")
        JMenu helpMenu = new JMenu("Data");
        JMenuItem page1 = new JMenuItem("page1");
        JMenuItem page2 = new JMenuItem("page2");
        JMenuItem page3 = new JMenuItem("page3");

        // Add an action listener to the "About" item
        page1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    main xer = new main();
                    xer.setVisible(true);
            }
        });

        helpMenu.add(page1);
        helpMenu.add(page2);
        helpMenu.add(page3);

        // Add "Help" to the menu bar
        menuBar.add(helpMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Set the size of the frame and close operation
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
    }

    public static void main(String[] args) {
        // Create and display the frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuFrame().setVisible(true);
            }
        });
    }
}
