package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Filler {
    static ObservableList data = FXCollections.observableArrayList();
    static int n;
    public static void fillColumnNames(ConnectionHandler connectionHandler, TableView table){
        data.clear();
        table.getColumns().clear();
        try {
            ResultSetMetaData rsmd = connectionHandler.getRsmd();
            ResultSet rs = connectionHandler.getRs();
            n = rsmd.getColumnCount();
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                final int j = i;
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                table.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");

            }

            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int j=1 ;  j<=rs.getMetaData().getColumnCount(); j++){
                    //Iterate Column
                    row.add(rs.getString(j));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);


            }

            table.setItems(data);
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
}
