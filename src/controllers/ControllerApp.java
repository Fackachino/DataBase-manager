package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.ConnectionHandler;
import sample.Filler;

import java.util.ArrayList;


public class ControllerApp<Table> {
    @FXML
    TableView table;

    @FXML
    Button btnAdd;

    @FXML
    Text txtColumnNumbers;

    @FXML
    TextField fldRequest;

    private static ConnectionHandler connectionHandler;
    private static ArrayList<String> listColumns = new ArrayList<>();

    @FXML
    void initialize() {
        connectionHandler = ControllerAuthorization.getConnectionHandler();
        table.setEditable(true);
        table.isResizable();


        btnAdd.setOnAction(actionEvent -> {
          String request = fldRequest.getText();
            connectionHandler.request(request);

            System.out.println(connectionHandler.getListOfColumns().toString());
            System.out.println(connectionHandler.getListOfTypes().toString());
            Filler.fillColumnNames(connectionHandler, table);


        });
    }
}
