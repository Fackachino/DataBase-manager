package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ConnectionHandler;

import java.io.IOException;

public class ControllerAuthorization {
    private static ConnectionHandler connectionHandler;
    @FXML
    private Button btnAutho;

    @FXML
    private TextField fldUserName;

    @FXML
    private TextField fldPass;

    private static Stage mainStage;
    @FXML
    void initialize() {
       btnAutho.setOnAction(actionEvent -> {
          String userName = fldUserName.getText();
          String pass = fldPass.getText();
          connectionHandler = new ConnectionHandler(userName, pass);


          if(connectionHandler.connect()){
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(getClass().getResource("../view/mainApp.fxml"));

              try {
                  loader.load();
              } catch (IOException e) {
                  e.printStackTrace();
                  System.out.println("");
              }
              mainStage.close();
              Parent root = loader.getRoot();
              Stage stage = new Stage();
              stage.setScene(new Scene(root));
              stage.showAndWait();
              stage.setResizable(false);
              stage.setTitle("DataBase management");
          }
          else {
              System.out.println("не смог подключиться");
          }



       });
    }

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static ConnectionHandler getConnectionHandler(){
        return connectionHandler;
    }
}



