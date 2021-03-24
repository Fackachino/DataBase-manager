package sample;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionHandler {
    Driver driver = new oracle.jdbc.driver.OracleDriver();
    String userName;
    String password;
    Connection connection = null;
    Statement st = null;
    ResultSet rs = null;
    private ResultSetMetaData rsmd;

    public ResultSetMetaData getRsmd() {
        return rsmd;
    }

    public ResultSet getRs() {
        return rs;
    }

    private int numberColumns = 0;
    private ArrayList<String> listOfColumns = new ArrayList<>();
    private ArrayList<String> listOfTypes = new ArrayList<>();

    public ConnectionHandler(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public boolean connect(){
        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.0.15:1521:xe", userName, password);
            System.out.println("Подключение установлено");
            return true;
        } catch (SQLException e){
            System.out.println("Не получилось подключиться");
            e.getSQLState();
            return false;
        }
    }

    public ArrayList<String> getListOfColumns() {
        return listOfColumns;
    }

    public ArrayList<String> getListOfTypes() {
        return listOfTypes;
    }

    public void request(String sql){
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            rsmd = rs.getMetaData();
            numberColumns = rsmd.getColumnCount();
            System.out.println("No. of columns : " + rsmd.getColumnCount());

            for (int i = 1; i <= rsmd.getColumnCount(); i++){
                System.out.println("Column name of " + i + " column : " + rsmd.getColumnName(i));
                listOfColumns.add(rsmd.getColumnName(i));
                System.out.println("Column type of "  + i + " column : " + rsmd.getColumnTypeName(i));
                listOfTypes.add(rsmd.getColumnTypeName(i));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }

}
