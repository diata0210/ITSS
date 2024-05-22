package app.controller;


import app.models.SiteOrder;
import app.models.WHCheckedTable;
import app.repositories.WarehouseRepository;
import app.repositories.implement.WarehouseRepositoryImp;
import app.services.WarehouseServiceImp;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NKController implements Initializable {
    WarehouseRepository warehouseRepository ;
    WarehouseServiceImp warehouseServiceImp ;
    private ObservableList<WHCheckedTable> orderDetails;
    private FilteredList<WHCheckedTable> filteredOrders;
    private SiteOrder siteOrder;

    @FXML
    private TableView<WHCheckedTable> table;

    @FXML
    private TableColumn<WHCheckedTable, Integer> stt;

    @FXML
    private TableColumn<WHCheckedTable, Integer> productID;

    @FXML
    private TableColumn<WHCheckedTable, String> productName;

    @FXML
    private TableColumn<WHCheckedTable, BigDecimal> price;

    @FXML
    private TableColumn<WHCheckedTable, Integer> quantity;

    @FXML
    private TableColumn<WHCheckedTable, Integer> checked;



    @FXML
    private TextField finalPrice;

    @FXML
    private TextField inputValue;

    @FXML
    private ComboBox<String> statuspValue;

    @FXML
    private TextField orderCodeValue;

    @FXML
    private TextField statusValue;

    @FXML
    private TextField siteValue;

    @FXML
    void back(MouseEvent event) {
        // Your code here
    }
    @FXML
    private Text code;
    @FXML
    void reCreateOrder(ActionEvent event) {
        // Your code here
    }
    @FXML
    public void saveAndExportPDF(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Chọn thư mục lưu tệp PDF");
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            String pdfFileName = "phieu_kiem_kho.pdf"; // Tên tệp PDF
            File pdfFile = new File(selectedDirectory, pdfFileName);

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
                document.open();
                Paragraph title = new Paragraph("PHIEU NHAP KHO");
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);



                PdfPTable pdfTable = new PdfPTable(6); // 6 cột tương ứng với các cột trong TableView
                pdfTable.addCell("STT");
                pdfTable.addCell("Product ID");
                pdfTable.addCell("Product Name");
                pdfTable.addCell("Quantity");
                pdfTable.addCell("Price");
                pdfTable.addCell("Checked");






                for (WHCheckedTable item : table.getItems()) {
                    pdfTable.addCell(String.valueOf(table.getItems().indexOf(item) + 1));
                    pdfTable.addCell(String.valueOf(item.getProductID()));
                    pdfTable.addCell(item.getProductName());
                    pdfTable.addCell(String.valueOf(item.getQuantity()));
                    pdfTable.addCell(item.getPrice().toString());
                    pdfTable.addCell(String.valueOf(item.getChecked()));
                }

                document.add(pdfTable);
                document.add(new Paragraph("Gia tri thuc te:"+ this.siteOrder.getActualValue()));
                document.add(new Paragraph("Chu ky cua nguoi nhap kho"));
                document.close();

                System.out.println("PDF đã được lưu thành công tại: " + pdfFile.getAbsolutePath());
            } catch (DocumentException | IOException e) {
                System.out.println("Không thể lưu file PDF: " + e.getMessage());
            }
        }
    }


    public void initializeTable() {
        stt.setCellValueFactory(column ->
                new ReadOnlyObjectWrapper<>(table.getItems().indexOf(column.getValue()) + 1)
        );
        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        checked.setCellValueFactory(new PropertyValueFactory<>("checked"));


    }

    public void loadData(SiteOrder siteOrder) {
        this.siteOrder = siteOrder;
        List<WHCheckedTable> whCheckeds = siteOrder.getWHCheckeds();
        System.out.println(whCheckeds.get(1).getPrice());
        orderDetails = FXCollections.observableArrayList(whCheckeds);
        table.setItems(orderDetails);
        code.setText(String.valueOf(siteOrder.getID()));

//        orderCodeValue.setText(String.valueOf(siteOrder.getID()));
//        statusValue.setText(siteOrder.getOStatus());
//        siteValue.setText(siteOrder.getSiteName());
//        finalPrice.setText(String.valueOf(siteOrder.getFinalPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warehouseRepository = new WarehouseRepositoryImp();
        warehouseServiceImp = new WarehouseServiceImp();
        warehouseServiceImp.setWarehouseRespository(warehouseRepository);
        initializeTable();
    }
}

