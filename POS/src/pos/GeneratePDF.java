package pos;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneratePDF {

    String fileName = "E:\\programs\\GeneratePDF\\inventory_report.pdf";
    String logoPath = "E:\\programs\\GeneratePDF\\logo.png";
    String jdbcURL = "jdbc:mysql://localhost:3306/POS";
    String dbUser = "root";
    String dbPassword = "xerxes54";

    public void createPDF() {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(100, 100);
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);

            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("Inventory Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());
            document.add(new Paragraph("Report Date and Time: " + currentDateTime));

            document.add(new Paragraph(" "));
            retrieveTableData(document);
            document.close();

            System.out.println("PDF generated successfully!");

        } catch (FileNotFoundException | DocumentException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrieveTableData(Document document) throws SQLException, ClassNotFoundException, DocumentException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String query = "SELECT item_name, quantity, price FROM inventory";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        PdfPTable table = new PdfPTable(3); // Create a table with 3 columns

        table.addCell("Item Name");   // Add column headers
        table.addCell("Quantity");
        table.addCell("Price");

        while (resultSet.next()) {
            String itemName = resultSet.getString("item_name");//if saved as string in sql table
            int quantity = resultSet.getInt("quantity");//if saved as integer in sql table
            double price = resultSet.getDouble("price");//if saved as double in sql table

            table.addCell(itemName);   // Add item details as table cells
            table.addCell(String.valueOf(quantity));
            table.addCell(String.valueOf(price));
        }

        document.add(table);  // Add the table to the document

        resultSet.close();
        statement.close();
        conn.close();
    }

/* Public static void main(String[] args) {
        GeneratePDF generatePDF = new GeneratePDF();
        generatePDF.createPDF();
    }*/
}
