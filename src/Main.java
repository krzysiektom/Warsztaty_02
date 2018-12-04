import Model.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("test");
        User user = new User();
        try {
            user= User.loadUserById(DatabaseConnection.getEfficientConnection(),1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());
    }
}
