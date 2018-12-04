import java.sql.*;

public class DatabaseConnection {
    private static Connection openConnetion = null;
    public static Connection getConnection(){
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/model",
                "root",
                "coderslab")) {
            return connection;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getEfficientConnection() throws SQLException{
        if(openConnetion ==null || openConnetion.isClosed()){

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/model",
                    "root",
                    "coderslab");
            openConnetion=connection;
        }
        return openConnetion;
    }

    /*
        private static Connection openConnection = null;
    public static Connection getConnection() {
            try (Connection newConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mojabaza?" +
                    "useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=" +
                    "false&serverTimezone=UTC", "root", "dupa")) {
                return newConnection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
    public static Connection getEfficientConnection() throws SQLException{
        if(openConnection == null || openConnection.isClosed()) {
            Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mojabaza?" +
                "useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=" +
                "false&serverTimezone=UTC", "root", "dupa");
                openConnection = newConnection;
            }

        return openConnection;
    }
    * */

}